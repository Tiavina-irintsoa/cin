package banque;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.google.gson.annotations.SerializedName;

public class Operation {

  double plus;
  double moins;

  @SerializedName("dateOperation")
  private String dateOperationString;

  public LocalDate getDateOperation() {
    return LocalDate.parse(dateOperationString);
  }

  public double getPlus() {
    return plus;
  }

  public void setPlus(double plus) {
    this.plus = plus;
  }

  public double getMoins() {
    return moins;
  }

  public void setMoins(double moins) {
    this.moins = moins;
  }

  public String getDateOperationString() {
    return dateOperationString;
  }

  public void setDateOperationString(String dateOperationString) {
    this.dateOperationString = dateOperationString;
  }
}
