package model;

import java.util.ArrayList;

// This class represents the user's wallet and is for holding all the purchased stocks in one place
public class StocksInWallet {
    private ArrayList stocks;

    // EFFECT: constructing a list of purchasedStocks and initializing stocks as a new ArrayList
    public StocksInWallet() {
        this.stocks = new ArrayList<PurchasedStock>();
    }

    // MODIFIES: this
    // EFFECT: adds a stock to the wallet
    public void addPurchasedStock(PurchasedStock stock) {
        stocks.add(stock);
    }

    public ArrayList<PurchasedStock> getStocks() {
        return stocks;
    }
}
