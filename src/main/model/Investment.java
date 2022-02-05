package model;


import java.util.ArrayList;


// In this class users actually do the investment
public class Investment {
    private PurchasedStock purchasedStock;
    private StocksInWallet stocksInWallet = new StocksInWallet();
    private ArrayList<PurchasedStock> needToRemove = new ArrayList<>();

    // EFFECT: constructing an Investment object with the given typeOfInvestment and calling a method to show actions
    public Investment() {
    }

    // MODIFIES: StocksInWallet
    // EFFECT: Selling the chosen n stocks by removing the first n of the particular stock from the user's wallet and
    // reporting the profit; if not enough funding giving them an error
    public Boolean sellingStocks(Stock stock, int numberOfToSellShares) {
        String tickerSoldStock = stock.getTicker();

        return findingTheStockInWalletAndReducing(numberOfToSellShares, tickerSoldStock);
    }

    private Boolean findingTheStockInWalletAndReducing(int numberOfToSellShares, String tickerSoldStock) {
        Boolean enoughFunding = true;
        int counter = 0;
        int totalRequestedSharesInWallet = 0;
        ArrayList<Integer> indexes = new ArrayList<>();

        for (PurchasedStock stockInWallet : stocksInWallet.getStocks()) {
            if (stockInWallet.getStock().getTicker().equalsIgnoreCase(tickerSoldStock)) {
                if (stockInWallet.getNumber() > numberOfToSellShares) {
                    stockInWallet.decreasingTheNumberOfShares(numberOfToSellShares);
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

    private Boolean checkingForEnoughFundingAndActingAccordingly(int totalRequestedSharesInWallet,
                                                             int numberOfToSellShares, ArrayList<Integer> indexes) {

        if (totalRequestedSharesInWallet < numberOfToSellShares) {
            return false;
        } else {
            reducingTheSellingSharesFromDifferentIndexes(indexes, numberOfToSellShares);
        }
        return true;
    }

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
                indexThatProvidesTheLastFunding = index;
                break;
            }
        }
        removingSoldStocksFromTheWallet(indexes, indexThatProvidesTheLastFunding);
    }

    private void removingSoldStocksFromTheWallet(ArrayList<Integer> indexes, int indexThatProvidesTheLastFunding) {
        int counter = 0;
        for (int index : indexes) {
            if (index == indexThatProvidesTheLastFunding) {
                break;
            }
            stocksInWallet.getStocks().remove((index - counter));
            counter++;
        }
    }


    // MODIFIES: StocksInWallet
    // EFFECT: adding the wanted stock to their wallet
    public StocksInWallet buyingStocks(Stock stock, int number, double price) {
        purchasedStock = new PurchasedStock(stock,number,price);
        stocksInWallet.addPurchasedStock(purchasedStock);
        return stocksInWallet;
    }

    public StocksInWallet getStocksInWallet() {
        return stocksInWallet;
    }
}
