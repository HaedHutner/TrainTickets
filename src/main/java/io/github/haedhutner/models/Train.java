package io.github.haedhutner.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Train implements Serializable {

    private int id;

    private List<Line> route;

    private LocalDateTime departs;

    public Train() {
        this.route = new ArrayList<>();
    }

    public Train ( int id, List<Line> route, LocalDateTime departs ) {
        this.id = id;
        this.route = route;
        this.departs = departs;
    }

    public List<Line> getRoute() {
        return route;
    }

    public LocalDateTime getDepartureTime() {
        return departs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoute(List<Line> route) {
        this.route = route;
    }

    public void setDepartureTime(LocalDateTime departs) {
        this.departs = departs;
    }
}
