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
                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String data;
                while ((data = br.readLine()) != null) {
                    System.out.println(data);
                    try {
                        //JSONObject obj = new JSONObject(data);
                        Problem p;
                        p = Loader.Load(data);
//                        Solver s = p.getNewSolver();
//                        s.run();
                        
                        AtomicInteger numThreads = new AtomicInteger(5);
                        SolverThread[] arrayThread = new SolverThread[5];
                        
 
                        
                        for (int i = 0; i < arrayThread.length; i++) {
                            arrayThread[i] = new SolverThread(p.getNewSolver(), numThreads);
                            arrayThread[i].start();
                            arrayThread[i].setName("" + i);
                        }

                        System.out.println("Start Async");
                        System.out.println("Async:: Client:"+p.getClientID()+" id:"+p.getProblemID());
                        AsyncStats async = new AsyncStats(numThreads, 5, p.getClientID(), p.getProblemID());
                        async.start();  

                    } catch (Exception ex) {
                        Logger.getLogger(WorkSocket.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }


            } catch (IOException ex) {
                Logger.getLogger(WorkSocket.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
