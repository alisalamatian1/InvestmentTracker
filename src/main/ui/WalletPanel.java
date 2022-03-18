package ui;

import model.PurchasedStock;
import model.StocksInWallet;
import persistence.JsonReading;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class WalletPanel extends JPanel {
    StocksInWallet stocksInWallet;

    public WalletPanel(StocksInWallet stocks) {
        this.stocksInWallet = stocks;
        makeWallet();
    }

    private void makeWallet() {
        List<PurchasedStock> stocks = stocksInWallet.getStocks();
        for (PurchasedStock stock : stocks) {
            add(new JLabel("You have " + stock.getNumber() + " shares of "
                    + stock.getStock().getTicker()));
            add(new JLabel("with the value of: " + stock.getPrice() * stock.getNumber()));
        }
    }
}
