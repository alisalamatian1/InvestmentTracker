package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Test for the Stock class
public class StockTest {
    @Test
    public void stockConstructorNameAndTickerTest() {
        Stock stock = new Stock ("Vanguard", "VFV");
        assertEquals("Vanguard", stock.getName());
        assertEquals("VFV", stock.getTicker());

        stock = new Stock ("Tesla", "TSLA");
        assertEquals("Tesla", stock.getName());
        assertEquals("TSLA", stock.getTicker());
    }

    @Test
    public void stockConstructorTickerTest() {
        Stock stock = new Stock ("VFV");
        assertEquals("VFV", stock.getTicker());

        stock = new Stock ( "TSLA");
        assertEquals("TSLA", stock.getTicker());
    }
}
