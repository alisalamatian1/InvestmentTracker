package model;

import model.exceptions.NegativeShareSellingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test for the investment class
public class InvestmentTest {
    Investment investment;
    StocksInWallet stocksInWallet;
    Stock stock;
    String ticker;

    @BeforeEach
    public void setUp(){
         investment = new Investment();
         stock = new Stock("VFV");
         ticker = "VFV";
    }

    @Test
    public void sellingStocksWithSufficientFundingNotRemovingTest() {
        stocksInWallet = investment.getStocksInWallet();
        stocksInWallet.addPurchasedStock((new PurchasedStock(stock, 10,100)));
        try {
            assertTrue(investment.sellingStocks(ticker, 2, 0));
        } catch (NegativeShareSellingException e) {
            fail("did not except an exception!");
        }
        assertEquals(8,stocksInWallet.getStocks().get(0).getNumber());
        assertEquals(1, stocksInWallet.getStocks().size());

        stocksInWallet.addPurchasedStock((new PurchasedStock(stock, 10,100)));
        try {
            assertTrue (investment.sellingStocks(ticker, 2, 0));
        } catch (NegativeShareSellingException e) {
            fail("did not except an exception!");
        }
        assertEquals(6,stocksInWallet.getStocks().get(0).getNumber());
        assertEquals(2, stocksInWallet.getStocks().size());

    }

    @Test
    public void sellingStocksWithSufficientFundingRemovingTest() {
        stocksInWallet = investment.getStocksInWallet();
        stocksInWallet.addPurchasedStock((new PurchasedStock(stock, 10,100)));
        stocksInWallet.addPurchasedStock((new PurchasedStock(stock, 10,100)));
        //assertTrue (investment.sellingStocks(stock, 12));
        try {
            investment.sellingStocks(ticker, 12, 0);
        } catch (NegativeShareSellingException e) {
            fail("did not except an exception!");
        }
        assertEquals(8,stocksInWallet.getStocks().get(0).getNumber());
        assertEquals(1, stocksInWallet.getStocks().size());


        try {
            assertTrue (investment.sellingStocks(ticker, 7, 0));
        } catch (NegativeShareSellingException e) {
            fail("did not except an exception!");
        }
        assertEquals(1,stocksInWallet.getStocks().get(0).getNumber());
        assertEquals(1, stocksInWallet.getStocks().size());
        try {
            assertTrue(investment.sellingStocks(ticker, 1, 0));
        } catch (NegativeShareSellingException e) {
            fail("did not except an exception!");
        }
        assertEquals(0,stocksInWallet.getStocks().size());

        stocksInWallet.addPurchasedStock((new PurchasedStock(stock, 10,101)));
        stocksInWallet.addPurchasedStock(new PurchasedStock(new Stock("TSLA"), 1,1700));
        stocksInWallet.addPurchasedStock((new PurchasedStock(stock, 10,102)));
        stocksInWallet.addPurchasedStock(new PurchasedStock(new Stock("TSLA"), 3,1700));
        stocksInWallet.addPurchasedStock((new PurchasedStock(stock, 10,103)));
        try {
            investment.sellingStocks(ticker,25, 0);
        } catch (NegativeShareSellingException e) {
            fail("did not except an exception!");
        }
        assertEquals(3,stocksInWallet.getStocks().size());
        assertEquals(5,stocksInWallet.getStocks().get(2).getNumber());
        assertEquals(103,stocksInWallet.getStocks().get(2).getPrice());

        PurchasedStock purchasedStock = new PurchasedStock(stock, 10, 50);
        stocksInWallet.addPurchasedStock(purchasedStock);
        try {
            assertTrue (investment.sellingStocks(ticker,6, 0));
        } catch (NegativeShareSellingException e) {
            fail("did not except an exception!");
        }
        assertEquals(4, stocksInWallet.getStocks().get(3).getNumber());
        assertEquals(50, stocksInWallet.getStocks().get(3).getPrice());
    }

    @Test
    public void sellingStocksWithInsufficientFundingTest() {
        stocksInWallet = investment.getStocksInWallet();
        stocksInWallet.addPurchasedStock((new PurchasedStock(stock, 10,100)));
        try {
            assertFalse (investment.sellingStocks(ticker, 12, 0));
        } catch (NegativeShareSellingException e) {
            fail("did not except an exception!");
        }
        assertEquals(10,stocksInWallet.getStocks().get(0).getNumber());
        assertEquals(1, stocksInWallet.getStocks().size());

        try {
            assertFalse (investment.sellingStocks(ticker, 11, 0));
        } catch (NegativeShareSellingException e) {
            fail("did not except an exception!");
        }
        assertEquals(10,stocksInWallet.getStocks().get(0).getNumber());
        assertEquals(1, stocksInWallet.getStocks().size());
    }

    @Test
    public void sellingWithException() {
        stocksInWallet = investment.getStocksInWallet();
        stocksInWallet.addPurchasedStock((new PurchasedStock(stock, 10,100)));
        try {
            assertTrue(investment.sellingStocks(ticker, -1, 0));
            fail("expected an Exception!");
        } catch (NegativeShareSellingException e) {
            // expected
        }
        assertEquals(10,stocksInWallet.getStocks().get(0).getNumber());
        assertEquals(1, stocksInWallet.getStocks().size());
    }

    @Test
    public void testProfitCalculationSelling() {
        stocksInWallet = investment.getStocksInWallet();
        stocksInWallet.addPurchasedStock((new PurchasedStock(stock, 10,100)));
        try {
            assertTrue(investment.sellingStocks(ticker, 2, 150));
        } catch (NegativeShareSellingException e) {
            fail("did not except an exception!");
        }
        assertEquals(8,stocksInWallet.getStocks().get(0).getNumber());
        assertEquals(1, stocksInWallet.getStocks().size());
        assertEquals(100, investment.getProfit());
        stocksInWallet.addPurchasedStock((new PurchasedStock(new Stock("apple"), 10,100)));
        stocksInWallet.addPurchasedStock((new PurchasedStock(stock, 10,100)));
        try {
            assertTrue(investment.sellingStocks(ticker, 10, 150));
        } catch (NegativeShareSellingException e) {
            fail("did not except an exception!");
        }
        assertEquals(8,stocksInWallet.getStocks().get(1).getNumber());
        assertEquals(2, stocksInWallet.getStocks().size());
        assertEquals(600, investment.getProfit());

    }

    @Test
    public void buyingStocksTest() {
        assertEquals(investment.getStocksInWallet(), investment.buyingStocks(stock, 5, 120));
        assertEquals(stock, investment.getStocksInWallet().getStocks().get(0).getStock());
        assertEquals(5, investment.getStocksInWallet().getStocks().get(0).getNumber());
        assertEquals(120, investment.getStocksInWallet().getStocks().get(0).getPrice());
        assertEquals(1, investment.getStocksInWallet().getStocks().size());

        assertEquals(investment.getStocksInWallet(), investment.buyingStocks(stock, 10, 121));
        assertEquals(5, investment.getStocksInWallet().getStocks().get(0).getNumber());
        assertEquals(120, investment.getStocksInWallet().getStocks().get(0).getPrice());
        assertEquals(stock, investment.getStocksInWallet().getStocks().get(1).getStock());
        assertEquals(10, investment.getStocksInWallet().getStocks().get(1).getNumber());
        assertEquals(121, investment.getStocksInWallet().getStocks().get(1).getPrice());
        assertEquals(2, investment.getStocksInWallet().getStocks().size());
    }

    @Test
    public void testSetStocksInWallet() {
        StocksInWallet test = new StocksInWallet();
        test.addPurchasedStock (new PurchasedStock(stock, 1, 1));
        investment.setStocksInWallet(test);
        assertEquals(1, test.getStocks().get(0).getNumber());
    }

    @Test
    public void testSetProfit() {
        investment.setProfit(3);
        assertEquals(3, investment.getProfit());
    }


}
