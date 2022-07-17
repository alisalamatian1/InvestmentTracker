package ui;

import javax.swing.*;

// class representing the sell panel
public class SellPanel extends TradePanel {
    protected JButton sellButton;

    // EFFECTS: constructing a sell panel and making a sell button
    public SellPanel() {
        super();
        makeButton();
    }

    // MODIFIES: this
    // EFFECTS: making a sell button
    @Override
    public void makeButton() {
        sellButton = new JButton("SELL");
        this.add(sellButton);
    }

    public JButton getSellButton() {
        return sellButton;
    }
}
