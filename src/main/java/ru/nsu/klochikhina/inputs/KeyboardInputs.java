package ru.nsu.klochikhina.inputs;

import ru.nsu.klochikhina.view.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {
    private final GamePanel panel;
    public KeyboardInputs(GamePanel panel) {
        this.panel = panel;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                panel.getGame().getPlayer().setUp(true);
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                panel.getGame().getPlayer().setLeft(true);
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                panel.getGame().getPlayer().setDown(true);
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                panel.getGame().getPlayer().setRight(true);
                break;
            case KeyEvent.VK_E:
                panel.getGame().getPlayer().changeCharacter();
                break;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                panel.getGame().getPlayer().setUp(false);
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                panel.getGame().getPlayer().setLeft(false);
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                panel.getGame().getPlayer().setDown(false);
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                panel.getGame().getPlayer().setRight(false);
                break;
        }
    }
}
