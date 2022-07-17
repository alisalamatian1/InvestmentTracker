package ui;

import javax.swing.*;

// abstract class for holding the commonalities between buying and selling
public abstract class TradePanel extends JPanel {
    protected JTextField tickerText;
    protected JTextField numSharesText;
    protected JTextField priceText;

    // EFFECTS: constructing a trade panel
    public TradePanel() {
        makeTextFields();
    }

    // MODIFIES: this
    // EFFECTS: making the text fields for ticker, number of shares and price
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
        JLabel price = new JLabel("Price: ");
        price.setBounds(10, 80, 80, 25);
        this.add(price);
        priceText = new JTextField(20);
        priceText.setBounds(100, 20, 165, 25);
        this.add(priceText);
    }

    // EFFECTS: making a button
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
