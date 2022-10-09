/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monster;

import entity.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;
import nhom2.gg2.*;

/**
 *
 * @author ADMIN
 */
public class MON_GreenSlime extends Entity{

    public MON_GreenSlime(GamePanel gp) {
        super(gp);
        
        name = "Green Slime";
        speed = 1;
        hp = 4;
        
        solidArea = new Rectangle();
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        getImage();
    }
      
    public void getImage(){
        
        left1 = setup("/monster/anh1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/anh2", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/anh1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/anh2", gp.tileSize, gp.tileSize);
        jump1 = setup("/objects/key", gp.tileSize, gp.tileSize);
    }
    
    public void setAction(){
        
        actionLockCounter++;
        
        if(actionLockCounter == 30){
            
            Random random = new Random();
            int i = random.nextInt(100) + 1; // random tu 1 -> 100
            speed = 4;
            if(i <= 50){
                direction = "right";
            }
            else if(i > 50){
                direction = "left";
            }
            
            actionLockCounter = 0;
        }
    }
    //demo
    public void update(){
        setAction();
        if(direction.equals("right")) worldX += speed;
        if(direction.equals("left")) worldX -= speed;
        speed = 0;
        spriteCounter++;
        if(spriteCounter > 30){ //cu 10/FPS s thay doi hoat anh 1 lan 
            if(spriteNum == 1){
                spriteNum = 2;
            }
            else if(spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
    
    public void draw(Graphics2D g2){
        
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
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        
        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX < gp.player.worldX + (gp.screenWidth - gp.player.screenX) &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY < gp.player.worldY + (gp.screenHeight - gp.player.screenY)){
            
           g2.drawImage(image, screenX, screenY, null);   
        }
        //g2.drawImage(image, worldX, worldY, null);
    }
}
