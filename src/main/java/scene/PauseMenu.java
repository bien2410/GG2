package scene;

import e.*;
import nhom2.gg2.*;

import java.awt.Font;
import javax.swing.JCheckBox;
import java.awt.event.*;
//import java.awt.event.KeyEvent;
//import java.awt.Graphics;

public class PauseMenu extends Scene{
    private JCheckBox checkMute;
    private ESlider musicSlider;
    private ESlider sfxSlider;
    private boolean dis=true;
    private KeyHandler key;
    public PauseMenu(KeyHandler key) {
        super(.7f,GameSetting.SCREEN_WIDTH,GameSetting.SCREEN_HEIGHT);
        this.pos_x=1152/2-this.screenWidth/2;
        this.pos_y=768/2-this.screenHeight/2;
        this.key=key;
        this.setTextColor(GameSetting.DF_TEXT_COLOR);
        checkMute=new JCheckBox("Mute All");
        checkMute.setFont(new Font(null,Font.PLAIN,17));
        checkMute.setForeground(this.getTextColor());
        checkMute.setOpaque(false);
        checkMute.setFocusPainted(false);
        checkMute.setBounds(alignmentBottomCenter(150,40,40,0));
        checkMute.addActionListener(new TurnSound());
        this.add(new EButton("x",15, this.getTextColor(),alignmentTopRight(42,42,10,10),new CloseScene()));
        this.add(new EButton("Resume",30, this.getTextColor(),alignmentCenter(200,50,0,-75),new CloseScene()));
        //this.add(new EButton("New game",30, this.getTextColor(),alignmentCenter(200,50,0,-25),new CloseScene()));
        this.add(new EButton("Main Menu",30, this.getTextColor(),alignmentCenter(200,50,0,25),new PauseMenu.MainMenu()));
        this.musicSlider=new ESlider(alignmentCenter((int)(this.getScreenWidth()/4), 15,75,90));
        this.add(new EButton("-",11, this.getTextColor(),alignmentCenter(40,40,-50,90),new DecreVolumeMusic()));
        this.add(new EButton("+",11, this.getTextColor(),alignmentRightCenter(40,40,100,90),new IncreVolumeMusic()));
        this.add(new EImage("Music", 25,this.getTextColor(), alignmentCenter(150,40,-175,90)));
        this.sfxSlider=new ESlider(alignmentCenter((int)(this.getScreenWidth()/4), 15,75,125));
        this.add(new EButton("-",11, this.getTextColor(),alignmentCenter(40,40,-50,125),new DecreVolumeSFX()));
        this.add(new EButton("+",11, this.getTextColor(),alignmentRightCenter(40,40,100,125),new IncreVolumeSFX()));
        this.add(new EImage("SFX", 25,this.getTextColor(), alignmentCenter(150,40,-175,125)));
        this.add(new EImage("Pause", 50,this.getTextColor(), alignmentTopCenter(500,75)));
        this.add(checkMute);
        this.add(this.sfxSlider);
        this.add(this.musicSlider);
        this.add(new EImage("bg/setting_back.jpg",alignmentCenter(this.getScreenWidth(), this.getScreenHeight())));
        this.setImage();
        this.addKeyListener(key);
        this.setFocusable(true);

    }
    public void disable(boolean b){
        this.dis=b;
    }
    public boolean isDisable(){
        return dis;
    }
    private class CloseScene implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            dis=true;
            GG2.getInstance().closeScene();
        }
    }
    private class MainMenu implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            GG2.getInstance().enterMainMenu();
        }
    }
    private class IncreVolumeMusic implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            musicSlider.setValue(musicSlider.getValue()+1);
            GG2.getInstance().getMus().setSoundVol(musicSlider.getValue());
            checkMute.setSelected(false);
        }
    }
    private class DecreVolumeMusic implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            musicSlider.setValue(musicSlider.getValue()-1);
            GG2.getInstance().getMus().setSoundVol(musicSlider.getValue());
        }
    }
    private class IncreVolumeSFX implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            sfxSlider.setValue(sfxSlider.getValue()+1);
            GG2.getInstance().getMus().setSfxVol(sfxSlider.getValue());
            checkMute.setSelected(false);
        }
    }
    private class DecreVolumeSFX implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            sfxSlider.setValue(sfxSlider.getValue()-1);
            GG2.getInstance().getMus().setSfxVol(sfxSlider.getValue());
        }
    }
    private class TurnSound implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(checkMute.isSelected()) {
                GG2.getInstance().getMus().setSoundVol(0);
                GG2.getInstance().getMus().setSfxVol(0);
            }
            else {
                GG2.getInstance().getMus().setSoundVol(musicSlider.getValue());
                GG2.getInstance().getMus().setSfxVol(sfxSlider.getValue());
            }
        }
    }
}
