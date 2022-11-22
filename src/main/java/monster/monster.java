/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monster;

/**
 *
 * @author ADMIN
 */
import bullet.*;
import entity.*;
import java.awt.*;
import java.util.*;
import nhom2.gg2.GamePanel;
public class monster extends Entity{
    public boolean alive = true;
    public boolean dying = false;
    public int dyingCounter = 0;
    public boolean hpBarOn = false;
    public int hpBarCounter = 0;
    
    public int dfX;
    public int dfY;
    public int atkSpeed;
    public String atkDirection = "";
    public String atkDirection2 = "";
    public boolean mode = false;
    
    public boolean checkAttack = false;
    public int counterAtk = 0;
    public Rectangle moveRange = new Rectangle();
    public int timeRevival = 1 * gp.FPS;
    public int counterRevival = timeRevival;
    public monsterBullet mb;
    public followBullet fb;
    public monster(GamePanel gp, int x, int y) {
        super(gp);
        dfX = x;
        dfY = y;
        worldX = dfX;
        worldY = dfY;
        
        moveRange.x = x - gp.tileSize * 3;
        moveRange.y = y - gp.tileSize * 2;
        moveRange.width = gp.tileSize * 7;
        moveRange.height = gp.tileSize * 3;
        
    }
    
    public void updateMoveRange(){
        moveRange.x = worldX - gp.tileSize * 3;
        moveRange.y = worldY - gp.tileSize * 2;
        moveRange.width = gp.tileSize * 7;
        moveRange.height = gp.tileSize * 3;
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
        
        actionLockCounter2++;
        
        if(actionLockCounter2 == 30){
            
            Random random = new Random();
            int i = random.nextInt(100) + 1; // random tu 1 -> 100
            
            if(i <= 50){
                direction2 = "up";
            }
            else if(i > 50){
                direction2 = "down";
            }
            
            actionLockCounter2 = 0;
        }
    }
    
    public void checkMove(){ // bo qua di
        if(worldX < moveRange.x){
            worldX = moveRange.x;
        }
        if(worldX > moveRange.x + moveRange.width){
            worldX = moveRange.x + moveRange.width;
        }
        if(worldY < moveRange.y){
            worldY = moveRange.y;
        }
        if(worldY > moveRange.y + moveRange.height){
            worldY = moveRange.y + moveRange.height;
        }
    }
    
    public void follow(){
        speed = 3;
        if(worldX + gp.tileSize - 20 < gp.player.worldX) direction = "right";
        else if(worldX - gp.tileSize + 20 > gp.player.worldX) direction = "left";
        else direction = "";
    }
    
    public void attacking(){
        if(direction != "") atkDirection = direction;
        counterAtk++;
        if(counterAtk >= atkSpeed){
            checkAttack = true;
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;
            if(atkDirection.equals("right")){
                solidArea.width += attackArea.width;
            }
            if(atkDirection.equals("left")){
                solidArea.width += attackArea.width;
                worldX -= attackArea.width;
            }
            boolean b = gp.cChecker.checkPlayer(this);
            if(b) gp.player.takeDame(this);
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if(counterAtk >= atkSpeed + 10){
            checkAttack = false;
            counterAtk = 0;
        }
    }
    
    public void shooting(int x, int y){
        
        mb = new monsterBullet(gp, this, x, y);
        
    }
    public void shooting1(int x, int y){
        
        fb = new followBullet(gp, this, x, y);
        
    }
    
    public void checkDie(){
        if(worldY > gp.tileSize * 47) alive = false;
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
    
}
