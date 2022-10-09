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
//class bo cua player, npc, monster,...
public class Entity {
    
    public String name;
    public int worldX, worldY; // vi tri cua player tren map(ko phai tren man hinh)
    public int speed;
    public int hp;
    
    public GamePanel gp;
    public BufferedImage jump1, jump2, left1, left2, right1, right2;
    public BufferedImage attackLeft1, attackLeft2, attackRight1, attackRight2;
    public String direction = "right"; // huong quay mat
    
    // bien de chinh hoat anh
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int actionLockCounter;
    
    //collision
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    
    //attack hit detection
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);

    public Entity(GamePanel gp) {
        this.gp = gp;
    }
    
    public BufferedImage setup(String imageName, int width, int height){ //gp.tileSize
       
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res" + imageName + ".png"));
            image = uTool.scaleImage(image, width, height);
        }
        catch(IOException e){
            e.printStackTrace();
        }
             
        return image;
    }
}
