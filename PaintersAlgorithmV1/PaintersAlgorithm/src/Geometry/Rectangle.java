/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geometry;

import Math.Point;
import Math.Plane;
import java.awt.Color;
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
public class Rectangle {
    // lower left
    Point p1;
    // lower right
    Point p2;
    // upper right
    Point p3;
    // upper left
    Point p4;
    // plane. The equation is formed with p1, p2, and p3
    Plane plane;
    int distance = 100;
    /**
     * Axis-aligned rectangle. 
     * Corners are P1, P2, P3 and P4.
     * The Z coordinate of P4 is computed, so that P4 is coplanar with 
     * P1, P2 and P3
     * @param xMin Left side of the rectangle
     * @param xMax Right side of the rectangle
     * @param yMin Lower side of the rectangle
     * @param yMax Upper side of the rectangle
     * @param z1 z coordinate of Point 1
     * @param z2 z coordinate of Point 2
     * @param z3 z coordinate of Point 3
     * @throws java.lang.Exception In case there are an infinite number of
     * solutions for z4.
     */
    public Rectangle(double xMin, double xMax, double yMin, double yMax, 
            double z1, double z2, double z3) throws Exception {
        p1 = new Point(xMin, yMin, z1);
        p2 = new Point(xMax, yMin, z2);
        p3 = new Point(xMax, yMax, z3);
        // Given 3 points (p1, p2 and p3), create the plane
        plane = new Plane(p1, p2, p3);
        // Evaluate z4, based on the equation of the plane
        double z4 = plane.evaluateZ(xMin, yMax);
        // Now create p4, the final point
        p4 = new Point(xMin, yMax, z4);
    }

    @Override
    public String toString() {
        return "Rectangle{" + "p1=" + p1 + ", p2=" + p2 + ", p3=" + p3 + ", p4=" + p4 + ", plane=" + plane + '}';
    }

    public static void main(String [] args) throws Exception {
        double xMin = 50d;
        double xMax = 200d;
        double yMin = 50d;
        double yMax = 200d;
        double z1 = -220d;
        double z2 = -200d;
        double z3 = -200d;
        Rectangle rect = new Rectangle(xMin, xMax, yMin, yMax, z1, z2, z3);
        System.out.println(rect);
        JPanel panel = new JPanel();
        JFrame fr = new JFrame();
        fr.setSize(400,400);
        fr.setContentPane(panel);
        fr.setDefaultCloseOperation(EXIT_ON_CLOSE);
        fr.setResizable(false);
        fr.setVisible(true);
        
        Graphics2D gr = (Graphics2D)panel.getGraphics();
        rect.paintRectangle(gr,Color.red);
    }
    
    public void paintRectangle(Graphics2D gr, Color col){
        gr.setColor(col);
        long init = System.currentTimeMillis();
        for(int i = (int)this.p1.getX(); i < (int)this.p2.getX();i++){
            for(int j = (int)this.p1.getY(); j < (int)this.p3.getY();j++){
                try {
                    int xp = Math.abs((int)(i*(distance/this.plane.evaluateZ(i, j))));
                    int yp = Math.abs((int)(j*(distance/this.plane.evaluateZ(i, j))));
                    gr.drawLine(xp, yp, xp, yp);
                } catch (Exception ex) {
                    Logger.getLogger(Rectangle.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(""+(end-init));
    }
}
