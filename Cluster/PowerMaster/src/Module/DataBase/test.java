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
        Database db = new Database("power", "_p55!gv{7MJ]}dIpPk7n1*0-,hq(PD", "code.dei.estt.ipt.pt","powercomputing");
       
            Thread.sleep(10000);
            System.out.println(db.ExecuteCountQuery(122, 122, 122));

       
       // db.ExecuteNonQuery("INSERT INTO tblResults VALUES (1,1,1,0.1,0.1,0.1,10,0.1)");
       // db.ExecuteCountQuery(1, 1, 1);
//        db.ExecuteNonQuery("INSERT INTO tblIterations VALUES (1,1,1,1,NOW(),0.1,0.1,1,'das',0.1,1,0.1)");
//        System.out.println(db.ExecuteCountQuery(1, 1, 1));
        
        //db.ExecuteNonQuery("INSERT INTO tblIterations VALUES (" + 1 + "," + 1 + "," + 1 + "," + 1 + ",NOW()," + 1 + "," + 0.1 + "," + 0.1 + ",'" + "dsa" + "'," + 0.1 + "," + 1 + "," + 0.1 + ");");
          
       //db.ExecuteNonQuery("SELECT AVG(average) AS mediaAverage, MAX(best) AS best, AVG(deviation) AS deviation, AVG(numBest) AS numBest, AVG(variance) AS variance FROM tblIterations WHERE itera='"+ 1 +"' AND idClient='"+1 + "' AND idProblem='"+ 1 + "';");     
        
    }
}
