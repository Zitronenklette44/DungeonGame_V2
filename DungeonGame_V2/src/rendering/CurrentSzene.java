package rendering;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

import main.Main;
import system.Logger;

public class CurrentSzene extends JLabel{
	private static final long serialVersionUID = 1L;
	
	int Height;
	int Width;
	private static Color BackgroundColor;
	
	
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
		
		
		
		
		//debug Camera 
		/*g.setColor(Color.white);
		g.drawOval(Screen.size.width/2 - Main.gvStorage.innerCameraBox/2, Screen.size.height/2-Main.gvStorage.innerCameraBox/2, Main.gvStorage.innerCameraBox, Main.gvStorage.innerCameraBox);
		g.drawOval(Screen.size.width/2 - Main.gvStorage.outerCameraBox/2, Screen.size.height/2-Main.gvStorage.outerCameraBox/2, Main.gvStorage.outerCameraBox, Main.gvStorage.outerCameraBox);*/
//		Logger.logInfo((Screen.size.width/2 - Main.gvStorage.innerCameraBox/2)+", " +(Screen.size.height/2-Main.gvStorage.innerCameraBox/2)+", " + Main.gvStorage.innerCameraBox+", " + Main.gvStorage.innerCameraBox);
	}
	
	
	
	
	
	
	
	
	
}
