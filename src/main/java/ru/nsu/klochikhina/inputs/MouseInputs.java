package ru.nsu.klochikhina.inputs;

import ru.nsu.klochikhina.view.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {
    private final GamePanel panel;
    public MouseInputs(GamePanel panel) {
        this.panel = panel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
            panel.getGame().getPlayer().setAttacking(true);
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
    
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
    
    }
}
