import java.awt.Graphics;

import java.awt.Graphics2D;

import java.awt.Color;

import java.awt.Rectangle;
import java.awt.*;

import java.util.*;

public class Line{

	private int xPosition1;

	private int yPosition1;

	private int xPosition2;

	private int yPosition2;

	private int height;

	private int xPosFinal;

	private int yPosFinal;

	public Line(int xPosition,int yPosition)
	{
		this.xPosition1 = xPosition;
		this.xPosition2 = xPosition;
		this.yPosition1 = yPosition;
		this.yPosition2 = yPosition;
		xPosFinal = xPosition;
	}

	public void setFinal()
	{
		yPosFinal = yPosition2;
		height = yPosition1 - yPosition2;
	}

	public void refractor(int angle)
	{
		double radians = Math.toRadians(angle);
		int xChange = (int) (height*Math.sin(radians));
		int yChange = (int) (height*Math.cos(radians));
		System.out.println(yChange+"     " + xChange);
		xPosition2 = xPosFinal+ xChange;
		yPosition2 = yPosFinal+height-yChange;
	}

	public int getPosition()
	{
		return xPosition2;
	}

	public void move()
	{
		xPosition1--;
		xPosition2--;
	}

	public void increaseHeight()
	{
		yPosition2--;
	}

	public void paintLine(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(3));
		g2d.drawLine(xPosition1,yPosition1,xPosition2,yPosition2);
	}

}