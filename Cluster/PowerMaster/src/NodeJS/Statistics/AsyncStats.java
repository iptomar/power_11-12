/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package NodeJS.Statistics;

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

    public AsyncStats(AtomicInteger numThreads, int period) {
        this.numThreads = numThreads;
        this.period = period;
        this.aux = period;
    }

    @Override
    public void run() {
        while (numThreads.get() != 0) {
            
            try {
                Database db = new Database("root", "", "127.0.0.1");
                int result_count = db.ExecuteCountQuery("SELECT * FROM teste WHERE itera='"+ period +"';");
                
                System.out.println("a espera. " +numThreads.get());
                
                // deve ser apenas > .. está assim porque cada solver so esta a fazer uma inserção
                if (result_count>=numThreads.get()) {
                    System.out.println("Periodo:" +period + "query_result: " +result_count);
                    period = period + aux;
                }
                
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println("Error - Sync Class");
            }
            
        }

    }
}
