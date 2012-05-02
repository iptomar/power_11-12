/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package powermaster;

import Emulator.EmuladorEcran;
import Module.Aplication;
import Module.Loader.Loader;
import Module.Loader.Problem;
import Module.WebHTTP.WebFileDownloader;
import NodeJS.Statistics.AsyncStats;
import genetics.Solver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class WorkSocket2 extends Thread {

    private int port;
    private ServerSocket ss;
 public static SolverThread[] arrayThread;
    public WorkSocket2(int port) {
        this.port = port;
        try {
            ss = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(WorkSocket2.class.getName()).log(Level.SEVERE, null, ex);
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
                        Solver s = p.getNewSolver();
                        s.run();
                    } catch (Exception ex) {
                        Logger.getLogger(WorkSocket2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }


            } catch (IOException ex) {
                Logger.getLogger(WorkSocket2.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
//public static void IniciarPeloEmulador(String caminho){
//        //Inicialização de todos os módulos do PowerMaster
//        //Aplication app = new Aplication();
//        
//        Problem p = null;
//        try {
//           String  resultado = WebFileDownloader.Download(new URL(caminho));
//           p = Loader.Load(resultado);
//           System.out.println("\n"+resultado);
//           
//            arrayThread = new SolverThread[PowerMaster.NUM_THREADS];
//            AtomicInteger numThreads = new AtomicInteger(PowerMaster.NUM_THREADS);
//
//            for (int i = 0; i < arrayThread.length; i++) {
//                arrayThread[i] = new SolverThread(p.getNewSolver(), numThreads);
//                arrayThread[i].start();
//                arrayThread[i].setName(""+i);
//            }
//
//        }
//}
    
    public static void main(String[] args) throws InterruptedException {
        //WorkSocket2 ws = new WorkSocket2(8080);
        //ws.start();
        //ws.join();
        
        //new Aplication();
        
        try {
            //Problem p = Loader.Load("{type:\"OneMax\",id:1001,client:1000,selection:[\"roulette\",100],mutation:[\"flipbit\",0.01],recombination:[\"crossover\"],replacement:[\"tournament\",20,30],iterations:100,pop:1000,alello:100,best:80.0,lenght:1000,data:[[x,y],[y,z],[b,a]]}");
            //Problem p = Loader.Load("{type:\"KnapSack\",id:1001,client:1000,selection:[\"roulette\",100],mutation:[\"flipbit\",0.01],recombination:[\"crossover\"],replacement:[\"tournament\",20,30],iterations:100,pop:1000,alello:100,best:80.0,penalty:10,mode:1,lenght:3,data:[[1,1],[2,2],[3,3]]}");
            //Problem p = Loader.Load("{type:KnapSack,id:1335305430171,client:1335305430172,selection:[roulette,100],mutation:[flipbit,0.01],recombination:[crossover],replacement:[tournament,70,2],iterations:200,pop:223,alello:3,best:100,penalty:1,mode:3,weight:150,lenght:3,data:[[94,3],[70,41],[90,22]]}");
            Problem p = Loader.Load("{type:OneMax,id:50000,client:50000,selection:[roulette,70],mutation:[flipbit,0.01],recombination:[crossover],replacement:[tournament,20,2],iterations:5000,pop:1000,alello:1000,best:1000,lenght:1000,data:[[x,y],[y,z],[b,a]]}");

            
            Solver s = p.getNewSolver();
            s.run();
        } catch (Exception ex) {
            Logger.getLogger(WorkSocket2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

