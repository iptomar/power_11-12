/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package NodeJS.Statistics;

import Module.Aplication;
import Module.DataBase.Database;
import Module.GlobalData;
import Module.WebHTTP.SolverCreator;
import genetics.GenericSolver;
import genetics.Individual;
import genetics.Population;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import powermaster.GeneticEvents;
import powermaster.PowerMaster;
import powermaster.SaveStatus;
import powermaster.SolverThread;
import statistics.Statistics;
import utils.ComparatorIndividual;
import utils.PopulationUtils;

/**
 *
 * @author Joao
 */
public class AsyncStats extends Thread {

    /**
     * Objecto atómico que indica o número de Solver activos
     */
    private volatile AtomicInteger numThreads;
    /**
     * Variável que indica o periodo de saltos entre iterações para anailise de valores
     */
    private int period;
    /**
     * Variável auxiliar no calculo do periodo
     */
    private int aux;
    /**
     * Variável que indica o idCliente a que pertence o problema
     */
    private int idClient;
    /**
     * Variável que indica que o ID problem do problema
     */
    private int idProblem;
    /**
     * Variável de controlo para terminar os solver e o AsyncStats
     */
    private volatile boolean Stop;
    /**
     * Array de threads do tipo SolverThread. Cada obejcto do tipo SolverThread contem um solver.
     */
    private SolverThread[] arrayThread;
    /**
     * Veriável que contem o best fitness que irá ser encontrado por os solvers.
     */
    private int BestToFound;
    /**
     * Variável de controlo para verificar se é de maximização e minimização
     */
    private int controlo;
    /**
     * Contrutor do AsynsStats
     * @param period Espaçamento entre calculos estatisticos (entre iterações)
     * @param idClient Id do cliente
     * @param idProblem Id do problema
     * @param input String JSON com o problema a ser carregado. 
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws JSONException 
     */
    public AsyncStats(int period, int idClient, int idProblem, JSONObject input) throws InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException, JSONException {
        this.period = 0;
        this.aux = period;
        this.idClient = idClient;
        this.idProblem = idProblem;
        this.setName("AsyncThread");
        this.Stop = false;

        //Verificar qual o best fitness a ser encontrado
        try {
            JSONArray problem = input.getJSONArray("algorithm");
            String problemStop = problem.getString(2);
            BestToFound = Integer.parseInt(problemStop.split(" ")[0]);
            controlo = Integer.parseInt(problemStop.split(" ")[3]);
        } catch (Exception e) {
            BestToFound = 0;
        }

        //Inicialização dos diversos solvers.
        numThreads = new AtomicInteger(PowerMaster.NUM_THREADS);
        arrayThread = new SolverThread[PowerMaster.NUM_THREADS];
        for (int i = 0; i < arrayThread.length; i++) {
            GenericSolver solver = SolverCreator.CreateSolver(input);
            solver.SetEvents(new GeneticEvents(PowerMaster.INTERVAL_PART, idClient, idProblem));
            arrayThread[i] = new SolverThread(solver, numThreads);
            arrayThread[i].setName("" + i);
            arrayThread[i].start();
        }

        //this.start();
    }

    /**
     * Método para terminar os solvers e o objecto AsysnStats
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public synchronized void Stop() throws FileNotFoundException, IOException {
        SaveStatus save = new SaveStatus(idClient + "_" + idProblem,this.controlo);
        for (int i = 0; i < arrayThread.length; i++) {
            save.AddPopulation(arrayThread[i].Stop());
        }
        Stop = true;
        //Gravar a população dos solvers num objecto do tipo SavaStatus;
        FileOutputStream fos = new FileOutputStream("Pops/" + idClient + "_" + idProblem);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(save);
        oos.close();
    }

    /**
     * Método para obter de todos os solvers a melhor poulação entre eles através do fitness. (Pedido feito por optimum computing para o problema KnapSack)
     * @return String formatada em json com todos os individuos da população
     */
    public synchronized String getBestPopulation() {
        double bestOfAll = 0;
        int index = -1;//index da melhor população encontrada
        for (int i = 0; i < arrayThread.length; i++) {
            Statistics s = new Statistics(arrayThread[i].getPopulation());
            if (s.getMediaFitnessPopulation() > bestOfAll) {
                index = i;
            }
        }
        String json = "";
        if (index >= 0) {
            json += "'data':[";
            Population p = arrayThread[index].getPopulation();
            int i = 1;
            for (Individual individuo : p) {
                json += "[";
                json += "'" + i + "',";
                json += "'" + individuo.fitness() + "'";
                json += "]";
                if (i < p.getSizePopulation()) {
                    json += ",";
                }
                i++;
            }
            json += "]";

        }
        return json;
    }

    /**
     * Método que permite actializar em tempo real os parametros dos solvers
     * @param input Objecto JSONObject que contem os novos parametros a serem carregados
     */
    public void UpdateParametrs(JSONObject input) {
        JSONArray problem = null;
        String mutationName = null;
        String mutationParms = null;
        try {
            problem = input.getJSONArray("mutation");
            mutationName = problem.getString(0);
            System.out.println("Mutação:" + mutationName);
            mutationParms = problem.getString(1);
            System.out.println("Parametros de Mutação:" + mutationParms + "\n\n");
        } catch (Exception e) {
        }

        String recombinationName = null;
        String recombinationParms = null;
        try {
            problem = input.getJSONArray("recombination");
            recombinationName = problem.getString(0);
            System.out.println("Recominação:" + recombinationName);
            recombinationParms = problem.getString(1);
            System.out.println("Parametros de Recombinação:" + recombinationParms + "\n\n");
        } catch (Exception e) {
        }

        String replacementName = null;
        String replacementParms = null;
        try {
            problem = input.getJSONArray("replacement");
            replacementName = problem.getString(0);
            System.out.println("Replacement:" + replacementName);
            replacementParms = problem.getString(1);
            System.out.println("Parametros de Replacement:" + replacementParms + "\n\n");
        } catch (Exception e) {
        }

        String selectionName = null;
        String selectionParms = null;
        try {
            problem = input.getJSONArray("selection");
            selectionName = problem.getString(0);
            System.out.println("Selection:" + selectionName);
            selectionParms = problem.getString(1);
            System.out.println("Parametros de Replacement:" + selectionParms + "\n\n");
        } catch (Exception e) {
        }

        //Aplicar as alterações a todos os solvers
        for (int i = 0; i < arrayThread.length; i++) {
            GenericSolver solver = arrayThread[i].getSolver();
            if (selectionName != null && selectionParms != null) {
                System.out.println("SetSelection:" + solver.SetSelection(selectionName + " " + selectionParms));
            }
            if (mutationName != null && mutationParms != null) {
                System.out.println("SetMutation:" + solver.SetMutation(mutationName + " " + mutationParms));
            }
            if (replacementName != null && replacementParms != null) {
                System.out.println("SetReplacement:" + solver.SetReplacement(replacementName + " " + replacementParms));
            }
            if (recombinationName != null && recombinationParms != null) {
                System.out.println("SetRecombination:" + solver.SetRecombination(recombinationName + " " + recombinationParms));
            }
        }

    }

    /**
     * Método para obter entre todos os solvers os melhores individuos para um determinado fitness
     * @param Fitness de pesquesa nas populações
     * @return String com todos os indidivudos encontrados e unicos.
     */
    public String getAllUniqueIndividuals(double fitness) {
        //TreeSet result = new TreeSet(new ComparatorIndividual());
        Hashtable ht = new Hashtable();
        for (int i = 0; i < arrayThread.length; i++) {
//            Iterator<Individual> iterator = arrayThread[i].getUniqueIndividuals(fitness).iterator();
            Iterator<Individual> iterator = arrayThread[i].getPopulation().iterator();
            while (iterator.hasNext()) {
                Individual individuo = iterator.next();
                if (ht.containsKey(individuo.toString()) == false && individuo.fitness() == fitness) {
                    ht.put(individuo.toString(), individuo);
                } else {
                    //System.out.println(individuo.toString());
                }
            }
//            for (Individual individuo : arrayThread[i].getUniqueIndividuals(fitness)) {
//                ht.put(individuo.toString(), individuo);
//            }
        }
        StringBuilder sb = new StringBuilder();
        Enumeration e = ht.elements();
        System.out.println("Resultados:");
        while (e.hasMoreElements()) {
            Individual ind = (Individual) e.nextElement();
            sb.append(ind.toString() + " __ " + ind.fitness() + "<br>\n");
            //sb.append(":" + "\n");
            //System.out.println(sb.toString());
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * Método que faz a contagem do número de individuos entre todas as populações com um determinado fitness
     * @param fitness que vai ser utilizado par a pesquisa
     * @return número de individuos
     */
    public int getAllUniqueIndividualsCount(double fitness) {
//        TreeSet result = new TreeSet(new ComparatorIndividual());
//        for (int i = 0; i < arrayThread.length; i++) {
//            //Entra uma collection
//            result.addAll( arrayThread[i].getUniqueIndividuals(fitness));
//        }
//        return result.size();
        Hashtable ht = new Hashtable();
        for (int i = 0; i < arrayThread.length; i++) {
            Iterator<Individual> iterator = arrayThread[i].getPopulation().getPopulation().iterator();
            //Iterator<Individual> iterator = p.iterator();
            while (iterator.hasNext()) {
                Individual individuo = iterator.next();
                if (ht.containsKey(individuo.toString()) == false && individuo.fitness() == fitness) {
                    ht.put(individuo.toString(), individuo);
                }
            }
        }
        return ht.size();
    }

    /**
     * Método para percorrer todas as threads e procurar o maior double
     */
    public double getBestIndividual() {
        double maxFitness = 0, aux = 0;
        if(this.controlo==0){
            for (int i = 0; i < arrayThread.length; i++) {
                if ((aux = PopulationUtils.getBestFitness(arrayThread[i].getPopulation())) > maxFitness) {
                    maxFitness = aux;
                }
            }
        }else{
            maxFitness=10000000;
            for (int i = 0; i < arrayThread.length; i++) {
                if ((aux = PopulationUtils.getBestFitness(arrayThread[i].getPopulation())) < maxFitness) {
                    maxFitness = aux;
                }
            }            
        }
        return maxFitness;
    }

    @Override
    public void run() {

        //Database db = new Database("power", "_p55!gv{7MJ]}dIpPk7n1*0-,hq(PD", "127.0.0.1");
        Database db = new Database(GlobalData.database_user, GlobalData.database_pass, GlobalData.database_location, GlobalData.database_database);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AsyncStats.class.getName()).log(Level.SEVERE, null, ex);
        }

        boolean chegouAoFim = false;
        while (!Stop) {
            try {
                int result_count = db.ExecuteCountQuery(period, idClient, idProblem);
                int numThread = numThreads.get();

                // System.out.println("Async|  Period: " + period + "  Threads working: " + numThread + "  Result count: " + result_count + "  Cliente: " + idClient + "  Problema: " + idProblem);
                if (result_count == 0 && numThread == 0) {
                    chegouAoFim = true;
                    //getAllUniqueIndividuals(getBestIndividual());
                    System.out.println("********************************************-------------" + period);
                    db.ExecuteMedia(period, idClient, idProblem, "" + getAllUniqueIndividualsCount(this.BestToFound), "" + getAllUniqueIndividuals(getBestIndividual()),controlo);
                    Aplication.nodeJS.Emit("end", this.period, this.idClient, this.idProblem);
                    System.out.println("Async Stop");
                    break;
                }

                if (result_count >= numThread && chegouAoFim == false) {
                    System.out.println("********************************************=============" + period);
                    //System.out.println("Fechado"+Aplication.db.Connection.isClosed());
                    boolean temp = db.ExecuteMedia(period, idClient, idProblem, "" + getAllUniqueIndividualsCount(this.BestToFound), " ",controlo);
                    //System.out.println("Async Insertion| Iteration:" + period);
                    Aplication.nodeJS.Emit("run", this.period, this.idClient, this.idProblem);
                    period = period + aux;
                }

                Thread.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();

                System.out.println("Error - Sync Class " + e);
            }
        }

        if (Stop == true) {
            db.ExecuteMedia(period, idClient, idProblem, "" + getAllUniqueIndividualsCount(this.BestToFound), "" + getAllUniqueIndividuals(getBestIndividual()),controlo);
            try {
                Aplication.nodeJS.Emit("end", this.period, this.idClient, this.idProblem);
            } catch (JSONException ex) {
                Logger.getLogger(AsyncStats.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Async Stop By Force");
        }
        try {
            Stop();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AsyncStats.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AsyncStats.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
