package Geometry;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Math.Vector4;

/**
 * 
 * @author Sebastian Lopez, Juan Manuel Mejia y Alejandra Perez
 * With collaboration of Helmuth Trefftz
 */
public class Edge {
    Vector4 start;
    Vector4 end;

    /**
     * Constructor
     * @param start start Vertex
     * @param end end Vertex
     */
    
    public Edge(Vector4 start, Vector4 end) {
        this.start = start;
        this.end = end;
    }
    
    @Override
    public String toString() {
        String s = "Edge ";
        s += "start " + start.toString() + "end " + end.toString();
        return s;
    }
}