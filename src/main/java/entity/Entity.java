/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
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
    public int maxHp;
    public int mana;
    public int maxMana;
    public int manaCounter = 0;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Entity currentWeapon;
    public Entity currentShield;
    
    public int attackValue;
    public int defenseValue;
    public String description = "";
    //TYPE ITEM
    public int type;
    public final int type_weapon = 1;
    public final int type_shield = 2;
    public final int type_consumable = 3;
    
    /*public boolean alive = true;
    public boolean dying = false;
    public int dyingCounter = 0;
    public boolean hpBarOn = false;
    public int hpBarCounter = 0;*/
    
    //image
    public BufferedImage jump1, jump2, left1, left2, right1, right2, down1, down2, up1, up2; // anh di chuyen
    public BufferedImage attackLeft, attackRight, shootLeft, shootRight; // anh tan cong
    public BufferedImage image; // anh object or tile,..
    public ArrayList<BufferedImage> left,right,attLeft,attRight,jumpLeft,jumpRight; 
    
    public String direction = "right"; // huong quay mat
    public String direction2 = "";
    // bien de chinh hoat anh
    public int spriteCounter = 0; // bien dem time doi anh
    public int spriteNum = 1; // bien doi anh
    public int spriteCounterAttack = 0; // bien dem time doi anh
    public int spriteNumAttack = 1; // bien doi anh
    public int actionLockCounter; //monster or npc di chuyen random
    public int actionLockCounter2;
    public boolean invincible = false;
    public int invincibleCounter = 0;
    public boolean invincibleAnimation = true;
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
    
    public void damageReaction(){ //tu chay khi nhan dame, tu danh khi nhan dame,...
       
    }
    
    public void speak(){ //npc
        if(!gp.dia.isEmpty()){
            gp.ui.currentDialogue = gp.dia.poll();
            if(gp.dia.size() == 0){
                gp.player.speaking = false;
            }
        }
        else{
            gp.ui.currentDialogue = "Còn không mau đi làm nhiệm vụ";
            gp.player.speaking = false;
        }
    }
    
    public void use(Entity entity){
        
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
