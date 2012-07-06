/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module.Administration;

import Module.WebHTTP.WorkSocket;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author KopDicht
 */
public class GraphicThreadSocket extends Thread{
    
    private ServerSocket server2;
    private WorkSocket ws;
    
    public GraphicThreadSocket(WorkSocket ws) throws IOException{
        server2 = new ServerSocket(667, 10);
        this.ws = ws;
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
                AdminClientThread receber2 = new AdminClientThread(socket2,ws);
                
                //receber1.start();
                receber2.start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
