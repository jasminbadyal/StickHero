import java.awt.Graphics;

import java.awt.Graphics2D;

import java.awt.Color;

import java.awt.Rectangle;

public class Walls{

	private final int yPosition=330;

	private final int height = 230;

	int width;

	int xPosition;

	public Walls()
	{
		width = (int)(80*Math.random())+40;
		xPosition = (int)(150*Math.random())+100;
	}

	public void makeSecondWall()
	{
		xPosition+=500; //moves the second wall by 500px
	}


	public void addPosition(int x)
	{
		xPosition+=x;
	}

	public void move()
	{
		xPosition--;
	}

	public int getWidth()
	{
		return width;
	}

	public int getPosition()
	{
	return xPosition;
	}
	public void paintWalls(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.drawRect(xPosition,yPosition,width,height);
		g2d.fillRect(xPosition,yPosition,width,height);
	}

}