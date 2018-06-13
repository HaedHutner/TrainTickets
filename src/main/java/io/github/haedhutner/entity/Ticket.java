package io.github.haedhutner.entity;

public class Ticket implements Entity<Integer> {

    public static double PRICE_PER_KILOMETER = 0.05d;

    private Integer id;

    private double price;

    private Train train;

    public Ticket() {}

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer integer) {
        this.id = integer;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Train getTrain() {
        return train;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
