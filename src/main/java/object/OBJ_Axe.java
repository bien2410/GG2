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
public class OBJ_Axe extends Entity{

    public OBJ_Axe(GamePanel gp) {
        super(gp);
        
        type = type_weapon;
        name = "Woodcutter's Axe";
        image = setup("/objects/axe", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = gp.tileSize;
        attackArea.height = gp.tileSize;
        description = "[" + name + "]\nA bit rusty but still\ncan cut some trees.";
    }
    
}
