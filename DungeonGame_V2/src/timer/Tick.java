package timer;

import java.util.Timer;
import java.util.TimerTask;

import main.Main;
import rendering.Screen;
import system.GameLogic;
import system.MyConsole;
import system.interfaces.InterfaceLogic;

public class Tick implements Runnable {
	private Timer timer;
	public static GameLogic gameLogic = new GameLogic(); 
	public InterfaceLogic interfaceLogic = new InterfaceLogic();
	public Tick() {
		timer = new Timer();
	}


	@Override
	public void run() {
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				try {
					Main.gvStorage.screenController.checkCurrentObjects();
					gameLogic.startProcesses();
					interfaceLogic.startLogic();
				} catch (NullPointerException e) {
					MyConsole.logError("Object not fully generated", e);
				}
				
				Screen.Repaint();
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				





			}
		}, 0, 17);
	}
	
	
	public void stopTimer() {
		timer.cancel();
	}

}
