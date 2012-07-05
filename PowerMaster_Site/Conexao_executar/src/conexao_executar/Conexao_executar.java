/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao_executar;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.applet.*;
import java.awt.Graphics;
/**
 *
 * @author filipe
 */
public class Conexao_executar extends Applet {
     public void paint(Graphics g){
        try {
            Socket socket = new Socket("code.dei.estt.ipt.pt", 10001);
            Scanner in = new Scanner(socket.getInputStream());
            PrintStream out = new PrintStream(socket.getOutputStream());
            out.println("executar");
            g.drawString("Pedido enviado! Por favor aguarde!", 100, 20);
            String msg = in.nextLine();


            g.drawString("Received: " + msg, 100, 40);
            socket.close();
            in.close();
            out.close();
        } catch (Exception e) {
            g.drawString("Impossivel estabelecer ligação com o servidor!" + e, 0, 20);
        }
    }
}
