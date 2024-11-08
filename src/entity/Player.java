package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler){
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){

        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {

        try {

            up0 = ImageIO.read(getClass().getResourceAsStream("/player/up0.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));
            down0 = ImageIO.read(getClass().getResourceAsStream("/player/down0.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/down2.png"));
            left0 = ImageIO.read(getClass().getResourceAsStream("/player/left0.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
            right0 = ImageIO.read(getClass().getResourceAsStream("/player/right0.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));

        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public void update(){

        moving = true;

        if (keyHandler.upPressed == true ||
                keyHandler.downPressed == true ||
                keyHandler.leftPressed == true ||
                keyHandler.rightPressed == true) {

            moving = true;

            if(keyHandler.upPressed == true){
                direction = "up";
                y -= speed;
            } else if (keyHandler.downPressed == true){
                direction = "down";
                y += speed;
            } else if (keyHandler.leftPressed == true){
                direction = "left";
                x -= speed;
            } else if (keyHandler.rightPressed == true) {
                direction = "right";
                x += speed;
            }

            spriteCounter++;
            if (spriteCounter > 12){
                if (spriteNum == 1){
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {
            moving = false;
        }
    }
    public void draw(Graphics2D g2){

        BufferedImage image = null;

        switch (direction){
            case "up":
                if (moving == false){
                    image = up0;
                } else {
                    if (spriteNum == 1){
                        image = up1;
                    }
                    if (spriteNum == 2){
                        image = up2;
                    }
                }
                break;

            case "down":
                if (moving == false){
                    image = down0;
                } else {
                    if (spriteNum == 1){
                        image = down1;
                    }
                    if (spriteNum == 2){
                        image = down2;
                    }
                }
                break;

            case "left":
                if (moving == false){
                    image = left0;
                } else {
                    if (spriteNum == 1){
                        image = left1;
                    }
                    if (spriteNum == 2){
                        image = left2;
                    }
                }
                break;

            case "right":
                if (moving == false){
                    image = right0;
                } else {
                    if (spriteNum == 1){
                        image = right1;
                    }
                    if (spriteNum == 2){
                        image = right2;
                    }
                }
                break;
        }
        g2.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
