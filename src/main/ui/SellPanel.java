package ui;

import javax.swing.*;

public class SellPanel extends TradePanel {
    protected JTextField tickerText;
    protected JTextField numSharesText;
    protected JTextField priceText;
    protected JButton sellButton;

    public SellPanel() {
        super();
        makeButton();
    }

    @Override
    public void makeButton() {
        sellButton = new JButton("SELL");
        this.add(sellButton);
    }

    public JButton getSellButton() {
        return sellButton;
    }
}
