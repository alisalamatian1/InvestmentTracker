package ui;

import javax.swing.*;

public abstract class TradePanel extends JPanel {
    protected JTextField tickerText;
    protected JTextField numSharesText;
    protected JTextField priceText;

    public TradePanel() {
        makeTextFields();
    }

    public void makeTextFields() {
        JLabel ticker = new JLabel("Ticker");
        ticker.setBounds(10, 20, 80, 25);
        this.add(ticker);
        tickerText = new JTextField(20);
        tickerText.setBounds(100, 20, 165, 25);
        this.add(tickerText);
        JLabel shares = new JLabel("Number of shares");
        shares.setBounds(10, 50, 80, 25);
        this.add(shares);
        numSharesText = new JTextField(20);
        numSharesText.setBounds(100, 20, 165, 25);
        this.add(numSharesText);
        // adding price
        JLabel price = new JLabel("Price: ");
        price.setBounds(10, 80, 80, 25);
        this.add(price);
        priceText = new JTextField(20);
        priceText.setBounds(100, 20, 165, 25);
        this.add(priceText);
    }

    public abstract void makeButton();

    public JTextField getTicker() {
        return tickerText;
    }

    public JTextField getNumSharesText() {
        return numSharesText;
    }

    public JTextField getPriceText() {
        return priceText;
    }
}
