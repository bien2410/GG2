/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import bullet.*;
import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;
import monster.*;
import nhom2.gg2.*;
import object.*;


/**
 *
 * @author ADMIN
 */
public class Player extends Entity{
    KeyHandler keyH; // input tu ban phim
    
    //vi tri cua player tren man hinh
    public int screenX;
    public int screenY;
    
    public int DFx1 = gp.tileSize * 2;
    public int DFx2 = gp.tileSize * 47;
    public int DFy[] = {44, 38, 28, 44, 20};
    public int potion = 0;
    public int potionCounter = 60;
    
    public boolean hide = false;
    
    public boolean running = false;
    //Jump
    boolean jumping = false;
    boolean falling = false;
    int jumpStrength;
    int fallStrength = 0;
    int weight = 1;
    
    //attack
    int attackNum = 0;
    public boolean attacking = false;
    
    //shoot
    public boolean shooting = false;
    boolean shootingAnimation = false;
    //image them
    public BufferedImage attackHighLeft, attackHighRight;
    
    //INVENTORY
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    
    public boolean speaking = false;
    public int speakingIndex;
    
    //CAMERA
    public Rectangle cam = new Rectangle(gp.tileSize*7, gp.tileSize*5, gp.tileSize*8, gp.tileSize*8);
    
    public void move(){
        if(screenX < cam.x){
            screenX = cam.x;
        }
        if(screenX > cam.x + cam.width - gp.tileSize){
            screenX = cam.x + cam.width - gp.tileSize;
        }
        if(screenY < cam.y){
            screenY = cam.y;
        }
        if(screenY > cam.y + cam.height - gp.tileSize){
            screenY = cam.y + cam.height - gp.tileSize;
        }
    }
    
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
        worldY = gp.tileSize * 44; // co 5 hang dat ben duoi
        
        speed = 5;
        maxHp = 100;
        hp = maxHp;
        maxMana = 100;
        mana = maxMana;
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
        left3 = setup("/player/leftMove2", gp.tileSize, gp.tileSize);
        right1 = setup("/player/right", gp.tileSize, gp.tileSize);
        right2 = setup("/player/rightMove", gp.tileSize, gp.tileSize);
        right3 = setup("/player/rightMove2", gp.tileSize, gp.tileSize);
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
        
        if(gp.currentMap == 2 && worldX >= 11 * gp.tileSize && worldY >= 37 * gp.tileSize){
            gp.tileM.loadMap("/res/maps/map31.txt", 2);
            
        }
        if(gp.currentMap == 4 && worldX >= 5 * gp.tileSize && worldY <= 16 * gp.tileSize){
            gp.tileM.loadMap("/res/maps/map52.txt", 4);
        }
        if(gp.monster[4][0] != null && gp.monster[4][0].alive == false){
            gp.tileM.loadMap("/res/maps/map51.txt", 4);
            gp.npc[4][2] = null;
            gp.aSetter.setEnd(2);
        }
        if(worldX >= gp.tileSize * 48 && gp.currentMap < 4){
            teleport(1);
        }
        else if(worldX <= gp.tileSize * 1 && gp.currentMap > 0){
            teleport(0);
        }
        if(gp.currentMap == 0 && gp.mission.checkMission[8] && gp.npc[0][0] != null){
            gp.gameState = gp.dialogueState;  
            gp.aSetter.setItem(gp.npc[0][0].worldX, gp.npc[0][0].worldY, 0);
            gp.npc[0][0] = null;
        }
        potionCounter--;
        if(potionCounter < 0) potionCounter = 0;
        //check object collision
        int objIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(objIndex);
        
        //check chest
        int chestIndex = gp.cChecker.checkChest(this);
        openChest(chestIndex);
        
        //coin
        int coinIndex = gp.cChecker.checkCoin(this);
        pickUpCoin(coinIndex);
        
        //npc
        int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
        if(keyH.enterPressed == true && npcIndex != 999) {
            speaking = true;
            speakingIndex = npcIndex;
        }
        if(speaking == true) interactNpc(speakingIndex);
        //tha roi tu do
        if(gp.cChecker.checkFloor(this) == false && jumping == false){
            
            falling = true;
        }
        // check chem
        if(keyH.jPressed == true && attacking == false && !invincible && !hide){
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
        if(keyH.kPressed == true && attacking == false && shooting == false && !invincible && mana >= 10 && !hide){
            mana -= 10;
            gp.bullet = new Bullet(gp);
            shooting = true;
            shootingAnimation = true;
        }
        
        //monster
        int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
        if(!hide) touchMonster(monsterIndex);
        
        //invinvible
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 60){ // sua lai o day
                invincible = false;
                invincibleCounter = 0;
            }
        }
        
        running = false;
        if((keyH.leftPressed == true || keyH.rightPressed == true) && !attacking){ // hoat anh chi doi khi di chuyen, h co the di chuyen khi invincible
            shootingAnimation = false;
            running = true;
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
                    case"left": 
                        worldX -= speed;
                        screenX -= speed;
                        break;
                    case"right": 
                        worldX += speed; 
                        screenX += speed;
                        break;
                }

            }

            spriteCounter++;
            if(spriteCounter > 10){ //cu 10/FPS s thay doi hoat anh 1 lan 
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        
        if(keyH.upPressed == true && !invincible && !attacking){
            shootingAnimation = false;
            if(gp.cChecker.checkFloor(this)){ // co the doi thanh check Jump de cuoi may
                jumpStrength = 20;
                jumping = true;
            }
        }
        //jump
        if(jumping){
            worldY -= jumpStrength;
            screenY -= jumpStrength;
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
            screenY += fallStrength;
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
        
        if(keyH.ePressed == true && potion > 0 && potionCounter == 0){
            potion--;
            hp += 25;
            if(hp > maxHp) hp = maxHp;
            gp.ui.addMessage("Hồi 25 máu");
            potionCounter = 60;
        }
        move();
        manaRecover();
        
        if(worldY > gp.tileSize * 47) hp = 0;
        if(hp <= 0){
            gp.gameState = gp.revivalState;
        }
    }
    
    public void restore(){
        worldX = gp.tileSize * 8;
        worldY = gp.tileSize * (gp.maxWorldRow - 6);
        direction = "right";
        hp = maxHp;
        mana = maxMana;
        invincible = false;
        gp.currentMap = 0;
    }
    
    public void teleport(int i){
        gp.coin.clear();
        if(i == 0){ // lui map
            gp.currentMap -= 1;
            worldX = DFx2;
            worldY = DFy[gp.currentMap] * gp.tileSize;
            if(gp.currentMap == 3) worldY = 5 * gp.tileSize;
            
        }
        else{
            gp.currentMap += 1;
            worldX = DFx1;
            worldY = DFy[gp.currentMap] * gp.tileSize;
        }
        if(gp.currentMap == 4) {
            gp.aSetter.setBoss();
            gp.aSetter.setEnd(1);
        }
    }
    
    public void manaRecover(){
        manaCounter++;
        if(manaCounter >= 60){
            mana += 1;
            if(mana > maxMana) mana = maxMana;
            manaCounter = 0;
        }
    }
    
    public void interactNpc(int i){
        if(i == 0){
            gp.gameState = gp.dialogueState;
            gp.npc[gp.currentMap][i].speak();
            if(gp.mission.indexMission >= 0 && gp.mission.checkMission[gp.mission.indexMission]){
                switch (gp.mission.indexMission) {
                    case 0:
                        gp.aSetter.setCoin(worldX, worldY, 100);
                        break;
                    case 1:
                        gp.aSetter.setCoin(worldX, worldY, 200);
                        gp.aSetter.setItem(worldX, worldY, 1);
                        break;
                    case 2:
                        gp.aSetter.setCoin(worldX, worldY, 300);
                        gp.aSetter.setItem(worldX, worldY, 0);
                        break;
                    case 3:
                        gp.aSetter.setCoin(worldX, worldY, 400);
                        gp.ui.addMessage("Tốc độ di chuyển đã tăng!");
                        speed = 8;
                        break;
                    case 4:
                        gp.aSetter.setCoin(worldX, worldY, 500);
                        gp.ui.addMessage("Năng lượng tối đa cộng thêm 50");
                        maxMana += 50;
                        mana = maxMana;
                        break;
                    case 5:
                        gp.aSetter.setCoin(worldX, worldY, 600);
                        gp.aSetter.setItem(worldX, worldY, 0);
                        break;
                    case 6:
                        gp.aSetter.setCoin(worldX, worldY, 700);
                        gp.aSetter.setItem(worldX, worldY, 2);
                        break;
                    case 7:
                        gp.aSetter.setCoin(worldX, worldY, 800);
                        gp.ui.addMessage("Máu tối đa cộng thêm 100");
                        maxHp += 100;
                        hp = maxHp;
                        break;
                    default:
                        break;
                }
            }
            if(gp.mission.doing == false) gp.mission.nextMission();
        }
        if(i == 1 && keyH.enterPressed){
            speaking = false;
            keyH.enterPressed = false;
            if(coin >= 50){
                coin -= 50;
                potion += 1;
                gp.ui.addMessage("+1 bình máu");
            }
            else{
                gp.ui.addMessage("Bạn không đủ tiền");
            }
        }
        if(i == 2){
            if(gp.timeRevival <= 15 * 60){
                gp.gameState = gp.gameWinState;
            }
            else {
                gp.gameState = gp.gameOverState;
            }
        }
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
               
               inventory.add(gp.obj[gp.currentMap][i]);
               gp.playSE(1);
               text = "Nhận được " + gp.obj[gp.currentMap][i].name + "!";
           }
           else{
               text = "Bạn không thể nhặt thêm!";
           }
           gp.ui.addMessage(text);
           gp.obj[gp.currentMap][i] = null;
           
        }
    }
    
    public void openChest(int i){
        if(i != 999 && keyH.enterPressed == true && gp.currentMap != 4){
            keyH.enterPressed = false;
            if(gp.chest[gp.currentMap][i].hp > 0) gp.chest[gp.currentMap][i].open();
        }
        if(i != 999 && keyH.enterPressed == true && gp.currentMap == 4){
            keyH.enterPressed = false;
            if(gp.chest[gp.currentMap][i].hp > 0) gp.chest[gp.currentMap][i].openBomb();
        }
    }
    
    public void pickUpCoin(int i){
        if(i != 999){
            String text = "Nhận được " + gp.coin.get(i).coin + " xu !";
            coin += gp.coin.get(i).coin;
            gp.coin.remove(i);
            gp.ui.addMessage(text);
        }
    }
    
    //tuong tac monster
    public void damageMonster(int i, String s){
        if(i != 999){
            int damage = 0;
            switch(s){
                case"attack":
                    damage = attack - gp.monster[gp.currentMap][i].defense;
                    break;
                case"shoot":
                    damage = gp.bullet.attack + strength; // dame chuan
                    break;
            }
            if(gp.monster[gp.currentMap][i].invincible == false){
                if(damage <= 0) {
                    damage = 1;
                }
                gp.monster[gp.currentMap][i].hp -= damage;
                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();
                gp.playSE(2);
                gp.ui.addMessage(damage + " sát thương!");
                if(gp.monster[gp.currentMap][i].hp <= 0){
                    gp.monster[gp.currentMap][i].dying = true;
                    gp.ui.addMessage("Tiêu diệt " + gp.monster[gp.currentMap][i].name);
                    exp += gp.monster[gp.currentMap][i].exp;
                    gp.ui.addMessage("+" + gp.monster[gp.currentMap][i].exp + " kinh nghiệm");
                    checkLevelUp();
                    int x = gp.monster[gp.currentMap][i].worldX;
                    int y = gp.monster[gp.currentMap][i].worldY;
                    gp.aSetter.setCoin(x, y, gp.monster[gp.currentMap][i].coin);
                }
            }
        }
    }
    
    public void touchMonster(int i){
        if(i != 999 && gp.monster[gp.currentMap][i].invincible == false){
   
            if(invincible == false){
                
                int damage = gp.monster[gp.currentMap][i].attack - defense;
                if(damage <= 0){
                    damage = 1;
                }
                hp -= damage;
                invincible = true;
                gp.playSE(3);
                gp.ui.addMessage("-" + damage + " máu");
            }
            //khi dung sat tuong se bi bug
            //khi bi sat thuong
            /*if(direction.equals("right")) worldX -= 10; // tam dc
            else worldX += 10;
            worldY -= 30;
            */
        }
    }
    
    public void takeDame(monster m){
        if(invincible == false){
                
            int damage = m.attack - defense;
            if(damage <= 0){
                damage = 1;
            }
            hp -= damage;
            invincible = true;
            gp.playSE(3);
            gp.ui.addMessage("-" + damage + " máu");
        }
    }
    
    public void checkLevelUp(){
        
        if(exp >= nextLevelExp){
            
            level++;
            nextLevelExp = nextLevelExp * 2;
            maxHp += 10;
            hp = maxHp;
            maxMana += 5;
            mana = maxMana;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();
            gp.playSE(4);
            gp.ui.addMessage("Lên cấp!");
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
                if(selectedItem.name.equals("Khiên tàng hình")) hide = true;
                else hide = false;
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
        // thanh mau, mana, exp
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(5));
        g2.drawRect(5, 5, gp.tileSize * 3 / 2, gp.tileSize * 2);
        g2.setFont(g2.getFont().deriveFont(12F));
        g2.drawString("Cấp:" + level, gp.tileSize / 3, gp.tileSize);
        double tmp = (double)exp / nextLevelExp * 100;
        String tmpt = String.format("%.2f", tmp);
        g2.drawString(tmpt + "%", gp.tileSize / 3 + 10, gp.tileSize + 20);
        g2.drawRect(5 + gp.tileSize * 3 / 2, gp.tileSize / 3, gp.tileSize * 10, gp.tileSize / 3 * 2);
        double tmp1 = (double)(gp.tileSize * 10 - 4) / maxHp;
        tmp1 = tmp1 * hp;
        g2.drawRect(5 + gp.tileSize * 3 / 2, gp.tileSize * 4 / 3 , gp.tileSize * 10, gp.tileSize / 3 * 2);
        double tmp2 = (double)(gp.tileSize * 10 - 4) / maxMana;
        tmp2 = tmp2 * mana;
        g2.setStroke(new BasicStroke(1));
        g2.setColor(Color.red);
        g2.fillRect(7 + gp.tileSize * 3 / 2, gp.tileSize / 3 + 2, (int)tmp1, gp.tileSize / 3 * 2 - 5);
        g2.setColor(Color.blue);
        g2.fillRect(7 + gp.tileSize * 3 / 2, gp.tileSize * 4 / 3 + 2, (int)tmp2, gp.tileSize / 3 * 2 - 5);
        
        // coin, binh mau
        g2.setFont(g2.getFont().deriveFont(35F));
        g2.setColor(Color.white);
        String tmpc = coin + "";
        g2.drawString(tmpc, gp.tileSize / 2 - 24, gp.tileSize * 3);
        g2.drawImage(setup("/objects/coin_bronze", gp.tileSize, gp.tileSize), gp.tileSize / 2 + tmpc.length() * 15, gp.tileSize * 3 - 38, gp.tileSize, gp.tileSize, null);
        
        g2.drawImage(setup("/objects/potion_red", gp.tileSize, gp.tileSize), gp.tileSize * 4, gp.tileSize * 3 - 38, gp.tileSize, gp.tileSize, null);
        g2.drawString("x " + potion, gp.tileSize * 5, gp.tileSize * 3);
            
        BufferedImage image = null;
        int inx = screenX;
        int iny = screenY;
        if(!attacking){
            if(!jumping){
                if(!running){
                    if(direction.equals("left")) image = left1;
                    if(direction.equals("right")) image = right1;
                }
                else{
                    switch(direction){
                        case"left":
                            if(spriteNum == 1){
                                image = left2;
                            }
                            if(spriteNum == 2){
                                image = left3;
                            }
                            break;
                        case"right":
                            if(spriteNum == 1){
                                image = right2;
                            }
                            if(spriteNum == 2){
                                image = right3;
                            }
                            break;
                    }
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
        if(hide) image = null;
        g2.drawImage(image, inx, iny, null);
        /*g2.setColor(Color.red);
        g2.drawRect(cam.x, cam.y, cam.width, cam.height);*/
        //g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
