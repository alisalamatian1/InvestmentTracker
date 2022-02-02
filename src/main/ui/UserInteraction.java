package ui;

import model.PurchasedStock;
import model.Stock;
import java.util.Scanner;


public class UserInteraction {
    private Scanner scanner = new Scanner(System.in);

    public UserInteraction() {

    }

    public String questionnaire() {
        System.out.println("how much risk you are willing to take?");
        System.out.println("Remember that ususally the higher the risk the better chance of bigger reward...");
        System.out.println("\t 1) very little");
        System.out.println("\t 2) moderate");
        System.out.println("\t 3) risky");
        return scanner.next();
    }

    public PurchasedStock buyingStocksMenu() {
        System.out.println("What is the ticker of the stock you buying?");
        Stock purchasingStock = new Stock(scanner.next());
        System.out.println("How many shares you are buying?");
        int numberOfShares = scanner.nextInt();
        System.out.println("At what price you are buying? (please check https://finance.yahoo.com for live prices)");
        double price = scanner.nextDouble();
        return new PurchasedStock(purchasingStock, numberOfShares, price);
    }

    public String showActionType() {
        System.out.println("Do you want to buy(B), sell(S), or quit(Q)?");
        return scanner.next();
    }

    public String askUserName() {
        System.out.println("What is your userName?");
        return scanner.next();
    }
    public String askUserPassword() {
        System.out.println("What is your password (at least 8 characters or digits)?");
        return scanner.next();
    }
}
