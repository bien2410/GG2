/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tile;

import java.awt.*;
import java.io.*;
import javax.imageio.*;
import nhom2.gg2.*;

/**
 *
 * @author ADMIN
 */
public class TileManager {
    
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];
    
    public TileManager(GamePanel gp){
        
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        
        getTileImage();
        //dien duong dan
        //loadMap("");
    }
    
    // nhap anh tile vao
    public void getTileImage(){
        try{
            //dien duong dan
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream(""));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    //tai map tu txt
    public void loadMap(String filePath){
        try{

            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            int col = 0;
            int row = 0;
            
            while(col < gp.maxScreenCol && row < gp.maxScreenRow){
                
                String line = br.readLine();
                
                while(col < gp.maxScreenCol){
                    
                    String numbers[] = line.split(" ");
                    
                    int num = Integer.parseInt(numbers[col]);
                    
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol){
                    col = 0;
                    row++;
                }
                
                br.close();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    //ve tile
    public void draw(Graphics2D g2){
        
        g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
    }
}
