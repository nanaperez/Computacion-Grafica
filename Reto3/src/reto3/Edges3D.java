/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto3;

/**
 *
 * @author Sebastian-PC
 */
public class Edges3D {
    
    Vertex3D start;
    Vertex3D end;

    /**
     * Constructor
     * @param start start Vertex
     * @param end end Vertex
     */
    public Edges3D(Vertex3D start, Vertex3D end) {
        this.start = start;
        this.end = end;
    }
}
