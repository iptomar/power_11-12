/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import Module.Administration.Administration;
import Module.DataBase.Database;
import Module.WebSockets.WebSocket;
import NodeJS.NodeEmiter;
import java.net.MalformedURLException;
import java.util.Hashtable;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class Aplication {

    public static boolean STATUS;
    public static Database db;
    public static Administration admin;
    public static WebSocket webSocket;
    public static NodeEmiter nodeJS;
    private Hashtable AplicationStatus;

    public Aplication() {
        AplicationStatus = new Hashtable();
        STATUS = iniModules();
    }

    private boolean iniModules() {
        System.out.println("Start Database Connection...");
        db = new Database("root", "iptpsi2012ipt", "127.0.0.1");
        if (db.isIniStatus()) {
            System.out.println("Databse Module - OK");
            AplicationStatus.put(Database.ModuleName, true);
        } else {
            db = null;
            System.out.println("Databse Module - Not OK!!!");
            return false;
        }

        /*System.out.println("Start Administration Connections...");
        admin = new Administration();
        if(admin.isIniStatus()){
        System.out.println("Administration Module - OK");
        AplicationStatus.put(Administration.ModuleName, true);
        }else{
        System.out.println("Administration Module - Not OK!!!");
        db = null;
        admin = null;
        return false;
        }*/
        try {
            webSocket = new WebSocket();
            if (webSocket.isIniStatus()) {
                System.out.println("WebSocket Module - OK");
                AplicationStatus.put(WebSocket.ModuleName, true);
            } else {
                System.out.println("WebSocket Module - Not OK!!!");
                db = null;
                admin = null;
                webSocket = null;
                return false;
            }
        } catch (Exception e) {
            System.out.println("WebSocket Module - Not OK!!!");
            db = null;
            admin = null;
            webSocket = null;
            return false;
        }
        try {
            nodeJS = new NodeEmiter();
            if (nodeJS.isIniStatus()) {
                System.out.println("NodeJS Module - OK");
            }
        } catch (MalformedURLException ex) {
            System.out.println("Node.JS Module - Not OK!!!");
            db = null;
            admin = null;
            webSocket = null;
            return false;
        }

        return true;

    }
}
