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
public class MainMenu extends Scene{
    public MainMenu() {
        super(GameSetting.SCREEN_WIDTH,GameSetting.SCREEN_HEIGHT);
        setTextColor(GameSetting.DF_TEXT_COLOR);
        this.add(new EButton("Play",40,getTextColor(),alignmentRightCenter(250,75,50,0),new EnterGame()));
        this.add(new EButton("Help",40,getTextColor(),alignmentRightCenter(250,75,50,75),new HelpBt()));
        this.add(new EButton("Exit",40,getTextColor(),alignmentRightCenter(250,75,50,75*2),new ExitBt()));
        this.add(new EButton("i",17,getTextColor(),alignmentTopLeft(40,40,15,15),new InfoBt()));
        this.add(new EButton("s",25,getTextColor(),alignmentBottomRight(50,50,10,10),new SettingBt()));
        this.add(new EImage("Game", 100,getTextColor(), alignmentTopCenter(600,150)));
        this.add(new EImage("ver 1.0.0",15,getTextColor(),alignmentBottomLeft(80,25)));
        this.add(new EImage("bg/main_back.jpg",0,0,this.getScreenWidth(),this.getScreenHeight()));
        this.setImage();
    }
    public void animated() {
        super.animated();
        this.setAnimated(true);
        this.animatedFunc =(g)->{
            this.appearEffect(g);
        };
    }
    private class HelpBt implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            GG2.getInstance().enterHelpMenu();
        }
    }
    private class ExitBt implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            GG2.getInstance().enterExitMenu();
        }
    }
    private class InfoBt implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            GG2.getInstance().enterInfoMenu();
        }
    }
    private class SettingBt implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            GG2.getInstance().enterSettingMenu();
        }
    }
    /*private class LevelBt implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            GameManager.getInstance().enterLevelMenu();
        }
    }*/
    private class EnterGame implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            GG2.getInstance().enterMainGame();
        }
    }
}
