package io.github.haedhutner.entity;

public class Ticket implements Entity<Integer> {

    private Integer id;

    private Double price;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
