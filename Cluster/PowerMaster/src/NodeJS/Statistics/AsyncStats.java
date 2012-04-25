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

    public AsyncStats(AtomicInteger numThreads, int period, int idClient, int idProblem) {
        this.numThreads = numThreads;
        this.period = period;
        this.aux = period;
        this.idClient = idClient;
        this.idProblem = idProblem;
    }

    @Override
    public void run() {

        Database db = new Database("power", "_p55!gv{7MJ]}dIpPk7n1*0-,hq(PD", "127.0.0.1");
        //Database db = new Database("power", "_p55!gv{7MJ]}dIpPk7n1*0-,hq(PD", "code.dei.estt.ipt.pt");
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AsyncStats.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while (true) {
            try {
                int result_count = db.ExecuteCountQuery(period, idClient, idProblem);
                int numThread = numThreads.get();
               
                System.out.println("Async|  Period: "+period +"  Threads working: " + numThread + "  Result count: " + result_count + "  Cliente: "+idClient+ "  Problema: "+idProblem );
               
                if (result_count > numThread) {
                    boolean temp = db.ExecuteMedia(period, idClient, idProblem);
                    System.out.println("Async Insertion| Iteration:" + period);
                    Aplication.nodeJS.Emit("run",this.period ,this.idClient, this.idProblem);
                    period = period + aux;
                }
                else if (result_count == 0 && numThread == 0) {
                    Aplication.nodeJS.Emit("end",this.period ,this.idClient, this.idProblem);
                    System.out.println("Async Stop");
                    break;
                }

                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error - Sync Class " + e);
            }
        }

    }
}
