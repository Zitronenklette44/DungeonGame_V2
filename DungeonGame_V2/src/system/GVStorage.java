package system;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import entitys.CreateMob;
import entitys.mobs.Player;
import items.ItemManager;
import items.ItemTemplate;
import main.Main;
import rendering.Screen;
import system.interfaces.InterfaceLogic;
import timer.Tick;

public class GVStorage {
	
	//random System stuff
	public ScreenController screenController;
	public ItemManager itemManager;
	public Tick tickTimer;
	public Player player;
	public boolean debug = false;
	
	//key Variables
	public boolean moveLeft = false;
	public boolean moveRight = false;
	public boolean moveUp = false;
	public boolean moveDown = false;
	
	
	//Screen details
	public int innerCameraBox = 300;
	public int outerCameraBox = 900;
	public Vector3 centerPos = new Vector3(300, 300, 0);
	
	
	//Listen
	
	
	public GVStorage() {
		screenController = new ScreenController();
		itemManager = new ItemManager();
		tickTimer = new Tick();
		player = new Player();
		
		
		startTimer();
	}
	
	
	public void createObjects() {
	    Main.gvStorage.screenController.addObject(player);

	    int numObjects = 500;
	    int minDistance = 250;  // Mindestabstand zwischen den Objekten
	    List<Vector3> positions = new ArrayList<>();  // Speichert die Positionen aller Objekte
	    
	    for (int i = 0; i < numObjects; i++) {
	        boolean validPosition = false;
	        Vector3 newPos = null;

	        while (!validPosition) {
	            // Zufällige Position innerhalb eines größeren Bereichs, auch negative Koordinaten
	            int randomX = (int)(Math.random() * 20000) - 10000;  // -1000 bis 1000
	            int randomY = (int)(Math.random() * 20000) - 10000;  // -1000 bis 1000

	            newPos = new Vector3(randomX, randomY, 0);  // Z-Koordinate auf 0 setzen (2D)
	            
	            validPosition = true;
	            // Prüfe, ob die neue Position mindestens minDistance von allen bisherigen Objekten entfernt ist
	            for (Vector3 pos : positions) {
	                if (newPos.getDistance(pos) < minDistance) {
	                    validPosition = false;
	                    break;
	                }
	            }
	        }

	        // Füge die Position der Liste hinzu, nachdem sie validiert wurde
	        positions.add(newPos);

	        // Erstelle das Objekt mit der validierten Position
	        int randomNum = (int) (Math.random()*100);
	        if(randomNum%2 == 0) {
	        	CreateMob.testObject(new Dimension((int)(Math.random()*100)+10, (int)(Math.random()*100)+10), (int)newPos.getVecX(), (int)newPos.getVecY());
	        } else {
	        	CreateMob.colorSwitcher(new Dimension((int)(Math.random()*60)+50, (int)(Math.random()*60)+50), (int)newPos.getVecX(), (int)newPos.getVecY());
	        }
	    }
	}

	
	private void startTimer() {
		Thread tickThread = new Thread(tickTimer);
		tickThread.start();

	}
	
	public void updateScreenDetails() {
		MyConsole.logWarning("updated Screen");
		innerCameraBox = (Screen.size.height/100)*40;
		outerCameraBox = (Screen.size.height/100)*80;
		centerPos = new Vector3(Screen.size.width/2, Screen.size.height/2, 0);
	}
	
	
	

}
