package dungeon.roomLogic;

import java.awt.Color;
import java.awt.Dimension;

public class Rooms {
	
	public static RoomTemplate generateStart(float posX, float posY) {
		RoomTemplate room = new RoomTemplate(new Dimension(1000, 1000), posX, posY);
		room.color = Color.gray.brighter().brighter();
		return room;
	}
	
	public static RoomTemplate generateTestRoom1(float posX, float posY) {
		RoomTemplate room = new RoomTemplate(new Dimension(1000, 1000), posX, posY);
		room.color = Color.blue;
		return room;
	}
	
	public static RoomTemplate generateTestRoom2(float posX, float posY) {
		RoomTemplate room = new RoomTemplate(new Dimension(1000, 1000), posX, posY);
		room.color = Color.cyan;
		return room;
	}
	
	
	public static RoomTemplate generateTestRoom3(float posX, float posY) {
		RoomTemplate room = new RoomTemplate(new Dimension(1000, 1000), posX, posY);
		room.color = Color.white;
		return room;
	}

}
