package fundamentals;

import java.awt.Dimension;
import java.awt.Graphics2D;

import main.Main;
import system.MyConsole;
import system.Vector3;
import system.interfaces.Collidable;
import system.interfaces.EmitsLight;

public class SimpleObject implements Collidable, EmitsLight, Cloneable{
	
	public Dimension size;
	public Vector3 pos;
	public boolean isColliding = false;
	public boolean hasCollision = true;
//	public boolean isInLight = false;
	public boolean isEmitting  = false;
	public int lightRange = 0;
	public int burnTime = -1;
	public Vector3 relativePosition;  
	
	
	
	public SimpleObject(Dimension size, float posX, float posY) {
		this.size = size;
		pos = new Vector3(posX, posY, 0);
		relativePosition = new Vector3(posX, posY, 0);
	}
	
	@Override
	public SimpleObject clone() {
		try {
			return (SimpleObject) super.clone();
		} catch (CloneNotSupportedException e) {
			MyConsole.logError("error while cloning");
		}
		return null;
	}
	
	public void draw(Graphics2D g) {}

	@Override
	public void onCollision(SimpleObject ob) {}

	@Override
	public void stopCollision(SimpleObject ob) {}

	@Override
	public void startCollision(SimpleObject ob) {}

	@Override
	public void emitLight() {}

	@Override
	public void burnsOut() {};
	
	public void setDefaultValues() {
		pos = worldToScreen(this.relativePosition, Main.gvStorage.cameraPos);
//		MyConsole.logInfo(pos.toString());
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
}
