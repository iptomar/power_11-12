/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module.WebHTTP;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class WebFileDownloader {
    
    /**
     * Ver http://cookbooks.adobe.com/post_Download_a_file_from_a_URL_in_Java-17947.html
     */
    
    /**
     * Tamanho do buffer de leitura
     */
    private static final int BUFFER_SIZE = 1024;
    
    /**
     * Método que permite fazer download de um ficheiro na Web
     * @param url Objecto do tipo URL que contem o local do ficheiro na web
     * @return Retorno de todo o ficheiro web num objecto do tipo String
     * @throws IOException 
     */
    public static String Download(URL url) throws IOException{
        url.openConnection();
        InputStream dataIN = url.openStream();
        
        byte[] buffer = new byte[BUFFER_SIZE];
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
         
        while ((dataIN.read(buffer)) > 0){
            byteBuffer.write(buffer, 0, buffer.length);
            buffer = new byte[BUFFER_SIZE];           
        }
        
        buffer = byteBuffer.toByteArray();
        String data = new String(buffer);
        
        return data;
    }    
}
   
