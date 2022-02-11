package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StocksInWalletTest {
    StocksInWallet stocksInWallet;
    @BeforeEach
    public void setUp() {
        stocksInWallet = new StocksInWallet();
    }

    @Test
    public void StocksInWalletConstructorTest() {
        assertEquals(0, stocksInWallet.getStocks().size());
    }

    @Test
    public void addPurchaseStock() {
        PurchasedStock purchasedStock = new PurchasedStock(new Stock("VFV"), 1, 100);
        stocksInWallet.addPurchasedStock(purchasedStock);
        assertEquals(purchasedStock, stocksInWallet.getStocks().get(0));
        assertEquals(1, stocksInWallet.getStocks().size());

        purchasedStock = new PurchasedStock(new Stock("TSLA"), 1, 100);
        stocksInWallet.addPurchasedStock(purchasedStock);
        assertEquals(purchasedStock, stocksInWallet.getStocks().get(1));
        assertEquals(2, stocksInWallet.getStocks().size());
    }
}
