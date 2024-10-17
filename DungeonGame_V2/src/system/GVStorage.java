package system;

import entitys.CreateMob;
import entitys.mobs.Player;
import main.Main;
import rendering.Screen;
import timer.Tick;

public class GVStorage {
	
	//random System stuff
	public ScreenController screenController;
	public Tick tickTimer;
	public Player player;
	
	//key Variables
	public boolean moveLeft = false;
	public boolean moveRight = false;
	public boolean moveUp = false;
	public boolean moveDown = false;
	
	
	//Screen details
	public int innerCameraBox = 300;
	public int outerCameraBox = 900;
	public Vector3 centerPos = new Vector3(300, 300, 0);
	
	
	
	public GVStorage() {
		screenController = new ScreenController();
		tickTimer = new Tick();
		player = new Player();
		
		
		startTimer();
	}
	
	
	public void createObjects() {
		Main.gvStorage.screenController.addObject(player);
		CreateMob.createTestObject();
		
	}
	
	private void startTimer() {
		Thread tickThread = new Thread(tickTimer);
		tickThread.start();

	}
	
	public void updateScreenDetails() {
		Logger.logWarning("updated Screen");
		innerCameraBox = (Screen.size.height/100)*40;
		outerCameraBox = (Screen.size.height/100)*80;
		centerPos = new Vector3(Screen.size.width/2, Screen.size.height/2, 0);
	}
	
	
	

}
