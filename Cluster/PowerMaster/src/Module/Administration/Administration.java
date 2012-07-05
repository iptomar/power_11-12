/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module.Administration;

import Module.AbstractAplication;
import Module.WebHTTP.WorkSocket;
import com.jezhumble.javasysmon.JavaSysMon;
import java.io.IOException;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class Administration extends AbstractAplication{
    
    public static JavaSysMon mon;
  
    private WorkSocket ws;
    
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
     
    public void SetWorkSocketReference(WorkSocket ws){
        this.ws = ws;
    }

    private boolean StartUp() throws IOException{
        
        AdminSocketThread chamar_tudo = new AdminSocketThread(this.ws);
        chamar_tudo.setPriority(9);
        chamar_tudo.start();

        GraphicThreadSocket chamar_tudo2 = new GraphicThreadSocket(this.ws);
        chamar_tudo2.setPriority(9);
        chamar_tudo2.start();
        
        return true;
    }
}
