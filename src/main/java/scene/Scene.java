/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scene;

/**
 *
 * @author ADMIN
 */
import java.awt.*;
import javax.swing.*;
import java.util.*;
import nhom2.gg2.*;
public abstract class Scene extends JComponent {
    protected int screenWidth;
    protected int screenHeight;
    protected int pos_x;
    protected int pos_y;
    private float size = 1;
    private Color textcolor = Color.black;
    private boolean animated;
    private ArrayList<Image> img;
    private int animated_alpha;
    private int pos_x_animated;
    private int pos_y_animated;
    protected Animation animatedFunc;
    
    public Scene(){
        super();
    }
    
    public Scene(int width, int height){
        this(1, width, height);
    }
    
    public Scene(float size, int width, int height){
        super();
        this.size = size;
        this.screenWidth = (int)(width * size);
        this.screenHeight = (int)(height * size);
        this.setSize(this.screenWidth, this.screenHeight);
        this.pos_x = centerPosX();
        this.pos_y = centerPosY();
        this.setAnimated(false);
        this.setPreferredSize(new Dimension(this.screenWidth, this.screenHeight));
        this.setLayout(null);
        img = new ArrayList<>();
    }
    
    public void setImage(){
        for(var comp : this.getComponents()){
            try{
                Image tmp = (Image) comp.getClass().getDeclaredMethod("getImage").invoke(comp);
                if(tmp != null) img.add(tmp);
            }
            catch(Exception e){
                //e.printStackTrace();
            }
        }
    }
    
    protected int centerPosX(){
        return GameSetting.SCREEN_WIDTH / 2 - this.screenWidth / 2;
    }
    protected int centerPosY() {
        return GameSetting.SCREEN_HEIGHT/2-this.screenHeight/2;
    }
    public void setTextColor(Color color) {
        this.textcolor=color;
    }
    public Color getTextColor() {
        return this.textcolor;
    }
    public int getScreenWidth(){
        return this.screenWidth;
    }
    public int getScreenHeight() {
        return this.screenHeight;
    }
    public float getScreenSize() {
        return this.size;
    }
    
    //animated
    public static interface Animation {
        public void animated(Graphics g);
    }
    public void animated() {
        this.animated_alpha=255;
        this.pos_x_animated=this.pos_x;
        this.pos_y_animated=-this.screenHeight;
    }
    public boolean isAnimated() {
        return this.animated;
    }
    public void setAnimated(boolean b) {
        this.animated=b;
        this.setEnabled(!b);
    }
    public void paint(Graphics g) {
        if(animatedFunc==null) super.paint(g);
        else animatedFunc.animated(g);
    }
    public void appearEffect(Graphics a) {
        if(animated) {
            Graphics2D g=(Graphics2D) a;
            for(var i:img)
                g.drawImage(i,this.pos_x,this.pos_y,null);
            g.setColor(new Color(0,0,0,animated_alpha));
            g.fillRect(pos_x, pos_y, this.screenWidth, this.screenHeight);
            this.repaint();
            animated_alpha-=2;
            if(animated_alpha<0) {
                this.setAnimated(false);
                animated_alpha=255;
                animatedFunc=null;
            } 
        }
    }
    public void disapearEffect(Graphics a) {
        if(animated) {
            Graphics2D g=(Graphics2D) a;
            for(var i:img) g.drawImage(i,this.pos_x,this.pos_y,null);
            g.setColor(new Color(0,0,0,255-animated_alpha));
            g.fillRect(pos_x, pos_y, this.screenWidth, this.screenHeight);
            this.repaint();
            animated_alpha-=4;
            if(animated_alpha<0) {
                this.setAnimated(false);
                this.animated_alpha=255;
                animatedFunc=null;
            } 
        }
    }
    public void fallEffect(Graphics a){
        if(animated) {
            Graphics2D g=(Graphics2D) a;
            for (var i:img) g.drawImage(i,this.pos_x_animated,this.pos_y_animated,null);
            this.pos_y_animated+=10;
            this.repaint();
            if(this.pos_y_animated>=this.pos_y) {
                this.setAnimated(false);
                this.pos_y_animated=this.pos_y;
                animatedFunc=null;
            }
        }
    }
    public void fallv2Effect(Graphics a) {
        if(animated) {
            Graphics2D g=(Graphics2D) a;
            for (var i:img) g.drawImage((Image) i,this.pos_x_animated,this.pos_y_animated,null);
            this.pos_y_animated+=7;
            this.repaint();
            if(this.pos_y_animated>=this.screenHeight+this.pos_y) {
                this.setAnimated(false);
                animatedFunc=null;
            }
        }
    }
    public void flyEffect(Graphics a) {
        if(animated) {
            Graphics2D g=(Graphics2D) a;
            for (var i:img) g.drawImage((Image) i,this.pos_x_animated,this.pos_y_animated,null);
            this.pos_y_animated-=7;
            this.repaint();
            if(this.pos_y_animated<=-this.screenHeight) {
                this.setAnimated(false);
                animatedFunc=null;
            }
        }
    }
    
    @Override
    public void setEnabled(boolean b){
        for(var comp : this.getComponents()) comp.setEnabled(b);
        super.setEnabled(b);
    }
    
    //alignment
    protected Rectangle alignmentCenter( int w,int h,int a,int b) {
        return new Rectangle(this.pos_x+this.screenWidth/2-w/2+a,this.pos_y+ this.screenHeight/2-h/2+b,w,h);
    }
    protected Rectangle alignmentTopLeft( int w,int h, int a, int b) {
        return new Rectangle(this.pos_x+a,this.pos_y+b,w,h);
    }
    protected Rectangle alignmentTopRight( int w,int h, int a, int b) {
        return new Rectangle(this.pos_x+ this.screenWidth-w-a,this.pos_y+b,w,h);
    }
    protected Rectangle alignmentBottomLeft( int w,int h, int a, int b) {
        return new Rectangle(this.pos_x+a,this.pos_y+ this.screenHeight-h-b,w,h);
    }
    protected Rectangle alignmentBottomRight( int w,int h, int a, int b) {
        return new Rectangle(this.pos_x+ this.screenWidth-w-a,this.pos_y+ this.screenHeight-h-b,w,h);
    }
    protected Rectangle alignmentTopCenter( int w,int h,int a,int b) {
        return new Rectangle(this.pos_x+ this.screenWidth/2-w/2+a,this.pos_y+b,w,h);
    }
    protected Rectangle alignmentBottomCenter( int w,int h,int a,int b) {
        return new Rectangle(this.pos_x+ this.screenWidth/2-w/2+a,this.pos_y+ this.screenHeight-h-b,w,h);
    }
    protected Rectangle alignmentLeftCenter( int w,int h, int a,int b) {
        return new Rectangle(this.pos_x+a,this.pos_y+ this.screenHeight/2-h/2+b,w,h);
    }
    protected Rectangle alignmentRightCenter( int w,int h, int a,int b) {
        return new Rectangle( this.pos_x+this.screenWidth-w-a,this.pos_y+ this.screenHeight/2-h/2+b,w,h);
    }
    //override alignment
    protected Rectangle alignmentCenter() {
        return alignmentCenter(this.getScreenWidth(), this.getScreenHeight(),0,0);
    }
    protected Rectangle alignmentCenter( int w,int h) {
        return alignmentCenter(w, h,0,0);
    }
    protected Rectangle alignmentTopLeft( int w,int h) {
        return alignmentTopLeft(w,h,0,0);
    }
    protected Rectangle alignmentTopRight( int w,int h) {
        return alignmentTopRight(w,h,0,0);
    }
    protected Rectangle alignmentBottomLeft( int w,int h) {
        return alignmentBottomLeft(w,h,0,0);
    }
    protected Rectangle alignmentBottomRight( int w,int h) {
        return alignmentBottomRight(w,h,0,0);
    }
    protected Rectangle alignmentTopCenter( int w,int h) {
        return alignmentTopCenter( w,h,0,0);
    }
    protected Rectangle alignmentBottomCenter( int w,int h) {
        return alignmentBottomCenter(w,h,0,0);
    }
    protected Rectangle alignmentLeftCenter( int w,int h) {
        return alignmentLeftCenter(w,h,0,0);
    }
    protected Rectangle alignmentRightCenter( int w,int h) {
        return alignmentRightCenter(w,h,0,0);
    }
}
