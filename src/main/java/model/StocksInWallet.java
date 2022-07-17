package model;

import java.util.Comparator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// This class represents the user's wallet and is for holding all the purchased stocks in one place
public class StocksInWallet {
    private List<PurchasedStock> stocks;

    // EFFECT: constructing a list of purchasedStocks and initializing stocks as a new ArrayList
    public StocksInWallet() {
        this.stocks = new ArrayList<PurchasedStock>();
    }

    // MODIFIES: this
    // EFFECT: adds a stock to the wallet
    public void addPurchasedStock(PurchasedStock stock) {
        stocks.add(stock);
    }

    public List<PurchasedStock> getStocks() {
        return stocks;
    }

    // EFFECTS: making a JSONObject of StocksInWallet
    public JSONArray toJson() {
        JSONArray json = new JSONArray();
        for (PurchasedStock purchasedStock : stocks) {
            json.put(purchasedStock.toJson());
        }
        return json;
    }
}
