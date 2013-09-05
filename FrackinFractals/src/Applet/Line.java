package Applet;

import java.awt.*;
import java.awt.geom.*;

public class Line 
{
	double[] yCoord;
	double[] xCoord;
	double M;
	double C;
	double B;
	//yCoord[i] = (xCoord[i]*M - C) + B
	//
	
	
	public Line(double slope, double XTranslationFromOrigin,  double YTranslationFromOrigin)
	{
		M = slope;
		C = XTranslationFromOrigin;
		B = YTranslationFromOrigin;
		
	}
	
	public Line()
	{
		
	}
	
	public Line(Point2D.Double p1, Point2D.Double p2)
	{
		//implement later
		
	}
	
	public int Calculate(Complex Z, Complex C, int pass)
	{
		double ABSValue = Math.pow(Z.real(), 2) + Math.pow(Z.imag(), 2);
		//ABSValue = Math.pow(ABSValue, 2);//Test
		//System.out.println(C + "\n\tABS: " + ABSValue + "\n\tPass: " + pass +"\n");
		//System.out.println("iteration: " + pass + "\n\tZ = " + Z.toString() + "\n\tC = " + C.toString());//DEBUG
		if(ABSValue >= 4.0 || pass == 300)
		{
			return pass;
		}
		else
		{
			pass++;
			Complex newZ = new Complex(Math.pow(Z.real(), 2) - Math.pow(Z.imag(), 2), 
					2 * Z.real() * Z.imag());
			Complex returnedZ = newZ.plus(C);
			
			return Calculate(returnedZ, C, pass);
		}
		
		/*int Size = (int)(Math.abs((maxX-minX) / increment));
		xCoord = new double[Size];
		yCoord = new double[Size];
		int index = 0;
		double currentValue = minX;
		
		while(index < Size)
		{
			//add equations here
			//yCoord[index] = (currentValue*M - C) + B;
			yCoord[index] = (Math.pow(currentValue, 2) * M) + (currentValue * C) + B;
			xCoord[index] = currentValue;
			//DEBUG System.out.println("X: "+xCoord[index] + ",\nY: "+ yCoord[index]);
			currentValue+=increment;
			index++;
			//need to center graphing around center point.
		}
		
		return this;
		*/
	}
	
	public Point2D.Double[] getData()
	{
		Point2D.Double[] points = new Point2D.Double[xCoord.length];
		for(int i = 0; i < xCoord.length; i++)
		{
			points[i] = new Point2D.Double(xCoord[i], yCoord[i]);
		}

		return points;
	}
	
	public void Draw(Graphics g, Point Origin, double Ratio)
	{
		g.setColor(Color.RED);
		
		if(Ratio < 1.0) 
			Ratio = 1.0;
		
		int X[] = new int[xCoord.length];
		int Y[] = new int[yCoord.length];
		
		for(int i = 0; i < xCoord.length; i++)
		{
			X[i] = (int)xCoord[i] + Origin.x;
			Y[i] = (int)yCoord[i] + Origin.y;
		}
		
		g.drawPolygon(X, Y, xCoord.length);
	}
		
	}

