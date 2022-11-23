package object;

import entity.Entity;
import nhom2.gg2.GamePanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ADMIN
 */
public class OBJ_VIP_Sword extends Entity{

    public OBJ_VIP_Sword(GamePanel gp) {
        super(gp);
        
        type = type_weapon;
        name = "Kiếm VIP";
        image = setup("/objects/sword_normal", gp.tileSize, gp.tileSize);
        attackValue = 100;
        attackArea.width = gp.tileSize * 2;
        attackArea.height = gp.tileSize * 2;
        description = "[" + name + "]\nĐồ VIP.";
    }
    
}
