public class Airport {
  private String id;
  private String name;
  private String city;
  private String country;
  private String iata_code;
  private String icaco_code;
  private String latitude;
  private String longitude;
  private String altitude;

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCountry() {
    return this.country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getIata_code() {
    return this.iata_code;
  }

  public void setIata_code(String iata_code) {
    this.iata_code = iata_code;
  }

  public String getIcaco_code() {
    return this.icaco_code;
  }

  public void setIcaco_code(String icaco_code) {
    this.icaco_code = icaco_code;
  }

  public String getLatitude() {
    return this.latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public String getLongitude() {
    return this.longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public String getAltitude() {
    return this.altitude;
  }

  public void setAltitude(String altitude) {
    this.altitude = altitude;
  }

  public Airport(String id, String name, String city, String country, String iata, String icao, String lat, String lon, String alt) {
    this.id = id;
    this.name = name;
    this.city = city;
    this.country = country;
    this.iata_code = iata;
    this.icaco_code = icao;
    this.latitude = lat;
    this.longitude = lon;
    this.altitude = alt;
  }
}
