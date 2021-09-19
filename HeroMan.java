import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.applet.*;
//import java.awt.event.*;
import java.net.URL;

public class HeroMan{

	private int xPosition1;

	private int yPosition1;
	int height = 30;
	public static int playmusic;

    BufferedImage img;
	int width;

	int xPosition;

	public HeroMan(int xPosition,int yPosition)
	{
		width = 40;
		//xPosition = 100;
		this.xPosition1 = xPosition;
		this.yPosition1 = yPosition-60;
		this.yPosition1 = 240;
		try {
		img = ImageIO.read(new File("StickImage.jpeg"));
	   	} catch (IOException e) {}

			String filename = "test.wav";
	   		URL url = null;
			try {
				File file = new File(filename);
				if (file.canRead())
					url = file.toURI().toURL();
			} catch (IOException e) {
				throw new IllegalArgumentException("could not play '" + filename + "'", e);
			}

			if (playmusic <= 1)
			{
				AudioClip clip = Applet.newAudioClip(url);
  				clip.play();
			}
  			playmusic ++;
  			System.out.println("playmusic :"+playmusic);
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

	public void paintHeroMan(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
  		g.drawImage(img, xPosition1,yPosition1, null);
  		//System.out.println("paintHeroMan xPosition1:"+xPosition1 +" yPosition1:"+yPosition1);
	}

}