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
public class Player extends Entity{
    
    GamePanel gp;
    KeyHandler keyH;
    
    //Jump
    boolean jumping = false;
    int floor;
    int jumpStrength;
    int weight = 1;
    
    public Player(GamePanel gp, KeyHandler keyH){
        
        this.gp = gp;
        this.keyH = keyH;
        
        setDefaultValues();
    }
    
    public void setDefaultValues(){
        x = 100;
        y = 500;
        speed = 4;
        floor = y;
        direction = "right";
    }
    
    //nhap anh dau vao
    public void getPlayerImage(){
        try{
            // dien duong dan zo
            jump1 = ImageIO.read(getClass().getResourceAsStream(""));
            jump2 = ImageIO.read(getClass().getResourceAsStream(""));
            left1 = ImageIO.read(getClass().getResourceAsStream(""));
            left2 = ImageIO.read(getClass().getResourceAsStream(""));
            right1 = ImageIO.read(getClass().getResourceAsStream(""));
            right2 = ImageIO.read(getClass().getResourceAsStream(""));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    // thao tac cua nhan vat
    public void update(){
        
        if(keyH.leftPressed == true || keyH.rightPressed == true){ // hoat anh chi doi khi di chuyen
                
            if(keyH.leftPressed == true){
                direction = "left";
                x -= speed;
            }
            else if(keyH.rightPressed == true){
                direction = "right";
                x += speed;
            }
        }
        if(keyH.upPressed == true){
            if(y >= floor){
                jumpStrength = 24;
                jumping = true;
            }
        }
        if(jumping){
            y -= jumpStrength;
            jumpStrength -= weight;
            if(y >= floor){
                y = floor;
                jumping = false;
            }
        }
        
        /*spriteCounter++;
        if(spriteCounter > 10){ //cu 10/FPS s thay doi hoat anh 1 lan 
            if(spriteNum == 1){
                spriteNum = 2;
            }
            else if(spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }*/
    }
    
    // ve nhan vat
    public void draw(Graphics2D g2){
        
        g2.setColor(Color.white);
        
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        /*sau nay ve nv o day
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
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        */
    }
}
