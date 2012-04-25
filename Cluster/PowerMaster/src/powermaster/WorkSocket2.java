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

    
    
    public static void main(String[] args) throws InterruptedException {
        //WorkSocket2 ws = new WorkSocket2(8080);
        //ws.start();
        //ws.join();
        try {
            //Problem p = Loader.Load("{type:\"OneMax\",id:1001,client:1000,selection:[\"roulette\",100],mutation:[\"flipbit\",0.01],recombination:[\"crossover\"],replacement:[\"tournament\",20,30],iterations:100,pop:1000,alello:100,best:80.0,lenght:1000,data:[[x,y],[y,z],[b,a]]}");
            //Problem p = Loader.Load("{type:\"KnapSack\",id:1001,client:1000,selection:[\"roulette\",100],mutation:[\"flipbit\",0.01],recombination:[\"crossover\"],replacement:[\"tournament\",20,30],iterations:100,pop:1000,alello:100,best:80.0,penalty:10,mode:1,lenght:3,data:[[1,1],[2,2],[3,3]]}");
            //Problem p = Loader.Load("{type:KnapSack,id:1335305430171,client:1335305430172,selection:[roulette,100],mutation:[flipbit,0.01],recombination:[crossover],replacement:[tournament,70,2],iterations:200,pop:223,alello:3,best:100,penalty:1,mode:3,weight:150,lenght:3,data:[[94,3],[70,41],[90,22]]}");
            Problem p = Loader.Load("{type:KnapSack,id:1335390108,client:1335390108,selection:[sus,70],mutation:[flipbit,0.01],recombination:[crossover],replacement:[tournament,100,2],iterations:10000,pop:100,alello:1000,best:1000,penalty:2,mode:2,lenght:97,data:[[24,26],[8,10],[8,13],[38,39],[33,34],[40,43],[50,52],[21,24],[24,27],[15,17],[33,35],[45,47],[16,18],[22,25],[7,8],[32,35],[33,36],[33,36],[16,21],[14,16],[23,27],[25,26],[33,34],[14,15],[7,12],[32,35],[36,40],[46,47],[11,14],[50,55],[26,28],[38,40],[46,51],[49,51],[7,8],[36,41],[26,28],[19,20],[6,11],[38,42],[10,11],[18,23],[45,49],[9,13],[50,54],[47,49],[38,40],[15,18],[42,45],[11,13],null,[49,51],[2,4],null,[31,33],[40,43],[28,32],[35,39],[32,36],[5,7],[39,44],[7,11],[43,45],[35,36],[23,37],[48,52],[28,33],[8,11],null,[21,22],[47,52],null,[2,7],[42,45],[11,13],[15,17],[49,52],[16,19],[44,37],[12,15],[33,38],[33,35],[22,23],[15,16],[14,15],[28,30],[39,42],[31,34],[30,34],[37,39],[19,21],[14,18],[33,37],[39,41],[10,13],[46,48],[32,36]]}");

            Solver s = p.getNewSolver();
            s.run();
        } catch (Exception ex) {
            Logger.getLogger(WorkSocket2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

