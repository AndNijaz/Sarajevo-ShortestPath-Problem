package org.example;

import java.util.HashMap;
import java.util.Map;

public class ThreadSafeSingletonPlaces {

    private static volatile ThreadSafeSingletonPlaces instance;
    private Map<String, Place> placesMap;
    private int counter;

    private ThreadSafeSingletonPlaces() {
        // Initialization code, if any
        placesMap = new HashMap<>();
    }

    public static ThreadSafeSingletonPlaces getInstance() {
        if (instance == null) {
            synchronized (ThreadSafeSingletonPlaces.class) {
                if (instance == null) {
                    instance = new ThreadSafeSingletonPlaces();
                }
            }
        }
        return instance;
    }

    public void addPlace(String key, Place place) {
        if(!placesMap.containsKey(key)){
            placesMap.put(key, place);
        }
        placesMap.put(key, place);
    }

    public Place getPlace(String key) {
        return placesMap.get(key);
    }

    public Map<String, Place> getAllData() {
        return new HashMap<>(placesMap);
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getCounter() {
        return counter;
    }
    public void updateCounter() {
        counter++;
    }
    // Return a copy of the dataMap or an unmodifiable view to prevent external modifications

        // Other methods and properties of the class can be defined here
}