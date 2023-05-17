package ru.nsu.klochikhina.view;

import ru.nsu.klochikhina.inputs.KeyboardInputs;
import ru.nsu.klochikhina.inputs.MouseInputs;
import ru.nsu.klochikhina.model.Game;

import javax.swing.*;
import java.awt.*;

import static ru.nsu.klochikhina.model.Game.GAME_HEIGHT;
import static ru.nsu.klochikhina.model.Game.GAME_WIDTH;

public class GamePanel extends JPanel {
    private final Game game;
    public GamePanel(Game game) {
        this.game = game;
        MouseInputs mouse = new MouseInputs(this);
        
        addKeyListener(new KeyboardInputs(this));
        setPanelSize();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }
    
    private void setPanelSize() {
        Dimension windowSize = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        
        setMinimumSize(windowSize);
        setPreferredSize(windowSize);
        setMaximumSize(windowSize);
        
        System.out.println("size: " + GAME_WIDTH + " : " + GAME_HEIGHT);
    }
    
    public void updateGame(){
    
    }
    
    public void render(Graphics graphics){
    
    }
    
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        game.render(graphics);
    }
    
    public Game getGame() {
        return game;
    }
}
