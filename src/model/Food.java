/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
//Libreria de awt para interfaces
//Libreria util para herramientas de java
import java.awt.Point;
import java.util.Random;

public class Food {
    private Point position;
    private Random random = new Random();

    public Food(Snake snake) {
        respawn(snake);
    }

    /** Genera una nueva posición que no esté sobre la serpiente. */
    public void respawn(Snake snake) {
        Point p;
        do {
            p = new Point(random.nextInt(Snake.COLS),        random.nextInt(Snake.ROWS));
        } while (snake.getBody().contains(p));
        position = p;
    }

    public Point getPosition() { return position; }
}
