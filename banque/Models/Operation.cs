using System.Data.SQLite;
using banque.Models;
using Newtonsoft.Json;

namespace RH.Models{
    public class Operation{
        [JsonProperty("dateOperation")]
        public string DateOperation { get; set; }
        [JsonProperty("plus")]
        public double Plus { get; set; }
         [JsonProperty("moins")]
        public double Moins { get; set; }
        public Transfert Transfert { get; set; }
        public Citoyen Citoyen;

        public Operation(string date, double plus, double moins){
            this.DateOperation = date; 
            this.Plus = plus; 
            this.Moins = moins;
        }
        public Operation(string date, double plus, double moins, Transfert transfert,Citoyen citoyen){
            this.DateOperation = date; 
            this.Plus = plus; 
            this.Moins = moins;
            this.Transfert = transfert;
            Citoyen = citoyen;
        }
        public void insert(SQLiteConnection connection){
            Boolean opened = false;
            if(connection == null){
                opened = true;
                Connect c = new Connect();
                connection = c.GetConnectionSqlite();
            }
            try
            {
                Console.WriteLine("entered");
                string sql = "INSERT INTO operation (idcin, montant_en_plus, montant_en_moins, dateOperation,idtransfert) values (@idcin, @montant_en_plus, @montant_en_moins, @dateOperation,@idtransfert)";
                using (SQLiteCommand command = new SQLiteCommand(sql, connection))
                {
                    command.Parameters.AddWithValue("@idcin", Citoyen.idcin);
                    command.Parameters.AddWithValue("@montant_en_plus",Plus);
                    command.Parameters.AddWithValue("@montant_en_moins",Moins);
                    command.Parameters.AddWithValue("@dateOperation",Transfert.dateTransfert);
                    command.Parameters.AddWithValue("@idtransfert",Transfert.IdTransfert);
                    int rowsAffected = command.ExecuteNonQuery();
                }
            }
            catch (Exception e)
            {
                throw e;
            }
            finally{
                if(opened){
                    connection.Close();
                }
            }
        }
    }
}