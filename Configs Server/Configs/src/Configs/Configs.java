/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Configs;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Pedro
 */
public class Configs implements Serializable {

    Map<String, Object> map = new HashMap<String, Object>();
    public static String key;
    static Configs conf = new Configs();

    public static void main(String[] args) throws Exception {
//        conf.Adiciona("ola", "mundo");
//        conf.Adiciona("ola2", "mundo2");
//        conf.Adiciona("ola3", "mundo3");
//        conf.Adiciona("ola4", "mundo4");
//        conf.Adiciona("ola5", "mundo5");
        conf.Load();
        conf.VerItem("ola");
     conf.GetObjecto("ola");
conf.VerMapa();
//conf.Save();
    }

    public static Configs getConf() {
        return conf;
    }

    public static void setConf(Configs conf) {
        Configs.conf = conf;
    }

    public void Save() throws Exception {
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream("teste.txt"));

        Set s = map.entrySet();
        Iterator it = s.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            out.writeObject(conf);
        }
        
        System.out.println("Configs Gravadas");

    }

    public void Load() throws Exception {

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("teste.txt"));
        Object obj = in.readObject();

        if (obj instanceof Configs) {
            conf = (Configs) obj;
        }
    }

    public void Adiciona(String Chave, Object Valor) {
        map.put(Chave, Valor);
    }

    public void VerMapa() {
        //Get Map in Set interface to get key and value
        Set s = map.entrySet();
        //Move next key and value of Map by iterator
        Iterator it = s.iterator();

        while (it.hasNext()) {
            // key=value separator this by Map.Entry to get key and value
            Map.Entry entry = (Map.Entry) it.next();

            // getKey is used to get key of Map
            Object key = entry.getKey();
            // Valor associado 
            Object value = entry.getValue();
            System.out.println("String: " + key + "\t  Associado: " + value);
        }
    }

    public void VerItem(String str) {

        //Get Map in Set interface to get key and value
        Set s = map.entrySet();
        //Move next key and value of Map by iterator
        Iterator it = s.iterator();

        while (it.hasNext()) {
            // key=value separator this by Map.Entry to get key and value
            Map.Entry entry = (Map.Entry) it.next();

            // getKey is used to get key of Map
            String key = (String) entry.getKey();

            if (key == str) {
                Object value = entry.getValue();
                System.out.println(key + " &#& " + value);
            }
        }
    }

    public Object GetObjecto(String str) {
        //Get Map in Set interface to get key and value
        Set s = map.entrySet();
        //Move next key and value of Map by iterator
        Iterator it = s.iterator();
        Object value = null;
        while (it.hasNext()) {
            // key=value separator this by Map.Entry to get key and value
            Map.Entry entry = (Map.Entry) it.next();

            // getKey is used to get key of Map
            String key = (String) entry.getKey();

            if (key == str) {
                value = entry.getValue();
                System.out.println(value);
            }
        }
        return value;
    }
}
