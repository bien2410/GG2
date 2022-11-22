/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhom2.gg2;

import entity.*;
import monster.*;
import object.*;

/**
 *
 * @author ADMIN
 */
// cai dat bat cu cai gi o day: object, npc, monster
public class AssetSetter {
    
    GamePanel gp;
    
    public int monsterX[][] = new int[10][20];
    public int monsterY[][] = new int[10][20];
    public int typeMonster[][] = new int[10][20];
    
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    
    public void setObject(){
        int mapNum = 0;
        int i = 0;
        gp.obj[mapNum][i] = new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX = 9 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 44 * gp.tileSize;
        i++;
        
        gp.obj[mapNum][i] = new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX = 16 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 44 * gp.tileSize;
        i++;
        
        gp.obj[mapNum][i] = new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX = 16 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 38 * gp.tileSize;
        i++;
        
        gp.obj[mapNum][i] = new OBJ_Axe(gp);
        gp.obj[mapNum][i].worldX = 11 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 44 * gp.tileSize;
        i++;
        
        gp.obj[mapNum][i] = new OBJ_Shield_Blue(gp);
        gp.obj[mapNum][i].worldX = 10 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 44 * gp.tileSize;
        i++;
        
        /*gp.obj[i] = new OBJ_Potion_Red(gp);
        gp.obj[i].worldX = 13 * gp.tileSize;
        gp.obj[i].worldY = 44 * gp.tileSize;
        i++;*/
        mapNum = 1;
    }
    
    public void setChest(){
        int mapNum = 0;
        int i = 0;
        gp.chest[mapNum][i] = new OBJ_Chest(gp, 0);
        gp.chest[mapNum][i].worldX = 9 * gp.tileSize;
        gp.chest[mapNum][i].worldY = 44 * gp.tileSize;
        i++;
        mapNum = 1;
    }
    
    public void setMonster(){
        
        int mapNum = 1;
        int i = 0;
        monsterX[mapNum][i] = 7 * gp.tileSize;
        monsterY[mapNum][i] = 38 * gp.tileSize;
        typeMonster[mapNum][i] = 0;
        resetMonster(mapNum, i, typeMonster[mapNum][i]);
        setMonsterR(mapNum, i, typeMonster[mapNum][i]);
        i++;
        monsterX[mapNum][i] = 15 * gp.tileSize;
        monsterY[mapNum][i] = 38 * gp.tileSize;
        typeMonster[mapNum][i] = 0;
        resetMonster(mapNum, i, typeMonster[mapNum][i]);
        setMonsterR(mapNum, i, typeMonster[mapNum][i]);
        i++;
        monsterX[mapNum][i] = 24 * gp.tileSize;
        monsterY[mapNum][i] = 38 * gp.tileSize;
        typeMonster[mapNum][i] = 0;
        resetMonster(mapNum, i, typeMonster[mapNum][i]);
        setMonsterR(mapNum, i, typeMonster[mapNum][i]);
        i++;
        monsterX[mapNum][i] = 11 * gp.tileSize;
        monsterY[mapNum][i] = 36 * gp.tileSize;
        typeMonster[mapNum][i] = 1;
        resetMonster(mapNum, i, typeMonster[mapNum][i]);
        setMonsterR(mapNum, i, typeMonster[mapNum][i]);
        i++;
        monsterX[mapNum][i] = 19 * gp.tileSize;
        monsterY[mapNum][i] = 36 * gp.tileSize;
        typeMonster[mapNum][i] = 1;
        resetMonster(mapNum, i, typeMonster[mapNum][i]);
        setMonsterR(mapNum, i, typeMonster[mapNum][i]);
        i++;
        monsterX[mapNum][i] = 28 * gp.tileSize;
        monsterY[mapNum][i] = 36 * gp.tileSize;
        typeMonster[mapNum][i] = 1;
        resetMonster(mapNum, i, typeMonster[mapNum][i]);
        setMonsterR(mapNum, i, typeMonster[mapNum][i]);
        i++;
        monsterX[mapNum][i] = 35 * gp.tileSize;
        monsterY[mapNum][i] = 38 * gp.tileSize;
        typeMonster[mapNum][i] = 0;
        resetMonster(mapNum, i, typeMonster[mapNum][i]);
        setMonsterR(mapNum, i, typeMonster[mapNum][i]);
        i++;
        monsterX[mapNum][i] = 31 * gp.tileSize;
        monsterY[mapNum][i] = 32 * gp.tileSize;
        typeMonster[mapNum][i] = 5;
        resetMonster(mapNum, i, typeMonster[mapNum][i]);
        setMonsterR(mapNum, i, typeMonster[mapNum][i]);
        i++;
        monsterX[mapNum][i] = 35 * gp.tileSize;
        monsterY[mapNum][i] = 30 * gp.tileSize;
        typeMonster[mapNum][i] = 5;
        resetMonster(mapNum, i, typeMonster[mapNum][i]);
        setMonsterR(mapNum, i, typeMonster[mapNum][i]);
        i++;
        monsterX[mapNum][i] = 39 * gp.tileSize;
        monsterY[mapNum][i] = 28 * gp.tileSize;
        typeMonster[mapNum][i] = 5;
        resetMonster(mapNum, i, typeMonster[mapNum][i]);
        setMonsterR(mapNum, i, typeMonster[mapNum][i]);
        i++;
        
        mapNum = 2;
        i = 0;
        monsterX[mapNum][i] = 6 * gp.tileSize;
        monsterY[mapNum][i] = 28 * gp.tileSize;
        typeMonster[mapNum][i] = 2;
        resetMonster(mapNum, i, typeMonster[mapNum][i]);
        setMonsterR(mapNum, i, typeMonster[mapNum][i]);
        i++;
        monsterX[mapNum][i] = 15 * gp.tileSize;
        monsterY[mapNum][i] = 28 * gp.tileSize;
        typeMonster[mapNum][i] = 2;
        resetMonster(mapNum, i, typeMonster[mapNum][i]);
        setMonsterR(mapNum, i, typeMonster[mapNum][i]);
        i++;
        monsterX[mapNum][i] = 20 * gp.tileSize;
        monsterY[mapNum][i] = 28 * gp.tileSize;
        typeMonster[mapNum][i] = 3;
        resetMonster(mapNum, i, typeMonster[mapNum][i]);
        setMonsterR(mapNum, i, typeMonster[mapNum][i]);
        i++;
        monsterX[mapNum][i] = 28 * gp.tileSize;
        monsterY[mapNum][i] = 28 * gp.tileSize;
        typeMonster[mapNum][i] = 2;
        resetMonster(mapNum, i, typeMonster[mapNum][i]);
        setMonsterR(mapNum, i, typeMonster[mapNum][i]);
        i++;
        monsterX[mapNum][i] = 31 * gp.tileSize;
        monsterY[mapNum][i] = 28 * gp.tileSize;
        typeMonster[mapNum][i] = 7;
        resetMonster(mapNum, i, typeMonster[mapNum][i]);
        setMonsterR(mapNum, i, typeMonster[mapNum][i]);
        i++;
        monsterX[mapNum][i] = 38 * gp.tileSize;
        monsterY[mapNum][i] = 28 * gp.tileSize;
        typeMonster[mapNum][i] = 3;
        resetMonster(mapNum, i, typeMonster[mapNum][i]);
        setMonsterR(mapNum, i, typeMonster[mapNum][i]);
        i++;
        monsterX[mapNum][i] = 42 * gp.tileSize;
        monsterY[mapNum][i] = 28 * gp.tileSize;
        typeMonster[mapNum][i] = 7;
        resetMonster(mapNum, i, typeMonster[mapNum][i]);
        setMonsterR(mapNum, i, typeMonster[mapNum][i]);
        i++;
        
        mapNum = 3;
        i = 0;
        monsterX[mapNum][i] = 6 * gp.tileSize;
        monsterY[mapNum][i] = 44 * gp.tileSize;
        typeMonster[mapNum][i] = 4;
        resetMonster(mapNum, i, typeMonster[mapNum][i]);
        setMonsterR(mapNum, i, typeMonster[mapNum][i]);
        i++;
        monsterX[mapNum][i] = 11 * gp.tileSize;
        monsterY[mapNum][i] = 44 * gp.tileSize;
        typeMonster[mapNum][i] = 4;
        resetMonster(mapNum, i, typeMonster[mapNum][i]);
        setMonsterR(mapNum, i, typeMonster[mapNum][i]);
        i++;
        monsterX[mapNum][i] = 28 * gp.tileSize;
        monsterY[mapNum][i] = 44 * gp.tileSize;
        typeMonster[mapNum][i] = 4;
        resetMonster(mapNum, i, typeMonster[mapNum][i]);
        setMonsterR(mapNum, i, typeMonster[mapNum][i]);
        i++;
        monsterX[mapNum][i] = 34 * gp.tileSize;
        monsterY[mapNum][i] = 44 * gp.tileSize;
        typeMonster[mapNum][i] = 4;
        resetMonster(mapNum, i, typeMonster[mapNum][i]);
        setMonsterR(mapNum, i, typeMonster[mapNum][i]);
        i++;
        monsterX[mapNum][i] = 40 * gp.tileSize;
        monsterY[mapNum][i] = 44 * gp.tileSize;
        typeMonster[mapNum][i] = 4;
        resetMonster(mapNum, i, typeMonster[mapNum][i]);
        setMonsterR(mapNum, i, typeMonster[mapNum][i]);
        i++;
        monsterX[mapNum][i] = 17 * gp.tileSize;
        monsterY[mapNum][i] = 41 * gp.tileSize;
        typeMonster[mapNum][i] = 8;
        resetMonster(mapNum, i, typeMonster[mapNum][i]);
        setMonsterR(mapNum, i, typeMonster[mapNum][i]);
        i++;
        monsterX[mapNum][i] = 22 * gp.tileSize;
        monsterY[mapNum][i] = 41 * gp.tileSize;
        typeMonster[mapNum][i] = 8;
        resetMonster(mapNum, i, typeMonster[mapNum][i]);
        setMonsterR(mapNum, i, typeMonster[mapNum][i]);
        i++;
        monsterX[mapNum][i] = 6 * gp.tileSize;
        monsterY[mapNum][i] = 36 * gp.tileSize;
        typeMonster[mapNum][i] = 6;
        resetMonster(mapNum, i, typeMonster[mapNum][i]);
        setMonsterR(mapNum, i, typeMonster[mapNum][i]);
        i++;
        monsterX[mapNum][i] = 12 * gp.tileSize;
        monsterY[mapNum][i] = 34 * gp.tileSize;
        typeMonster[mapNum][i] = 6;
        resetMonster(mapNum, i, typeMonster[mapNum][i]);
        setMonsterR(mapNum, i, typeMonster[mapNum][i]);
        i++;
        monsterX[mapNum][i] = 12 * gp.tileSize;
        monsterY[mapNum][i] = 28 * gp.tileSize;
        typeMonster[mapNum][i] = 6;
        resetMonster(mapNum, i, typeMonster[mapNum][i]);
        setMonsterR(mapNum, i, typeMonster[mapNum][i]);
        i++;
        monsterX[mapNum][i] = 8 * gp.tileSize;
        monsterY[mapNum][i] = 24 * gp.tileSize;
        typeMonster[mapNum][i] = 6;
        resetMonster(mapNum, i, typeMonster[mapNum][i]);
        setMonsterR(mapNum, i, typeMonster[mapNum][i]);
        i++;
        monsterX[mapNum][i] = 26 * gp.tileSize;
        monsterY[mapNum][i] = 34 * gp.tileSize;
        typeMonster[mapNum][i] = 6;
        resetMonster(mapNum, i, typeMonster[mapNum][i]);
        setMonsterR(mapNum, i, typeMonster[mapNum][i]);
        i++;
        monsterX[mapNum][i] = 36 * gp.tileSize;
        monsterY[mapNum][i] = 37 * gp.tileSize;
        typeMonster[mapNum][i] = 6;
        resetMonster(mapNum, i, typeMonster[mapNum][i]);
        setMonsterR(mapNum, i, typeMonster[mapNum][i]);
        i++;
        monsterX[mapNum][i] = 39 * gp.tileSize;
        monsterY[mapNum][i] = 27 * gp.tileSize;
        typeMonster[mapNum][i] = 8;
        resetMonster(mapNum, i, typeMonster[mapNum][i]);
        setMonsterR(mapNum, i, typeMonster[mapNum][i]);
        i++;
        monsterX[mapNum][i] = 17 * gp.tileSize;
        monsterY[mapNum][i] = 18 * gp.tileSize;
        typeMonster[mapNum][i] = 8;
        resetMonster(mapNum, i, typeMonster[mapNum][i]);
        setMonsterR(mapNum, i, typeMonster[mapNum][i]);
        i++;
        monsterX[mapNum][i] = 18 * gp.tileSize;
        monsterY[mapNum][i] = 18 * gp.tileSize;
        typeMonster[mapNum][i] = 8;
        resetMonster(mapNum, i, typeMonster[mapNum][i]);
        setMonsterR(mapNum, i, typeMonster[mapNum][i]);
        i++;
        
    }
    
    public void setNpc(){
        int mapNum = 0;
        int i = 0;
        gp.npc[mapNum][i] = new NPC(gp);
        gp.npc[mapNum][i].worldX = 15 * gp.tileSize;
        gp.npc[mapNum][i].worldY = 44 * gp.tileSize;
        i++;
        gp.npc[0][0].setBeginConversation();
        gp.npc[0][0].setDialogue(0);
        gp.npc[mapNum][i] = new MachineNPC(gp);
        gp.npc[mapNum][i].worldX = 20 * gp.tileSize;
        gp.npc[mapNum][i].worldY = 44 * gp.tileSize;
        i++;
    }
    
    public void resetMonster(int mapNum, int i, int j){
        switch(j){
            case 0:
                gp.monster[mapNum][i] = new MON_GreenSlime(gp, monsterX[mapNum][i], monsterY[mapNum][i]);
                break;
            case 1:
                gp.monster[mapNum][i] = new meleMonster(gp, monsterX[mapNum][i], monsterY[mapNum][i]);
                break;
            case 2:
                gp.monster[mapNum][i] = new followMonster(gp, monsterX[mapNum][i], monsterY[mapNum][i]);
                break;
            case 3:
                gp.monster[mapNum][i] = new meleFollowMonster(gp, monsterX[mapNum][i], monsterY[mapNum][i]);
                break;
            case 4:
                gp.monster[mapNum][i] = new explodeMonster(gp, monsterX[mapNum][i], monsterY[mapNum][i]);
                break;
            case 5:
                gp.monster[mapNum][i] = new flyMonster(gp, monsterX[mapNum][i], monsterY[mapNum][i]);
                break;
            case 6:
                gp.monster[mapNum][i] = new shootMonster(gp, monsterX[mapNum][i], monsterY[mapNum][i]);
                break;
            case 7:
                gp.monster[mapNum][i] = new simpleShootMonster(gp, monsterX[mapNum][i], monsterY[mapNum][i]);
                break;
            case 8:
                gp.monster[mapNum][i] = new followShootMonster(gp, monsterX[mapNum][i], monsterY[mapNum][i]);
                break;
        }
    }
    
    public void setMonsterR(int mapNum, int i, int j){
        switch(j){
            case 0:
                gp.monsterR[mapNum][i] = new MON_GreenSlime(gp, monsterX[mapNum][i], monsterY[mapNum][i]);
                break;
            case 1:
                gp.monsterR[mapNum][i] = new meleMonster(gp, monsterX[mapNum][i], monsterY[mapNum][i]);
                break;
            case 2:
                gp.monsterR[mapNum][i] = new followMonster(gp, monsterX[mapNum][i], monsterY[mapNum][i]);
                break;
            case 3:
                gp.monsterR[mapNum][i] = new meleFollowMonster(gp, monsterX[mapNum][i], monsterY[mapNum][i]);
                break;
            case 4:
                gp.monsterR[mapNum][i] = new explodeMonster(gp, monsterX[mapNum][i], monsterY[mapNum][i]);
                break;
            case 5:
                gp.monsterR[mapNum][i] = new flyMonster(gp, monsterX[mapNum][i], monsterY[mapNum][i]);
                break;
            case 6:
                gp.monsterR[mapNum][i] = new shootMonster(gp, monsterX[mapNum][i], monsterY[mapNum][i]);
                break;
            case 7:
                gp.monsterR[mapNum][i] = new simpleShootMonster(gp, monsterX[mapNum][i], monsterY[mapNum][i]);
                break;
            case 8:
                gp.monsterR[mapNum][i] = new followShootMonster(gp, monsterX[mapNum][i], monsterY[mapNum][i]);
                break;
        }
    }
    
    public void setCoin(int x, int y, int c){
        OBJ_Coin coin = new OBJ_Coin(gp);
        coin.worldX = x;
        coin.worldY = y;
        coin.coin = c;
        gp.coin.add(coin);
    }
    
    public void setItem(int x, int y, int z){
        
        Entity item = new Entity(gp);
        switch (z) {
            case 0:
                item = new OBJ_Key(gp);
                break;
            case 1:
                item = new OBJ_Axe(gp);
                break;
            case 2:
                item = new OBJ_Shield_Blue(gp);
                break;
            default:
                break;
        }
        item.worldX = x;
        item.worldY = y;
        for(int i = 0; i < 50; i++){
            if(gp.obj[gp.currentMap][i] == null){
                gp.obj[gp.currentMap][i] = item;
                break;
            }
        }
    }
}
