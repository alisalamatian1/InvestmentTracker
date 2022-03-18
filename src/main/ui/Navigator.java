package ui;

import model.*;
import persistence.JsonReading;
import persistence.JsonWriting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

//
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

public class Navigator {
    private static final String JSON_STORAGE = "./data/";
    MainWindow mainWindow;
    JPanel mainPanel;
    LoginPanel loginPanel;
    StockSuggestionPanel stockSuggestionPanel;
    WalletPanel walletPanel;
    JPanel tabPanel;
    CardLayout cl;
    boolean loginStatus;
    private String typeOfInvestment;
    private Investment investment;
    private java.util.List<Stock> balancedStocks;
    private java.util.List<Stock> conservativeStocks;
    private List<Stock> riskyStocks;
    private StocksInWallet stocksInWallet;
    private UserProfile userProfile;
    private UserProfileAndWallet userProfileAndWallet;
    private final boolean isSignedUp = false;
    private final boolean isLoggedIn = false;
    private JsonWriting jsonWriting;
    private JsonReading jsonReading;
    private double profit;


    public Navigator() {
        tabPanel = new JPanel();
        mainWindow = new MainWindow();
        loginPanel = new LoginPanel();
        cl = new CardLayout();
        stockSuggestionPanel = new StockSuggestionPanel();
        mainPanel = new JPanel();
        mainPanel.setLayout(cl);
        mainPanel.add(loginPanel, "login");
        mainPanel.add(tabPanel, "tabPanel");
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
                    // todo: add a panel to ask for loadingData
                    userProfileAndWallet = loginPanel.loadData();
                    settingDataAfterLoading(userProfileAndWallet);
                    walletPanel = new WalletPanel(stocksInWallet);
                    setUpTabPanel();
                    cl.show(mainPanel, "tabPanel");
                } else {
                    JOptionPane.showMessageDialog(mainWindow,
                            "User does not exist, please try again",
                            "Invalid username or password",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void setUpTabPanel() {
        JTabbedPane tabbedPane = new JTabbedPane();
        ImageIcon icon = new ImageIcon();

        tabbedPane.addTab("Tab 1", icon, walletPanel,
                "Does nothing");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JComponent panel2 = makeTextPanel("Panel #2");
        tabbedPane.addTab("Tab 2", icon, panel2,
                "Does twice as much nothing");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        JComponent panel3 = makeTextPanel("Panel #3");
        tabbedPane.addTab("Tab 3", icon, panel3,
                "Still does nothing");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        JComponent panel4 = makeTextPanel(
                "Panel #4 (has a preferred size of 410 x 50).");
        panel4.setPreferredSize(new Dimension(410, 50));
        tabbedPane.addTab("Tab 4", icon, panel4,
                "Does nothing at all");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

        //Add the tabbed pane to this panel.
        tabPanel.add(tabbedPane);
    }

    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }

    //MODIFY: this
    //EFFECTS: reading the stored file
    public void settingDataAfterLoading(UserProfileAndWallet userProfileAndWallet) {
        stocksInWallet = userProfileAndWallet.getWallet();
//        investment.setStocksInWallet(stocksInWallet);
//        investment.setProfit(userProfileAndWallet.getProfit());
        this.profit = userProfileAndWallet.getProfit();
    }

}


// resources: https://github.com/BranislavLazic/SwingTutorials/blob/master/src/main/java/CardLayoutTutorial.java for cardLayout
// error message from https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html#features
// tabbedpanel from https://docs.oracle.com/javase/tutorial/uiswing/components/tabbedpane.html