package ui;

import javax.swing.*;

// this class represent the buying option
public class BuyPanel extends TradePanel {
    private JButton buyButton;

    // EFFECTS: constructing a buy panel and making a buy button
    public BuyPanel() {
        super();
        makeButton();
    }

    // MODIFIES: this
    // EFFECTS: adding the buy button
    @Override
    public void makeButton() {
        buyButton = new JButton("BUY");
        this.add(buyButton);
    }

    public JButton getBuyButton() {
        return buyButton;
    }
}
