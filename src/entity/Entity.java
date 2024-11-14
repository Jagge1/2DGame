package entity;

import java.awt.image.BufferedImage;


public class Entity {

    public int worldX, worldY;
    public int speed;

    public BufferedImage up0, up1, up2, down0, down1, down2,left0, left1, left2, right0, right1, right2;
    public String direction;
    public boolean moving;

    public int spriteCounter = 0;
    public int spriteNum = 1;


}
