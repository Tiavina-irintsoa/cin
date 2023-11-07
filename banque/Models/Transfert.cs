using System.Data.SQLite;
using banque.Models;
using Newtonsoft.Json;

namespace banque.Models{
    public class  Transfert{
        public int? IdTransfert {get;set;}
        public Citoyen ? Sender {get;set;}
        public Citoyen ? Receiver {get;set;}
        public double montant {get;set;}
        public Cours ? cours {get;set;} 
        public string dateTransfert {get;set;} 


        public Transfert(){

        }
        public Transfert(int idtransfert){
            IdTransfert = idtransfert;
        }
        public void getTransfert(SQLiteConnection connection){
            Boolean opened = false;
            if(connection == null){
                opened = true;
                Connect c = new Connect();
                connection = c.GetConnectionSqlite();
            }
            try
            {
                string sql = "select * from transfert where idtransfert = @idtransfert";
                using (SQLiteCommand command = new SQLiteCommand(sql, connection))
                {
                    command.Parameters.AddWithValue("@idtransfert", IdTransfert);
                    SQLiteDataReader reader = command.ExecuteReader();
                    reader.Read();
                    this.cours = new Cours(Convert.ToInt32(reader["iddevise"]));
                    this.Sender = new Citoyen(Convert.ToString(reader["sender"]));
                    this.montant = Convert.ToDouble(reader["montant"]);
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
        public Transfert(string sender, string receiver,string montant,string iddevise, string date){
            Sender = new Citoyen(sender);
            Receiver = new Citoyen(receiver);
            this.montant = double.Parse(montant);
            if(this.montant<=0){
                throw new Exception("Montant invalide");
            }
            dateTransfert = date;
            cours = new Cours(int.Parse(iddevise));
            cours = GetDeviseInWs();
        }

        public void Do(){
            if(SenderHasEnoughMoney() ==  false){
                throw new Exception("Montant insuffisant");
            }
            Connect connect = new Connect();
            SQLiteConnection connection = connect.GetConnectionSqlite();
            try{
                insertTransfert(connection);
                Sender.envoyer(getMontantAriaryAchat(),this,connection);
                Receiver.recevoir(getMontantAriaryVente(),this,connection);
            }
            catch(Exception e){
                throw e;
            }
            finally{
                connection.Close();
            }
        }
        public void insertTransfert(SQLiteConnection connection){
            Boolean opened = false;
            if(connection == null){
                opened = true;
                Connect c = new Connect();
                connection = c.GetConnectionSqlite();
            }
            try
            {
                string sql = "insert into transfert (iddevise, montant, sender, receiver,dateTransfert) values (@iddevise, @montant,@sender,@receiver , @dateTransfert) returning idtransfert";
                using (SQLiteCommand command = new SQLiteCommand(sql, connection))
                {
                    command.Parameters.AddWithValue("@iddevise", cours.devise.IdDevise);
                    command.Parameters.AddWithValue("@montant",montant);
                    command.Parameters.AddWithValue("@sender",Sender.idcin);
                    command.Parameters.AddWithValue("@receiver",Receiver.idcin);
                    command.Parameters.AddWithValue("@dateTransfert",dateTransfert);
                    IdTransfert = Convert.ToInt32(command.ExecuteScalar());;
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
        public Boolean SenderHasEnoughMoney(){
            return Sender.HasEnoughMoney(getMontantAriaryAchat());
        }
        public double getMontantAriaryAchat(){
            return montant * cours.tauxAchat;
        }
        public double getMontantAriaryVente(){
            return montant * cours.tauxVente;
        }
        public Cours GetDeviseInWs(){
            using (HttpClient client = new HttpClient())
            {
                string apiUrl = "http://127.0.0.1:8080/sante/appli/devise/cours/"+cours.devise.IdDevise+"/"+dateTransfert;
                Console.WriteLine(apiUrl);
                HttpResponseMessage response = client.GetAsync(apiUrl).Result;
                if (response.IsSuccessStatusCode)
                {
                    string responseBody = response.Content.ReadAsStringAsync().Result;
                    Cours res = JsonConvert.DeserializeObject<Cours>(responseBody);
                    return res;
                }
                else
                {
                    throw new Exception("Cours invalide");  
                }
            };
        }
        
    }
}
