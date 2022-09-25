/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhom2.gg2;


/**
 *
 * @author ADMIN
 */
import java.awt.*;
import javax.swing.*;
public class GamePanel extends JPanel implements Runnable{
    
    // cai dat man hinh
    final int originalTileSize = 16; // anh se la 16 pixels * 16 pixels
    final int scale = 3; // chi so phong to hinh anh khi vao game
    final int tileSize = originalTileSize * scale; // kich co hinh anh sau khi phong to
    // co the thay doi kich thuoc tuy vao anh dau vao
    
    final int maxScreenCol = 24;
    final int maxScreenRow = 16;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    //chia man hinh game thanh 24tileSize * 16tileSize
    
    //vi tri nhan vat mac dinh
    int playerX = 100;
    int playerY = 500;
    int playerSpeed = 4;
    
    //Jump
    boolean jumping = false;
    int floor = playerY;
    int jumpStrength;
    int weight = 1;
    
    
    //FPS 
    int FPS = 60;
    
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    
    public GamePanel(){
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //kich co cua GamePanel
        this.setBackground(Color.black); // mau cua nen 
        this.setDoubleBuffered(true); // cho phep su dung phuong thuc draw...
        // dau vao tu ban phim
        this.addKeyListener(keyH); 
        this.setFocusable(true);
    
    }

    
    public void startGameThread(){
        
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    @Override
    // vong lap game
    public void run() {
        // do tre tu vong lap nay den vong lap ke: FPS lan/s
        double drawInterval = 1000000000/FPS; 
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        //long timer = 0;
        //int drawCount = 0;
        while(gameThread != null){
            
            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime) / drawInterval;
            //timer += (currentTime - lastTime);
            lastTime = currentTime;
        
            if(delta >= 1){
                update();
            
                repaint();
                
                delta--;
                //drawCount++;
            }
            // print FPS
            /*if(timer >= 1000000000){
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }*/
            
        }
    }
    
    public void update(){
        if(keyH.upPressed == true){
            playerY -= playerSpeed;
        }
        if(keyH.downPressed == true){
            playerY += playerSpeed;
        }
        if(keyH.leftPressed == true){
            playerX -= playerSpeed;
        }
        if(keyH.rightPressed == true){
            playerX += playerSpeed;
        }
        if(keyH.spacePressed == true){
            if(playerY >= floor){
                jumpStrength = 24;
                jumping = true;
            }
        }
        if(jumping){
            playerY -= jumpStrength;
            jumpStrength -= weight;
            if(playerY >= floor){
                playerY = floor;
                jumping = false;
            }
        }
        
    }
    
    public void paintComponent(Graphics g){ // subclass of JPanel
        
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
        
        g2.setColor(Color.white);
        
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        
        g2.dispose();
    }
}
