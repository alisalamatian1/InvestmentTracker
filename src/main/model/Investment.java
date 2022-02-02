package model;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

// In this class users actually do the investment
public class Investment {
    private String actionType;
    private Scanner scanner = new Scanner(System.in);
    private String typeOfInvestment;
    private List<Stock> balancedStocks;
    private PurchasedStock purchasedStock;
    private StocksInWallet stocksInWallet = new StocksInWallet();

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
        System.out.println("Do you want to buy(B), sell(S), or quit(Q)?");
        String answer = scanner.next();
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
        System.out.println("What is the ticker of the stock you buying?");
        Stock purchasingStock = new Stock(scanner.next());
        System.out.println("How many shares you are buying");
        int numberOfShares = scanner.nextInt();
        System.out.println("At what price you are buying? (please check https://finance.yahoo.com for live prices)");
        double price = scanner.nextDouble();
        purchasedStock = new PurchasedStock(purchasingStock, numberOfShares, price);
        stocksInWallet.addPurchasedStock(purchasedStock);
        showTheWalletContent();
    }

    private void showTheWalletContent() {
        ArrayList<PurchasedStock> stocks = stocksInWallet.getStocks();
        for (PurchasedStock stock: stocks) {
            System.out.println("You have " + stock.getNumber() + "of " + stock.getStock().getTicker());
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
