/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module.Administration;

import com.jezhumble.javasysmon.JavaSysMon;
import java.io.IOException;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class Administration{
    
    public static JavaSysMon mon;
    
    private boolean iniStatus;
    
    public static String ModuleName = "Administration Module";
    
    public Administration(){
        mon = new JavaSysMon();
        try {
            this.iniStatus = StartUp();
        } catch (IOException ex) {
            this.iniStatus = false;
        }
        
    }
    
    public boolean isIniStatus() {
        return iniStatus;
    }    

    private boolean StartUp() throws IOException{
        
        CommandThread chamar_tudo = new CommandThread();
        chamar_tudo.start();

        GraphicThread chamar_tudo2 = new GraphicThread();
        chamar_tudo2.start();
        
        return true;
    }
}
