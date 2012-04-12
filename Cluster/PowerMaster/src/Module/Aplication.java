/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import Module.Administration.Administration;
import Module.DataBase.Database;
import Module.WebHTTP.WebServer;
import NodeJS.NodeEmiter;
import java.net.MalformedURLException;
import java.util.Hashtable;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class Aplication {

    /**
     * Variável que inidica o estado global da aplicação (Todos os módulos)
     */
    public static boolean STATUS;
    /**
     * Referencia estatica para o objecto Database
     */
    public static Database db;
    /**
     * Referencia estatica para o objecto Administration
     */
    public static Administration admin;
    /**
     * Referencia estatica para o objecto WebSocket
     */
    public static WebServer webSocket;
    /**
     * Referencia estatica para o objecto NodeEmiter
     */    
    public static NodeEmiter nodeJS;
    /**
     * NOT IN WORK YET
     */
    private Hashtable AplicationStatus;

    public Aplication() {
        AplicationStatus = new Hashtable();
        STATUS = iniModules();
    }

    /**
     * Método que inicializa todos os módulos existentes no PowerMaster
     * @return O retorno deste método é um boolean  (True/False) | True -> Inicialização Ok | False -> Falha na inicialização
     * 
     */
    private boolean iniModules() {
        
         // --------------------------------------------------
         // ---- Módulo base de dados
         // --------------------------------------------------        
        
        System.out.println("Databse Module - Offline");
        /*System.out.println("Start Database Connection...");
        db = new Database("root", "iptpsi2012ipt", "127.0.0.1");
        //db = new Database("optima", "optimapsi", "192.168.10.251");
        if (db.getAplicationStatus()) {
            System.out.println(db.AplicationName+" - OK");
            AplicationStatus.put(db.AplicationName, true);
        } else {
            db = null;
            System.out.println(db.AplicationName+" - Not OK!!!");
            return false;
        }*/
         // --------------------------------------------------

         // --------------------------------------------------
         // ---- Módulo Administração
         // --------------------------------------------------
        
        System.out.println("Start Administration Connections...");
        admin = new Administration();
        if(admin.AplicationStatus){
        System.out.println(admin.AplicationName+" - OK");
        AplicationStatus.put(admin.AplicationName, true);
        }else{
        System.out.println(admin.AplicationName+" - Not OK!!!");
        db = null;
        admin = null;
        return false;
        }
         // --------------------------------------------------
        
         // --------------------------------------------------
         // ---- Módulo WebSocket (8080)
         // --------------------------------------------------
        try {
            webSocket = new WebServer();
            if (webSocket.getAplicationStatus()) {
                System.out.println("WebSocket Module - OK");
                AplicationStatus.put(webSocket.AplicationName, true);
            } else {
                System.out.println(webSocket.AplicationName+" - Not OK!!!");
                db = null;
                admin = null;
                webSocket = null;
                return false;
            }
        } catch (Exception e) {
            System.out.println(webSocket.AplicationName+" - Not OK!!!");
            db = null;
            admin = null;
            webSocket = null;
            return false;
        }
        // --------------------------------------------------

         // --------------------------------------------------
         // ---- Módulo Node.Js
         // --------------------------------------------------        
        try {
            nodeJS = new NodeEmiter();
            if (nodeJS.getAplicationStatus()) {
                System.out.println(admin.AplicationName+" - OK");
            }
        } catch (MalformedURLException ex) {
            System.out.println(admin.AplicationName+" - Not OK!!!");
            db = null;
            admin = null;
            webSocket = null;
            return false;
        }
        // --------------------------------------------------
        
        //Se chegar aqui é porque todos os módulos foram carregados e inicializados
        return true;

    }
}
