/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package e;

/**
 *
 * @author ADMIN
 */
import java.awt.*;
import javax.swing.*;
public class ESlider extends JSlider{
    public ESlider(int x,int y,int w,int h) {
        super(JSlider.HORIZONTAL,0,10,7);
        this.setBounds(x,y,w,h);
        this.setBackground(new Color(0,0,0)); 
    }
    public ESlider(Rectangle r) {
        this(r.x,r.y,r.width,r.height);
    }
    public Image getImage(){
        return null;
    }
}
