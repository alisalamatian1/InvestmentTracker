package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TradePanel extends JPanel {
    private final JComboBox cb;
    private final CardLayout cl;
    private final JPanel mainPanel;
    private JPanel sellPanel;
    private JTextField tickerText;
    private JTextField numSharesText;
    private JTextField priceText;
    private JButton sellButton;

    public TradePanel() {
        mainPanel = new JPanel();
        cl = new CardLayout();
        mainPanel.setLayout(cl);
        String[] comboBoxItems = {"buy", "sell"};
        cb = new JComboBox(comboBoxItems);
        mainPanel.add(cb, "sellOrBuy");
        this.setLayout(cl);
        makeSellAndBuyOption();
        cl.show(mainPanel, "sellOrBuy");
        makeSellPanel();
        mainPanel.add(sellPanel, "sell");
    }

    private void makeSellPanel() {
        sellPanel = new JPanel();
        JLabel ticker = new JLabel("Ticker");
        ticker.setBounds(10, 20, 80, 25);
        sellPanel.add(ticker);
        tickerText = new JTextField(20);
        tickerText.setBounds(100, 20, 165, 25);
        sellPanel.add(tickerText);
        JLabel shares = new JLabel("Number of shares");
        shares.setBounds(10, 50, 80, 25);
        sellPanel.add(shares);
        numSharesText = new JTextField(20);
        numSharesText.setBounds(100, 20, 165, 25);
        sellPanel.add(numSharesText);
        // adding price
        JLabel price = new JLabel("Price: ");
        price.setBounds(10, 80, 80, 25);
        sellPanel.add(price);
        priceText = new JTextField(20);
        priceText.setBounds(100, 20, 165, 25);
        sellPanel.add(priceText);
        sellButton = new JButton("SELL");
    }

    private void makeSellAndBuyOption() {
        cb.setEditable(false);
        cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand());
                if (e.getActionCommand().equals("buy")) {
                    //
                } else {
                    cl.show(mainPanel, "sell");
                }
            }
        });
        add(cb);
    }

    public JButton getSellButton() {
        return sellButton;
    }

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
