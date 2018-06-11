package io.github.haedhutner.entity;

import java.time.LocalDateTime;

public class Train implements Entity<Integer> {

    private Integer id;

    private Line route;

    private LocalDateTime departs;

    public Train() {
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

    @Override
    public String toString() {
        return String.format("[%d]%s @ %s", this.id, this.route.toString(), this.departs.toString());
    }
}
