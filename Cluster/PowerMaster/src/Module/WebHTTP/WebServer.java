/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module.WebHTTP;

import Module.AbstractAplication;
import java.io.IOException;


/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class WebServer extends AbstractAplication {
   
    public static boolean REMOTE_IP_SECURITY = false;
    public static String REDIRECT_IP = "130.185.82.35";
    public static int LOCAL_PORT = 8080;    
     
     private webServerSocket webSocket;
     
     public WebServer() throws IOException{
            super("WebSocket Module");
            webSocket = new webServerSocket(this.LOCAL_PORT);
            this.AplicationStatus = true;
     }
       
     
}
