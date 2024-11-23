package system;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import commandLine.CommandInterpretor;
import dungeon.DungeonManager;
import dungeon.roomLogic.RoomTemplate;
import entitys.CreateMob;
import entitys.mobs.Player;
import fundamentals.SimpleObject;
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
	public CommandInterpretor commandInterpretor;
	public Tick tickTimer;
	public Player player;
	public boolean debug = false;
	public boolean consoleOpen = false;
	public RoomTemplate lastRoom;
	public int currentLvl = 0;
	public int switchToLvl = 0;
	public boolean startLvlSwitch = false;
	
	//key Variables
	public boolean moveLeft = false;
	public boolean moveRight = false;
	public boolean moveUp = false;
	public boolean moveDown = false;
	
	
	//Screen details
	public int innerCameraBox = 300;
	public int outerCameraBox = 900;
	public Vector3 centerPos = new Vector3(300, 300, 0);
	public Vector3 cameraPos;
	
	
	//Listen
	ArrayList<CopyOnWriteArrayList<SimpleObject>> allLvls = new ArrayList<CopyOnWriteArrayList<SimpleObject>>();
	ArrayList<Vector3> playerPosLvl = new ArrayList<Vector3>();
	
	public GVStorage() {
		screenController = new ScreenController();
		itemManager = new ItemManager();
		commandInterpretor = new CommandInterpretor();
		tickTimer = new Tick();
		player = new Player();
		cameraPos = player.pos;
		
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
	
	public void updateScreenDetails(boolean showMessages) {
		if(showMessages)
			MyConsole.logWarning("updated Screen");
		innerCameraBox = (Screen.size.height/100)*40;
		outerCameraBox = (Screen.size.height/100)*80;
		centerPos = new Vector3(Screen.size.width/2.0f, Screen.size.height/2.0f, 0);
//		MyConsole.logInfo("centerPos -->"+centerPos.toString());
	}
	
	public void initLvls() {
		allLvls.add(screenController.getAllObjects());
		playerPosLvl.add(player.relativePosition);
	}
	
	public void switchLvl(int newLevel){
		if(newLevel < 0) {
			MyConsole.logError("Invalid Lvl: smaller zero -->"+newLevel);
			return;
		}
		MyConsole.logInfo("currentLvl-->"+currentLvl+" newLvl-->"+newLevel);
		playerPosLvl.set(currentLvl, player.relativePosition);
		allLvls.set(currentLvl, screenController.getAllObjects());
		if(allLvls.size() - 1 < newLevel) {
			CopyOnWriteArrayList<SimpleObject> temp = new CopyOnWriteArrayList<SimpleObject>();
			temp.add(player);
			allLvls.add(temp);
			playerPosLvl.add(player.relativePosition.copy());
			newLevel = allLvls.size()-1;
		}
		screenController.replaceObjectList(allLvls.get(newLevel));
		player.relativePosition = playerPosLvl.get(newLevel);
		cameraPos = playerPosLvl.get(newLevel).copy();
		player.pos.vecZ = newLevel;
		player.pos.vecX = centerPos.vecX;
		player.pos.vecY = centerPos.vecY;
		currentLvl = newLevel;
	}
	

}
