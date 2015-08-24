/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto2;

/**
 *
 * @author Sebastian Lopez, Juan Manuel Mejia y Alejandra Perez
 */

public class Edge {
    Vertex start;
    Vertex end;
    /**
     * Constructor
     * @param start start Vertex
     * @param fin end Vertex
     */
    public Edge(Vertex start, Vertex fin){
        this.start = start;
        this.end = fin;
    }
}