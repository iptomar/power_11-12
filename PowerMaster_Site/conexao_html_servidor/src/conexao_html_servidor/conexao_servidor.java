/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao_html_servidor;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Cardoso
 */
public class conexao_servidor {
    public static void main(String[] args) throws Exception {
        //make a server socket to listen the clients
        ServerSocket server = new ServerSocket(10001,10);
        while (true) {
            System.out.println("Waiting for clients");
            //waiting for the contact
            Socket socket = server.accept();
            System.out.println("Conected to :" + socket.getInetAddress() + " Port: " + socket.getPort());
            //Make the service thread
            ThreadConexao service = new ThreadConexao(socket);
            //run the service
            service.start();
        }
    }
}
