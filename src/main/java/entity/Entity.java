/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.*;
import java.awt.image.*;

/**
 *
 * @author ADMIN
 */
//class bo cua player, npc, monster,...
public class Entity {
    
    public int worldX, worldY; // vi tri cua player tren map(ko phai tren man hinh)
    public int speed;
    
    public BufferedImage jump1, jump2, left1, left2, right1, right2;
    public String direction; // huong quay mat
    
    // bien de chinh hoat anh
    public int spriteCounter = 0;
    public int spriteNum = 1;
    
    //collision
    public Rectangle solidArea;
    public boolean collisionOn = false;
}
