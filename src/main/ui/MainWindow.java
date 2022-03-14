package ui;

import javax.swing.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        initialize();
    }

    private void initialize() {
        setTitle("Investment Tracker");
        setSize(350, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
