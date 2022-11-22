/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import entity.*;
import nhom2.gg2.GamePanel;

/**
 *
 * @author ADMIN
 */
public class OBJ_Chest extends Entity{
    int z;
    public OBJ_Chest(GamePanel gp, int z) {
        super(gp);
        hp = 1;
        name = "Chest";
        image = setup("/objects/chest", gp.tileSize, gp.tileSize);
        this.z = z;
    }
    
    public void open(){
        hp = 0;
        image = setup("/objects/chest_opened", gp.tileSize, gp.tileSize);
        gp.aSetter.setCoin(worldX, worldY, 1000);
        gp.aSetter.setItem(worldX, worldY, z);
    }
}
