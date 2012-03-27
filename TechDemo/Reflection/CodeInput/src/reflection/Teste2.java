/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reflection;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class Teste2 extends GenericObject{
    
    private int[] t;
    
    public Teste2(){
        t = new int[1000];
        for (int i = 0; i < 1000; i++) {
            t[i] = (int)Math.random()*1000+1;
        }
    }

    public boolean verPar(int x){
        if(x%2==0){
            return true;
        }
        return false;
    }    
    
    @Override
    public void Execution() {
        int total=0;
        for (int i = 0; i < 1000; i++) {
            total+=t[i];
            //if(verPar(i)){
                //System.out.println(i+":É par.");
            //}
            System.out.println("Este é o valor total até agora:"+total);
        }
        super.Execution();
    }
    
    
    
}
