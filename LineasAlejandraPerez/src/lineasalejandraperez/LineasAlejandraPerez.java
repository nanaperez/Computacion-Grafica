/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineasalejandraperez;

import java.awt.*;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author Alejandra Pérez
 */
public class LineasAlejandraPerez extends JPanel{
    
    //n es el numero de puntos a dibujar
    int n = 15;
    int x;
    int y;
    //Se crea un ArrayList donde se guardan los puntos (n)
    public ArrayList<LineasAlejandraPerez> puntosArreglo = new ArrayList<LineasAlejandraPerez>();
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //Size es el tamaño de la ventana.
        Dimension size = getSize();
        //Insets son los bordes y los títulos de la ventana.
        Insets insets = getInsets();

        int W =  size.width - insets.left - insets.right;
        int H =  size.height - insets.top - insets.bottom;
        
        //Llamamos a Pi
        double pi = Math.PI;
        this.puntosArreglo.clear();
        //Este ciclo permite guardar los puntos en el ArrayList
        for (int i = 0; i < this.n; i++){
            //Xm y Ym son variables para obtener el x y y en toda la mitad (como si fuera el plano cartesiano
            int Xm = (int) (100*(cos((2*pi/n)*i)));
            int Ym = (int) (100*(sin((2*pi/n)*i)));
            
            LineasAlejandraPerez punto = new LineasAlejandraPerez();
            int octante = this.obtenerOct((2*pi/n)*i);
            
            g2d.drawLine(punto.x, punto.y, punto.x, punto.y);
            LineasAlejandraPerez pixeles = this.Pintar(octante, Xm, Ym);
            pixeles.x = Xm + W/2;
            //El -Ym se debe a que hay que cambiarlo para que quede como el plano cartesiano normal
            pixeles.y = -Ym + H/2;
            this.puntosArreglo.add(pixeles);
        }
        //Hago dos for para recorrer el ArrayList de los puntos
        for (int i = 0; i < this.puntosArreglo.size(); i++){
            for (int j = i; j < this.puntosArreglo.size()-1; j++){
                this.Bresenham(g, this.puntosArreglo.get(i).x, this.puntosArreglo.get(i).y, 
                               this.puntosArreglo.get(j+1).x, this.puntosArreglo.get(j+1).y);
            }
        }
    }

    public int obtenerOct(double angulo) {
        return 0;
    }

    public LineasAlejandraPerez Pintar(int octante, int x, int y) {
        
        LineasAlejandraPerez punto = new LineasAlejandraPerez();
        switch (octante){
            //Primer octante
            case 0:
                punto.x = x;
                punto.y = y;
                break;
            //Segundo octante
            case 1:
                punto.x = y;
                punto.y = x;
                break;
            //Tercer octante
            case 2:
                punto.x = y;
                punto.y = -x;
                break;
            //Cuarto octante
            case 3:
                punto.x = -x;
                punto.y = -y;
                break;
            //Quinto octante
            case 4:
                punto.x = -x;
                punto.y = -y;
                break;
            //Sexto octante
            case 5:
                punto.x = -y;
                punto.y = -x;
                break;
            //Septimo octante
            case 6:
                punto.x = -y;
                punto.y = x;
                break;
            //Octavo octante
            case 7:
                punto.x = x;
                punto.y = -y;
                break;
            default:
                break;
        }
        return punto;
    }
    
    public void Bresenham (Graphics g,int x0, int y0, int x1, int y1){ 
        //xp y yp son variables que se nombran asi para no confundirlas con las otras
        int xp, yp, dx, dy, p, incE, incNE, stepx, stepy;
        dx = x1 - x0;
        dy = y1 - y0;
        //Determinar que punto usar para empezar, y cual para terminar
        if (dy < 0){ 
            dy = -dy; 
            stepy = -1; 
        } 
        else{
            stepy = 1;
        }
        if (dx < 0){  
            dx = -dx;  
            stepx = -1; 
        } 
        else{
            stepx = 1;
        }
        xp = x0;
        yp = y0;
        g.drawLine(x0, y0, x0, y0);
        //Se hace un ciclo hasta llegar al extremo de la línea
        if (dx > dy){
            p = 2*dy - dx;
            incE = 2*dy;
            incNE = 2*(dy-dx);
            while (xp != x1){
                xp = xp + stepx;
                if (p < 0){
                    p = p + incE;
                }
                else{
                    yp = yp + stepy;
                    p = p + incNE;
                }
            g.drawLine(xp, yp, xp, yp);
            }
        }
        else{
            p = 2*dx - dy;
            incE = 2*dx;
            incNE = 2*(dx-dy);
            while (yp != y1){
                yp = yp + stepy;
                if (p < 0){
                    p = p + incE;
                }
                else{
                    xp = xp + stepx;
                    p = p + incNE;
                }
            g.drawLine(xp, yp, xp, yp);
            }
        }
    }
    
    public static void main(String[] args) {
        //Crea un nuevo Frame
        JFrame frame = new JFrame("Reto1 Alejandra Pérez");
        //Al cerrar el frame, termina la ejecucion de este programa
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Agrega el JPanel de Puntos(mirar Puntos.java)
        frame.add(new LineasAlejandraPerez());
        //Asigna el tamaño del frame
        frame.setSize(400, 400);
        //Pone el frame en el centro de la pantalla cuando se ejecuta
        frame.setLocationRelativeTo(null);
        //Muesta el frame -->true
        frame.setVisible(true);

        //Este es el mismo n que hay en Puntos.java (n es la entrada de puntos a dibujar)
        int n = 15;
        int grados = 360;
        int result = grados/n;
        System.out.println("El numero de grados a moverse es: " + result);
    }  
}