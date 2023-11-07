import presidence.CitoyenData;

public class App {
    public static void main(String[] args) throws Exception {
        String idcin = "CIN001";
        CitoyenData data = new CitoyenData(idcin);
        data.complete();
    }
}
