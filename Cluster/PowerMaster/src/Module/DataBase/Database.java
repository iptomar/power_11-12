package Module.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Rodrigo Marinheiro
 */
public class Database {

    private Connection Connection;
    private Statement Command;
    
    private boolean iniStatus;
    
    private String username;
    private String password;
    private String ipAddress;
    
    public static String ModuleName = "Database Module";
    
    public Database(String user, String password, String address){
        try {
            this.username = user;
            this.password = password;
            this.ipAddress = address;
            
            StartUp();
            iniStatus = true;
        } catch (Exception ex) {
            iniStatus = false;
            ex.printStackTrace();
        }
        
    }

    public boolean isIniStatus() {
        return iniStatus;
    }
    
    private void StartUp() throws SQLException, ClassNotFoundException {
            Class.forName("com.mysql.jdbc.Driver");
            Connection = DriverManager.getConnection("jdbc:mysql://"+this.ipAddress+":3306/powercomputing", this.username, this.password);
            Command = Connection.createStatement();
    }
    
    public boolean ExecuteNonQuery(String cmd) throws SQLException{
        if(iniStatus){
            return this.Command.execute(cmd);
        }
        return false;
    }

}
