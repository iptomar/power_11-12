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
public class WorkSocket2 extends Thread {

    private int port;
    private ServerSocket ss;

    public WorkSocket2(int port) {
        this.port = port;
        try {
            ss = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(WorkSocket2.class.getName()).log(Level.SEVERE, null, ex);
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
                        //JSONObject obj = new JSONObject(data);
                        Problem p;
                        p = Loader.Load(data);
                        Solver s = p.getNewSolver();
                        s.run();
                    } catch (Exception ex) {
                        Logger.getLogger(WorkSocket2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }


            } catch (IOException ex) {
                Logger.getLogger(WorkSocket2.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    /*public static void main(String[] args) throws InterruptedException {
        //WorkSocket2 ws = new WorkSocket2(8080);
        //ws.start();
        //ws.join();
        try {
            //Problem p = Loader.Load("{type:\"OneMax\",id:1001,client:1000,selection:[\"roulette\",100],mutation:[\"flipbit\",0.01],recombination:[\"crossover\"],replacement:[\"tournament\",20,30],iterations:100,pop:1000,alello:100,best:80.0,lenght:1000,data:[[x,y],[y,z],[b,a]]}");
            //Problem p = Loader.Load("{type:\"KnapSack\",id:1001,client:1000,selection:[\"roulette\",100],mutation:[\"flipbit\",0.01],recombination:[\"crossover\"],replacement:[\"tournament\",20,30],iterations:100,pop:1000,alello:100,best:80.0,penalty:10,mode:1,lenght:3,data:[[1,1],[2,2],[3,3]]}");
            Problem p = Loader.Load("{type:KnapSack,id:1335305430171,client:1335305430172,selection:[roulette,100],mutation:[flipbit,0.01],recombination:[crossover],replacement:[tournament,70,2],iterations:200,pop:223,alello:3,best:100,penalty:1,mode:3,weight:150,lenght:3,data:[[94,3],[70,41],[90,22]]}");

            Solver s = p.getNewSolver();
            s.run();
        } catch (Exception ex) {
            Logger.getLogger(WorkSocket2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
}

