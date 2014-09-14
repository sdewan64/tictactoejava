/*
 * Tic Tac Toe - The Online Game
 * Author : Shaheed Ahmed Dewan Sagar
 * AUST CSE : 12-01-04-085
 * Dated: 23.07.2013
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PlayOnlineHost.java
 *
 * Created on 23 Jul, 2013, 9:46:53 PM
 */

package tictacktoe;

import com.sun.awt.AWTUtilities;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Shaheed Ahmed Dewan Sagar
 */
public class PlayOnlineHost extends javax.swing.JFrame {

    ServerSocket serverSocket  = null;
    Socket socket;
    
    char sign = 'O';
    char signop = 'X';
    int count,port;

    public PlayOnlineHost() throws HeadlessException {
    }
    DataInputStream in;
    DataOutputStream out;
    waitThread wt = new waitThread();
    
    int[][] array = new int[3][3];
    boolean clickable = true;
    
    
    public PlayOnlineHost(int port) throws IOException {
        initComponents();       
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int sx = (screen.width / 2) - 100;
        int sy = (screen.height / 2) - 150;
        this.setBounds(sx, sy, 250, 350);
        this.getContentPane().setBackground(Color.DARK_GRAY);
        
        serverSocket = new ServerSocket(port);
        socket = serverSocket.accept();
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        
        this.port = port;
        int i,j;
        for(i=0;i<3;i++){
            for(j=0;j<3;j++){
                array[i][j] = 0;
            }
        }
        statusbox.setText("Your TURN");
        count = 1;
        
        URL whatismyip = new URL("http://checkip.amazonaws.com/");
	BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
        String ip = in.readLine();
        ipbox.setText("Your IP:PORT : "+ip+":"+port);
    }
int Iswin()
{

    if (array[0][0]==1 && array[0][1]==1 && array[0][2]==1)
        return 1;
    else if (array[1][0]==1 && array[1][1]==1 && array[1][2]==1)
        return 1;
    else if (array[2][0]==1 && array[2][1]==1 && array[2][2]==1)
        return 1;
    else if (array[0][0]==1 && array[1][0]==1 && array[2][0]==1)
        return 1;
    else if (array[0][1]==1 && array[1][1]==1 && array[2][1]==1)
        return 1;
    else if (array[0][2]==1 && array[1][2]==1 && array[2][2]==1)
        return 1;
    else if (array[0][0]==1 && array[1][1]==1 && array[2][2]==1)
        return 1;
    else if (array[0][2]==1 && array[1][1]==1 && array[2][0]==1)
        return 1;
    else if (array[0][0]==2 && array[0][1]==2 && array[0][2]==2)
        return 2;
    else if (array[1][0]==2 && array[1][1]==2 && array[1][2]==2)
        return 2;
    else if (array[2][0]==2 && array[2][1]==2 && array[2][2]==2)
        return 2;
    else if (array[0][0]==2 && array[1][0]==2 && array[2][0]==2)
        return 2;
    else if (array[0][1]==2 && array[1][1]==2 && array[2][1]==2)
        return 2;
    else if (array[0][2]==2 && array[1][2]==2 && array[2][2]==2)
        return 2;
    else if (array[0][0]==2 && array[1][1]==2 && array[2][2]==2)
        return 2;
    else if (array[0][2]==2 && array[1][1]==2 && array[2][0]==2)
        return 2;
    else
        return -1;
}
void Won(int who) throws IOException{
    if(who==1){
        clickable = false;
        int choice = JOptionPane.showConfirmDialog(null, "You WON!Congratulations.\nDo you Want to Play again?");
        if(choice==0){
            out.writeInt(22);
            socket.close();
            port++;
            PlayOnlineHost ph = new PlayOnlineHost(port);
            ph.setVisible(true);
            this.setVisible(false);
        }else if(choice==1||choice==2){
            System.exit(0);
        }
    }else if(who==2){
        out.writeInt(22);
        clickable = false;
        int choice = JOptionPane.showConfirmDialog(null, "You Lost!Better Luck next Time.\nDo you Want to Play again?");
        if(choice==0){
            socket.close();
            port++;
            PlayOnlineHost ph = new PlayOnlineHost(port);
            ph.setVisible(true);
            this.setVisible(false);
        }else if(choice==1||choice==2){
            System.exit(0);
        }
    }else if(who==3){      
        out.writeInt(22);
        clickable = false;
        int choice = JOptionPane.showConfirmDialog(null, "Game DRAWN.\nDo you Want to Play again?");
        if(choice==0){
            socket.close();
            port++;
            PlayOnlineHost ph = new PlayOnlineHost(port);
            ph.setVisible(true);
            this.setVisible(false);
        }else if(choice==1||choice==2){
            System.exit(0);
        }   
    }
}       
JLabel link(int l,int m)
    {
        if(l==0)
        {
            if(m==0)
                    return box1;
            if(m==1)
                    return box2;
            if(m==2)
                    return box3;
        }
        if(l==1)
        {
            if(m==0)
                    return box4;
            if(m==1)
                    return box5;
            if(m==2)
                    return box6;
        }
        if(l==2)
        {
            if(m==0)
                    return box7;
            if(m==1)
                    return box8;
            if(m==2)
                    return box9;
        }
        return null;
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        box1 = new javax.swing.JLabel();
        box2 = new javax.swing.JLabel();
        box3 = new javax.swing.JLabel();
        box6 = new javax.swing.JLabel();
        box5 = new javax.swing.JLabel();
        box4 = new javax.swing.JLabel();
        box9 = new javax.swing.JLabel();
        box8 = new javax.swing.JLabel();
        box7 = new javax.swing.JLabel();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        label4 = new java.awt.Label();
        statusbox = new javax.swing.JLabel();
        ipbox = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        exit = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        howto = new javax.swing.JMenuItem();
        about = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tic Tac Toe");
        setBackground(java.awt.Color.darkGray);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        box1.setBackground(java.awt.Color.darkGray);
        box1.setFont(new java.awt.Font("Comic Sans MS", 1, 60)); // NOI18N
        box1.setForeground(java.awt.Color.white);
        box1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        box1.setOpaque(true);
        box1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                box1MouseClicked(evt);
            }
        });
        getContentPane().add(box1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 68, 69));

        box2.setBackground(java.awt.Color.darkGray);
        box2.setFont(new java.awt.Font("Comic Sans MS", 1, 60)); // NOI18N
        box2.setForeground(java.awt.Color.white);
        box2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        box2.setOpaque(true);
        box2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                box2MouseClicked(evt);
            }
        });
        getContentPane().add(box2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 68, 69));

        box3.setBackground(java.awt.Color.darkGray);
        box3.setFont(new java.awt.Font("Comic Sans MS", 1, 60)); // NOI18N
        box3.setForeground(java.awt.Color.white);
        box3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        box3.setOpaque(true);
        box3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                box3MouseClicked(evt);
            }
        });
        getContentPane().add(box3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 68, 69));

        box6.setBackground(java.awt.Color.darkGray);
        box6.setFont(new java.awt.Font("Comic Sans MS", 1, 60)); // NOI18N
        box6.setForeground(java.awt.Color.white);
        box6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        box6.setOpaque(true);
        box6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                box6MouseClicked(evt);
            }
        });
        getContentPane().add(box6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 68, 69));

        box5.setBackground(java.awt.Color.darkGray);
        box5.setFont(new java.awt.Font("Comic Sans MS", 1, 60)); // NOI18N
        box5.setForeground(java.awt.Color.white);
        box5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        box5.setOpaque(true);
        box5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                box5MouseClicked(evt);
            }
        });
        getContentPane().add(box5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 68, 69));

        box4.setBackground(java.awt.Color.darkGray);
        box4.setFont(new java.awt.Font("Comic Sans MS", 1, 60)); // NOI18N
        box4.setForeground(java.awt.Color.white);
        box4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        box4.setOpaque(true);
        box4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                box4MouseClicked(evt);
            }
        });
        getContentPane().add(box4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 68, 69));

        box9.setBackground(java.awt.Color.darkGray);
        box9.setFont(new java.awt.Font("Comic Sans MS", 1, 60)); // NOI18N
        box9.setForeground(java.awt.Color.white);
        box9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        box9.setOpaque(true);
        box9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                box9MouseClicked(evt);
            }
        });
        getContentPane().add(box9, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 68, 69));

        box8.setBackground(java.awt.Color.darkGray);
        box8.setFont(new java.awt.Font("Comic Sans MS", 1, 60)); // NOI18N
        box8.setForeground(java.awt.Color.white);
        box8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        box8.setOpaque(true);
        box8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                box8MouseClicked(evt);
            }
        });
        getContentPane().add(box8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 68, 69));

        box7.setBackground(java.awt.Color.darkGray);
        box7.setFont(new java.awt.Font("Comic Sans MS", 1, 60)); // NOI18N
        box7.setForeground(java.awt.Color.white);
        box7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        box7.setOpaque(true);
        box7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                box7MouseClicked(evt);
            }
        });
        getContentPane().add(box7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 68, 69));

        label1.setBackground(java.awt.Color.white);
        getContentPane().add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 161, 228, 6));

        label2.setBackground(java.awt.Color.white);
        getContentPane().add(label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 81, 228, 6));

        label3.setBackground(java.awt.Color.white);
        getContentPane().add(label3, new org.netbeans.lib.awtextra.AbsoluteConstraints(161, 10, 6, 230));

        label4.setBackground(java.awt.Color.white);
        getContentPane().add(label4, new org.netbeans.lib.awtextra.AbsoluteConstraints(81, 10, 6, 230));

        statusbox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statusbox.setText("Your Turn");
        statusbox.setOpaque(true);
        getContentPane().add(statusbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 250, 18));

        ipbox.setFont(new java.awt.Font("Trajan Pro", 0, 11)); // NOI18N
        ipbox.setForeground(new java.awt.Color(255, 255, 255));
        ipbox.setText("IP");
        getContentPane().add(ipbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 230, 20));

        jMenuBar1.setBackground(java.awt.Color.darkGray);
        jMenuBar1.setBorder(null);
        jMenuBar1.setForeground(java.awt.Color.white);
        jMenuBar1.setToolTipText("TicTacToe");
        jMenuBar1.setFocusable(false);
        jMenuBar1.setOpaque(false);

        jMenu1.setForeground(new java.awt.Color(51, 51, 51));
        jMenu1.setText("Game");

        exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        exit.setText("Exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        jMenu1.add(exit);

        jMenuBar1.add(jMenu1);

        jMenu3.setForeground(new java.awt.Color(51, 51, 51));
        jMenu3.setText("Help");

        howto.setText("How to Play");
        howto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                howtoActionPerformed(evt);
            }
        });
        jMenu3.add(howto);

        about.setText("About");
        about.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutActionPerformed(evt);
            }
        });
        jMenu3.add(about);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void box1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_box1MouseClicked
        if(array[0][0]==0&&clickable==true){
        int i,j;
        array[0][0] = 1;
        JLabel ct=link(0,0);
        ct.setText(String.valueOf(sign));
        int win = Iswin();
        if(win==1){
            try {
            out.writeInt(5);
            out.writeInt(10);
            out.writeInt(0);
            out.writeInt(10);
            out.writeInt(0);
            Won(1);
            } catch (IOException ex) {
            Logger.getLogger(PlayOnlineHost.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else if(win==2){
            try {
            out.writeInt(5);
            out.writeInt(10);
            out.writeInt(0);
            out.writeInt(10);
            out.writeInt(0);
            Won(2);
            } catch (IOException ex) {
            Logger.getLogger(PlayOnlineHost.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{
        try {
            out.writeInt(5);
            out.writeInt(10);
            out.writeInt(0);
            out.writeInt(10);
            out.writeInt(0);
            new waitThread().start();
        } catch (IOException ex) {
            Logger.getLogger(PlayOnlineHost.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        }
    }//GEN-LAST:event_box1MouseClicked
    
    private void box2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_box2MouseClicked
       if(array[0][1]==0&&clickable==true){
        int i,j;
        array[0][1] = 1;
        JLabel ct=link(0,1);
        ct.setText(String.valueOf(sign));
        int win = Iswin();
        if(win==1){
            try {
            out.writeInt(5);
            out.writeInt(10);
            out.writeInt(0);
            out.writeInt(10);
            out.writeInt(1);
            Won(1);
            } catch (IOException ex) {
            Logger.getLogger(PlayOnlineHost.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else if(win==2){
            try {
            out.writeInt(5);
            out.writeInt(10);
            out.writeInt(0);
            out.writeInt(10);
            out.writeInt(1);
            Won(2);
            } catch (IOException ex) {
            Logger.getLogger(PlayOnlineHost.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{
        try {
            out.writeInt(5);
            out.writeInt(10);
            out.writeInt(0);
            out.writeInt(10);
            out.writeInt(1);
            new waitThread().start();
        } catch (IOException ex) {
            Logger.getLogger(PlayOnlineHost.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        }
    }//GEN-LAST:event_box2MouseClicked

    private void box3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_box3MouseClicked
       if(array[0][2]==0&&clickable==true){
        int i,j;
        array[0][2] = 1;
        JLabel ct=link(0,2);
        ct.setText(String.valueOf(sign));
        int win = Iswin();
        if(win==1){
            try {
            out.writeInt(5);
            out.writeInt(10);
            out.writeInt(0);
            out.writeInt(10);
            out.writeInt(2);
            Won(1);
            } catch (IOException ex) {
            Logger.getLogger(PlayOnlineHost.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else if(win==2){
            try {
            out.writeInt(5);
            out.writeInt(10);
            out.writeInt(0);
            out.writeInt(10);
            out.writeInt(2);
            Won(2);
            } catch (IOException ex) {
            Logger.getLogger(PlayOnlineHost.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{
        try {
            out.writeInt(5);
            out.writeInt(10);
            out.writeInt(0);
            out.writeInt(10);
            out.writeInt(2);
            new waitThread().start();
        } catch (IOException ex) {
            Logger.getLogger(PlayOnlineHost.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        }
    }//GEN-LAST:event_box3MouseClicked

    private void box4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_box4MouseClicked
       if(array[1][0]==0&&clickable==true){
        int i,j;
        array[1][0] = 1;
        JLabel ct=link(1,0);
        ct.setText(String.valueOf(sign));
        int win = Iswin();
        if(win==1){
            try {
            out.writeInt(5);
            out.writeInt(10);
            out.writeInt(1);
            out.writeInt(10);
            out.writeInt(0);
            Won(1);
            } catch (IOException ex) {
            Logger.getLogger(PlayOnlineHost.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else if(win==2){
            try {
            out.writeInt(5);
            out.writeInt(10);
            out.writeInt(1);
            out.writeInt(10);
            out.writeInt(0);
            Won(2);
            } catch (IOException ex) {
            Logger.getLogger(PlayOnlineHost.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{
        try {
            out.writeInt(5);
            out.writeInt(10);
            out.writeInt(1);
            out.writeInt(10);
            out.writeInt(0);
            new waitThread().start();
        } catch (IOException ex) {
            Logger.getLogger(PlayOnlineHost.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        }
    }//GEN-LAST:event_box4MouseClicked

    private void box5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_box5MouseClicked
       if(array[1][1]==0&&clickable==true){
        int i,j;
        array[1][1] = 1;
        JLabel ct=link(1,1);
        ct.setText(String.valueOf(sign));
        int win = Iswin();
        if(win==1){
            try {
            out.writeInt(5);
            out.writeInt(10);
            out.writeInt(1);
            out.writeInt(10);
            out.writeInt(1);
            Won(1);
            } catch (IOException ex) {
            Logger.getLogger(PlayOnlineHost.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else if(win==2){
            try {
            out.writeInt(5);
            out.writeInt(10);
            out.writeInt(1);
            out.writeInt(10);
            out.writeInt(1);
            Won(2);
            } catch (IOException ex) {
            Logger.getLogger(PlayOnlineHost.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{
        try {
            out.writeInt(5);
            out.writeInt(10);
            out.writeInt(1);
            out.writeInt(10);
            out.writeInt(1);
            new waitThread().start();
        } catch (IOException ex) {
            Logger.getLogger(PlayOnlineHost.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        }
    }//GEN-LAST:event_box5MouseClicked

    private void box6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_box6MouseClicked
        if(array[1][2]==0&&clickable==true){
        int i,j;
        array[1][2] = 1;
        JLabel ct=link(1,2);
        ct.setText(String.valueOf(sign));
        int win = Iswin();
        if(win==1){
            try {
            out.writeInt(5);
            out.writeInt(10);
            out.writeInt(1);
            out.writeInt(10);
            out.writeInt(2);
            Won(1);
            } catch (IOException ex) {
            Logger.getLogger(PlayOnlineHost.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else if(win==2){
            try {
            out.writeInt(5);
            out.writeInt(10);
            out.writeInt(1);
            out.writeInt(10);
            out.writeInt(2);
            Won(2);
            } catch (IOException ex) {
            Logger.getLogger(PlayOnlineHost.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{
        try {
            out.writeInt(5);
            out.writeInt(10);
            out.writeInt(1);
            out.writeInt(10);
            out.writeInt(2);
            new waitThread().start();
        } catch (IOException ex) {
            Logger.getLogger(PlayOnlineHost.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        }
    }//GEN-LAST:event_box6MouseClicked

    private void box7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_box7MouseClicked
       if(array[2][0]==0&&clickable==true){
        int i,j;
        array[2][0] = 1;
        JLabel ct=link(2,0);
        ct.setText(String.valueOf(sign));
        int win = Iswin();
        if(win==1){
            try {
            out.writeInt(5);
            out.writeInt(10);
            out.writeInt(2);
            out.writeInt(10);
            out.writeInt(0);
            Won(1);
            } catch (IOException ex) {
            Logger.getLogger(PlayOnlineHost.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else if(win==2){
            try {
            out.writeInt(5);
            out.writeInt(10);
            out.writeInt(2);
            out.writeInt(10);
            out.writeInt(0);
            Won(2);
            } catch (IOException ex) {
            Logger.getLogger(PlayOnlineHost.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{
        try {
            out.writeInt(5);
            out.writeInt(10);
            out.writeInt(2);
            out.writeInt(10);
            out.writeInt(0);
            new waitThread().start();
        } catch (IOException ex) {
            Logger.getLogger(PlayOnlineHost.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        }
    }//GEN-LAST:event_box7MouseClicked

    private void box8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_box8MouseClicked
        if(array[2][1]==0&&clickable==true){
        int i,j;
        array[2][1] = 1;
        JLabel ct=link(2,1);
        ct.setText(String.valueOf(sign));
        int win = Iswin();
        if(win==1){
            try {
            out.writeInt(5);
            out.writeInt(10);
            out.writeInt(2);
            out.writeInt(10);
            out.writeInt(1);
            Won(1);
            } catch (IOException ex) {
            Logger.getLogger(PlayOnlineHost.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else if(win==2){
            try {
            out.writeInt(5);
            out.writeInt(10);
            out.writeInt(2);
            out.writeInt(10);
            out.writeInt(1);
            Won(2);
            } catch (IOException ex) {
            Logger.getLogger(PlayOnlineHost.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{
        try {
            out.writeInt(5);
            out.writeInt(10);
            out.writeInt(2);
            out.writeInt(10);
            out.writeInt(1);
            new waitThread().start();
        } catch (IOException ex) {
            Logger.getLogger(PlayOnlineHost.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        }
    }//GEN-LAST:event_box8MouseClicked

    private void box9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_box9MouseClicked
       if(array[2][2]==0&&clickable==true){
        int i,j;
        array[2][2] = 1;
        JLabel ct=link(2,2);
        ct.setText(String.valueOf(sign));
        int win = Iswin();
        if(win==1){
            try {
            out.writeInt(5);
            out.writeInt(10);
            out.writeInt(2);
            out.writeInt(10);
            out.writeInt(2);
            Won(1);
            } catch (IOException ex) {
            Logger.getLogger(PlayOnlineHost.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else if(win==2){
            try {
            out.writeInt(5);
            out.writeInt(10);
            out.writeInt(2);
            out.writeInt(10);
            out.writeInt(2);
            Won(2);
            } catch (IOException ex) {
            Logger.getLogger(PlayOnlineHost.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{
        try {
            out.writeInt(5);
            out.writeInt(10);
            out.writeInt(2);
            out.writeInt(10);
            out.writeInt(2);
            new waitThread().start();
        } catch (IOException ex) {
            Logger.getLogger(PlayOnlineHost.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        }
    }//GEN-LAST:event_box9MouseClicked

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(PlayOnlineHost.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }//GEN-LAST:event_exitActionPerformed

    private void howtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_howtoActionPerformed
      JOptionPane.showMessageDialog(null, "*Play by clicking with the mouse.\n*During online play the game must be hosted first.\n*On re-game the hosted game should accept re-match first.\n*Enjoy the game.");
    }//GEN-LAST:event_howtoActionPerformed

    private void aboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutActionPerformed
      JOptionPane.showMessageDialog(null, "Author : Shaheed Ahmed Dewan Sagar\nAUST CSE\n12-01-04-085");
    }//GEN-LAST:event_aboutActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                int p = Integer.parseInt(JOptionPane.showInputDialog("Enter PORT number : "));
                try {
                    new PlayOnlineHost(p).setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(PlayOnlineHost.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem about;
    private javax.swing.JLabel box1;
    private javax.swing.JLabel box2;
    private javax.swing.JLabel box3;
    private javax.swing.JLabel box4;
    private javax.swing.JLabel box5;
    private javax.swing.JLabel box6;
    private javax.swing.JLabel box7;
    private javax.swing.JLabel box8;
    private javax.swing.JLabel box9;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JMenuItem exit;
    private javax.swing.JMenuItem howto;
    private javax.swing.JLabel ipbox;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private javax.swing.JLabel statusbox;
    // End of variables declaration//GEN-END:variables
    
    class waitThread extends Thread{
        boolean respond = false;
        public void run(){
            //System.out.println("Thread running");
            try {
                clickable=false;
                statusbox.setText("Wait for Opponents TURN");
                if(in.readInt()==5){
                    //System.out.println("REading data from client:");
                    int a,b,c;
                    c = in.readInt();
                    a = in.readInt();
                    c = in.readInt();
                    b = in.readInt();
                    //System.out.println("DATA : "+a+" "+b);
                    array[a][b] = 2;
                    JLabel ct=link(a,b);
                    ct.setText(String.valueOf(signop));
                    int win = Iswin();
                    if(win==1){
                        Won(1);
                    }else if(win==2){
                        Won(2);
                    }else{
                    if(count==5){
                        Won(3);
                    }
                    statusbox.setText("Your TURN");
                    count++;
                    int x,y;
                    /*
                    for(x=0;x<3;x++){
                        for(y=0;y<3;y++){
                            System.out.print(array[x][y]+" ");
                        }
                        System.out.println();
                    }*/
                    clickable=true;
                    wt.stop();
                    }
                }else if(in.readInt()==22){
                    int win = Iswin();
                    if(win==1){
                            Won(1);
                        }else if(win==2){
                           Won(2);
                        }else{
                        if(count==5){
                            Won(3);
                        }
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(PlayOnlineClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
