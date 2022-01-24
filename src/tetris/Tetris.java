/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jmque
 */
public class Tetris {
    private static MainFrame mainFrame;
    private static MenuFrame menuFrame;
    private static LeaderboardFrame leaderboard;
    private static String playerName;
    private static boolean pause = true;
    private static Music gameMusic = new Music("tetrisMusic.wav");
    
    public static void start(){
        mainFrame.setVisible(true);
        mainFrame.startGame(false);
    }
    
    public static void showLeaderboard(){
        try {
            leaderboard.load();
            leaderboard.refreshText();
        } catch (Exception e) {
        }
        leaderboard.setVisible(true);
    }
    
    public static void showStartup(){
        menuFrame.setVisible(true);
    }
    
    public static void gameOver(int score) throws IOException{
        Tetris.setPause(true);
        Tetris.checkMusic();
        JOptionPane.showMessageDialog(mainFrame, "You lost\nBetter luck next time!", "Game Over", JOptionPane.OK_OPTION);
        mainFrame.setVisible(false);
        mainFrame.resetGame();
        leaderboard.addScore(playerName, score);
        leaderboard.save();
        leaderboard.refreshText();
        leaderboard.setVisible(true);
    }
    
    public static void loadGame() throws IOException, ClassNotFoundException{
        //mainFrame.startGame(true);
        playerName = JOptionPane.showInputDialog("Please enter your name.");
        if (playerName == null){
            menuFrame.setVisible(true);
            return;
        }
        if (playerName.equals("")){
            JOptionPane.showMessageDialog(menuFrame, "No name was given.", "Invalid Name", JOptionPane.ERROR_MESSAGE);
            getUsername();
            return;
        }   
        mainFrame.setUsername(playerName);
        mainFrame.loadGame();
        //mainFrame.setVisible(true);
    }
    
    public static void getUsername(){
        
        playerName = JOptionPane.showInputDialog("Please enter your name.");
        System.out.println(playerName);
        if (playerName == null){
            menuFrame.setVisible(true);
            return;
        }
        if (playerName.equals("")){
            JOptionPane.showMessageDialog(menuFrame, "No name was given.", "Invalid Name", JOptionPane.ERROR_MESSAGE);
            getUsername();
            return;
        }       
        System.out.println("Flag");
        mainFrame.setUsername(playerName);
        start();
    }
    
    public static void setPause(boolean p) {
        pause = p;
    }
    
    public static void checkMusic() {
        if(pause) {
            gameMusic.pauseMusic();
        }
        else {
            gameMusic.playMusic();
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //matriz.printTest();
//        Tetromino shape = new Tetromino();
//        Board board = new Board(20, 10);
//        board.printTest();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                mainFrame = new MainFrame();
                menuFrame = new MenuFrame();
                leaderboard = new LeaderboardFrame();
            
                try {
                    leaderboard.load();
                } catch (Exception ex) {
                }
                menuFrame.setVisible(true);
                
            }
        });
        
//        MainFrame mainFrame = new MainFrame();
//        mainFrame.setVisible(true);
//        mainFrame.startGame();
//        for (int i = 0; i < 100; i++){
//        System.out.println("xdddd");
//        }
    }
    
}
