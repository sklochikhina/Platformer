package ru.nsu.klochikhina.utils;

import ru.nsu.klochikhina.model.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {
    public static final String PLAYER_ATLAS = "img.png";
    public static final String PLAYER_ATTACK = "attack.png";
    public static final String PLAYER_TRANSFORM = "transform.png";
    public static final String LEVEL_SKY = "sky.png";
    public static final String LEVEL_BG = "background.png";
    public static final String LEVEL_ATLAS = "atlas.png";
    public static final String LEVEL_ONE_DATA = "level.png";
    
    public static BufferedImage getSpriteAtlas(String pictureName){
        BufferedImage image = null;
        InputStream stream = LoadSave.class.getResourceAsStream("/" + pictureName);
        
        try {
           image = ImageIO.read(stream);
        } catch (IOException e){
            System.out.println(e.getMessage());
        } finally {
            try {
                stream.close();
            } catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
        return image;
    }
    
    public static int[][] getLevelData(){
        int[][] levelData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        BufferedImage image = getSpriteAtlas(LEVEL_ONE_DATA);
        
        for(int j = 0; j < image.getHeight(); j++)
            for(int i = 0; j < image.getWidth(); i++) {
                Color color = new Color(image.getRGB(i, j));
                int value = color.getRed();
                if(value >= 48)
                    value = 0;
                levelData[j][i] = value;
            }
        return levelData;
    }
}
