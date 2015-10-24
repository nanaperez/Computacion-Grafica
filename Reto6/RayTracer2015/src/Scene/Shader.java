/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scene;

import Math.Point;
import Math.Vector4;

/**
 *
 * @author htrefftz
 */
public class Shader {
    public static Colour computeColor(Point point, Vector4 normal, Material material) {
        normal.normalize();
        Point viewer = new Point(0,0,0);
        Vector4 v = new Vector4(viewer,point);
        v.normalize();
        // We will add all the colors in acum
        Colour acum = new Colour(0, 0, 0);
        // Compute the Ambient Reflection
        Colour AmbientReflection = Colour.multiply(Colour.multiply(Scene.ambientLight.color, material.color), 
                material.Ka);
        acum = Colour.add(acum, AmbientReflection);
        // Compute the Diffuse Reflection, respect to all point lights
        for(PointLight pl: Scene.pointLights) {
            Vector4 light = new Vector4(point, pl.point);
            light.normalize();
            double scalar = Vector4.dotProduct(normal, light) * material.Kd;
            // If dot product is < 0, the point is not receiving light from
            // this source.
            if(scalar < 0) scalar = 0;
            Colour DiffuseReflection = Colour.multiply(Colour.multiply(pl.color, material.color), 
                    scalar);
            acum = Colour.add(acum, DiffuseReflection);
        }
        for(PointLight pl: Scene.pointLights) {
            Vector4 light = new Vector4(point, pl.point);
            light.normalize();
            Vector4 rSpecular = Vector4.specular(light, normal);
            rSpecular.normalize();
            double scalar = Math.pow(Vector4.dotProduct(rSpecular, v),material.n);
            scalar *= material.Ks;
            Colour specularReflection = Colour.multiply(Colour.multiply(pl.color, material.color), 
                    scalar);
            acum = Colour.add(acum, specularReflection);
            //double scalar = Vector4.dotProduct(normal, light) * material.Kd;
            // If dot product is < 0, the point is not receiving light from
            // this source.
            //if(scalar < 0) scalar = 0;
            //Colour DiffuseReflection = Colour.multiply(Colour.multiply(pl.color, material.color), 
              //      scalar);
            //acum = Colour.add(acum, DiffuseReflection);
        }
        // Compute the Specular Reflection (pending)
        
        return acum;
    }
}
