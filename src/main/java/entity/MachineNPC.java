/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import nhom2.gg2.GamePanel;

/**
 *
 * @author ADMIN
 */
public class MachineNPC extends NPC {

    public MachineNPC(GamePanel gp) {
        super(gp);
        
        name = "Máy bán máu tự động";
        image = setup("/npc/machine", gp.tileSize, gp.tileSize);
    }
    public void draw(Graphics2D g2){
        
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        
        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX < gp.player.worldX + (gp.screenWidth - gp.player.screenX) &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY < gp.player.worldY + (gp.screenHeight - gp.player.screenY)){
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(10f));
            g2.drawString(name, screenX - 20, screenY - 10);
            
            g2.drawImage(image, screenX, screenY, null);   
        }
    }
}
