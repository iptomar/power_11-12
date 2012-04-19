package Module.DataBase;

import Module.AbstractAplication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Rodrigo Marinheiro
 */
public class Database extends AbstractAplication {
    
    /**
     * Variável que contem a a ligação com o servido MySQL
     */
    private Connection Connection;
    /**
     * Variável que contem um comando a ser executado no servidor MySQL
     */
    private Statement Command;
    
    /**
     * Variável que contem o Username do servidor MySQL
     */
    private String username;
    /**
     * Variável que contem a Password do servidor de MySQL
     */
    private String password;
    /**
     * Variável o endereço de IP do servidor
     */
    private String ipAddress;
    
    //public static String ModuleName = "Database Module";
    
    /**
     * Construtor do objecto Database
     * Este objecto permite criar uma ligação com um servidor de MySQL
     * @param user Nome de utilizador do servidor de MySQL
     * @param password Password do utilizador do servidor de MySQL
     * @param address  Endereço publico do servidor de MySQL
     */
    
    public Database(){}
    
    public Database(String user, String password, String address){
        //Invocar o constutor da classe abstracta
        super("Database Module");
        try {
            //Atribuição de balores
            this.username = user;
            this.password = password;
            this.ipAddress = address;
            //Inicialização do Módulo
            StartUp();
            //Se chegar aqui o módulo foi inicializado correctamente
            this.AplicationStatus = true;
        } catch (Exception ex) {
            //Se chegar a interrupção a iniciaçização do módulo foi comprometida
            this.AplicationStatus = false;
            ex.printStackTrace();
        }
        
    }
    
    /**
     * Método interno ao objecto para a inicialização da conecção ao MySQL 
     * e inicialização de variáveis
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    private void StartUp() throws SQLException, ClassNotFoundException {
            Class.forName("com.mysql.jdbc.Driver");
            Connection = DriverManager.getConnection("jdbc:mysql://"+this.ipAddress+":3306/powercomputing", this.username, this.password);
            Command = Connection.createStatement();
    }
    
    
    public int ExecuteCountQuery(int period, int idClient, int idProblem) throws SQLException{
        int count = 0;
        if(this.AplicationStatus){
            ResultSet rs = this.Command.executeQuery("SELECT * FROM tblIterations WHERE itera='"+ period +"' AND idClient='"+idClient + "' AND idProblem='"+ idProblem + "';");
            rs.last();
            count = rs.getRow();
        }
        return count;
    }
    
    public boolean ExecuteMedia(int period, int idClient, int idProblem) throws SQLException{
        boolean erro = false;
        if(this.AplicationStatus){
            ResultSet rs = this.Command.executeQuery("SELECT AVG(average) AS mediaAverage, MAX(best) AS best, AVG(deviation) AS deviation, AVG(numBest) AS numBest FROM tblIterations WHERE itera='"+ period +"' AND idClient='"+idClient + "' AND idProblem='"+ idProblem + "';");
            rs.first();
            
            
            erro = this.ExecuteNonQuery("INSERT INTO tblResult VALUES "+period+","+idClient+","+idProblem+","+rs.getString("mediaAverage")+","+rs.getString("deviation")+","+rs.getString("best")+","+rs.getString("numBest")+"");
        }
        return erro;
    }
   
    /**
     * Método para executar uma query sem retorno no servidor MySQL
     * @param cmd Comando a ser executado no MySQL
     * @return True -> Comando executado com sucesso; False -> Ocorreu um erro na execução do comando
     * @throws SQLException 
     */
    public boolean ExecuteNonQuery(String cmd) throws SQLException{
        //Verificação se o estado do módulo é de activo e pronto a usar
        if(this.AplicationStatus){
            return this.Command.execute(cmd);
        }
        return false;
    }

}
