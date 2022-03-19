package ui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        initialize();
    }

    private void initialize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = new Dimension(screenSize.width * 3 / 4,
                screenSize.height * 3 / 4);
        System.out.println("width : " + windowSize.width + " height : " + windowSize.height);
        setTitle("Investment Tracker");
        setSize(windowSize.width, windowSize.height);
        setVisible(true);
        setLocation((screenSize.width - getWidth()) / 2, ((screenSize.height - getHeight()) / 2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
