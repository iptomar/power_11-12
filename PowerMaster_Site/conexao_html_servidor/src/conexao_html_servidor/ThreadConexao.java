/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao_html_servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cardoso
 */
public class ThreadConexao extends Thread {

    BufferedReader in;
    PrintStream out;
    Socket socket;

    public ThreadConexao(Socket sock) {
        socket = sock;
        try {
            //open the streams
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ThreadConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        String client = socket.getInetAddress().getHostName() + ":" + socket.getPort();
        while (true) {
            try {
                //read the message
                String message = in.readLine();

                if (message.equalsIgnoreCase("processos")) {
                    Process teste = Runtime.getRuntime().exec("ps -A");
                    BufferedReader in2 = new BufferedReader(
                            new InputStreamReader(teste.getInputStream()));
                    String line = null;
                    while ((line = in2.readLine()) != null) {
                        out.println(line);
                    }
                    out.flush();
                    socket.close();
                    in.close();
                    out.close();


                } else {
                    if (message.equalsIgnoreCase("executar")) {
                        Process teste2 = Runtime.getRuntime().exec("top | grep PowerMaster");
                        BufferedReader in3 = new BufferedReader(new InputStreamReader(teste2.getInputStream()));
                        String line = null;
                        if ((line = in3.readLine()) != null){
                            out.println("O servidor já se encontra em execução!");
                        }else{
                        Process teste = Runtime.getRuntime().exec("java -jar -server PowerMaster.jar 25 1 &");
                        BufferedReader in2 = new BufferedReader(
                                new InputStreamReader(teste.getInputStream()));
                        line = null;
                        while ((line = in2.readLine()) != null) {
                            out.println(line);
                        }
                        out.flush();
                        socket.close();
                        in.close();
                        out.close();
                        }
                    } else {
                        out.println("Comando errado ou servidor inacessivel!");
                        out.flush();
                        socket.close();
                        in.close();
                        out.close();
                    }



                }
            } catch (Exception ex) {
                System.out.println(" finish " + client + " job.");
                return;
            }
        }
    }
}
