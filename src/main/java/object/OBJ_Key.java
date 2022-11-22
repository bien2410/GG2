/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import entity.*;
import java.awt.*;
import nhom2.gg2.*;


/**
 *
 * @author ADMIN
 */
public class OBJ_Key extends Entity {
    
    public OBJ_Key(GamePanel gp) {
   
        super(gp);
        name = "Key";
        image = setup("/objects/key", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nMở khóa cổng.";
        //collision = true;
    }
    
}
