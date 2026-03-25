/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.awt.Point;
import javax.swing.Timer;
import model.*;
import view.GamePanel;

public class GameController {
    private Snake     snake;
    private Food      food;
    private GameState state;
    private GamePanel panel;
    private Timer     timer;

    public GameController(GamePanel panel) {
        this.panel = panel;
        this.state = new GameState();
        this.snake = new Snake();
        this.food  = new Food(snake);
        timer = new Timer(state.getDelay(), e -> tick());
        timer.start();
    }

   private void tick() {
        if (state.isPaused() || state.isGameOver()) return;
        snake.move();

        // ── Colisión con bordes o consigo misma :v ────────
        if (snake.isOutOfBounds() || snake.hitSelf()) {
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

           state.addPoint();

          food.respawn(snake);

          timer.setDelay(state.getDelay());
        }
        panel.repaint();
    }


    public void setDirection(Snake.Direction d) { snake.setDirection(d); }
    public void togglePause() { state.setPaused(!state.isPaused()); panel.repaint(); }
    public Snake     getSnake() { return snake; }
    public Food      getFood()  { return food; }
    public GameState getState() { return state; }
    
    public void resetGame() {
    state.reset();   
    snake.reset();
    food.respawn(snake);
    timer.setDelay(state.getDelay());
    timer.start();
    panel.repaint();
}
}


