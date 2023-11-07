using System; 
using System.Data.SQLite;

namespace banque.Models{
    public class Connect{
        public SQLiteConnection  GetConnectionSqlite(){
            string connectionString = "Data Source=C:/Users/Tita/banque.db;Version=3;";
            SQLiteConnection connection = new SQLiteConnection(connectionString);
            connection.Open();
            return connection;
        }
    }
}