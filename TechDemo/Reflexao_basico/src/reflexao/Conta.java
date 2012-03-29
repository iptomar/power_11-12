/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reflexao;

/**
 *
 * @author Miranda
 */
public class Conta{
    public String nome;

    public Conta(){}
    
    public Conta(String nome) {
        this.nome = nome;
    }
    
    public String OlaMundo(){
        return "Ola Mundo, Eu sou a Class Conta, enviaste o nome: " + nome;
    }
    
    public String OlaMundoParametros(String info){
        return "Ola MundoParametros, Eu sou a Class Conta, enviaste o nome: " + info;
    }

}
