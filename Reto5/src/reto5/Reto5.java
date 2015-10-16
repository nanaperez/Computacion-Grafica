/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto5;

import Geometry.DibujarCasita;
import Geometry.Edge;
import Geometry.PolygonObject;
import Math.UVNMatrix;
import Math.Vector4;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author Sebastian-PC
 */
public class Reto5 extends JPanel implements KeyListener {
    public static final int panelWidth = 600;
    public static final int panelHeight = 400;
    
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
        int x1 = panel.getPoints("x1");
        int y1 = panel.getPoints("y1");
        int z1 = panel.getPoints("z1");
        int x2 = panel.getPoints("x2");
        int y2 = panel.getPoints("y2");
        int z2 = panel.getPoints("z2");
        PolygonObject op = panel.buildRect3d(x1, y1, z1, x2, y2, z2);
        op.drawObject(null);
        // TODO code application logic here
    }
     public void drawOneLine(int x1, int y1, int x2, int y2) {

        Graphics2D g2d = (Graphics2D) this.getGraphics();
        g2d.drawLine(x1, y1, x2, y2);
    }
    public Reto5() {

       this.setBackground(Color.WHITE);
       this.setFocusable(true);
       this.addKeyListener(this);
    }
    
    private int getPoints(String point){
        int num = 0;
        String numS = JOptionPane.showInputDialog("Ingrese el punto: "+point);
        try{
            num = Integer.parseInt(numS);
        }catch(Exception e){
            num = getPoints(point);
        }
        return num;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("You pressed "+e.getKeyChar());
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * 
     * @param x1 x coord point nearest to cam at up to the left
     * @param y1 y coord point nearest to cam at up to the left
     * @param z1 z coord point nearest to cam at up to the left
     * @param x2 x coord point furthest to cam at down to the right
     * @param y2
     * @param z2
     * @return 
     */
    public PolygonObject buildRect3d(int x1, int y1, int z1, int x2, int y2, int z2){ 
        int x3 = x1;
        int y3 = y1;
        int z3 = z2;
        int x4 = x1;
        int y4 = y2;
        int z4 = z1;
        int x5 = x1;
        int y5 = y2;
        int z5 = z2;
        int x6 = x2;
        int y6 = y1;
        int z6 = z1;
        int x7 = x2;
        int y7 = y1;
        int z7 = z2;
        int x8 = x2;
        int y8 = y2;
        int z8 = z1;
        PolygonObject po = new PolygonObject();
        // Read the number of vertices
        int numVertices = 8;
        Vector4[] vertexArray = new Vector4[numVertices];
        vertexArray[0] = new Vector4(x1, y1, z1);
        vertexArray[1] = new Vector4(x2, y2, z2);
        vertexArray[2] = new Vector4(x3, y3, z3);
        vertexArray[3] = new Vector4(x4, y4, z4);
        vertexArray[4] = new Vector4(x5, y5, z5);
        vertexArray[5] = new Vector4(x6, y6, z6);
        vertexArray[6] = new Vector4(x7, y7, z7);
        vertexArray[7] = new Vector4(x8, y8, z8);
        //edges
        po.addEdge(new Edge(vertexArray[0], vertexArray[2]));
        po.addEdge(new Edge(vertexArray[0], vertexArray[3]));
        po.addEdge(new Edge(vertexArray[0], vertexArray[5]));
        po.addEdge(new Edge(vertexArray[1], vertexArray[7]));
        po.addEdge(new Edge(vertexArray[1], vertexArray[4]));
        po.addEdge(new Edge(vertexArray[1], vertexArray[6]));
        po.addEdge(new Edge(vertexArray[2], vertexArray[4]));
        po.addEdge(new Edge(vertexArray[2], vertexArray[6]));
        po.addEdge(new Edge(vertexArray[3], vertexArray[4]));
        po.addEdge(new Edge(vertexArray[3], vertexArray[7]));
        po.addEdge(new Edge(vertexArray[4], vertexArray[3]));
        po.addEdge(new Edge(vertexArray[5], vertexArray[6]));
        po.addEdge(new Edge(vertexArray[5], vertexArray[6]));
        return po;
    }
    public void drawPolygon(Graphics2D gr){
        
    }
    
}
