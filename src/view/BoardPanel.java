/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

/**
 *
 * @author danie
 */
import java.awt.*;
import javax.swing.JPanel;
import controller.GameController;
import model.Snake;
import java.awt.Point;
import model.Food;

public class BoardPanel extends JPanel {
    public static final int CELL = 28;
    private GameController ctrl;

    public BoardPanel(GameController ctrl) {
        this.ctrl = ctrl;
        setPreferredSize(new Dimension(Snake.COLS * CELL, Snake.ROWS * CELL));
        setBackground(Color.BLACK);
    }

@Override
    protected void paintComponent(Graphics g) {
        
       super.paintComponent(g);

       int level = ctrl.getState().getLabel();

        g.setColor(getBackgroundColorByLevel(level));
        g.fillRect(0, 0, getWidth(), getHeight());

        drawGrid1(g, level);
        drawFood(g);
        drawSnake(g);

        if (ctrl.getState().isGameOver())   drawGameOver(g); 
        if (ctrl.getState().isPaused())  drawPaused(g);
        if (ctrl.getState().isWin()) drawWin(g);
        
       Food extraFood = ctrl.getExtraFood();
         boolean doubleFoodActive = ctrl.isDoubleFoodActive();
        
        if (doubleFoodActive && extraFood != null) {

          Point p = extraFood.getPosition();

          g.setColor(Color.green);

            g.fillOval(
            p.x * CELL,
            p.y * CELL,
            CELL,
            CELL
                    
          );
        }
        
        if (ctrl.estaEsperandoInicio() && !ctrl.estaContando()) {
        g.setColor(Color.GREEN);
        g.setFont(new Font("Arial", Font.PLAIN, 35));

        String msg = "Presiona C para comenzar";

        FontMetrics fm = g.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(msg)) / 2;
        g.drawString(msg, x, getHeight()/2);
        
        g.setFont(new Font("Arial", Font.PLAIN, 14));

    String restart = "Presiona R para reiniciar";
    String exit    = "Presiona X para salir";
    String ganar = "Recoge 5 premios para ganar";

    g.drawString(restart, (getWidth()-g.getFontMetrics().stringWidth(restart))/2, getHeight()/2 + 40);
    g.drawString(exit,    (getWidth()-g.getFontMetrics().stringWidth(exit))/2,    getHeight()/2 + 60);
    g.drawString(ganar, (getWidth()-g.getFontMetrics().stringWidth(ganar))/2,    getHeight()/2 + 80);
    }      
        if (ctrl.estaContando()) {
         g.setColor(Color.YELLOW);
         g.setFont(new Font("Arial", Font.BOLD, 50));

        String texto;

        if (ctrl.getContador() >= 1) {
        texto = String.valueOf(ctrl.getContador());
    }
        else {
        texto = "¡Comienza!";
}

         FontMetrics fm = g.getFontMetrics();
         int x = (getWidth() - fm.stringWidth(texto)) / 2;
         int y = getHeight() / 2;

        g.drawString(texto, x, y);
    }
    }
    
    
    private Color getBackgroundColorByLevel(int level) {
    switch (level % 6) {
        case 0: return Color.BLACK;
        case 1: return new Color(20, 20, 60) ;  
        case 2: return new Color(0, 60, 40);  
        case 3: return new Color(60, 0, 40);  
        case 4: return new Color(80, 40, 0);  
        case 5: return new Color(40, 0, 0);   
        default: return Color.BLACK;
    }
    }
    
    
      private void drawGrid1(Graphics g, int level){
    switch (level % 6) {
        case 0:  g.setColor(new Color(30, 30, 30));
        for (int r = 0; r < Snake.ROWS; r++)
            for (int c = 0; c < Snake.COLS; c++)
                g.drawRect(c * CELL, r * CELL, CELL, CELL);
        break;
        case 1: g.setColor(new Color(112, 107, 0));
        for (int r = 0; r < Snake.ROWS; r++)
            for (int c = 0; c < Snake.COLS; c++)
                g.drawRect(c * CELL, r * CELL, CELL, CELL);
        break;
        case 2: g.setColor(new Color(112, 107, 0));
        for (int r = 0; r < Snake.ROWS; r++)
            for (int c = 0; c < Snake.COLS; c++)
                g.drawRect(c * CELL, r * CELL, CELL, CELL);
        break;
        case 3: g.setColor(new Color(0, 117, 31));
        for (int r = 0; r < Snake.ROWS; r++)
            for (int c = 0; c < Snake.COLS; c++)
                g.drawRect(c * CELL, r * CELL, CELL, CELL);
        break;
        case 4: g.setColor(new Color(67, 97, 0));
        for (int r = 0; r < Snake.ROWS; r++)
            for (int c = 0; c < Snake.COLS; c++)
                g.drawRect(c * CELL, r * CELL, CELL, CELL);
        break;
        case 5: g.setColor(new Color(0, 39, 41));
        for (int r = 0; r < Snake.ROWS; r++)
            for (int c = 0; c < Snake.COLS; c++)
                g.drawRect(c * CELL, r * CELL, CELL, CELL);
        default: g.setColor(new Color(94, 138, 0));
        for (int r = 0; r < Snake.ROWS; r++)
            for (int c = 0; c < Snake.COLS; c++)
                g.drawRect(c * CELL, r * CELL, CELL, CELL);;
        }
     }

    private void drawGrid(Graphics g) {
        g.setColor(new Color(30, 30, 30));
        for (int r = 0; r < Snake.ROWS; r++)
            for (int c = 0; c < Snake.COLS; c++)
                g.drawRect(c * CELL, r * CELL, CELL, CELL);
    }

   private void drawSnake(Graphics g) {
    java.util.LinkedList<Point> body = ctrl.getSnake().getBody();

    int size = body.size();

    for (int i = 0; i < size; i++) {
        Point seg = body.get(i);

        float t = (size > 1) ? (float) i / (size - 1) : 0;

        int r = 0;
        int gColor = (int) (255 - t * 150);
        int b = (int) (120 + t * 80);

        g.setColor(new Color(r, gColor, b));

        g.fillRoundRect(
            seg.x * CELL + 2,
            seg.y * CELL + 2,
            CELL - 4,
            CELL - 4,
            6,
            6
        );
    }
    }

    private void drawFood(Graphics g) {
        Point fp = ctrl.getFood().getPosition();
        g.setColor(Color.RED);
        g.fillOval(fp.x*CELL+3, fp.y*CELL+3, CELL-6, CELL-6);
    }

private void drawGameOver(Graphics g) {
    g.setColor(new Color(0, 0, 0, 160));
    g.fillRect(0, 0, getWidth(), getHeight());

    g.setColor(Color.WHITE);
    g.setFont(new Font("Arial", Font.BOLD, 28));
    String msg = "Game Over";
    FontMetrics fm = g.getFontMetrics();
    g.drawString(msg, (getWidth()-fm.stringWidth(msg))/2, getHeight()/2 - 20);

    // Texto adicional
    g.setFont(new Font("Arial", Font.PLAIN, 14));

    String restart = "Presiona R para reiniciar";
    String exit    = "Presiona X para salir";

    g.drawString(restart, (getWidth()-g.getFontMetrics().stringWidth(restart))/2, getHeight()/2 + 20);
    g.drawString(exit,    (getWidth()-g.getFontMetrics().stringWidth(exit))/2,    getHeight()/2 + 40);
}

    private void drawPaused(Graphics g) {
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", Font.BOLD, 22));
        g.drawString("PAUSA", 10, 30);
    }
    
    private void drawWin(Graphics g) {
        g.setColor(new Color(0, 0, 0, 160));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.GREEN);
        g.setFont(new Font("Arial", Font.BOLD, 28));
        String msg = "Ganaste";
        FontMetrics fm = g.getFontMetrics();
        g.drawString(msg, (getWidth()-fm.stringWidth(msg))/2, getHeight()/2);
        
            g.setFont(new Font("Arial", Font.PLAIN, 14));

    String restart = "Presiona R para reiniciar";
    String exit    = "Presiona X para salir";

    g.drawString(restart, (getWidth()-g.getFontMetrics().stringWidth(restart))/2, getHeight()/2 + 20);
    g.drawString(exit,    (getWidth()-g.getFontMetrics().stringWidth(exit))/2,    getHeight()/2 + 40);
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
