using System.Data.SQLite;
using System.Globalization;
using Newtonsoft.Json;
using RH.Models;

namespace banque.Models{
    public class Citoyen{

        [JsonProperty("idcin")]
        public string? idcin { get; set; }
        [JsonProperty("nom")]
        public string? nom { get; set; }
        [JsonProperty("prenom")]
        public string? prenom { get; set; }
        [JsonProperty("dateNaissance")]
        public string? DateNaissanceString { get; set; }
        [JsonIgnore]
        public DateTime? DateNaissance { get { return DateTime.ParseExact(DateNaissanceString, "yyyy-MM-dd", CultureInfo.InvariantCulture); } }

        [JsonProperty("adresse")]    
        public string? adresse { get; set; }
        public void envoyer(double montant, Transfert transfert,SQLiteConnection connection){
            Operation o = new Operation(transfert.dateTransfert,0,montant,transfert,this);
            o.insert(connection);
        }
        public void recevoir(double montant, Transfert transfert,SQLiteConnection connection){
            Operation o = new Operation(transfert.dateTransfert,montant,0,transfert,this);
            o.insert(connection);
        }
        public static Citoyen getCitoyen(string idcin){
            using (HttpClient client = new HttpClient())
            {
                string apiUrl = "http://127.0.0.1:8080/sante/appli/citoyens/"+idcin;
                HttpResponseMessage response = client.GetAsync(apiUrl).Result;
                if (response.IsSuccessStatusCode)
                {
                    string responseBody = response.Content.ReadAsStringAsync().Result;
                    Citoyen res = JsonConvert.DeserializeObject<Citoyen>(responseBody);
                    return res;
                }
                else
                {
                    throw new Exception("Citoyen invalide");  
                }
            };
        }
        public Boolean HasEnoughMoney(double needed){
            FicheBanque fiche =  new FicheBanque(this.idcin);
            if(needed <= fiche.GetSolde()){
                return true;
            }
            return false;
        }
        public Citoyen(string idcin){
            this.idcin = idcin;
        }
    }
}
