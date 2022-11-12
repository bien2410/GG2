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
        speed = 2; 
        maxHp = 10;
        hp = maxHp;
        attack = 5;
        defense = 0;
        exp = 3;
        
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30 - 1;
        //bien de check collision xong chuyen ve
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        getImage();
    }
      
    public void getImage(){
        
        left1 = setup("/monster/anh1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/anh2", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/anh1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/anh2", gp.tileSize, gp.tileSize);
    }
    
    public void setAction(){
        
        actionLockCounter++;
        
        if(actionLockCounter == 30){
            
            Random random = new Random();
            int i = random.nextInt(100) + 1; // random tu 1 -> 100
            
            if(i <= 50){
                direction = "right";
            }
            else if(i > 50){
                direction = "left";
            }
            
            actionLockCounter = 0;
        }
    }
    
    public void damageReaction(){
        
        actionLockCounter = 0;
        direction = gp.player.direction;
    }
    //demo
    public void update(){
        setAction();
        collisionOn = false;
        gp.cChecker.checkTile(this);
        //roi
        if(gp.cChecker.checkFloor(this) == false) {
            collisionOn = true;
            worldY++;
        }
        if(collisionOn == false){
            if(direction.equals("right")) worldX += speed;
            if(direction.equals("left")) worldX -= speed;
        }
       
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
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 50){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
    
    public void dyingAnimation(Graphics2D g2){
        
        dyingCounter++;
        
        int i = 5;
        
        if(dyingCounter <= i) changeAlpha(g2, 0f);
        if(dyingCounter > i && dyingCounter <= i * 2) changeAlpha(g2, 1f);    
        if(dyingCounter > i * 2 && dyingCounter <= i * 3) changeAlpha(g2, 0f);
        if(dyingCounter > i * 3 && dyingCounter <= i * 4) changeAlpha(g2, 1f);
        if(dyingCounter > i * 4 && dyingCounter <= i * 5) changeAlpha(g2, 0f);
        if(dyingCounter > i * 5 && dyingCounter <= i * 6) changeAlpha(g2, 1f);
        if(dyingCounter > i * 6 && dyingCounter <= i * 7) changeAlpha(g2, 0f);
        if(dyingCounter > i * 7 && dyingCounter <= i * 8) changeAlpha(g2, 1f);
        
        if(dyingCounter > i * 8){
            dying = false;
            alive = false;
        }
    }
    
    public void changeAlpha(Graphics2D g2, float alphaValue){ // chinh do mo
        
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
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
            
            //Monster hp bar
            
            double oneScale = (double)gp.tileSize / maxHp;
            double hpBarValue = oneScale * hp;
            if(hpBarOn == true){
                g2.setColor(new Color(35,35,35));
                g2.fillRect(screenX - 1, screenY - 16, gp.tileSize + 2, 12);

                g2.setColor(new Color(255,0,30));
                g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);
                
                hpBarCounter++;
                if(hpBarCounter > 600){
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }
            if(invincible){
                hpBarOn = true;
                hpBarCounter = 0;
                if(invincibleAnimation == true){
                    image = null;
                    invincibleAnimation = false;
                }
                else{
                    invincibleAnimation = true;
                }
            }
            if(dying == true){
                dyingAnimation(g2);
            }
           g2.drawImage(image, screenX, screenY, null);  
           changeAlpha(g2, 1f);
        }
   
    }
}
