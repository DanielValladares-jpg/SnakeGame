/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sonido;

import java.io.FileInputStream;
import javazoom.jl.player.Player;

/**
 *
 * @author danie
 */
public class Reproductor{
    
    public void reproductor(String rolitaxd){
        new Thread(() ->{
            try{
               
                Player player = new Player(new FileInputStream(rolitaxd));
                player.play();
            }
             catch (Exception e){
                System.out.println(e);
            }
        }).start();
    }
    

}
