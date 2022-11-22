/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scene;

/**
 *
 * @author ADMIN
 */

import e.*;
import java.awt.event.*;
import nhom2.gg2.*;

public class MainGame extends Scene{
    GamePanel gp = new GamePanel();
    KeyHandler keyH = new KeyHandler(gp);
    public MainGame() {
        //super(GameSetting.SCREEN_WIDTH,GameSetting.SCREEN_HEIGHT);
        this.add(gp);
        gp.setKeyH(keyH);
        //gp.keyH = new KeyHandler(gp);
    }
    
}
