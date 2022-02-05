package model;

public class PurchasedStock {
    private Stock stock;
    private int number;
    private double price;

    // EFFECT: constructing a purchasedStock with the given stock, number of shares, and the price
    public PurchasedStock(Stock stock, int number, double price) {
        this.stock = stock;
        this.number = number;
        this.price = price;
    }

    // REQUIRES: number > amount, amount > 0
    // MODIFIES: this
    // EFFECT: decreasing the number of shares by the given amount
    public void decreasingTheNumberOfShares(int amount) {
        number -= amount;
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

    public void setNumber(int number) {
        this.number = number;
    }
}
