package ru.nsu.klochikhina.entities;

import ru.nsu.klochikhina.utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static ru.nsu.klochikhina.utils.Constants.PLayerActions.*;

public class Player extends Entity{
    private BufferedImage[][] animations;
    private BufferedImage[][] attack;
    private BufferedImage[][] transformation;
    
    private int aniTick = 0;
    private int aniIndex = 0, attIndex = 0, transIndex = 0;
    private int action = TRANSFORM;
    private boolean isMoving = false, isAttacking = false;
    private boolean isLeft, isRight, isUp, isDown, isRed = true;
    
    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }
    
    public void update(){
        updatePosition();
        updateAnimationTick();
        setAnimation();
    }
    
    public void render(Graphics graphics){
        if(action >= 0 && action <= 5)
            graphics.drawImage(animations[action + (isRed ? 0 : 1)][aniIndex], (int)x, (int)y, 128, 128, null);
        else if(action == 6)
            graphics.drawImage(attack[isRed ? 0 : 1][attIndex], (int)x, (int)y, 240, 240, null);
        else {
            graphics.drawImage(transformation[isRed ? 1 : 0][transIndex], (int) x - 55, (int) y - 128, 240, 240, null);
            action = IDLE;
        }
    }
    
    private void updateAnimationTick() {
        aniTick++;
        
        int aniSpeed = 14;
        
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            
            aniIndex++;
            attIndex++;
            transIndex++;
            
            int amount = getSpriteAmount(action);
            
            if (aniIndex >= amount) {
                aniIndex = 0;
                isAttacking = false;
            }
            if (attIndex >= amount){
                attIndex = 0;
                isAttacking = false;
            }
            if (transIndex >= amount){
                transIndex = 0;
                isAttacking = false;
            }
        }
    }
    
    private void setAnimation() {
        int start = action;
        
        if (isMoving)
            action = RUNNING;
        else if (isUp)
            action = JUMP;
        else if(!isRed) {
            action = TRANSFORM;
            isRed = true;
        }
        else
            action = IDLE;
        
        if(isAttacking)
            action = ATTACK;
        
        if(start != action)
            resetAniTick();
    }
    
    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }
    
    private void updatePosition() {
        isMoving = false;
        
        float speed = 2.5f;
        
        if(isLeft && !isRight) {
            x -= speed;
            isMoving = true;
        }
        else if (isRight && !isLeft){
            x += speed;
            isMoving = true;
        }
        
        if(isUp && !isDown){
            y -= speed;
            isMoving = true;
        }
        else if (isDown && !isUp) {
            y += speed;
            isMoving = true;
        }
        
    }
    
    private void loadAnimations() {
        BufferedImage image = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);
        BufferedImage attacking = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATTACK);
        BufferedImage transform = LoadSave.getSpriteAtlas(LoadSave.PLAYER_TRANSFORM);
        
        int animationWidthHeight = 32;
        int attackWidthHeight = 60;
        int transformWidthHeight = 70;
        
        animations = new BufferedImage[12][20];
        attack = new BufferedImage[2][6];
        transformation = new BufferedImage[2][34];
        
        fillBuffer(animations, image, animationWidthHeight);
        fillBuffer(attack, attacking, attackWidthHeight);
        fillBuffer(transformation, transform, transformWidthHeight);
    }
    
    private void fillBuffer(BufferedImage[][] anis, BufferedImage image, int width_height){
        for(int i = 0; i < anis.length; i++)
            for (int j = 0; j < anis[i].length; j++)
                anis[i][j] = image.getSubimage(j * width_height, i * width_height, width_height, width_height);
    }
    
    public void resetDirBooleans() {
        isLeft = false;
        isUp = false;
        isDown = false;
        isRight = false;
    }
    
    public void setAttacking(boolean attacking) {
        isAttacking = attacking;
    }
    
    public void setLeft(boolean left) {
        isLeft = left;
    }
    
    public void setRight(boolean right) {
        isRight = right;
    }
    
    public void setUp(boolean up) {
        isUp = up;
    }
    
    public void setDown(boolean down) {
        isDown = down;
    }
    
    public void changeCharacter() {
        isRed = !isRed;
    }
    
    public boolean isLeft() {
        return isLeft;
    }
    
    public boolean isRight() {
        return isRight;
    }
    
    public boolean isUp() {
        return isUp;
    }
    
    public boolean isDown() {
        return isDown;
    }
    
    public boolean isRed() {
        return isRed;
    }
}
