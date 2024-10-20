package system;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Main;

public class KeyHandler implements KeyListener{
	
	public KeyHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {
			Main.gvStorage.moveLeft = true;
		}
		
		if(e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
			Main.gvStorage.moveRight = true;
		}
		
		if(e.getKeyChar() == 's' || e.getKeyChar() == 'S') {
			Main.gvStorage.moveDown = true;
		}
		
		if(e.getKeyChar() == 'w' || e.getKeyChar() == 'W') {
			Main.gvStorage.moveUp = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_F1) {
			Main.gvStorage.debug = !Main.gvStorage.debug;
			Logger.logInfo("debug change: "+ Main.gvStorage.debug);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {
			Main.gvStorage.moveLeft = false;
		}
		
		if(e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
			Main.gvStorage.moveRight = false;
		}
		
		if(e.getKeyChar() == 's' || e.getKeyChar() == 'S') {
			Main.gvStorage.moveDown = false;
		}
		
		if(e.getKeyChar() == 'w' || e.getKeyChar() == 'W') {
			Main.gvStorage.moveUp = false;
		}
		
	}

}
