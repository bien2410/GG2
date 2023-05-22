/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhom2.gg2;


/**
 *
 * @author ADMIN
 */
import bullet.*;
import entity.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import monster.*;
import object.*;
import scene.*;
import tile.*;
public class GamePanel extends Scene implements Runnable{
    public BufferedImage bg = null;
     
    // cai dat man hinh
    final int originalTileSize = 16; // anh se la 16 pixels * 16 pixels
    final int scale = 3; // chi so phong to hinh anh khi vao game
    public final int tileSize = originalTileSize * scale; // kich co hinh anh sau khi phong to
    // co the thay doi kich thuoc tuy vao anh dau vao
    
    public final int maxScreenCol = 24;
    public final int maxScreenRow = 16;
    public final int screenWidth = tileSize * maxScreenCol; //1152
    public final int screenHeight = tileSize * maxScreenRow; //768
    //chia man hinh game thanh 24tileSize * 16tileSize
    
    // World setting la map day
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
   
    public int maxMap = 10;
    public int currentMap = 0;
    //FPS 
    public int FPS = 60;
    
    //tile
    public TileManager tileM = new TileManager(this);
    //keyHandler
    public KeyHandler keyH = new KeyHandler(this);
    //public KeyHandler keyH;
    //Sound
    Sound music = new Sound();
    Sound se = new Sound();
    //collision
    public CollisionChecker cChecker = new CollisionChecker(this);
    //AssetSetter kieu cai dat ay
    public AssetSetter aSetter = new AssetSetter(this);
    //UI
    public UI ui = new UI(this);
    //game
    Thread gameThread;
    //player
    public Player player = new Player(this, keyH);
    //bullet
    //muon nhieu hon thi thay cai nay thanh arrayList
    public Bullet bullet = null;
    //monster
    public monster[][] monster = new monster[maxMap][20];
    public monster[][] monsterR = new monster[maxMap][20];
    
    public int countM[] = new int[9];
    
    //object
    public Entity[][] obj = new Entity[maxMap][50];
    //Chest
    public OBJ_Chest[][] chest = new OBJ_Chest[maxMap][20];
    //Coin
    public ArrayList<OBJ_Coin> coin = new ArrayList<>();
    //NPC
    public NPC[][] npc = new NPC[maxMap][10];
    //MISSION
    public Mission mission = new Mission(this);
    //dialogue
    public Queue<String> dia = new LinkedList<>();
    //title
    public Queue<String> tit = new LinkedList<>();
    public boolean speaking = true;
    
    public long timeRevival = 15 * 60;
    public long counterRevival = timeRevival;
    //GAME STATE
    public int gameState = 0;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int revivalState = 5;
    public final int gameLoseState = 6;
    public final int gameOverState = 7;
    public final int gameWinState = 8;
    private PauseMenu pause=new PauseMenu(keyH);
    public GamePanel(){
        super();
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //kich co cua GamePanel
        this.setSize(screenWidth, screenHeight);
        this.setBackground(Color.black); // mau cua nen 
        this.setDoubleBuffered(true); // cho phep su dung phuong thuc draw...
        setText(0);
        setMonsterCounter();
        // dau vao tu ban phim
        this.addKeyListener(keyH); 
        this.setFocusable(true);
        //this.setupGame();
        this.startGameThread();
        //this.requestFocusInWindow();
        try{

            bg = ImageIO.read(getClass().getResourceAsStream("/res/background.png"));

        }
        catch(Exception e){
            
        }
    }
    
    public void setMonsterCounter(){
        for(int i = 0; i < 9; i++) countM[i] = 0;
    }
    
    public void setText(int i){
        if(i == 0){
            tit.add(".......................");
            tit.add("Đây là ai tôi là đâu?. Chuyện gì đang xảy ra thế này");
            tit.add("Mình nhớ là đang code PTIT đến phút thứ 181 mà");
            dia.add("Mình ngủ thiếp đi và rồi ......");
            dia.add("Đằng kia có 1 ông lão đến hỏi thử xem nào");
            dia.add("Ấn A, D để di chuyển trái phải. W để nhảy\nẤn ENTER để tương tác với các vật thể và đối thoại");
            dia.add("Giờ thì đi tìm ông Ôp nào");
        }
        if(i == 1){
            tit.add("You Lose");
        }
        if(i == 2){
            tit.add("You Over");
        }
        if(i == 3){
            tit.add("You Win");
        }
    }
    
    public void speak(){
        if(!dia.isEmpty()){
            ui.currentDialogue = dia.poll();
            gameState = dialogueState;
        }
        else{
            this.setupGame();
            speaking = false;
        }
    }
    
    public void setupGame(){
        //aSetter.setObject();
        aSetter.setMonster();
        aSetter.setNpc();
        aSetter.setChest();
        playMusic(0);
        stopMusic();
    }
    
    public void startGameThread(){
        
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    public void setKeyH(KeyHandler keyH){
        this.keyH = keyH;
    }
    
    public void pause(){
        pause.disable(!pause.isDisable());
        if(!pause.isDisable())GG2.getInstance().enterScene(pause);
        else GG2.getInstance().closeScene();
    }
    
    @Override
    // vong lap game
    public void run() {
        // do tre tu vong lap nay den vong lap ke: FPS lan/s
        double drawInterval = 1000000000/FPS; 
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        //long timer = 0;
        //int drawCount = 0;
        while(gameThread != null){
            
            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime) / drawInterval;
            //timer += (currentTime - lastTime);
            lastTime = currentTime;
        
            if(delta >= 1){
                if(pause.isDisable()){
                    update();

                    repaint();
                }
                delta--;
                //drawCount++;
                
            }
            // print FPS
            /*if(timer >= 1000000000){
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }*/
            
        }
    }
    
    public void update(){
        if(gameState == playState){
            if(speaking){
                speak();
            }
            //update nhan vat
            player.update();
            for(int i = 0; i < npc[1].length; i++){
                if(npc[currentMap][i] != null){
                    npc[currentMap][i].update();
                }
            }
            mission.updateMission(mission.indexMission);
            if(mission.indexMission != -1 && mission.doing)mission.checkMission();
            for(int i = 0; i < monster[1].length; i++){
                if(monster[currentMap][i] != null){
                    if(monster[currentMap][i].alive == true && monster[currentMap][i].dying == false){
                        monster[currentMap][i].update();
                    }
                    if(monster[currentMap][i].alive == false){
                        countM[aSetter.typeMonster[currentMap][i]]++; // sua lai
                        monster[currentMap][i] = null;
                    }
                }
                if(monsterR[currentMap][i] != null && monster[currentMap][i] == null){
                    monsterR[currentMap][i].counterRevival--;
                }
                //Revival
                if(monsterR[currentMap][i] != null && monsterR[currentMap][i].counterRevival <= 0){
                    aSetter.resetMonster(currentMap, i, aSetter.typeMonster[currentMap][i]); // sua 
                    monsterR[currentMap][i].counterRevival = monsterR[currentMap][i].timeRevival;   
                }
                
            }
            if(bullet != null) {
                bullet.update();
                if(bullet.worldX == bullet.limitX) bullet = null;
                else if(bullet.touch) bullet = null;
            }
            //update coin
            for(OBJ_Coin i : coin){
                if(i != null){
                    i.update();
                }
            }
        }
        if(gameState == pauseState){
            //nothing
        }
        if(gameState == revivalState){
            counterRevival--;
            if(counterRevival <= 0){
                revival();
            }
        }
    }
    
    public void paintComponent(Graphics g){ // subclass of JPanel
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, screenWidth, screenHeight);
        g.drawImage(bg, 0, 0, screenWidth, screenHeight, null);
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
        if(gameState == titleState){
           ui.draw(g2);
        }
        else {
            //ve tile trc khi ve nhan vat
            tileM.draw(g2);
            //ve NPC
            for(int i = 0; i < npc[1].length; i++){
                if(npc[currentMap][i] != null){
                    
                    npc[currentMap][i].draw(g2);            
                }
            }
            //ve object
            for(int i = 0; i < obj[1].length; i++){
                if(obj[currentMap][i] != null){
                    
                    obj[currentMap][i].draw(g2);            
                }
            }
            //ve chest
            for(int i = 0; i < chest[1].length; i++){
                if(chest[currentMap][i] != null){
                    
                    chest[currentMap][i].draw(g2);            
                }
            }
            //ve coin
            for(OBJ_Coin i : coin){
                if(i != null){
                    i.draw(g2);
                }
            }
            //ve nhan vat
            player.draw(g2);

            //ve dan
            if(bullet != null) {
                bullet.draw(g2);
            }

            //ve monster
            for(int i = 0; i < monster[1].length; i++){
                if(monster[currentMap][i] != null){
                    monster[currentMap][i].draw(g2);            
                }
            }
            //ve UI
            ui.draw(g2); // o day thanh mau con o ui
        }
        
        g2.dispose();
    }
    
    public void playMusic(int i){ // am thanh nen
        
        music.setFile(i);
        music.play();
        music.loop();
    }
    
    public void stopMusic(){
        music.stop();
    }
    
    public void playSE(int i){ // an thanh hieu ung
        
        se.setFile(i);
        se.play();
    }
    
    public void revival(){
        gameState = playState;
        coin.clear();
        player.restore();
        aSetter.setMonster();
        timeRevival += 15 * 60;
        counterRevival = timeRevival;
    }
}
