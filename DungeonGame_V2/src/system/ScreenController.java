package system;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import fundamentals.SimpleObject;
import rendering.Screen;

public class ScreenController {

	private ArrayList<SimpleObject> currentObjects;
	private ArrayList<SimpleObject> allObjects;

	public ScreenController() {
		currentObjects = new ArrayList<>();
		allObjects = new ArrayList<>();

	}

	public void checkCurrentObjects() {
		int Width = Screen.size.width;
		int Height = Screen.size.height;

		Iterator<SimpleObject> iterator = currentObjects.iterator();
		while (iterator.hasNext()) {
			SimpleObject Object = iterator.next();

			if (Object.pos.getVecX() + Object.size.width < 0 || Object.pos.getVecX() > Width - Object.size.width - 5
					|| Object.pos.getVecY() < 0 || Object.pos.getVecY() > Height) {
				iterator.remove();
			}
		}

		iterator = allObjects.iterator();
		while (iterator.hasNext()) {
			SimpleObject Object = iterator.next();

			if (Object.pos.getVecX() + Object.size.width > 0 && Object.pos.getVecX() < Width && Object.pos.getVecY() > 0
					&& Object.pos.getVecY() < Height) {
				if (!currentObjects.contains(Object)) {
					currentObjects.add(Object);
				}
			}
		}
//		Logger.logInfo("current: " + currentObjects.toString() + "\n all: " + allObjects.toString());

	}

	public void addObject(SimpleObject newObject) {
		allObjects.add(newObject);
	}

	public void removeObject(SimpleObject object) {
		for (int i = 0; i < allObjects.size(); i++) {
			if (allObjects.get(i) == object) {
				allObjects.remove(i);
				break;
			}
		}
		Logger.logError("invalid Object to remove", new IllegalArgumentException());
	}

	public void drawObjects(Graphics2D g) {
		Iterator<SimpleObject> iterator = currentObjects.iterator();
		while (iterator.hasNext()) {
			SimpleObject ob = iterator.next();
			ob.draw(g);
		}

	}

	public ArrayList<SimpleObject> getAllObjects() {
		return allObjects;
	}

	public ArrayList<SimpleObject> getCurrentObjects() {
		return currentObjects;
	}

	
}