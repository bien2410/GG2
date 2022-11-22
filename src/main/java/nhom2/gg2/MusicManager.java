/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhom2.gg2;

/**
 *
 * @author ADMIN
 */
public class MusicManager{
    private int soundVol;
    private int sfxVol;
    public MusicManager(int s,int sfx){
        this.soundVol=s;
        this.sfxVol=sfx;
    }
    public int getSoundVol() {
        return soundVol;
    }
    public void setSoundVol(int soundVol) {
        this.soundVol = soundVol;
    }
    public int getSfxVol() {
        return sfxVol;
    }
    public void setSfxVol(int sfxVol) {
        this.sfxVol = sfxVol;
    }

}
