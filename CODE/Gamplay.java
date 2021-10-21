package brickBreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;



public class Gamplay extends JPanel implements KeyListener, ActionListener // Keylistener - slider & ActionListener - Ball
{
	private boolean play = true ;  // game will not play itself
    private int score = 0;
    private int totalBricks = 32;
    
    private Timer timer;   // Timer class to set ball speed
    private int delay = 4;  //speed
    
    private int playerX = 300;// starting position of slider
  
    private int ballposX= 120;
    private int ballposY= 350;
    private int ballXdir = -2;
    private int ballYdir = -3;
    private MapGenerator map;    
    
    JLabel label = new JLabel();

    
    public Gamplay ()             // constructor
    {
    	
    	//map 
    	map= new MapGenerator(4,8);
    	//KeyListener keyListener = new this();
//    	KeyListener listener = new Gamplay();
    	this.addKeyListener(this);
    	this.requestFocusInWindow();
    	this.setFocusable(true);
    	this.setFocusTraversalKeysEnabled(false);
    	  
    	this.add(label);
    	
    //	icon= new Image("om.png");
//   	addKeyListener(this);
//   	setFocusable(true);
  	
    
//    	setFocusable(true);
//    	requestFocus(); 
    	
    	  //  this.setFocusable(true);
//    
    	
   	timer = new Timer(delay, this);               // got error because importef wrong package correct is "import javax.swing.Timer;
   	timer.start(); 	
  }
    
   

	public void paint(Graphics g)  // use to draw graphics
    {
    	//Background
    	g.setColor(Color.black);
    	g.fillRect(1, 1, 692, 592);
    	
    	
    	//map
    	map.draw((Graphics2D) g);
    	
    	//border
    	g.setColor(Color.yellow);
    	g.fillRect(0, 0, 3, 592);
    	g.fillRect(0, 0, 692, 3);
    	g.fillRect(680 , 0, 3, 592);
    	
    	//score
    	g.setColor(Color.white);
    	g.setFont(new Font("serif", Font.BOLD, 25));
    	g.drawString(""+ score , 590, 30);
    	
    	//paddle
    	g.setColor(Color.yellow);
    	g.fillRect(playerX, 550, 25, 8);
    	g.setColor(Color.green);
    	g.fillRect(playerX + 25, 550, 50, 8);
    	g.setColor(Color.yellow);
    	g.fillRect(playerX + 75, 550, 25, 8);
    	
    	//ball
    	g.setColor(Color.yellow);
    	g.fillOval(ballposX, ballposY, 20, 20);
        
    	
    	label.setBounds(ballposX, ballposY, 20, 20);
    
    	URL url = main.class.getResource("KARMA LOGO SYMBOL.png");
   	    ImageIcon image = new ImageIcon(url);   	
    	label.setIcon(image);
    	
    	//if completed Win the Game 
    	if(totalBricks <=0) {
    		play= false;
    		ballXdir = 0;
    		ballYdir = 0;
    		
    		g.setColor(Color.red);
    		g.setFont(new Font("serif", Font.BOLD, 30));
        	g.drawString("GAME WIN, Scores :  "+ score , 190, 250);
        	
        	g.setFont(new Font("serif", Font.BOLD, 20));
        	g.drawString("PRESS ENTER TO RESTART " , 230, 300);
    		
    	}
    	
    	// Game over
    	if(ballposY >570) {
    		play= false;
    		ballXdir = 0;
    		ballYdir = 0;
    		
    		g.setColor(Color.red);
    		g.setFont(new Font("serif", Font.BOLD, 30));
        	g.drawString("GAME OVER, Scores :  "+ score , 190, 250);
        	
        	g.setFont(new Font("serif", Font.BOLD, 20));
        	g.drawString("PRESS ENTER TO RESTART " , 230, 300);
    	}
    	
    	g.dispose();
    	
    	
    	

    }
    
    
    
    




	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(play) 
		{
			//intersection of ball and green paddle
			if(new Rectangle(ballposX, ballposY, 20,20).intersects(new Rectangle(playerX,550,25,8)) && new Rectangle(ballposX, ballposY, 20,20).intersects(new Rectangle(playerX +25,550,50,8)))
			{
				ballYdir = -ballYdir;
			}
			if(new Rectangle(ballposX, ballposY, 20,20).intersects(new Rectangle(playerX +75,550,25,8)) && new Rectangle(ballposX, ballposY, 20,20).intersects(new Rectangle(playerX +25,550,50,8)))
			{
				ballYdir = -ballYdir;
			}
			if(new Rectangle(ballposX, ballposY, 20,20).intersects(new Rectangle(playerX,550,25,8)))
			{
				ballYdir = -ballYdir;
				ballXdir = -ballXdir;
				System.out.println("touched1");
				
			}
			if(new Rectangle(ballposX, ballposY, 20,20).intersects(new Rectangle(playerX +25,550,50,8)))
			{
				ballYdir = -ballYdir;
				
				
				System.out.println("touched2");
			}
			
			
			if(new Rectangle(ballposX, ballposY, 20,20).intersects(new Rectangle(playerX +75,550,25,8)))
			{
				ballYdir = -ballYdir;
				ballXdir = -ballXdir;
				System.out.println("touched3");
				
				
			}
			
			
			//intersection of ball and brick
			A : for(int i=0; i<map.map.length; i++) {                   //map object access the map array in the class mapGenerator therefore map.map
				for(int j=0; j< map.map[0].length; j++) {
					if(map.map[i][j] > 0) {
						int brickX = j*map.brickWidth + 80;
						int brickY = i*map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20,20);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect)) {
							map.setBrickValue(0, i, j);
							totalBricks--;
							score += 5;
							
							if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.width) {
								ballXdir = -ballXdir;
							} else {
								ballYdir = -ballYdir ;
							}
							break A;
						}
						
					}
				}
			}
			
			//motion of ball
			ballposX += ballXdir;
			ballposY += ballYdir;
			if(ballposX <0)
			{
				ballXdir = -ballXdir;
			}
			if(ballposY < 0)
			{
				ballYdir = -ballYdir;
			}
			if(ballposX > 670)
			{
				ballXdir = -ballXdir;
			}
		}
		
		repaint();             // call paint after changes has been made
		
	}

	@Override
	public void keyTyped(KeyEvent e) { }       // Don't need  but if removed give error
	@Override
	public void keyReleased(KeyEvent e) {}    // Don't need but if removed give error


	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()== KeyEvent.VK_RIGHT)
		{
			if(playerX >=600)
			{
				playerX = 600;
			} else {  moveRight(); }
		}
		
		if(e.getKeyCode()== KeyEvent.VK_LEFT)
		{
			if(playerX < 10)
			{
				playerX = 10;
			} else {  moveLeft(); }
		}
	if (e.getKeyCode()== KeyEvent.VK_ENTER)	{          // to restart the game
		if(!play) {
			play= true;
			ballposX =120;
			ballposY =350;
			ballXdir = -1;
			ballYdir = -2;
			playerX = 310;
			score = 0;
			totalBricks = 21;
			map = new MapGenerator(3,7);
			repaint();
			
		}
	}
		
	}
	
	public void moveRight()    // paddle move right by 20
	{
		play = true;
		playerX+=20; 
	}
	public void moveLeft()     // paddle move left by 20
	{
		play = true;
		playerX-=20; 
	}

	
}
