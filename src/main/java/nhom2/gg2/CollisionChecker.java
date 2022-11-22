/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhom2.gg2;

import entity.*;
import java.awt.*;
import object.OBJ_Coin;

/**
 *
 * @author ADMIN
 */
public class CollisionChecker {
    
    GamePanel gp;
    
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }
    //check trai phai
    public void checkTile(Entity entity){ // co di chuyen nhu player, monster, npc,...
        
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
        
        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;
        
        int tileNum1, tileNum2;
        
        switch(entity.direction){
            /*case"up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case"down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;*/
            case"left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case"right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if((gp.tileM.tile[tileNum1].name.equals("door") && gp.tileM.tile[tileNum1].collision == true)|| (gp.tileM.tile[tileNum2].name.equals("door") && gp.tileM.tile[tileNum2].collision == true)){
                    boolean b = false;
                    int tmpi = 0;
                    for(int index = 0; index < gp.player.inventory.size(); index++){
                        if(gp.player.inventory.get(index).name.equals("Key")) {
                            tmpi = index;
                            b = true;
                            break;
                        }
                    }
                    if(b == true){
                        gp.player.inventory.remove(tmpi);
                        if(gp.tileM.tile[tileNum1].name.equals("door")) gp.tileM.tile[tileNum1].collision = false;
                        if(gp.tileM.tile[tileNum2].name.equals("door")) gp.tileM.tile[tileNum2].collision = false;
                    }
                    else gp.ui.addMessage("ban can chia khoa de mo cong");
                }
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
        }
    }
    // check san nha
    public boolean checkFloor(Entity entity){
       
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height + 1;
        
        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
       
        int entityBottomRow = entityBottomWorldY / gp.tileSize;
        
        int tileNum1, tileNum2;
        tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
        tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
        if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
            return true;
        }
        return false;
    }
    
    //check tran nha
    public boolean checkRoof(Entity entity){
       
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
       
        
        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        
        
        int tileNum1, tileNum2;
        tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
        tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
        if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
            return true;
        }
        return false;
    }

    public int checkObject(Entity entity, boolean player){
        
        int index = 999;
        
        for(int i = 0; i < gp.obj[1].length; i++){
            
            if(gp.obj[gp.currentMap][i] != null){
                
                //Get entity's solid area position
                entity.solidArea.x += entity.worldX;
                entity.solidArea.y += entity.worldY;
                
                //Get the object's solid area position
                gp.obj[gp.currentMap][i].solidArea.x += gp.obj[gp.currentMap][i].worldX;
                gp.obj[gp.currentMap][i].solidArea.y += gp.obj[gp.currentMap][i].worldY;
                
                if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)){ // thay cho 1 doan code cua player, nhung ko thay dc
                    if(gp.obj[gp.currentMap][i].collision == true){
                        entity.collisionOn = true; // bo di, check collision nay phai khi di chuyen, nhay kho lam vl
                    }
                    if(player == true){ // neu cai khac cham vao thi ko tuong tac: npc, monster
                        index = i;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].solidAreaDefaultX;
                gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].solidAreaDefaultY;
            }
        }
        return index;
    }
    
    public int checkChest(Entity entity){
        
        int index = 999;
        
        for(int i = 0; i < gp.chest[1].length; i++){
            
            if(gp.chest[gp.currentMap][i] != null){
                
                //Get entity's solid area position
                entity.solidArea.x += entity.worldX;
                entity.solidArea.y += entity.worldY;
                
                //Get the object's solid area position
                gp.chest[gp.currentMap][i].solidArea.x += gp.chest[gp.currentMap][i].worldX;
                gp.chest[gp.currentMap][i].solidArea.y += gp.chest[gp.currentMap][i].worldY;
                
                if(entity.solidArea.intersects(gp.chest[gp.currentMap][i].solidArea)){ // thay cho 1 doan code cua player, nhung ko thay dc
                   
                        index = i;
                    
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.chest[gp.currentMap][i].solidArea.x = gp.chest[gp.currentMap][i].solidAreaDefaultX;
                gp.chest[gp.currentMap][i].solidArea.y = gp.chest[gp.currentMap][i].solidAreaDefaultY;
            }
        }
        return index;
    }
    
    public int checkCoin(Entity entity){
        int index = 999;
        
        for(int i = 0; i < gp.coin.size(); i++){
            
            if(gp.coin.get(i) != null){
                
                //Get entity's solid area position
                entity.solidArea.x += entity.worldX;
                entity.solidArea.y += entity.worldY;
                
                //Get the object's solid area position
                gp.coin.get(i).solidArea.x += gp.coin.get(i).worldX;
                gp.coin.get(i).solidArea.y += gp.coin.get(i).worldY;
                
                if(entity.solidArea.intersects(gp.coin.get(i).solidArea)){ // thay cho 1 doan code cua player, nhung ko thay dc
                    index = i;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.coin.get(i).solidArea.x = gp.coin.get(i).solidAreaDefaultX;
                gp.coin.get(i).solidArea.y = gp.coin.get(i).solidAreaDefaultY;
            }
        }
        return index;
    }
    
    //NPC OR MONSTER
    public int checkEntity(Entity entity, Entity[][] target){
        
        int index = 999;
        
        for(int i = 0; i < target[1].length; i++){
            
            if(target[gp.currentMap][i] != null){
                
                //Get entity's solid area position
                entity.solidArea.x += entity.worldX;
                entity.solidArea.y += entity.worldY;
                
                //Get the target's solid area position
                target[gp.currentMap][i].solidArea.x += target[gp.currentMap][i].worldX;
                target[gp.currentMap][i].solidArea.y += target[gp.currentMap][i].worldY;
                
                if(entity.solidArea.intersects(target[gp.currentMap][i].solidArea)){ 
                    index = i;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefaultX;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefaultY;
            }
        }
        return index;
    }
    
    public boolean checkPlayer(Entity entity){
        boolean ans = false;
        //Get entity's solid area position
        entity.solidArea.x += entity.worldX;
        entity.solidArea.y += entity.worldY;
                
        //Get the target's solid area position
        gp.player.solidArea.x += gp.player.worldX;
        gp.player.solidArea.y += gp.player.worldY;
                
        if(entity.solidArea.intersects(gp.player.solidArea)){ 
                ans = true;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            
    
        return ans;
    }
    
    public boolean detectPlayer(Rectangle r){
        boolean ans = false;
        gp.player.solidArea.x += gp.player.worldX;
        gp.player.solidArea.y += gp.player.worldY;
                
        if(r.intersects(gp.player.solidArea)){ 
                ans = true;
        }
        
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        if(gp.player.hide == true) ans = false;
        return ans;
    }
}
