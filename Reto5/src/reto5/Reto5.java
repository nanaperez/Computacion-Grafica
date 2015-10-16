/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto5;

import Geometry.Edge;
import Geometry.PolygonObject;
import Math.Vector4;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
    private int mvVert = 0, mvHor = 0;
    private static PolygonObject op;
    private static final int chngValue = 10;
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
        op = panel.buildRect3d(x1, y1, z1, x2, y2, z2);
        op.drawObject(panel);
        panel.fillObject(x1, y1, x2, y2);
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
    
    private void fillObject(int x1, int y1, int x2, int y2){
         Graphics2D g2d = (Graphics2D) this.getGraphics();
        
        for(int i = x1; i < x2;i++){
            for(int j = y1; j < y2;j++){
                g2d.drawLine(i, j, i, j);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

//To change body of generated methods, choose Tools | Templates.
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
        int x3 = x2;
        int y3 = y1;
        int z3 = z1;
        int x4 = x1;
        int y4 = y2;
        int z4 = z2;
        
        PolygonObject po = new PolygonObject();
        // Read the number of vertices
        int numVertices = 4;
        Vector4[] vertexArray = new Vector4[numVertices];
        vertexArray[0] = new Vector4(x1, y1, z1);
        vertexArray[1] = new Vector4(x2, y2, z2);
        vertexArray[2] = new Vector4(x3, y3, z3);
        vertexArray[3] = new Vector4(x4, y4, z4);
        //edges
        po.addEdge(new Edge(vertexArray[0], vertexArray[2]));
        po.addEdge(new Edge(vertexArray[0], vertexArray[3]));
        po.addEdge(new Edge(vertexArray[1], vertexArray[2]));
        po.addEdge(new Edge(vertexArray[1], vertexArray[3]));
        return po;
    }

    private void cleanWindow(){
        Graphics2D gr = (Graphics2D) this.getGraphics();
        gr.clearRect(0, 0, this.getWidth(), this.getHeight());
    }
}
