/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module.Administration;

import Module.Loader.Loader;
import Module.Loader.Problem;
import Module.WebHTTP.WebFileDownloader;
import Module.WebHTTP.WorkSocket;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import powermaster.PowerMaster;
import powermaster.SolverThread;
import reflection.GeneticLoader;

/**
 *
 * @author KopDicht
 */
public class AdminClientThread extends Thread {

    BufferedReader in;
    PrintStream out;
    Socket socket;
    String estado;
    public static SolverThread[] arrayThread;
    private WorkSocket ws;

    public AdminClientThread(Socket sock, WorkSocket ws) {
        socket = sock;
        this.ws = ws;
        try {


            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out = new PrintStream(socket.getOutputStream());

        } catch (IOException ex) {
            Logger.getLogger(AdminClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void run() {

        String client = socket.getInetAddress().getHostName() + ":" + socket.getPort();
        String[] cmds;
        String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

        try {
            String message = in.readLine();
            cmds = message.split(" ");
            if ("get".equals(cmds[0])) {
                //<editor-fold>
                if ("-s".equals(cmds[1])) {
                    //<editor-fold>
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
                    //</editor-fold>
                } else if ("-l".equals(cmds[1])) {
                    //<editor-fold>
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
                    //</editor-fold>
                } else if ("-cpu".equals(cmds[1])) {
                    //<editor-fold>

                    /*
                     * if (recebe_cena.mon.osName().contains("Windows")) {
                     *
                     * OperatingSystemMXBean osBean =
                     * ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
                     *
                     * // What % CPU load this current JVM is taking, from
                     * 0.0-1.0 //System.out.println(osBean.getProcessCpuLoad());
                     * // What % load the overall system is at, from 0.0-1.0
                     * //System.out.println(osBean.getSystemCpuLoad());
                     *
                     * out.println("CPU Usage: " + (int)
                     * (osBean.getSystemCpuLoad() * 100) + "%"); out.flush();
                     *
                     * } else {
                     */
                    String cois = "";
                    Process p;

                    //p = Runtime.getRuntime().exec("./cpu");

                    try {
                        FileInputStream fstream = new FileInputStream("Scripts/cpu.res");
                        DataInputStream in = new DataInputStream(fstream);
                        BufferedReader br = new BufferedReader(new InputStreamReader(in));
                        String str;
                        while ((str = br.readLine()) != null) {
                            cois = str;
                        }
                        in.close();
                    } catch (Exception e) {
                        System.err.println(e);
                    }

                    out.println(cois);

                    //</editor-fold>
                } else if ("-mem".equals(cmds[1])) {
                    //<editor-fold>
                    out.println("Memory: " + (Administration.mon.physical().getFreeBytes() / (1024 * 1024)) + "MB / " + (Administration.mon.physical().getTotalBytes() / (1024 * 1024)) + "MB");
                    out.flush();
                    //</editor-fold>
                } else if ("-info".equals(cmds[1])) {
                    //<editor-fold>
                    GeneticLoader genLoad = new GeneticLoader();
                    String res = "";
                    try {
                        // Create file 
                        FileWriter fstream = new FileWriter(cmds[2].toString() + ".ff");
                        BufferedWriter out = new BufferedWriter(fstream);
                        out.write(genLoad.getConstructors("genetics/algorithms/" + cmds[2].toString() + ".class"));
                        //Close the output stream
                        out.close();

                        // Open the file that is the first 
                        // command line parameter

                        FileInputStream stream = new FileInputStream(cmds[2].toString() + ".ff");
                        // Get the object of DataInputStream
                        DataInputStream in = new DataInputStream(stream);
                        BufferedReader br = new BufferedReader(new InputStreamReader(in));
                        String strLine;
                        //Read File Line By Line
                        while ((strLine = br.readLine()) != null) {
                            // Print the content on the console
                            res += strLine.replace("<p>", "").replace("</p>", "€") + "#";
                        }
                        //Close the input stream
                        in.close();
                    } catch (Exception e) {//Catch exception if any
                        System.err.println("Error: " + e.getMessage());
                    }

                    out.println(res.split("#")[2].toString());
                    out.flush();

                    //</editor-fold>

                } else if ("-cpumem".equals(cmds[1])) {
                    //<editor-fold>

                    String cpu = "";
                    Process p;


                    try {
                        FileInputStream fstream = new FileInputStream("Scripts/cpu.res");
                        DataInputStream in = new DataInputStream(fstream);
                        BufferedReader br = new BufferedReader(new InputStreamReader(in));
                        String str;
                        while ((str = br.readLine()) != null) {
                            cpu = str;
                        }
                        in.close();
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                    long TotalMem = Administration.mon.physical().getTotalBytes();

                    long OcupMem = TotalMem - Administration.mon.physical().getFreeBytes();

                    long MemPercent = (OcupMem * 100) / TotalMem;

                    out.println(cpu + "£" + MemPercent);
//                    out.println("50£50");
                    out.flush();

                    //</editor-fold>

                } else if ("-tasks".equals(cmds[1])) {
                    //<editor-fold>
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
                    //</editor-fold>
                } else if ("-running".equals(cmds[1])) {
                    //<editor-fold>
                    try {
                        out.println(ws.getClientsData());


                    } catch (Exception err) {
                        out.println(""+err.getMessage());
                    }

                    out.flush();
                    //</editor-fold>
                } else {
                    //<editor-fold>
                    out.println("Comando não reconhecido");
                    out.flush();
                    //</editor-fold>
                }
                //</editor-fold>
            } else if ("killRunning".equals(cmds[0])) {
                //<editor-fold>
                
                
                //</editor-fold>
            } else if ("kill".equals(cmds[0])) {
                //<editor-fold>
                Administration.mon.killProcess(Integer.parseInt(cmds[1]));
                out.println("You just killed " + cmds[1]);
                out.flush();
                //</editor-fold>
            } else {
                //<editor-fold>
                out.println("Comando não reconhecido");
                out.flush();
                //</editor-fold>
            }

        } catch (Exception ex) {
            System.out.println(ex);
            return;
        }
    }

    public Boolean VerInt(String valor) {
        boolean bem = false;
        try {
            Integer.parseInt(valor);
            bem = true;
        } catch (Exception e) {
            return false;
        }
        return bem;
    }

    public static void RemoteWork(String path) {
        //Inicialização de todos os módulos do PowerMaster
        //Aplication app = new Aplication();

        Problem p = null;
        try {
            String resultado = WebFileDownloader.Download(new URL(path));
            p = Loader.Load(resultado);
            System.out.println("\n" + resultado);

            arrayThread = new SolverThread[PowerMaster.NUM_THREADS];
            AtomicInteger numThreads = new AtomicInteger(PowerMaster.NUM_THREADS);

            for (int i = 0; i < arrayThread.length; i++) {
                arrayThread[i] = new SolverThread(p.getNewSolver(), numThreads);
                arrayThread[i].start();
                arrayThread[i].setName("" + i);
            }

            //AsyncStats async = new AsyncStats(numThreads, PowerMaster.INTERVAL_PART, p.getClientID(), p.getProblemID());
            //async.start();

        } catch (Exception ex) {
            System.out.println("Erro no RemoteWork(): " + ex);
            //EmuladorEcran.Escrever("\n\nERRO: "+ex);
        }
    }
}
