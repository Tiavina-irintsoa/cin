package foncier;

import com.google.gson.annotations.SerializedName;

public class Coordonnees {

  @SerializedName("lng")
  String longitude;

  @SerializedName("lat")
  String latitude;

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }
}
