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
public class OBJ_Coin extends Entity {

    public OBJ_Coin(GamePanel gp) {
        super(gp);
        
        name = "Coin";
        image = setup("/objects/coin_bronze", gp.tileSize, gp.tileSize);
        
    }
    
    public void update(){
        if(gp.cChecker.checkFloor(this) == false) {
                worldY++;
        }
    }
    
}
