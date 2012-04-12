/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module.Administration;

import com.sun.management.OperatingSystemMXBean;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.management.ManagementFactory;
import java.net.Socket;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KopDicht
 */
public class AdministrationClient extends Thread {

    BufferedReader in;
    PrintStream out;
    Socket socket;
    String estado;

    public AdministrationClient(Socket sock) {
        socket = sock;
        try {


            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out = new PrintStream(socket.getOutputStream());

        } catch (IOException ex) {
            Logger.getLogger(AdministrationClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void run() {

        String client = socket.getInetAddress().getHostName() + ":" + socket.getPort();
        String[] cmds;
        String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        //while (true) {
        try {

            String message = in.readLine();
            cmds = message.split(" ");

            if ("get".equals(cmds[0])) {
                if ("-s".equals(cmds[1])) {

                    if ("cores".equals(cmds[2])) {
                        out.println(Runtime.getRuntime().availableProcessors());
                        out.flush();

                    } else if ("date".equals(cmds[2])) {
                        Calendar cal = new GregorianCalendar();
                        int month = cal.get(Calendar.MONTH);
                        int year = cal.get(Calendar.YEAR);
                        int day = cal.get(Calendar.DAY_OF_MONTH);

                        out.println(day + "/" + (month + 1) + "/" + year);
                        out.flush();
                    } else {
                        out.println("Comando não reconhecido");
                        out.flush();
                    }


                } else if ("-l".equals(cmds[1])) {
                    if ("date".equals(cmds[2])) {
                        Calendar cal = new GregorianCalendar();
                        int month = cal.get(Calendar.MONTH);
                        int year = cal.get(Calendar.YEAR);
                        int day = cal.get(Calendar.DAY_OF_MONTH);

                        out.println(day + " de " + meses[month] + " de " + year);
                        out.flush();
                    } else if ("cores".equals(cmds[2])) {
                        out.println("O Servidor tem " + Runtime.getRuntime().availableProcessors() + " Core's");
                        out.flush();
                    } else {
                        out.println("Comando não reconhecido");
                        out.flush();
                    }

                } else if ("-cpu".equals(cmds[1])) {

                    /*if (recebe_cena.mon.osName().contains("Windows")) {

                    OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

                    // What % CPU load this current JVM is taking, from 0.0-1.0
                    //System.out.println(osBean.getProcessCpuLoad());
                    // What % load the overall system is at, from 0.0-1.0
                    //System.out.println(osBean.getSystemCpuLoad());

                    out.println("CPU Usage: " + (int) (osBean.getSystemCpuLoad() * 100) + "%");
                    out.flush();

                    } else {*/
                    String cois = "";


                    Process p;
                    
                    //p = Runtime.getRuntime().exec("./cpu");

                    try {
                        FileInputStream fstream = new FileInputStream("Scripts/cpu.res");
                        DataInputStream in = new DataInputStream(fstream);
                        BufferedReader br = new BufferedReader(new InputStreamReader(in));
                        String str;
                        while ((str = br.readLine()) != null) {
                            cois=str;
                        }
                        in.close();
                    } catch (Exception e) {
                        System.err.println(e);
                    }

                    out.println(cois);
                    
                } else if ("-mem".equals(cmds[1])) {

                    out.println("Memory: " + (Administration.mon.physical().getFreeBytes() / (1024 * 1024)) + "MB / " + (Administration.mon.physical().getTotalBytes() / (1024 * 1024))+"MB");
                    out.flush();
                    
                } else if ("-tasks".equals(cmds[1])) {
                    
                    String cois = "";
                    try {
                        String line;
                        Process p;
                        if (Administration.mon.osName().contains("Windows")) {
                            p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");
                        } else {
                            p = Runtime.getRuntime().exec("ps -e");
                        }
                        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
                        while ((line = input.readLine()) != null) {
                            cois += line + "£";

                        }
                        out.println(cois);
                        input.close();
                        
                    } catch (Exception err) {
                        err.printStackTrace();
                    }


                    out.flush();
                } else {
                    out.println("Comando não reconhecido");
                    out.flush();
                }
            } else if ("kill".equals(cmds[0])) {
                Administration.mon.killProcess(Integer.parseInt(cmds[1]));
                out.println("You just killed " + cmds[1]);
                out.flush();
            } else {
                out.println("Comando não reconhecido");
                out.flush();
            }

            //Administration.txt_consola.append(message+ "\n");



        } catch (Exception ex) {
            System.out.println(" finish " + client + " job.");
            return;
        }
        //}

    }
}
