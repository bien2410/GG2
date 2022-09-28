/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhom2.gg2;

import java.awt.*;
import java.awt.image.*;

/**
 *
 * @author ADMIN
 */
//phong to tile, object,... trc khi in ra de chay nhanh hon
public class UtilityTool {
    
    public BufferedImage scaleImage(BufferedImage original, int width, int height){
        
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();
        
        return scaledImage;
    }
}
