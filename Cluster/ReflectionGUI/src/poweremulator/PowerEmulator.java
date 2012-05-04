/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poweremulator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Miranda
 */
public class PowerEmulator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, IOException {
        EmuladorEcran emu = new EmuladorEcran();
        emu.setVisible(true);
        
//       try{
//       //EmuladorEcran emu = new EmuladorEcran();
//       //emu.setVisible(true);
//       Socket socket = new Socket("192.168.10.247", 8080);
//       //Socket socket = new Socket("code.dei.estt.ipt.pt", 8080);
//       System.out.println("so... "+socket.isConnected());
//       BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//       wr.write("{type:KnapSack,id:30,client:30,selection:[sus,700],mutation:[flipbit,0.01],recombination:[crossover],replacement:[tournament,500,2],iterations:10000,pop:1000,alello:50,best:1921,penalty:2,mode:1,weight:1473,lenght:50,data:[[94,3],[70,41],[90,22],[97,30],[54,45],[31,99],[82,75],[97,76],[1,79],[58,77],[96,41],[96,98],[87,31],[53,28],[62,58],[89,32],[68,99],[58,48],[81,20],[83,3],[67,81],[41,17],[50,3],[58,62],[61,39],[45,76],[64,94],[55,75],[12,44],[87,63],[32,35],[53,11],[25,21],[59,45],[23,43],[77,46],[22,26],[18,2],[64,53],[85,37],[14,32],[23,78],[76,74],[81,66],[49,61],[47,51],[88,11],[19,85],[74,90],[31,40]]}");
//       //wr.write("{type:KnapSack,id:2,client:2,selection:[roulette,100],mutation:[flipbit,0.01],recombination:[crossover],replacement:[tournament,500,2],iterations:3000,pop:700,alello:50,best:1921,penalty:2,mode:1,weight:1473,lenght:50,data:[[10,5],[10,5],[10,5],[10,5],[10,5],[10,5],[10,5],[10,5],[10,5],[10,5],[10,5],[10,5],[10,3],[10,3],[10,3],[10,3],[10,3],[10,3],[10,3],[10,3],[10,3],[10,3],[10,3],[10,3],[10,3],[10,3],[10,3],[10,3],[10,3],[10,3],[10,3],[10,3],[10,3],[10,3],[10,3],[10,3],[10,3],[10,3],[10,3],[10,3],[10,3],[10,3],[10,3],[10,3],[10,3],[10,3],[10,3],[10,3],[10,3],[10,3]]}");
//       wr.close();
//       }catch(Exception e){
//           System.out.println("Erro: "+e);
//       }
   }
}
