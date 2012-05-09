/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reflection;

import genetics.Individual;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import operators.Operator;
import org.xeustechnologies.jcl.JarClassLoader;
import org.xeustechnologies.jcl.JclObjectFactory;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class GeneticLoader {

    private String jarFile = "GeneticAlgoritms.jar";
    private Map genericList;
    private JarClassLoader jcl;
    public static String STRING_GENETIC = "genetics";
    public static String STRING_ALGOTITHMS = "algorithms";
    public static String STRING_OPERATORS = "operators";
    public static String STRING_MUTATION = "mutation";
    public static String STRING_REPLACEMENTS = "replacements";
    public static String STRING_SELECTIONS = "selections";
    public static String STRING_RECOMBINATIONS = "recombinations";

    public GeneticLoader() {
        loadClasses();
    }

    private void loadClasses() {
        //Carregar objectos da biblioteca jar
        jcl = new JarClassLoader();
        jcl.add(jarFile);

        //obter os recursos carregados da biblioteca
        genericList = jcl.getLoadedResources();
        Set keys = genericList.keySet();

        for (Iterator i = keys.iterator(); i.hasNext();) {
            String key = (String) i.next();
            //System.out.println(key);
        }
    }

    public String getConstructors(String classToLoad) {
        byte[] classData = (byte[]) genericList.get(classToLoad);
        classToLoad = classToLoad.replace("/", ".");
        classToLoad = classToLoad.replace(".class", "");
        TaskLoader tl = new TaskLoader(classData, classToLoad);
        Class c = tl.getClassObject();
    


        StringBuilder sb = new StringBuilder();
        sb.append(new String("Objecto: " + classToLoad) + "\n");
  
        //Verificar se a class é abstracta
        //http://stackoverflow.com/questions/1072890/how-can-i-determine-whether-a-java-class-is-abstract-by-reflection
        if(Modifier.isAbstract(c.getModifiers())){
            sb.append("    +Class abstracta");
            return sb.toString();
        }  
        if(c.isEnum()){
            sb.append("    +Enumeração");
            return sb.toString();
        }
        sb.append("INFO:\n" + getInfo(c));

        Constructor[] construtors = c.getConstructors();

        sb.append(new String("**********************************************************************"));
        sb.append(new String("\nNúmero de construtores :" + construtors.length));
        for (int i = 0; i < construtors.length; i++) {
            Class[] parameters = construtors[i].getParameterTypes();
            sb.append(new String("\n  +" + construtors[i].getName()));
            if (parameters.length == 0) {
                sb.append(new String(" -- Construtor por defeito"));
            } else {
                sb.append(new String(" -- " + parameters.length + " parametros"));
            }
            for (int j = 0; j < parameters.length; j++) {
                sb.append(new String("\n            -" + parameters[j].getCanonicalName()));
                if (parameters[j].isPrimitive()) {
                    sb.append(new String("  -- Primitiva"));
                } else {
                    sb.append(new String("  -- Objecto"));
                }
            }
        }
        return sb.toString();
    }

    private String getInfo(Class c){
        try {
            if (c.getName().contains(STRING_ALGOTITHMS)) {
                Individual obj = (Individual) c.newInstance();
                return obj.getInfo() + "\n\n";
            } else {

                Operator obj = (Operator) c.newInstance();
                return obj.getInfo() + "\n\n";

            }
        } catch (Exception e) {
            return "Erro no reflection.\n Possivelmente existe uma alteração nas classes da genetic lab.\n Falar com a Power Computing";
        }
    }

    private ArrayList<String> loadClasses(String Field) {
        Set keys = genericList.keySet();

        ArrayList<String> data = new ArrayList<String>();
        for (Iterator i = keys.iterator(); i.hasNext();) {
            String key = (String) i.next();
            if (key.contains(Field)) {
                data.add(key);
            }
        }

        return data;
    }

    public ArrayList<String> getAlgorithms() {
        return loadClasses(STRING_ALGOTITHMS);
    }

    public String getInfoJSON(String dir) {
        ArrayList<String> ar = loadClasses(dir);
        String ax="[";
        for (int i = 0; i < ar.size(); i++) {
            String classToLoad = ar.get(i);
            byte[] classData = (byte[]) genericList.get(classToLoad);
            classToLoad = classToLoad.replace("/", ".");
            classToLoad = classToLoad.replace(".class", "");
            TaskLoader tl = new TaskLoader(classData, classToLoad);
            Class c = tl.getClassObject();    
            
            if(Modifier.isAbstract(c.getModifiers())){
                //ax +=("['"+classToLoad+"','Class abstracta'],");
                continue;
            }  
            if(c.isEnum()){
                //ax +=("['"+classToLoad+"','Enumeração'],");
                continue;
            }              
            
            ax+="[";
            ax+="'"+classToLoad+"',";
            ax+="'"+getInfo(c).replace("\n", "")+"'";
            ax+="]";
            if(i<ar.size()-1){
                ax+=",";
            }
        }
        if(ax.charAt(ax.length()-1)==','){
            //System.out.println("\n\n Encontrado , \n\n");
            ax = ax.substring(0, ax.length()-1);
        }
        ax+="]";
        return ax;
    }    
    
    public ArrayList<String> getGenetics() {
        return loadClasses(STRING_GENETIC);
    }

    public ArrayList<String> getMutation() {
        return loadClasses(STRING_MUTATION);
    }

    public ArrayList<String> getOperators() {
        return loadClasses(STRING_OPERATORS);
    }

    public ArrayList<String> getReplacements() {
        return loadClasses(STRING_REPLACEMENTS);
    }

    public ArrayList<String> getSelections() {
        return loadClasses(STRING_SELECTIONS);
    }

    public ArrayList<String> getRecombinations() {
        return loadClasses(STRING_RECOMBINATIONS);
    }
    /**
     * @param args the command line arguments
     */
    /*public static void main(String[] args) {
    try {
    ClassLoader classLoader = GeneticLoader.class.getClassLoader();
    
    String url = "D:\\GeneticAlgoritms.jar";
    
    //JarResources jcl = new JarResources();  
    //jcl.loadJar("GeneticAlgoritms.jar");
    JarClassLoader jcl = new JarClassLoader();
    jcl.add("GeneticAlgoritms.jar");
    
    Map list = jcl.getLoadedResources();
    Set keys = list.keySet();
    
    for (Iterator i = keys.iterator(); i.hasNext();) {
    String key = (String) i.next();
    //Class value = (Class) list.get(key);
    //System.out.println(key+" - "+ value.getSimpleName());
    System.out.println(key);
    }
    //JclObjectFactory factory = JclObjectFactory.getInstance();
    
    System.out.println("\n\n\n\n\n");
    
    System.out.println("A carregar genetics.Solver");
    byte[] classData = (byte[]) list.get("genetics/algorithms/OnesMax.class");
    TaskLoader tl = new TaskLoader(classData, "genetics.algorithms.OnesMax");
    Class c = tl.getClassObject();
    Constructor[] construtors = c.getConstructors();
    
    System.out.println("Número de construtores:" + construtors.length);
    for (int i = 0; i < construtors.length; i++) {
    Class[] parameters = construtors[i].getParameterTypes();
    System.out.println("--" + construtors[i].getName());
    for (int j = 0; j < parameters.length; j++) {
    System.out.println("------" + parameters[j].getCanonicalName());
    }
    }
    
    } catch (Exception e) {
    e.printStackTrace();
    }
    //Object obj = factory.create(jcl,"");
    //factory.
    //Create object of loaded class  
    //Object obj = factory.create(jcl,"mypackage.MyClass");
    }*/
}
