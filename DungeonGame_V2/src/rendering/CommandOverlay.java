package rendering;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

import commandLine.CommandInterpretor;
import main.Main;
import system.MyConsole;

public class CommandOverlay extends JLabel{
	private static final long serialVersionUID = 1L;
	
	private StringBuilder currentText = new StringBuilder();
	
	public CommandOverlay() {
		
	}
	
	@Override
	protected void paintComponent(Graphics gd) {
		super.paintComponent(gd);
		Graphics2D g = (Graphics2D) gd;
		if(!isVisible()) {
			return;
		}
		
		g.setColor(Color.black);
		g.fillRect(0, getHeight()-getHeight()/100*10, getWidth(), getHeight()/100*5);
		g.setFont(new Font("Arial Bold", 0, 20));
		g.setColor(Color.white);
		g.drawString(currentText.toString(), getWidth()/100, getHeight()-getHeight()/100*7);
		
		
	}
	
	public void addString(char c) {
		if(Character.isLetterOrDigit(c)) {
			currentText.append(c);
		}else if(c == ' ') {
			currentText.append(c);
		}
	}
	
	public void removeLastChar() {
		if(currentText.length() > 0) {
			currentText.setLength(currentText.length()-1);
		}
	}
	
	public void endInput() {
		setVisible(false);
		Main.gvStorage.commandInterpretor.startInterpretation(currentText.toString());
		currentText.setLength(0);
	}
	
	
	@Override
	public void setVisible(boolean aFlag) {
		Main.gvStorage.consoleOpen = aFlag;
		super.setVisible(aFlag);
	}

}




