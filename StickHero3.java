import javax.swing.JFrame;

import javax.swing.JPanel;

import javax.swing.WindowConstants;

import java.awt.Font;
import java.awt.*;

import javax.swing.JOptionPane;

import java.awt.Graphics;

import java.awt.Graphics2D;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

import java.awt.event.MouseEvent;

import java.awt.event.MouseListener;

import java.util.*;

public class StickHero3 extends JPanel implements KeyListener,MouseListener{

	boolean isPressed = false;

	private int pressedValue = 0;

	private int wallIndex = 0;

	private int lineIndex = 0;

	private int heroIndex = 0;

	private int scoretotal = 0;
	private int cherryflag = 0;

	private boolean scoreChanged = true;
	private boolean gameEnd = false;

	boolean shouldMove = false;

	private ArrayList <Walls> list = new ArrayList <Walls>();

	private ArrayList <Line> lineList = new ArrayList <Line>();

	private ArrayList <HeroMan> hero = new ArrayList <HeroMan>();

	private JFrame frame  = new JFrame("Stick Hero");

	Label l1;

	public StickHero3()  //constructor
	{
		//create frame
		frame.setFocusable(true);
		frame.setSize(1000,600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.addKeyListener(this);
		frame.addMouseListener(this);
		frame.add(this);

		list.add(new Walls());
		list.add(new Walls());
		hero.add(new HeroMan(100,300));
		lineList.add(new Line(0,0));
		list.get(1).makeSecondWall();
		repaint();
		moveLeft();
		while(true)
		{
			if(isPressed == true)
			{
				icrementValue();
			}
			if(shouldMove == true)
			{
				moveLeft();
				shouldMove = false;
			}
		}
	}

	public void icrementValue()
	{
		lineList.add(new Line(list.get(wallIndex).getPosition()+list.get(wallIndex).getWidth()/2,330));
		lineIndex++;
		hero.add(new HeroMan(list.get(wallIndex).getPosition()+list.get(wallIndex).getWidth()/2,330));
		heroIndex++;
		while(isPressed == true){
			pressedValue++;
			lineList.get(lineIndex).increaseHeight();
			repaint();
			try{
			Thread.sleep(6);
			} catch(Exception e){}
		}
		lineList.get(lineIndex).setFinal();
		for(int angle = 0;angle<91;angle++)
		{
			lineList.get(lineIndex).refractor(angle);
			try{
			Thread.sleep(6);
			} catch(Exception e){}
			repaint();
		}
		System.out.println(pressedValue);
		pressedValue = 0;
		int linePosition = lineList.get(lineIndex).getPosition();
		if(linePosition>list.get(wallIndex+1).getPosition()&&linePosition<(list.get(wallIndex+1).getPosition()+list.get(wallIndex+1).getWidth())) //checks if line fell on wall
		{
			scoretotal++;
			if (cherryflag ==1)
				scoretotal++;
			scoreChanged = true;
			shouldMove = true;
			gameEnd = false;
			//System.out.println("scoretotal :"+scoretotal);

		}
		else
		{
			System.out.println("It is Short or Long Game Ends :");
			gameEnd = true;
		}
	}

	public void moveLeft()
	{
		for(int x = 0;x<500;x++)
		{
			if(list.get(wallIndex).getPosition()==0)
			{
				wallIndex++;
				list.add(new Walls());
				//hero.add(new HeroMan());
				list.get(wallIndex+1).makeSecondWall();
				list.get(wallIndex+1).addPosition(500-x);
			}
			list.get(wallIndex).move();
			list.get(wallIndex+1).move();
			//hero.get(heroIndex).move();
			lineList.get(lineIndex).move();
			try{
				Thread.sleep(4);
			} catch(Exception e){}
			repaint();
		}

	}

	public void paintComponent(Graphics g)  //generates the images
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawString("Score: "+scoretotal,30,30);
		cherryflag = 0;
		if (scoretotal == 2  || scoretotal == 5)
		{
				cherryflag = 1;
		 		//g.fillOval(50,
		 		g.setColor(Color.RED);
		 		//g.fillOval(list.get(wallIndex+1).getPosition()-10,300, 10,10);
		 		//g.fillOval(list.get(wallIndex+1).getPosition()- 20,310, 10,10);
		 		g.fillOval(list.get(wallIndex+1).getPosition()- 30,290, 10,10);
		}

		if(gameEnd == true)
		{
			g2d.drawString("GAME OVER!! Press c and Play again",70,100);
		}
		else
		{
			list.get(wallIndex).paintWalls(g);
			list.get(wallIndex+1).paintWalls(g);
			hero.get(heroIndex).paintHeroMan(g);
			lineList.get(lineIndex).paintLine(g);
		}
	}


		public void keyPressed(KeyEvent k)
		{

		}

		public void keyReleased(KeyEvent k)
		{

		}

		public void keyTyped(KeyEvent k)
		{
			char keyChar = k.getKeyChar();
			int key = k.getKeyCode();
			if (keyChar == 'c') {
			  System.out.println("You typed 'c'");
			  scoretotal = 0;
			  gameEnd = false;
			}
			if (key == KeyEvent.VK_UP || key == KeyEvent.VK_KP_UP)
			{
				isPressed = true;
				System.out.println("human pressed UP ");
			}
			if (key == KeyEvent.VK_RIGHT  || key == KeyEvent.VK_KP_RIGHT) {
				System.out.println("human pressed RIGHT ");
			}
			if (key == KeyEvent.VK_DOWN  || key == KeyEvent.VK_KP_DOWN) {
				System.out.println("human pressed DOWN ");
			}
			if (key == KeyEvent.VK_LEFT  || key == KeyEvent.VK_KP_LEFT) {
				System.out.println("human pressed LEFT ");
			}
		}

		public void mousePressed(MouseEvent e)
		{
			if(e.getButton()==MouseEvent.BUTTON1)
			{
					isPressed = true;
			}
		}

		public void mouseReleased(MouseEvent e)
		{
			if(e.getButton()==MouseEvent.BUTTON1)
			{
				isPressed = false;
			}
		}

		public void mouseEntered(MouseEvent e)
		{

		}

		public void mouseClicked(MouseEvent e)
		{

		}

		public void mouseExited(MouseEvent e)
		{

		}

}