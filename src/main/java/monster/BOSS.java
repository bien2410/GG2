/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monster;

import bullet.followBullet;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import nhom2.gg2.GamePanel;

/**
 *
 * @author ADMIN
 */
public class BOSS extends monster{
    
    public boolean attacking, shooting = false;
    
    public BOSS(GamePanel gp, int x, int y) {
        super(gp, x, y);
        
        name = "Cấu trúc dữ liệu và giải thuật";
        speed = gp.player.speed;
        maxHp = gp.player.maxHp * 2;
        hp = maxHp;
        attack = gp.player.attack * 2;
        defense = gp.player.defense * 2;
        
        solidArea.x = 6;
        solidArea.y = 36;
        solidArea.width = 84;
        solidArea.height = 60 - 1;
        //bien de check collision xong chuyen ve
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        attackArea.width = gp.tileSize * 4;
        attackArea.height = gp.tileSize * 2;
        getImage();
    }
    
    public void getImage(){
        
        left1 = setup("/monster/anh1", gp.tileSize * 2, gp.tileSize * 2);
        left2 = setup("/monster/anh2", gp.tileSize * 2, gp.tileSize * 2);
        right1 = setup("/monster/anh1", gp.tileSize * 2, gp.tileSize * 2);
        right2 = setup("/monster/anh2", gp.tileSize * 2, gp.tileSize * 2);
    }
    
    public void updateFollowPlayer(){
        if(gp.player.direction == "left") direction = "right";
        else if(gp.player.direction == "right") direction = "left";
        if(gp.player.attacking) {
            attacking = true;
        }
        if(gp.player.shooting && fb == null) {
            shooting = true;
        }
    }
    
    public void attack(){
        if(direction != "") atkDirection = direction;
        int currentWorldX = worldX;
        int currentWorldY = worldY;
        int solidAreaWidth = solidArea.width;
        int solidAreaHeight = solidArea.height;
        if(atkDirection.equals("right")){
            solidArea.width += attackArea.width;
            solidArea.height += attackArea.height;
        }
        if(atkDirection.equals("left")){
            solidArea.width += attackArea.width;
            solidArea.height += attackArea.height;
            worldX -= attackArea.width;
        }
        boolean b = gp.cChecker.checkPlayer(this);
        if(b) gp.player.takeDame(this);
        worldX = currentWorldX;
        worldY = currentWorldY;
        solidArea.width = solidAreaWidth;
        solidArea.height = solidAreaHeight;
    }
    
    public void shoot(){
        
        fb = new followBullet(gp, this, worldX, worldY);
        shooting = false;
    }
    
    public void update(){
            attacking = false;
            updateFollowPlayer();
            if(attacking) attack();
            if(shooting) shoot();
            collisionOn = false;
            gp.cChecker.checkTile(this);
            if(collisionOn == false && gp.player.running){
                if(direction.equals("right")) worldX += speed;
                if(direction.equals("left")) worldX -= speed;
            }
            spriteCounter++;
            if(spriteCounter > 30){ //cu 10/FPS s thay doi hoat anh 1 lan 
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        if(fb != null){

            int x = gp.player.worldX;
            int y = gp.player.worldY;
            fb.set(x, y);
            fb.update();
            fb.range++;
            if(fb.touch) fb = null;
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
        if(fb != null){
            fb.draw(g2);
        }
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
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        
        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX < gp.player.worldX + (gp.screenWidth - gp.player.screenX) &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY < gp.player.worldY + (gp.screenHeight - gp.player.screenY)){
            
            //Monster hp bar
            
            double oneScale = (double)gp.tileSize * 2 / maxHp;
            double hpBarValue = oneScale * hp;
            if(hpBarOn == true){
                g2.setColor(new Color(35,35,35));
                g2.fillRect(screenX - 1, screenY - 16, gp.tileSize * 2 + 2, 12);

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
            g2.drawString(name, screenX - 17, screenY - 30);
            
            g2.setColor(Color.red);
            if(attacking){
                if("right".equals(atkDirection)){
                    g2.drawRect(screenX + gp.tileSize * 2, screenY, gp.tileSize * 4, gp.tileSize * 2);
                }
                if("left".equals(atkDirection)){
                    g2.drawRect(screenX - gp.tileSize * 4, screenY, gp.tileSize * 4, gp.tileSize * 2);
                }
            }
            
            if(invincible){
                hpBarOn = true;
                hpBarCounter = 0;
                if(invincibleAnimation == true){
                    image = null;
                    invincibleAnimation = false;
                }
                else{
                    invincibleAnimation = true;
                }
            }
            if(dying == true){
                dyingAnimation(g2);
            }
           g2.drawImage(image, screenX, screenY, null);  
           changeAlpha(g2, 1f);
           
        }
   
    }
}
