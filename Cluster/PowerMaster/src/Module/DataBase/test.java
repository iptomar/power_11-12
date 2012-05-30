/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module.DataBase;

import java.sql.SQLException;

/**
 *
 * @author Joao
 */
public class test {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        Database db = new Database("power", "_p55!gv{7MJ]}dIpPk7n1*0-,hq(PD", "code.dei.estt.ipt.pt", "powercomputing");
        //System.out.println(db.ExecuteCountQuery(122, 122, 122));
        //System.out.println(db.InserirIteracoes("122", 12,12 , 123, 123123, 312312, 123123, "dsads", 312312, 12, 12312));


        int interval = 0;
        int idClient = -2;
        int idProblem = -2;
        
       double ze = 0.70;
       String jose = "asdas";
       
       //db.InserirIteracoes(0, 1, idClient, idProblem, ze, ze, ze, jose, ze, 1, ze);
       //ze = 0.51;
       //db.InserirIteracoes(1, 1, idClient, idProblem, ze, ze, ze, jose, ze, 1, ze);
       
//       
//       
       //ze = 0.49;
       //db.InserirIteracoes(0, 1, idClient, idProblem, ze, ze, ze, jose, ze, 2, ze);
       //ze = 0.51;
       //db.InserirIteracoes(1, 1, idClient, idProblem, ze, ze, ze, jose, ze, 2, ze);
       
       //db.InserirResult(interval, idClient, idProblem, ze, ze, ze, idClient, ze);
      
       //db.ExecuteMedia(1, idClient, idProblem, "123");
       

        
        //db.ExecuteLastItera(idClient, idProblem, jose);
       //System.out.println(db.ExecuteCountQuery(interval, idClient, idProblem));
        
       
    }
}
