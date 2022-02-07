package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PurchasedStockTest {
    Investment investment;
    StocksInWallet stocksInWallet;
    PurchasedStock purchasedStock;
    @BeforeEach
    public void setUp(){
        investment = new Investment();
        stocksInWallet = new StocksInWallet();
        Stock stock = new Stock("VFV");
        purchasedStock = new PurchasedStock(stock, 10, 111);
    }

    @Test
    public void constructorTest() {
        PurchasedStock purchasedStockTest = new PurchasedStock(new Stock ("VFV"), 2, 100);
        assertEquals("VFV", purchasedStockTest.getStock().getTicker());
        assertEquals(2,purchasedStockTest.getNumber());
        assertEquals(100, purchasedStockTest.getPrice());

        purchasedStockTest = new PurchasedStock(new Stock ("TESLA"), 3, 200);
        assertEquals("TESLA", purchasedStockTest.getStock().getTicker());
        assertEquals(3,purchasedStockTest.getNumber());
        assertEquals(200, purchasedStockTest.getPrice());


    }

    @Test
    public void decreasingNumberOfSharesTest() {
        purchasedStock.decreasingTheNumberOfShares(2);
        assertEquals(8, purchasedStock.getNumber());
        purchasedStock.decreasingTheNumberOfShares(3);
        assertEquals(5, purchasedStock.getNumber());
    }
}
