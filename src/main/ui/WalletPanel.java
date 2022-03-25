package ui;

import model.PurchasedStock;
import model.StocksInWallet;
import javax.swing.*;
import java.awt.*;
import java.util.List;

// class representing a page showing the wallet content
// Used the following to learn JList:
//https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ListDemoProject/src/components/ListDemo.java
public class WalletPanel extends JPanel {
    private StocksInWallet stocksInWallet;
    private JList list;
    private DefaultListModel listModel;

    // EFFECTS: constructing the wallet panel based on the stocks in wallet
    public WalletPanel(StocksInWallet stocks) {
        super(new BorderLayout());
        listModel = new DefaultListModel();
        list = new JList(listModel);
        JScrollPane listScrollPane = new JScrollPane(list);
        add(listScrollPane, BorderLayout.CENTER);
        this.stocksInWallet = stocks;
    }

    // MODIFIES: this
    // EFFECTS: making a list of owned stocks
    public void makeWallet() {
        listModel.removeAllElements();
        List<PurchasedStock> stocks = stocksInWallet.getStocks();
        for (PurchasedStock stock : stocks) {
            listModel.addElement("You have " + stock.getNumber() + " shares of "
                    + stock.getStock().getTicker()
                    + " with the value of: " + stock.getPrice() * stock.getNumber());
        }

    }
}
