/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module.WebHTTP;

import Module.Aplication;
import java.io.FileNotFoundException;
import org.json.JSONException;
import powermaster.*;

import NodeJS.Statistics.AsyncStats;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import reflection.GeneticLoader;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class WorkSocket extends Thread {

    private int port;
    private ServerSocket ss;
    private Map<String, newClient> clients;

    public WorkSocket(int port) throws IOException {
        clients = new Hashtable();
        this.port = port;
        ss = new ServerSocket(port);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket client = ss.accept();
                client.getInetAddress().getHostAddress();

                newClient nc = new newClient(client);
                nc.start();

            } catch (IOException ex) {
                Logger.getLogger(WorkSocket.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    
    /**
     * Método para percorrer todas os clientes activos e retornar a informação em tempo real.
     * @return 
     */
    public String getClientsData(){
        Iterator it = clients.entrySet().iterator();
        StringBuilder sb = new  StringBuilder();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            sb.append(pairs.getKey()+":");
        }
        return sb.toString();
    }

    public class newClient extends Thread {

        private Socket client;
        //private GenericSolver solver;
        //private SolverThread[] arrayThread;
        //private AtomicInteger numThreads;
        private AsyncStats async;
        int idClient;
        int id;

        public newClient(Socket client) {
            this.client = client;
        }

        public void StopSolver() throws FileNotFoundException, IOException {
            this.async.Stop();
        }

        public void getPopulation() {
        }

        public void UpdateParms(JSONObject input) {
            async.UpdateParametrs(input);
        }
        /**
         * Método para retorna todos os individuos fitness
         */
        public void getBestPopulation() {
            String result = "{'idProblem':" + id;
            result += ",'idClient':" + idClient + ",";

            String data = async.getBestPopulation();
            result += data;
            result += "}";
            Aplication.nodeJS.EmitPop(result);
            System.out.println(result);
        }

        /**
         * Método para retorna todos os individuos (toString())
         * Martelada para as funções... Não sei se funciona nem quero saber. Pedido feito por a optimum
         */
        public void getBestPopulationFunction() {
            String result = "{'idProblem':" + id;
            result += ",'idClient':" + idClient + ",";
            String data = async.getBestPopulationFunction();
            result += data;
            result += "}";
            Aplication.nodeJS.EmitPopFunction(result);
            System.out.println(result);
        }        
        
        @Override
        public void run() {
            try {

                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String data;
                while ((data = br.readLine()) != null) {
                    System.out.println(data);
                    if (data.contains("info|")) {
                        System.out.println("New Info Request:" + data);
                        String[] params = data.split("\\|");
                        try {
                            GeneticLoader gl = new GeneticLoader();
                            String dataToSend = "{";
                            dataToSend += "'" + GeneticLoader.STRING_ALGOTITHMS + "':" + gl.getInfoJSON(GeneticLoader.STRING_ALGOTITHMS) + ",";

                            dataToSend += "'solver':'" + gl.getSolver().getInfo().replace(",", ";") + "',";

                            dataToSend += "'" + GeneticLoader.STRING_MUTATION + "':" + gl.getInfoJSON(GeneticLoader.STRING_MUTATION) + ",";
                            dataToSend += "'" + GeneticLoader.STRING_OPERATORS + "':" + gl.getInfoJSON(GeneticLoader.STRING_OPERATORS) + ",";
                            dataToSend += "'" + GeneticLoader.STRING_RECOMBINATIONS + "':" + gl.getInfoJSON(GeneticLoader.STRING_RECOMBINATIONS) + ",";
                            dataToSend += "'" + GeneticLoader.STRING_REPLACEMENTS + "':" + gl.getInfoJSON(GeneticLoader.STRING_REPLACEMENTS) + ",";
                            dataToSend += "'" + GeneticLoader.STRING_SELECTIONS + "':" + gl.getInfoJSON(GeneticLoader.STRING_SELECTIONS) + "";
                            dataToSend += ",'idClient':" + params[1];
                            dataToSend += "}";

                            //System.out.println(dataToSend);
                            
                            try {
                                Aplication.nodeJS.EmitInfo(dataToSend);
                            } catch (JSONException ex) {
                                Logger.getLogger(WorkSocket.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        //Novo parametro
                    } else if (data.contains("load|")) {
                        System.out.println("New Problem Request:" + data);
                        String[] params = data.split("\\|");
                        try {
                            JSONObject input = new JSONObject(params[1]);

                            idClient = input.getInt("client");
                            id = input.getInt("id");
//                            if ((new File("Pops/" + idClient + "_" + id).exists())) {
//                            } else {
//                            }
                            clients.put(new String(idClient + "_" + id), this);
                            try {
                                System.out.println("Start Async");
                                System.out.println("Async:: Client:" + idClient + " id:" + id);
                                async = new AsyncStats(PowerMaster.INTERVAL_PART, idClient, id, input);
                                async.setPriority(Thread.MAX_PRIORITY);
                                async.start();
                                //async.join();

                            } catch (Exception ex) {
                                Logger.getLogger(WorkSocket.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        } catch (JSONException ex) {
                            Logger.getLogger(WorkSocket.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        System.out.println(data);
                        //Novo Pedido do estado do servidor e slaves
                    } else if (data.contains("status|")) {
                        System.out.println("New Status request...");
                        Aplication.nodeJS.EmitStatus();
                        //Pedido de paragem do Solver
                    } else if (data.contains("stop|")) {
                        System.out.println("New Stop Request:" + data);
                        String[] params = data.split("\\|");
                        JSONObject input = new JSONObject(params[1]);
                        int idClient = input.getInt("client");
                        int id = input.getInt("id");
                        newClient client = clients.get(new String(idClient + "_" + id));
                        if (client != null) {
                            client.StopSolver();
                        } else {
                            System.out.println("ProblemID/ClientID - Error - NO STOP");
                        }
                    } else if (data.contains("pop|")) {
                        System.out.println("New Population Request:" + data);
                        String[] params = data.split("\\|");
                        JSONObject input = new JSONObject(params[1]);
                        int idClient = input.getInt("client");
                        int id = input.getInt("id");
                        newClient client = clients.get(new String(idClient + "_" + id));                        
                        if (client != null) {
                            client.getBestPopulation();
                        }else{//verificar localmente
                            try{                                
                                System.out.println("Pop não encontrada... Procurar no disco!");
                                FileInputStream fis = new FileInputStream(new File(new String("Pops/"+idClient + "_" + id)));
                                ObjectInputStream ois = new ObjectInputStream(fis);      
                                SaveStatus st = (SaveStatus) ois.readObject();
                                String result = "{'idProblem':" + id;
                                result += ",'idClient':" + idClient + ",";
                                result += st.getBestPopulation();
                                result += "}";
                                Aplication.nodeJS.EmitPop(result);
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }
                    } else if (data.contains("popf|")) {
                        System.out.println("New Population Request:" + data);
                        String[] params = data.split("\\|");
                        JSONObject input = new JSONObject(params[1]);
                        int idClient = input.getInt("client");
                        int id = input.getInt("id");
                        newClient client = clients.get(new String(idClient + "_" + id));
                        if (client != null) {
                            client.getBestPopulationFunction();
                        }else{//verificar localmente
                            try{                                
                                System.out.println("Pop não encontrada... Procurar no disco!");
                                FileInputStream fis = new FileInputStream(new File(new String("Pops/"+idClient + "_" + id)));
                                ObjectInputStream ois = new ObjectInputStream(fis);      
                                SaveStatus st = (SaveStatus) ois.readObject();
                                String result = "{'idProblem':" + id;
                                result += ",'idClient':" + idClient + ",";
                                result += st.getBestPopulationFunction();
                                result += "}";
                                Aplication.nodeJS.EmitPop(result);
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }                                            
                    } else if (data.contains("update|")) {
                        System.out.println("New Update Request:" + data);
                        String[] params = data.split("\\|");
                        JSONObject input = new JSONObject(params[1]);
                        int idClient = input.getInt("client");
                        int id = input.getInt("id");
                        newClient client = clients.get(new String(idClient + "_" + id));
                        if (client != null) {
                            client.UpdateParms(input);
                        }
                    }
                }


            } catch (Exception ex) {
                Logger.getLogger(WorkSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
