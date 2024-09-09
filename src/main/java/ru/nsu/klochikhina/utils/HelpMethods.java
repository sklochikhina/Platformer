package ru.nsu.klochikhina.utils;

import ru.nsu.klochikhina.model.Game;

public class HelpMethods {
    public static boolean canMoveHere(float x, float y, int width, int height, int[][] levelData){
        if(isSolid(x, y, levelData))
            if(!isSolid(x + width, y + height, levelData))
                if(!isSolid(x + width, y, levelData))
                    return !isSolid(x, y + height, levelData);
        return false;
    }
    
    public static boolean isSolid(float x, float y, int[][] levelData){
        if(x < 0 || x >= Game.GAME_WIDTH)
            return true;
        if(y < 0 || y >= Game.GAME_HEIGHT)
            return true;
        
        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;
        
        int value = levelData[(int)xIndex][(int)yIndex];
        
        return value >= 48 || value < 0 || value == 11;
    }
}
