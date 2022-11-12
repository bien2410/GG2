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
    Font TNR_40;
    BufferedImage keyImage;
    public boolean messageOn = false;
    
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    //public boolean gameFinished = false; luc day gameThread = null; 
    public String currentDialogue = "";
    public int commandNum = 0;
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
        
        int messageX = gp.tileSize;
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
    
    public void draw(Graphics2D g2){
        
        this.g2 = g2;
        
        if (gp.gameState == gp.playState){
            /*
            g2.setFont(TNR_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.hasKey, 74, 65);
            */
            //ve hp player
            g2.setColor(Color.red);
            g2.fillRect(gp.tileSize * 1, gp.tileSize * 2, gp.player.hp * 3, gp.tileSize / 2);

            g2.setColor(Color.white);
            //MESSAGE
            drawMessage();
        }
        //PLAY STATE
        if(gp.gameState == gp.playState){
            // do playState stuff late
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
        final int frameWidth = gp.tileSize * 5;
        final int frameHeight = gp. tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        
        //TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));
        
        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 35;
        
        //NAMES
        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("HP", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense", textX, textY);
        textY += lineHeight;
        g2.drawString("Exp", textX, textY);
        textY += lineHeight;
        g2.drawString("Next Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Coin", textX, textY);
        textY += lineHeight + 20;
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight + 15;
        g2.drawString("Shield", textX, textY);
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
        
        value = String.valueOf(gp.player.coin);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        g2.drawImage(gp.player.currentWeapon.image, tailX - gp.tileSize, textY - 14, null);
        textY += gp.tileSize;
        g2.drawImage(gp.player.currentShield.image, tailX - gp.tileSize, textY - 14, null);
    }
    
    public void drawInventory(){
        
        //frame
        int frameX = gp.tileSize * 9;
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
