package ui;

import model.*;
import model.exceptions.NegativeShareSellingException;
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
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.List;

public class Navigator {
    private static final String JSON_STORAGE = "./data/";
    MainWindow mainWindow;
    JPanel mainPanel;
    LoginPanel loginPanel;
    private SignUpPanel signUpPanel;
    private JPanel startPanel;
    StockSuggestionPanel stockSuggestionPanel;
    WalletPanel walletPanel;
    JPanel tabPanel;
    CardLayout cl;
    SellPanel sellPanel;
    BuyPanel buyPanel;

    boolean loginStatus;
    private String typeOfInvestment;
    private Investment investment = new Investment();
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
    Button save;
    Button load;
    Button logOut;
    JTabbedPane tabbedPane;
    ImageIcon icon;
    BarChart chart;

    public Navigator() {
        tabPanel = new JPanel();
        tabPanel.setLayout(new BorderLayout());

        JPanel left = new JPanel();
        left.setLayout(new GridLayout(10, 1, 2, 5));
        left.setBackground(Color.BLUE);
        makeMenuButtons(left);

        tabPanel.add(left, BorderLayout.WEST);
        mainWindow = new MainWindow();
        startPanel = new JPanel();
        signUpPanel = new SignUpPanel();
        loginPanel = new LoginPanel();
        makeTabbedStartPage();
        cl = new CardLayout();
        stockSuggestionPanel = new StockSuggestionPanel();
        stockSuggestionPanel.setLayout(new GridLayout(12, 1, 2,2));
        mainPanel = new JPanel();
        mainPanel.setLayout(cl);
        mainPanel.add(startPanel, "start");
        mainPanel.add(tabPanel, "tabPanel");
        mainPanel.add(stockSuggestionPanel, "stock");
        cl.show(mainPanel, "start");
        navigate();
    }

    // MODIFIES: this
    // EFFECTS: adding save, load and log out buttons to the left panel and set their corresponding actions
    private void makeMenuButtons(JPanel left) {
        save = new Button("save");
        load = new Button("load");
        logOut = new Button("log out");
        left.add(save);
        left.add(load);
        left.add(logOut);
        setActions();
    }

    // MODIFIES: this
    // EFFECTS: setting up the actions of each button
    private void setActions() {
        handleSaveButton();
        handleLoadButton();
        handleLogOutButton();
    }

    private void handleLogOutButton() {
        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void handleLoadButton() {
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                walletPanel.makeWallet();
                chart.setLoaded(true);
                settingDataAfterLoading();
            }
        });
    }

    private void handleSaveButton() {
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    saveUserInfo();
                } catch (FileNotFoundException ex) {
                    System.out.println("file not found!");
                }
            }
        });
    }

    public void navigate() {
        mainWindow.add(mainPanel);
        addLoggingButton();
        addSigningButton();
        addNextButton();
    }


    private void makeTabbedStartPage() {
        JTabbedPane tabbedPane = new JTabbedPane();
        ImageIcon icon = new ImageIcon();

        tabbedPane.addTab("Sign up", icon, signUpPanel,
                "Signing up");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        tabbedPane.addTab("Login", icon, loginPanel,
                "Logging in");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_1);
        startPanel.add(tabbedPane, BorderLayout.CENTER);
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
                    userProfileAndWallet = loginPanel.loadData();
                    settingDataAfterLoading();
                    walletPanel = new WalletPanel(stocksInWallet);
                    setUpTabPanel("login");
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

    // MODIFIES: this
    // EFFECTS: creates a signup button
    public void addSigningButton() {
        JButton signUpButton = new JButton("sign up");
        signUpButton.setBounds(10, 80, 80, 25);
        signUpPanel.add(signUpButton);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    signUpPanel.saveUserInfo();
                } catch (FileNotFoundException ex) {
                    System.out.println("file not found");
                }
                setDataAfterSignUp();
                walletPanel = new WalletPanel(stocksInWallet);
                cl.show(mainPanel, "stock");
            }
        });
    }

    // MODIFY: this
    // EFFECT: adding the next button for stockSuggestion Panel
    private void addNextButton() {
        JButton start = new JButton("Start investing!");
        stockSuggestionPanel.add(start);
        start.setLocation(200, 200);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setUpTabPanel("signUp");
                cl.show(mainPanel, "tabPanel");
            }
        });

    }

    public void setUpTabPanel(String namePanel) {
        tabbedPane = new JTabbedPane();
        icon = new ImageIcon();
        addWalletTab();
        sellPanel = new SellPanel();
        addSellTab();
        buyPanel = new BuyPanel();
        addBuyTab();
        sortStocks();
        chart = new BarChart(stocksInWallet);
        addBarChartTab();
    }

    private void addBarChartTab() {
        //chart.addStockLabels();
        tabbedPane.addTab("Bar Chart", icon, chart,
                "Asset Allocation Bar Chart");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
        tabPanel.add(tabbedPane, BorderLayout.CENTER);
    }

    private void sortStocks() {
        stocksInWallet.getStocks().sort(new Comparator<PurchasedStock>() {
            @Override
            public int compare(PurchasedStock o1, PurchasedStock o2) {
                if (o1.getStock().getTicker().equals(o2.getStock().getTicker())) {
                    return 0;
                }
                return o1.getStock().getTicker().compareTo(o2.getStock().getTicker());
            }
        });
    }

    private void addBuyTab() {
        JButton buyButton = buyPanel.getBuyButton();
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("we are inside buying");
                buyingStocks();
            }
        });
        tabbedPane.addTab("Buy", icon, buyPanel,
                "Buying Stocks");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
    }

    private void addSellTab() {
        JButton sellButton = sellPanel.getSellButton();
        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sellingStock();
            }
        });
        tabbedPane.addTab("Sell", icon, sellPanel,
                "Selling Stocks");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
    }

    private void addWalletTab() {
        Button update = new Button("update");
        walletPanel.add(update, BorderLayout.WEST);
        tabbedPane.addTab("Wallet", icon, walletPanel,
                "Wallet Content");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                walletPanel.makeWallet();
            }
        });
    }

    //MODIFY: this
    //EFFECTS: reading the stored file
    public void settingDataAfterLoading() {
        stocksInWallet = userProfileAndWallet.getWallet();
        investment.setStocksInWallet(stocksInWallet);
        investment.setProfit(userProfileAndWallet.getProfit());
        this.profit = userProfileAndWallet.getProfit();
        userProfile = userProfileAndWallet.getProfile();
    }

    public void setDataAfterSignUp() {
        userProfileAndWallet = signUpPanel.loadData();
        userProfile = userProfileAndWallet.getProfile();
        stocksInWallet = new StocksInWallet();

    }

    public void sellingStock() {
        String ticker = sellPanel.getTicker().getText();
        int numberOfShares = Integer.parseInt(sellPanel.getNumSharesText().getText());
        double price = Double.parseDouble(sellPanel.getPriceText().getText());
        Boolean wasSuccessful = null;
        try {
            wasSuccessful = investment.sellingStocks(ticker, numberOfShares, price);
        } catch (NegativeShareSellingException e) {
            JOptionPane.showMessageDialog(tabPanel,
                    "please enter positive number of shares only.",
                    "Invalid number of shares",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println("please enter positive number of shares only.");
        }
        showMessage(wasSuccessful);
        userProfileAndWallet.setProfit(investment.getProfit());
        this.profit = investment.getProfit();
        changeUserProfileAndWallet();
    }

    private void showMessage(Boolean wasSuccessful) {
        if (!wasSuccessful) {
            JOptionPane.showMessageDialog(tabPanel,
                    "Insufficient funding! Please look at the listed stocks you have and try again!",
                    "Insufficient funding",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println("Insufficient funding! Please look at the listed stocks you have and try again!");
        } else {
            JOptionPane.showMessageDialog(tabPanel,
                    "Successful sell",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECT: changing userProfileAndWallet when user changes their StocksInWallet
    public void changeUserProfileAndWallet() {
        userProfileAndWallet.addAssociatedWallets(userProfile, stocksInWallet);
    }

    public void saveUserInfo() throws FileNotFoundException {
        jsonWriting = new JsonWriting(JSON_STORAGE + "/" + userProfile.getUserName()
                + userProfile.getPassword() + ".json");
        jsonWriting.open();
        jsonWriting.write(userProfileAndWallet);
        jsonWriting.close();
    }

    public void buyingStocks() {
        String ticker = buyPanel.getTicker().getText();
        int numberOfShares = Integer.parseInt(buyPanel.getNumSharesText().getText());
        double price = Double.parseDouble(buyPanel.getPriceText().getText());
        Stock purchasingStock = new Stock(ticker);
        investment.setStocksInWallet(stocksInWallet);
        stocksInWallet = investment.buyingStocks(purchasingStock, numberOfShares, price);
        changeUserProfileAndWallet();
        JOptionPane.showMessageDialog(tabPanel,
                "Successful buy",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

}
// resources: https://github.com/BranislavLazic/SwingTutorials/blob/master/src/main/java/CardLayoutTutorial.java for cardLayout
// error message from https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html#features
// tabbedpanel from https://docs.oracle.com/javase/tutorial/uiswing/components/tabbedpane.html

