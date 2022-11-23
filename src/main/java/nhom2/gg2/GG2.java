/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package nhom2.gg2;

/**
 *
 * @author ADMIN
 */
import java.io.IOException;
import javax.swing.*;
import scene.*;
public class GG2 {
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
        this.enterMainMenu();
        //this.enterMainGame();
        window.setVisible(true);
    }
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
    }
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
        window.pack();
        window.setLocationRelativeTo(null);
    }
    public void enterMainGame() throws IOException {
        window.getContentPane().removeAll();
        this.enterScene(new GamePanel());
        window.pack();
        window.setLocationRelativeTo(null);
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

