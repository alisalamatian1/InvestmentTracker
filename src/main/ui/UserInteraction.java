package ui;

import model.Investment;
import model.PurchasedStock;
import model.Stock;
import model.StocksInWallet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class UserInteraction {
    private Scanner scanner = new Scanner(System.in);
    private String typeOfInvestment;
    private Investment investment;
    private List<Stock> balancedStocks;
    private List<Stock> conservativeStocks;
    private List<Stock> riskyStocks;
    private StocksInWallet stocksInWallet = new StocksInWallet();

    public UserInteraction() {
        questionnaire();
    }

    public void questionnaire() {
        System.out.println("how much risk you are willing to take?");
        System.out.println("Remember that ususally the higher the risk the better chance of bigger reward...");
        System.out.println("\t 1) very little");
        System.out.println("\t 2) moderate");
        System.out.println("\t 3) risky");
        pickInvestment(scanner.next());
    }

    public void pickInvestment(String choiceNumber) {
        switch (choiceNumber) {
            case "1":
                typeOfInvestment = "conservative";
                break;
            case "2":
                typeOfInvestment = "balanced";
                break;
            case "3":
                typeOfInvestment = "risky";
                break;
            default:
                System.out.println("Wrong choice! Please pick again from the given options.");
                questionnaire();
        }
        createInvestment();
    }

    public void createInvestment() {
        System.out.print("let's check out a " + typeOfInvestment);
        System.out.println(" investment, that is what our algorithms suggest for you!");
        investment = new Investment();
        showActionType();
    }

    public void showActionType() {
        System.out.println("Do you want to buy(B), sell(S), or quit(Q)?");
        String answer = scanner.next();
        if (answer.equalsIgnoreCase("b")) {
            showDescription();
            buyingStocksMenu();
        } else if (answer.equalsIgnoreCase("s")) {
            sellingStockMenu();
        } else {
            System.out.println("It was nice serving you, see you soon!");
            System.out.println("Your wallet content for a last look :)");
            showTheWalletContent();
        }
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
        for (Stock stock : riskyStocks) {
            System.out.print(stock.getName() + ", ");
            System.out.println("with the ticker: " + stock.getTicker());
        }
    }

    public void conservativeList() {
        conservativeStocks = new ArrayList<Stock>();
        conservativeStocks.add(new Stock("Vanguard Conservative ETF Portfolio", "VCNS"));
        conservativeStocks.add(new Stock("iShares Core Conservative Balanced ETF Portfolio", "XCNS"));
        for (Stock stock : conservativeStocks) {
            System.out.print(stock.getName() + ", ");
            System.out.println("with the ticker: " + stock.getTicker());
        }
    }

    public void balancedStocksList() {
        balancedStocks = new ArrayList<Stock>();
        balancedStocks.add(new Stock("Vanguard S&P 500 Index ETF", "VFV"));
        balancedStocks.add(new Stock("Vanguard Balanced ETF Portfolio", "VBAL"));

        for (Stock stock : balancedStocks) {
            System.out.print(stock.getName() + ", ");
            System.out.println("with the ticker: " + stock.getTicker());
        }
    }

    public void buyingStocksMenu() {
        System.out.println("What is the ticker of the stock you buying?");
        Stock purchasingStock = new Stock(scanner.next());
        System.out.println("How many shares you are buying?");
        int numberOfShares = scanner.nextInt();
        System.out.println("At what price you are buying? (please check https://finance.yahoo.com for live prices)");
        double price = scanner.nextDouble();
        stocksInWallet =  investment.buyingStocks(purchasingStock, numberOfShares, price);
        showTheWalletContent();
        showActionType();
    }

    public void sellingStockMenu() {
        System.out.println("What stock are you selling? (Enter the ticker)");
        Stock sellingStock = new Stock(scanner.next());
        System.out.println("How many shares are you selling?");
        int numberOfShares = scanner.nextInt();
        System.out.println("At what price you are selling? (please check https://finance.yahoo.com for live prices)");
        double price = scanner.nextDouble();
        Boolean wasSuccessful =  investment.sellingStocks(sellingStock, numberOfShares);
        if (!wasSuccessful) {
            System.out.println("Insufficient funding! Please look at the listed stocks you have and try again!");
            showTheWalletContent();
            sellingStockMenu();
        }
        showTheWalletContent();
        showActionType();
    }


    public String askUserName() {
        System.out.println("What is your userName?");
        return scanner.next();
    }

    public String askUserPassword() {
        System.out.println("What is your password (at least 8 characters or digits)?");
        return scanner.next();
    }

    // EFFECT: showing the wallet content to the user
    public void showTheWalletContent() {
        ArrayList<PurchasedStock> stocks = stocksInWallet.getStocks();
        for (PurchasedStock stock : stocks) {
            System.out.println("You have " + stock.getNumber() + " shares of " + stock.getStock().getTicker());
            System.out.println("with the value of: " + stock.getPrice() * stock.getNumber());
        }
    }

}
