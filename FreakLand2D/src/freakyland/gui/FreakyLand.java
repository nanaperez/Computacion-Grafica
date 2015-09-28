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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

public class FreakyLand extends javax.swing.JPanel implements java.awt.event.KeyListener {
    private Player player;
    private Phantom phantomTrap;
    private Phantom phantom;
    private Scene scene;
    // Array to manage phantoms in the screen as value objects
    private ArrayList<Phantom> phantoms;
    // Array to manage phantoms in the screen as JLabels
    private ArrayList<JLabel> phantomsImg;
    private JLabel playerImg;
    private JLabel sceneImg;
    private JLabel phantomImg;
    private JLabel scoreBoard;
    private final int playerHitBoxX = 100;
    private final int playerHitBoxY = 100;
    private static final int WIDTH = 1000; //WIDTH of the panel.
    private static final int HEIGHT = 700; //HEIGHT of the panel.
    private static final int TIMETOREAD = 2500;
    private ImageIcon backgr;
    public FreakyLand(){
        initComponents();
    }
    private void initComponents(){
        //JPanel configs and stuff
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(new java.awt.Color(255, 255, 255)); //White screen, why not.
        // Layout to put objects in whatever part
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        //the scene
        scene = new Scene();
        scene.setImg(scene.randomizeScenario());
        player = new Player();
        // Config to know if pacman hit the phantom
        player.setHitBoxX(playerHitBoxX); //SET HITBOX
        player.setHitBoxY(playerHitBoxY); //SET HITBOX
        playerImg = new JLabel();
        //upload pacman image
        playerImg.setIcon(new javax.swing.ImageIcon(getClass().getResource(
        player.getImgIcon())));
        playerImg.setPreferredSize(new Dimension(150, 150));
        //initial Position for Pacman
        player.setPosX(WIDTH/2);
        player.setPosY(HEIGHT - 200);
        playerImg.setLocation(player.getPosX(), player.getPosY());
        phantoms = new ArrayList<>();
        phantomsImg = new ArrayList<>();
        // show the score
        scoreBoard = new JLabel();
        scoreBoard.setFont(new Font("Serif", Font.PLAIN, 25));
        scoreBoard.setText(Integer.toString(scene.getScore()));
        //add Pacman to JPanel
       add(playerImg, new AbsoluteConstraints(WIDTH/2, HEIGHT-200, -1, -1));
       //add score to JPanel
       add(scoreBoard, new AbsoluteConstraints(50, 50, -1, -1));
       // upload the scene background
       backgr = new javax.swing.ImageIcon(getClass().getResource(
            scene.getImg()));
      
    }
    public static void main(String args[]) {
        FreakyLand panel = new FreakyLand();
        JFrame frame = new JFrame("Freakyland: Old School");
        // Dimensions for the frame
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        //To listen events
        frame.setFocusable(true);
        frame.addKeyListener(panel);
        frame.setResizable(false);
        frame.setVisible(true);
        panel.startGame();
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // Not necessary to do something here
    }
    /**
     * Method to manage key pressed
     * @param e KeyEvent   
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int click = e.getKeyCode();
        // if key is left arrow or a
        if(KeyEvent.VK_LEFT == click||KeyEvent.VK_A == click){
            player.moveLeft();
            // if key is right arrow or d
        } else if (KeyEvent.VK_RIGHT == click ||KeyEvent.VK_D == click ){
            player.moveRight();
        }
        playerImg.setLocation(player.getPosX(), player.getPosY());
    }
    @Override
    public void keyReleased(KeyEvent e) {
        //Not necessary to do
    }
    int check;
    Random rn = new Random(System.currentTimeMillis());
    @Override
    public void paintComponent(Graphics g) {
       super.paintComponent(g); 
       g.drawImage(backgr.getImage(), 0, 0, null);
       // Trick to control phatoms spawn wit out interruptions
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
                   && Math.abs(playerPosY - phantomPosY)<=player.getHitBoxY() - 50){
               final JLabel phantomTemp = phantomsImg.get(i);
               if(phantoms.get(i).getType()==phantomTrap.getType()){
                   try {
                       // Add points to score
                       scene.addPoint();
                       scoreBoard.setText(Integer.toString(scene.getScore()));
                       // Read again phantom image to resize
                       BufferedImage myPicture = ImageIO.read(getClass().getResource(phantoms.get(i).getImg()));
                       // set Icon with a bigger image 
                       phantomsImg.get(i).setIcon(new ImageIcon(resizeToBig(myPicture, 400, 400)));
                   } catch (IOException ex) {
                       Logger.getLogger(FreakyLand.class.getName()).log(Level.SEVERE, null, ex);
                   }
               } else {
                   //LOL N00B
                   // when you fail
                   scene.deductPoint();
                   if(scene.getScore() < -30){
                       showLostMessage();
                   }
                   scoreBoard.setText(Integer.toString(scene.getScore()));  
               }
               
               new java.util.Timer().schedule( 
                    new java.util.TimerTask() {
                    @Override
                    public void run() {
                        // remove phantom after Pacman hit it
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
    /**
     * Method to resize an image
     * @param originalImage
     * @param biggerWidth
     * @param biggerHeight
     * @return image with the specified width and height
     */
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
    /**
     * Method to show the message with the information about the phantom to "eat" with pacman
     */
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
    /**
     * Method to show lost message and deactivate keyListener
     */
    private void showLostMessage(){
        Lost ins = new Lost();
        JFrame frame = (JFrame)this.getParent().getParent().getParent();
        frame.add(ins,new org.netbeans.lib.awtextra.AbsoluteConstraints(WIDTH/2- 175, HEIGHT/2 - 200, -1, -1));
        ins.setVisible(true);
        frame.removeKeyListener(this);
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
                if(phantoms.get(j).getPosY() >= HEIGHT){
                    this.remove(phantomsImg.get(j));
                    phantomsImg.remove(j);
                    phantoms.remove(j);
                }
            }
        }
        i=i+1;
    }
    /**
     * Method to rotate images
     * @param in
     * @param degrees
     * @return BufferedImage with the rotated image
     */
    private BufferedImage rotate(BufferedImage in, int degrees){
        BufferedImage bufferedImage;
        AffineTransform tx = new AffineTransform();
        tx.rotate(Math.toRadians(degrees), in.getWidth(), in.getHeight());
        AffineTransformOp op = new AffineTransformOp(tx,
        AffineTransformOp.TYPE_BILINEAR);
        bufferedImage = op.filter(in, null);
        return bufferedImage;
    }
}