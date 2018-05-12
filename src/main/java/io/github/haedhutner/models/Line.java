package io.github.haedhutner.models;

import java.io.Serializable;

public class Line implements Serializable {

    private int id;

    private String stationStart;
    private double distance;
    private String stationStop;

    public Line() {
    }

    public Line ( String start, double distance, String stop ) {
        this.stationStart = start;
        this.distance = distance;
        this.stationStop = stop;
    }

    public int getId() {
        return id;
    }

    public String getStart() {
        return stationStart;
    }

    public double getDistance() {
        return distance;
    }

    public String getStop() {
        return stationStop;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStationStart(String stationStart) {
        this.stationStart = stationStart;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setStationStop(String stationStop) {
        this.stationStop = stationStop;
    }
}
