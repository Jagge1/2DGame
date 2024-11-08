package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //Screen settings
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public int tileSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels


    int FPS = 60;

    KeyHandler keyHandler = new KeyHandler();

    Thread gameThread;

    //Setting the players default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    Player player = new Player(this, keyHandler);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
//        SLEEP GAME LOOP
//        double drawInterval = 1000000000/FPS; // 0.01666 sec
//        double nextDrawTime = System.nanoTime() + drawInterval;
//
//        while (gameThread != null){
//
//            // 1. Update information (character positions)
//            update();
//
//            // 2. Draw the screen
//            //paintComponent method is being called through repaint
//            repaint();
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime/1000000; // Converting to milliseconds
//
//                if (remainingTime < 0){
//                    remainingTime = 0;
//                }
//
//                Thread.sleep((long) remainingTime); // Pauses the game loop
//
//                nextDrawTime += drawInterval;
//
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//
//
//        }

        // DELTA GAME LOOP
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();

                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update(){

        player.update();

    }
    //JPanel method
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        //Changed Graphics g to Graphics2D g2 for extra functionality
        Graphics2D g2 = (Graphics2D)g;

        player.draw(g2);
        g2.dispose();
    }
}
