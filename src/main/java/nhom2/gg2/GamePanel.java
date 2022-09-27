/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhom2.gg2;


/**
 *
 * @author ADMIN
 */
import entity.*;
import java.awt.*;
import javax.swing.*;
import tile.*;
public class GamePanel extends JPanel implements Runnable{
    
    // cai dat man hinh
    final int originalTileSize = 16; // anh se la 16 pixels * 16 pixels
    final int scale = 3; // chi so phong to hinh anh khi vao game
    public final int tileSize = originalTileSize * scale; // kich co hinh anh sau khi phong to
    // co the thay doi kich thuoc tuy vao anh dau vao
    
    public final int maxScreenCol = 24;
    public final int maxScreenRow = 16;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    //chia man hinh game thanh 24tileSize * 16tileSize
    
    // World setting
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWitdth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    
    //FPS 
    int FPS = 60;
    
    //tile
    TileManager tileM = new TileManager(this);
    //keyHandler
    KeyHandler keyH = new KeyHandler();
    //game
    Thread gameThread;
    //player
    public Player player = new Player(this, keyH);
    
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
        
        //update nhan vat
        player.update();
      
    }
    
    public void paintComponent(Graphics g){ // subclass of JPanel
        
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
        
        //ve tile trc khi ve nhan vat
        tileM.draw(g2);
        
        //ve nhan vat
        player.draw(g2);
        
        g2.dispose();
    }
}
