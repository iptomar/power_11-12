/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.logging.Level;
import java.util.logging.Logger;
import reflection.GenericObject;
import reflection.TaskLoader;

/**
 * 
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class Reflection {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            //localizar e abrir ficheiro
            //TaskLoader Task1 = new TaskLoader(ClassLoader.getSystemClassLoader(),"Teste.class");
            //Class newTask1 = Task1.getClassObject();

            //Criar o objecto que vai carregar o ficheiro que contem a class
            TaskLoader Task2 = new TaskLoader(ClassLoader.getSystemClassLoader(),"Teste2.class");
            //Objeter a class carregada por o objecto
            Class newTask2 = Task2.getClassObject();
            
        try {
            //GenericObject gObj1 = (GenericObject) newTask1.newInstance();
            //gObj1.Execution();
            //Object ob1 = newTask1.newInstance();
            
            //Cast para o objecto genérico. Este cast é necessário para ser possivel executar os métodos conhecidos e implementados por a interface GanericTask
            GenericObject gObj2 = (GenericObject) newTask2.newInstance();
            //Execução do método conhecido.
            gObj2.Execution();
            Object ob2 = newTask2.newInstance();            
            
        } catch (InstantiationException ex) {
            Logger.getLogger(Reflection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Reflection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
