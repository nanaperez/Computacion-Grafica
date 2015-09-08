package Geometry;

/**
 */

import java.awt.*;

import javax.swing.JPanel;
import javax.swing.JFrame;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import Math.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 
 * @author Sebastian Lopez, Juan Manuel Mejia y Alejandra Perez
 * With collaboration of Helmuth Trefftz
 */

public class DibujarCasita extends JPanel implements KeyListener{

    PolygonObject po;

    private int mvVert = 0;
    private int mvHor = 0;

    private final int chngValue = 20;
    public static int AXIS_SIZE = 50;

    Dimension size;
    Graphics2D g2d;
    int proyectionPlaneDistance;

    /*
     * En esta función se dibuja.
     * La función es llamada por Java2D.
     * Se recibe una variable Graphics, que contiene la información del contexto
     * gráfico.
     * Es necesario hacerle un cast a Graphics2D para trabajar en Java2D.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g2d = (Graphics2D) g;
        //Size es el tamaño de la ventana
        size = getSize();
        
        //Draw the X axis
        g2d.setColor(Color.BLUE);
        drawOneLine(-DibujarCasita.AXIS_SIZE, 0, DibujarCasita.AXIS_SIZE, 0);

        //Draw the Y axis
        g2d.setColor(Color.BLUE);
        drawOneLine(0, -DibujarCasita.AXIS_SIZE, 0, DibujarCasita.AXIS_SIZE);

        //Draw the polygon object
        g2d.setColor(Color.GREEN);
        po.drawObject(this);  
    }

    /**
     * This function draws one line on this JPanel.
     * A mapping is done in order to:
     * - Have the Y coordinate grow upwards
     * - Have the origin of the coordinate system in the middle of the panel
     *
     * @param x1 Starting x coordinate of the line to be drawn
     * @param y1 Starting y coordinate of the line to be drawn
     * @param x2 Ending x coordinate of the line to be drawn
     * @param y2 Ending x coordinate of the line to be drawn
     */
    
    public void drawOneLine(int x1, int y1, int x2, int y2){

        x1 = x1 + size.width / 2;
        x2 = x2 + size.width / 2;

        y1 = size.height / 2 - y1;
        y2 = size.height / 2 - y2;

        g2d.drawLine(x1, y1, x2, y2);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        if(KeyEvent.VK_UP == e.getKeyCode()){
            mvVert += chngValue;
        }
        else if(KeyEvent.VK_DOWN == e.getKeyCode()){
            mvVert -= chngValue;
        }
        else if(KeyEvent.VK_LEFT == e.getKeyCode()){
            mvHor -= chngValue; 
        }
        else if(KeyEvent.VK_RIGHT == e.getKeyCode()){
            mvHor += chngValue; 
        }
        
        readObjectDescription("casita3D.txt");
        UVNMatrix m3 = new UVNMatrix(
                new Vector4(0+mvHor, 0+mvVert, -200),   //camera position
                new Vector4(0, 0, -350),                //look-at 
                new Vector4(0, 1, 0)                    //up vector
        );
        po.transformObject(m3);
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {      
    }
    
    /**
     * Read the description of the object from the given file
     * @param fileName Name of the file with the object description
     */
    public void readObjectDescription(String fileName){
        
        Scanner in;
        po = new PolygonObject();
        try{
            in = new Scanner(new File(fileName));
            //Read the number of vertices
            int numVertices = in.nextInt();
            Vector4[] vertexArray = new Vector4[numVertices];
            //Read the vertices
            for(int i = 0; i < numVertices; i++){
                //Read a vertex
                int x = in.nextInt();
                int y = in.nextInt();
                int z = in.nextInt();
                vertexArray[i] = new Vector4(x, y, z);
            }
            //Read the number of edges
            int numEdges = in.nextInt();
            //Read the edges
            for(int i = 0; i < numEdges; i++){
                //Read an edge
                int start = in.nextInt();
                int end = in.nextInt();
                Edge edge = new Edge(vertexArray[start], vertexArray[end]);
                po.addEdge(edge);
            }
            //Read the Project Plane Distance to the virtual camera
            proyectionPlaneDistance = in.nextInt();
        } 
        catch (FileNotFoundException e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        
        DibujarCasita dc = new DibujarCasita();
        //Read the file with the object description
        dc.readObjectDescription("casita3D.txt");
        UVNMatrix m3 = new UVNMatrix(
                new Vector4(200, 100, -200),    //camera position
                new Vector4(0, 0, -350),        //look-at 
                new Vector4(0, 1, 0)            //up vector
        );
        dc.po.transformObject(m3);
        //Crear un nuevo Frame
        JFrame frame = new JFrame("Casita3D - J.A.S Software");
        //Al cerrar el frame, termina la ejecución de este programa
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Agregar el listener de las teclas
        dc.addKeyListener(dc);
        dc.setFocusable(true);
        frame.add(dc);
        //Asignarle tamaño
        frame.setSize(600, 400);
        //Poner el frame en el centro de la pantalla
        frame.setLocationRelativeTo(null);
        //Mostrar el frame
        frame.setVisible(true);
    }
}