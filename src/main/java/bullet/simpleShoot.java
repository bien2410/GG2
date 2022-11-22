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
public class simpleShoot extends Entity{
    
    public int range = 100;
    public boolean touch = false;
    public monster m;

    public simpleShoot(GamePanel gp, monster m) {
        super(gp);
        this.m = m;
      
        name = "simpleRock";
        worldX = m.worldX;
        worldY = m.worldY;
        speed = 5;
        direction = m.direction;
        image = setup("/bullet/da", gp.tileSize, gp.tileSize);
    }
    
    public void update(){
        range--;
        boolean b = gp.cChecker.checkPlayer(this);
        if(b) {
            gp.player.takeDame(m);
            
            touch = true;
        }      
        
        if(direction.equals("right")) worldX += speed;
        if(direction.equals("left")) worldX -= speed;
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
