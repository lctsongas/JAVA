package Applet;

import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class Graph{

	int WindowWidth;
	int WindowHeight;
	
	double Xmin;
	double Xmax;
	double Ymin;
	double Ymax;
	double increment;
	double Xincrement;
	double Yincrement;
	
	Axis xAxis;
	Axis yAxis;
	Point Origin;
	Line Equation;
	ArrayList<Node> xNodes;
	ArrayList<Node> yNodes;
	HashMap<Integer, Color> colors;
	
	
	class Axis {
	    
	    private double MIN;
	    private double MAX;
	    	void setDomain(double min, double max)
	    		{ MIN = min;	MAX = max; }
	    		
	    private int LENGTH;
	    private int Position;
	    
	    Axis(int Length, double MinVal, double MaxVal)
	    { 	
	    	MIN = MinVal;
	    	MAX = MaxVal;
	    	LENGTH = Length;
	    	Position = LENGTH / 2;
	    	
	    }
	    
	    int getLength()
	    {
	    	return LENGTH;	    	
	    }
	    
	    int getPosition()
	    {
	    	return Position;
	    }
	    
	    double LowestValue()
	    {
	    	return MIN;
	    }
	    
	    double HighestValue()
	    {
	    	return MAX;
	    }

	}
	
	
	public Graph()
	{
		WindowWidth = Main.WIDTH;
		WindowHeight = Main.HEIGHT;
		
		Xmin = -2.0;
		Xmax = 2.0;
		
		Ymin = -2.0;
		Ymax = 2.0;
		
		setUpIncrements();
		
		xAxis = new Axis(WindowHeight, Xmin, Xmax);
		xNodes = new ArrayList<Node>();
		
		yAxis = new Axis(WindowWidth, Ymin, Ymax);
		yNodes = new ArrayList<Node>();
		
		findOrigin();
		colors = new HashMap<Integer,Color>();
	
	}
	
	private void setUpIncrements()
	{
		Xincrement = (Xmax - Xmin) / WindowWidth;
		Yincrement = (Ymax - Ymin) / WindowHeight;
	}
	
	public void LoadEquation(Line equation)
	{
		Equation = equation;
	}
	
	public void ZoomIn(double Domain, double Range, Point ZoomOrigin)
	{
		Complex ZoomValue = GetValueOf(ZoomOrigin);
		
		Domain /= 2;
		Range /= 2;
		
		Xmin = -Domain/2 + ZoomValue.real();
		Xmax = Domain/2 + ZoomValue.real();
		
		Ymin = -Range/2 + ZoomValue.imag();
		Ymax = Range/2 + ZoomValue.imag();
		
		setUpIncrements();
	}
	
	private void findOrigin()
	{
		Origin = new Point(yAxis.getLength()/2, xAxis.getLength()/2);
	}
	
	private Complex GetValueOf(Point pixel)
	{	
		double x = (pixel.x - Origin.x) * Xincrement;
		double y = (pixel.y - Origin.y) * Yincrement;
		
		Complex values = new Complex(x,y);
		
		return values;
	}
	
	public void Draw(Graphics g)
	{
		
		for(int y = 0; y < WindowHeight; y++)
		{
		for(int x = 0; x < WindowWidth; x++)
		{
				Point pixel = new Point(x, y);
				Complex PixelValues = GetValueOf(pixel);
				//calculate with pixelvalues
				//System.out.println("Pixel: " + pixel + "\nValue: " + PixelValues);//DEBUG
				
				
				int passes = Equation.Calculate(new Complex(0,0), 
						PixelValues, 0);
				//System.out.println("Values: " + PixelValues + "\nPasses: " + passes);
				g.setColor(findColor(passes));
				g.drawRect(pixel.x, pixel.y, 1, 1);
		}
			
		}
		
		g.setColor(Color.white);
		//draw x-axis
				//g.drawLine(0, xAxis.getPosition(), yAxis.getLength(), xAxis.getPosition());
				
				//draw y-axis
				//g.drawLine(yAxis.getPosition(), 0, yAxis.getPosition(), yAxis.getLength());
	}
	
	private Color findColor(int passes)
	{
		if(passes == 300)
			return Color.black;
		
		if(colors.containsKey(passes))
		{
			return colors.get(passes);
		}
		else
		{
			Random rand = new Random();
			int r = rand.nextInt(255);
			int g = rand.nextInt(255);
			int b = rand.nextInt(255);
			
			colors.put(passes, new Color(r,g,b));
			
			return colors.get(passes);
			
		}
			
		
	}
}
