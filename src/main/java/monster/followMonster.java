/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monster;

import java.awt.*;
import java.awt.image.*;
import nhom2.gg2.*;

/**
 *
 * @author ADMIN
 */
public class followMonster extends monster{

    public followMonster(GamePanel gp, int x, int y) {
        super(gp, x, y);
        
        name = "Triết";
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
    
    public void update(){
        if(invincible == false){
            mode = gp.cChecker.detectPlayer(moveRange);
            if(mode == false) {
                speed = 2;
                setAction();
            }
            else{
                follow();
            }
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
            updateMoveRange();
            checkMove();
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
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 50){
                invincible = false;
                invincibleCounter = 0;
            }
        }
        checkDie();
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
            default:
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
            
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(10f));
            g2.drawString(name, screenX + 13, screenY - 30);
            
            g2.setColor(Color.red);
            g2.drawRect(screenX - gp.tileSize * 3, screenY - gp.tileSize * 2, gp.tileSize * 7, gp.tileSize * 3);
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
