package ru.nsu.klochikhina.model;

import ru.nsu.klochikhina.entities.Player;
import ru.nsu.klochikhina.levels.LevelManager;
import ru.nsu.klochikhina.view.GamePanel;
import ru.nsu.klochikhina.view.GameWindow;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame implements Runnable {
    private static GamePanel panel;
    private LevelManager manager;
    private Player player;
    
    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.5f;
    public final static int TILES_IN_WIDTH = 31;
    public final static int TILES_IN_HEIGHT = 16;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
    
    public Game() {
        initClasses();
        panel = new GamePanel(this);
        GameWindow window = new GameWindow(panel);
        panel.requestFocus();
        startGameLoop();
    }
    
    private void initClasses() {
        manager = new LevelManager(this);
        player = new Player(200, 200, (int) (12 * 3), (int) (20 * 3));
        manager.setRed(player.isRed());
        player.loadLevelData(manager.getCurrentLevel().getLevelData());
    }
    
    private void startGameLoop(){
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        player.update();
        manager.update();
    }
    
    public void render(Graphics graphics){
        manager.draw(graphics, player.isRed());
        player.render(graphics);
        
    }
    
    @Override
    public void run() {
        int FPS_SET = 120;
        double timePerFrame = 1000000000.0 / FPS_SET;
        int UPS_SET = 200;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        
        long previousTime = System.nanoTime();
        
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();
        
        double deltaU = 0;
        double deltaF = 0;
        
        while (true){
            long currentTime = System.nanoTime();
            
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;
            
            if(deltaU >= 1){
                update();
                updates++;
                deltaU--;
            }
            
            if(deltaF >= 1){
                panel.repaint();
                frames++;
                deltaF--;
            }
            
            if(System.currentTimeMillis() - lastCheck >= 1000){ // >= 1 sec
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
        
    }
    
    public void windowFocusLost() {
        player.resetDirBooleans();
    }
    
    public Player getPlayer() {
        return player;
    }
}
