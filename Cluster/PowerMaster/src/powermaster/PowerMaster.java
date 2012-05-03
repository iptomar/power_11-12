/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package powermaster;

import Module.Aplication;
import Module.Loader.Loader;
import Module.Loader.Problem;
import Module.WebHTTP.WorkSocket;
import NodeJS.Statistics.AsyncStats;
import io.socket.IOConnection;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class PowerMaster {

    public static SolverThread[] arrayThread;
    public static int NUM_THREADS = 1;
    public static int INTERVAL_PART = 1;

    /**
     * 
     * @param args 
     */
    public static void main(String[] args) throws Exception {
        //Inicialização de todos os módulos do PowerMaster
        Aplication app = new Aplication();

        System.out.println("PowerMaster Initializing..");
        
        IOConnection.loggerDebug = false;
        
//        INTERVAL_PART = Integer.parseInt(args[0]);
//        NUM_THREADS = Integer.parseInt(args[1]);
        
        INTERVAL_PART = 10;
        NUM_THREADS = 1;        
        
        WorkSocket ws = new WorkSocket(8080);
        ws.start();
        

//        Problem p = null;
//        p = Loader.Load("{type:'OneMax',id:1,client:1,selection:['roulette',100],mutation:['flipbit',0.01],recombination:['crossover'],replacement:['tournament',20,30],iterations:1000,pop:1000,alello:100,best:99,lenght:1000,data:[[x,y],[y,z],[b,a]]}");
//        arrayThread = new SolverThread[NUM_THREADS];
//        AtomicInteger numThreads = new AtomicInteger(NUM_THREADS);
//        
//        for (int i = 0; i < arrayThread.length; i++) {
//            arrayThread[i] = new SolverThread(p.getNewSolver(), numThreads);
//            arrayThread[i].start();
//            arrayThread[i].setName(""+i);
//        }
//        
//        AsyncStats async = new AsyncStats(numThreads,INTERVAL_PART,p.getClientID(),p.getProblemID());
//        async.start();
    }
}


//        Exemplo de um loader para OnesMax
//        Problem p = null;
//        try {
//            String resultado = WebFileDownloader.Download(new URL("http://code.dei.estt.ipt.pt:81/loader/load1.txt"));
//            p = Loader.Load(resultado);
//            Solver s = p.getNewSolver();
//            //p.LoadOperators("selector=Tournament");
//        } catch (Exception ex) {
//            Logger.getLogger(PowerMaster.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//
//        try {
//            Solver exe = p.getNewSolver();
//            exe.run();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
