package ui;

import model.Stock;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StockSuggestionPanel extends JPanel {
    private ArrayList<ArrayList<Stock>> suggestion;

    public StockSuggestionPanel() {
        suggestion = new ArrayList<>();
        makeSuggestions();
    }

    // EFFECTS: making the list of suggestions
    private void makeSuggestions() {
        makeBalancedStocksList();
        makeConservativeList();
        makeRiskyList();
    }

    // EFFECTS: making and displaying a list of balanced stocks
    public void makeBalancedStocksList() {
        ArrayList<Stock> balancedStocks = new ArrayList<Stock>();
        balancedStocks.add(new Stock("Vanguard S&P 500 Index ETF", "VFV"));
        balancedStocks.add(new Stock("Vanguard Balanced ETF Portfolio", "VBAL"));
        JLabel balanced = new JLabel("balanced: ");
        this.add(balanced);
        for (Stock stock : balancedStocks) {
            JLabel name = new JLabel(stock.getName() + "with the ticker" + stock.getTicker());
            this.add(name);
        }
    }

    // EFFECTS: making a list of conservative stocks
    public void makeConservativeList() {
        ArrayList<Stock> conservativeStocks = new ArrayList<Stock>();
        conservativeStocks.add(new Stock("Vanguard Conservative ETF Portfolio", "VCNS"));
        conservativeStocks.add(new Stock("iShares Core Conservative Balanced ETF Portfolio", "XCNS"));
        JLabel conservative = new JLabel("conservative: ");
        this.add(conservative);
        for (Stock stock : conservativeStocks) {
            JLabel name = new JLabel(stock.getName() + "with the ticker" + stock.getTicker());
            this.add(name);
        }
    }

    public void makeRiskyList() {
        ArrayList<Stock> riskyStocks = new ArrayList<Stock>();
        riskyStocks.add(new Stock("ProShares Ultra S&P500", "SSO"));
        riskyStocks.add(new Stock("ProShares UltraPro QQQ", "TQQQ"));
        JLabel risky = new JLabel("risky: ");
        this.add(risky);
        for (Stock stock : riskyStocks) {
            JLabel name = new JLabel(stock.getName() + "with the ticker" + stock.getTicker());
            this.add(name);
        }
    }
}
