/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import Module.Administration.Administration;
import Module.DataBase.Database;
import Module.WebHTTP.WorkSocket;
import NodeJS.NodeEmiter;
import java.net.MalformedURLException;
import java.util.Hashtable;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class Aplication {

    /**
     * Variável que indica o estado global da aplicação (Todos os módulos)
     */
    public boolean STATUS;
    /**
     * Referencia estatica para o objecto Database
     */
    public static Database db;
    /**
     * Referencia estatica para o objecto Administration
     */
    public static Administration admin;
    /**
     * Referencia estatica para o objecto WorkSocket
     */
    public static WorkSocket workSocket;
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

        //System.out.println("Databse Module - Offline");
        System.out.println("Start Database Connection...");
        //db = new Database("power", "_p55!gv{7MJ]}dIpPk7n1*0-,hq(PD", "code.dei.estt.ipt.pt","powercomputing");
        db = new Database("root", "testestestes", "130.185.82.39","powercomputing");
        //db = new Database("power", "_p55!gv{7MJ]}dIpPk7n1*0-,hq(PD", "127.0.0.1");

        if (db.getAplicationStatus()) {
            System.out.println(db.AplicationName + " - OK");
            AplicationStatus.put(db.AplicationName, true);
        } else {
            db = null;
            System.out.println(db.AplicationName + " - Not OK!!!");
            return false;
        }
        // --------------------------------------------------

        // --------------------------------------------------
        // ---- Módulo Administração
        // --------------------------------------------------

        System.out.println("Start Administration Connections...");
        admin = new Administration();
        if (admin.AplicationStatus) {
            System.out.println(admin.AplicationName + " - OK");
            AplicationStatus.put(admin.AplicationName, true);
        } else {
            System.out.println(admin.AplicationName + " - Not OK!!!");
            db = null;
            admin = null;
            return false;
        }
        // --------------------------------------------------

        // --------------------------------------------------
        // ---- Módulo WWorkSocket (8080)
        // --------------------------------------------------
        try {
            System.out.println("Start WorkSocket on port 8080...");
            workSocket = new WorkSocket(8080);
            workSocket.start();
            System.out.println("WorkSocket (8080) - OK");
            admin.SetWorkSocketReference(workSocket);
        } catch (Exception e) {
            System.out.println("WorkSocket (8080) - Not OK!!!");
            db = null;
            admin = null;
            workSocket = null;
            return false;
        }
        // --------------------------------------------------

        // --------------------------------------------------
        // ---- Módulo Node.Js
        // --------------------------------------------------        
        try {
            System.out.println("Start Node.JS connection...");
            nodeJS = new NodeEmiter();
            if (nodeJS.getAplicationStatus()) {
                System.out.println(nodeJS.AplicationName + " - OK");
            }else{
                System.out.println(nodeJS.AplicationName + " - ?? OK!!!");
                db = null;
                admin = null;
                workSocket = null;
                return false;                
            }
        } catch (MalformedURLException ex) {
            System.out.println(nodeJS.AplicationName + " - Not OK!!!");
            db = null;
            admin = null;
            workSocket = null;
            return false;
        }
        // --------------------------------------------------

        //Se chegar aqui é porque todos os módulos foram carregados e inicializados
        return true;

    }
    
    public boolean getApplicationSatus(){
        return this.STATUS;
    }
    
}
