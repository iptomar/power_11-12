/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module.WebSockets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class WebSocket {
     private boolean iniStatus;
     
     private webSocketServer webSocket;
     
     public static String ModuleName = "WebSocket Module";
     
     public WebSocket() throws IOException{
            webSocket = new webSocketServer(WebSocketConfig.LOCAL_PORT);
            iniStatus = true;
     }
     
    public boolean isIniStatus() {
        return iniStatus;
    }     
     
}
