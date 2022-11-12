/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import bullet.*;
import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;
import nhom2.gg2.*;
import object.*;


/**
 *
 * @author ADMIN
 */
public class Player extends Entity{
    KeyHandler keyH; // input tu ban phim
    
    //vi tri cua player tren man hinh
    public final int screenX;
    public final int screenY;
    
    //public int hasKey = 0;
    
    //Jump
    boolean jumping = false;
    boolean falling = false;
    int jumpStrength;
    int fallStrength = 0;
    int weight = 1;
    
    //attack
    int attackNum = 0;
    boolean attacking = false;
    
    //shoot
    boolean shooting = false;
    boolean shootingAnimation = false;
    //image them
    public BufferedImage attackHighLeft, attackHighRight;
    
    //INVENTORY
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    
    public Player(GamePanel gp, KeyHandler keyH){
        
        super(gp);
        this.keyH = keyH;
        //vi tri cua player tren man hinh
        screenX = gp.tileSize * 8;
        screenY = gp.tileSize * 10;
        
        //collision
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 31; // chinh sua ne
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
              
        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
    }
    
    public void setDefaultValues(){
        //vi tri that su cua player tren map
        worldX = gp.tileSize * 8;
        worldY = gp.tileSize * (gp.maxWorldRow - 6); // co 5 hang dat ben duoi
        
        speed = 5;
        maxHp = 100;
        hp = maxHp;
        level = 1;
        strength = 1;
        dexterity = 1;
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        attack = getAttack();
        defense = getDefense();
    }
    
    public void setItems(){
        
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Key(gp));
       
    }
    
    public int getAttack(){
        attackArea = currentWeapon.attackArea;
        return strength * currentWeapon.attackValue;
    }
    
    public int getDefense(){
        return dexterity * currentShield.defenseValue;
    }
    
    //nhap anh dau vao
    public void getPlayerImage(){
        //dien duong dan
        jump1 = setup("/player/leftJump", gp.tileSize, gp.tileSize);
        jump2 = setup("/player/rightJump", gp.tileSize, gp.tileSize);
        left1 = setup("/player/left", gp.tileSize, gp.tileSize);
        left2 = setup("/player/leftMove", gp.tileSize, gp.tileSize);
        right1 = setup("/player/right", gp.tileSize, gp.tileSize);
        right2 = setup("/player/rightMove", gp.tileSize, gp.tileSize);
       
    }
    public void getPlayerAttackImage(){
        
        attackLeft = setup("/player/leftAttack", gp.tileSize * 3, gp.tileSize);
        attackRight = setup("/player/rightAttack", gp.tileSize * 3, gp.tileSize);
        attackHighLeft = setup("/player/highLeftAttack", gp.tileSize * 3, gp.tileSize * 5);
        attackHighRight = setup("/player/highRightAttack", gp.tileSize * 3, gp.tileSize * 5);
        shootLeft = setup("/player/shootLeft", gp.tileSize, gp.tileSize);
        shootRight = setup("/player/shootRight", gp.tileSize, gp.tileSize);
    }
    
    //2 ham xu ly cho muot
    public int land(){
        int ans = worldY;
        while(ans % gp.tileSize != 0){
            ans--;
        }
        return ans;
    }
    
    public int head(){
        int ans = worldY;
        while(ans % gp.tileSize != 0){
            ans++;
        }
        return ans - solidArea.y; // hoat anh
    }
    
    // thao tac cua nhan vat
    public void update(){
        
        //check object collision
        int objIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(objIndex);
        
        //tha roi tu do
        if(gp.cChecker.checkFloor(this) == false && jumping == false){
            
            falling = true;
        }
        // check chem
        if(keyH.jPressed == true && attacking == false && !invincible){
            shootingAnimation = false;
            attacking = true;
            if(jumping == false && falling == false) attackNum = 1; //chem thuong
            else attackNum = 2; // nhay chem
        }
        //check co dang ban ko
        if(shooting == true && gp.bullet == null){
            shooting = false;
            shootingAnimation = false;
        }
        if(keyH.kPressed == true && attacking == false && shooting == false && !invincible){
            gp.bullet = new Bullet(gp);
            shooting = true;
            shootingAnimation = true;
        }
        
        //monster
        int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
        touchMonster(monsterIndex);
        
        //invinvible
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
        
        spriteNum = 1;
        if((keyH.leftPressed == true || keyH.rightPressed == true) && !attacking && !invincible){ // hoat anh chi doi khi di chuyen
            shootingAnimation = false;
            spriteNum = 2;
            if(keyH.leftPressed == true){
                direction = "left";
                
            }
            else if(keyH.rightPressed == true){
                direction = "right";
                
            }
            //collision
            collisionOn = false;
            gp.cChecker.checkTile(this);
            
            //IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(collisionOn == false){
                
                switch(direction){
                    case"left": worldX -= speed; break;
                    case"right": worldX += speed; break;
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
        
        if(keyH.upPressed == true && !invincible && !attacking){
            shootingAnimation = false;
            if(gp.cChecker.checkFloor(this)){ // co the doi thanh check Jump de cuoi may
                jumpStrength = 25;
                jumping = true;
            }
        }
        //jump
        if(jumping){
            worldY -= jumpStrength;
            jumpStrength -= weight;
            
            if(gp.cChecker.checkRoof(this)){ // check roof
                worldY = head();
                jumpStrength = 0;
            }
            
            if(gp.cChecker.checkFloor(this) && jumpStrength <= 0){ //check floor
                worldY = land();
                jumping = false;
            }
        }
        // roi tu do
        if(falling){
            worldY += fallStrength;
            fallStrength++;
            if(gp.cChecker.checkFloor(this)){
                worldY = land();
                falling = false;
                fallStrength = 0;
            }
        }
        
        //attack
        if(attacking == true){
            attacking();
        }
        
        /*if(shooting == true){
            shooting();
        }*/
    }
    
    public void attacking(){
        //doi hoat anh, sua lai sau
        spriteCounterAttack++;
        
        if(spriteCounterAttack <= 5){
            spriteNumAttack = 1;
        }
        if(spriteCounterAttack > 5 && spriteCounterAttack <= 20){
            spriteNumAttack = 2;
            
            //save the current worldX, worldY, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;
            
            // Adjust player's worldX,Y for the attackArea
            if(attackNum == 1){
                if(direction.equals("right")){
                    solidArea.width += attackArea.width;
                }
                if(direction.equals("left")){
                    solidArea.width += attackArea.width;
                    worldX -= attackArea.width;
                }
            }
            if(attackNum == 2){
                if(direction.equals("right")){
                    worldY -= attackArea.height;
                    solidArea.width += attackArea.width;
                    solidArea.height += attackArea.height * 2;
                }
                if(direction.equals("left")){
                    worldY -= attackArea.height;
                    solidArea.width += attackArea.width;
                    solidArea.height += attackArea.height * 2;
                    worldX -= attackArea.width;
                }
            }
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex, "attack");
            
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if(spriteCounterAttack > 20){
            spriteNumAttack = 1;
            if(spriteCounterAttack > 20){
                spriteCounterAttack = 0;
                attacking = false;
                attackNum = 0;
            }
        }
    }
    
    /*public void shooting(){
    }*/
            
    //tuong tac object
    public void pickUpObject(int i){
       if(i != 999){
           String text;
           if(inventory.size() != maxInventorySize){
               
               inventory.add(gp.obj[i]);
               gp.playSE(1);
               text = "Got a " + gp.obj[i].name + "!";
           }
           else{
               text = "You cannot carry any more!";
           }
           gp.ui.addMessage(text);
           gp.obj[i] = null;
           
        }
    }
    
    //tuong tac monster
    public void damageMonster(int i, String s){
        if(i != 999){
            int damage = 0;
            switch(s){
                case"attack":
                    damage = attack - gp.monster[i].defense;
                    break;
                case"shoot":
                    damage = gp.bullet.attack; // dame chuan
                    break;
            }
            if(gp.monster[i].invincible == false){
                if(damage < 0) {
                    damage = 0;
                }
                gp.monster[i].hp -= damage;
                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();
                gp.playSE(2);
                gp.ui.addMessage(damage + " damage!");
                if(gp.monster[i].hp <= 0){
                    gp.monster[i].dying = true;
                    gp.ui.addMessage("killed the " + gp.monster[i].name);
                    exp += gp.monster[i].exp;
                    gp.ui.addMessage("+" + gp.monster[i].exp + " exp");
                    checkLevelUp();
                }
            }
        }
    }
    
    public void touchMonster(int i){
        if(i != 999 && gp.monster[i].invincible == false){
   
            if(invincible == false){
                
                int damage = gp.monster[i].attack - defense;
                if(damage < 0){
                    damage = 0;
                }
                hp -= damage;
                invincible = true;
                gp.playSE(3);
                gp.ui.addMessage("-" + damage + " hp");
            }
            //khi dung sat tuong se bi bug
            //khi bi sat thuong
            if(direction.equals("right")) worldX -= 10; // tam dc
            else worldX += 10;
            worldY -= 30;
            
        }
    }
    
    public void checkLevelUp(){
        
        if(exp >= nextLevelExp){
            
            level++;
            nextLevelExp = nextLevelExp * 2;
            maxHp += 10;
            hp = maxHp;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();
            gp.playSE(4);
            gp.ui.addMessage("Level up!");
        }
    }
    
    public void selectItem(){
        
        int itemIndex = gp.ui.getItemIndexOnSlot();
        
        if(itemIndex < inventory.size()){
            
            Entity selectedItem = inventory.get(itemIndex);
            
            if(selectedItem.type == type_weapon){
                
                currentWeapon = selectedItem;
                attack = getAttack();
                //getPlayerAttackImage();
            }
            if(selectedItem.type == type_shield){
                
                currentShield = selectedItem;
                defense = getDefense();
            }
            if(selectedItem.type == type_consumable){
                
                selectedItem.use(this);
                inventory.remove(itemIndex);
            }
        }
    }
    
    // ve nhan vat
    public void draw(Graphics2D g2){
        
        /*g2.setColor(Color.white);
        g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);*/
            
        //ve demo
        /*if(attacking == true){
            if(attackNum == 1){
                if("right".equals(direction)){
                    g2.setColor(Color.orange);
                    g2.drawRect(screenX + gp.tileSize, screenY, gp.tileSize * 2, gp.tileSize);
                }
                if("left".equals(direction)){
                    g2.setColor(Color.orange);
                    g2.drawRect(screenX - gp.tileSize * 2, screenY, gp.tileSize * 2, gp.tileSize);
                }
            }
            if(attackNum == 2){
                if("right".equals(direction)){
                    g2.setColor(Color.orange);
                    g2.drawRect(screenX, screenY - gp.tileSize * 2, gp.tileSize * 3, gp.tileSize * 5);
                }
                if("left".equals(direction)){
                    g2.setColor(Color.orange);
                    g2.drawRect(screenX - gp.tileSize * 2, screenY - gp.tileSize * 2, gp.tileSize * 3, gp.tileSize * 5);
                }
            }
        }*/
        //sau nay ve nv o day
        BufferedImage image = null;
        int inx = screenX;
        int iny = screenY;
        if(!attacking){
            if(!jumping){
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
            }
            else{
                switch(direction){
                    case"left":
                        image = jump1;
                        break;
                    case"right":
                        image = jump2;
                        break;
                }

            }
            if(shootingAnimation){
                switch(direction){
                    case"left":
                        image = shootLeft;
                        break;
                    case"right":
                        image = shootRight;
                        break;
                }
            }
        }
        else{
            if(attackNum == 1){
                switch(direction){
                    case"left":
                        if(spriteNumAttack == 1){
                            image = left1;
                        }
                        if(spriteNumAttack == 2){
                            image = attackLeft;
                            inx -= gp.tileSize * 2;
                        }
                        break;
                    case"right":
                        if(spriteNumAttack == 1){
                            image = right1;
                        }
                        if(spriteNumAttack == 2){
                            image = attackRight;
                        }
                        break;
                }
            }
            else{
                switch(direction){
                    case"left":
                        if(spriteNumAttack == 1){
                            image = jump1;
                        }
                        if(spriteNumAttack == 2){
                            image = attackHighLeft;
                            inx -= gp.tileSize * 2;
                            iny -= gp.tileSize * 2;
                        }
                        break;
                    case"right":
                        if(spriteNumAttack == 1){
                            image = jump2;
                        }
                        if(spriteNumAttack == 2){
                            image = attackHighRight;
                            iny -= gp.tileSize * 2;
                        }
                        break;
                }
            }
        }
        if(invincible){
            //g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            if(invincibleAnimation == true){
                image = null;
                invincibleAnimation = false;
            }
            else{
                invincibleAnimation = true;
            }
        }
        g2.drawImage(image, inx, iny, null);
        //g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
