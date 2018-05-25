package io.github.haedhutner.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Train implements Entity<Integer>, Serializable {

    private Integer id;

    private List<Line> route = new ArrayList<>();

    private LocalDateTime departs;

    public Train() {
        this.route = new ArrayList<>();
    }

    public Train ( int id, List<Line> route, LocalDateTime departs ) {
        this.id = id;
        this.route = route;
        this.departs = departs;
    }

    public Train(int id, LocalDateTime departure) {
        this.id = id;
        this.departs = departure;
    }

    public List<Line> getRoute() {
        return route;
    }

    public LocalDateTime getDepartureTime() {
        return departs;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public void setRoute(List<Line> route) {
        this.route = route;
    }

    public void setDepartureTime(LocalDateTime departs) {
        this.departs = departs;
    }
}
