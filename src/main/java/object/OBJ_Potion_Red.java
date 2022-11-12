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
public class OBJ_Potion_Red extends Entity{
    
    int value = 5;
    
    public OBJ_Potion_Red(GamePanel gp) {
        super(gp);
        
        type = type_consumable;
        name = "Red Potion";
        image = setup("/objects/potion_red", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nHeals your life by " + value + ".";
    }
    
    public void use(Entity entity){
        
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You drink the " + name + "!\n"
                + "Your life has been recovered by " + value + ".";
        entity.hp += value;
        if(gp.player.hp > gp.player.maxHp){
            gp.player.hp = gp.player.hp;
        }
    }
}
