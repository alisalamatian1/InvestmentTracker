package model;

public class Stock {
    private String name;
    private String ticker;

    // EFFECT: constructing a stock with the given name, and ticker
    public Stock(String name, String ticker) {
        this.name = name;
        this.ticker = ticker;
    }

    // EFFECT: constructing a stock with only the ticker
    public Stock(String ticker) {
        this.ticker = ticker;
    }


    public String getName() {
        return name;
    }

    public String getTicker() {
        return ticker;
    }
}
