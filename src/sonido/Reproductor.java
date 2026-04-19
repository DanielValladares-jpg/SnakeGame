/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sonido;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;

/**
 *
 * @author danie
 */
public class Reproductor{
    
    private Clip musica; 
    private Clip efecto; 
    private boolean enLoop = false;
    
    public void loop(String ruta){
    try {
        if (musica != null) {
            musica.stop();
            musica.close();
        }
        AudioInputStream audio = AudioSystem.getAudioInputStream(
        getClass().getResource("/" + ruta)
        );
        musica = AudioSystem.getClip();
        musica.open(audio);

        enLoop = true; 

        musica.addLineListener(event -> {
            if (event.getType() == LineEvent.Type.STOP && enLoop) {
                musica.setFramePosition(0);
                musica.start();
            }
        });

        musica.start();

    } 
    catch (Exception e){
          e.printStackTrace();}
    }
    
    public void reproductor(String ruta){
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(
            getClass().getResource("/" + ruta)
            );
            efecto = AudioSystem.getClip();
            efecto.open(audio);
            efecto.start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
     
    public void detener(){
       if (musica != null){
        enLoop = false;
        musica.stop();
        musica.close();}
    }
     
    public void pausar(){
    if (musica != null && musica.isRunning()){
        enLoop = false; 
        musica.stop();}
    }
     
    public void reanudar(){
    if (musica != null) {
        enLoop = true; 
        musica.start();}
    }
    }
