using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;

namespace PowerComputing.Classes
{
    class Acesso : Conectar
    {
        public void Registar()
        {
            string[] guid = Guid.NewGuid().ToString().Split('-');
            string salt = "";

            for (int i = 0; i < guid.Length - 2; i++)
            {
                salt += guid[i];
            }

            Coneccao.Open();
            Comando.CommandText = inserts;
            Comando.ExecuteNonQuery();
            Coneccao.Close();
        }

        public bool Login(string email, string password)
        {
            Comando.Parameters.AddWithValue("@email", email);
            Comando.Parameters.AddWithValue("@password", password);

            Coneccao.Open();

            Comando.CommandText = "SELECT * FROM logins WHERE email = @email AND password = SHA1(CONCAT(@password, salt))";
            DataTable dt = new DataTable();

            dt.Load(Comando.ExecuteReader());

            Coneccao.Close();

            Comando.Parameters.Clear();

            if (dt.Rows.Count == 0)
                return false;
            else
            {
                Utilizador.ID_LOGIN = dt.Rows[0]["idLogin"].ToString();
                Utilizador.NOME = dt.Rows[0]["name"].ToString();
                Utilizador.EMAIL = dt.Rows[0]["email"].ToString();

                return true;
            }

        }
    }
}
