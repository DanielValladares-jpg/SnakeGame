/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Point;
import java.util.LinkedList;

public class Snake {
    public enum Direction { UP, DOWN, LEFT, RIGHT }

    public static final int COLS = 20;
    public static final int ROWS = 20;

    private LinkedList<Point> body = new LinkedList<>();
    private Direction direction    = Direction.RIGHT;
    private boolean  grew         = false;

    public Snake() {
        // Serpiente inicial de 3 segmentos en el centro
        body.add(new Point(11, 10));
        body.add(new Point(10, 10));
        body.add(new Point(9,  10));
    }

    /** Avanza la serpiente un paso en la dirección actual. */
    public void move() {
        Point head = body.getFirst();
        Point next;
        switch (direction) {
            case UP:    next = new Point(head.x, head.y - 1); break;
            case DOWN:  next = new Point(head.x, head.y + 1); break;
            case LEFT:  next = new Point(head.x - 1, head.y); break;
            default:    next = new Point(head.x + 1, head.y); break;
        }
        body.addFirst(next);
        if (grew) {
            grew = false;   // mantiene la cola (creció)
        } else {
            body.removeLast(); // quita la cola
        }
    }

    /** Marca que en el próximo move() la cola NO se elimina. */
    public void grow() { grew = true; }

    /** Cambia dirección. Ignora el giro de 180 grados. */
    public void setDirection(Direction d) {
        if (d == Direction.UP    && direction == Direction.DOWN)  return;
        if (d == Direction.DOWN  && direction == Direction.UP)    return;
        if (d == Direction.LEFT  && direction == Direction.RIGHT) return;
        if (d == Direction.RIGHT && direction == Direction.LEFT)  return;
        direction = d;
    }

    /** Retorna true si la cabeza chocó con un borde del tablero. */
    public boolean isOutOfBounds() {
        Point h = body.getFirst();
        return h.x < 0 || h.x >= COLS || h.y < 0 || h.y >= ROWS;
    }

    /** Retorna true si la cabeza chocó con algún segmento del cuerpo. */
    public boolean hitSelf() {
        Point head = body.getFirst();
        // Recorrer desde el índice 1 (ignorar la cabeza misma)
        for (int i = 1; i < body.size(); i++) {
            if (body.get(i).equals(head)) return true;
        }
        return false;
    }
    
    //Cambia a su estado inicial el cuerpo de la serpiente//
    public void reset() {
    body.clear();
     body.add(new Point(11, 10));
     body.add(new Point(10, 10));
     body.add(new Point(9,  10));
    direction = Direction.RIGHT;
}

    public LinkedList<Point> getBody()  { return body; }
    public Point             getHead()  { return body.getFirst(); }
    public Direction         getDirection() { return direction; }
}

