package com.nikhildagrawal.cafelocator.activities;

import android.os.AsyncTask;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nikhildagrawal.cafelocator.http.FetchDataHttp;
import com.nikhildagrawal.cafelocator.models.Cafes;
import com.nikhildagrawal.cafelocator.parser.DataParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GetAsyncData extends AsyncTask<Object, String, String> {

    private String googlePlacesData;
    private GoogleMap mMap;
    String url;

    public static ArrayList<Cafes> resultList;
    @Override
    protected String doInBackground(Object... objects){


        mMap = (GoogleMap)objects[0];
        url = (String)objects[1];

        FetchDataHttp downloadURL = new FetchDataHttp();
        try {
            googlePlacesData = downloadURL.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return googlePlacesData;
    }

    @Override
    protected void onPostExecute(String s){

        List<HashMap<String, String>> nearbyPlaceList;
        DataParser parser = new DataParser();
        nearbyPlaceList = parser.parse(s);
        showNearbyPlaces(nearbyPlaceList);
        resultList = getListResult(nearbyPlaceList);

    }

    private void showNearbyPlaces(List<HashMap<String, String>> nearbyPlaceList)
    {
        for(int i = 0; i < nearbyPlaceList.size(); i++)
        {
            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String, String> googlePlace = nearbyPlaceList.get(i);

            String placeName = googlePlace.get("place_name");
            String rating = googlePlace.get("rating");
            double lat = Double.parseDouble( googlePlace.get("lat"));
            double lng = Double.parseDouble( googlePlace.get("lng"));

            LatLng latLng = new LatLng( lat, lng);
            markerOptions.position(latLng);
            markerOptions.title(placeName + " : "+ rating);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            mMap.addMarker(markerOptions);
        }
    }

    public ArrayList<Cafes> getListResult(List<HashMap<String,String>> result){

        ArrayList<Cafes> resultList = new ArrayList<>();

        for (Map map:result) {

            String name = (String) map.get("place_name");
            String rating =(String) map.get("rating");
            resultList.add(new Cafes(name,Double.valueOf(rating)));
        }

        Collections.sort(resultList, new Comparator<Cafes>() {

            @Override
            public int compare(Cafes o1, Cafes o2) {
                return Double.compare(o2.getRating(),o1.getRating());
            }
        });

        return resultList;
    }
}
