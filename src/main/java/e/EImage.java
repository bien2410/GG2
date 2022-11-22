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
import javax.imageio.*;
import javax.swing.*;
import nhom2.gg2.*;
public class EImage extends JLabel{
    
    private ImageIcon bg;
    private boolean border;
    public EImage(String text,Integer textsize,Color color,String img,int x,int y,int w,int h) {
        super();
        this.setBounds(x,y,w,h);
        if(img!=null) {
            bg=new ImageIcon(img);
            bg.setImage(bg.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
            this.setIcon(bg);
            this.setHorizontalTextPosition(JLabel.CENTER);
            this.setVerticalTextPosition(JLabel.CENTER);
            this.setDisabledIcon(bg);
        }
        
        this.border=true;
        this.setOpaque(this.getIcon()==null);
        this.setBackground(new Color(0,0,0,100));
        this.setForeground(color);
        this.setText(text);
        if(textsize!=null) this.setFont(new Font(null,Font.PLAIN,textsize));
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }
    public void setBorder(boolean b) {
        this.border=b;
    }
    private void drawBorder(Graphics a) {
        Graphics2D g=(Graphics2D) a;
        //g.fillRoundRect(this.getX(),this.getY(),this.getWidth(),this.getHeight(),35,35);
        g.setStroke(new BasicStroke(5));
        g.setColor(GameSetting.DF_TEXT_COLOR);
        g.drawRect(0,0,this.getWidth(),this.getHeight());
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(this.border) this.drawBorder(g);
    }
    public void hideBG() {
        this.setBackground(new Color(0,0,0,0));
    }
    public void setAli(int a) {
        this.setHorizontalAlignment(a);
    }
    public Image getImage() {
        return bg.getImage();
    }
    public EImage(String img,int x,int y,int w,int h) {
        this(null,null,null,img,x,y,w,h);
    }
    public EImage(String text,Integer textsize,Color color,int x,int y,int w,int h){
        this(text,textsize,color,null,x,y,w,h);
    }
    public EImage(String text,Integer textsize,int x,int y,int w,int h){
        this(text,textsize,Color.black,null,x,y,w,h);
    }
    public EImage(String text,Integer textsize,Color color,String img,Rectangle r){
        this(text,textsize,color,img,r.x,r.y,r.width,r.height);
    }
    public EImage(String img,Rectangle r) {
        this(null,null,null,img,r.x,r.y,r.width,r.height);
    }
    public EImage(String text,Integer textsize,Color color,Rectangle r){
        this(text,textsize,color,null,r.x,r.y,r.width,r.height);
    }
    public EImage(String text,Integer textsize,Rectangle r){
        this(text,textsize,Color.black,null,r.x,r.y,r.width,r.height);
    }
    public static Image resize(Image a,float size){
        return a.getScaledInstance((int)(a.getWidth(null)*size), (int)(a.getHeight(null)*size), Image.SCALE_DEFAULT);
    }
}
