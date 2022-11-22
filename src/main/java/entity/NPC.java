/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.*;
import nhom2.gg2.*;

/**
 *
 * @author ADMIN
 */
public class NPC extends Entity{

    public NPC(GamePanel gp) {
        super(gp);
        
        name = "Ông ôp";
        image = setup("/npc/npc", gp.tileSize, gp.tileSize);
    }
    
    public void setBeginConversation(){
        gp.dia.add("Chào. Ngươi muốn gì ở ta?");
        gp.dia.add("Sao cơ ngươi muốn về thế giới cũ để code tiếp hả.");
        gp.dia.add("Chuyện này cũng dễ thôi.\nTa đã từng giúp vài trường hợp như ngươi rồi.");
        gp.dia.add("Trước hết hãy học cách sinh tồn ở đây.\nĐây là thế giới mọi thứ đều có thể tái sinh.\nNhưng vẫn còn nhiều thứ nguy hiểm đang tồn tại.");
        gp.dia.add("Hãy học cách tự bảo vệ mình.\nẤn J để chém, K để ném phi tiêu.\nKết hợp khi nhảy sẽ có cơ chế thú vị đấy");
        gp.dia.add("Ngươi cũng có thể ấn C để xem chỉ số của mình.\nGiờ thì giúp ta làm vài việc nào!");
    }
    
    public void setDialogue(int i){
        gp.mission.setConversation(i);
        gp.dia.addAll(gp.mission.conservation);
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
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(10f));
            g2.drawString(name, screenX + 5, screenY - 10);
            
            g2.drawImage(image, screenX, screenY, null);   
        }
    }
}
