package ui;

import model.PurchasedStock;
import model.StocksInWallet;

import javax.swing.*;
import java.awt.*;


public class BarChart extends JPanel {
    private static final int HEIGHT = 600;
    private static final int WIDTH = 600;
    private static final int Y_COORDINATE = HEIGHT - 10;
    private static final int X_2_LINE = WIDTH * 2;
    private final StocksInWallet stocksInWallet;
    private String label;
    private final JLabel stockLabels;
    private boolean loaded = false;

    public BarChart(StocksInWallet stocksInWallet) {
        this.setLayout(null);
        this.stocksInWallet = stocksInWallet;
        stockLabels = new JLabel();
        this.add(stockLabels);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void addStockLabels() {
        label = "labels: ";
        for (PurchasedStock stocks : stocksInWallet.getStocks()) {
            label += " " + stocks.getStock().getTicker();
        }
        setTextLabel();
        stockLabels.setBounds(10, Y_COORDINATE + 5, WIDTH, 20);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (loaded) {
            addStockLabels();
            System.out.println("running well");
            int maxHeight = calculateTheMaxHeight();
            double maxHeightRatio = ((double) HEIGHT) / maxHeight;
            int numStocksAndGaps = calculateNumOfGaps() + stocksInWallet.getStocks().size() + 2;
            int widthBar = WIDTH / numStocksAndGaps;
            int dx = widthBar;
            drawLines(g, widthBar);
            String tempName = "";
            loopAndDraw(g, maxHeightRatio, widthBar, dx, tempName);
        }
    }

    private void loopAndDraw(Graphics g, double maxHeightRatio, int widthBar, int dx, String tempName) {
        for (PurchasedStock stocks : stocksInWallet.getStocks()) {
            Double value = stocks.getNumber() * stocks.getPrice();
            Double heightDouble = value.intValue() * maxHeightRatio;
            int height = heightDouble.intValue();
            if (!stocks.getStock().getTicker().equals(tempName)) {
                tempName = stocks.getStock().getTicker();
                dx += widthBar;
                drawTheBar(g, dx, widthBar, height);
                dx += widthBar;
                continue;
            }
            drawTheBar(g, dx, widthBar, height);
            dx += widthBar;
        }
    }

    private void drawLines(Graphics g, int widthBar) {
        if (!stocksInWallet.getStocks().isEmpty()) {
            g.drawLine(widthBar, Y_COORDINATE + 1, X_2_LINE, Y_COORDINATE);
            g.drawLine(widthBar, Y_COORDINATE + 1, widthBar, 0);
        }
    }

    private int calculateNumOfGaps() {
        int numOfGaps = -1;
        String tempName = "";
        //String tempName = stocksInWallet.getStocks().get(0).getStock().getTicker();
        for (PurchasedStock stocks : stocksInWallet.getStocks()) {
            if (stocks.getStock().getTicker().equals(tempName)) {
                numOfGaps++;
                tempName = stocks.getStock().getTicker();
            }
        }
        return numOfGaps;
    }

    public int calculateTheMaxHeight() {
        int max = Integer.MIN_VALUE;
        for (PurchasedStock stock : stocksInWallet.getStocks()) {
            Double value = stock.getNumber() * stock.getPrice();
            max = Math.max(max, value.intValue());
        }
        return max;
    }

    public void drawTheBar(Graphics g, int dx, int widthBar, int height) {
        g.setColor(Color.PINK);
        g.fillRect(dx, Y_COORDINATE - height, widthBar, height);
        g.setColor(Color.red);
        g.drawRect(dx, Y_COORDINATE - height, widthBar, height);
    }

    public void setTextLabel() {
        System.out.println(label);
        stockLabels.setText(label);
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }
}