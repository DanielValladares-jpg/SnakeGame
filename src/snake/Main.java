/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package snake;

import java.awt.Image;
import javax.swing.*;
import view.GamePanel;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Snake - POO");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ImageIcon imicon = new ImageIcon(Main.class.getResource("/snake/iconoSnake.jpg"));
            frame.setIconImage(imicon.getImage());
            GamePanel gp = new GamePanel();
            frame.add(gp);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setVisible(true);
            gp.requestFocusInWindow();
        });
    }
}


