package scene;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ADMIN
 */

import e.*;
import java.awt.event.*;
import nhom2.gg2.*;
public class HelpMenu extends Scene{
    public HelpMenu() {
        super(.8f,GameSetting.SCREEN_WIDTH,GameSetting.SCREEN_HEIGHT);
        this.setTextColor(GameSetting.DF_TEXT_COLOR);
        this.add(new EButton("x",15, this.getTextColor(),alignmentTopRight(42,42,10,10),new CloseScene()));
        this.add(new EButton(">",15, this.getTextColor(),alignmentRightCenter(45,75),null));
        this.add(new EButton("<",15, this.getTextColor(),alignmentLeftCenter(45,75),null));
        this.add(new EImage("Help", 50,this.getTextColor(), alignmentTopCenter(500,75)));
        this.add(new EImage("bg/help_back.jpg",alignmentCenter(this.getScreenWidth(), this.getScreenHeight())));
        this.setImage();
    }
    public void animated() {
        super.animated();
        this.setAnimated(true);
        this.animatedFunc=(g)->{
            this.appearEffect(g);
        };
    }
    private class CloseScene implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            setAnimated(true);
            animatedFunc=g->{
                disapearEffect(g);
                if(!isAnimated()) GG2.getInstance().closeScene();
            };
        }
    }
}
