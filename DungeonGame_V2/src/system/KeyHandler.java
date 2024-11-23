package system;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import items.ItemManager;
import main.Main;
import rendering.Screen;

public class KeyHandler implements KeyListener{
	
	public KeyHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if(Main.gvStorage.consoleOpen) {
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				Screen.showCommandOverlay(false);
			}else if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
				Screen.commandOverlay.removeLastChar();
			}else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				Screen.commandOverlay.endInput();
			}else {
				Screen.commandOverlay.addString(e.getKeyChar());
				
			}
			
			return;
		}
		
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
		
		if(e.getKeyChar() == 't' || e.getKeyChar() == 'T') {
			Screen.showCommandOverlay(true);;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_F1) {
			Main.gvStorage.debug = !Main.gvStorage.debug;
			MyConsole.logInfo("debug change: "+ Main.gvStorage.debug);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_F2) {
			Main.gvStorage.itemManager.generateXPOrb(Main.gvStorage.player.pos.vecX+150, Main.gvStorage.player.pos.vecY, 10);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP && !Main.gvStorage.startLvlSwitch) {
//			Main.gvStorage.switchLvl(Main.gvStorage.currentLvl + 1);
			Main.gvStorage.startLvlSwitch = true;
			Main.gvStorage.switchToLvl++;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN && !Main.gvStorage.startLvlSwitch) {
//			Main.gvStorage.switchLvl(Main.gvStorage.currentLvl - 1);
			Main.gvStorage.startLvlSwitch = true;
			if(Main.gvStorage.currentLvl > 0)
				Main.gvStorage.switchToLvl--;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(Main.gvStorage.consoleOpen) {
			return;
		}
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
