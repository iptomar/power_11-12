/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poweremulator;





//import Modulos.Problem;
//import Modulos.SolverThread;
//import Modulos.Loader;
//import Modulos.WebFileDownloader;
import com.sun.xml.internal.ws.api.server.Module;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;



/**
 *
 * @author Miranda
 */
public class Emulador {
//    public static SolverThread[] arrayThread;
//    public static int NUM_THREADS = 2;
//    public static int INTERVAL_PART = 1;
    public Emulador()
    {

        
    }
    
   public static void IniciarPeloEmulador(String caminho){
        //Inicialização de todos os módulos do PowerMaster
        //Aplication app = new Aplication();
        
//        Problem p = null;
//        try {
//           String  resultado = WebFileDownloader.Download(new URL(caminho));
//           p = Loader.Load(resultado);
//           System.out.println("\n"+resultado);
//           
//            arrayThread = new SolverThread[NUM_THREADS];
//            AtomicInteger numThreads = new AtomicInteger(NUM_THREADS);
//
//            for (int i = 0; i < arrayThread.length; i++) {
//                arrayThread[i] = new SolverThread(p.getNewSolver(), numThreads);
//                arrayThread[i].start();
//                arrayThread[i].setName(""+i);
//            }
//
//            //AsyncStats async = new AsyncStats(numThreads,INTERVAL_PART,p.getClientID(),p.getProblemID());
//            //async.start();
//
//        } catch (Exception ex) {
//            System.out.println("Erro no IniciarPeloEmulador(): "+ex);
//            EmuladorEcran.Escrever("\n\nERRO: "+ex);
//        }
    }
}
