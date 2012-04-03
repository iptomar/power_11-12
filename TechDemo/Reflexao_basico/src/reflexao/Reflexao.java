/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reflexao;

import java.lang.String;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 *
 * @author Miranda
 */
public class Reflexao{
    
    public static void main(String[] args) throws Exception{
        //Localizacao da Class 
        String nome= "reflexao.Conta";
        Class classe = Class.forName(nome);
        //Retorna o nome da Classe
        System.out.println(classe.getSimpleName()+"\n");
        
        //Retorna um array das variaveis publicas da classe
        Field[] f = classe.getFields();
        for (Field field : f) {
            System.out.println(field);
        }
        
        //System.out.println("\n");
        
        //Retorna um array dos metodos da classe
        Method[] m = classe.getDeclaredMethods();
        for (Method method : m) {
            System.out.println(method.getName());
        }
        
        //System.out.println("\n");
        
        //Retorna um array dos Contrutores da classe
        Constructor[] c = classe.getConstructors();
        for (Constructor constructor : c) {
            System.out.println(constructor);
        }
        
        //System.out.println("\n");
        
        //Executar metodos
        Object o = classe.newInstance();//Inicia os metodos com o construtor vazio
        Method md = classe.getMethod("OlaMundo");
        Method me = classe.getMethod("OlaMundoParametros", String.class);
        
        //Executar Construtor
        Constructor co = classe.getConstructor(String.class);//Inicia os metodos com o construtor de parametros
        Object o2 = co.newInstance("Miguel");
        
        //Executar Metodos Mesmo
        System.out.println(md.invoke(o2));
        System.out.println( me.invoke(o2,"Miranda"));
        

    }

    
}
