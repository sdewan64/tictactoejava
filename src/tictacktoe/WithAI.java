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
 * WithAI.java
 *
 * Created on 23 Jul, 2013, 12:26:56 PM
 */

package tictacktoe;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Shaheed Ahmed Dewan Sagar
 *         sdewan64@gmail.com
 *         AUST-12-01-04-085
 */
public class WithAI extends javax.swing.JFrame {

    int[][] array=new int[3][3];
    int cnt,val,diff=1,vs=1;
    boolean clickable = true;
    char sign = 'O';
    String pl1="You",pl2="Computer";
    Random rnd=new Random();
    boolean gamedone;

    public WithAI() {
        initComponents();       
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int sx = (screen.width / 2) - 100;
        int sy = (screen.height / 2) - 150;
        this.setBounds(sx, sy, 250, 350);
        this.getContentPane().setBackground(Color.DARK_GRAY);
        gamedone = false;
        reset();
    }
    

    void reset()
    {
        for (int i=0;i<3 ;i++ )
        {
            for (int j=0;j<3 ;j++ ){
                array[i][j]=0;
            }
        }
        Component[] cmpnt=this.getContentPane().getComponents();
        for(int i=0;i<cmpnt.length;i++)
        {
            if(cmpnt[i] instanceof JLabel)
                ((JLabel) cmpnt[i]).setText("") ;
        }
        cnt=0;
        val=1;
        sign = 'O';
        statusbox.setText(pl1+" will Play NOW.");
        clickable = true;
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
        gamedone = true;
        int choice = JOptionPane.showConfirmDialog(null, "You WON!Congratulations.\nDo you Want to Play again?");
        if(choice==0){
            WithAI wa = new WithAI();
            wa.setVisible(true);
            this.setVisible(false);
            return;
        }else if(choice==1||choice==2){
            System.exit(0);
        }
    }else if(who==2){
        gamedone = true;
        clickable = false;
        int choice = JOptionPane.showConfirmDialog(null, "You Lost!Better Luck next Time.\nDo you Want to Play again?");
        if(choice==0){
            WithAI wa = new WithAI();
            wa.setVisible(true);
            this.setVisible(false);
            return;
        }else if(choice==1||choice==2){
            System.exit(0);
        }
    }else if(who==3){      
        clickable = false;
        gamedone = true;
        int choice = JOptionPane.showConfirmDialog(null, "Game DRAWN.\nDo you Want to Play again?");
        if(choice==0){
            WithAI wa = new WithAI();
            wa.setVisible(true);
            this.setVisible(false);
            return;
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

    void flip()
    {
        if(sign=='X')
        {
            sign = 'O';
            cnt++;
        }
        else
        {
            sign = 'X';
            cnt++;
        }
    }

    void compplay()
    {
        if(gamedone){
            return;
        }
        if(diff==1){
            int pos1 = rnd.nextInt(3);
            int pos2 = rnd.nextInt(3);
            boolean done = true;
            boolean flag = false;
            int tr = 0;
            if(array[pos1][pos2]!=0)
                done = false;
            
            while(done==false){
                tr++;
                pos1 = rnd.nextInt(3);
                pos2 = rnd.nextInt(3);
                if(array[pos1][pos2]==0)
                    done = true;
                if(tr>50) break;
            }
            if(array[pos1][pos2]!=0){
               
                int i,j;
                for(i=0;i<3;i++){
                    for(j=0;j<3;j++){
                        if(array[i][j]==0) 
                        {
                            flag = true;
                            break;
                        }
                    }
                }
                if(array[pos1][pos2]!=0&&flag==false){
                    try {
                        Won(3);
                    } catch (IOException ex) {
                        Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            array[pos1][pos2] = 2;
            JLabel ct = link(pos1,pos2);
            ct.setText(String.valueOf(sign));
            flip();
            int win = Iswin();
            if(win==1){
               try {
                   Won(1);
               } catch (IOException ex) {
                   Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
               }
            }else if(win==2){
               try {
                   Won(2);
               } catch (IOException ex) {
                   Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
               }
            }else if(cnt > 9){
               try {
                   Won(3);
               } catch (IOException ex) {
                   Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
         clickable = true;
        }else if(diff==2){
            if(cnt<2){
                int pos1 = rnd.nextInt(3);
                int pos2 = rnd.nextInt(3);
                boolean done = true;
                boolean flag = false;
                int tr = 0;
                if(array[pos1][pos2]!=0)
                    done = false;
                while(done==false){
                    tr++;
                    pos1 = rnd.nextInt(3);
                    pos2 = rnd.nextInt(3);
                    if(array[pos1][pos2]==0)
                        done = true;
                    if(tr>50) break;
                }
                if(array[pos1][pos2]!=0){
               
                int i,j;
                for(i=0;i<3;i++){
                    for(j=0;j<3;j++){
                        if(array[i][j]==0) 
                        {
                            flag = true;
                            break;
                        }
                    }
                }
                if(array[pos1][pos2]!=0&&flag==false){
                    try {
                        Won(3);
                    } catch (IOException ex) {
                        Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
                array[pos1][pos2] = 2;
                JLabel ct = link(pos1,pos2);
                ct.setText(String.valueOf(sign));
                flip();
                int win = Iswin();
                if(win==1){
                   try {
                       Won(1);
                   } catch (IOException ex) {
                       Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
                   }
                }else if(win==2){
                   try {
                       Won(2);
                   } catch (IOException ex) {
                       Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
                   }
                }else if(cnt > 9){
                   try {
                       Won(3);
                   } catch (IOException ex) {
                       Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
             clickable = true;
            }else{
                int x = 0,y = 0;
                if((array[0][0]==1)&&(array[0][1]==1)&&(array[0][2]==0)){
                    x=0;
                    y=2;
                }else if((array[0][0]==1)&&(array[0][2]==1)&&(array[0][1]==0)){
                    x=0;
                    y=1;
                }else if((array[0][1]==1)&&(array[0][2]==1)&&(array[0][0]==0)){
                    x=0;
                    y=0;
                }else if((array[1][0]==1)&&(array[1][1]==1)&&(array[1][2]==0)){
                    x=1;
                    y=2;
                }else if((array[1][0]==1)&&(array[1][2]==1)&&(array[1][1]==0)){
                    x=1;
                    y=1;
                }else if((array[1][1]==1)&&(array[1][2]==1)&&(array[1][0]==0)){
                    x=1;
                    y=0;
                }else if((array[2][0]==1)&&(array[2][1]==1)&&(array[2][2]==0)){
                    x=2;
                    y=2;
                }else if((array[2][0]==1)&&(array[2][2]==1)&&(array[2][1]==0)){
                    x=2;
                    y=1;
                }else if((array[2][1]==1)&&(array[2][2]==1)&&(array[2][0]==0)){
                    x=2;
                    y=0;
                }else if((array[0][0]==1)&&(array[1][0]==1)&&(array[2][0]==0)){
                    x=2;
                    y=0;
                }else if((array[0][0]==1)&&(array[2][0]==1)&&(array[1][0]==0)){
                    x=1;
                    y=0;
                }else if((array[1][0]==1)&&(array[2][0]==1)&&(array[0][0]==0)){
                    x=0;
                    y=0;
                }else if((array[0][1]==1)&&(array[1][1]==1)&&(array[2][1]==0)){
                    x=2;
                    y=1;
                }else if((array[0][1]==1)&&(array[2][1]==1)&&(array[1][1]==0)){
                    x=1;
                    y=1;
                }else if((array[1][1]==1)&&(array[2][1]==1)&&(array[0][1]==0)){
                    x=0;
                    y=1;
                }else if((array[0][2]==1)&&(array[1][2]==1)&&(array[2][2]==0)){
                    x=2;    
                    y=2;
                }else if((array[0][2]==1)&&(array[2][2]==1)&&(array[1][2]==0)){
                    x=1;
                    y=2;
                }else if((array[1][2]==1)&&(array[2][2]==1)&&(array[0][2]==0)){
                    x=0;
                    y=2;
                }else if((array[0][0]==1)&&(array[1][1]==1)&&(array[2][2]==0)){
                    x=2;
                    y=2;
                }else if((array[0][0]==1)&&(array[2][2]==1)&&(array[1][1]==0)){
                    x=1;    
                    y=1;
                }else if((array[1][1]==1)&&(array[2][2]==1)&&(array[0][0]==0)){
                    x=0;
                    y=0;
                }else if((array[0][2]==1)&&(array[1][1]==1)&&(array[2][0]==0)){
                    x=2;    
                    y=0;
                }else if((array[2][0]==1)&&(array[1][1]==1)&&(array[0][2]==0)){
                    x=0;
                    y=2;
                }else if((array[0][2]==1)&&(array[2][0]==1)&&(array[1][1]==0)){
                    x=1;
                    y=1;
                }else{
                    boolean done = true;
                    boolean flag = false;
                    int tr = 0;
                    if(array[x][y]!=0)
                        done = false;
                    while(done==false){
                        tr++;
                        x = rnd.nextInt(3);
                        x = rnd.nextInt(3);
                        if(tr>50) break;
                    if(array[x][y]==0)
                        done = true;
                    }
                    if(array[x][y]!=0){
                    int i,j;
                    for(i=0;i<3;i++){
                    for(j=0;j<3;j++){
                        if(array[i][j]==0) 
                        {
                            flag = true;
                            break;
                        }
                    }
                }
                if(array[x][y]!=0&&flag==false){
                    try {
                        Won(3);
                    } catch (IOException ex) {
                        Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
                }
                array[x][y] = 2;
                JLabel ct = link(x,y);
                ct.setText(String.valueOf(sign));
                flip();
                int win = Iswin();
                if(win==1){
                   try {
                       Won(1);
                   } catch (IOException ex) {
                       Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
                   }
                }else if(win==2){
                   try {
                       Won(2);
                   } catch (IOException ex) {
                       Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
                   }
                }else if(cnt > 9){
                   try {
                       Won(3);
                   } catch (IOException ex) {
                       Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
             clickable = true;
            }
        }
        
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
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem3 = new javax.swing.JRadioButtonMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

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
        statusbox.setText("Player 1 to play First");
        statusbox.setOpaque(true);
        getContentPane().add(statusbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 250, 18));

        jMenuBar1.setBackground(java.awt.Color.darkGray);
        jMenuBar1.setBorder(null);
        jMenuBar1.setForeground(java.awt.Color.white);
        jMenuBar1.setFocusable(false);
        jMenuBar1.setOpaque(false);

        jMenu1.setForeground(new java.awt.Color(51, 51, 51));
        jMenu1.setText("Game");

        jMenuItem1.setText("New Game");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Exit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setForeground(new java.awt.Color(51, 51, 51));
        jMenu2.setText("Options");

        buttonGroup2.add(jRadioButtonMenuItem1);
        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("Easy");
        jRadioButtonMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jRadioButtonMenuItem1);

        buttonGroup2.add(jRadioButtonMenuItem3);
        jRadioButtonMenuItem3.setText("Hard");
        jRadioButtonMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jRadioButtonMenuItem3);

        jMenuBar1.add(jMenu2);

        jMenu3.setForeground(new java.awt.Color(51, 51, 51));
        jMenu3.setText("Help");

        jMenuItem3.setText("How to Play");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem4.setText("About");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void box1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_box1MouseClicked
       if(array[0][0]==0 && clickable==true && ((cnt%2)==0)){
           array[0][0] = 1;
           JLabel ct = link(0,0);
           ct.setText(String.valueOf(sign));
           int win = Iswin();
           if(win==1){
               try {
                   Won(1);
               } catch (IOException ex) {
                   Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
               }
           }else if(win==2){
               try {
                   Won(2);
               } catch (IOException ex) {
                   Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
               }
           }else if(cnt > 9){
               try {
                   Won(3);
               } catch (IOException ex) {
                   Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       }
       flip();
       clickable = false;
       compplay();
    }//GEN-LAST:event_box1MouseClicked

    private void box2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_box2MouseClicked
        if(array[0][1]==0 && clickable==true && ((cnt%2)==0)){
           array[0][1] = 1;
           JLabel ct = link(0,1);
           ct.setText(String.valueOf(sign));
           int win = Iswin();
           if(win==1){
               try {
                   Won(1);
               } catch (IOException ex) {
                   Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
               }
           }else if(win==2){
               try {
                   Won(2);
               } catch (IOException ex) {
                   Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
               }
           }else if(cnt > 9){
               try {
                   Won(3);
               } catch (IOException ex) {
                   Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       }
       flip();
       clickable = false;
       compplay();
    }//GEN-LAST:event_box2MouseClicked

    private void box3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_box3MouseClicked
        if(array[0][2]==0 && clickable==true && ((cnt%2)==0)){
           array[0][2] = 1;
           JLabel ct = link(0,2);
           ct.setText(String.valueOf(sign));
           int win = Iswin();
           if(win==1){
               try {
                   Won(1);
               } catch (IOException ex) {
                   Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
               }
           }else if(win==2){
               try {
                   Won(2);
               } catch (IOException ex) {
                   Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
               }
           }else if(cnt > 9){
               try {
                   Won(3);
               } catch (IOException ex) {
                   Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       }
       flip();
       clickable = false;
       compplay();
    }//GEN-LAST:event_box3MouseClicked

    private void box4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_box4MouseClicked
        if(array[1][0]==0 && clickable==true && ((cnt%2)==0)){
           array[1][0] = 1;
           JLabel ct = link(1,0);
           ct.setText(String.valueOf(sign));
           int win = Iswin();
           if(win==1){
               try {
                   Won(1);
               } catch (IOException ex) {
                   Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
               }
           }else if(win==2){
               try {
                   Won(2);
               } catch (IOException ex) {
                   Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
               }
           }else if(cnt > 9){
               try {
                   Won(3);
               } catch (IOException ex) {
                   Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       }
       flip();
       clickable = false;
       compplay();
    }//GEN-LAST:event_box4MouseClicked

    private void box5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_box5MouseClicked
        if(array[1][1]==0 && clickable==true && ((cnt%2)==0)){
           array[1][1] = 1;
           JLabel ct = link(1,1);
           ct.setText(String.valueOf(sign));
           int win = Iswin();
           if(win==1){
               try {
                   Won(1);
               } catch (IOException ex) {
                   Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
               }
           }else if(win==2){
               try {
                   Won(2);
               } catch (IOException ex) {
                   Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
               }
           }else if(cnt > 9){
               try {
                   Won(3);
               } catch (IOException ex) {
                   Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       }
       flip();
       clickable = false;
       compplay();
    }//GEN-LAST:event_box5MouseClicked

    private void box6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_box6MouseClicked
       if(array[1][2]==0 && clickable==true && ((cnt%2)==0)){
           array[1][2] = 1;
           JLabel ct = link(1,2);
           ct.setText(String.valueOf(sign));
           int win = Iswin();
           if(win==1){
               try {
                   Won(1);
               } catch (IOException ex) {
                   Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
               }
           }else if(win==2){
               try {
                   Won(2);
               } catch (IOException ex) {
                   Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
               }
           }else if(cnt > 9){
               try {
                   Won(3);
               } catch (IOException ex) {
                   Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       }
       flip();
       clickable = false;
       compplay();
    }//GEN-LAST:event_box6MouseClicked

    private void box7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_box7MouseClicked
        if(array[2][0]==0 && clickable==true && ((cnt%2)==0)){
           array[2][0] = 1;
           JLabel ct = link(2,0);
           ct.setText(String.valueOf(sign));
           int win = Iswin();
           if(win==1){
               try {
                   Won(1);
               } catch (IOException ex) {
                   Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
               }
           }else if(win==2){
               try {
                   Won(2);
               } catch (IOException ex) {
                   Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
               }
           }else if(cnt > 9){
               try {
                   Won(3);
               } catch (IOException ex) {
                   Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       }
       flip();
       clickable = false;
       compplay();
    }//GEN-LAST:event_box7MouseClicked

    private void box8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_box8MouseClicked
       if(array[2][1]==0 && clickable==true && ((cnt%2)==0)){
           array[2][1] = 1;
           JLabel ct = link(2,1);
           ct.setText(String.valueOf(sign));
           int win = Iswin();
           if(win==1){
               try {
                   Won(1);
               } catch (IOException ex) {
                   Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
               }
           }else if(win==2){
               try {
                   Won(2);
               } catch (IOException ex) {
                   Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
               }
           }else if(cnt > 9){
               try {
                   Won(3);
               } catch (IOException ex) {
                   Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       }
       flip();
       clickable = false;
       compplay();
    }//GEN-LAST:event_box8MouseClicked

    private void box9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_box9MouseClicked
        if(array[2][2]==0 && clickable==true && ((cnt%2)==0)){
           array[2][2] = 1;
           JLabel ct = link(2,2);
           ct.setText(String.valueOf(sign));
           int win = Iswin();
           if(win==1){
               try {
                   Won(1);
               } catch (IOException ex) {
                   Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
               }
           }else if(win==2){
               try {
                   Won(2);
               } catch (IOException ex) {
                   Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
               }
           }else if(cnt > 9){
               try {
                   Won(3);
               } catch (IOException ex) {
                   Logger.getLogger(WithAI.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       }
       flip();
       clickable = false;
       compplay();
    }//GEN-LAST:event_box9MouseClicked

    private void jRadioButtonMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem1ActionPerformed
        diff=1;
        reset();
    }//GEN-LAST:event_jRadioButtonMenuItem1ActionPerformed

    private void jRadioButtonMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem3ActionPerformed
        diff=2;
        reset();
    }//GEN-LAST:event_jRadioButtonMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if(vs==1)
        {
            pl1="You";
            pl2="Computer";
        }
        reset();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        JOptionPane.showMessageDialog(null, "Play by clicking with the mouse");
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
       JOptionPane.showMessageDialog(null, "Author : Shaheed Ahmed Dewan Sagar\nAUST CSE\n12-01-04-085");
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WithAI().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem3;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private javax.swing.JLabel statusbox;
    // End of variables declaration//GEN-END:variables

}
