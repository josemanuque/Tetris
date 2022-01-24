/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jmque
 */
public class TimeThread extends Thread{

    private GameArea gameArea;
    private GameThread gameThread;
    private MainFrame mainFrame;
    private boolean isRunning = true;
    private int minutes = 0;
    private int seconds = 0;

    public TimeThread(GameArea gameArea, GameThread gameThread, MainFrame mainFrame, int minutes, int seconds) {
        this.gameArea = gameArea;
        this.gameThread = gameThread;
        this.mainFrame = mainFrame;
        this.minutes = minutes;
        this.seconds = seconds;
    }
    
    
    
    
    @Override
    public void run(){
        while (isRunning){
            try {
                sleep(1000);
                seconds++;
                if (minutes == 2){
                    minutes = 0;
                    seconds = 0;
                }
                
                if (seconds > 59){
                    seconds = 0;
                    minutes++;
                }
                String timeString = minutes + ":" + setNiceTime(seconds);
                mainFrame.setTextToTimeLabel(timeString);
                if (minutes == 2){
                    gameThread.levelIncrease();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(TimeThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public String setNiceTime(int number){
        if (number < 10)
            return "0" + number;
        return "" + number;
    }
    public void stopRunning(){
        this.isRunning = false;
    }
    
    public int getMinutes(){
        return minutes;
    }
    
    public int getSeconds(){
        return seconds;
    }
    
    public void setTime(int minutes, int seconds){
        this.minutes = minutes;
        this.seconds = seconds;
    }
}
