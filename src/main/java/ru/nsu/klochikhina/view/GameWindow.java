package ru.nsu.klochikhina.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class GameWindow extends JFrame {
    public GameWindow(final GamePanel panel) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        add(panel);
        
        Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
        int width = 1488;
        int height = 864;
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
