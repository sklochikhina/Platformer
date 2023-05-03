package view;

import javax.swing.*;

public class GameWindow extends JFrame {
    private final int width = 400;
    private final int height = 400;
    public GameWindow() {
        setSize(width, height);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
