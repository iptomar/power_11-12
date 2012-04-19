/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package NodeJS.Statistics;

import Module.DataBase.Database;
import java.util.concurrent.atomic.AtomicInteger;

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
        while (numThreads.get() != 0) {
            
            try {
                Database db = new Database();
                int result_count = db.ExecuteCountQuery(period, idClient, idProblem);
                
                System.out.println("a espera. " +numThreads.get());
                
                if (result_count>=numThreads.get()) {
                    System.out.println("Periodo:" +period + "query_result: " +result_count);
                    
                    db.ExecuteMedia(period, idClient, idProblem);
                    period = period + aux;
                }
                
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println("Error - Sync Class");
            }
            
        }

    }
}
