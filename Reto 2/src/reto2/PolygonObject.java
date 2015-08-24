/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto2;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Sebastian Lopez, Juan Manuel Mejia y Alejandra Perez
 */
public class PolygonObject{
    ArrayList<Edge> edges;

    /**
     * Constructor
     */
    public PolygonObject(){
        edges = new ArrayList<>();
    }
    
    /**
     * Add an edge to the collection of edges
     * @param edge edge to be added
     */
    public void addEdge(Edge edge) {
        edges.add(edge);
    }
    
    /**
     * Draw the lines composing the polygon
     * @param gr JPanel to draw the object on
     */
    public void drawObject(Graphics2D gr){
        edges.stream().forEach((edge) -> {
            gr.drawLine(edge.start.x, edge.start.y, edge.end.x, edge.end.y);
        });
    }
   
    public void drawOneLine(Graphics2D gr, Edge edge){
        int x1 = edge.start.x;
        int y1 = edge.start.y;
        int x2 = edge.end.x;
        int y2 = edge.end.y;
        gr.drawLine(x1, y1, x2, y2);
    }
}