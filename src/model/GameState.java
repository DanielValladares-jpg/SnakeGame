/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

//Se definen las variables enteras y booleanas
public class GameState {
    private int score = 0;
    private int level = 1;
    private int premios = 0;
    private boolean gameOver = false;
    private boolean paused   = false;
    private boolean win = false;
    
    //metodo para añadir puntos a cada marcador
    public void addPoint()   { 
        score += 10;
        if (score % 50 == 0){
            level++;
        }
        if (score % 100 == 0){
        premios++;}
    }
    
    //Getter y setter para modificar y usar los estados
    public int  getScore()             { return score; }
    public int getLabel()             {return level;}
    public int getpremios()            {return premios;}
    public boolean isGameOver()        { return gameOver; }
    public boolean isPaused()          { return paused; }
    public boolean isWin() { return win;}
    public void setGameOver(boolean v) { gameOver = v; }
    public void setPaused(boolean v)   { paused = v; }
    public void setWin(boolean v) { win = v;}
    
    // Velocidad: inicia en 175ms, baja 5ms cada 50 puntos (mínimo 40ms)
    public int getDelay() { return Math.max(40, 175 - ((score*2) / 50) * 10); }
    
    //Vuelve los valores a 0
    public void reset() {
    score = 0;
    level = 1;
    premios = 0;
    gameOver = false;
    paused = false;
    win = false;}
    }
