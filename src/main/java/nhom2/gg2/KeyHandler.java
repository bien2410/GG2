/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhom2.gg2;

/**
 *
 * @author ADMIN
 */
import java.awt.event.*;
public class KeyHandler implements KeyListener {
    
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean jPressed, kPressed, enterPressed;
    public GamePanel gp;
    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }
    
    
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode();
        
       //TITLE STATE
       if(gp.gameState == gp.titleState){
           titleState(code);
       }
       
       //PLAY STATE
       else if(gp.gameState == gp.playState){
           playState(code);
       }
       
       //PAUSE STATE
       else if(gp.gameState == gp.pauseState){
           pauseState(code);
       }
       
       //DIALOGUE STATE
       else if(gp.gameState == gp.dialogueState){
           dialogueState(code);
       }
       
       //CHARACTER STATE
       else if(gp.gameState == gp.characterState){
           characterState(code);
       }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        int code = e.getKeyCode();
        
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_J){
            jPressed = false;
        }
        if(code == KeyEvent.VK_K){
            kPressed = false;
        }
        if(code == KeyEvent.VK_K){
            enterPressed = false;
        }
    }
    
    public void titleState(int code){
        
        if(code == KeyEvent.VK_W){
            gp.ui.commandNum--;
            if(gp.ui.commandNum > 0){
                gp.ui.commandNum = 2;
            }
        }
        if(code == KeyEvent.VK_S){
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 2){
                gp.ui.commandNum = 0;
            }
        }
        
        if(code == KeyEvent.VK_ENTER){
            if(gp.ui.commandNum == 0){
                gp.gameState = gp.playState;
            }
            if(gp.ui.commandNum == 1){
                //add later
            }
            if(gp.ui.commandNum == 2){
                System.exit(0);
            }
        }
        
    }
    
    public void playState(int code){
        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_J){
            jPressed = true;
        }
        if(code == KeyEvent.VK_K){
            kPressed = true;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.pauseState;
        }
        if(code == KeyEvent.VK_C ){
            gp.gameState = gp.characterState;
        }
        
    }
    public void pauseState(int code){
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.playState;
        }
    }
    
    public void dialogueState(int code){
        if(code == KeyEvent.VK_ENTER){
            gp.gameState = gp.playState;
        }
    }
    
    public void characterState(int code){
        //chen am thanh neu thich video 27
        if(code == KeyEvent.VK_W){
            if(gp.ui.slotRow != 0){
                gp.ui.slotRow--;
            }
        }
        if(code == KeyEvent.VK_A){
            if(gp.ui.slotCol != 0){
                gp.ui.slotCol--;
            }
        }
        if(code == KeyEvent.VK_S){
            if(gp.ui.slotRow != 3){
                gp.ui.slotRow++;
            }
        }
        if(code == KeyEvent.VK_D){
            if(gp.ui.slotCol != 4){
                gp.ui.slotCol++;
            }
        }
        if(code == KeyEvent.VK_ENTER){
            gp.player.selectItem();
        }
        if(code == KeyEvent.VK_C){
            gp.gameState = gp.playState;
        }
    }
    
}
