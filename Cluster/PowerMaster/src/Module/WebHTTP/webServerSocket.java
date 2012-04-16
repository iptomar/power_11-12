/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module.WebHTTP;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Properties;
import powermaster.SolverThread;

public class webServerSocket extends NanoHTTPD {

    /**
     * Variável interna para guardar o enderenço IP do cliente
     */
    private String thisIp;

    /**
     * Contrutor do objecto webServerSocket
     * Este objecto permite criar um servidor web na porta especificada no contrutor.
     * Ver documentação e código do ficheiro NanoHTTPD.java
     * http://elonen.iki.fi/code/nanohttpd/
     * @param port
     * @throws IOException 
     */
    public webServerSocket(int port) throws IOException {
        super(port, new File("C:\\xampp\\htdocs\\index.html"));
        //System.out.println(port);
    }

    /**
     * Método que permite fazer uma resposta ao cliente HTTP
     * Ver documentação e código do ficheiro NanoHTTPD.java
     * http://elonen.iki.fi/code/nanohttpd/
     * @param uri
     * @param method
     * @param header
     * @param parms
     * @param files
     * @param s
     * @return 
     */
    @Override
    public Response serve(String uri, String method, Properties header, Properties parms, Properties files, Socket s) {

        try {
            thisIp = InetAddress.getLocalHost().getHostAddress();
            System.out.println(s.getInetAddress().getHostAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //int sizePopulation, int sizeAllelo, int intera, int bestfitness
        //code.dei.estt.ipt.pt:8080?pop=<val>&alello=<val>&itera=<val>&best=<val>
        if (parms.getProperty("pop") != null && parms.getProperty("alello") != null && parms.getProperty("itera") != null && parms.getProperty("best") != null) {
            int pop = Integer.parseInt(parms.getProperty("pop"));
            int alello = Integer.parseInt( parms.getProperty("alello"));
            int itera = Integer.parseInt(parms.getProperty("itera"));
            int best = Integer.parseInt(parms.getProperty("best"));
            System.out.println("Population: " + pop + "\n Alello: " + alello + "\n Iterations: " + itera+"\n BestValue:" + best);
            
            SolverThread thr = new  SolverThread(pop,alello,itera,best);
            thr.start();              
            
            //SolverThread thr = new SolverThread(Integer.parseInt(pop), Integer.parseInt(max), Double.parseDouble(mut));
            //thr.start();
        }

           // Solver __newSolver = new Solver(1000, 100, new OnesMax(), 100000, 99, new GeneticEvents());
           // __newSolver.run(); 
//        if (parms.getProperty("pop")!=null){
//            SolverThread thr = new  SolverThread();
//            thr.start();
//        }
        //System.out.println( method + " '" + uri + "' " );
	String msg = "<html><head></head><body onload='submitform()'>";
        msg += "<script type='text/javascript'>";
        msg += "function submitform()	{window.location='http://"+ WebServer.REDIRECT_IP +"/index.html';}</script></body></html>";


        return new NanoHTTPD.Response(HTTP_OK, MIME_HTML, msg);
    }
    /*public static void main( String[] args )
    {
    try
    {
    new webSocketServer(8080);
    }
    catch( IOException ioe )
    {
    System.err.println( "Couldn't start server:\n" + ioe );
    System.exit( -1 );
    }
    System.out.println( "Listening on port 8080. Hit Enter to stop.\n" );
    try { System.in.read(); } catch( Throwable t ) {};
    }*/
}