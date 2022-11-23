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
public class InfoMenu extends Scene {
    public InfoMenu() {
        super(.75f,GameSetting.SCREEN_WIDTH,GameSetting.SCREEN_HEIGHT);
        this.setTextColor(GameSetting.DF_TEXT_COLOR);
        this.add(new EButton("x",15, this.getTextColor(),alignmentTopRight(42,42,10,10),new CloseScene()));
        this.add(new EImage("Information", 50,this.getTextColor(), alignmentTopCenter(500,75)));
        this.add(new EImage("<html>Phạm Trọng Biên<br>Vũ Huy Trường<br>Lê Văn Dũng<br>Phan Minh Tiến<br>Lưu Văn Hoàng Hiệp<br>          <html>", 20,this.getTextColor(), alignmentTopCenter(500,280,0,100)));
        this.add(new EImage("bg/info_back.jpg",alignmentCenter(this.getScreenWidth(), this.getScreenHeight())));
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
}
