/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package powermaster;

import Module.Aplication;
import Module.Loader.Loader;
import Module.Loader.Problem;
import NodeJS.Statistics.AsyncStats;
import genetics.Solver;
import io.socket.IOConnection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class PowerMaster {

    private static SolverThread[] arrayThread;
    private static int NUM_THREADS = 5;
    public static int INTERVAL_PART = 1;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Inicialização de todos os módulos do PowerMaster
        //Aplication app = new Aplication();

       

        
         Aplication ap = new Aplication();
        
        //Verificação se está tudo Ok
        if (ap.STATUS) {
        }

        // Desligar o debug do sockets em IOConnection
        //IOConnection.loggerDebug = false;


        //Exemplo de um loader para OnesMax
//        Problem p = null;
//        try {
//            String resultado = WebFileDownloader.Download(new URL("http://code.dei.estt.ipt.pt:81/loader/load1.txt"));
//            p = Loader.Load(resultado);
//            Solver s = p.getNewSolver();
//            p.LoadOperators("selector=Tournament");
//        } catch (Exception ex) {
//            Logger.getLogger(PowerMaster.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        try {
//            Solver exe = p.getNewSolver();
//            exe.run();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        
        
        Problem p = null;
        try {
            p = Loader.Load("{type:\"OneMax\",id:1,client:1,selection:[\"tournament\",20,30],mutation:[\"flipbit\",0.01],recombination:[\"crossover\"],replacement:[\"tournament\",20,30],iterations:1000,pop:10000,alello:100,best:80.0,lenght:1000,data:[[x,y],[y,z],[b,a]]}");
        } catch (Exception ex) {
            Logger.getLogger(PowerMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        arrayThread = new SolverThread[NUM_THREADS];
        AtomicInteger numThreads = new AtomicInteger(NUM_THREADS);

        for (int i = 0; i < arrayThread.length; i++) {
            arrayThread[i] = new SolverThread(p.getNewSolver(), numThreads);
            arrayThread[i].start();
            arrayThread[i].setName(""+i);
        }

        AsyncStats async = new AsyncStats(numThreads, INTERVAL_PART, 1, 1);
        async.start();

    }
}
