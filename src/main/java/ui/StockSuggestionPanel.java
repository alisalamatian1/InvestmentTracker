package ui;

import model.Stock;

import javax.swing.*;
import java.util.ArrayList;

// class representing the stock suggestion page
public class StockSuggestionPanel extends JPanel {

    // EFFECTS: constructing a stock suggestion panel
    public StockSuggestionPanel() {
        makeSuggestions();
    }

    // MODIFIES: this
    // EFFECTS: making the list of suggestions
    private void makeSuggestions() {
        JLabel suggest = new JLabel("Here are some suggestions: ");
        this.add(suggest);
        makeBalancedStocksList();
        makeConservativeList();
        makeRiskyList();
    }

    // MODIFIES: this
    // EFFECTS: making and displaying a list of balanced stocks
    private void makeBalancedStocksList() {
        ArrayList<Stock> balancedStocks = new ArrayList<Stock>();
        balancedStocks.add(new Stock("Vanguard S&P 500 Index ETF", "VFV"));
        balancedStocks.add(new Stock("Vanguard Balanced ETF Portfolio", "VBAL"));
        JLabel balanced = new JLabel("balanced: ");
        this.add(balanced);
        for (Stock stock : balancedStocks) {
            JLabel name = new JLabel(stock.getName() + " with the ticker " + stock.getTicker());
            this.add(name);
        }
    }

    // MODIFIES: this
    // EFFECTS: making a list of conservative stocks and adding it to the panel
    private void makeConservativeList() {
        ArrayList<Stock> conservativeStocks = new ArrayList<Stock>();
        conservativeStocks.add(new Stock("Vanguard Conservative ETF Portfolio", "VCNS"));
        conservativeStocks.add(new Stock("iShares Core Conservative Balanced ETF Portfolio", "XCNS"));
        JLabel conservative = new JLabel("conservative: ");
        this.add(conservative);
        for (Stock stock : conservativeStocks) {
            JLabel name = new JLabel(stock.getName() + " with the ticker " + stock.getTicker());
            this.add(name);
        }
    }

    // MODIFIES: this
    // EFFECTS: making a list of risky stocks and adding it to the panel
    private void makeRiskyList() {
        ArrayList<Stock> riskyStocks = new ArrayList<Stock>();
        riskyStocks.add(new Stock("ProShares Ultra S&P500", "SSO"));
        riskyStocks.add(new Stock("ProShares UltraPro QQQ", "TQQQ"));
        JLabel risky = new JLabel("risky: ");
        this.add(risky);
        for (Stock stock : riskyStocks) {
            JLabel name = new JLabel(stock.getName() + " with the ticker " + stock.getTicker());
            this.add(name);
        }
    }
}
