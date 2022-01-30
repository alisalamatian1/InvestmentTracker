package model;

// In this class users actually do the investment
public class Investment {
    private String actionType;

    public Investment() {
    }

    public void showDescription(String typeOfInvestment) {
        System.out.println("List of the stocks for you is: ");
        switch (typeOfInvestment) {
            default:
                balancedStocksList();
        }
        System.out.println("Do you want to sell, buy, or quit?");
    }

    public void balancedStocksList() {
        System.out.println("S & p 500");
    }

    public void doAction(String actionType) {
        // sellDescription();
        // buyDescription();
    }
}
