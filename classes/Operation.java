package sante;

import java.sql.Date;

public class Operation {
    int idOperation; 
    Date operation;
    PartieCorps partieCorps; 
    public int getIdOperation() {
        return idOperation;
    }
    public void setIdOperation(int idOperation) {
        this.idOperation = idOperation;
    }
    public Date getOperation() {
        return operation;
    }
    public void setOperation(Date operation) {
        this.operation = operation;
    } 
    
}
