/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reflection;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class TaskLoader extends ClassLoader{

    /**
     * Objecto que permite carregar um ficheiro .class do java e criar um objecto atraves desse ficheiro.
     * Este ficheiro pode estar localizado num servidor Web, ou no disco. Neste exemplo é utilizado o disco.
     */
    
    /**
     * Nome do ficheiro (class/objecto)
     */
    private String ClassName;
    /**
     * Objecto do tipo Class que contem o objecto que é carregado atraves do ficheiro
     */
    private Class classObject;
    /**
     * Contrutor do objecto TaskLoader
     * Este contrutor tem dois parametros
     * @param instance Instancia do class loader. Ver arvore de ClassLoader do java
     * @param localClass String que define o nome do objecto juntamente com a sua localização
     */
    public TaskLoader(ClassLoader instance,String localClass){
        super(instance);
        this.ClassName = localClass;
        
        this.classObject = createClass(FileToByteArray(new File(localClass)));
    }
    
    /**
     * Método que retorna o objecto da class criada atraves do ficheiro .class
     * @return 
     */
    public Class getClassObject(){
        return this.classObject;
    }
    
    /**
     * Método para carregar o ficheiro num byteArray
     * @param f Ficheiro que vai ser carregado para a estrutura
     * @return retorna um array de Bytes com a informação do ficheiro
     */
    private byte[] FileToByteArray(File f) {
        DataInputStream input = null;
        //try {
            //preparação para a leitura do ficheiro
            //input = new DataInputStream(new BufferedInputStream( new FileInputStream(f)));
            String url = "file:C:/Users/Bruno/Documents/NetBeansProjects/Reflection/" +ClassName;
            URL myUrl;
            URLConnection connection;
            try {
                myUrl = new URL(url);
                connection = myUrl.openConnection();
                input = new DataInputStream(new BufferedInputStream( connection.getInputStream()));
            } catch (MalformedURLException ex) {
                Logger.getLogger(TaskLoader.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(TaskLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
                       
            
        //} catch (FileNotFoundException ex) {
            //Logger.getLogger(Reflection.class.getName()).log(Level.SEVERE, null, ex);
        //}
        if(input!=null){
            //Criar a estrutura onde vai ser escrita os bytes do ficheiro
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();

            //enquanto existir informação no array vai sendo colocada na estrutura
            while (true) {
                try {
                    outStream.write(input.readByte());
                } catch (IOException e) {
                    break;
                }
            }
            //retornar um array de Bytes com os dados do ficheiro
            return outStream.toByteArray();
        }
        return null;
    }    
    
    /**
     * Método que permite criar uma class java a partir de um array de bytes
     * @param array Array de Bytes
     * @return 
     */
    private Class createClass(byte[] array) {

        Class c = null;

        try {
            //super.
            c = defineClass(null, array, 0, array.length);
            resolveClass(c);
            //if (resolve) {
                //resolveClass(c);
                //classList.put(ClassName, c);
            //}
        } catch (ClassFormatError e) {
            e.printStackTrace();
        }

        return (c);
    }    
    
}
