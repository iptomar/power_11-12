/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package NodeJS;

import Module.AbstractAplication;
import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class NodeEmiter extends AbstractAplication implements IOCallback {

    /**
     * Objecto do tipo SocketIO
     * Para ser possivel ter este objecto é necessário as seguintes bibliotecas:
     *  - ioSocket.jar (Pré-Compilada)
     *  - webrkenecht-0.1.1.jar
     * 
     * Este objecto permite enviar informação para um servidor de Node.JS com eventos.
     */
    private SocketIO socket;

    /**
     * Construtor do objecto NodeEmiter
     * @throws MalformedURLException Caso existe algum erro será enviado por o contrutor
     */
    public NodeEmiter() throws MalformedURLException {
        super("Node.JS Module");
        socket = new SocketIO();
        socket.connect("http://130.185.82.35:90", this);
        this.AplicationStatus = true;
    }

    /**
     * Método que permite enviar um envento para o servidor Node.JS (Versão DEMO)
     * @param event Identificador do evento
     * @param iteration String valor 1 - neste caso é a iteração
     * @param bestValue String valor 2 - neste caso é o melhor valor encontrado
     * @throws JSONException 
     */
    public void Emit(String event, int iteration, int clientID, int problemID) throws JSONException {
        //Criar o objecto JSON
        if(this.socket.isConnected()){
            JSONObject x = new JSONObject();
            x.put("Itera", "" + iteration);
            x.put("idClient", "" + clientID);
            x.put("idProblem", "" + problemID);
            //Enviar o evento para o servidor Node.JS
            socket.emit(event, x);
            System.out.println(x.toString());
        }else{
            ReconectSingle();
            System.out.println("Server Node Fechado");
        }
    }

    private void ReconectSingle(){
               socket = new SocketIO();
                try {
                    System.out.println("A reconectar NODE.JS...");
                    socket.connect("http://130.185.82.35:90", this);
                    this.AplicationStatus = true;
                } catch (MalformedURLException ex) {
                    this.AplicationStatus = false;
                    Logger.getLogger(NodeEmiter.class.getName()).log(Level.SEVERE, null, ex);


                }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                System.out.println("Node.JS parece morto....");
                this.AplicationStatus = false;
            }        
    }
    
    private void Reconect(){
        while(socket.isConnected()){
                socket = new SocketIO();
                try {
                    System.out.println("A reconectar NODE.JS...");
                    socket.connect("http://130.185.82.35:90", this);
                    this.AplicationStatus = true;
                } catch (MalformedURLException ex) {
                    this.AplicationStatus = false;
                    Logger.getLogger(NodeEmiter.class.getName()).log(Level.SEVERE, null, ex);
                }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                System.out.println("Node.JS parece morto....");
                this.AplicationStatus = false;
                break;
            }
        }        
    }
    
    @Override
    public void onDisconnect() {
        Reconect();
    }

    @Override
    public void onConnect() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onMessage(String string, IOAcknowledge ioa) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onMessage(JSONObject jsono, IOAcknowledge ioa) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void on(String string, IOAcknowledge ioa, Object... os) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onError(SocketIOException sioe) {
        Reconect();
    }
}
