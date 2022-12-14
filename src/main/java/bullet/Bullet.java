/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bullet;

import entity.*;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import nhom2.gg2.GamePanel;

/**
 *
 * @author ADMIN
 */
public class Bullet extends Entity{
    public int limitX;
    public boolean touch = false;
    public Bullet(GamePanel gp) {
        super(gp);
        worldX = gp.player.worldX;
        worldY = gp.player.worldY;
        speed = 7;
        attack = 1;
        mana = 10;
        direction = gp.player.direction;
        if(direction.equals("right")){
            limitX = worldX + gp.tileSize * 10;
        }
        if(direction.equals("left")){
            limitX = worldX - gp.tileSize * 10;
        }
        image = setup("/bullet/phitieu", gp.tileSize, gp.tileSize);
    }
    public void update(){
        int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            if(monsterIndex != 999){
                touch = true;
                gp.player.damageMonster(monsterIndex, "shoot");
            
        }
        if(direction.equals("right")){
            worldX += speed;
            if(worldX > limitX) worldX = limitX;
            
        }
        if(direction.equals("left")){
            worldX -= speed;
            if(worldX < limitX) worldX = limitX;
        }
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
