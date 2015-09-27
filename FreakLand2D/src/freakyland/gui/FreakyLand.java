package freakyland.gui;

import freakyland.Player;
import freakyland.Scene;
import freakyland.Phantom;
import java.awt.AlphaComposite;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

public class FreakyLand extends javax.swing.JPanel implements java.awt.event.KeyListener, java.awt.event.ActionListener {
    
    private Player player;
    private Phantom phantomTrap;
    private Phantom phantom;
    private Scene scene;
    private ArrayList<Phantom> phantoms;
    private ArrayList<JLabel> phantomsImg;
    private JLabel playerImg;
    private JLabel sceneImg;
    private JLabel phantomImg;
    private JLabel scoreBoard;
    private int playerHitBoxX = 100;
    private int playerHitBoxY = 100;
    
    private static final int WIDTH = 1000; //WIDTH of the panel.
    private static final int HEIGHT = 700; //HEIGHT of the panel.
    private static final int TIMETOREAD = 2500;
    
    private ImageIcon backgr;
    private int score;
    
    public FreakyLand(){
        initComponents();
    }
    
    private void initComponents(){
        //JPanel configs and stuff
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(new java.awt.Color(255, 255, 255)); //White screen, why not.
        setFocusable(true);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        //Code n' stuff 
        scene = new Scene();
        
        scene.setImg(scene.randomizeScenario());
        
        player = new Player();
        player.setHitBoxX(playerHitBoxX); //SET HITBOX
        player.setHitBoxY(playerHitBoxY); //SET HITBOX
        playerImg = new JLabel();
        playerImg.setIcon(new javax.swing.ImageIcon(getClass().getResource(
        player.getImgIcon())));
        playerImg.setPreferredSize(new Dimension(150, 150));
        player.setPosX(WIDTH/2);
        player.setPosY(HEIGHT - 200);
        playerImg.setLocation(player.getPosX(), player.getPosY());
        
        phantoms = new ArrayList<>();
        phantomsImg = new ArrayList<>();
        
        scoreBoard = new JLabel();
        scoreBoard.setFont(new Font("Serif", Font.PLAIN, 25));
        scoreBoard.setText(Integer.toString(scene.getScore()));
        
       add(playerImg, new AbsoluteConstraints(WIDTH/2, HEIGHT-200, -1, -1));
       add(scoreBoard, new AbsoluteConstraints(50, 50, -1, -1));
       backgr = new javax.swing.ImageIcon(getClass().getResource(
            scene.getImg()));
       score = 0;
    }
        
    
    
    public static void main(String args[]) {
        FreakyLand panel = new FreakyLand();
        
        javax.swing.JFrame frame = new JFrame();
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.setFocusable(true);
        frame.addKeyListener(panel);
        frame.setResizable(false);
        frame.setVisible(true);     
        panel.startGame();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int click = e.getKeyCode();
        if(KeyEvent.VK_LEFT == click||KeyEvent.VK_A == click){
            player.moveLeft();
        } else if (KeyEvent.VK_RIGHT == click ||KeyEvent.VK_D == click ){
            player.moveRight();
        }
        playerImg.setLocation(player.getPosX(), player.getPosY());
    }

    @Override
    public void keyReleased(KeyEvent e) {
 
    }
    
    int check;
    Random rn = new Random();
    
    @Override
    public void paintComponent(Graphics g) {
       
       super.paintComponent(g); 
       g.drawImage(backgr.getImage(), 0, 0, null);
       
       check = Math.abs(rn.nextInt())%700;
       
       if(check==0){
            spawnPhantom();
       }
       
       //Check for colision
       int playerPosX = player.getPosX();
       int playerPosY = player.getPosY();
       int phantomPosX, phantomPosY;
       for(int i = 0; i<phantomsImg.size();i++){
           phantomPosX = phantoms.get(i).getPosX();
           phantomPosY = phantoms.get(i).getPosY();
           if(Math.abs(playerPosX - phantomPosX)<=player.getHitBoxX()
                   && Math.abs(playerPosY - phantomPosY)<=player.getHitBoxY()){
               
               final JLabel phantomTemp = phantomsImg.get(i);
               if(phantoms.get(i).getType()==phantomTrap.getType()){
                   try {
                       //GG
                       
                       scene.addPoint();
                       scoreBoard.setText(Integer.toString(scene.getScore()));
                       //TODO: INCREASE SIZE OF GHOST
                       //phantomsImg.get(i).setSize(phantomTrap.getSizeX()*2, phantomTrap.getSizeY()*2);
                       BufferedImage myPicture = ImageIO.read(getClass().getResource(phantoms.get(i).getImg()));
                       
                       phantomsImg.get(i).setIcon(new ImageIcon(resizeToBig(myPicture, 400, 400)));
                       //phantomsImg.get(i).setLocation(WIDTH/2, HEIGHT/2);

                       //validate();
                       //repaint();
                   } catch (IOException ex) {
                       Logger.getLogger(FreakyLand.class.getName()).log(Level.SEVERE, null, ex);
                   }
               } else {
                   //LOL N00B
                   
                   scene.deductPoint();
                   scoreBoard.setText(Integer.toString(scene.getScore()));
                   //TODO: DECREASE SIZE OF GHOST
                   /*BufferedImage myPicture;
                   try {
                       myPicture = ImageIO.read(getClass().getResource(phantoms.get(i).getImg()));
                       phantomsImg.get(i).setIcon(new ImageIcon(resizeToBig(myPicture, 50, 50)));
                   } catch (IOException ex) {
                       Logger.getLogger(FreakyLand.class.getName()).log(Level.SEVERE, null, ex);
                   }
                     */  
               }
               
               new java.util.Timer().schedule( 
                    new java.util.TimerTask() {
                    @Override
                    public void run() {
                        // your code here
                        remove(phantomTemp);
                    }
                }, 
                500 
                );
               phantoms.remove(i);
               phantomsImg.remove(i);
               validate();
               repaint();
           }
           playerImg.setLocation(player.getPosX(), player.getPosY());
       }
       
       movePhantoms();
       repaint();
        
        
    }
    
    private Image resizeToBig(Image originalImage, int biggerWidth, int biggerHeight) {
    int type = BufferedImage.TYPE_INT_ARGB;

BufferedImage resizedImage = new BufferedImage(biggerWidth, biggerHeight, type);
	Graphics2D g = resizedImage.createGraphics();
	g.drawImage(originalImage, 0, 0, biggerWidth,  biggerHeight, null);
	g.dispose();	
	g.setComposite(AlphaComposite.Src);
	g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	g.setRenderingHint(RenderingHints.KEY_RENDERING,
	RenderingHints.VALUE_RENDER_QUALITY);
	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	RenderingHints.VALUE_ANTIALIAS_ON);
	return resizedImage;
}
    
    public void startGame(){
        showMessage();
    }
    
    private void showMessage(){ 
        phantomTrap =  new Phantom();
        String imgPath = phantomTrap.randomizePhantom();
        phantomTrap.setImgIcon(imgPath);
        Instructions ins = new Instructions(new javax.swing.ImageIcon(getClass()
                .getResource(phantomTrap.getImgIcon())));
        JFrame frame = (JFrame)this.getParent().getParent().getParent();
        frame.add(ins,new org.netbeans.lib.awtextra.AbsoluteConstraints(WIDTH/2- 175, HEIGHT/2 - 200, -1, -1));
        ins.setVisible(true);
        repaint();
        try{
            Thread.sleep(TIMETOREAD);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        frame.remove(ins);
        repaint();
    }
    

    
    private void spawnPhantom(){
        phantom = new Phantom();
        phantom.setPosX(phantom.randomizePosX(WIDTH));
        phantom.setPosY(-100);
        
        phantomImg = new JLabel();
        phantomImg.setIcon(new ImageIcon(getClass().getResource(
        phantom.randomizePhantom())));
        
        add(phantomImg,new AbsoluteConstraints(phantom.getPosX(), phantom.getPosY(), -1, -1));
        
        phantoms.add(phantom);
        phantomsImg.add(phantomImg);
        validate();
        playerImg.setLocation(player.getPosX(), player.getPosY());
        repaint();
    }
    
    int i = 0;
    private void movePhantoms(){
        
        if(i%5==0){
            for(int j = 0; j<phantomsImg.size();j++){
                
                phantoms.get(j).fall();
               if(i % 800 == 0){
                    phantoms.get(j).setDegrees((phantoms.get(j).getDegrees()+ 45)% 360);
                    
                int rot = phantoms.get(j).getDegrees();
                try {
                    BufferedImage myPicture = ImageIO.read(getClass().getResource(phantoms.get(j).getImg()));
                    phantomsImg.get(j).setIcon(new ImageIcon(rotate(myPicture,rot)));
                } catch (IOException ex) {
                    Logger.getLogger(FreakyLand.class.getName()).log(Level.SEVERE, null, ex);
                }

               }
                phantomsImg.get(j).setLocation(phantoms.get(j).getPosX(), phantoms.get(j).getPosY());
                if(phantomsImg.get(j).getY() >= HEIGHT){
                    this.remove(phantomsImg.get(j));
                    phantomsImg.remove(j);
                    phantoms.remove(j);
                }
                //validate();
                
            }
        }
        i=i+1;
    }
    
    private BufferedImage rotate(BufferedImage in, int degrees){
        BufferedImage bufferedImage;
        AffineTransform tx = new AffineTransform();
        tx.rotate(Math.toRadians(degrees), in.getWidth(), in.getHeight());
        AffineTransformOp op = new AffineTransformOp(tx,
        AffineTransformOp.TYPE_BILINEAR);
        bufferedImage = op.filter(in, null);
        return bufferedImage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
