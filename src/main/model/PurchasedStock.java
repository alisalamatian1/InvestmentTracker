package model;

import org.json.JSONObject;

// This class has the information about the purchased stocks
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

    // REQUIRES: number >= amount, amount > 0
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

    // EFFECTS: making a JSONObject of PurchasedStock
    public JSONObject  toJson() {
        JSONObject json = new JSONObject();
        json.put("Stock", stock.toJson());
        json.put("number", number);
        json.put("price", price);
        return json;
    }

    @Override
    public String toString() {
        return "PurchasedStock{" +
                "stock=" + stock +
                ", number=" + number +
                ", price=" + price +
                '}';
    }
}
