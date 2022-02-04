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
    private List<Stock>  balancedStocks;
    private List<Stock> conservativeStocks;
    private List<Stock> riskyStocks;
    private PurchasedStock purchasedStock;
    private StocksInWallet stocksInWallet = new StocksInWallet();
    private UserInteraction userInteraction = new UserInteraction();

    // EFFECT: constructing an Investment object with the given typeOfInvestment and calling a method to show actions
    public Investment(String typeOfInvestment) {
        this.typeOfInvestment = typeOfInvestment;
        showActionType();
    }

    // EFFECT: showing the list of stocks according to the chosen type of investment
    public void showDescription() {
        System.out.println("List of the stocks we suggest for you is: ");
        switch (typeOfInvestment) {
            case "conservative":
                conservativeList();
                break;
            case "risky":
                riskyList();
            default:
                balancedStocksList();
        }
    }

    // Effect: a list of risky stocks
    public void riskyList() {
        riskyStocks = new ArrayList<Stock>();
        riskyStocks.add(new Stock("ProShares Ultra S&P500", "SSO"));
        riskyStocks.add(new Stock("ProShares UltraPro QQQ", "TQQQ"));
        for (Stock stock: riskyStocks) {
            System.out.print(stock.getName() + ", ");
            System.out.println("with the ticker: " + stock.getTicker());
        }
    }

    public void conservativeList() {
        conservativeStocks = new ArrayList<Stock>();
        conservativeStocks.add(new Stock("Vanguard Conservative ETF Portfolio", "VCNS"));
        conservativeStocks.add(new Stock("iShares Core Conservative Balanced ETF Portfolio", "XCNS"));
        for (Stock stock: conservativeStocks) {
            System.out.print(stock.getName() + ", ");
            System.out.println("with the ticker: " + stock.getTicker());
        }
    }

    public void balancedStocksList() {
        balancedStocks = new ArrayList<Stock>();
        balancedStocks.add(new Stock("Vanguard S&P 500 Index ETF", "VFV"));
        balancedStocks.add(new Stock("Vanguard Balanced ETF Portfolio", "VBAL"));

        for (Stock stock: balancedStocks) {
            System.out.print(stock.getName() + ", ");
            System.out.println("with the ticker: " + stock.getTicker());
        }
    }

    // EFFECT: showing the different possible actions
    public void showActionType() {
        String answer = userInteraction.showActionType();
        if (answer.toLowerCase().equals("b")) {
            showDescription();
            buyingStocks();
        } else if (answer.toLowerCase().equals("s")) {
            sellingStocks();
        } else {
            System.out.println("It was nice serving you, see you soon!");
            System.out.println("Your wallet content for a last look :)");
            showTheWalletContent();
        }
    }

    // MODIFIES: StocksInWallet
    // EFFECT: Selling the chosen n stocks by removing the first n of the particular stock from the user's wallet and
    // reporting the profit; if not enough funding giving them an error
    public void sellingStocks() {
        // todo 3
    }

    // MODIFIES: StocksInWallet
    // EFFECT: adding the wanted stock to their wallet
    public void buyingStocks() {
        purchasedStock = userInteraction.buyingStocksMenu();
        stocksInWallet.addPurchasedStock(purchasedStock);
        showTheWalletContent();
        showActionType();
    }

    // EFFECT: showing the wallet content to the user
    public void showTheWalletContent() {
        ArrayList<PurchasedStock> stocks = stocksInWallet.getStocks();
        for (PurchasedStock stock: stocks) {
            System.out.println("You have " + stock.getNumber() + " shares of " + stock.getStock().getTicker());
            System.out.println("with the value of: " + stock.getPrice() * stock.getNumber());
        }
    }


    public void doAction(String actionType) {
        // sellDescription();
        // buyDescription();
    }
}
