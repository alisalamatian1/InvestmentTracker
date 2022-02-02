package model;

public class Stock {
    private String name;
    private String ticker;

    public Stock(String name, String ticker) {
        this.name = name;
        this.ticker = ticker;
    }

    public Stock(String ticker) {

    }

    public String getName() {
        return name;
    }

    public String getTicker() {
        return ticker;
    }
}
