package Applet;

import java.awt.*;

public class Node 
{
	
	private Point position;
	private double Value = 0.0;
	
	public Node(Point pos, double val)
	{
		Value = val;
		position = pos;
		System.out.println("NODE_CREATED Value = " + val + " | Position = " + pos.toString());
	}
	
	public double getValue()
	{
		return Value;
	}
	
	
	
}
