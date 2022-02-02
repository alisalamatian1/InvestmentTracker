package model;

import java.util.ArrayList;

public class StocksInWallet {
    private ArrayList stocks;

    public StocksInWallet() {
        this.stocks = new ArrayList<PurchasedStock>();
    }

    public ArrayList getStocks() {
        return stocks;
    }

    // MODIFIES: this
    // EFFECT: adds a stock to the wallet
    public void addPurchasedStock(PurchasedStock stock) {
        stocks.add(stock);
    }
}
