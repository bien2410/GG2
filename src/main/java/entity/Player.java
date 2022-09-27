/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import nhom2.gg2.*;

/**
 *
 * @author ADMIN
 */
public class Player extends Entity{
    
    GamePanel gp;
    KeyHandler keyH;
    
    //vi tri cua player tren man hinh
    public final int screenX;
    public final int screenY;
    
    //Jump
    boolean jumping = false;
    boolean falling = false;
    int jumpStrength;
    int fallStrength = 0;
    int weight = 1;
    
    public Player(GamePanel gp, KeyHandler keyH){
        
        this.gp = gp;
        this.keyH = keyH;
        //player o giua man hinh -> sua lai sau
        screenX = gp.tileSize * 8;
        screenY = gp.tileSize * 10;
        
        //collision
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 31; // chinh sua ne
        
        setDefaultValues();
    }
    
    public void setDefaultValues(){
        worldX = gp.tileSize * 8;
        worldY = gp.tileSize * (gp.maxWorldRow - 6); // co 5 hang dat ben duoi
        speed = 5;
        direction = "right";
    }
    
    //nhap anh dau vao
    public void getPlayerImage(){
        try{
            // dien duong dan zo
            jump1 = ImageIO.read(getClass().getResourceAsStream(""));
            jump2 = ImageIO.read(getClass().getResourceAsStream(""));
            left1 = ImageIO.read(getClass().getResourceAsStream(""));
            left2 = ImageIO.read(getClass().getResourceAsStream(""));
            right1 = ImageIO.read(getClass().getResourceAsStream(""));
            right2 = ImageIO.read(getClass().getResourceAsStream(""));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    //floor
    public int floor(){
        int ans = worldY;
        while(ans % gp.tileSize != 0){
            ans--;
        }
        return ans;
    }
    
    //roof
    public int roof(){
        int ans = worldY;
       while(ans % gp.tileSize != 0){
            ans++;
        }
        return ans - solidArea.y; // hoat anh
    }
    
    // thao tac cua nhan vat
    public void update(){
        
        //tha roi tu do
        if(gp.cChecker.checkFloor(this) == false && jumping == false){
            
            falling = true;
        }
        if(keyH.leftPressed == true || keyH.rightPressed == true){ // hoat anh chi doi khi di chuyen
                
            if(keyH.leftPressed == true){
                direction = "left";
                
            }
            else if(keyH.rightPressed == true){
                direction = "right";
                
            }
            //collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(collisionOn == false){
                
                switch(direction){
                    case"left": worldX -= speed; break;
                    case"right": worldX += speed; break;
                }

            }

            /*spriteCounter++;
            if(spriteCounter > 10){ //cu 10/FPS s thay doi hoat anh 1 lan 
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }*/
        }
        
        if(keyH.upPressed == true){
            if(gp.cChecker.checkFloor(this)){ // co the doi thanh check Jump de cuoi may
                jumpStrength = 25;
                jumping = true;
            }
        }
        //jump
        if(jumping){
            worldY -= jumpStrength;
            jumpStrength -= weight;
            
            if(gp.cChecker.checkRoof(this)){ // check roof
                worldY = roof();
                jumpStrength = 0;
            }
            
            if(gp.cChecker.checkFloor(this) && jumpStrength <= 0){ //check floor
                worldY = floor();
                jumping = false;
            }
        }
        // roi tu do
        if(falling){
            worldY += fallStrength;
            fallStrength++;
            if(gp.cChecker.checkFloor(this)){
                worldY = floor();
                falling = false;
                fallStrength = 0;
            }
        }
    }
    
    // ve nhan vat
    public void draw(Graphics2D g2){
        
        g2.setColor(Color.white);
        
        g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
        
        /*sau nay ve nv o day
        BufferedImage image = null;
        switch(direction){
            case"left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case"right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        */
    }
}
