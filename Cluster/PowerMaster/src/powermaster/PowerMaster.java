/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package powermaster;

import Module.Aplication;
import Module.WebHTTP.WebFileDownloader;
import Module.WebHTTP.WebServer;
import genetics.OnesMax;
import genetics.Solver;
import io.socket.IOConnection;

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
                Aplication app = new Aplication();
                
                //String resultado = WebFileDownloader.Download(new URL("http://google.com"));
                //System.out.println(resultado);
                
                try{
                    if(args[0].equals("false")){
                        IOConnection.loggerDebug = false;
                    }else{
                        IOConnection.loggerDebug = true;
                    }
                    INTERVAL_PART = Integer.parseInt(args[1]);
                
                }catch(Exception e){
                    IOConnection.loggerDebug = false;
                    INTERVAL_PART = 50;
                }
                
                
                
                // Desligar o debug do sockets em IOConnection
                //IOConnection.loggerDebug = false;
                
                //Solver __newSolver = new Solver(100, 100, new OnesMax(), 1000, 80, new GeneticEvents());
                //__newSolver.run();               
                
                /*if(Aplication.STATUS){
                arrayThread = new SolverThread[4];
                
                for (int i = 0; i < arrayThread.length; i++) {
                    arrayThread[i] = new SolverThread();
                }
                
                for (int i = 0; i < arrayThread.length; i++) {
                    arrayThread[i].start();
                }
                
                }else{
                    System.out.println("Module Error!!!");
                    
                }*/
                //Solver teste = new Solver(1000, 10, 0.1);
                //teste.execute();
    }
}
