using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net.Sockets;
using System.IO;
using System.Timers;

namespace PowerComputing
{
    class MySocket
    {
        public TcpClient Cliente { get; set; }

        public NetworkStream Stream { get; set; }

        public StreamReader In { get; set; }
        public StreamWriter Out { get; set; }


        public int Porta { get; set; }
        public String IP { get; set; }

        public String EnviaPedido(string txt, bool read)
        {

            Cliente = new TcpClient(IP, Porta);

            Cliente.ReceiveTimeout = 3000;
            Cliente.SendTimeout = 3000;

            Stream = Cliente.GetStream();

            In = new StreamReader(Stream);
            Out = new StreamWriter(Stream);

            Out.WriteLine(txt);
            Out.Flush();

            String recebi;
            if(read)
                recebi = In.ReadLine();
            else
                recebi = "";

            Stream.Close();

            return recebi;
        }

    }
}
