/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhom2.gg2;

import monster.MON_GreenSlime;
import object.*;

/**
 *
 * @author ADMIN
 */
// cai dat bat cu cai gi o day: object, npc, monster
public class AssetSetter {
    
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    
    public void setObject(){
        int i = 0;
        gp.obj[i] = new OBJ_Key(gp);
        gp.obj[i].worldX = 9 * gp.tileSize;
        gp.obj[i].worldY = 44 * gp.tileSize;
        i++;
        
        gp.obj[i] = new OBJ_Key(gp);
        gp.obj[i].worldX = 16 * gp.tileSize;
        gp.obj[i].worldY = 44 * gp.tileSize;
        i++;
        
        gp.obj[i] = new OBJ_Key(gp);
        gp.obj[i].worldX = 16 * gp.tileSize;
        gp.obj[i].worldY = 38 * gp.tileSize;
        i++;
        
        gp.obj[i] = new OBJ_Axe(gp);
        gp.obj[i].worldX = 11 * gp.tileSize;
        gp.obj[i].worldY = 44 * gp.tileSize;
        i++;
        
        gp.obj[i] = new OBJ_Shield_Blue(gp);
        gp.obj[i].worldX = 10 * gp.tileSize;
        gp.obj[i].worldY = 44 * gp.tileSize;
        i++;
        
        gp.obj[i] = new OBJ_Potion_Red(gp);
        gp.obj[i].worldX = 13 * gp.tileSize;
        gp.obj[i].worldY = 44 * gp.tileSize;
        i++;
    }
    
    public void setMonster(){
        
        gp.monster[0] = new MON_GreenSlime(gp);
        gp.monster[0].worldX = 20 * gp.tileSize;
        gp.monster[0].worldY = 44 * gp.tileSize;
        
        gp.monster[1] = new MON_GreenSlime(gp);
        gp.monster[1].worldX = 28 * gp.tileSize;
        gp.monster[1].worldY = 43 * gp.tileSize;
        
        gp.monster[2] = new MON_GreenSlime(gp);
        gp.monster[2].worldX = 20 * gp.tileSize;
        gp.monster[2].worldY = 35 * gp.tileSize;
    }
}
