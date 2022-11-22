/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scene;

/**
 *
 * @author ADMIN
 */
import java.awt.Font;
import javax.swing.JCheckBox;
import e.*;
import java.awt.event.*;
import nhom2.gg2.*;
public class SettingMenu extends Scene {
    private JCheckBox checkMute;
    private ESlider musicSlider;
    private ESlider sfxSlider;
    public SettingMenu() {
        super(.75f,GameSetting.SCREEN_WIDTH,GameSetting.SCREEN_HEIGHT);
        this.setTextColor(GameSetting.DF_TEXT_COLOR);
        checkMute=new JCheckBox("Mute All");
        checkMute.setFont(new Font(null,Font.PLAIN,25));
        checkMute.setForeground(this.getTextColor());
        checkMute.setOpaque(false);
        checkMute.setFocusPainted(false);
        checkMute.setBounds(alignmentBottomCenter(150,50,0,25));
        checkMute.addActionListener(new TurnSound());
        this.add(checkMute);
        this.sfxSlider=new ESlider(alignmentCenter((int)(this.getScreenWidth()/3.75), 25,125,90));
        this.sfxSlider.setValue(GG2.getInstance().getMus().getSfxVol());
        this.musicSlider=new ESlider(alignmentCenter((int)(this.getScreenWidth()/3.75), 25,125,0));
        this.sfxSlider.setValue(GG2.getInstance().getMus().getSfxVol());
        this.add(this.sfxSlider);
        this.add(this.musicSlider);
        this.add(new EButton("x",15, this.getTextColor(),alignmentTopRight(42,42,10,10),new CloseScene()));
        this.add(new EButton("-",20, this.getTextColor(),alignmentCenter(50,50,-50,0),new DecreVolumeMusic()));
        this.add(new EButton("-",20, this.getTextColor(),alignmentCenter(50,50,-50,90),new DecreVolumeSFX()));
        this.add(new EButton("+",20, this.getTextColor(),alignmentRightCenter(50,50,50,0),new IncreVolumeMusic()));
        this.add(new EButton("+",20, this.getTextColor(),alignmentRightCenter(50,50,50,90),new IncreVolumeSFX()));
        this.add(new EImage("Music", 40,this.getTextColor(), alignmentCenter(200,75,-200,0)));
        this.add(new EImage("SFX", 40,this.getTextColor(), alignmentCenter(200,75,-200,90)));
        this.add(new EImage("Setting", 50,this.getTextColor(), alignmentTopCenter(500,75)));
        this.add(new EImage("bg/setting_back.jpg",alignmentCenter(this.getScreenWidth(), this.getScreenHeight())));
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
                flyEffect(g);
                if(!isAnimated()) GG2.getInstance().closeScene();
            };
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
