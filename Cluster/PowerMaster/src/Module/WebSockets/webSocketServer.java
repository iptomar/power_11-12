/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module.WebSockets;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Properties;
import powermaster.SolverThread;



public class webSocketServer extends NanoHTTPD
{
    String thisIp;
	public webSocketServer(int port) throws IOException
	{
		super(port,new File("C:\\xampp\\htdocs\\index.html"));
                //System.out.println(port);
	}

    @Override
	public Response serve( String uri, String method, Properties header, Properties parms, Properties files,Socket s )
	{
            
           try {
             thisIp =InetAddress.getLocalHost().getHostAddress();
             System.out.println(s.getInetAddress().getHostAddress());
            }
            catch(Exception e) {
            e.printStackTrace();
           }
            
		//System.out.println( method + " '" + uri + "' " );
		String msg = "<body onload='submitform()'>";
		if ( parms.getProperty("pop") != null&& parms.getProperty("max")!= null && parms.getProperty("mut")  != null ){
			String pop =  parms.getProperty("pop");
                        String max =  parms.getProperty("max");
                        String mut = parms.getProperty("mut");
                        System.out.println("Population: " + pop+ "\nMax Population: "+max + "\nMutation: " + mut);
                        SolverThread thr = new SolverThread(Integer.parseInt(pop), Integer.parseInt(max), Double.parseDouble(mut));
                        thr.start();
                }
                
                
                msg += "<script type='text/javascript'>";
                msg += "function submitform()	{window.location='http://"+ WebSocketConfig.REDIRECT_IP +"/index.html';}</script></body>";
		return new NanoHTTPD.Response( HTTP_OK, MIME_HTML, msg );
	}


	public static void main( String[] args )
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
	}
}