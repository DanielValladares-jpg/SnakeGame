/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//libreria awt y swing para manejo de interfaces
package controller;
import java.awt.Point;
import javax.swing.Timer;
import model.*;
import view.GamePanel;
import sonido.Reproductor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;

//declaracion de clases
public class GameController {
    private Snake     snake;
    private Food      food;
    private Food extraFood;
    private boolean doubleFoodActive = false;
    private GameState state;
    private GamePanel panel;
    private Timer     timer;
    private Reproductor sonido;
    private int lastLevel;
    private boolean esperandoInicio = true;
    private boolean contando = false;
    private int contador = 3;
    private int bestScore = 0;
    private final String archivoScore = "score.txt";

    //contructores
    public GameController(GamePanel panel) {
        this.panel = panel;
        this.state = new GameState();
        this.snake = new Snake();
        this.lastLevel = state.getLabel();
        this.food  = new Food(snake);
        timer = new Timer(state.getDelay(), e -> tick());
        timer.start();
        sonido = new Reproductor();
        cargarScore();
    }

    
   private void tick() {
        if (state.isPaused() || state.isGameOver()|| esperandoInicio || contando) return;
        snake.move();

        // ── Colisión con bordes o consigo misma :v ────────
        if (snake.isOutOfBounds() || snake.hitSelf()) {
            if (!state.isGameOver()){
            sonido.detener();
            sonido.reproductor("sonido/perder.wav");}
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
          sonido.reproductor("sonido/grow-sound.wav");           
          state.addPoint();
          if (state.getScore() > bestScore) {
          bestScore = state.getScore();
          guardarScore();
        }
          food.respawn(snake);
          timer.setDelay(state.getDelay());
        }
        
        if (state.getLabel() % 2 == 0) {
              if (!doubleFoodActive) {
               extraFood = new Food(snake);
               doubleFoodActive = true;
           }
           } else {
                doubleFoodActive = false;
              extraFood = null;
       
        }
        
        if (doubleFoodActive && extraFood != null) {
        if (snake.getHead().equals(extraFood.getPosition())) {
        snake.grow();
        sonido.reproductor("sonido/grow-sound.wav");           
        state.addPoint();
        if (state.getScore() > bestScore) {
        bestScore = state.getScore();
        guardarScore(); 
        }
        extraFood.respawn(snake);
        
        if(state.getpremios() == 5 && !state.isWin()){
           state.setWin(true);
            timer.stop();
            panel.repaint();   
          };
    }
}
         int currentLevel = state.getLabel();
           if (currentLevel > lastLevel) {
           sonido.reproductor("sonido/Level Up.wav"); 
           lastLevel = currentLevel;
    }
        panel.repaint();
    }
    //Salir del juego
    public void exitGame() {
        System.exit(0);}
    
    //Cambiar la direccion de la serpiente
    public void setDirection(Snake.Direction d) { snake.setDirection(d); }
    
    //Manejo de pausa
    public void togglePause() { state.setPaused(!state.isPaused()); 
    if (state.isPaused()){
        sonido.pausar();
        sonido.reproductor("sonido/pausa.wav");
    } 
    else 
    {sonido.reanudar();}
    panel.repaint(); 
    }
    
    //Getters
    public Snake     getSnake() { return snake; }
    public Food      getFood()  { return food; }
    public GameState getState() { return state; }
    public Food getExtraFood()  { return extraFood; }
    public boolean isDoubleFoodActive() { return doubleFoodActive; }
    
    //metodo de reseteo seteando a su estado de inicio
    public void resetGame() {
    sonido.detener();
    state.reset();   
    snake.reset();
    food.respawn(snake);

    lastLevel = state.getLabel();
    esperandoInicio = true;
    contando = false;
    contador = 3;

    timer.setDelay(state.getDelay());
    timer.start();
    
    iniciarCuentaRegresiva();
    panel.repaint();
}
    //metodo de cuenta regresiva del inicio y al reiniciar
    public void iniciarCuentaRegresiva() {
    if (!esperandoInicio || contando) return;

    contando = true;
    contador = 3;
    
    panel.repaint();

    Timer countdown = new Timer(1000, null);

    countdown.addActionListener(e -> {
        
        contador--;
        
        panel.repaint();

        if (contador < -1) {
            contando = false;
            esperandoInicio = false;

            sonido.loop("sonido/musica de fondo.wav"); 

            ((Timer)e.getSource()).stop();
        }
    });
    countdown.start();
    }
    
    //getters del metodo de cuenta regresiva
    public boolean estaEsperandoInicio() {return esperandoInicio;}
    public boolean estaContando() {return contando;}
    public int getContador() {return contador;}
    
    private void cargarScore() {
    try (BufferedReader br = new BufferedReader(new FileReader(archivoScore))) {
        String linea = br.readLine();
        if (linea != null) {
            bestScore = Integer.parseInt(linea);
        }
    } catch (Exception e) {
        bestScore = 0; 
    }
  }
    
    private void guardarScore() {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoScore))) {
        bw.write(String.valueOf(bestScore));
    } catch (Exception e) {
        System.out.println(e);
    }
  }
    
    public int getBestScore() {
    return bestScore;
  }
    }


