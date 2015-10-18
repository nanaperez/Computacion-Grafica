/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintersalgorithm;

import Geometry.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;

/**
 *
 * @author htrefftz
 */
public class PaintersAlgorithm extends JPanel{
    private Rectangle rec;
    public PaintersAlgorithm(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            double xMin = 100d;
            double xMax = 300d;
            double yMin = 100d;
            double yMax = 300d;
            double z1 = -200d;
            double z2 = -150d;
            double z3 = -200d;
            Rectangle rect = new Rectangle(xMin, xMax, yMin, yMax, z1, z2, z3);
            System.out.println(rect);
            PaintersAlgorithm panel = new PaintersAlgorithm(true);
            JFrame fr = new JFrame();
            fr.setSize(600,500);
            fr.setContentPane(panel);
            fr.setDefaultCloseOperation(EXIT_ON_CLOSE);
            fr.setResizable(false);
            fr.setVisible(true);
            
            Graphics2D gr = (Graphics2D)panel.getGraphics();
            rect.paintRectangle(gr,Color.red);
            // TODO code application logic herePaintersAlgorithm
        } catch (Exception ex) {
            Logger.getLogger(PaintersAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void managePainter(){
        if(){
            
        }else if(){
            
        }else{
           //conflict
        }
    }
    public boolean painter(Rectangle r1, Rectangle r2){
        
    }
    public boolean case1(double z1, double z2){
        if(z1 < z2){
            return true;
        }
        return false;
    }
    
    public boolean case2(Rectangle r1, Rectangle r2){
        return r1.intersect(r2);
    }
    
}
