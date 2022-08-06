package ui;

import dao.DbConnector;
import model.*;
import model.exceptions.NegativeShareSellingException;
import persistence.JsonWriting;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.util.Comparator;

// class for controlling the navigation system
public class Navigator {
    private static final String JSON_STORAGE = "./data/";
    private MainWindow mainWindow;
    private JPanel mainPanel;
    private LoginPanel loginPanel;
    private SignUpPanel signUpPanel;
    private JPanel startPanel;
    private StockSuggestionPanel stockSuggestionPanel;
    private WalletPanel walletPanel;
    private JPanel tabPanel;
    private CardLayout cl;
    private SellPanel sellPanel;
    private BuyPanel buyPanel;
    private boolean loginStatus;
    private Investment investment = new Investment();
    private StocksInWallet stocksInWallet;
    private UserProfileAndWallet userProfileAndWallet;
    private JsonWriting jsonWriting;
    private double profit;
    private Button save;
    private Button load;
    private Button logOut;
    private JTabbedPane tabbedPane;
    private ImageIcon icon;
    private BarChart chart;

    // EFFECTS: constructing a navigator and initializing its panels
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
        initialize();
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

    // MODIFIES: this
    // EFFECTS: handling the action of the logout button and printing the log
    private void handleLogOutButton() {
        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogPrinter lp = new LogPrinter();
                lp.printLog(EventLog.getInstance());
                System.exit(0);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: handling the action of the load button
    private void handleLoadButton() {
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                walletPanel.makeWallet();
                chart.setLoaded(true);
                chart.addStockLabels();
                settingDataAfterLoading();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: handling the action of the save button
    private void handleSaveButton() {
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeUserProfileAndWallet();
                try {
                    saveUserInfo();
                } catch (FileNotFoundException ex) {
                    System.out.println("file not found!");
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: adding the main panel to the frame and adding the essential buttons
    public void initialize() {
        mainWindow.add(mainPanel);
        addLoggingButton();
        addSigningButton();
        addNextButton();
    }

    // MODIFIES: this
    // EFFECTS: making a tab panel with options to log in or sign in
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
    // EFFECTS: creates a login button and handles the corresponding action
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
    // EFFECTS: creates a signup button and handles the corresponding action
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
    // EFFECT: adding the next button for stockSuggestion Panel and handles the corresponding action
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

    // MODIFIES: this
    // EFFECTS: making the main page tab panel with the options:
    // wallet, sell, buy, and barchart
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
        tabPanel.add(tabbedPane, BorderLayout.CENTER);
        addObservers();
    }

    // MODIFIES: Investment
    // EFFECTS: adding chart and wallet panels to the list of observers in investment
    private void addObservers() {
        investment.addObserver(walletPanel);
        investment.addObserver(chart);
    }

    // MODIFIES: this
    // EFFECTS: adding the bar chart panel to tabbedPane
    private void addBarChartTab() {
        tabbedPane.addTab("Bar Chart", icon, chart,
                "Asset Allocation Bar Chart");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
    }

    // MODIFIES: this
    // EFFECTS: sorting the stocks so the same stocks come together
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

    // MODIFIES: this
    // EFFECTS: adding the buy panel to tabbedPane
    private void addBuyTab() {
        JButton buyButton = buyPanel.getBuyButton();
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyingStocks();
            }
        });
        tabbedPane.addTab("Buy", icon, buyPanel,
                "Buying Stocks");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
    }

    // MODIFIES: this
    // EFFECTS: adding the sell panel to tabbedPane
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

    // MODIFIES: this
    // EFFECTS: adding the wallet panel to tabbedPane
    private void addWalletTab() {
        tabbedPane.addTab("Wallet", icon, walletPanel,
                "Wallet Content");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
    }

    //MODIFIES: this
    //EFFECTS: reading the stored file
    public void settingDataAfterLoading() {
        stocksInWallet = userProfileAndWallet.getWallet();
        investment.setStocksInWallet(stocksInWallet);
        investment.setProfit(userProfileAndWallet.getProfit());
        this.profit = userProfileAndWallet.getProfit();
    }

    // MODIFIES: this
    // EFFECTS: setting the necessary info after sign up
    public void setDataAfterSignUp() {
        userProfileAndWallet = signUpPanel.loadData();
        stocksInWallet = new StocksInWallet();
    }

    // MODIFIES: this
    // EFFECTS: selling the stocks based on the inputted ticker and number of shares
    public void sellingStock() {
        String ticker = sellPanel.getTicker().getText();
        int numberOfShares = Integer.parseInt(sellPanel.getNumSharesText().getText());
        double price = Double.parseDouble(sellPanel.getPriceText().getText());
        try {
            boolean wasSuccessful = investment.sellingStocks(ticker, numberOfShares, price);
            showMessage(wasSuccessful);
            userProfileAndWallet.setProfit(investment.getProfit());
            this.profit = investment.getProfit();
        } catch (NegativeShareSellingException e) {
            JOptionPane.showMessageDialog(tabPanel,
                    "please enter positive number of shares only.",
                    "Invalid number of shares",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // EFFECTS: show the corresponding message based on the success of the sell
    private void showMessage(Boolean wasSuccessful) {
        if (!wasSuccessful) {
            JOptionPane.showMessageDialog(tabPanel,
                    "Insufficient funding! Please look at the listed stocks you have and try again!",
                    "Insufficient funding",
                    JOptionPane.ERROR_MESSAGE);
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
        userProfileAndWallet.setWallet(stocksInWallet);
    }

    // MODIFIES: this
    // EFFECTS: saving the user info and writing that into the database
    public void saveUserInfo() throws FileNotFoundException {
        String id = userProfileAndWallet.getProfile().getUserName() + userProfileAndWallet.getProfile().getPassword();
        DbConnector.update(userProfileAndWallet, id);
    }

    // MODIFIES: this
    // EFFECTS: buying stocks based on the entered ticker and number of shares
    public void buyingStocks() {
        String ticker = buyPanel.getTicker().getText();
        int numberOfShares = Integer.parseInt(buyPanel.getNumSharesText().getText());
        double price = Double.parseDouble(buyPanel.getPriceText().getText());
        Stock purchasingStock = new Stock(ticker);
        investment.setStocksInWallet(stocksInWallet);
        stocksInWallet = investment.buyingStocks(purchasingStock, numberOfShares, price);
        JOptionPane.showMessageDialog(tabPanel,
                "Successful buy",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
// Used resources:
// https://github.com/BranislavLazic/SwingTutorials/blob/master/src/main/java/CardLayoutTutorial.java for cardLayout
// error message from https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html#features
// tabbedPanel from https://docs.oracle.com/javase/tutorial/uiswing/components/tabbedpane.html

