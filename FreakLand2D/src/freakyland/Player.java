

package freakyland;

/**
 *
 * @author JuanManuel
 */
public class Player extends Figura{
    
    
    private static final int change = 10;
    
    public Player(){
        super();
        hitBoxX = 100;
        hitBoxY = 120;
        sizeX = 150;
        sizeY = 150;
        imgIcon = "/res/img/PacAnimation.gif";
    } 
    public void moveLeft(){
        if(posX > 0){
            posX = posX - change;
        }
    }
    
    public void moveRight(){
        if(posX < 850){
            posX = posX + change;
        }
    }
    
    public void setHitBoxX(int width){
        hitBoxX = width;
    }
    
    public void setHitBoxY(int height){
        hitBoxY = height;
    }
    
    public int getHitBoxX(){
        return hitBoxX;
    }
    
    public int getHitBoxY(){
        return hitBoxY;
    }
}
