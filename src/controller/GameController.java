/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.awt.Point;
import javax.swing.Timer;
import model.*;
import view.GamePanel;
import sonido.Reproductor;


public class GameController {
    private Snake     snake;
    private Food      food;
    private GameState state;
    private GamePanel panel;
    private Timer     timer;
    private Reproductor sonido;
    
    public GameController(GamePanel panel) {
        this.panel = panel;
        this.state = new GameState();
        this.snake = new Snake();
        this.food  = new Food(snake);
        timer = new Timer(state.getDelay(), e -> tick());
        timer.start();
        sonido = new Reproductor();
//        BackgroundMusic();
    }
  
//    private void BackgroundMusic(){
//        while(state.isGameOver()) {
//           sonido.reproductor("src/sonido/perder.mp3");}
//        } 
    
   private void tick() {
        if (state.isPaused() || state.isGameOver()) return;
        snake.move();

        // ── Colisión con bordes o consigo misma :v ────────
        if (snake.isOutOfBounds() || snake.hitSelf()) {
            if (!state.isGameOver()){
           sonido.reproductor("src/sonido/perder.mp3");}
            
            state.setGameOver(true);
            timer.stop();
            panel.repaint();
            return;
        }
        
        Point head = snake.getHead();
        Point foodPos = food.getPosition();
        if (head.equals(foodPos)) {
         // Crecer la serpiente
           snake.grow();
           sonido.reproductor("src/sonido/grow-sound.mp3");
           state.addPoint();
          food.respawn(snake);
          timer.setDelay(state.getDelay());
        }
        panel.repaint();
    }


    public void setDirection(Snake.Direction d) { snake.setDirection(d); }
    public void togglePause() { state.setPaused(!state.isPaused()); 
    if (state.isPaused()){
        sonido.reproductor("src/sonido/pausa.mp3");
    }
    panel.repaint(); }
    
    public Snake     getSnake() { return snake; }
    public Food      getFood()  { return food; }
    public GameState getState() { return state; }
    
    public void resetGame() {
//    BackgroundMusic();
    state.reset();   
    snake.reset();
    food.respawn(snake);
    timer.setDelay(state.getDelay());
    timer.start();
    panel.repaint();
}
}


