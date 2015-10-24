/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scene;

import Math.Point;
import java.util.ArrayList;
import Math.Sphere;
import Math.Ray;
import Math.Solutions;
import Math.Vector4;
import java.util.TreeSet;
import javafx.scene.paint.Color;

/**
 *
 * @author htrefftz
 */
public class Scene {
    // Ambient light, comes from "everywhere"
    static AmbientLight ambientLight;
    // Several Point Lights
    static ArrayList<PointLight> pointLights = new ArrayList<>();
    // Several Spheres
    static ArrayList<Sphere> spheres = new ArrayList<>();
    // Bacground color
    static final Colour BACKGROUNDCOLOR = new Colour(0.7, 0.7, 0.7);
    private static TreeSet<Sphere> tree = new TreeSet();
    
    /**
     * Set the ambient light in the scene
     * @param myAmbientLight Ambient light to set
     */
    public static void setAmbientLight(AmbientLight myAmbientLight) {
        ambientLight = myAmbientLight;
    }
    
    /**
     * Add a point light
     * @param myPointLight Point Light to be added 
     */
    public static void addPointLight(PointLight myPointLight) {
        pointLights.add(myPointLight);
    }
    
    /**
     * Add a Sphere
     * @param mySphere Sphere to be added
     */
    public static void addSphere(Sphere mySphere) {
        spheres.add(mySphere);
    }

    /**
     * Compute the color of the closest object in the Scene this ray 
     * intersects with
     * @param ray Ray to intersect with the Scene
     * @return 
     */
    public static Colour intersectRay(Ray ray) {
        double minT = Double.MAX_VALUE;
        Sphere closest = null;
        // Chose the closest surface
        for(Sphere sphere: spheres) {
            Solutions s = Sphere.intersect(sphere, ray);
            if(s.getNumSolutions() > 0) {
                // ignore solutins with a t value smaller than 0,
                // because they are behind the origin of the ray
                if(s.getT1() > 0.01) {
                    // Is this surface closer to the origin of the ray?
                    if(s.getT1() < minT) {
                        minT = s.getT1();
                        closest = sphere;
                    }
                }
            }
        }
        if(closest != null) {
            Colour acum  = new Colour(0, 0, 0);
            double Ko = closest.getMaterial().Ko;
            double Kr = closest.getMaterial().Kr;
            double Kt = closest.getMaterial().Kt;
            // Compute this oject's color
            if(Ko != 0) {
                Colour thisColor = Colour.multiply(closest.callShader(ray, minT),Ko);
                acum = Colour.add(acum, thisColor);
            }
            // Compute the reflection
            if(Kr != 0) {
                    Solutions s = Sphere.intersect(closest, ray);
                    if(s.getNumSolutions() > 1) {
                        double t2 = s.getT1();
                        Point end = ray.evaluate(t2);
                        // create the reflecion ray,
                        Vector4 normal = Vector4.computeNormal(end, closest.getCenter());
                        Vector4 r = Vector4.reflection(ray.getU(), normal);
                        Ray reflectedRay = new Ray(end,r);
                    // send the ray to intersect with objects in the scene (Scene.intersectRay(reflectedRay))
                        //if(!tree.contains(closest)){
                          Colour colour = Scene.intersectRay(reflectedRay);
                          colour = Colour.multiply(colour, Kr);
                          acum = Colour.add(acum, colour);
                        //}
                    }
                
                // (this is where recursion takes place)
                
                // multiply the color by the corresponding Weight (Kr) 
                // and add the color to acum
            }
            return acum;
        }
        return BACKGROUNDCOLOR;
    }

}
