/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author jmque
 */
public class GameArea extends JPanel{
    private int rows;
    private int columns;
    private int cellSize;
    private NextShapes nextShapes;
    private int pauseTime = 1000; // fall speed (the lesser the faster)
    private Board board;
    private boolean play = true; 
    private Color color;
    private MainFrame mainFrame;
    private int level = 1;
//    private TimeThread timeThread = new TimeThread(this);
    
    public GameArea(MainFrame mainFrame, JPanel placeholder, Board board, NextShapes nextShapes){ // whole panel for game area
        placeholder.setVisible(false);
        this.setBounds(200, 50, 300, 600); // JPanel
        this.setBackground(null);
//        this.nextShapes = nextShapes;
        this.board = board;
        this.rows = board.getRows();
        this.columns = board.getColumns();
        this.cellSize = 30;
//        this.mainFrame = mainFrame;

    }
    
    private void drawBlock(Graphics g){
        for (int i = 2; i < board.getRows(); i++)
            for (int j = 0; j < board.getColumns(); j++){
                int type = board.get(i, j);
                switch (type){
                    case 0 -> { color = Color.BLACK;}
                    case 1 -> { color = new Color(255, 204, 0);}
                    case 2-> { color = new Color(255, 102, 0);}
                    case 3 -> { color = Color.BLUE;}
                    case 4 -> { color = new Color(75, 172, 201);}
                    case 5-> { color = new Color(42, 186, 32);}
                    case 6 -> { color = Color.RED;}
                    case 7 -> { color = Color.MAGENTA;}
                }
                int x = j * cellSize;
                int y = (i - 2) * cellSize;

                g.setColor(color);
                g.fillRect(x, y, cellSize, cellSize);
                if (type != 0){
                    g.setColor(Color.white);
                    g.drawRect(x, y, cellSize, cellSize);
                }
            }
    }
    
//    public void run(){
//        int score = 0;
        
//        while (play){
////            timeThread.run();
//            board.spawnShape();
//            nextShapes.repaint();
//            while (!board.atBottom()){
//                try {
//                    moveDown();
//                    Thread.sleep(pauseTime);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(GameArea.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//            if (board.lost()){
//                repaint(); //repaint to desplay how last shape doesnt fit VISUAL
//                System.out.println("GAME OVER");
//                break;
//            }
//            
//            int tempScore = score + clearLines();
//            int combo = tempScore - score;
//            if (combo == 3)
//                tempScore += 4;
//            else if (combo == 4)
//                tempScore += 5;
//            score = tempScore;
//            mainFrame.updateScore(score);
//        }
//        
//    }
    
    public int levelIncrease(){
        if (level == 10)
            return pauseTime;
        level++;
        pauseTime -= 100;
        updateLevel();
        return pauseTime;
    }
    
    public void moveDown(){
        board.moveShapeDown();
        repaint();
    }
    
    public void moveLeft(){
        board.moveShapeLeft();
        repaint();
    }
    
    public void moveRight(){
        board.moveShapeRight();
        repaint();
    }
    
    public void rotate(){
        board.rotateShape();
        repaint();
    }
    
    public int clearLines(){
        int score = board.clearCompleteLines();
//        System.out.println(score);
        repaint();
        return score;
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g); // paint whole game area
        drawBlock(g);
        
    }
    
    public void updateScore(int score){
        mainFrame.updateScore(score);
    }
    
    public void updateLevel(){
        mainFrame.updateLevel(level);
    }
    
    public void resetLevel(){
        level = 1;
    }
    
    public void resetScore(){
        updateScore(0);
    }

}
