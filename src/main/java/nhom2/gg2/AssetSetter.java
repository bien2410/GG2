/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhom2.gg2;

import object.OBJ_Key;

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
        
        gp.obj[0] = new OBJ_Key(gp);
        gp.obj[0].worldX = 9 * gp.tileSize;
        gp.obj[0].worldY = 44 * gp.tileSize;
        
        gp.obj[1] = new OBJ_Key(gp);
        gp.obj[1].worldX = 16 * gp.tileSize;
        gp.obj[1].worldY = 44 * gp.tileSize;
        
        gp.obj[2] = new OBJ_Key(gp);
        gp.obj[2].worldX = 16 * gp.tileSize;
        gp.obj[2].worldY = 38 * gp.tileSize;
    }
}
