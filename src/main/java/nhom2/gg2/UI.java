/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhom2.gg2;

import java.awt.*;
import java.awt.image.*;
import java.util.*;
import object.*;

/**
 *
 * @author ADMIN
 */
public class UI {
    
    GamePanel gp;
    Graphics2D g2;
    public Font TNR_40;
    BufferedImage keyImage;
    public boolean messageOn = false;
    
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public String currentMission = "";
    //public boolean gameFinished = false; luc day gameThread = null; 
    public String currentDialogue = "";
    public String currentTitle = "Nhấn ENTER để bắt đầu";
    //public int commandNum = 0;
    public int titleScreenState = 0;
    public int slotCol = 0;
    public int slotRow = 0;
    public UI(GamePanel gp){
        this.gp = gp;
        
        TNR_40 = new Font("Times New Roman", Font.PLAIN, 40);
        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;
    }
    
    public void addMessage(String text){
        
        message.add(text);
        messageCounter.add(0);
    }
    
    public void drawMessage(){
        
        int messageX = gp.tileSize / 2;
        int messageY = gp.tileSize * 4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        
        for(int i = 0; i < message.size(); i++){
            
            if(message.get(i) != null){
                
                g2.setColor(Color.red); //shadow of text
                g2.drawString(message.get(i), messageX + 2, messageY);
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);
                
                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i, counter);
                messageY += 50;
                
                if(messageCounter.get(i) > 100){
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }
    
    public void drawMission(){
        drawSubWindow(gp.tileSize * 20, gp.tileSize * 7 / 2, gp.tileSize * 4, gp.tileSize * 2);
        g2.setColor(Color.white);
        currentMission = gp.mission.currentMission;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));
        g2.drawString("NHIỆM VỤ", gp.tileSize * 21, gp.tileSize * 4 + 5);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 10F));
        int messageX = gp.tileSize * 20 + 20;
        int messageY = gp.tileSize * 4 + gp.tileSize / 2 + 5;
        if(currentMission != null){
            for(String line : currentMission.split("\n")){
                g2.drawString(line, messageX, messageY);
                messageY += 10;
            }
        }
    }
    
    public void draw(Graphics2D g2){
        
        this.g2 = g2;
        
        if (gp.gameState == gp.playState){
            /*
            g2.setFont(TNR_40);
            g2.setColor(Color.white);
            g2.drawImage(keyI/*mage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.hasKey, 74, 65);
            */
            //ve hp player
            /*g2.setColor(Color.red);
            g2.fillRect(gp.tileSize * 1, gp.tileSize * 2, gp.player.hp * 3, gp.tileSize / 2);

            g2.setColor(Color.white);*/
            //MESSAGE
            drawMessage();
            //MISSION
            drawMission();
        }
        //TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        
        // PAUSE STATE
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        
        // DIALOGUE STATE
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }
        
        //character state
        if (gp.gameState == gp.characterState){
            drawCharacterScreen(); // con phai dung gamepanel nua
            drawInventory();
        }
        
        // revivalSTATE
        if(gp.gameState == gp.revivalState){
            drawRevivalScreen();
        }
        // LOSE
        if(gp.gameState == gp.gameLoseState){
            drawEndScreen(0);
        }
        // GAMEOVER
        if(gp.gameState == gp.gameOverState){
            drawEndScreen(1);
        }
        // WIN
        if(gp.gameState == gp.gameWinState){
            drawEndScreen(2);
        }
    }
    
    public void drawTitleScreen(){
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);

        g2.setFont(TNR_40);
        x += gp.tileSize;
        y += gp.tileSize;
       if(currentTitle != ""){
            for(String line : currentTitle.split("\n")){
                 g2.drawString(line, x, y);
                y += 40;
            }
        }
    }
    
    public void drawPauseScreen(){
        
        g2.setFont(TNR_40);
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
    }
    public void drawDialogueScreen(){
        
        //WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);
        
        g2.setFont(TNR_40);
        x += gp.tileSize;
        y += gp.tileSize;
        
        for(String line : currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y += 40;
        }
        
    }
    
    public void drawCharacterScreen(){
        
        // CREATE A FRAME
        final int frameX = gp.tileSize;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize * 8;
        final int frameHeight = gp. tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        
        //TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));
        
        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 35;
        
        //NAMES
        g2.drawString("Cấp", textX, textY);
        textY += lineHeight;
        g2.drawString("Máu", textX, textY);
        textY += lineHeight;
        g2.drawString("Năng lượng", textX, textY);
        textY += lineHeight;
        g2.drawString("Sức mạnh", textX, textY);
        textY += lineHeight;
        g2.drawString("Bền bỉ", textX, textY);
        textY += lineHeight;
        g2.drawString("Tấn công", textX, textY);
        textY += lineHeight;
        g2.drawString("Phòng thủ", textX, textY);
        textY += lineHeight;
        g2.drawString("Kinh nghiệm", textX, textY);
        textY += lineHeight;
        g2.drawString("Cấp kế tiếp cần", textX, textY);
        textY += lineHeight + 20;
        g2.drawString("Vũ khí", textX, textY);
        textY += lineHeight + 15;
        g2.drawString("Khiên", textX, textY);
        textY += lineHeight;
        
        //VALUE
        int tailX = (frameX + frameWidth) - 30; //gioi han x
        //Reset textY
        textY = frameY + gp.tileSize;
        String value;
        
        value = String.valueOf(gp.player.level);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.hp + "/" + gp.player.maxHp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.strength);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.dexterity);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.attack);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.defense);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.exp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        g2.drawImage(gp.player.currentWeapon.image, tailX - gp.tileSize, textY - 14, null);
        textY += gp.tileSize;
        g2.drawImage(gp.player.currentShield.image, tailX - gp.tileSize, textY - 14, null);
    }
    
    public void drawRevivalScreen(){
        g2.setColor((new Color(0, 0, 0, 150)));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50f));
        text = "Hồi sinh sau: " + gp.counterRevival / 60 + "s";
        g2.setColor(Color.white);
        x = getXforCenteredText(text);
        y = gp.tileSize * 5;
        g2.drawString(text, x, y);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24f));
        text = "Hoặc mất: " + gp.timeRevival / 60 + " xu để hồi sinh ngay lập tức -> Bấm ENTER";
        g2.setColor(Color.yellow);
        x = getXforCenteredText(text);
        y += gp.tileSize * 2;
        g2.drawString(text, x, y);
    }
    
    public void drawEndScreen(int i){
        
        g2.setColor((new Color(0, 0, 0, 150)));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        int x;
        int y;
        String text = "";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
        switch (i) {
            case 0:
                text = "YOU LOSE";
                break;
            case 1:
                text = "GAME OVER";
                break;
            case 2:
                text = "YOU WIN";
                break;
        }
        //Shadow
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.tileSize * 4;
        g2.drawString(text, x, y);
        //Main
        g2.setColor(Color.white);
        g2.drawString(text, x - 4, y - 4);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24f));
        switch (i) {
            case 0:
                text = "Bạn đã mắc bẫy của ông Ôp - thực ra hắn ta là OOP kẻ đứng sau mọi chuyện!";
                g2.drawString(text, getXforCenteredText(text), gp.tileSize * 10);
                break;
            case 1:
                text = "Bạn đã tái sinh, một phần cơ thể bạn đã ở lại thế giới này nên bạn không thể quay về thế giới thực!";
                break;
            case 2:
                text = "Chúc mừng bạn đã phá đảo thể giới này, giờ thì quay trở lại code tiếp thôi!";
                break;
        }
    }
    
    public void drawInventory(){
        
        //frame
        int frameX = gp.tileSize * 12;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 6;
        int frameHeight = gp.tileSize * 5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        
        //slot
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize + 3;
        
        //DRAW PLAYER"S ITEMS
        for(int i = 0; i < gp.player.inventory.size(); i++){
            
            //EQUIP CURSOR
            if(gp.player.inventory.get(i) == gp.player.currentWeapon ||
                    gp.player.inventory.get(i) == gp.player.currentShield){
                g2.setColor(new Color(240, 190, 90));
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            }
            
            g2.drawImage(gp.player.inventory.get(i).image, slotX, slotY, null);
            
            slotX += slotSize;
            
            if(i == 4 || i == 9 || i == 14){
                slotX = slotXstart;
                slotY += slotSize;
            }
        }
        
        //cursor
        int cursorX = slotXstart + (slotSize * slotCol);
        int cursorY = slotYstart + (slotSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;
        
        //draw cursor
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
    
        //DESCRIPTION FRAME
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.tileSize * 3;
        
        //DRAW DESCRIPTION TEXT
        int textX = dFrameX + 20;
        int textY = dFrameY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(28F));
        
        int itemIndex = getItemIndexOnSlot();
        if(itemIndex < gp.player.inventory.size()){
            drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
            for(String line : gp.player.inventory.get(itemIndex).description.split("\n")){
                g2.drawString(line, textX, textY);
                textY += 32;
            }
        }
    }
    
    public int getItemIndexOnSlot(){
        int itemIndex = slotCol + (slotRow * 5);
        return itemIndex;
    }
    
    public void drawSubWindow(int x, int y, int width, int height){
        
        Color c = new Color(0, 0, 0, 200); // RGB color, chi so thu 4 la chinh do mo
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        
        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }
    
    public int getXforCenteredText(String text){

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
    
    public int getXforAlignToRightText(String text, int tailX){

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
}
