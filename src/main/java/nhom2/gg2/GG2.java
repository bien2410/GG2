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
public class GG2 {

    public static void main(String[] args) {
        //TAO CUA SO TRO CHOI
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // dong chuong trinh khi tat cua so
        window.setResizable(false); // ko chinh kich co cua so
        window.setTitle("GG2"); // dat ten cua so
        window.setSize(100, 200);
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        
        window.pack(); // cua so window khop voi kich co GamePanel
        window.setLocationRelativeTo(null); // cua so luon hien o giua man hinh
        window.setVisible(true); // hien ra cua so de nhin thay
        
        gamePanel.setupGame();
        gamePanel.startGameThread(); // bat dau game
    }
}
