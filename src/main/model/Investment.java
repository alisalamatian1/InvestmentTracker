package model;

import ui.UserInteraction;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// In this class users actually do the investment
public class Investment {
    private String actionType;
    private String typeOfInvestment;
    private List<Stock> balancedStocks;
    private PurchasedStock purchasedStock;
    private StocksInWallet stocksInWallet = new StocksInWallet();
    private UserInteraction userInteraction = new UserInteraction();

    public Investment(String typeOfInvestment) {
        this.typeOfInvestment = typeOfInvestment;
        showActionType();
    }

    public void showDescription() {
        System.out.println("List of the stocks we suggest for you is: ");
        switch (typeOfInvestment) {
            default:
                balancedStocksList();
        }
    }

    // EFFECT: showing the different possible actions
    private void showActionType() {
        String answer = userInteraction.showActionType();
        if (answer.toLowerCase().equals("b")) {
            showDescription();
            buyingStocks();
        } else if (answer.toLowerCase().equals("s")) {
            sellingStocks();
        }
    }

    // MODIFIES: StocksInWallet
    // EFFECT: removing the chosen stock from their profile
    private void sellingStocks() {
    }

    // MODIFIES: StocksInWallet
    // EFFECT: adding the wanted stock to their wallet
    private void buyingStocks() {
        purchasedStock = userInteraction.buyingStocksMenu();
        stocksInWallet.addPurchasedStock(purchasedStock);
        showTheWalletContent();
    }

    private void showTheWalletContent() {
        ArrayList<PurchasedStock> stocks = stocksInWallet.getStocks();
        for (PurchasedStock stock: stocks) {
            System.out.println("You have " + stock.getNumber() + " shares of " + stock.getStock().getTicker());
            System.out.println("with the value of: " + stock.getPrice() * stock.getNumber());
        }
    }

    public void balancedStocksList() {
        balancedStocks = new ArrayList<Stock>();
        balancedStocks.add(new Stock("Vanguard S&P 500 Index ETF", "VFV"));
        balancedStocks.add(new Stock("Vanguard Balanced ETF Portfolio", "VBAL"));

        for (Stock stock: balancedStocks) {
            System.out.println(stock.getName());
        }
    }

    public void doAction(String actionType) {
        // sellDescription();
        // buyDescription();
    }
}
