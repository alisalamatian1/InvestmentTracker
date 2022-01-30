package model;

import java.util.Scanner;

public class UserProfile {
    private String userName;
    private String password;
    private Scanner scanner = new Scanner(System.in);

    //constructor
    public UserProfile() {
        makeProfile();
    }

    public void makeProfile() {
        System.out.println("What is your userName?");
        this.userName = scanner.next();
        boolean correctPass = false;
        while (!correctPass) {
            System.out.println("Enter a password at least 8 digits");
            this.password = scanner.next();
            if (password.length() >= 8) {
                correctPass = true;
            }
        }
        addNewUser();
    }

    // Effect: add the created user to the hashSet of users
    public void addNewUser() {
        // Question: how to add the user to a dataBase that updates each time
        // I run the code?

        callingInvestmentPlan();
    }

    public void callingInvestmentPlan() {
        new InvestmentPlans();
    }

    //Calling InvestmentPlan
}
