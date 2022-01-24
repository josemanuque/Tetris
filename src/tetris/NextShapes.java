/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import javax.swing.JPanel;

/**
 *
 * @author jmque
 */
public class NextShapes extends JPanel {
    public LinkedList<Shape> shapes;
    private Color color;
    private int[][] shapeArray;
    private int cellSize = 20;

    public NextShapes(LinkedList<Shape> nextShapes) {
        this.shapes = nextShapes;
        this.setBounds(600, 150, 200, 300); // JPanel
        shapeArray = new int[5][5];
    }
    
    public void scaleShape(int index){
        //shapeArray = new int[5][5];
        int widthMiddle = (5 - shapes.get(index).getColumns()) / 2;
        int heightMiddle = (5 - shapes.get(index).getRows()) / 2;
        for (int i = 0; i < shapes.get(index).getRows(); i++){
            for (int j = 0; j < shapes.get(index).getColumns(); j++){
                if (shapes.get(index).get(i, j) != 0)
                    shapeArray[heightMiddle + i][widthMiddle + j] = shapes.get(index).get(i, j);
            }
        }
    }
    
    public void drawNextShape(Graphics g){
        drawBackground(g, 0);
        scaleShape(0);
        drawShape(g, 0, 0);
        repaint();
    }
    
    public void drawSecondNextShape(Graphics g){
        drawBackground(g, 150);
        scaleShape(1);
        drawShape(g, 150, 1);
        repaint();
    }
    
    private void getColor(int type){
        switch (type){
            //case 0 -> { color = null;}
            case 1 -> { color = new Color(255, 204, 0);}
            case 2-> { color = new Color(255, 102, 0);}
            case 3 -> { color = Color.BLUE;}
            case 4 -> { color = new Color(75, 172, 201);}
            case 5-> { color = new Color(42, 186, 32);}
            case 6 -> { color = Color.RED;}
            case 7 -> { color = Color.MAGENTA;}
            }
    }
    
    
    public void drawShape(Graphics g, int offset, int index){
        int columns = shapes.get(index).getColumns();
        int rows = shapes.get(index).getRows();
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++){
                int type = shapes.get(index).get(i, j);//[i][j];
                getColor(type);
                
                double c = (double) columns;
                double r = (double) rows;
                double width = (c / 2) * cellSize;
                double height = (r / 2) * cellSize;
                
                double centerX = 50 - width;
                double centerY = 50 - height;
                
                int x = (int) centerX + j * cellSize;
                int y = (int) centerY + i * cellSize;
                
                if (type != 0){
                    g.setColor(color);
                    g.fillRect(x, y + offset, cellSize, cellSize);
                    g.setColor(Color.white);
                    g.drawRect(x, y + offset, cellSize, cellSize);
                }
            }
    }
    
    public void drawBackground(Graphics g, int offset){
        g.setColor(Color.BLACK);
        g.fillRect(0, offset, 100, 100);
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g); // paint whole game area
        drawNextShape(g);
        drawSecondNextShape(g);
        
    }
    
    public void setShapes(LinkedList<Shape> shapes){
        this.shapes = shapes;
    }
}
