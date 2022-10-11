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
    
    public GamePanel gp;
    
    public String name;
    public int worldX, worldY; // vi tri cua player tren map(ko phai tren man hinh)
    
    public int speed;
    public int hp;
    
    //image
    public BufferedImage jump1, jump2, left1, left2, right1, right2, down1, down2, up1, up2; // anh di chuyen
    public BufferedImage attackLeft1, attackLeft2, attackRight1, attackRight2, shootLeft, shootRight; // anh tan cong
    public BufferedImage image; // anh object or tile,..
    public String direction = "right"; // huong quay mat
    
    // bien de chinh hoat anh
    public int spriteCounter = 0; // bien dem time doi anh
    public int spriteNum = 1; // bien doi anh
    public int actionLockCounter; //monster or npc di chuyen random
    
    //collision cua chinh entity
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false; //tile or object
    public boolean collisionOn = false; // player, npc, monster,... co the di chuyen
    
    //attack hit detection
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    
    public Entity(GamePanel gp) {
        this.gp = gp;
    }
    
    public void setAction(){ //monster or npc di chuyen di lai
        
    }
    
    public void speak(){ //npc
        
    }
    
    public BufferedImage setup(String imagePath, int width, int height){ //phong to anh thuong la tilesize * tilesize
       
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res" + imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);
        }
        catch(IOException e){
            e.printStackTrace();
        }
             
        return image;
    }
    
    public void update(){
        
    }
    
    public void draw(Graphics2D g2){
        
    }
}
