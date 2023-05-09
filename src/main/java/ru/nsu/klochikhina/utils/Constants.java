package ru.nsu.klochikhina.utils;

public class Constants {
    public static class Directions{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }
    public static class PLayerActions{
        public static final int IDLE = 0;       // standing still
        public static final int DEATH = 1;      // full lost of HP
        public static final int JUMP = 2;       // W or Up Arrow
        public static final int RUNNING = 3;    // <- or ->
        public static final int FALLING = 4;    // falling
        public static final int HIT = 5;        // getting hit
        public static final int ATTACK = 6;     // attack by pressing left mouse button
        public static final int TRANSFORM = 7;  // transform by pressing E
        
        public static int getSpriteAmount(int action){
            switch (action){
                case TRANSFORM:
                    return 34;
                case IDLE:
                    return 30;
                case DEATH:
                    return 20;
                case JUMP:
                    return 10;
                case RUNNING:
                case ATTACK:
                    return 6;
                case HIT:
                    return 5;
                case FALLING:
                default:
                    return 4;
            }
        }
        
    }
}
