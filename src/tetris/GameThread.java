/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;

/**
 *
 * @author jmque
 */
public class GameThread extends Thread implements Serializable{
    private GameArea ga;
    private boolean play = true;
    private Board board;
    private NextShapes nextShapes;
    private int pauseTime = 1000;
    private MainFrame mainFrame;
    private TimeThread timeThread;
    private int score;
    private int level = 1;
    private int lines;
    private int minutes;
    private int seconds;
    private boolean loadedGame;
    private Config config;

    public GameThread(GameArea ga, NextShapes nextShapes, MainFrame mainFrame, Board board, boolean loadGame){// Board board, NextShapes nextShapes, MainFrame mainframe) {
        this.ga = ga;
        this.board = board;
        this.nextShapes = new NextShapes(board.getGeneratedShapes());
        //System.out.println(nextShapes.shapes.get(0));
        this.config = new Config();
        this.nextShapes = nextShapes;
        this.mainFrame = mainFrame;
        //this.timeThread =  new TimeThread(ga, this, mainFrame);
        this.loadedGame = loadGame;
    }
    
    @Override
    public void run(){
//        lines = score = 0;
//        level = 1;
//        System.out.println(loadedGame);
        int index = 1;
        if (loadedGame){
            nextShapes.setShapes(board.getGeneratedShapes());
        }
        while (play){
            
            board.spawnShape();
//            nextShapes.repaint();
            if (loadedGame && index == 2){
                
                //nextShapes = new NextShapes(board.getGeneratedShapes());
//                while(true){
//                nextShapes.setShapes(null);
//                board.getGeneratedShapes().getFirst().printTest();
                nextShapes.setShapes(board.getGeneratedShapes());
////                nextShapes.shapes.getFirst().printTest();
                nextShapes.repaint();
                loadedGame = false;
//                board.printTest();
            }
            
            while (!board.atBottom()){
                try {
                    ga.moveDown();
//                    System.out.println("xd");
                    Thread.sleep(pauseTime);
                } catch (InterruptedException ex) {
                    return;
                }
                
            }
            if (board.lost()){
                ga.repaint(); //repaint to desplay how last shape doesnt fit VISUAL
                try {
                    Tetris.gameOver(score);
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(GameThread.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
                break;
            }
            int clearLine = ga.clearLines();
            lines += clearLine;
            int tempScore = score + clearLine;
            int combo = tempScore - score;
            if (combo == 3 || combo == 4) // +1 Bonus por 3 o 4 lineas completadas con 1 sola fugura
                tempScore += 1;
            score = tempScore;
            mainFrame.updateScore(score);
            mainFrame.updateLevel(level);
            mainFrame.updateLines(lines);
        }
    }
    
//    public void startTime(){
//        timeThread.start();
//    }
    
    public void levelIncrease(){
        if (level == 10)
            return;
        level++;
        pauseTime -= 80;
        System.out.println(pauseTime);
        mainFrame.updateLevel(level);
    }
    
    public void saveConfig(String username) throws IOException{
        config.setConfig(level, score, lines, pauseTime, minutes, seconds);
        FileOutputStream fout = new FileOutputStream(username + "/config.txt");
        ObjectOutputStream out = new ObjectOutputStream(fout);
        out.writeObject(config);
        fout.close();
        out.close();
    }
    
    public void loadConfig(String username) throws IOException, ClassNotFoundException{
        FileInputStream fin = new FileInputStream(username + "/config.txt");
        ObjectInputStream in = new ObjectInputStream(fin);
        config = (Config) in.readObject();
        fin.close();
        level = config.getLevel();
        pauseTime = config.getPauseTime();
        lines = config.getLines();
        score = config.getScore();
        minutes = config.getMinutes();
        seconds = config.getSeconds();
        
    }
    
    public void setTime(int minutes, int seconds){
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public int getLines() {
        return lines;
    }
    
    
    
}
