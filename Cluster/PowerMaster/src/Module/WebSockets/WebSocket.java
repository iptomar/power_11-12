/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module.WebSockets;

import Module.AbstractAplication;
import java.io.IOException;


/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class WebSocket extends AbstractAplication {
   
     
     private webSocketServer webSocket;
     
     public WebSocket() throws IOException{
            super("WebSocket Module");
            webSocket = new webSocketServer(WebSocketConfig.LOCAL_PORT);
            this.AplicationStatus = true;
     }
       
     
}
