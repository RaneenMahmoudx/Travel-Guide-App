package com.example.raneenandnemat;

import java.util.Objects;

public class Favorite {
    public static final String TABLE_NAME = "favorites";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ITEM = "item";
    private Destination destination;

    public Favorite(Destination destination) {
        this.destination = destination;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "destination=" + destination +
                '}';
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favorite favorite = (Favorite) o;
        return destination.equals(favorite.destination);
    }


}
