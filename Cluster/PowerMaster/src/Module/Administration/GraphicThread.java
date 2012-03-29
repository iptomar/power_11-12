/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module.Administration;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KopDicht
 */
public class GraphicThread extends Thread{
    
    private ServerSocket server2;
    
    public GraphicThread() throws IOException{
        server2 = new ServerSocket(667, 10);
    }
    
    @Override
    public void run() {

        try {
            //ServerSocket server1 = new ServerSocket(666, 10);
            //server2 = new ServerSocket(667, 10);
            while (true) {

                //Socket socket1 = server1.accept();
                Socket socket2 = server2.accept();

                //Thread_recebe receber1 = new Thread_recebe(socket1);                
                AdministrationClient receber2 = new AdministrationClient(socket2);
                
                //receber1.start();
                receber2.start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
