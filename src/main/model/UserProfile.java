package model;

import ui.UserInteraction;


public class UserProfile {
    private String userName;
    private String password;
    private UserInteraction userInteraction = new UserInteraction();

    // EFFECT: calling makeProfile function
    public UserProfile() {
        makeProfile();
    }

    // MODIFIES: this
    // EFFECT: asking for the user credentials
    public void makeProfile() {
        this.userName = userInteraction.askUserName();
        boolean correctPass = false;
        while (!correctPass) {
            this.password = userInteraction.askUserPassword();
            if (password.length() >= 1) {
                correctPass = true;
            }
        }
        addNewUser();
    }

    // todo: adding a list that keeps track of those who logged in
    // Effect: add the created user to the hashSet of users
    public void addNewUser() {
        // Question: how to add the user to a dataBase that updates each time
        // I run the code?

        callingInvestmentPlan();
    }

    public void callingInvestmentPlan() {
        new InvestmentPlans();
    }

}
