/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module.WebHTTP;

import Module.Aplication;
import org.json.JSONException;
import powermaster.*;

import NodeJS.Statistics.AsyncStats;
import genetics.GenericSolver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import reflection.GeneticLoader;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class WorkSocket extends Thread {

    private int port;
    private ServerSocket ss;
    private Map<String,newClient>clients;

    public WorkSocket(int port) {
        clients = new Hashtable();
        this.port = port;
        try {
            ss = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(WorkSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket client = ss.accept();
                client.getInetAddress().getHostAddress();
                
                newClient nc = new newClient(client);
                nc.start();
                //if(client.getInetAddress().getHostAddress().equals("")){

                //}


            } catch (IOException ex) {
                Logger.getLogger(WorkSocket.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public class newClient extends Thread {

        private Socket client;

        public newClient(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                
                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String data;
                while ((data = br.readLine()) != null) {

                    if (data.contains("info-")) {
                        System.out.println("New Info Request:" + data);
                        String[] params = data.split("-");
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

                            try {
                                Aplication.nodeJS.EmitInfo(dataToSend);
                            } catch (JSONException ex) {
                                Logger.getLogger(WorkSocket.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    //Novo parametro
                    } else if (data.contains("load-")) {
                        System.out.println("New Info Request:" + data);
                        String[] params = data.split("-");
                        try {
                            JSONObject input = new JSONObject(params[1]);

                            int idClient = input.getInt("client");
                            int id = input.getInt("id");

                            clients.put(new String(idClient+"_"+id), this);
                            
                            GeneticLoader gl = new GeneticLoader();
                            try {
                                GenericSolver solver = gl.getSolver();
                                JSONArray problem = input.getJSONArray("algorithm");
                                String problemName = problem.getString(0);
                                String problemParms = problem.getString(1);
                                String problemStop = problem.getString(2);

                                problem = input.getJSONArray("mutation");
                                String mutationName = problem.getString(0);
                                String mutationParms = problem.getString(1);

//                                problem = input.getJSONArray("operator");
//                                String operatorName = problem.getString(0);
//                                String operatorParms = problem.getString(1);     

                                problem = input.getJSONArray("recombination");
                                String recombinationName = problem.getString(0);
                                String recombinationParms = problem.getString(1);

                                problem = input.getJSONArray("replacement");
                                String replacementName = problem.getString(0);
                                String replacementParms = problem.getString(1);

                                problem = input.getJSONArray("selection");
                                String selectionName = problem.getString(0);
                                String selectionParms = problem.getString(1);

                                solver.setParameters(problemParms);
                                solver.SetSelection(selectionName + " " + selectionParms);
                                solver.SetMutation(mutationName + " " + mutationParms);
                                solver.SetReplacement(replacementName + " " + replacementParms);
                                solver.SetRecombination(recombinationName + " " + recombinationParms);
                                solver.SetStopCrit(problemStop);

                                solver.SetEvents(new GeneticEvents(PowerMaster.INTERVAL_PART, idClient, id));


                                AtomicInteger numThreads = new AtomicInteger(PowerMaster.NUM_THREADS);
                                SolverThread[] arrayThread = new SolverThread[PowerMaster.NUM_THREADS];



                                for (int i = 0; i < arrayThread.length; i++) {
                                    arrayThread[i] = new SolverThread(solver, numThreads);
                                    arrayThread[i].start();
                                    arrayThread[i].setName("" + i);
                                }

                                System.out.println("Start Async");
                                System.out.println("Async:: Client:" + idClient + " id:" + id);
                                AsyncStats async = new AsyncStats(numThreads, PowerMaster.INTERVAL_PART, idClient, id);
                                async.setPriority(Thread.MAX_PRIORITY);
                                async.start();
                                async.join();

                            } catch (Exception ex) {
                                Logger.getLogger(WorkSocket.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        } catch (JSONException ex) {
                            Logger.getLogger(WorkSocket.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        System.out.println(data);
                    }else if(data.contains("status-")){
                        System.out.println("New Status request...");
                        Aplication.nodeJS.EmitStatus();
                        
                    }
                }                
                
                
            } catch (Exception ex) {
                Logger.getLogger(WorkSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
}
