/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import entity.Entity;
import nhom2.gg2.GamePanel;

/**
 *
 * @author ADMIN
 */
public class OBJ_VIP_Shield extends Entity {
    
    public OBJ_VIP_Shield(GamePanel gp) {
        super(gp);
        
        type = type_shield;
        name = "Khiên VIP";
        image = setup("/objects/shield_wood", gp.tileSize, gp.tileSize);
        defenseValue = 100;
        description = "[" + name + "]\nĐồ VIP.";
    }
}
