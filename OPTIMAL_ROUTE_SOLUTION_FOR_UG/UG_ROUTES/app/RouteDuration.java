package app;

import java.util.ArrayList;

//Comparable interface implemented with the instantiation of the RouteDuration class
public class RouteDuration implements Comparable<RouteDuration> {
    private Destination origin;
    private Destination destination;
    private long time;
    private int distance;
    private ArrayList<String> landMarks = new ArrayList<>(); // Private ArrayList to be used to store landmarks

    // Edge object structure definition
    public RouteDuration(Destination origin, Destination finalDestination, int routeLength) {
        this.origin = origin;
        this.destination = finalDestination;
        this.distance = routeLength;
        this.time = -1;
    }

    public RouteDuration(Destination source, Destination destination, int distance, long time) {
        this.origin = source;
        this.destination = destination;
        this.distance = distance;
        this.time = time;
    }

    public Destination getDestination() {
        return destination;
    }

    public Destination getOrigin() {
        return origin;
    }

    public int getDistance() {
        return distance;
    }

    public long getTime() {
        return time;
    }

    @Override
    public String toString() {
        return origin.getName() + " -> " + destination.getName() + " " + getDistance();
    }

    @Override
    public int compareTo(RouteDuration other) {
        if (getDistance() > other.getDistance())
            return 1;
        else if (getDistance() < other.getDistance())
            return -1;
        return 0;
    }

}
