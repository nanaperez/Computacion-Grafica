
package reto3;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @authors Sebastian López, Juan Manuel Mejía, Alejandra Pérez
 */
public class PolygonObject3D {
        ArrayList<Edges3D> edges3D;
        Vertex3D arr[];
 /**
  * Constructor
  */
    public PolygonObject3D() {
        edges3D = new ArrayList<>();
    }

    public Vertex3D[] getArr() {
        return arr;
    }

    public void setArr(Vertex3D[] arr) {
        this.arr = arr;
    }
    
    /**
     * Add an edge to the collection of edges
     * @param edge edge to be added
     */
    public void addEdge(Edges3D edge) {
        edges3D.add(edge);
    }
    
    /**
     * Draw the lines composing the polygon
     * @param gr JPanel to draw the object on
     */
    public void drawObjectScale(Graphics2D gr, int distance, Color color, int widthPanel, int heightPanel) {
        gr.clearRect(0, 0, 800, 800);
        gr.setColor(color);
        distance = (int)(distance / Math.tan(20));
        for(Edges3D edge: edges3D) {
            int x0 = edge.start.x / (edge.start.z / distance);
            int y0 = edge.start.y / (edge.start.z /distance);
            int x1 =edge.end.x / (edge.end.z/distance);
            int y1 = edge.end.y / (edge.end.z/distance);
            gr.drawLine(x0+ widthPanel /2,heightPanel/2 - y0, x1+ widthPanel /2,heightPanel/2 - y1);
        }   
    }
}
