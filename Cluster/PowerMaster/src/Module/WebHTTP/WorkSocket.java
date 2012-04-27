/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module.WebHTTP;

import powermaster.*;

import Module.Loader.Loader;
import Module.Loader.Problem;
import NodeJS.Statistics.AsyncStats;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                        client.getInetAddress().getHostAddress();
                                client.getInetAddress().getHostAddress();
                //if(client.getInetAddress().getHostAddress().equals("")){
                    BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    String data;
                    while ((data = br.readLine()) != null) {
                        System.out.println(data);
                        new pedido(data).start();
                    }
                //}


            } catch (IOException ex) {
                Logger.getLogger(WorkSocket.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    
    public class pedido extends Thread{
    
        private String data;
        
        public pedido(String data){
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
                        System.out.println("Async:: Client:"+p.getClientID()+" id:"+p.getProblemID());
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
