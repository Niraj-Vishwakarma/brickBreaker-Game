package brickBreaker;

import javax.swing.JFrame;

public class main {

	public static void main(String[] args) {
		
		
		JFrame obj = new JFrame();
		  // Declare the object of Gamplay Class
		Gamplay gameplay = new Gamplay();
		obj.add(gameplay); 
		
		obj.setBounds(10, 10, 700, 600);
		obj.setTitle("Breakout_Ball");
		obj.setResizable(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.setVisible(true);
		
		          // Add object gameplay to obj. it gives error "The method add(Component) in the type Container is not applicable for the 
		                            // arguments (Gamplay) and it solved when we include extends JPanel in Gamplay
	}
	
}
