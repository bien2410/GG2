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
public class ExitMenu extends Scene{
    public ExitMenu() {
        super(0.6f,GameSetting.SCREEN_WIDTH,GameSetting.SCREEN_HEIGHT);
        this.setTextColor(GameSetting.DF_TEXT_COLOR);
        this.add(new EButton("Yes",30, this.getTextColor(),this.alignmentBottomLeft(150, 50, 70,50),new QuitGame()));
        this.add(new EButton("No",30, this.getTextColor(),this.alignmentBottomRight(150, 50, 70,50),new CloseScene()));
        this.add(new EButton("x",15, this.getTextColor(),alignmentTopRight(42,42,10,10),new CloseScene()));
        this.add(new EImage("Are you sure?", 40,this.getTextColor(), alignmentTopCenter(400,75)));
        this.add(new EImage("bg/exit_back.jpg",alignmentCenter(this.getScreenWidth(), this.getScreenHeight())));
        this.setImage();
    }
    public void animated() {
        super.animated();
        this.setAnimated(true);
        this.animatedFunc=(g)->{
            this.fallEffect(g);
        };
    }
    private class CloseScene implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            setAnimated(true);
            animatedFunc=g->{
                fallv2Effect(g);
                if(!isAnimated()) GG2.getInstance().closeScene();
            };
        }
    }
    private class QuitGame implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            setAnimated(true);
            animatedFunc=g->{
                fallv2Effect(g);
                if(!isAnimated()) GG2.getInstance().quitGame();
            };
        }
    }
}
