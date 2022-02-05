package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class InvestmentTest {
    Investment investment;
    StocksInWallet stocksInWallet;
    Stock stock;
    @BeforeEach
    public void setUp(){
         investment = new Investment();
         stock = new Stock("VFV");

    }

    @Test
    public void sellingStocksWithSufficientFundingNotRemovingTest() {
        stocksInWallet = investment.getStocksInWallet();
        stocksInWallet.addPurchasedStock((new PurchasedStock(stock, 10,100)));
        assertTrue (investment.sellingStocks(stock, 2));
        assertEquals(8,stocksInWallet.getStocks().get(0).getNumber());
        assertEquals(1, stocksInWallet.getStocks().size());

        stocksInWallet.addPurchasedStock((new PurchasedStock(stock, 10,100)));
        assertTrue (investment.sellingStocks(stock, 2));
        assertEquals(6,stocksInWallet.getStocks().get(0).getNumber());
        assertEquals(2, stocksInWallet.getStocks().size());

    }

    @Test
    public void sellingStocksWithSufficientFundingRemovingTest() {
        stocksInWallet = investment.getStocksInWallet();
        stocksInWallet.addPurchasedStock((new PurchasedStock(stock, 10,100)));
        stocksInWallet.addPurchasedStock((new PurchasedStock(stock, 10,100)));
        //assertTrue (investment.sellingStocks(stock, 12));
        investment.sellingStocks(stock, 12);
        assertEquals(8,stocksInWallet.getStocks().get(0).getNumber());
        assertEquals(1, stocksInWallet.getStocks().size());


        assertTrue (investment.sellingStocks(stock, 7));
        assertEquals(1,stocksInWallet.getStocks().get(0).getNumber());
        assertEquals(1, stocksInWallet.getStocks().size());
        assertTrue(investment.sellingStocks(stock, 1));
        assertEquals(0,stocksInWallet.getStocks().size());

        stocksInWallet.addPurchasedStock((new PurchasedStock(stock, 10,101)));
        stocksInWallet.addPurchasedStock(new PurchasedStock(new Stock("TSLA"), 1,1700));
        stocksInWallet.addPurchasedStock((new PurchasedStock(stock, 10,102)));
        stocksInWallet.addPurchasedStock(new PurchasedStock(new Stock("TSLA"), 3,1700));
        stocksInWallet.addPurchasedStock((new PurchasedStock(stock, 10,103)));
        investment.sellingStocks(stock,25);
        assertEquals(3,stocksInWallet.getStocks().size());
        assertEquals(5,stocksInWallet.getStocks().get(2).getNumber());
        assertEquals(103,stocksInWallet.getStocks().get(2).getPrice());

        PurchasedStock purchasedStock = new PurchasedStock(stock, 10, 50);
        stocksInWallet.addPurchasedStock(purchasedStock);
        assertTrue (investment.sellingStocks(stock,6));
        assertEquals(4, stocksInWallet.getStocks().get(3).getNumber());
        assertEquals(50, stocksInWallet.getStocks().get(3).getPrice());
    }

    @Test
    public void sellingStocksWithInsufficientFundingTest() {
        stocksInWallet = investment.getStocksInWallet();
        stocksInWallet.addPurchasedStock((new PurchasedStock(stock, 10,100)));
        assertFalse (investment.sellingStocks(stock, 12));
        assertEquals(10,stocksInWallet.getStocks().get(0).getNumber());
        assertEquals(1, stocksInWallet.getStocks().size());

        assertFalse (investment.sellingStocks(stock, 11));
        assertEquals(10,stocksInWallet.getStocks().get(0).getNumber());
        assertEquals(1, stocksInWallet.getStocks().size());
    }


}
