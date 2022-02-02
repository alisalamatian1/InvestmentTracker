package model;

public class PurchasedStock {
    private Stock stock;
    private int number;
    private double price;

    public PurchasedStock(Stock stock, int number, double price) {
        this.stock = stock;
        this.number = 0;
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public double getPrice() {
        return price;
    }

    public Stock getStock() {
        return stock;
    }
}
