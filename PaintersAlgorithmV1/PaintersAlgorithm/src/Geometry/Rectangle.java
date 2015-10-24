package Geometry;

import Math.Point;
import Math.Plane;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
/**
 *
 * @author htrefftz edited by: Sebastian López, Alejandra Pérez, Juan Manuel Mejía 
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
    Point arr[];

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public Point getP3() {
        return p3;
    }

    public Point getP4() {
        return p4;
    }

    public int getDistance() {
        return distance;
    }

    public Point[] getArr() {
        return arr;
    }
    
    public double getZMax(){
        double zMax = p1.getZ();
        for(Point arr1:arr){
            if(arr1.getZ()>zMax){
                zMax = arr1.getZ();
            }
        }
        return zMax;
    }
    
     public double getZMin(){
        double zmin = p1.getZ();
        for(Point arr1:arr){
            if(arr1.getZ()<zmin){
                zmin = arr1.getZ();
            }
        }
        return zmin;
    }
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
        arr = new Point[4];
        arr[0] = p1;
        arr[1] = p2;
        arr[2] = p3;
        arr[3] = p4;
    }
    public boolean intersect(Rectangle two){
        Rectangle2D.Double one = new Rectangle2D.Double(this.p1.getX(),
                this.p1.getY(),this.p2.getX() - this.p1.getX(),
                this.p3.getY() - this.p1.getY());
         Rectangle2D.Double twot = new Rectangle2D.Double(two.p1.getX(),
                two.p1.getY(),two.p2.getX() - two.p1.getX(),
                two.p3.getY() - two.p1.getY());
        return one.intersects(twot);
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
        rect.paintBorder(gr,Color.red);
    }
    
    public void paintRectangle(Graphics2D gr, Color col){
        System.out.println(this.toString());
        gr.setColor(col);
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
    }
    public void paintBorder(Graphics2D gr, Color col){
        gr.setColor(col);
        boolean sw1 = true, sw2 = true;
        int nX = (int)Math.round(this.p2.getX()-1);
        int nY = (int)Math.round(this.p3.getY()-1);
         for(int i = (int)this.p1.getX(); i < (int)this.p2.getX();i++){
             sw2 = true;
            for(int j = (int)this.p1.getY(); j < (int)this.p3.getY();j++){
                try {
                    int xp = Math.abs((int)(i*(distance/this.plane.evaluateZ(i, j))));
                    int yp = Math.abs((int)(j*(distance/this.plane.evaluateZ(i, j))));
                    if(sw1 || i == nX || sw2 || j == nY){
                        gr.drawLine(xp, yp, xp, yp);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(Rectangle.class.getName()).log(Level.SEVERE, null, ex);
                }
                sw2 = false;
            }
            sw1 = false;
        }
        
    }
    
    public Point[] getPointsArray(){
        return arr;
    }

    public Plane getPlane() {
        return plane;
    }
    
}
