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
    public Tile[] tile;
    public int mapTileNum[][];
    
    public TileManager(GamePanel gp){
        
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        
        getTileImage();
        //dien duong dan
        loadMap("/res/maps/world01.txt");
    }
    
    // nhap anh tile vao
    public void getTileImage(){
        try{
            //dien duong dan
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/earth.png"));
            tile[0].collision = true;
            
            tile[4] = new Tile();
            tile[4].collision = false;
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
            
            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                
                String line = br.readLine();
                
                while(col < gp.maxWorldCol){
                    
                    String numbers[] = line.split(" ");
                    
                    int num = Integer.parseInt(numbers[col]);
                    
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
                
            }
            br.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    //ve tile
    public void draw(Graphics2D g2){
        
        //draw with camera
        
        int worldCol = 0;
        int worldRow = 0;
        
        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            
            int tileNum = mapTileNum[worldCol][worldRow];
            
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            
            //ve tile trong man hinh
            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX < gp.player.worldX + (gp.screenWidth - gp.player.screenX) &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY < gp.player.worldY + (gp.screenHeight - gp.player.screenY)){
               if(tileNum != 4)
                    g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            worldCol++;
            
            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
