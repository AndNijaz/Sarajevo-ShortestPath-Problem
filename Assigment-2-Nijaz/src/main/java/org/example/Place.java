package org.example;

public class Place {
    String placeName;
    Integer placeID;
    Place(String placeName, Integer placeID){
        this.placeName = placeName;
        this.placeID = placeID;
    }
    public String getPlaceName(){
        return this.placeName;
    }
    public Integer getPlaceID(){
        return this.placeID;
    }
}