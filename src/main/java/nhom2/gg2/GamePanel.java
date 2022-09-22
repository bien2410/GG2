/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhom2.gg2;


/**
 *
 * @author ADMIN
 */
import java.awt.*;
import javax.swing.*;
public class GamePanel extends JPanel {
    
    // cai dat man hinh
    final int originalTileSize = 16; // anh se la 16 pixels * 16 pixels
    final int scale = 3; // chi so phong to hinh anh khi vao game
    final int tileSize = originalTileSize * scale; // kich co hinh anh sau khi phong to
    // co the thay doi kich thuoc tuy vao anh dau vao
    
    final int maxScreenCol = 24;
    final int maxScreenRow = 16;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    //chia man hinh game thanh 24tileSize * 16tileSize
    
    
    
    public GamePanel(){
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //kich co cua GamePanel
        this.setBackground(Color.black); // mau cua nen 
        this.setDoubleBuffered(true); // cho phep su dung phuong thuc draw...
    }
}
