package ui;

import javax.swing.*;

public class StockSuggestionPanel extends JPanel {
    public StockSuggestionPanel() {
        initialize();
    }

    private void initialize() {
        JLabel listOfStocks = new JLabel("stocks");
        this.add(listOfStocks);
    }
}
