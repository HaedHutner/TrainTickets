package io.github.haedhutner.models;

import java.io.Serializable;

public class Ticket implements Serializable {

    private static final double PRICE_MODIFIER = 0.005d;

    private int id;

    private Train train;

    private double price;

    public Ticket() {

    }

    public Ticket ( Train train ) {
        this.train = train;
        this.price = calcPrice();
    }

    private double calcPrice() {
        double price = 0.0d;
        for ( Line line : train.getRoute() ) {
            price += line.getDistance();
        }
        return price * PRICE_MODIFIER;
    }

    public double getPrice() {
        return price;
    }

    public Train getTrain() {
        return train;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
