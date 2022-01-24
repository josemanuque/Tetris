/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author jmque
 */
public class Shape implements Serializable{
    private int [][] shapeArray;
    private int type; // see if it is needed
    private int x;
    private int y;
    private Color color;
    
    public Shape(Tetromino allShapes){
        shapeArray = allShapes.randomShape();
        type = allShapes.getType(); // O, L, J, I, S, Z, T
        setColor();
    }
    
    public Shape(int[][] shapeArray){
        this.shapeArray = shapeArray;
        setColor();
    }
    
    private void setColor(){
        switch (type){
            case 0 -> { color = Color.YELLOW;}
            case 1-> { color = Color.ORANGE;}
            case 2 -> { color = Color.BLUE;}
            case 3 -> { color = Color.CYAN;}
            case 4 -> { color = Color.GREEN;}
            case 5 -> { color = Color.RED;}
            case 6 -> { color = Color.MAGENTA;}
        }
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public int getRows(){
        return shapeArray.length;
    }
    public int getColumns(){
        return shapeArray[0].length;
    }
    
    public int get(int row, int column){
        try {
        return shapeArray[row][column];
        }
        catch(Exception e) {
            return 0;
        }
        
    }
    
    public void rotate(){

        int n = this.getRows();
        int m = this.getColumns();
        int [][] output = new int [m][n];

        for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++)
                        output [j][n - 1 - i] = shapeArray[i][j];
        shapeArray = output;
    }
    
    public void printTest(){
         for(int r = 0; r < this.getRows(); r++){
            for (int c = 0; c < this.getColumns(); c++)
                System.out.print(shapeArray[r][c] + " ");
            System.out.println("\n");
         }
    }

    public int[][] getShapeArray() {
        return shapeArray;
    }
    
    public void setShapeArray(int[][] shape){
        this.shapeArray = shape;
    }
    
    public int getType(){
        return type;
    }
    
    
    
        
}
