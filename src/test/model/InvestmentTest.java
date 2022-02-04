package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class InvestmentTest {
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
    public void sellingStocksTest() {
        stocksInWallet.addPurchasedStock(purchasedStock);
        int numberOfSharesInWallet = purchasedStock.getNumber();
        investment.sellingStocks();
        assertTrue(stocksInWallet.getStocks().get(0).getNumber() < numberOfSharesInWallet);
    }

}
