package io.github.haedhutner.entity;

import java.time.LocalDateTime;

public class Train implements Entity<Integer> {

    private Integer id;

    private Line route;

    private LocalDateTime departs;

    public Train() {
    }

    public Train(int id, Line route) {
        this.id = id;
        this.route = route;
        this.departs = LocalDateTime.now();
    }

    public Train(int id, Line route, LocalDateTime departs) {
        this.id = id;
        this.route = route;
        this.departs = departs;
    }

    public Train(int id, LocalDateTime departure) {
        this.id = id;
        this.departs = departure;
    }

    public Line getRoute() {
        return route;
    }

    public void setRoute(Line route) {
        this.route = route;
    }

    public LocalDateTime getDepartureTime() {
        return departs;
    }

    public void setDepartureTime(LocalDateTime departs) {
        this.departs = departs;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
