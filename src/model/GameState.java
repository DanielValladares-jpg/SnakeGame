/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class GameState {
    private int score = 0;
      private int level = 1;
    private boolean gameOver = false;
    private boolean paused   = false;
    
    public void addPoint()   { 
        score += 10;
        if (score % 50 == 0){
            level++;
        }
    }
    public int  getScore()             { return score; }
     public int getLabel()             {return level;}
    public boolean isGameOver()        { return gameOver; }
    public boolean isPaused()          { return paused; }
    public void setGameOver(boolean v) { gameOver = v; }
    public void setPaused(boolean v)   { paused = v; }
    // Velocidad: inicia en 150ms, baja 5ms cada 50 puntos (mínimo 60ms)
//    public int getDelay() { return Math.max(60, 150 - (score / 50) * 5); }
    public int getDelay() { return Math.max(40, 175 - ((score*2) / 50) * 10); }
    public void reset() {
    score = 0;
    level = 1;
    gameOver = false;
    paused = false;}
    }
