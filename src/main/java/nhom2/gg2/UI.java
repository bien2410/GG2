/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhom2.gg2;

import java.awt.*;
import java.awt.image.*;
import object.*;

/**
 *
 * @author ADMIN
 */
public class UI {
    
    GamePanel gp;
    Font TNR_40;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    //public boolean gameFinished = false; luc day gameThread = null; 
            
    public UI(GamePanel gp){
        this.gp = gp;
        
        TNR_40 = new Font("Times New Roman", Font.PLAIN, 40);
        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;
    }
    
    public void showMessage(String text){
        
        message = text;
        messageOn = true;
    }
    
    public void draw(Graphics2D g2){
        //ve haskey
        g2.setFont(TNR_40);
        g2.setColor(Color.white);
        g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
        g2.drawString("x " + gp.player.hasKey, 74, 65);
        
        //ve hp player
        g2.setColor(Color.red);
        g2.fillRect(gp.tileSize * 1, gp.tileSize * 2, gp.player.hp * 3, gp.tileSize / 2);
        
        g2.setColor(Color.white);
        //MESSAGE
        if(messageOn == true){
            
            g2.setFont(g2.getFont().deriveFont(24F)); // chu f de giam nho hay sao ay :V
            g2.drawString(message, gp.tileSize/2, gp.tileSize * 5);
            
            messageCounter++;
            
            if(messageCounter > 60){
                messageCounter = 0;
                messageOn = false;
            }
        }
    }
}
