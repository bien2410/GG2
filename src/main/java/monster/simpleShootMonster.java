/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monster;

import bullet.*;
import java.awt.*;
import java.awt.image.*;
import nhom2.gg2.GamePanel;

/**
 *
 * @author ADMIN
 */
public class simpleShootMonster extends monster {
    public simpleShoot ss;
    public simpleShootMonster(GamePanel gp, int x, int y) {
        super(gp, x, y);
        
        name = "C";
        speed = 2;
        maxHp = 10;
        hp = maxHp;
        attack = 10;
        defense = 1;
        exp = 5;
        mode = true;
        atkSpeed = gp.FPS;
        attackArea.width = gp.tileSize;
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30 - 1;
        
        //bien de check collision xong chuyen ve
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        dirImgNumb=1;
        moveRange.x = x - gp.tileSize * 5;
        moveRange.y = y - gp.tileSize * 3;
        moveRange.width = gp.tileSize * 11;
        moveRange.height = gp.tileSize * 4;
        
        getImage();
    }
    
    public void update(){
        if(invincible == false){
            if(gp.player.worldX < worldX) direction = "left";
            else direction = "right";
            mode = gp.cChecker.detectPlayer(moveRange);
            counterAtk++;
            if(mode){

                if(counterAtk >= atkSpeed) {
                    ss = new simpleShoot(gp, this);
                    counterAtk = 0;
                }
            }
            if(counterAtk >= Integer.MAX_VALUE - 100){
                counterAtk = 0;
            }
            
        }
        if(ss != null){
            ss.update();
            if(ss.range < 0) ss = null;
            else if(ss.touch) ss = null;
        }
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 50){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
    
    public void draw(Graphics2D g2){
        
        if(ss != null){
            ss.draw(g2);
        }
        super.draw(g2);
        
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
            g2.drawString(name, screenX - 5, screenY - 30);
            
            g2.setColor(Color.red);
            g2.drawRect(screenX - gp.tileSize * 5, screenY - gp.tileSize * 3, gp.tileSize * 11, gp.tileSize * 4);
        }
    }
}
