package com.f.minmadairports;

import java.io.Serializable;

/**
 * Created by ferdinand on 25-10-2017.
 */

public class Airport implements Serializable {
    int id;
    String icao;
    String name;
    double longitude;
    double lattitude;
    String elevation;
    String iso_country;
    String municipaty;

    public Airport(String icao, String name, double longitude, double lattitude, String elevation, String iso_country, String municipaty) {
        this.icao = icao;
        this.name = name;
        this.longitude = longitude;
        this.lattitude = lattitude;
        this.elevation = elevation;
        this.iso_country = iso_country;
        this.municipaty = municipaty;
    }

    public int getId(){
        return id;
    }

    public String getIcao() {
        return icao;
    }

    public void setIcao(String icao) {
        this.icao = icao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    public String getIso_country() {
        return iso_country;
    }

    public void setIso_country(String iso_country) {
        this.iso_country = iso_country;
    }

    public String getMunicipaty() {
        return municipaty;
    }

    public void setMunicipaty(String municipaty) {
        this.municipaty = municipaty;
    }
}
