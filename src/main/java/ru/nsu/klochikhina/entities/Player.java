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
    private boolean isLeft, isRight, isUp, isDown, isRed = true, needToTransform = false;
    
    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
    }
    
    public void update(){
        updatePosition();
        updateAnimationTick();
        setAnimation();
    }
    
    public void render(Graphics graphics){
        if(action >= IDLE && action <= HIT)
            graphics.drawImage(animations[action * 2 + (isRed ? 0 : 1)][aniIndex], (int)x, (int)y, 192, 192, null);
        else if(action == ATTACK)
            graphics.drawImage(attack[isRed ? 0 : 1][attIndex], (int)(x - (5.0 * 1.5)), (int)(y - (112.0 * 1.5)), 360, 360, null);
        else if (action == TRANSFORM){
            graphics.drawImage(transformation[isRed ? 1 : 0][transIndex], (int) (x - (72.0 * 1.5)), (int) (y - (152.0 * 1.5)), 420, 420, null);
            if (transIndex == transformation[0].length - 1)
                needToTransform = false;
        }
    }
    
    private void updateAnimationTick() {
        aniTick++;
        
        int aniSpeed = 10;
        
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
        else
            action = IDLE;
        
        if (isUp)
            action = JUMP;
        else if(needToTransform)
            action = TRANSFORM;
        else if(isLeft || isRight)
            action = RUNNING;
        
        if(isAttacking)
            action = ATTACK;
        
        if(start != action)
            resetAniTick();
    }
    
    private void resetAniTick() {
        aniTick = 0;
        
        aniIndex = 0;
        attIndex = 0;
        transIndex = 0;
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
        
        animations = new BufferedImage[12][30];
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
        needToTransform = true;
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
