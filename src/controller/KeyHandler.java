/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import model.Snake;

public class KeyHandler extends KeyAdapter {
    private GameController ctrl;
    public KeyHandler(GameController ctrl) { this.ctrl = ctrl; }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:    ctrl.setDirection(Snake.Direction.UP);    break;
            case KeyEvent.VK_DOWN:  ctrl.setDirection(Snake.Direction.DOWN);  break;
            case KeyEvent.VK_LEFT:  ctrl.setDirection(Snake.Direction.LEFT);  break;
            case KeyEvent.VK_RIGHT: ctrl.setDirection(Snake.Direction.RIGHT); break;
            case KeyEvent.VK_P:     ctrl.togglePause(); break;
            case KeyEvent.VK_R:     ctrl.resetGame(); break;
            case KeyEvent.VK_W: ctrl.setDirection(Snake.Direction.UP); break;
            case KeyEvent.VK_A: ctrl.setDirection(Snake.Direction.LEFT); break;
            case KeyEvent.VK_S: ctrl.setDirection(Snake.Direction.DOWN); break;
            case KeyEvent.VK_D: ctrl.setDirection(Snake.Direction.RIGHT); break;
            case KeyEvent.VK_SPACE: ctrl.togglePause(); break;
            
        }
    }
}
