/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto5;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author Sebastian-PC
 */
public class Reto5 extends JPanel implements KeyListener {
    public static final int panelWidth = 600;
    public static final int panelHeight = 400;
    private int arr[][];
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Reto 5");
        Reto5 panel = new Reto5();
        frame.setContentPane(panel);
        frame.setSize(panelWidth,panelHeight);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // TODO code application logic here
    }

    public Reto5() {
       this.arr = new int[2][2];
       this.setBackground(Color.WHITE);
       this.setFocusable(true);
       this.addKeyListener(this);
    }
    
    

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("You pressed"+e.getKeyChar());
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
