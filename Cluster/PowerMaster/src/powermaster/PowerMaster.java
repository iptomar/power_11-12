/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package powermaster;

import Module.Aplication;
import Module.Loader.Loader;
import io.socket.IOConnection;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class PowerMaster {

    public static SolverThread[] arrayThread;
    public static int NUM_THREADS = 1;
    public static int INTERVAL_PART = 25;

    /**
     * 
     * @param args 
     */
    public static void main(String[] args) throws Exception {
        //Inicialização de todos os módulos do PowerMaster
        System.out.println("PowerMaster Initializing...\n");
        Aplication app = new Aplication();
        if(app.getApplicationSatus()){
            System.out.println("\nPowerMaster Initialization - OK");
            System.out.println("\n\nAll systems good to go!!!\n\n");
        }
        
        IOConnection.loggerDebug = true;
////        
        INTERVAL_PART = Integer.parseInt(args[0]);
        NUM_THREADS = Integer.parseInt(args[1]);
//        
//    INTERVAL_PART = 25;
//    NUM_THREADS = 1;        
    }
    
}
