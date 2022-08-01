package model;


import model.exceptions.NegativeShareSellingException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


// This class provides functionality to buy and sell stocks
public class Investment extends Subject {
    private PurchasedStock purchasedStock;
    private StocksInWallet stocksInWallet = new StocksInWallet();
    private ArrayList<PurchasedStock> needToRemove = new ArrayList<>();
    private double profit = 0.0;
    private double sellingPrice = 0.0;

    private HashMap<String, List<PurchasedStock>> tickerToListPurchasedStock;
    private HashMap<String, Integer> tickerToNumberOfShares;

    // EFFECT: constructing an Investment object with the given typeOfInvestment and calling a method to show actions
    public Investment() {
        tickerToListPurchasedStock = new HashMap<>();
        tickerToNumberOfShares = new HashMap<>();
    }

    // MODIFIES: this
    // EFFECT: Selling the chosen n stocks by removing the first n of the particular stock from the user's wallet and
    // reporting the profit; if not enough funding giving them an error
    public boolean sellingStocks(String tickerSoldStock, int numberOfToSellShares, double sellingPrice)
            throws NegativeShareSellingException {
//        if (numberOfToSellShares <= 0) {
//            EventLog.getInstance().logEvent(new Event("unsuccessful attempt to sell " + numberOfToSellShares
//                    + " " + tickerSoldStock + " with the price of " + sellingPrice));
//            throw new NegativeShareSellingException();
//        }
//        this.sellingPrice = sellingPrice;
//        boolean isSellPossible = findingTheStockInWalletAndReducing(numberOfToSellShares, tickerSoldStock);
//        if (isSellPossible) {
//            EventLog.getInstance().logEvent(new Event("selling " + numberOfToSellShares + " shares of the "
//                    + tickerSoldStock + " stock with the price of " + sellingPrice));
//            notifyAllObserver();
//        } else {
//            EventLog.getInstance().logEvent(new Event("unsuccessful attempt to sell " + numberOfToSellShares
//                    + " " + tickerSoldStock + " with the price of " + sellingPrice));
//        }
//        return isSellPossible;
        this.sellingPrice = sellingPrice;
        if (numberOfToSellShares < 0) {
            throw (new NegativeShareSellingException());
        }
        initializeMaps();
        if (!tickerToNumberOfShares.containsKey(tickerSoldStock)) {
            EventLog.getInstance().logEvent(new Event("unsuccessful attempt to sell " + numberOfToSellShares
                    + " " + tickerSoldStock + " with the price of " + sellingPrice + "due to not owning any of that stock"));
            return false;
        } else if(tickerToNumberOfShares.get(tickerSoldStock) < numberOfToSellShares) {
            EventLog.getInstance().logEvent(new Event("unsuccessful attempt to sell " + numberOfToSellShares
                    + " " + tickerSoldStock + " with the price of " + sellingPrice + "due to not having enough of that stock" + "and selling shares #: " + numberOfToSellShares));
            return false;
        } else {
            List<PurchasedStock> temp = tickerToListPurchasedStock.get(tickerSoldStock);
            int remaining = -1;
            for (PurchasedStock ps : temp) {
                int number = ps.getNumber();
                if (remaining == 0) {
                    break;
                }
                if (number < numberOfToSellShares) {
                    ps.decreasingTheNumberOfShares(number);
                    remaining = numberOfToSellShares - number;
                    numberOfToSellShares -= number;
                    calculateProfit(ps.getPrice(), number);
                    EventLog.getInstance().logEvent(new Event("sold " + number + " of shares of " + tickerSoldStock + " and the PurchasedStock " + ps.toString()));
                    stocksInWallet.getStocks().remove(ps);
                } else {
                    ps.decreasingTheNumberOfShares(numberOfToSellShares);
                    remaining = 0;
                    calculateProfit(ps.getPrice(), numberOfToSellShares);
                }
            }
            EventLog.getInstance().logEvent(new Event("selling " + numberOfToSellShares + " shares of the "
                    + tickerSoldStock + " stock with the price of " + sellingPrice));
            notifyAllObserver();
            return true;
        }
    }

    private void initializeMaps() {
        List<PurchasedStock> purchasedStockList = stocksInWallet.getStocks();
        for (PurchasedStock ps : purchasedStockList) {
            String ticker = ps.getStock().getTicker();
            List<PurchasedStock> temp = tickerToListPurchasedStock.getOrDefault(ticker, new ArrayList<>());
            temp.add(ps);
            tickerToListPurchasedStock.put(ticker, temp);
            tickerToNumberOfShares.put(ticker, tickerToNumberOfShares.getOrDefault(ticker, 0) + ps.getNumber());
        }
    }

    // EFFECT: finding the stocks in the wallet and reducing the number of owned shares by the wanted amount
    private boolean findingTheStockInWalletAndReducing(int numberOfToSellShares, String tickerSoldStock) {
        boolean enoughFunding = true;
        int counter = 0;
        int totalRequestedSharesInWallet = 0;
        ArrayList<Integer> indexes = new ArrayList<>();

        for (PurchasedStock stockInWallet : stocksInWallet.getStocks()) {
            if (stockInWallet.getStock().getTicker().equalsIgnoreCase(tickerSoldStock)) {
                if (stockInWallet.getNumber() > numberOfToSellShares) {
                    stockInWallet.decreasingTheNumberOfShares(numberOfToSellShares);
                    calculateProfit(stockInWallet.getPrice(), numberOfToSellShares);
                    enoughFunding = false;
                    break;
                } else {
                    totalRequestedSharesInWallet += stockInWallet.getNumber();
                    indexes.add(counter);
                }
            }
            counter++;
        }
        if (enoughFunding) {
            return checkingForEnoughFundingAndActingAccordingly(totalRequestedSharesInWallet,
                    numberOfToSellShares, indexes);
        }
        return true;
    }

    // EFFECT: checking if the user has enough of the stock that they are selling in their account
    // reducing the number if they had and returning true, returning false otherwise
    private boolean checkingForEnoughFundingAndActingAccordingly(int totalRequestedSharesInWallet,
                                                             int numberOfToSellShares, ArrayList<Integer> indexes) {

        if (totalRequestedSharesInWallet < numberOfToSellShares) {
            return false;
        } else {
            reducingTheSellingSharesFromDifferentIndexes(indexes, numberOfToSellShares);
        }
        return true;
    }

    // MODIFIES: this
    // EFFECT: going through the indexes that have the wanted stock and removing them
    // till we reach the numberOfToSell shares
    private void reducingTheSellingSharesFromDifferentIndexes(ArrayList<Integer> indexes, int numberOfToSellShares) {
        int indexThatProvidesTheLastFunding = -1;
        for (int index : indexes) {
            PurchasedStock currentlyChecking = stocksInWallet.getStocks().get(index);
            if (currentlyChecking.getNumber() < numberOfToSellShares) {
                numberOfToSellShares -= currentlyChecking.getNumber();
                needToRemove.add(new PurchasedStock(stocksInWallet.getStocks().get(index).getStock(),
                        stocksInWallet.getStocks().get(index).getNumber(),
                        stocksInWallet.getStocks().get(index).getPrice()));
            } else if (currentlyChecking.getNumber() == numberOfToSellShares) {
                numberOfToSellShares -= currentlyChecking.getNumber();
            } else {
                currentlyChecking.decreasingTheNumberOfShares(numberOfToSellShares);
                calculateProfit(currentlyChecking.getPrice(), numberOfToSellShares);
                indexThatProvidesTheLastFunding = index;
                break;
            }
        }
        removingSoldStocksFromTheWallet(indexes, indexThatProvidesTheLastFunding);
    }

    // MODIFIES: this
    // EFFECT: removing the sold shares from stockInWallet
    private void removingSoldStocksFromTheWallet(ArrayList<Integer> indexes, int indexThatProvidesTheLastFunding) {
        int counter = 0;
        for (int index : indexes) {
            if (index == indexThatProvidesTheLastFunding) {
                break;
            }
            calculateProfit(stocksInWallet.getStocks().get(index - counter).getPrice(),
                    stocksInWallet.getStocks().get(index - counter).getNumber());
            stocksInWallet.getStocks().remove((index - counter));
            counter++;
        }
    }

    // REQUIRES: number > 0, price > 0
    // MODIFIES: this
    // EFFECT: adding the wanted stock, including its information about number of shares and price to user wallet
    // and returning the modified stocks in Wallet
    public StocksInWallet buyingStocks(Stock stock, int number, double price) {
        purchasedStock = new PurchasedStock(stock,number,price);
        stocksInWallet.addPurchasedStock(purchasedStock);
        EventLog.getInstance().logEvent(new Event("buying " + number + " shares of the " + stock.getTicker()
                                                            + " stock with the price of " + price));
        notifyAllObserver();
        return stocksInWallet;
    }

    // MODIFIES: this
    // EFFECTS: calculating profit based on the selling and bought price and number of sold shares
    private void calculateProfit(double boughtPrice, int numberOfSoldShares) {
        profit += numberOfSoldShares * (sellingPrice - boughtPrice);
    }

    public StocksInWallet getStocksInWallet() {
        return stocksInWallet;
    }

    public void setStocksInWallet(StocksInWallet stocksInWallet) {
        this.stocksInWallet = stocksInWallet;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
}
