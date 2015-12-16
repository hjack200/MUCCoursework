package com.example.hollyjack.disneycw;

import java.io.Serializable;


public class disneyMapData implements Serializable {

    private int ParkID;
    private String ParkName;
    private String ParkAddress;
    private float Latitude;
    private float Longitude;

    private static final long serialVersionUID = 0L;

    public int getParkID() {
        return ParkID;
    }

    public void setParkID(int parkID) {
        this.ParkID = parkID;
    }

    public String getParkName() {
        return ParkName;
    }

    public void setParkName(String parkName) {
        this.ParkName = parkName;
    }

    public String getParkAddress() {
        return ParkAddress;
    }

    public void setParkAddress(String parkAddress) {
        this.ParkAddress = parkAddress;
    }

    public float getLatitude()
    {
        return Latitude;
    }

    public void setLatitude(float Lat)
    {
        this.Latitude = Lat;
    }

    public float getLongitude()
    {
        return Longitude;
    }

    public void setLongitude(float fLongitude)
    {
        this.Longitude = fLongitude;
    }

    @Override
    public String toString() {
        String mapData;
        mapData = "disneyMapData [ParkID=" + ParkID;
        mapData = ", Park Name=" + ParkName;
        mapData = ", Park Address=" + ParkAddress;
        mapData = ", Longitude=" + Longitude;
        mapData = ", Latitude=" + Latitude +"]";
        return mapData;
    }
}
