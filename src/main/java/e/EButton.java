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
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;
public class EButton extends JButton{
    private ImageIcon bg;
    public EButton(String text,Integer textsize,Color color,String img,int x,int y,int w,int h,ActionListener ac) {
        super(text);
        if(img==null) {
            this.setBackground(Color.black);
        }
        else {
            bg=new ImageIcon(img);
            bg.setImage(bg.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
            this.setIcon(bg);
            this.setHorizontalTextPosition(JButton.CENTER);
            this.setVerticalTextPosition(JButton.CENTER);
            this.setDisabledIcon(bg);
        }
        this.setBounds(x,y,w,h);
        this.setForeground(color);
        this.setFocusPainted(false);;
        this.setOpaque(false);
        if(textsize!=null) this.setFont(new Font(null,Font.PLAIN,textsize));
        this.setUI(new MetalButtonUI() {
            protected Color getDisabledTextColor() {
                return Color.RED;
            }
            protected Color getSelectColor(){
                 return new Color(255,0,0,75);
            }
        });
        this.addActionListener(ac);
    }
    public Image getImage() {
        return bg.getImage();
    }
    public EButton(String text,Integer textsize,Color color,int x,int y,int w,int h,ActionListener ac) {
        this(text,textsize,color,null,x,y,w,h,ac);
    }
    public EButton(String text,Integer textsize,int x,int y,int w,int h,ActionListener ac) {
        this(text,textsize,Color.BLACK,null,x,y,w,h,ac);
    }
    public EButton(String img,int x,int y,int w,int h,ActionListener ac){
        this(null,null,null,img,x,y,w,h,ac);
    }
    public EButton(String text,Integer textsize,Color color,String img,Rectangle r,ActionListener ac){
        this(text,textsize,color,img,r.x,r.y,r.width,r.height,ac);
    }
    public EButton(String text,Integer textsize,Color color,Rectangle r ,ActionListener ac) {
        this(text,textsize,color,null,r.x,r.y,r.width,r.height,ac);
    }
    public EButton(String text,Integer textsize,Rectangle r ,ActionListener ac) {
        this(text,textsize,Color.BLACK,null,r.x,r.y,r.width,r.height,ac);
    }
    public EButton(String img,Rectangle r,ActionListener ac){
        this(null,null,null,img,r.x,r.y,r.width,r.height,ac);
    }
    public EButton(Rectangle r,ActionListener ac){
        this(null,null,null,null,r.x,r.y,r.width,r.height,ac);
    }
}
