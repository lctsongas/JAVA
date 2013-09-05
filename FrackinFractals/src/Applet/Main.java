package Applet;

import java.applet.*; 
//required to paint on screen 
import java.awt.*; 
import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


//the start of an applet - HelloWorld will be the executable class 
//Extends applet means that you will build the code on the standard Applet class 
public class Main extends Applet implements MouseListener 
{
	private Graph graph;
	public static int WIDTH;
	public static int HEIGHT;
	
	Line line;

//The method that will be automatically called  when the applet is started 
  public void init() 
  { 
	  
	  addMouseListener(this);
	  setVisible(false);
	  setSize(Toolkit.getDefaultToolkit().getScreenSize().width,
			  Toolkit.getDefaultToolkit().getScreenSize().height);
	  WIDTH = this.getWidth();
	  HEIGHT = this.getHeight();
	  
	  try
	  {

	  
	  
	  
	  line = new Line();
	  
	  //System.out.println("(-1.5, 1) = "  + line.Calculate(new Complex(0,0), new Complex(-1.5, 1), 1));
	  
	  }
	  catch(Exception e)
	  {
		  System.out.println("Input mismatch error!");//catch errors better later
	  }
	  
	  graph = new Graph();
	  
	  graph.LoadEquation(line);
	  
	  setVisible(true);
  } 
	  


//This method gets called when the applet is terminated 
//That's when the user goes to another page or exits the browser. 
  public void stop() 
  { 
  // no actions needed here now. 
  } 


//The standard method that you have to use to paint things on screen 
//This overrides the empty Applet method, you can't called it "display" for example.

  public void paint(Graphics g) 
  { 
//method to draw text on screen 
// String first, then x and y coordinate. 
   graph.Draw(g);

  }
  
  public void mousePressed(MouseEvent e) { }

   public void mouseReleased(MouseEvent e) { }

   public void mouseEntered(MouseEvent e) { }

   public void mouseExited(MouseEvent e) { }

   public void mouseClicked(MouseEvent e)
   {
	  if(e.getButton() == e.BUTTON1)
	  {
		  //zoom in
		  double Domain = (graph.Xmax - graph.Xmin);
		  double Range = (graph.Ymax - graph.Ymin);
		  
		  graph.ZoomIn(Domain, Range, e.getLocationOnScreen());
		  setSize(WIDTH,HEIGHT);
		  System.out.println("Left Mouse");
	  }
	  if(e.getButton() == e.BUTTON2)
	  {
		  System.out.println("Right Mouse");
	  }
	  
      Point p = e.getLocationOnScreen();
      
      
   }

  

} 
