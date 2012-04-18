/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package powermaster;

import Module.Aplication;
import Module.Loader.Loader;
import Module.Loader.OnesMax;
import Module.Loader.Problem;
import Module.WebHTTP.WebFileDownloader;
import genetics.Solver;
import io.socket.IOConnection;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class PowerMaster {

    private static SolverThread[] arrayThread;
    public static int INTERVAL_PART = 50;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Inicialização de todos os módulos do PowerMaster
        /*Aplication app = new Aplication();

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
        }*/

        // Desligar o debug do sockets em IOConnection
        //IOConnection.loggerDebug = false;

        
        //Exemplo de um loader para OnesMax
        Problem p = null;
        try {


            //String resultado = WebFileDownloader.Download(new URL("File:C:\\Users\\Bruno\\Desktop\\load2.txt"));
                        String resultado = WebFileDownloader.Download(new URL("http://code.dei.estt.ipt.pt:81/loader/load1.txt"));

            p = Loader.Load(resultado);
        } catch (IOException ex) {
            Logger.getLogger(PowerMaster.class.getName()).log(Level.SEVERE, null, ex);
        }

//        System.out.println(p.getParms(OnesMax.PARAM_ITERATIONS));
//        Solver s = p.getNewSolver();
    }
}
