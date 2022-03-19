package ui;

import model.PurchasedStock;
import model.StocksInWallet;

import javax.swing.*;
import java.awt.*;


public class BarChart extends JPanel {
    private static final int HEIGHT = 600;
    private static final int WIDTH = 600;
    private static final int Y_COORDINATE = HEIGHT;
    private StocksInWallet stocksInWallet;

    public BarChart(StocksInWallet stocksInWallet) {
        this.stocksInWallet = stocksInWallet;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBorder(BorderFactory.createLineBorder(Color.black));
        addStockLabels();
    }

    public void addStockLabels() {
        JLabel stockLabels = new JLabel("The stock tickers are:");
        this.add(stockLabels);
        for (PurchasedStock stocks : stocksInWallet.getStocks()) {
            addJLabel(0, HEIGHT, stocks.getStock().getTicker());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        // determine longest bar
        System.out.println("I am called!!!!!");
        // todo: adding the titles

        // paint bars
        int maxHeight = calculateTheMaxHeight();
        System.out.println("max height: " + maxHeight);
        double maxHeightRatio =  ((double) HEIGHT) / maxHeight;
        System.out.println(" ratio : " + maxHeightRatio);
        // todo: draw a line with the given width
        int widthGraphNoScaling = calculateNumOfGaps() + stocksInWallet.getStocks().size() + 2;
        int widthBar = WIDTH / widthGraphNoScaling;
        int dx = 2 * widthBar;
        g.drawLine(widthBar, Y_COORDINATE, WIDTH, Y_COORDINATE);
        g.drawLine(widthBar, Y_COORDINATE, widthBar, 0);
        String tempName = stocksInWallet.getStocks().get(0).getStock().getTicker();
        for (PurchasedStock stocks : stocksInWallet.getStocks()) {
            Double value = stocks.getNumber() * stocks.getPrice();
            Double heightDouble = value.intValue() * maxHeightRatio;
            int height = heightDouble.intValue();
            if (!stocks.getStock().getTicker().equals(tempName)) {
                tempName = stocks.getStock().getTicker();
                dx += widthBar;
                drawTheBar(g, dx, widthBar, height);
                System.out.println("drawing " + stocks.getStock().getTicker() + stocks.getPrice() + ", x:" + dx + ", height: " + height);
                dx += widthBar;
                continue;
            }
            drawTheBar(g, dx, widthBar, height);
            System.out.println("drawing " + stocks.getStock().getTicker() + stocks.getPrice() + ", x:" + dx + ", height: " + height);

            dx += widthBar;
        }
    }

    private int calculateNumOfGaps() {
        int numOfGaps = -1;
        String tempName = stocksInWallet.getStocks().get(0).getStock().getTicker();
        for (PurchasedStock stocks : stocksInWallet.getStocks()) {
            if (stocks.getStock().getTicker().equals(tempName)) {
                numOfGaps++;
                tempName = stocks.getStock().getTicker();
            }
        }
        return numOfGaps;
    }

    // todo: add a function for drawing each bar

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
    // todo: figure out the bug for the height of the bar, possibility they are too high

    public void addJLabel(int dx, int dy, String name) {
        JLabel ticker = new JLabel(name);
        ticker.setLocation(dx, dy);
        this.add(ticker);
    }
}