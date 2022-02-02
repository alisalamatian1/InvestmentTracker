package ui;

import model.PurchasedStock;
import model.Stock;
import java.util.Scanner;


public class UserInteraction {
    private Scanner scanner = new Scanner(System.in);

    public UserInteraction() {

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
        String answer = scanner.next();
        return answer;
    }
}
