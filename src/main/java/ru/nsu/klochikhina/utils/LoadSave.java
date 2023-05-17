package ru.nsu.klochikhina.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {
    public static final String PLAYER_ATLAS = "img.png";
    public static final String PLAYER_ATTACK = "attack.png";
    public static final String PLAYER_TRANSFORM = "transform.png";
    public static final String LEVEL_ATLAS = "level.png";
    
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
}
