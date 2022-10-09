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
    
    //attack
    int attackNum = 0;
    boolean attacking = false;
    
    //shoot
    boolean shooting = false;
    public Player(GamePanel gp, KeyHandler keyH){
        
        super(gp);
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
        
        //attack hit detection, chinh sua sau nay tuy vu khi
        attackArea.width = gp.tileSize * 2;
        attackArea.height = gp.tileSize * 2;
        
        setDefaultValues();
        //getPlayerImage();
        //getPlayerAttackImage();
    }
    
    public void setDefaultValues(){
        worldX = gp.tileSize * 8;
        worldY = gp.tileSize * (gp.maxWorldRow - 6); // co 5 hang dat ben duoi
        speed = 5;
        hp = 100;
        direction = "right";
    }
    
    //nhap anh dau vao
    public void getPlayerImage(){
        //dien duong dan
        jump1 = setup("", gp.tileSize, gp.tileSize);
        jump2 = setup("", gp.tileSize, gp.tileSize);
        left1 = setup("", gp.tileSize, gp.tileSize);
        left2 = setup("", gp.tileSize, gp.tileSize);
        right1 = setup("", gp.tileSize, gp.tileSize);
        right2 = setup("", gp.tileSize, gp.tileSize);
       
    }
    public void getPlayerAttackImage(){ // change weapon 16*32
        
        attackLeft1 = setup("", gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setup("", gp.tileSize * 2, gp.tileSize);
        attackRight1 = setup("", gp.tileSize * 2, gp.tileSize);
        attackRight2 = setup("", gp.tileSize * 2, gp.tileSize);
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
        
        //check co dang ban ko
        if(shooting == true && gp.bullet == null){
            shooting = false;
        }
        //check object collision
        int objIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(objIndex);
        //tha roi tu do
        if(gp.cChecker.checkFloor(this) == false && jumping == false){
            
            falling = true;
        }
        if(keyH.jPressed == true && attacking == false){
            attacking = true;
            if(jumping == false && falling == false) attackNum = 1; //chem thuong
            else attackNum = 2; // nhay chem
        }
        if(keyH.kPressed == true && attacking == false && shooting == false){
            shooting = true;
            gp.bullet = new Bullet(gp);
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
        
        //attack
        if(attacking == true){
            attacking();
        }
    }
    
    public void attacking(){
        //doi hoat anh, sua lai sau
        spriteCounter++;
        
        if(spriteCounter <= 5){
            spriteNum = 1;
        }
        if(spriteCounter > 5 && spriteCounter <= 10){
            spriteNum = 2;
            
            //save the current worldX, worldY, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;
            
            // Adjust player's worldX,Y for the attackArea
            if(attackNum == 1){
                if(direction.equals("right")){
                    solidArea.width += attackArea.width;
                }
                if(direction.equals("left")){
                    solidArea.width += attackArea.width;
                    worldX -= attackArea.width;
                }
            }
            if(attackNum == 2){
                if(direction.equals("right")){
                    worldY -= attackArea.height;
                    solidArea.width += attackArea.width;
                    solidArea.height += attackArea.height * 2;
                }
                if(direction.equals("left")){
                    worldY -= attackArea.height;
                    solidArea.width += attackArea.width;
                    solidArea.height += attackArea.height * 2;
                    worldX -= attackArea.width;
                }
            }
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            interacMonster(monsterIndex);
            
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if(spriteCounter > 10){
            spriteNum = 1;
            if(spriteCounter > 60){
                spriteCounter = 0;
                attacking = false;
                attackNum = 0;
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
    
    //tuong tac monster
    public void interacMonster(int i){
        if(i != 999){
            
            gp.ui.showMessage("chem trung r");
        }
    }
    
    // ve nhan vat
    public void draw(Graphics2D g2){
        
        g2.setColor(Color.white);
        
        g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
        
        //ve thanh hp
        g2.setColor(Color.red);
        g2.fillRect(gp.tileSize * 1, gp.tileSize * 2, hp * 3, gp.tileSize / 2);
        
        //ve demo
        if(attacking == true){
            if(attackNum == 1){
                if("right".equals(direction)){
                    g2.setColor(Color.orange);
                    g2.drawRect(screenX + gp.tileSize, screenY, gp.tileSize * 2, gp.tileSize);
                }
                if("left".equals(direction)){
                    g2.setColor(Color.orange);
                    g2.drawRect(screenX - gp.tileSize * 2, screenY, gp.tileSize * 2, gp.tileSize);
                }
            }
            if(attackNum == 2){
                if("right".equals(direction)){
                    g2.setColor(Color.orange);
                    g2.drawRect(screenX, screenY - gp.tileSize * 2, gp.tileSize * 3, gp.tileSize * 5);
                }
                if("left".equals(direction)){
                    g2.setColor(Color.orange);
                    g2.drawRect(screenX - gp.tileSize * 2, screenY - gp.tileSize * 2, gp.tileSize * 3, gp.tileSize * 5);
                }
            }
        }
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
