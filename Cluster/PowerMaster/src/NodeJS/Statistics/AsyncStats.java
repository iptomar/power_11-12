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
        //Database db = new Database("power", "_p55!gv{7MJ]}dIpPk7n1*0-,hq(PD", "code.dei.estt.ipt.pt");
        Database db = new Database("power", "_p55!gv{7MJ]}dIpPk7n1*0-,hq(PD", "127.0.0.1");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AsyncStats.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (true) {

            
            try {
                int result_count = db.ExecuteCountQuery(period, idClient, idProblem);
                int numThread = numThreads.get();
                System.out.println(period +"... Numero de Threads: " + numThread + "  Result count: " + result_count + "    Cliente:"+idClient+ "    Problema:"+idProblem );

                if (result_count > numThread) {
                    System.out.println("Periodo:" + period + "query_result: " + result_count);
                    boolean ze = db.ExecuteMedia(period, idClient, idProblem);
                    period = period + aux;
                    Aplication.nodeJS.Emit("run",this.period ,this.idClient, this.idProblem);
                }
                else if (result_count == 0 && numThread == 0) {
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
