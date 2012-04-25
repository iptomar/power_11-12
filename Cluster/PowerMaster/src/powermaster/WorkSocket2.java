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
            Problem p = Loader.Load("{type:KnapSack,id:1335305430171,client:1335305430172,selection:[roulette,199],mutation:[flipbit,22],recombination:[crossover],replacement:[tournament,21,222],iterations:200,pop:223,alello:33,best:100,penalty:1,mode:3,weight:150,lenght:50,data:[[94,3],[70,41],[90,22],[97,30],[54,45],[31,99],[82,75],[97,76],[1,79],[58,77],[96,41],[96,98],[87,31],[53,28],[62,58],[89,32],[68,99],[58,48],[81,20],[83,3],[67,81],[41,17],[50,3],[58,62],[61,39],[45,76],[64,94],[55,75],[12,44],[87,63],[32,35],[53,11],[25,21],[59,45],[23,43],[77,46],[22,26],[18,2],[64,53],[85,37],[14,32],[23,78],[76,74],[81,66],[49,61],[47,51],[88,11],[19,85],[74,90],[31,40]]}");

            Solver s = p.getNewSolver();
            s.run();
        } catch (Exception ex) {
            Logger.getLogger(WorkSocket2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

