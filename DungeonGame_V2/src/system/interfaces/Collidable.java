package system.interfaces;

import fundamentals.SimpleObject;

public interface Collidable {
	
	void onCollision(SimpleObject ob);
	
	void startCollision(SimpleObject ob);
	
	void stopCollision(SimpleObject ob);
	
	
	
}
