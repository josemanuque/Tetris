/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author jmque
 */

/*
PROGRAM LOGIC
*/
public class Board {
    private int [][] matrix;
    private int columns;
    private int rows;
    private Tetromino tetromino;
     Shape shape;
    private LinkedList<Shape> generatedShapes = new LinkedList<>();
    private boolean loadedGame;

    public Board(int rows, int columns) {
        this.rows = rows + 2;
        this.columns = columns;
        tetromino = new Tetromino();
        
        initStructures();
        clearCompleteLines();
    }
    
    private void initStructures(){
        matrix = new int[rows][columns];
        for (int i = 0; i < 3; i++){
            generatedShapes.add(new Shape(tetromino));
//            generatedShapes.get(0).printTest();
        }
    }
    
    public void spawnShape(){
        if (!loadedGame){
            shape = generatedShapes.removeFirst();
            int middle = (columns - shape.getColumns()) / 2;
            setShapeAtPos(0, middle);
        }
        else
            loadedGame = false;
        generatedShapes.add(new Shape(tetromino));
    }
    
    public void setShapeAtPos(int row, int column){
        for (int i = 0; i < shape.getRows(); i++){
            for (int j = 0; j < shape.getColumns(); j++){
                if (shape.get(i, j) != 0)
                    matrix[row + i][column + j] = shape.get(i, j);
            }
        }
        shape.setY(row);
        shape.setX(column);
    }
    
    public boolean lost(){
        return shape != null && shape.getY() < 2;
    }
    
    public boolean atBottom(){
        //this.shape.printTest();
        if (shape.getY() + shape.getRows() == rows) // at the end of matrix
            return true;
        for (int j = 0; j < shape.getColumns(); j++) // touching another shape
            for (int i = shape.getRows() - 1; i >= 0; i--)
                if (shape.get(i, j) != 0){
                    if (matrix[i + shape.getY() + 1][j + shape.getX()] != 0)
                        return true;
                    break;
                }
        return false;
    }
    
    private boolean atFarLeft(){
        if (shape.getX() == 0)
            return true;
        for (int i = 0; i < shape.getRows(); i++)
            for (int j = 0; j < shape.getColumns(); j++)
                if (shape.get(i, j) != 0){
                    if (matrix[i + shape.getY()][j + shape.getX() - 1] != 0)
                        return true;
                    break;
                }
        return false;
    }
    
    private boolean atFarRight(){
        if (shape.getX() + shape.getColumns() == columns)
            return true;
        shape.getX();
        for (int i = 0; i < shape.getRows(); i++)
            for (int j = shape.getColumns()- 1; j >= 0; j--){
                if (shape.get(i, j) != 0){
                    if (matrix[i + shape.getY()][j + shape.getX() + 1] != 0)
                        return true;
                    break;
                }
            }
        return false;
    }
    
    public boolean isShapeOut(){
        return shape.getY() < 2;
    }
    
    public void moveShapeDown(){
        if (!atBottom()){
            clearShape();
            shape.setY(shape.getY() + 1);
            setShapeAtPos(shape.getY(), shape.getX());
        }
    }
    
    public void moveShapeLeft(){
        if (!atFarLeft()){
            clearShape();
            shape.setX(shape.getX() - 1);
            setShapeAtPos(shape.getY(), shape.getX());
        }
    }
    
    public void moveShapeRight(){
        if (!atFarRight()){
            clearShape();
            shape.setX(shape.getX() + 1);
            setShapeAtPos(shape.getY(), shape.getX());
        }
    }
    
    public boolean checkValidRotation(Shape temp){
        //System.out.println(shape.getShapeArray()[0].length);
        return temp.getShapeArray()[0].length < 3;
    }
    
    public void rotateShape(){
        
        Shape tempShape = shape;
        if (!atBottom() && !atFarLeft() && !atFarRight()){
//            System.out.println("FLAG");
            clearShape();
            shape.rotate();
            setShapeAtPos(shape.getY(), shape.getX());
            return;
        }
        Shape temp = new Shape(shape.getShapeArray());
        temp.rotate();
        if (!checkValidRotation(temp)){
//            System.out.println("FLAG222");
            temp.rotate();
            clearShape();
            setShapeAtPos(shape.getY(), shape.getX());
            return;
        }
        clearShape();
        shape.rotate();
        setShapeAtPos(shape.getY(), shape.getX());
    }
    
//    public void test(){
//        switch (type){
//            case 0-> { 
//                rotatefreely();
//            }
//            case 1-> {
//                if ()
//            }
//        }
//    }
    
    public void clearShape(){
        
        for (int i = 0; i < shape.getRows(); i++){
            for (int j = 0; j < shape.getColumns(); j++){
                if (shape.get(i, j) != 0)
                    matrix[shape.getY() + i][shape.getX() + j] = 0;
            }
        }
        //printTest();
    }
    
    public int clearCompleteLines(){
        int score = 0;
        boolean lineComplete; 
        for (int i = rows - 1; i >= 0; i-- ){
            lineComplete = true;
            for (int j = 0; j < columns; j++){
                if (matrix[i][j] == 0){
                    lineComplete = false;
                    break;
                }
            }
            if (lineComplete){
                clearLine(i);
                shiftDown(i);
                //clearLine(i);
                score++;
                i++;
            }
        }
        return score;
    }
    
    
    private void clearLine(int i){
        for (int j = 0; j < columns; j++)
            matrix[i][j] = 0;
    }
    
    private void shiftDown(int r){
        for (int i = r; i > 0; i--)
            for (int j = 0; j < columns; j++)
                matrix[i][j] = matrix[i - 1][j];
    }
    
    public void printTest() {
         for(int r = 0; r < rows; r++){
            for (int c = 0; c < columns; c++)
                System.out.print(matrix[r][c] + " ");
            System.out.println("\n");
         }
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }
    
    public int get(int row, int column){
        return matrix[row][column];
    }

    public LinkedList<Shape> getGeneratedShapes() {
        return generatedShapes;
    }
    
    public void clearMatrix(){
        matrix = new int[rows][columns];
    }
    
    public void saveLinkedList(String username) throws IOException{
        String path = username + "/nextShapes.txt";
        FileOutputStream fout = new FileOutputStream(path);
        ObjectOutputStream out = new ObjectOutputStream(fout);
        out.writeObject(generatedShapes);
        fout.close();
        out.close();
        saveShape(username);
        
        
        
    }
    
    public void saveShape(String username) throws IOException{
        LinkedList<Shape> tempList = new LinkedList<Shape>();
        tempList.add(shape);
        FileOutputStream fout = new FileOutputStream(username + "/currentShape.txt");
        ObjectOutputStream out = new ObjectOutputStream(fout);
        out.writeObject(tempList);
        fout.close();
        out.close();
    }
    public void saveGame(int score, int level, String username) throws FileNotFoundException, IOException {
        File file = new File(System.getProperty("user.dir") + "/" +username);
        file.mkdir();
        String path = System.getProperty("user.dir") + "/" + username;
        String path1 = path + "/matrix.txt";
        FileManager.createFile(path1);
//        clearShape();
        for(int r = 0; r < getRows(); r++) {
            for(int c = 0; c < getColumns(); c++) {
                //System.out.println(matrix[r][c]);
                FileManager.writeToFile(path1, Integer.toString(matrix[r][c])); // numero
                try {
//                    this.printTest();
                    matrix[r][c+1] = matrix[r][c+1];
//                    System.out.println(matrix[r][c]);
                    FileManager.writeToFile(path1, ","); // coma
                }
                catch(Exception e) {

                }

            }
            FileManager.writeToFile(path1, "\n"); // new line
        }
        
        // Save currentShape
//        FileManager.writeToFile(path1, "\n" + score + " " + level + " " + minutes + " " + seconds + "\n\n");
//        FileOutputStream fout = new FileOutputStream("currentShape.txt");
//        ObjectOutputStream out = new ObjectOutputStream(fout);
//        out.writeObject(shape);
        saveLinkedList(path);
    }

    public void loadGame(String username) throws FileNotFoundException, IOException, ClassNotFoundException {
        String txt = FileManager.readFile(System.getProperty("user.dir") + "/" + username + "/matrix.txt");
        //System.out.println(txt);
//        System.out.println(txt);
        int index = 0;
        for(int r = 0; r < getRows();r++) {
            for(int c = 0; c < getColumns(); c++) {
                matrix[r][c] = Character.getNumericValue(txt.charAt(index));
                index += 2;
            }
        }
//        index += 1;
//        int score = Character.getNumericValue(txt.charAt(index));
//        index += 2;
//        int level = Character.getNumericValue(txt.charAt(index));
        
        loadShapes(username);
        loadedGame = true;
    }
    public void loadShapes(String username){
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(System.getProperty("user.dir") + "/" + username + "/nextShapes.txt");
            ObjectInputStream in = new ObjectInputStream(fin);
            generatedShapes = (LinkedList<Shape>) in.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fin.close();
            } catch (IOException ex) {
                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            loadCurrentShape(username);
        } catch (IOException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadCurrentShape(String username) throws IOException, ClassNotFoundException{
        FileInputStream fin = new FileInputStream(System.getProperty("user.dir") + "/" + username + "/currentShape.txt");
        ObjectInputStream in = new ObjectInputStream(fin);
        LinkedList<Shape> tempList = (LinkedList<Shape>) in.readObject();
        fin.close();
        shape = tempList.remove();
        //shape.printTest();
        
    }
    
}
