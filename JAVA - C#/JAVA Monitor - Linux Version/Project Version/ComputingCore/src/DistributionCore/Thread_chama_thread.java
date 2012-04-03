/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DistributionCore;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KopDicht
 */
public class Thread_chama_thread extends Thread {

    @Override
    public void run() {

        try {
            ServerSocket server1 = new ServerSocket(10001, 10);
            //ServerSocket server2 = new ServerSocket(667, 10);
            while (true) {

                Socket socket1 = server1.accept();
                //Socket socket2 = server2.accept();

                Thread_recebe receber1 = new Thread_recebe(socket1);                
                //Thread_recebe receber2 = new Thread_recebe(socket2);
                
                receber1.start();
                //receber2.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(recebe_cena.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
