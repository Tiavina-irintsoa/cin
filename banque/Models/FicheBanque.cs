using System.Data.SQLite;
using banque.Models;
using banque.Models;

namespace RH.Models{
    public class FicheBanque{
        public Citoyen citoyen { get; set; }
        public List<Operation>? operations  { get; set; }

        public FicheBanque(string idcin){
            citoyen = Citoyen.getCitoyen(idcin);
            this.operations = GetOperations(idcin);
        }
        public double GetSolde(){
            double solde =0;
            foreach (Operation operation in this.operations)
            {
                solde+=operation.Plus;
                solde-=operation.Moins;
            }  
            return solde; 
        }
        public static List<Operation> GetOperations(string idcin){
            Connect c = new Connect();
            SQLiteConnection connection = c.GetConnectionSqlite();
            try
            {   
                
                List<Operation> operationList = new List<Operation>();

                string query = "SELECT * FROM operation where idcin = '"+idcin+"'";
                Console.WriteLine(query);
                SQLiteCommand command = new SQLiteCommand(query, connection);
                using (SQLiteDataReader reader = command.ExecuteReader()){
                    while (reader.Read())
                    {
                        DateTime date = Convert.ToDateTime(reader["dateOperation"]);
                        string datestring = date.ToString("yyyy-MM-dd");
                        Console.WriteLine(datestring);
                        Console.WriteLine("datestring");
                        Operation operation = new Operation
                        (
                            datestring,
                            Convert.ToDouble(reader["montant_en_plus"]),
                            Convert.ToDouble(reader["montant_en_moins"])  
                        );
                        Console.WriteLine(Convert.ToDecimal(reader["montant_en_plus"]));
                        // if(reader.IsDBNull(reader.GetOrdinal("idtransfert"))==false){
                        //     operation.Transfert = new Transfert(Convert.ToInt32(reader["idtransfert"]));
                        //     operation.Transfert.getTransfert(connection);
                        // }
                        operationList.Add(operation);
                    }
                }
                
                return operationList;
            }
            catch (Exception ex)
            {
                throw ex;
            }
            finally{
                connection.Close();
            }

        }    
    }
}