/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package powermaster;

import Module.Loader.Loader;
import Module.Loader.Problem;
import genetics.Solver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class WorkSocket extends Thread {

    private int port;
    private ServerSocket ss;

    public WorkSocket(int port) {
        this.port = port;
        try {
            ss = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(WorkSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket client = ss.accept();
                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String data;
                while ((data = br.readLine()) != null) {
                    System.out.println(data);
                    try {
                        JSONObject obj = new JSONObject(data);
                        int iterations = obj.getInt("type");
                        String hello = obj.getString("teste");
                        JSONArray array = obj.getJSONArray("array");
                        
                        
                        System.out.println(hello+":"+iterations+":"+array.getString(0)+":"+array.getInt(1));
                    } catch (JSONException ex) {
                        Logger.getLogger(WorkSocket.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }


            } catch (IOException ex) {
                Logger.getLogger(WorkSocket.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
//        WorkSocket ws = new WorkSocket(1234);
//        ws.start();
//        ws.join();
        try {
            Problem p = Loader.Load("{type:\"OneMax\",id:1001,client:1000,selection:[\"roulette\",100],mutation:[\"flipbit\",0.01],recombination:[\"crossover\"],replacement:[\"tournament\",20,30],iterations:100,pop:1000,alello:100,best:80.0,lenght:1000,data:[[x,y],[y,z],[b,a]]}");
            Solver s = p.getNewSolver();
            s.run();
        } catch (Exception ex) {
            Logger.getLogger(WorkSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
