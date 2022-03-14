package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Navigator {
    MainWindow mainWindow;
    JPanel mainPanel;
    LoginPanel loginPanel;
    StockSuggestionPanel stockSuggestionPanel;
    CardLayout cl;
    boolean loginStatus = false;
    int count = 0;

    public Navigator() {
        mainWindow = new MainWindow();
        loginPanel = new LoginPanel();
        cl = new CardLayout();
        stockSuggestionPanel = new StockSuggestionPanel();
        mainPanel = new JPanel();
        mainPanel.setLayout(cl);
        mainPanel.add(loginPanel, "login");
        mainPanel.add(stockSuggestionPanel, "stock");
        cl.show(mainPanel, "login");
        navigate();
    }

    public void navigate() {
        mainWindow.add(mainPanel);
        addLoggingButton();
    }

    // MODIFIES: this
    // EFFECTS: creates a login button
    public void addLoggingButton() {
        JButton loginButton = new JButton("login");
        loginButton.setBounds(10, 80, 80, 25);
        loginPanel.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginStatus = loginPanel.checkForProfile();
                if (loginStatus) {
                    System.out.println("I am here in if");
                    cl.show(mainPanel, "stock");
                } else {
                    System.out.println("in the error message for login");
                    JLabel errorMessage = new JLabel("try again"); //User does not exist, please
                    errorMessage.setBounds(10,100,100,25);
                    errorMessage.setVisible(true);
                    loginPanel.add(errorMessage);
                }
            }
        });
    }

}


// resources: https://github.com/BranislavLazic/SwingTutorials/blob/master/src/main/java/CardLayoutTutorial.java for cardLayout
