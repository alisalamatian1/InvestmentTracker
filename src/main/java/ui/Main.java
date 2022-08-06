package ui;

import dao.DbConnector;
import model.*;

// Main class
public class Main {
    public static void main(String[] args) {
        new Navigator();
    }
//    public static void main(String[] args) {
//        DbConnector db = new DbConnector();
//        db.setConnection();
//        String stock = db.select("Ali", "123");
//        System.out.println(stock);
//
//        StocksInWallet s = new StocksInWallet();
//        s.addPurchasedStock(new PurchasedStock(new Stock("apple"), 2, 200));
//
//        UserProfileAndWallet userProfileAndWallet = new UserProfileAndWallet(new UserProfile("Ali", "124"),
//                s);
//        String id = "Ali23";
//        DbConnector.update(userProfileAndWallet, id);
//    }

}
