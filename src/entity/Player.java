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

    public final int screenX;
    public final int screenY;
    public int sprintSpeed;

    public Player(GamePanel gamePanel, KeyHandler keyHandler){
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setDefaultValues();
        getPlayerImage();

        screenX = gamePanel.screenWidth/2 - (gamePanel.tileSize/2);
        screenY = gamePanel.screenHeight/2 - (gamePanel.tileSize/2);
    }

    public void setDefaultValues(){

        worldX = gamePanel.tileSize * 23;
        worldY = gamePanel.tileSize * 21;
        speed = 3;
        sprintSpeed = 4;
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

        int spriteCounterWalking = 14;


        if (keyHandler.upPressed == true ||
                keyHandler.downPressed == true ||
                keyHandler.leftPressed == true ||
                keyHandler.rightPressed == true) {

            moving = true;

            //Walking logic
            if(keyHandler.upPressed == true){
                direction = "up";
                worldY -= speed;
            } else if (keyHandler.downPressed == true){
                direction = "down";
                worldY += speed;
            } else if (keyHandler.leftPressed == true){
                direction = "left";
                worldX -= speed;
            } else if (keyHandler.rightPressed == true) {
                direction = "right";
                worldX += speed;
            }

            //Sprint logic
            if(keyHandler.upPressed == true && keyHandler.lShiftPressed == true){
                direction = "up";
                worldY -= sprintSpeed;
                spriteCounterWalking = 8;
            } else if (keyHandler.downPressed == true && keyHandler.lShiftPressed == true){
                direction = "down";
                worldY += sprintSpeed;
                spriteCounterWalking = 8;
            } else if (keyHandler.leftPressed == true && keyHandler.lShiftPressed == true){
                direction = "left";
                worldX -= sprintSpeed;
                spriteCounterWalking = 8;
            } else if (keyHandler.rightPressed == true && keyHandler.lShiftPressed == true) {
                direction = "right";
                worldX += sprintSpeed;
                spriteCounterWalking = 8;
            }



            spriteCounter++;
            if (spriteCounter > spriteCounterWalking){
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
        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
