package banque;

import java.util.List;

import sante.Citoyen;

public class FicheBanque {
    Citoyen citoyen;
    List<Operation> operations;
    public Citoyen getCitoyen() {
        return citoyen;
    }
    public void setCitoyen(Citoyen citoyen) {
        this.citoyen = citoyen;
    }
    public List<Operation> getOperations() {
        return operations;
    }
    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }
    public double getSolde(){
        double s= 0;
        for (Operation operation : operations) {
            s+=operation.getPlus();
            s-=operation.getMoins();
        }
        return s;
    }
}
