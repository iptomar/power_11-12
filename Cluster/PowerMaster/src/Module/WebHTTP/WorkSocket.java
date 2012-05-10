/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module.WebHTTP;

import Module.Aplication;
import java.lang.reflect.InvocationTargetException;
import org.json.JSONException;
import powermaster.*;

import Module.Loader.Loader;
import Module.Loader.Problem;
import NodeJS.Statistics.AsyncStats;
import genetics.GenericSolver;
import genetics.Solver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
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

    public WorkSocket(int port) {
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
                //if(client.getInetAddress().getHostAddress().equals("")){
                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String data;
                while ((data = br.readLine()) != null) {
                    //System.out.println("New Info Request:"+data);
                    if(data.contains("info-")){
                        String[] params = data.split("-");
                        try{
                            GeneticLoader gl = new GeneticLoader();
                            String dataToSend = "{"; 
                                dataToSend += "'"+GeneticLoader.STRING_ALGOTITHMS+"':"+gl.getInfoJSON(GeneticLoader.STRING_ALGOTITHMS)+","; 

                                dataToSend += "'solver':'"+gl.getSolver().getInfo().replace(",", ";") +"',"; 

                                dataToSend += "'"+GeneticLoader.STRING_MUTATION+"':"+gl.getInfoJSON(GeneticLoader.STRING_MUTATION)+","; 
                                dataToSend += "'"+GeneticLoader.STRING_OPERATORS+"':"+gl.getInfoJSON(GeneticLoader.STRING_OPERATORS)+","; 
                                dataToSend += "'"+GeneticLoader.STRING_RECOMBINATIONS+"':"+gl.getInfoJSON(GeneticLoader.STRING_RECOMBINATIONS)+","; 
                                dataToSend += "'"+GeneticLoader.STRING_REPLACEMENTS+"':"+gl.getInfoJSON(GeneticLoader.STRING_REPLACEMENTS)+","; 
                                dataToSend += "'"+GeneticLoader.STRING_SELECTIONS+"':"+gl.getInfoJSON(GeneticLoader.STRING_SELECTIONS)+""; 
                                dataToSend += ",'idClient':"+params[1];
                            dataToSend += "}";

                            try {
                                Aplication.nodeJS.EmitInfo(dataToSend);
                            } catch (JSONException ex) {
                                Logger.getLogger(WorkSocket.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                        
                    }else if(data.contains("load-")) {
                        String[] params = data.split("-");
                        try {
                            JSONObject input = new  JSONObject(params[1]);
                            
                            int idClient = input.getInt("client");
                            int id = input.getInt("id");
                            
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
                                solver.SetSelection(selectionName+" "+selectionParms);
                                solver.SetMutation(mutationName+" "+mutationParms);
                                solver.SetReplacement(replacementName+" "+replacementParms);
                                solver.SetRecombination(recombinationName+" "+recombinationParms);
                                solver.SetStopCrit(problemStop);

                                solver.SetEvents(new GeneticEvents(PowerMaster.INTERVAL_PART,idClient,id));

                                solver.run(); 
                                        
                            } catch (Exception ex) {
                                Logger.getLogger(WorkSocket.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            
 

                            
                            
                        } catch (JSONException ex) {
                            Logger.getLogger(WorkSocket.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        System.out.println(data);
                    }
                }
                //}


            } catch (IOException ex) {
                Logger.getLogger(WorkSocket.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public class pedido extends Thread {

        private String data;

        public pedido(String data) {
            this.data = data;
        }

        @Override
        public void run() {
            try {
                //JSONObject obj = new JSONObject(data);
                Problem p;
                p = Loader.Load(data);
//                        Solver s = p.getNewSolver();
//                        s.run();

                AtomicInteger numThreads = new AtomicInteger(PowerMaster.NUM_THREADS);
                SolverThread[] arrayThread = new SolverThread[PowerMaster.NUM_THREADS];



                for (int i = 0; i < arrayThread.length; i++) {
                    arrayThread[i] = new SolverThread(p.getNewSolver(), numThreads);
                    arrayThread[i].start();
                    arrayThread[i].setName("" + i);
                }

                System.out.println("Start Async");
                System.out.println("Async:: Client:" + p.getClientID() + " id:" + p.getProblemID());
                AsyncStats async = new AsyncStats(numThreads, PowerMaster.INTERVAL_PART, p.getClientID(), p.getProblemID());
                async.setPriority(Thread.MAX_PRIORITY);
                async.start();

                async.join();

            } catch (Exception ex) {
                Logger.getLogger(WorkSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
