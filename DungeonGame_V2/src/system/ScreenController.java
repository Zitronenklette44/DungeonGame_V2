package system;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import entitys.mobs.Player;
import fundamentals.SimpleObject;
import items.ItemTemplate;
import main.Main;
import rendering.Screen;

public class ScreenController {

	private CopyOnWriteArrayList<SimpleObject> currentObjects;
	private CopyOnWriteArrayList<SimpleObject> allObjects;
	private boolean darkness = false;

	public ScreenController() {
		currentObjects = new CopyOnWriteArrayList<>();
		allObjects = new CopyOnWriteArrayList<>();

	}

	public void checkCurrentObjects() {
		int Width = Screen.size.width;
		int Height = Screen.size.height;
		int padding = 200; // Bounding Box for rendered Objects

		for (int i = 0; i < currentObjects.size(); i++) {
			SimpleObject Object = currentObjects.get(i);

			if (Object.pos.getVecZ() != Main.gvStorage.player.pos.getVecZ()) {
				currentObjects.remove(i);
			} else if (Object.pos.getVecX() + Object.size.width < -padding || Object.pos.getVecX() > Width + padding
					|| Object.pos.getVecY() + Object.size.height < -padding
					|| Object.pos.getVecY() > Height + padding) {
				currentObjects.remove(i);
				i--;
			}
		}

		Iterator<SimpleObject> iterator = allObjects.iterator();
		while (iterator.hasNext()) {
			SimpleObject Object = iterator.next();

			if (Object.pos.getVecX() + Object.size.width > -padding && Object.pos.getVecX() < Width + padding
					&& Object.pos.getVecY() + Object.size.height > -padding
					&& Object.pos.getVecY() < Height + padding) {
				if (!currentObjects.contains(Object) && Object.pos.getVecZ() == Main.gvStorage.player.pos.getVecZ()) {
					currentObjects.add(Object);
				}
			}
		}
		
	}

	public void addObject(SimpleObject newObject) {
		allObjects.add(newObject);
		currentObjects.add(newObject);
	}

	public void removeObject(SimpleObject object) {
		for (int i = 0; i < allObjects.size(); i++) {
			if (allObjects.get(i) == object) {
				allObjects.remove(i);
				break;
			}
		}

		for (int i = 0; i < currentObjects.size(); i++) {
			if (currentObjects.get(i) == object) {
				currentObjects.remove(i);
				return;
			}
		}
		MyConsole.logError("invalid Object to remove", new IllegalArgumentException());
	}

	public void replaceObjectList(CopyOnWriteArrayList<SimpleObject> newObjects) {
		allObjects = newObjects;
	}
	
	
	
	public void drawObjects(Graphics2D g) {
		ArrayList<SimpleObject> drawLater = new ArrayList<>();
		try {
			Iterator<SimpleObject> iterator = currentObjects.iterator();
			while (iterator.hasNext()) {
				SimpleObject ob = iterator.next();
				if (darkness && (ob.isEmitting)) {
					drawLater.add(ob);
				} else {
					ob.draw(g);
				}
			}
		} catch (ConcurrentModificationException e) {
			// Handle exception gracefully
		}

		if (darkness) {
			// Step 1: Create a BufferedImage for the darkness layer
			BufferedImage darknessLayer = new BufferedImage(Screen.size.width, Screen.size.height,
					BufferedImage.TYPE_INT_ARGB);
			Graphics2D darknessGraphics = darknessLayer.createGraphics();

			// Fill the screen with black
			darknessGraphics.setColor(new Color(0, 0, 0, 235));
			darknessGraphics.fillRect(0, 0, Screen.size.width, Screen.size.height);

			// Enable anti-aliasing for smoother cutouts
			darknessGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			// Step 2: Cut out light circles
			for (SimpleObject ob : drawLater) {
				if (ob.isEmitting) {
					float centerX = (float) (ob.pos.getVecX() + ob.size.width / 2);
					float centerY = (float) (ob.pos.getVecY() + ob.size.height / 2);
					float radius = (float) (ob.lightRange * 1.5);

					// Use AlphaComposite.Clear to erase the light area
					darknessGraphics.setComposite(AlphaComposite.Clear);
					darknessGraphics.fillOval((int) (centerX - radius / 2), (int) (centerY - radius / 2), (int) radius,
							(int) radius);
				}
			}

			// Dispose of the graphics for the darkness layer
			darknessGraphics.dispose();

			// Step 3: Draw the darkness layer on top of the main graphics
			g.drawImage(darknessLayer, 0, 0, null);

			// Step 4: Draw the objects that need to be drawn after the darkness layer
			for (SimpleObject ob : drawLater) {
				ob.draw(g);
			}
		}
	}

	public CopyOnWriteArrayList<SimpleObject> getAllObjects() {
		return allObjects;
	}

	public CopyOnWriteArrayList<SimpleObject> getCurrentObjects() {
		return currentObjects;
	}
	
	

}
