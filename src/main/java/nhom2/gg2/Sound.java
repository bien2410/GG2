/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhom2.gg2;

import java.net.*;
import javax.sound.sampled.*;

/**
 *
 * @author ADMIN
 */
public class Sound {
    
    Clip clip;
    URL soundURL[] = new URL[30];
    
    public Sound(){
        
        soundURL[0] = getClass().getResource("/res/sound/wav.wav");
        soundURL[1] = getClass().getResource("/res/sound/coin.wav");
        soundURL[2] = getClass().getResource("/res/sound/hitmonster.wav");
        soundURL[3] = getClass().getResource("/res/sound/receivedamage.wav");
        soundURL[4] = getClass().getResource("/res/sound/levelup.wav");
        
    }
    
    public void setFile(int i){
        try{
            
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void play(){
        clip.start();
    }
    
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    public void stop(){
        clip.stop();
    }
}
