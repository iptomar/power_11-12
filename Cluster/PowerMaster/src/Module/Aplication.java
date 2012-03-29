/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import Module.Administration.Administration;
import Module.DataBase.Database;
import Module.WebSockets.WebSocket;
import java.util.Hashtable;
import java.util.Map;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class Aplication {
    
    public static boolean STATUS;
    
    public static Database db;
    public static Administration admin;
    public static WebSocket webSocket;
    
    private Hashtable AplicationStatus;
    
    public Aplication(){
        AplicationStatus = new Hashtable();
        STATUS = iniModules();
    }
    
    private boolean iniModules(){
        db = new Database("optima", "optimapsi", "192.168.10.210");
        if(db.isIniStatus()){
            System.out.println("Databse Module - OK");
            AplicationStatus.put(Database.ModuleName, true);
        }else{
            db=null;
            System.out.println("Databse Module - Not OK!!!");
            return false;
        }
        
        admin = new Administration();
        if(admin.isIniStatus()){
            System.out.println("Administration Module - OK");
            AplicationStatus.put(Administration.ModuleName, true);
        }else{
            System.out.println("Administration Module - Not OK!!!");
            db = null;
            admin = null;
            return false;
        }
        
        webSocket = new WebSocket();
        if(webSocket.isIniStatus()){
            System.out.println("WebSocket Module - OK");
            AplicationStatus.put(WebSocket.ModuleName, true);
        }else{
            System.out.println("WebSocket Module - Not OK!!!");
            db = null;
            admin = null;
            webSocket = null;
            return false;
        }
        
        return true;
        
    }
}
