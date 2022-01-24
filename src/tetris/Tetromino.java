/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.util.Random;


/**
 *
 * @author jmque
 */
public class Tetromino {
    private int type;
    
    
    private int [][][] shapeTypes = {
    {{1, 1}, {1, 1}}, // O
    {{2, 0}, {2, 0}, {2, 2}}, // L
    {{0, 3}, {0, 3}, {3, 3}}, // J
    {{4}, {4}, {4}, {4}}, // I
    {{0, 5, 5}, {5, 5, 0}}, // S
    {{6, 6, 0}, {0, 6, 6}}, // Z
    {{7, 7, 7}, {0, 7, 0}} // T
    };


    public int[][][] getShapes() {
        return shapeTypes;
    }
    
    public int[][] getShape(){
        return shapeTypes[1];
    }
    
    public int[][] randomShape(){
        Random random = new Random();
        type = random.nextInt(7);
        return shapeTypes[type];
    }

    public int getType() {
        return type;
    }
    
    
}
 
//class GFG
//{
//     
//static int N = 4;
// 
//// Function to rotate the matrix 90 degree clockwise
//static void rotate90Clockwise(int a[][])
//{
// 
//    // Traverse each cycle
//    for (int i = 0; i < N / 2; i++)
//    {
//        for (int j = i; j < N - i - 1; j++)
//        {
// 
//            // Swap elements of each cycle
//            // in clockwise direction
//            int temp = a[i][j];
//            a[i][j] = a[N - 1 - j][i];
//            a[N - 1 - j][i] = a[N - 1 - i][N - 1 - j];
//            a[N - 1 - i][N - 1 - j] = a[j][N - 1 - i];
//            a[j][N - 1 - i] = temp;
//        }
//    }
//}
// 
//// Function for print matrix
//static void printMatrix(int arr[][])
//{
//    for (int i = 0; i < N; i++)
//    {
//        for (int j = 0; j < N; j++)
//        System.out.print( arr[i][j] + " ");
//        System.out.println();
//    }
//}
// 
//// Driver code
// 
//    public static void main (String[] args)
//    {
//            int arr[][] = { { 1, 2, 3, 4 },
//                      { 5, 6, 7, 8 },
//                      { 9, 10, 11, 12 },
//                      { 13, 14, 15, 16 } };
//            int shit[][] = {{1, 0}, 
//                {1, 0}};
//    rotate90Clockwise(shit);
//    rotate90Clockwise(arr);
//    printMatrix(arr);
//    }
//}
 
// This code has been contributed by inder_verma.