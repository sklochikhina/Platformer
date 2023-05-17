package ru.nsu.klochikhina.levels;

import ru.nsu.klochikhina.model.Game;
import ru.nsu.klochikhina.utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelManager {
    private Game game;
    private BufferedImage levelSprite;
    public LevelManager(Game game) {
        this.game = game;
        levelSprite = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
    }
    
    public void draw(Graphics graphics){
        graphics.drawImage(levelSprite, 0, 0, null);
    }
    
    public void update(){
    
    }
}
