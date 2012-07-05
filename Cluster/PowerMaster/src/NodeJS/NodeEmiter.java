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
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import reflection.Base64Coder;

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
        if(socket.isConnected()){
            this.AplicationStatus = true;
        }else{
            AplicationStatus = false;
        }
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
    
    /**
     * Método para enviar a confirmação de paragem do solver
     * @param data Informação sobre a paragem do solver ()
     * @param clientID IdCliente
     * @param problemID IdProblema
     */
    public void EmitStop(String data,int clientID, int problemID){
        if(this.socket.isConnected()){
            try {
                JSONObject x = new JSONObject();
                x.put("idClient", "" + clientID);
                x.put("idProblem", "" + problemID);     
                x.put("data",data);
                socket.emit("stop",x);
            } catch (JSONException ex) {
                Logger.getLogger(NodeEmiter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            ReconectSingle();
            System.out.println("Server Node Fechado");
        }            
    }
    
    /**
     * Método para enviar a população de um conjunto de solver (idCliente + Problema) para optimum computing
     * @param pop String formatada por json com a popolação do solver com melhor media
     */
    public void EmitPop(String pop){
        //Criar o objecto JSON
        if(this.socket.isConnected()){
            socket.emit("pop", Base64Coder.encodeString(pop));
        }else{
            ReconectSingle();
            System.out.println("Server Node Fechado");
        }            
    }    

    public void EmitPopFunction(String pop){
        //Criar o objecto JSON
        if(this.socket.isConnected()){
            socket.emit("popf", Base64Coder.encodeString(pop));
        }else{
            ReconectSingle();
            System.out.println("Server Node Fechado");
        }            
    }    
    /**
     * Método para enviar para o servidor de Node.JS da Optimum Computing toda a informação lida por reflection.
     * A informação é enviada com codificação base64 devido aos caracteres especiadis que o JSON utiliza.
     * @param info Informação a ser enviada por reflection para o servidor Node. Esta String ja deve vir formatada em json.
     * @throws JSONException 
     */
    public void EmitInfo(String info) throws JSONException {
        //Criar o objecto JSON
        if(this.socket.isConnected()){
//            JSONObject x = new JSONObject();
//            //System.out.println("\n\n"+Base64Coder.encodeString(info));
//            x.put("client", client);
//            x.put("data", "" + Base64Coder.encodeString(info));
//            System.out.println(x.toString());
//            //Enviar o evento para o servidor Node.JS
            socket.emit("info", Base64Coder.encodeString(info));
        }else{
            ReconectSingle();
            System.out.println("Server Node Fechado");
        }
    }    
    
    /**
     * Método para enviar o estado do servidor PowerMaster
     * @throws JSONException
     * @throws UnknownHostException 
     */
    public void EmitStatus() throws JSONException, UnknownHostException {
        //Criar o objecto JSON
        if(this.socket.isConnected()){
            JSONObject x = new JSONObject();
            Map<String,String> Servers = new Hashtable();
            
            InetAddress thisIp =InetAddress.getLocalHost();
            Servers.put(thisIp.getHostName(), thisIp.getHostAddress());
            x.put("servers", Servers);
            
            socket.emit("codeStats",x );
        }else{
            ReconectSingle();
            System.out.println("Server Node Fechado");
        }
    }      
    
    /**
     * Método para tentar reconectar de modo unico ao servidor de Node.JS
     */
    private void ReconectSingle(){
               this.socket = new SocketIO();
                try {
                    System.out.println("A reconectar NODE.JS...");
                    this.socket.connect("http://130.185.82.35:90", this);
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
    
    /**
     * Método para reconectar de modo continuo ao servidor de Node.JS
     */
    private void Reconect(){
        while(this.socket.isConnected()){
                this.socket = new SocketIO();
                try {
                    System.out.println("A reconectar NODE.JS...");
                    this.socket.connect("http://130.185.82.35:90", this);
                    this.AplicationStatus = true;
                    break;
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
