package ui;

import model.*;
import model.exceptions.NegativeShareSellingException;
import persistence.JsonReading;
import persistence.JsonWriting;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

// This is the class that users can input their trading requests;
// it makes instances of classes in the method package and calls the appropriate methods
public class UserInteraction {
    private final Scanner scanner;
    private static final String JSON_STORAGE = "./data/";
    private String typeOfInvestment;
    private Investment investment;
    private List<Stock> balancedStocks;
    private List<Stock> conservativeStocks;
    private List<Stock> riskyStocks;
    private StocksInWallet stocksInWallet;
    private UserProfile userProfile;
    private UserProfileAndWallet userProfileAndWallet;
    private boolean isSignedUp = false;
    private JsonWriting jsonWriting;
    private int isSetUpCounter = 0;
    private JsonReading jsonReading;

    // EFFECT: initializing scanner, stockInWallet and userProfileAndWallet, and calling the starting page
    public UserInteraction() {
        scanner = new Scanner(System.in);
        stocksInWallet = new StocksInWallet();
        investment = new Investment();
        userProfileAndWallet = new UserProfileAndWallet(userProfile, stocksInWallet);
        startingPage();
    }

    // MODIFIES: this
    // EFFECT: printing the options to log in or sign up and call the corresponding method
    private void startingPage() {
        System.out.println("Sign up(S) or Log in(L) or Quit(q)?");
        String answer = scanner.next();
        if (answer.equalsIgnoreCase("s")) {
            createProfile();
        } else if (answer.equalsIgnoreCase("l")) {
            logIn();
        } else {
            System.out.println("Hope to see you soon!");
        }
    }

    // MODIFIES: this
    // EFFECT: ask the user to login or quit and acts correspondingly
    private void logInAgainPage() {
        System.out.println("Log in again(l) or Quit(q)?");
        String answer = scanner.next();
        if (answer.equalsIgnoreCase("l")) {
            logIn();
        } else {
            System.out.println("Hope to see you soon!");
            System.exit(0);
        }
    }

    // MODIFIES: this
    // EFFECT: asks for username and password, if founded in the system, showing their wallet content,
    // else heading back to the logInAgain or startingPage method
    private void logIn() {
        String name = askUserName();
        String password = checkPassword();
        boolean found = false;
        int counter  = 0;
        for (UserProfile key : userProfileAndWallet.getAssociatedWallet().keySet()) {
            // counter was needed because the first element in the list is null
            if (counter > 0 && key.getUserName().equals(name)) {
                if (key.getPassword().equals(password)) {
                    found = true;
                    showTheWalletContent();
                    showActionType();
                    break;
                }
            }
            counter++;
        }
        navigateUserNotFound(found);
    }

    // MODIFIES: this
    // EFFECT: handling the page to show depending on whether user is signed up or not
    private void navigateUserNotFound(boolean found) {
        if (!found) {
            System.out.println("Sorry, could not find you in our system!, try again");
            if (isSignedUp) {
                logInAgainPage();
            } else {
                startingPage();
            }
        }
    }

    // MODIFIES: this
    // EFFECT: creating a userProfile containing user's name and password
    public void createProfile() {
        String name = askUserName();
        String password = checkPassword();
        userProfile = new UserProfile(name, password);
        isSignedUp = true;
        changeUserProfileAndWallet();
        loadData();
        questionnaire();
    }

    // REQUIRES: username must be without whitespaces in between
    // EFFECT: asks for username and returns it
    public String askUserName() {
        System.out.println("What is your userName?");
        return scanner.next();
    }

    // REQUIRES: password must be without whitespaces in between
    // EFFECT: asks for password and checks if it is at least minimumPasswordLength characters (currently at 8)
    // when password is acceptable, returns it
    private String checkPassword() {
        int minimumPasswordLength = 8;
        String password = "";
        boolean correctPass = false;
        while (!correctPass) {
            password = askUsersPassword();
            if (password.length() >= minimumPasswordLength) {
                correctPass = true;
            }
        }
        return password;
    }

    // EFFECT: getting users to input their 8 digit password
    private String askUsersPassword() {
        System.out.println("What is your password (at least 8 characters or digits)?");
        return scanner.next();
    }




    // MODIFIES: this
    // EFFECT: asking the users about their risk tolerance and calling pickInvestment
    private void questionnaire() {
        System.out.println("how much risk you are willing to take?");
        System.out.println("Remember that usually the higher the risk the better chance of bigger reward...");
        System.out.println("\t 1) very little");
        System.out.println("\t 2) moderate");
        System.out.println("\t 3) risky");
        pickInvestment(scanner.next());
    }

    // MODIFIES: this
    // EFFECT: assigns the type of investment and calls createInvestment
    private void pickInvestment(String choiceNumber) {
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

    // MODIFIES: this
    // EFFECT: create an investment instance and calls showActionType
    public void createInvestment() {
        System.out.print("You should do a " + typeOfInvestment);
        System.out.println(" investment, that is what our algorithms suggest for you!");
        showActionType();
    }

    // MODIFIES: this
    // EFFECTS: gets users to choose their action and acts accordingly
    public void showActionType() {
        System.out.println("Do you want to buy(B), sell(S), or quit(Q)?");
        String answer = scanner.next();
        if (answer.equalsIgnoreCase("b")) {
            showDescription();
            buyingStocks();
        } else if (answer.equalsIgnoreCase("s")) {
            sellingStock();
        } else {
            System.out.println("do you want to save your data?(y/n)");
            if (scanner.next().equals("y")) {
                try {
                    saveUserInfo();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("It was nice serving you, see you soon! " + userProfile.getUserName());
            System.out.println("Your wallet content for a last look :)");
            showTheWalletContent();
            logInAgainPage();
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
                break;
            default:
                balancedStocksList();
        }
    }

    // MODIFIES: this
    // EFFECTS: showing a list of risky stocks
    public void riskyList() {
        riskyStocks = new ArrayList<Stock>();
        riskyStocks.add(new Stock("ProShares Ultra S&P500", "SSO"));
        riskyStocks.add(new Stock("ProShares UltraPro QQQ", "TQQQ"));
        for (Stock stock : riskyStocks) {
            System.out.print(stock.getName() + ", ");
            System.out.println("with the ticker: " + stock.getTicker());
        }
    }

    // MODIFIES: this
    // EFFECTS: showing a list of conservative stocks
    public void conservativeList() {
        conservativeStocks = new ArrayList<Stock>();
        conservativeStocks.add(new Stock("Vanguard Conservative ETF Portfolio", "VCNS"));
        conservativeStocks.add(new Stock("iShares Core Conservative Balanced ETF Portfolio", "XCNS"));
        for (Stock stock : conservativeStocks) {
            System.out.print(stock.getName() + ", ");
            System.out.println("with the ticker: " + stock.getTicker());
        }
    }

    // MODIFIES: this
    // EFFECTS: showing a list of balanced stocks
    public void balancedStocksList() {
        balancedStocks = new ArrayList<Stock>();
        balancedStocks.add(new Stock("Vanguard S&P 500 Index ETF", "VFV"));
        balancedStocks.add(new Stock("Vanguard Balanced ETF Portfolio", "VBAL"));
        for (Stock stock : balancedStocks) {
            System.out.print(stock.getName() + ", ");
            System.out.println("with the ticker: " + stock.getTicker());
        }
    }

    // MODIFY: this
    // EFFECT: adding the wanted stock to the stocksInWallet and updating userProfileAndWallet field accordingly
    public void buyingStocks() {
        System.out.println("What is the ticker of the stock you buying?");
        Stock purchasingStock = new Stock(scanner.next());
        System.out.println("How many shares you are buying?");
        int numberOfShares = scanner.nextInt();
        System.out.println("At what price you are buying? (please check https://finance.yahoo.com for live prices)");
        double price = scanner.nextDouble();
        investment.setStocksInWallet(stocksInWallet);
        stocksInWallet = investment.buyingStocks(purchasingStock, numberOfShares, price);
        showTheWalletContent();
        changeUserProfileAndWallet();
        showActionType();
    }

    // MODIFY: this
    // EFFECT: selling the wanted stock, removing it from stocksInWallet
    // and updating userProfileAndWallet field accordingly
    public void sellingStock() {
        System.out.println("What stock are you selling? (Enter the ticker)");
        String ticker = scanner.next();
        System.out.println("How many shares are you selling?");
        int numberOfShares = scanner.nextInt();
        System.out.println("At what price you are selling? (please check https://finance.yahoo.com for live prices)");
        double price = scanner.nextDouble();
        Boolean wasSuccessful = null;
        try {
            wasSuccessful = investment.sellingStocks(ticker, numberOfShares);
        } catch (NegativeShareSellingException e) {
            System.out.println("please enter positive number of shares only.");
            sellingStock();
        }
        if (!wasSuccessful) {
            System.out.println("Insufficient funding! Please look at the listed stocks you have and try again!");
            showTheWalletContent();
            sellingStock();
        }
        showTheWalletContent();
        changeUserProfileAndWallet();
        showActionType();
    }

    // EFFECT: showing the wallet's content to the user
    public void showTheWalletContent() {
        List<PurchasedStock> stocks = stocksInWallet.getStocks();
        for (PurchasedStock stock : stocks) {
            System.out.println("You have " + stock.getNumber() + " shares of " + stock.getStock().getTicker());
            System.out.println("with the value of: " + stock.getPrice() * stock.getNumber());
        }
    }

    // MODIFIES: this
    // EFFECT: changing userProfileAndWallet when user changes their StocksInWallet
    public void changeUserProfileAndWallet() {
        userProfileAndWallet.addAssociatedWallets(userProfile, stocksInWallet);
    }

    // EFFECT: saving the userProfileAndWallet to the file
    public void saveUserInfo() throws FileNotFoundException {
        jsonWriting = new JsonWriting(JSON_STORAGE + "/" + userProfile.getUserName() + ".json");
        jsonWriting.open();
        jsonWriting.write(userProfileAndWallet);
        jsonWriting.close();
    }

    //MODIFY: this
    //EFFECTS: reading the stored file
    public void loadData() {
        System.out.println("Do you want to load your data?(y/n)");
        if (scanner.next().equals("y")) {
            jsonReading = new JsonReading(JSON_STORAGE + "/" + userProfile.getUserName() + ".json");
            try {
                userProfileAndWallet = jsonReading.read();
                stocksInWallet = userProfileAndWallet.getWallet();
                investment.setStocksInWallet(stocksInWallet);
                showTheWalletContent();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
