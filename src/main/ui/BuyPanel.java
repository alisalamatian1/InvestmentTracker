package ui;

import model.Stock;

import javax.swing.*;

public class BuyPanel extends TradePanel {
    JButton buyButton;

    public BuyPanel() {
        super();
        makeButton();
    }

    @Override
    public void makeButton() {
        buyButton = new JButton("BUY");
        this.add(buyButton);
    }

    public JButton getBuyButton() {
        return buyButton;
    }
}
