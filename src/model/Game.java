package model;

import view.GameWindow;

import javax.swing.*;

public class Game extends JFrame implements Runnable {
    private static GameWindow window;
    public Game() {
        window = new GameWindow();

    }

    @Override
    public void run() {

    }
}
