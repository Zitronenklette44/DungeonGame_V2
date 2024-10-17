package timer;

import java.util.Timer;
import java.util.TimerTask;

import main.Main;
import rendering.Screen;
import system.GameLogic;
import system.Logger;

public class Tick implements Runnable {
	private Timer timer;
	public static GameLogic gameLogic = new GameLogic(); 
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
				} catch (NullPointerException e) {
					Logger.logError("Object to fully generated", e);
				}
				
				Screen.Repaint();
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				





			}
		}, 0, 17);
	}
	
	
	public void stopTimer() {
		timer.cancel();
	}

}
