package com.example.hollyjack.disneycw;

import android.content.pm.ActivityInfo;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class maps extends FragmentActivity {

    List<disneyMapData> mapDataList;
    public Marker[] mapDataMarkerList = new Marker[6];
    public GoogleMap mapDisneyParks;

    //sets the colour of each marker in the order they are being pulled from the database
    private float markerColour[] = {330.0f, 330.0f, 330.0f, 330.0f, 210.0f, 210.0f};

    private LatLng latLngDisney = new LatLng(28.418744, -81.581207);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps_activity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mapDataList = new ArrayList<disneyMapData>();
        disneyMapDataDBMgr mapDB = new disneyMapDataDBMgr(this,"disneyLandDB.s3db",null,1);
        try {
            mapDB.dbCreate();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mapDataList = mapDB.allMapData();
        SetUpMap();
        AddMarkers();
    }

    public void SetUpMap() {
        mapDisneyParks = ((MapFragment)getFragmentManager().findFragmentById(R.id.disneyMap)).getMap();
        if (mapDisneyParks != null) {
            mapDisneyParks.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngDisney, 12));
            mapDisneyParks.setMyLocationEnabled(true);
            mapDisneyParks.getUiSettings().setCompassEnabled(true);
            mapDisneyParks.getUiSettings().setMyLocationButtonEnabled(true);
            mapDisneyParks.getUiSettings().setRotateGesturesEnabled(true);
        }
    }

    public void AddMarkers() {
        MarkerOptions marker;
        disneyMapData mapData;
        String mrkTitle;
        String mrkText;

        for (int i = 0; i < mapDataList.size(); i++) {
            mapData = mapDataList.get(i);
            mrkTitle = "Park Name: " + mapData.getParkName();
            mrkText = "Address: " + mapData.getParkAddress();
            marker = SetMarker(mrkTitle, mrkText, new LatLng(mapData.getLatitude(), mapData.getLongitude()), markerColour[i], true);
            mapDataMarkerList[i] = mapDisneyParks.addMarker(marker);
        }
    }

    public MarkerOptions SetMarker(String title, String snippet, LatLng position, float markerColour, boolean centreAnchor) {
        float anchorX;
        float anchorY;

        if(centreAnchor) {
            anchorX = 0.5f;
            anchorY = 0.5f;
        } else {
            anchorX = 0.5f;
            anchorY = 1f;
        }

        MarkerOptions marker = new MarkerOptions().title(title).snippet(snippet).icon(BitmapDescriptorFactory.defaultMarker(markerColour)).anchor(anchorX, anchorY).position(position);

        return marker;
    }
}