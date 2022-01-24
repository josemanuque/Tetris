/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diemo
 */
public class FileManager {
    public static void createFile (String path){
        File myObj = new File(path);
        FileWriter myWriter;
        try {
            myWriter = new FileWriter(path, false);
            myWriter.close();
        } catch (IOException ex) {
            
        }
    }
    
    
    public static void writeToFile(String path, String text){
        try {
          FileWriter myWriter = new FileWriter(path, true);
          myWriter.write(text);
          myWriter.close();
        } catch (IOException e) {
        }
    }
    
    public static String readFile(String filePath) 
    {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) 
        {
 
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) 
            {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } 
        catch (IOException e) 
        {
            //System.out.println("File not found");
            //e.printStackTrace();
        }
        return contentBuilder.toString();
    }
            
}
