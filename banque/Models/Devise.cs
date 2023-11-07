using Newtonsoft.Json;

namespace banque.Models{
    public class Devise{
        public int? IdDevise {get;set;}
        public string? NomDevise{get;set;}

        public static List<Devise> GetDeviseInWs(){
            using (HttpClient client = new HttpClient())
            {
                string apiUrl = "http://127.0.0.1:8080/sante/appli/devise";
                HttpResponseMessage response = client.GetAsync(apiUrl).Result;
                if (response.IsSuccessStatusCode)
                {
                    string responseBody = response.Content.ReadAsStringAsync().Result;
                    List<Devise> res = JsonConvert.DeserializeObject<List<Devise>>(responseBody);
                    return res;
                    }
                else
                {
                    throw new Exception("Citoyen invalide");  
                }
            };
        }
        
    }
}