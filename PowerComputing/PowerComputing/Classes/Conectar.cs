using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MySql.Data.MySqlClient;
using System.Threading;

namespace PowerComputing.Classes
{
    class Conectar
    {
        //Coneccao e comando
        public MySqlConnection Coneccao { get; set; }
        public MySqlCommand Comando { get; set; }

        public String ConnectionString { get; set; }

        public Conectar()
        {
            ConnectionString = "";

            Coneccao = new MySqlConnection(ConnectionString);
            Comando = new MySqlCommand();

            Comando.Connection = Coneccao;
        }
    }
}
