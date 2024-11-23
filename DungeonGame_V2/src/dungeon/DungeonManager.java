package dungeon;

import java.util.ArrayList;

import dungeon.roomLogic.RoomTemplate;
import system.Vector3;

public class DungeonManager {
	
	private static ArrayList<RoomTemplate> possibleRooms = new ArrayList<RoomTemplate>();
	ArrayList<RoomTemplate> currentRooms = new ArrayList<RoomTemplate>();
	
	Vector3 startPos;
	boolean isDark;
	int maxRoomNR;
	int currentRoomNR;
	int minRoomNR;
	
	public static void init() {
		
	}
	
	
	public DungeonManager(Vector3 startPos, int maxRoomNR, int minRoomNR, boolean isDark) {
		this.maxRoomNR = maxRoomNR;
		this.minRoomNR = minRoomNR;
		this.startPos = startPos;
		this.isDark = isDark;
	}
	
	private void generateRoom(int RoomID) {

	}
	
	
	public void generateRooms() {
		// TODO Auto-generated method stub

	}
	
	
	
}
