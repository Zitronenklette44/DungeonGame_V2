package rendering;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

import javax.swing.JLabel;

import main.Main;
import system.MyConsole;

public class CurrentSzene extends JLabel{
	private static final long serialVersionUID = 1L;

	int Height;
	int Width;
	private static Color BackgroundColor = Color.green;


	public CurrentSzene(int Width, int Height) {
		this.Height = Height;
		this.Width = Width;
	}

	public CurrentSzene(Dimension size) {
		this.Height = size.height;
		this.Width = size.width;
	}

	@Override
	public void setBackground(Color bg) {
		BackgroundColor = bg;
	}


	@Override
	protected void paintComponent(Graphics gr) {
		super.paintComponent(gr);
		Graphics2D g = (Graphics2D) gr;
		g.setColor(BackgroundColor);
		g.fillRect(0, 0, getWidth(), getHeight());

		Main.gvStorage.screenController.drawObjects(g);
		
		//UI drawing
			drawXPBar(g);
		

		//debug Camera 
		if(Main.gvStorage.debug) {
			drawDebugScreen(g);
		}
	}
	
	private void drawDebugScreen(Graphics2D g) {
		g.setColor(Color.white);
		g.drawOval(Screen.size.width/2 - Main.gvStorage.innerCameraBox/2, Screen.size.height/2-Main.gvStorage.innerCameraBox/2, Main.gvStorage.innerCameraBox, Main.gvStorage.innerCameraBox);
		g.drawOval(Screen.size.width/2 - Main.gvStorage.outerCameraBox/2, Screen.size.height/2-Main.gvStorage.outerCameraBox/2, Main.gvStorage.outerCameraBox, Main.gvStorage.outerCameraBox);
		
		g.setFont(new Font("Arial Bold", 0, 20));
		g.drawString("allObjects: " + Main.gvStorage.screenController.getAllObjects().size(), Screen.size.width/100 * 2, Screen.size.height/100 * 2);
		g.drawString("currentObjects: " + Main.gvStorage.screenController.getCurrentObjects().size(), Screen.size.width/100 * 2, Screen.size.height/100 * 4);
		g.drawString("currentItems: " + Main.gvStorage.itemManager.getFloorItems().size(), Screen.size.width/100 * 2, Screen.size.height/100 * 6);
		g.drawString("currentXP: " + Main.gvStorage.player.currentXP, Screen.size.width/100 * 2, Screen.size.height/100 * 8);
		g.drawString("nextLvlXP: " + Main.gvStorage.player.nextLvlThreashold, Screen.size.width/100 * 2, Screen.size.height/100 * 10);
		g.drawString("currentLvl: " + Main.gvStorage.player.currentLvl, Screen.size.width/100 * 2, Screen.size.height/100 * 12);
		g.drawString(" ", Screen.size.width/100 * 2, Screen.size.height/100 * 14);
		g.drawString("PlayerPos: " + Math.round(Main.gvStorage.player.pos.getVecX()) + " | " + Math.round(Main.gvStorage.player.pos.getVecY()), Screen.size.width/100 * 2, Screen.size.height/100 * 16);
		
	}

	int currentProgress = 0;  

	private void drawXPBar(Graphics2D g) {
	    // Background
	    g.setColor(new Color(105, 90, 65));
	    Shape xpBar = CustomShapes.RoundedRec(Screen.size.width / 10 * 3, Screen.size.height / 100 * 3, Screen.size.width / 10 * 4, Screen.size.height / 100 * 2);
	    g.fill(xpBar);

	    if (Main.gvStorage.player.currentXP > 0) {
	        // current Progress gets displayed in percent
	        float currentProgressWidth = (Screen.size.width / 10 * 4) * (Main.gvStorage.player.currentXP / (float) Main.gvStorage.player.nextLvlThreashold);
	        
	        // stop from drawing outside of the shape
	        g.setClip(xpBar);

	        // get the difference
	        if (currentProgress != currentProgressWidth) {
	            int difference = (int) (currentProgressWidth - currentProgress);
	            currentProgress += difference / 10; // soft animation

	            // stop from exiting the Bar
	            if (currentProgress > currentProgressWidth) {
	                currentProgress = (int) currentProgressWidth;
	            } else if (currentProgress < 0) {
	                currentProgress = 0;
	            }
	        }

	        // draw progress
	        g.setColor(Color.white);
	        g.fill(CustomShapes.RoundedRec(Screen.size.width / 10 * 3, Screen.size.height / 100 * 3, (int) currentProgress, Screen.size.height / 100 * 2));

	        // reset clipping
	        g.setClip(null);
	    }

	    // draw border
	    g.setColor(Color.black);
	    g.draw(CustomShapes.RoundedRec(Screen.size.width / 10 * 3, Screen.size.height / 100 * 3, Screen.size.width / 10 * 4, Screen.size.height / 100 * 2));
	    g.setFont(new Font("Arial Bold", 0, 20));
	    g.drawString("Level "+Main.gvStorage.player.currentLvl, (int) (Screen.size.width / 10 * 4.8), (int) (Screen.size.height / 100 * 4.8));
	}








}
