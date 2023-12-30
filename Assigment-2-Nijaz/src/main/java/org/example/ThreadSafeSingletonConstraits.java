package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ThreadSafeSingletonConstraits {

    private static volatile ThreadSafeSingletonConstraits instance;
    private ArrayList<Constrait> constraitsList;

    private ThreadSafeSingletonConstraits() {
        // Initialization code, if any
        constraitsList = new ArrayList<>();
    }

    public static ThreadSafeSingletonConstraits getInstance() {
        if (instance == null) {
            synchronized (ThreadSafeSingletonConstraits.class) {
                if (instance == null) {
                    instance = new ThreadSafeSingletonConstraits();
                }
            }
        }
        return instance;
    }

    public void addConstrait(Constrait constrait) {
       constraitsList.add(constrait);
    }

    public Constrait getConstrait(int i) {
        return constraitsList.get(i);
    }

    public ArrayList<Constrait> getAllData() {
        return new ArrayList<>(constraitsList);
    }
        // Return a copy of the dataMap or an unmodifiable view to prevent external modifications

        // Other methods and properties of the class can be defined here
}