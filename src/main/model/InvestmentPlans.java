package model;

import java.util.Scanner;

//This is the entry point of the app after users make a profile
// Here I talk about different investment plans and give users suggestion
public class InvestmentPlans {
    private String typeOfInvestment;
    private Scanner scanner = new Scanner(System.in);

    //constructor
    public InvestmentPlans() {
        questionnaire();
    }

    public void questionnaire() {
        System.out.println("how much risk you are willing to take?");
        System.out.println("Remember that ususally the higher the risk the better chance of bigger reward...");
        System.out.println("\t 1) very little");
        System.out.println("\t 2) moderate");
        System.out.println("\t 3) risky");
        String choiceNumber = scanner.next();
        pickInvestment(choiceNumber);
    }

    public void pickInvestment(String choiceNumber) {
        switch (choiceNumber) {
            case "1":
                typeOfInvestment = "conservative";
                break;
            case "2":
                typeOfInvestment = "balance";
                break;
            case "3":
                typeOfInvestment = "risky";
                break;
            default:
                System.out.println("Wrong choice! Please pick again");
                questionnaire();
        }
        showDescription();
    }

    public void showDescription() {
        // at the end
        System.out.println("let's check out a " + typeOfInvestment + " investment, that our algorithms suggest for you!");
        new Investment().showDescription(typeOfInvestment);
    }

    public void addInvestmentType() {
        // add the type of the investment to the profile object and store it to the data base
    }

    public String getTypeOfInvestment() {
        return typeOfInvestment;
    }
}
