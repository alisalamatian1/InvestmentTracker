package model;

import ui.UserInteraction;


//This is the entry point of the app after users make a profile
// Here I talk about different investment plans and give users suggestion
public class InvestmentPlans {
    private String typeOfInvestment;
    private UserInteraction userInteraction = new UserInteraction();

    // EFFECT: calling pickInvestment function
    public InvestmentPlans() {
        pickInvestment(userInteraction.questionnaire());
    }

    // MODIFIES: this
    // EFFECT: setting the type of investment according to the userInput, then calling showDescription
    public void pickInvestment(String choiceNumber) {
        switch (choiceNumber) {
            case "1":
                typeOfInvestment = "conservative";
                break;
            case "2":
                typeOfInvestment = "balanced";
                break;
            case "3":
                typeOfInvestment = "risky";
                break;
            default:
                System.out.println("Wrong choice! Please pick again from the given options.");
                pickInvestment(userInteraction.questionnaire());
        }
        showDescription();
    }

    // EFFECT: describing the investment plan, and instantiating Investment class
    public void showDescription() {
        // at the end
        System.out.println("let's check out a " + typeOfInvestment + " investment, that is what our algorithms suggest for you!");
        new Investment(typeOfInvestment);
    }

    public void addInvestmentType() {
        // add the type of the investment to the profile object and store it to the data base
    }

    public String getTypeOfInvestment() {
        return typeOfInvestment;
    }
}
