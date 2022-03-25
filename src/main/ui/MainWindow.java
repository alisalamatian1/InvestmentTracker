package ui;

import javax.swing.*;
import java.awt.*;

// class representing the main Frame
public class MainWindow extends JFrame {
    // EFFECTS: initializing the JavaFrame
    public MainWindow() {
        initialize();
    }

    // EFFECTS: setting the size and other properties of the frame
    private void initialize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = new Dimension(screenSize.width * 3 / 4,
                screenSize.height * 3 / 4);
        setTitle("Investment Tracker");
        setSize(windowSize.width, windowSize.height);
        setVisible(true);
        setLocation((screenSize.width - getWidth()) / 2, ((screenSize.height - getHeight()) / 2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
