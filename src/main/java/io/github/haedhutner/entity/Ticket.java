package io.github.haedhutner.entity;

public class Ticket implements Entity<Integer> {

    private static final double PRICE_MODIFIER = 0.005d;

    private Integer id;

    private Train train;

    private double price;

    public Ticket() {

    }

    public Ticket(Train train) {
        this.train = train;
        this.price = calcPrice();
    }

    private double calcPrice() {
        double price = 0.0d;
        price += train.getRoute().getDistance();
        return price * PRICE_MODIFIER;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public Train getTrain() {
        return train;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
