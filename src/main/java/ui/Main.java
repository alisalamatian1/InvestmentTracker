package ui;

import dao.DbConnector;

// Main class
public class Main {
//    public static void main(String[] args) {
//        new Navigator();
//    }
    public static void main(String[] args) {
        DbConnector db = new DbConnector();
        db.setConnection();
        String stock = db.select("Ali", "123");
        System.out.println(stock);
    }

}
