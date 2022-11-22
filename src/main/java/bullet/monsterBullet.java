/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bullet;

import entity.*;
import java.awt.*;
import monster.*;
import nhom2.gg2.*;


/**
 *
 * @author ADMIN
 */
public class monsterBullet extends Entity{
    
    public int range = 100;
    public boolean touch = false;
    public monster m;
    public int x, y;
    public double speedX, speedY;
    public monsterBullet(GamePanel gp, monster m, int x, int y) {
        super(gp);
        this.m = m;
        this.x = x;
        this.y = y;
       
        name = "Rock";
        worldX = m.worldX;
        worldY = m.worldY;
        
        setDirection();
        xuly();
        image = setup("/bullet/da", gp.tileSize, gp.tileSize);
    }
    
    public void set(int x, int y){
        this.x = x;
        this.y = y;
        setDirection();
    }
    
    public void setDirection(){
        if(worldX < x) direction = "right";
        else if(worldX > x) direction = "left";
        else direction = "";
        if(worldY < y) direction2 = "down";
        else if(worldY > y) direction2 = "up";
        else direction2 = "";
    }
    
    public void xuly(){
        int tmp1 = Math.abs(x - worldX);
        int tmp2 = Math.abs(y - worldY);
        double tmp3;
        if(tmp1 > tmp2) {
            this.speedX = 5;
            tmp3 = (double)tmp2 * 5 / (double)tmp1;
            this.speedY = tmp3;
        }
        else {
            this.speedY = 5;
            tmp3 = (double)tmp1 * 5 / (double)tmp2;
            this.speedX = tmp3;
        }
        
    }
    
    public void update(){
        range--;
        boolean b = gp.cChecker.checkPlayer(this);
        if(b) {
            gp.player.takeDame(m);
            
            touch = true;
        }      
        
        if(direction.equals("right")) worldX += speedX;
        if(direction.equals("left")) worldX -= speedX;
        if(direction2.equals("up")) worldY -= speedY;
        if(direction2.equals("down")) worldY += speedY;
    }
    
    public void draw(Graphics2D g2){
        
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        
        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX < gp.player.worldX + (gp.screenWidth - gp.player.screenX) &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY < gp.player.worldY + (gp.screenHeight - gp.player.screenY)){
            
           g2.drawImage(image, screenX, screenY, null);   
        }
    }
}
