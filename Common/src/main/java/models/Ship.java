package models;

import java.awt.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Ship implements Serializable {

    private Set<Point> coordinates = new HashSet<>();
    public boolean isAlive = true;

    private Ship() {
    }

    public static Ship createShip(Set<Point> coordinates) {
        Ship ship = new Ship();
        ship.coordinates.addAll(coordinates);
        return ship;
    }

    public void shoot(Point coordinate) {
        coordinates.remove(coordinate);
        isAlive = !coordinates.isEmpty();
        if(! isAlive){
            for (Point cell : coordinates) {
                
            }
        }
    }

    public Set<Point> getCoordinates() {
        return Collections.unmodifiableSet(coordinates);
    }
}
