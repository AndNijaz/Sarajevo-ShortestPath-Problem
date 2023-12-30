package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ThreadSafeSingletonConstraits {

    private static volatile ThreadSafeSingletonConstraits instance;
    private ArrayList<Constrait> constraitsList;

    private ThreadSafeSingletonConstraits() {
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
}