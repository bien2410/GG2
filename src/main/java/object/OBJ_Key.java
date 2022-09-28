/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import java.io.*;
import javax.imageio.*;
import nhom2.gg2.GamePanel;


/**
 *
 * @author ADMIN
 */
public class OBJ_Key extends SuperObject {
    
    GamePanel gp;
    
    public OBJ_Key(GamePanel gp) {
    
        this.gp = gp;
        name = "Key";
        try{
            //dien duong dan
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/key.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
