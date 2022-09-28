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
    public int hasKey = 0;
    
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
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
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
        //dien duong dan
        jump1 = setup("");
        jump2 = setup("");
        left1 = setup("");
        left2 = setup("");
        right1 = setup("");
        right2 = setup("");
       
    }
    
    public BufferedImage setup(String imageName){
       
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/player" + imageName + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }
        catch(IOException e){
            e.printStackTrace();
        }
             
        return image;
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
        //check object collision
        int objIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(objIndex);
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
    
    //tuong tac object
    public void pickUpObject(int i){
       if(i != 999){
           
           String objectName = gp.obj[i].name;
           switch(objectName){
               case"Key":
                   hasKey++;
                   gp.obj[i] = null;
                   gp.playSE(1);
                   gp.ui.showMessage("nhat dc chia khoa");
                   break;
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
        g2.drawImage(image, screenX, screenY, null);
        */
    }
}
