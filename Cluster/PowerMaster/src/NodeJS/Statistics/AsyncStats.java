/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package NodeJS.Statistics;

import Module.Aplication;
import Module.DataBase.Database;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joao
 */
public class AsyncStats extends Thread {

    AtomicInteger numThreads;
    int period;
    int aux;
    int idClient;
    int idProblem;

    volatile boolean Stop;
    
    public AsyncStats(AtomicInteger numThreads, int period, int idClient, int idProblem) {
        this.numThreads = numThreads;
        this.period = period-1;
        this.aux = period;
        this.idClient = idClient;
        this.idProblem = idProblem;
        this.setName("AsyncThread");
        this.Stop = false;
    }

    public synchronized void Stop(){
        Stop=true;
    }
    
    @Override
    public void run() {

        //Database db = new Database("power", "_p55!gv{7MJ]}dIpPk7n1*0-,hq(PD", "127.0.0.1");
        Database db = new Database("power", "_p55!gv{7MJ]}dIpPk7n1*0-,hq(PD", "code.dei.estt.ipt.pt","powercomputing");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AsyncStats.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (!Stop) {
            try {
                int result_count = db.ExecuteCountQuery(period, idClient, idProblem);
                int numThread = numThreads.get();

              // System.out.println("Async|  Period: " + period + "  Threads working: " + numThread + "  Result count: " + result_count + "  Cliente: " + idClient + "  Problema: " + idProblem);
                if (result_count == 0 && numThread == 0) {
                    Aplication.nodeJS.Emit("end", this.period, this.idClient, this.idProblem);
                    System.out.println("Async Stop");
                    break;
                }                
                
                if (result_count >= numThread) {
                    //System.out.println("Fechado"+Aplication.db.Connection.isClosed());
                    boolean temp = db.ExecuteMedia(period, idClient, idProblem);
                  //System.out.println("Async Insertion| Iteration:" + period);
                    Aplication.nodeJS.Emit("run", this.period, this.idClient, this.idProblem);
                    period = period + aux;
                }
                
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error - Sync Class " + e);
            }
        }

    }
}
