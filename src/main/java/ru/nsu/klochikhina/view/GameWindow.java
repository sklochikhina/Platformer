package ru.nsu.klochikhina.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class GameWindow extends JFrame {
    private final int width = 1280;
    private final int height = 720;
    public GameWindow(final GamePanel panel) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        add(panel);
        
        Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (s.width - width) / 2;
        int y = (s.height - height) / 2;
        setBounds(x, y, width, height);
        
        setResizable(false);
        pack();
        setVisible(true);
        addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
            
            }
            
            @Override
            public void windowLostFocus(WindowEvent e) {
                panel.getGame().windowFocusLost();
            }
        });
    }
}
