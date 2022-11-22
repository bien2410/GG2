/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package nhom2.gg2;

/**
 *
 * @author ADMIN
 */
import javax.swing.*;
import scene.*;
public class GG2 {

    /*public static void main(String[] args) {
        //TAO CUA SO TRO CHOI
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // dong chuong trinh khi tat cua so
        window.setResizable(false); // ko chinh kich co cua so
        window.setTitle("GG2"); // dat ten cua so
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        
        window.pack(); // cua so window khop voi kich co GamePanel
        window.setLocationRelativeTo(null); // cua so luon hien o giua man hinh
        window.setVisible(true); // hien ra cua so de nhin thay
        
        gamePanel.setupGame();
        gamePanel.startGameThread(); // bat dau game
    }*/
    
    public static void main(String[] args) {}
    private static GG2 instance = new GG2();
    public static GG2 getInstance(){
        return instance;
    }
    
    private Scene curScene;
    private JFrame window;
    private MusicManager music;
    
    private GG2(){
        
        window=new JFrame();
        music=new MusicManager(7,7);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        window.setResizable(false);
        window.setTitle("GG2");
        window.setIconImage(null);
        window.setSize(1152, 768);
        this.enterMainMenu();
        //this.enterMainGame();
        //window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
    /*private GG2(){
        //TAO CUA SO TRO CHOI
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // dong chuong trinh khi tat cua so
        window.setResizable(false); // ko chinh kich co cua so
        window.setTitle("GG2"); // dat ten cua so
        
        this.enterMainGame();
        
        window.pack(); // cua so window khop voi kich co GamePanel
        window.setLocationRelativeTo(null); // cua so luon hien o giua man hinh
        window.setVisible(true); // hien ra cua so de nhin thay
        
    }*/
    //manage scene
    public void pauseScene() {
        if(curScene!=null) curScene.setEnabled(false);
    }
    public void unpauseScene() {
        curScene.setEnabled(true);
    }
    public void enterScene(Scene s){
        this.pauseScene();
        curScene=s;
        curScene.animated();
        window.getContentPane().add(curScene, 0);
        curScene.requestFocusInWindow();
        window.revalidate();
        window.repaint();
    }
    /*public void enterLevelMenu() {
        this.enterScene(new LevelMenu());
    }*/
    public void enterHelpMenu() {
        this.enterScene(new HelpMenu());
    }
    public void enterExitMenu() {
        this.enterScene(new ExitMenu());
    }
    public void enterSettingMenu() {
        this.enterScene(new SettingMenu());
    }
    public void enterInfoMenu() {
        this.enterScene(new InfoMenu());
    }
    public void enterMainMenu() {
        window.getContentPane().removeAll();
        this.enterScene(new MainMenu());
    }
    public void enterMainGame() {
        window.getContentPane().removeAll();
        this.enterScene(new MainGame());
    }
    public void quitGame(){
        System.exit(0);
    }
    public MusicManager getMus(){
        return this.music;
    }
    public void closeScene() {
        if(window.getContentPane().getComponentCount()<=1) return;
        window.getContentPane().remove(0);
        curScene=(Scene) window.getContentPane().getComponents()[0];
        this.unpauseScene();
        window.revalidate();
        curScene.repaint();
        curScene.requestFocusInWindow();
    }
}

