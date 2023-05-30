package ru.nsu.klochikhina.levels;

import ru.nsu.klochikhina.model.Game;
import ru.nsu.klochikhina.utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelManager {
    private final Game game;
    private BufferedImage[] levelSprite;
    private BufferedImage[] skySprite;
    private BufferedImage[] CloudsCity;
    private Level level;
    private float offsetX = 0.0f;
    private float width = 3200.0f;
    private boolean isRed;
    
    public LevelManager(Game game) {
        this.game = game;
        this.isRed = game.getPlayer().isRed();
        importOutsideSprite();
        importBackground();
        level = new Level(LoadSave.getLevelData());
    }
    
    public void importOutsideSprite() {
        BufferedImage image = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[4];
                for (int i = 0; i < 4; i++)
            levelSprite[i] = image.getSubimage(i * 32, 0, 32, 32);
    }
    
    public void importBackground(){
        BufferedImage sky = LoadSave.getSpriteAtlas(LoadSave.LEVEL_SKY);
        BufferedImage clouds_city = LoadSave.getSpriteAtlas(LoadSave.LEVEL_BG);
        
        skySprite = new BufferedImage[2];
        CloudsCity = new BufferedImage[4];
        
        for (int i = 0; i < 4; i++) {
            if(i < 2)
                skySprite[i] = sky.getSubimage(0, i * 768, 1488, 768);
            CloudsCity[i] = clouds_city.getSubimage(0, i * 768, 3200, 768);
        }
    }
    
    public void draw(Graphics graphics, boolean isRed){
        BufferedImage clouds = CloudsCity[isRed ? 0 : 1].getSubimage(0, 0, 1488, 768);
        
        if (isRed != this.isRed)
            offsetX = offsetX % CloudsCity[isRed ? 0 : 1].getWidth();

        offsetX += 0.3;

        if (offsetX >= CloudsCity[isRed ? 0 : 1].getWidth())
            offsetX = 0;
        
        this.isRed = isRed;
        
        int widthI = (int)this.width;
        int offsetXI = (int)offsetX;
        int sx2 = 1488 - widthI;
        
        graphics.drawImage(skySprite[isRed ? 0 : 1], 0, 0, null);
        
        graphics.drawImage(CloudsCity[isRed ? 0 : 1], 0, 0, widthI, 768, offsetXI, 0, 3200, 768, null);
        graphics.drawImage(clouds, widthI, 0, 1488, 768, 0, 0, sx2, 768, null);
        
        graphics.drawImage(CloudsCity[isRed ? 2 : 3], 0, -20, null);    // TODO: чтобы двигалось с игроком
        
        width -= 0.3;
        if (width < 0)
            width = 3200.0f;

//        for (int j = 0; j < Game.TILES_IN_HEIGHT; j++)
//            for (int i = 0; i < Game.TILES_IN_WIDTH; i++){
//                int index = level.getSpriteIndex(i, j);
//                graphics.drawImage(levelSprite[index], i * Game.TILES_SIZE, j * Game.TILES_SIZE, Game.TILES_SIZE, Game.TILES_SIZE, null);
//            }
            
    }
    
    public void update(){
    
    }
}
