/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module.Administration;

import Module.AbstractAplication;
import com.jezhumble.javasysmon.JavaSysMon;
import java.io.IOException;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class Administration extends AbstractAplication{
    
    public static JavaSysMon mon;
  
    /**
     * Construtor do módulo de Administração (Versão Exprimental) 
     */
    public Administration(){
        super("Administration Module");
        mon = new JavaSysMon();
        try {
            this.AplicationStatus = StartUp();
        } catch (IOException ex) {
            this.AplicationStatus = false;
        }
        
    }
     

    private boolean StartUp() throws IOException{
        
        CommandThread chamar_tudo = new CommandThread();
        chamar_tudo.start();

        GraphicThread chamar_tudo2 = new GraphicThread();
        chamar_tudo2.start();
        
        return true;
    }
}
