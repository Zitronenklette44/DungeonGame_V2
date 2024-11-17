package fundamentals;

import java.util.Iterator;

import main.Main;
import system.MyConsole;
import system.Vector3;
import timer.Tick;

public class CameraMovement {
	
	float cameraSpeed;
	
	private static float movementThreshold = Main.gvStorage.innerCameraBox / 2; 
    private static float centerThreshold = Main.gvStorage.innerCameraBox / 4;
	
	public CameraMovement() {
	}
	
	public static void movementLogic() {
        movementThreshold = Main.gvStorage.innerCameraBox / 2;		//refresh to exclude errors do to resizing the frame
        centerThreshold = Main.gvStorage.innerCameraBox / 4;

        float cameraSpeed = calculateCameraSpeed(movementThreshold);  // use movement Threshold

        if (Main.gvStorage.player.speed > 0 && !Main.gvStorage.player.isColliding) {  //when player is moving and isn't colliding
            if (cameraSpeed > 0) {
            	Vector3 directionToCenter = Main.gvStorage.centerPos.copy();	//calculate movement direction for camera
                directionToCenter.subtract(Main.gvStorage.player.pos);
                directionToCenter.normalize();
                
                moveObjects(directionToCenter, cameraSpeed);	//move camera relative to player distance
            }
        } else if (!isPlayerCentered(centerThreshold) /*&& !Main.gvStorage.player.isColliding*/) {  //if player isn't moving
            cameraSpeed = calculateCameraSpeed(centerThreshold);  //use center Threshold
            if (cameraSpeed > 0) {
                adjustCameraToCenter();		//move camera slowly until player is centered
            }
        }
    }
	
	
	private static float getPlayerDistCenter() {
		return Main.gvStorage.centerPos.getDistance(Main.gvStorage.player.pos);
	}
	
	private static float calculateCameraSpeed(float threshold) {
        float maxSpeed = Main.gvStorage.player.maxSpeed;
        float outerBox = Main.gvStorage.outerCameraBox / 2;
        float innerBox = Main.gvStorage.innerCameraBox / 2;
        float distance = getPlayerDistCenter();

        float distanceFactor = outerBox - innerBox;

        if (distanceFactor <= 0) {
            MyConsole.logError("Fehlerhafte berechnung: "+ new IllegalAccessException(""));
        }

        float adjustedDistance = Math.max(0, Math.min(distance - threshold, distanceFactor));
        return (maxSpeed / distanceFactor) * adjustedDistance;
    }
	
	private static void moveObjects(Vector3 direction, float speed) {
	    direction.scale(speed);		//adjust direction with speed

	    Iterator<SimpleObject> iterator = Main.gvStorage.screenController.getAllObjects().iterator();
	    Tick.gameLogic.moveLastPos(direction);
	    while (iterator.hasNext()) {
	        SimpleObject ob = iterator.next();
	        ob.pos.add(direction);  //move objects to center
	    }
	}
	
	
	
	private static boolean isPlayerCentered(float threshold) {
        float distance = getPlayerDistCenter();
        return distance <= threshold;
    }

	private static void adjustCameraToCenter() {
        // moves camera to player
        Vector3 directionToCenter = Main.gvStorage.centerPos.copy();
        directionToCenter.subtract(Main.gvStorage.player.pos);
        directionToCenter.normalize();
        
        moveObjects(directionToCenter, Main.gvStorage.player.maxSpeed / 3);  // move camera slowly to center player
    }
	
}
