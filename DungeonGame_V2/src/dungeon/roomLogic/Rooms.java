package dungeon.roomLogic;

import java.awt.Dimension;

public class Rooms {
	
	public static RoomTemplate generateStart(float posX, float posY) {
		return new RoomTemplate(new Dimension(500, 500), posX, posY);
	}
	
	
	
	

}
