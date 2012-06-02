/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package NodeJS.Statistics;

import Module.Aplication;
import Module.DataBase.Database;
import Module.WebHTTP.SolverCreator;
import genetics.GenericSolver;
import genetics.Individual;
import genetics.Population;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.Hashtable;
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

    AtomicInteger numThreads;
    int period;
    int aux;
    int idClient;
    int idProblem;
    volatile boolean Stop;
    private SolverThread[] arrayThread;

    public AsyncStats(int period, int idClient, int idProblem, JSONObject input) throws InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException, JSONException {
        this.period = 0;
        this.aux = period;
        this.idClient = idClient;
        this.idProblem = idProblem;
        this.setName("AsyncThread");
        this.Stop = false;

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

    public synchronized void Stop() throws FileNotFoundException, IOException {
        SaveStatus save = new SaveStatus(idClient + "_" + idProblem);
        for (int i = 0; i < arrayThread.length; i++) {
            save.AddPopulation(arrayThread[i].Stop());
        }

        Stop = true;

        FileOutputStream fos = new FileOutputStream("Pops/" + idClient + "_" + idProblem);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(save);
        oos.close();

        //clients.remove(save);
    }
    
    public synchronized String getBestPopulation(){
        
        double bestOfAll=0;
        int index=-1;//index da melhor população encontrada
        for (int i = 0; i < arrayThread.length; i++) {
            Statistics s = new Statistics(arrayThread[i].getPopulation());
            if(s.getMediaFitnessPopulation()>bestOfAll){
                index=i;
            }
        }   
        String json="";
        if(index>=0){
            json+="'data':[";
            Population p = arrayThread[index].getPopulation();
            int i=1;
            for (Individual individuo : p) {
                json+="[";
                json+="'"+i+"',";
                json+="'"+individuo.fitness()+"'";
                json+="]";
                if(i<p.getSizePopulation()){
                    json+=",";
                }  
                i++;                
            }
            json+="]";

        }
        return json;
    }
    
    
    public void UpdateParametrs(JSONObject input){
        JSONArray problem = null;
        String mutationName= null;
        String mutationParms = null;
        try{
            problem = input.getJSONArray("mutation");
            mutationName = problem.getString(0);
            System.out.println("Mutação:"+mutationName);
            mutationParms = problem.getString(1);
            System.out.println("Parametros de Mutação:"+mutationParms+"\n\n");
        }catch(Exception e){}
        
        String recombinationName = null;
        String recombinationParms = null;
        try{
            problem = input.getJSONArray("recombination");
            recombinationName = problem.getString(0);
            System.out.println("Recominação:"+recombinationName);
            recombinationParms = problem.getString(1);
            System.out.println("Parametros de Recombinação:"+recombinationParms+"\n\n");
        }catch(Exception e){}
        
         String replacementName = null;
         String replacementParms = null;
        try{
            problem = input.getJSONArray("replacement");
            replacementName = problem.getString(0);
            System.out.println("Replacement:"+replacementName);
            replacementParms = problem.getString(1);
            System.out.println("Parametros de Replacement:"+replacementParms+"\n\n");
        }catch(Exception e){}
        
        String selectionName = null;
        String selectionParms = null;
        try{
            problem = input.getJSONArray("selection");
            selectionName = problem.getString(0);
            System.out.println("Selection:"+selectionName);
            selectionParms = problem.getString(1);
            System.out.println("Parametros de Replacement:"+selectionParms+"\n\n");
        }catch(Exception e){}        
        
        for (int i = 0; i < arrayThread.length; i++) {
            GenericSolver solver = arrayThread[i].getSolver();
            if(selectionName!=null && selectionParms != null)
            System.out.println("SetSelection:"+solver.SetSelection(selectionName + " " + selectionParms));
            if(mutationName!=null && mutationParms != null)
            System.out.println("SetMutation:"+solver.SetMutation(mutationName + " " + mutationParms));  
            if(replacementName!=null && replacementParms != null)
            System.out.println("SetReplacement:"+solver.SetReplacement(replacementName + " " + replacementParms));
            if(recombinationName!=null && recombinationParms != null)
            System.out.println("SetRecombination:"+solver.SetRecombination(recombinationName + " " + recombinationParms));         
        }        
        
    }

    public String getAllUniqueIndividuals(double fitness){
        
        TreeSet result = new TreeSet(new ComparatorIndividual());
        Hashtable ht = new Hashtable();
        for (int i = 0; i < arrayThread.length; i++) {
            for (Individual individuo : arrayThread[i].getUniqueIndividuals(fitness)) {
                ht.put(individuo.toString(), individuo);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        Enumeration e = ht.elements();
        System.out.println("Resultados:");
        while(e.hasMoreElements()){
            sb.append(((Individual)e.nextElement()).toString());
            sb.append(":");
            System.out.println(sb.toString());
        }
        
//        for (int i = 0; i < arrayThread.length; i++) {
//            //Entra uma collection
//            result.addAll( arrayThread[i].getUniqueIndividuals(fitness));
//        }
//        StringBuilder sb = new StringBuilder();
//        System.out.println("Resultado Final:");
//        for (Object ind : result) {
//            sb.append(ind.toString());
//            sb.append(":");
//            System.out.println(ind.toString());
//        }
        return sb.toString();
    }
    
    public int getAllUniqueIndividualsCount(double fitness){
        TreeSet result = new TreeSet(new ComparatorIndividual());
        for (int i = 0; i < arrayThread.length; i++) {
            //Entra uma collection
            result.addAll( arrayThread[i].getUniqueIndividuals(fitness));
        }
        return result.size();
    }    
    
    /**
     * Método para percorrer todas as threads e procurar o maior double
     */
    public double getBestIndividual(){
        double maxFitness=0,aux=0;
        for (int i = 0; i < arrayThread.length; i++) {
            if((aux=PopulationUtils.getBestFitness(arrayThread[i].getPopulation()))>maxFitness){
                maxFitness=aux;
            }
        }
        return maxFitness;
    }
    
    @Override
    public void run() {

        //Database db = new Database("power", "_p55!gv{7MJ]}dIpPk7n1*0-,hq(PD", "127.0.0.1");
        Database db = new Database("power", "_p55!gv{7MJ]}dIpPk7n1*0-,hq(PD", "code.dei.estt.ipt.pt", "powercomputing");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AsyncStats.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (!Stop) {
            try {
                int result_count = db.ExecuteCountQuery(period, idClient, idProblem);
                int numThread = numThreads.get();

                // System.out.println("Async|  Period: " + period + "  Threads working: " + numThread + "  Result count: " + result_count + "  Cliente: " + idClient + "  Problema: " + idProblem);
                if (result_count == 0 && numThread == 0) {
                    //getAllUniqueIndividuals(getBestIndividual());
                    System.out.println("********************************************-------------"+period);
                    db.ExecuteMedia(period,idClient, idProblem, ""+getAllUniqueIndividualsCount(getBestIndividual()),""+getAllUniqueIndividuals(getBestIndividual()));                    
                    Aplication.nodeJS.Emit("end", this.period, this.idClient, this.idProblem);
                    System.out.println("Async Stop");
                    break;
                }

                if (result_count >= numThread) {
                    System.out.println("********************************************============="+period);
                    //System.out.println("Fechado"+Aplication.db.Connection.isClosed());
                    boolean temp = db.ExecuteMedia(period, idClient, idProblem, ""+getAllUniqueIndividualsCount(getBestIndividual())," ");
                    //System.out.println("Async Insertion| Iteration:" + period);
                    Aplication.nodeJS.Emit("run", this.period, this.idClient, this.idProblem);
                    period = period + aux;
                }

                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error - Sync Class " + e);
            }
        }
        
//        if(Stop=true){                              
//                db.ExecuteLastItera(idClient, idProblem, getAllUniqueIndividuals(getBestIndividual())); 
//                Aplication.nodeJS.EmitStop(getAllUniqueIndividuals(getBestIndividual()),idClient,idProblem);
//                System.out.println("Async Stop By Force");           
//        }

    }
}
