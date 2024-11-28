package dungeon;

import java.awt.Graphics2D;
import java.util.ArrayList;
import dungeon.roomLogic.RoomTemplate;
import dungeon.roomLogic.Rooms;
import fundamentals.SimpleObject;
import main.Main;
import rendering.Screen;
import system.MyConsole;
import system.Vector3;

public class DungeonManager {

	private static ArrayList<RoomTemplate> possibleRooms = new ArrayList<RoomTemplate>();
	public ArrayList<RoomTemplate> currentRooms = new ArrayList<RoomTemplate>();

	public Vector3 pos;
	public Vector3 relativePosition;
	public boolean isDark;
	public int maxRoomNR;
	public int currentRoomNR;
	public int minRoomNR;

	public static void init() {
		possibleRooms.add(Rooms.generateStart(0, 0));
		possibleRooms.add(Rooms.generateTestRoom1(0, 0));
		possibleRooms.add(Rooms.generateTestRoom2(0, 0));
		possibleRooms.add(Rooms.generateTestRoom3(0, 0));
	}

	public DungeonManager(Vector3 relativePosition, int maxRoomNR, int minRoomNR, boolean isDark) {
		this.maxRoomNR = maxRoomNR;
		this.minRoomNR = minRoomNR;
		this.relativePosition = relativePosition;
		this.isDark = isDark;
		setDefaultValues();
	}

	private void generateRoom(int RoomID) {
		RoomTemplate room = possibleRooms.get(RoomID).clone();
		room.pos = pos.copy();
		room.roomID = currentRoomNR;
		currentRooms.add(room);
		currentRoomNR++;
	}

	public void generateRooms() {
		generateRoom(0);

	}

	public ArrayList<SimpleObject> getAllFeatures() {
		ArrayList<SimpleObject> objects = new ArrayList<SimpleObject>();
		for (RoomTemplate room : currentRooms) {
			objects.addAll(room.features);
		}
		return objects;
	}

	public void draw(Graphics2D g) {
		int Width = Screen.size.width;
		int Height = Screen.size.height;
		int padding = 100; // Bounding Box for rendered Objects
		for (RoomTemplate room : currentRooms) {
			if (room.pos.getVecX() + room.size.width > -padding && room.pos.getVecX() < Width + padding
					&& room.pos.getVecY() + room.size.height > -padding && room.pos.getVecY() < Height + padding) {
				if (room.pos.getVecZ() == Main.gvStorage.player.pos.getVecZ()) {
					room.draw(g);
				}
			}
		}
	}

	public void changePos(Vector3 addVec) {
		pos.add(addVec);
		for (RoomTemplate room : currentRooms) {
			room.pos.add(addVec);
		}
	}
	
	public void setDefaultValues() {
		relativePosition.subtract(new Vector3(500, 500, 0));
		pos = worldToScreen(this.relativePosition, Main.gvStorage.cameraPos);
	}

	protected Vector3 worldToScreen(Vector3 worldPos, Vector3 cameraPos) {
	    if (worldPos == null || cameraPos == null) {
	        MyConsole.logError("worldToScreen: Invalid Input--> worldPos or cameraPos is null.");
	        return new Vector3(0, 0, 0);
	    }
	    
	    return new Vector3(
	        worldPos.getVecX() - cameraPos.getVecX() + Main.gvStorage.centerPos.getVecX(),
	        worldPos.getVecY() - cameraPos.getVecY() + Main.gvStorage.centerPos.getVecY(),
	        worldPos.getVecZ() 
	    );
	}
	
	
	
	
	
	
	
	
	
	
	
	//TODO: Dungeon Raum erstellung 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
