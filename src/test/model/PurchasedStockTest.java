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
        investment = new Investment("balanced");
        stocksInWallet = new StocksInWallet();
        Stock stock = new Stock("VFV");
        purchasedStock = new PurchasedStock(stock, 10, 111);
    }

    @Test
    public void decreasingNumberOfSharesTest() {
        purchasedStock.decreasingTheNumberOfShares(2);
        assertEquals(8, purchasedStock.getNumber());
        purchasedStock.decreasingTheNumberOfShares(3);
        assertEquals(5, purchasedStock.getNumber());
    }
}
