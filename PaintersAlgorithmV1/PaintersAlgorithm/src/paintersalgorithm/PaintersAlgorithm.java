/*
 * By: Alejandra Pérez
 * Juan Manuel Mejía Botero
 * Sebastian López Valencia

 */
package paintersalgorithm;

import Geometry.Rectangle;
import Math.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author htrefftz
 */
public class PaintersAlgorithm extends JPanel{
    private Rectangle rec;
    public PaintersAlgorithm(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            double xMin = 100d;
            double xMax = 300d;
            double yMin = 100d;
            double yMax = 300d;
            double z1 = -200d;
            double z2 = -200d;
            double z3 = -200d;
            Rectangle rect = new Rectangle(xMin, xMax, yMin, yMax, z1, z2, z3);
            System.out.println(rect);
            PaintersAlgorithm panel = new PaintersAlgorithm(true);
            JFrame fr = new JFrame();
            fr.setSize(600,600);
            fr.setContentPane(panel);
            fr.setDefaultCloseOperation(EXIT_ON_CLOSE);
            fr.setResizable(false);
            fr.setVisible(true);
            fr.setLocationRelativeTo(null);
            Graphics2D gr = (Graphics2D)panel.getGraphics();
            JOptionPane.showMessageDialog(panel,"Hola Solo debes presionar aceptar para ver el resultado del algoritmo");
            int xMinr2, xMaxr2,yMinr2,yMaxr2;
            int n1 = panel.randomizeCoords();
            int n2 = panel.randomizeCoords();
            if(n1 < n2){
                xMinr2 = n1;
                xMaxr2 = n2;
            }else{
                 xMinr2 = n2;
                xMaxr2 = n1;
            }
            int n3 = panel.randomizeCoords();
            int n4 = panel.randomizeCoords();
             if(n3 < n4){
                yMinr2 = n3;
                yMaxr2 = n4;
            }else{
                yMinr2 = n4;
                yMaxr2 = n3;
            }
            int n5 = panel.randomizeCoords();
            int n6 = panel.randomizeCoords();
            int n7 = panel.randomizeCoords();
            Rectangle rect2 = new Rectangle(xMinr2,xMaxr2,yMinr2,yMaxr2,n5,n6,n7);
            panel.managePainter(rect, rect2, gr, Color.black, Color.blue);
            // TODO code application logic herePaintersAlgorithm
        } catch (Exception ex) {
            Logger.getLogger(PaintersAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int randomizeCoords(){
        Random num = new Random();
        return (num.nextInt(this.getWidth())/2);
    }
    public void managePainter(Rectangle r1, Rectangle r2,Graphics2D gr, Color color1, Color color2){
        if(painter(r1,r2)){
            //Paint first r1
            r1.paintRectangle(gr, color1);
            r2.paintRectangle(gr, color2);
        }else if(painter(r2,r1)){
            //Paint first r2
            r2.paintRectangle(gr, color1);
            r1.paintRectangle(gr, color2);
        }else{
           //conflict
            r1.paintBorder(gr, color1);
            r2.paintBorder(gr, color2);
            JOptionPane.showMessageDialog(this, "Conflicto detectado, PUM!");
        }
    }
    
    public boolean painter(Rectangle r1, Rectangle r2){
       double zMaxr1 = r1.getZMax();
       double zMinr2 = r2.getZMin();
       if(case1(zMaxr1,zMinr2)){
           return true;
       }else if(case2(r1,r2)){
           return true;
       }else if(case3(r1,r2)){
           return true;
       }else if (case4(r1,r2)){
           return true;
       }
       return false;
    }
    /**
     * 
     * @param z1 ZMax S
     * @param z2 zMin S'
     * @return 
     */
    public boolean case1(double z1, double z2){
        if(z1 < z2){
            return true;
        }
        return false;
    }
    
    public boolean case2(Rectangle r1, Rectangle r2){
        return !(r1.intersect(r2));
    }
    
    public boolean case3(Rectangle r1, Rectangle r2){
        Point arr[] =  r1.getPointsArray();
        for (Point arr1 : arr) {
            double evaluation = r2.getPlane().evaluatePointInPlane(arr1.getX(), 
                     arr1.getY(), arr1.getZ());
            System.out.println("res eval:"+evaluation);
            if (evaluation < 0){
                return false;
            }
        }
        System.out.println("está por debajo");
        return true;
    }
    
    public boolean case4(Rectangle r1, Rectangle r2){
        Point arr[] =  r1.getPointsArray();
        for (Point arr1 : arr) {
            double evaluation = r2.getPlane().evaluatePointInPlane(arr1.getX(), 
                     arr1.getY(), arr1.getZ());
            System.out.println("res eval:"+evaluation);
            if (evaluation > 0){
                return false;
            }
        }
        System.out.println("está por encima");
        return true;
    }
    
}
