
package freakyland;

import java.util.Random;

public class Phantom extends Figura {
    
    private String img;
    private int phantom;
    
    private static final int change = 1;
    private static final int NUMBEROFPHANTOMS = 9;
    
    public Phantom(){
        super();
        sizeX = 100;
        sizeY = 100;
    }
    
    public String randomizePhantom(){
        
        Random rn = new Random();
        phantom = Math.abs(rn.nextInt())%NUMBEROFPHANTOMS;
        
        switch(phantom){
            case 0: //DEADPOOL, THE BOSS!
                img = "/res/img/Deadpool.png";
                return img;
            case 1: //D'OH!
                img = "/res/img/Homero.png";
                return img;
            case 2: //Pika... CHUUUUUUUUUUUUUUUUUUUUUU!!!
                img = "/res/img/Pikachu.png";
                return img;
            case 3: //theGuyWhoLooksLikeJohnnyBravo
                img = "/res/img/Duke.png";
                return img;
            case 4: //Ragnarök!!...Fail
                img = "/res/img/Hellboy.png";
                return img;
            case 5: //BLACK NOPE!
                img = "/res/img/Spawn.png";
                return img;
            case 6:
                img = "/res/img/Bomberman.png";
                return img;
            case 7:
                img = "/res/img/Joker.png";
                return img; 
            case 8:
                img = "/res/img/Magneto.png";
                return img;
            default://BUG LEL
                return "";
        }
        
    }
    
    public void fall(){
        posY = posY + change;
    }
    
    public int randomizePosX(int maxWidth){
        Random rn = new Random();
        int res = Math.abs(rn.nextInt()) % (maxWidth-100); //100 = Diameter
        return res;
    }
    
    public String getImg(){
        return img;
    }
    
    public int getType(){
        return phantom;
    }
}
