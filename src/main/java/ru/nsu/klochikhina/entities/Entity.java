package ru.nsu.klochikhina.entities;

import java.awt.*;

public abstract class Entity {
    protected float x, y;
    protected int width, height;
    protected Rectangle hitbox;
    
    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
//        initHitbox();
    }
//
//    protected void drawHitbox(Graphics graphics){
//        graphics.setColor(Color.PINK);
//        graphics.drawRect(hitbox.x + 31, hitbox.y + 35, hitbox.width, hitbox.height);
//    }
//
//    private void initHitbox(){
//        hitbox = new Rectangle((int)x, (int)y, width, height);
//
//    }
//
//    protected void updateHitbox(){
//        hitbox.x = (int)x;
//        hitbox.y = (int)y;
//
//    }
//
//    public Rectangle getHitbox() {
//        return hitbox;
//    }
}
