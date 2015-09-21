
package freakyland;

import java.util.Random;

public class Scene extends Figura{
    
    private String img;
    private int scenario;
    private int score;
    
    private static final int NUMBEROFSCENARIOS = 1;
    
    public Scene(){
        
    }
    
    public String randomizeScenario(){
        
        Random rn = new Random();
        scenario = rn.nextInt()%NUMBEROFSCENARIOS;
        
        switch(scenario){
            case 0:        
                return "/res/img/scene/scene0.jpg";
            default:
                return "";
        }
    }
    
    public void setImg(String scene){
        img = scene;
    }
    
    public String getImg() {
        return img;
    }
    
    public void addPoint(){
        score += 10;
    }
    
    public void deductPoint(){
        score -= 5;
    }
    
    public int getScore(){
        return score;
    }
}
