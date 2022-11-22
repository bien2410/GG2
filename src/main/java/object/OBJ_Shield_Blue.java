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
public class OBJ_Shield_Blue extends Entity {
    
    public OBJ_Shield_Blue(GamePanel gp) {
        super(gp);
        
        type = type_shield;
        name = "Khiên tàng hình";
        image = setup("/objects/shield_blue", gp.tileSize, gp.tileSize);
        defenseValue = 2;
        description = "[" + name + "]\nGiúp tàng hình.";
    }
}
