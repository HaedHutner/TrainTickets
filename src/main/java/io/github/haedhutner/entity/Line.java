package io.github.haedhutner.entity;

public class Line implements Entity<Integer> {

    private Integer id;

    private String stationStart;
    private double distance;
    private String stationStop;

    public Line(int line_id) {
        this.id = line_id;
    }

    public Line(String start, double distance, String stop) {
        this.stationStart = start;
        this.distance = distance;
        this.stationStop = stop;
    }

    public Line(int id, String start, double distance, String stop) {
        this.id = id;
        this.stationStart = start;
        this.distance = distance;
        this.stationStop = stop;
    }

    @Override
    public Integer getId() {
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

    @Override
    public void setId(Integer id) {
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

    @Override
    public String toString() {
        return stationStart + " --" + distance + "--> " + stationStop;
    }
}
