/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.io.Serializable;

/**
 *
 * @author jmque
 */
public class Config implements Serializable{
    private int level;
    private int score;
    private int lines;
    private int pauseTime;
    private int minutes;
    private int seconds;
    
    
    public Config(){
        
    }
    
    public void setConfig(int level, int score, int lines, int pauseTime, int minutes, int seconds){
        this.level = level;
        System.out.println(score);
        this.score = score;
        this.lines = lines;
        this.pauseTime = pauseTime;
        this.minutes = minutes;
        this.seconds = seconds;
        //System.out.println(seconds);
    }

    public int getLevel() {
        return level;
    }

    public int getScore() {
        return score;
    }

    public int getLines() {
        return lines;
    }

    public int getPauseTime() {
        return pauseTime;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }
    
    
}


