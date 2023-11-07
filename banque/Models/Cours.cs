namespace banque.Models{
    public class Cours{
        public int idCours   {get;set;}
        public Devise ? devise {get;set;}
        public double tauxAchat {get;set;}
        public double tauxVente {get;set;}
        public DateTime  dateCours  {get;set;}   
        public Cours(int iddevise){
            devise = new Devise();
            devise.IdDevise = iddevise;
        }
    }
}