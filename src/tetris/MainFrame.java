/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author jmque
 */
public class MainFrame extends javax.swing.JFrame {
    private GameArea gameArea;
    private NextShapes shapes;
    private Board board;
    private TimeThread time;
    private GameThread gameThread;
    private int score;
    private int level = 1;
    private int lines;
    private int cronoMin = 0;
    private int cronoSec = 0;
    private static String username;
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        board = new Board(20, 10);
        shapes = new NextShapes(board.getGeneratedShapes());
        gameArea = new GameArea(this, pnlBoard, board, shapes);
        
        
        this.add(gameArea);
        this.add(shapes);
        initControls();
        
        btnSetFocusable();
//        startGame();
    }
    
    private void btnSetFocusable(){
        btnMainMenu.setFocusable(false);
        btnSaveGame.setFocusable(false);
    }
    private void initControls(){
        InputMap im = this.getRootPane().getInputMap();
        ActionMap am = this.getRootPane().getActionMap();
        
        im.put(KeyStroke.getKeyStroke("RIGHT"), "right");
        im.put(KeyStroke.getKeyStroke("LEFT"), "left");
        im.put(KeyStroke.getKeyStroke("UP"), "up");
        im.put(KeyStroke.getKeyStroke("DOWN"), "down");
        
        am.put("right", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                gameArea.moveRight();
            }
        });
        am.put("left", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                gameArea.moveLeft();
            }
        });
        am.put("up", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                gameArea.rotate();
                
            }
        });
        am.put("down", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                gameArea.moveDown();
            }
        });
        
    }
    
    public void startGame(boolean loadedGame){
        lblTime.setText("0:00");
        gameThread = new GameThread(gameArea, shapes, this, board, loadedGame);
        gameThread.start();
        time = new TimeThread(gameArea, gameThread, this, cronoMin, cronoSec);
        time.start();
    }
    
    
    public void updateScore(int score){
        this.score = score;
        lblScore.setText("Score: " + score);
    }
    
    public void updateLevel(int level){
        this.level = level;
        lblLevel.setText("Level: " + level);
    }
    
    public void updateLines(int lines){
        this.lines = lines;
        lblLines.setText("Lines: " + lines);
    }
    
    
    public void setTextToTimeLabel(String time){
        this.lblTime.setText(time);
    }
    
    public static String getUsername(){
        return username;
    }
    
    public static void setUsername(String username){
        MainFrame.username = username; 
    }
    
    public void loadGame() throws IOException, ClassNotFoundException{
        
        try {
//            gameThread.loadConfig(username);
            
            
            board.loadGame(username);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Username " + username + " not found.", "NOT FOUND", JOptionPane.ERROR_MESSAGE);
            this.setVisible(false);
            Tetris.showStartup();
            return;
        }
        this.setVisible(true);
        startGame(true);
        gameThread.loadConfig(System.getProperty("user.dir") + "/" + username);
        lblLevel.setText("Level: " + gameThread.getLevel());
        lblLines.setText("Lines: " + gameThread.getLines());
        lblScore.setText("Score: " + gameThread.getScore());
        time.setTime(gameThread.getMinutes(), gameThread.getSeconds());
        lblTime.setText(time.getMinutes() + ":" + time.setNiceTime(time.getSeconds()));
    }
    
    public void resetGame(){
        gameThread.interrupt();
        time.stopRunning();
        board.clearMatrix();
        this.lblScore.setText("Score: 0");
        this.lblLevel.setText("Level: 1");
        this.lblLines.setText("Lines: 0");
        this.setVisible(false);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBoard = new javax.swing.JPanel();
        lblTetrisTitle = new javax.swing.JLabel();
        lblLevel = new javax.swing.JLabel();
        lblScore = new javax.swing.JLabel();
        btnMainMenu = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnSaveGame = new javax.swing.JButton();
        lblLines = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setResizable(false);

        pnlBoard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout pnlBoardLayout = new javax.swing.GroupLayout(pnlBoard);
        pnlBoard.setLayout(pnlBoardLayout);
        pnlBoardLayout.setHorizontalGroup(
            pnlBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 198, Short.MAX_VALUE)
        );
        pnlBoardLayout.setVerticalGroup(
            pnlBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );

        lblTetrisTitle.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblTetrisTitle.setText("Tetris");

        lblLevel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblLevel.setText("Level: 1");

        lblScore.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblScore.setText("Score: 0");

        btnMainMenu.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnMainMenu.setText("Main Menu");
        btnMainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMainMenuActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Next");

        btnSaveGame.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnSaveGame.setText("Save Game");
        btnSaveGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveGameActionPerformed(evt);
            }
        });

        lblLines.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblLines.setText("Lines: 0");

        lblTime.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTime.setText("0:00");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblTetrisTitle)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLines, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblScore, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(63, 63, 63))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnMainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSaveGame, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addComponent(pnlBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 259, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(120, 120, 120))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(lblTetrisTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnlBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(btnMainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSaveGame, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 195, Short.MAX_VALUE)
                .addComponent(lblLines, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblScore, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMainMenuActionPerformed
        this.resetGame();
        Tetris.showStartup();
        Tetris.setPause(true);
        Tetris.checkMusic();
    }//GEN-LAST:event_btnMainMenuActionPerformed

    private void btnSaveGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveGameActionPerformed
        try {
            board.saveGame(score, level, username);
            
            gameThread.setTime(time.getMinutes(), time.getSeconds());
            gameThread.saveConfig(System.getProperty("user.dir") + "/" + username);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSaveGameActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JButton btnSaveGame;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblLevel;
    private javax.swing.JLabel lblLines;
    private javax.swing.JLabel lblScore;
    private javax.swing.JLabel lblTetrisTitle;
    private javax.swing.JLabel lblTime;
    private javax.swing.JPanel pnlBoard;
    // End of variables declaration//GEN-END:variables

    
    
}
