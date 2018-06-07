package io.github.haedhutner.entity;

public class Line implements Entity<Integer> {

    private Integer id;

    private String from;
    private double distance;
    private String to;

    public Line(int line_id) {
        this.id = line_id;
    }

    public Line(String start, double distance, String stop) {
        this.from = start;
        this.distance = distance;
        this.to = stop;
    }

    public Line(int id, String start, double distance, String stop) {
        this.id = id;
        this.from = start;
        this.distance = distance;
        this.to = stop;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getStart() {
        return from;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getStop() {
        return to;
    }

    public void setStationStart(String stationStart) {
        this.from = stationStart;
    }

    public void setStationStop(String stationStop) {
        this.to = stationStop;
    }

    @Override
    public String toString() {
        return from + " --" + distance + "--> " + to;
    }
}
