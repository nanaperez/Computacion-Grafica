/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Math;

/**
 *
 * @author htrefftz
 */
public class Plane {
    double a;
    double b;
    double c;
    double d;
    
    private final boolean DEBUG = true;
    
    /**
     * Given 3 points, compute a, b, c and d
     * Equation of the plane is Ax + By + Cz + D = 0
     * @param p1
     * @param p2
     * @param p3 
     */
    public Plane (Point p1, Point p2, Point p3) {
        Vector4 v1 = new Vector4(p1.x, p1.y, p1.z, p2.x, p2.y, p2.z);
        Vector4 v2 = new Vector4(p2.x, p2.y, p2.z, p3.x, p3.y, p3.z);
        Vector4 n = Vector4.crossProduct(v1, v2);
        n.normalize();
        a = n.getX();
        b = n.getY();
        c = n.getZ();
        d = -(a * p1.x + b * p1.y + c * p1.z);
        if(DEBUG)
            System.out.println(this);
    }
    public double evaluatePointInPlane(double x, double y, double z){
        return this.a*x + this.b*y + this.c*z + d;
    }
    /**
     * Given the x and y coordinates of a point on the plane, find the z coordinate
     * @param x x coordinate
     * @param y y coordinate
     * @return z coordinate of the point
     * @throws Exception In case parameter c is 0, there are infinite solutions
     */
    public double evaluateZ(double x, double y) throws Exception {
        if(c == 0) {
            throw new Exception("Infinitas soluciones");
        }
        double z = -(a * x + b * y + d) / c;
        return z;
    }

    @Override
    public String toString() {
        return "Plane{" + "a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + '}';
    }
}
