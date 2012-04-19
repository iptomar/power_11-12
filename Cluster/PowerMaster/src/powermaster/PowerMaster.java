/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package powermaster;

import Module.Aplication;
import Module.Loader.KnapSack;
import Module.Loader.Loader;
import Module.Loader.Problem;
import Module.WebHTTP.WebFileDownloader;
import genetics.Solver;
import genetics.SolverKnapSack;
import io.socket.IOConnection;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.exceptions.SolverException;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class PowerMaster {

    private static SolverThread[] arrayThread;
    private static int NUM_THREADS = 5;
    public static int INTERVAL_PART = 50;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Inicialização de todos os módulos do PowerMaster
        //Aplication app = new Aplication();

        try {
            if (args[0].equals("false")) {
                IOConnection.loggerDebug = false;
            } else {
                IOConnection.loggerDebug = true;
            }
            INTERVAL_PART = Integer.parseInt(args[1]);

        } catch (Exception e) {
            IOConnection.loggerDebug = false;
            INTERVAL_PART = 50;
        }

        //Verificação se está tudo Ok
        if (Aplication.STATUS) {
        }

        // Desligar o debug do sockets em IOConnection
        //IOConnection.loggerDebug = false;

        
        //Exemplo de um loader para OnesMax
        Problem p = null;
        try {
            String resultado = WebFileDownloader.Download(new URL("http://code.dei.estt.ipt.pt:81/loader/load4.txt"));
            p = Loader.Load(resultado);
        } catch (IOException ex) {
            Logger.getLogger(PowerMaster.class.getName()).log(Level.SEVERE, null, ex);
        }

        Solver s = p.getNewSolver();
        try {
            s.run();
        } catch (SolverException ex) {
            Logger.getLogger(PowerMaster.class.getName()).log(Level.SEVERE, null, ex);
        }            
            
            
            
            
    //        System.out.println(p.getParms(OnesMax.PARAM_ITERATIONS));
    //        
    //        //Solver s = p.getNewSolver();
    //        
    //        arrayThread = new SolverThread[NUM_THREADS];
    //        AtomicInteger numThreads = new AtomicInteger(NUM_THREADS);
    //        
    //        for (int i = 0; i < arrayThread.length; i++) {
    //            arrayThread[i] = new SolverThread(p.getNewSolver(), numThreads);
    //            arrayThread[i].start();
    //        }
    //        
    //        async.start();
    //        async.start();

    }
}
