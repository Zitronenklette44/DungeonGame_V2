package fundamentals;

import java.util.Iterator;

import main.Main;
import system.Logger;
import system.Vector3;

public class CameraMovement {
	
	float cameraSpeed;
	
	private static float movementThreshold = Main.gvStorage.innerCameraBox / 2;  // Schwelle für Bewegung
    private static float centerThreshold = Main.gvStorage.innerCameraBox / 4;  // Kleinere Schwelle für Zentrieren
	
	public CameraMovement() {
	}
	
	public static void movementLogic() {
        movementThreshold = Main.gvStorage.innerCameraBox / 2;
        centerThreshold = Main.gvStorage.innerCameraBox / 4;

        float cameraSpeed = calculateCameraSpeed(movementThreshold);  // Bewegungsschwelle nutzen

        if (Main.gvStorage.player.speed > 0 && !Main.gvStorage.player.isColliding) {  // Wenn der Spieler sich bewegt
            if (cameraSpeed > 0) {
                moveObjects(cameraSpeed);
            }
        } else if (!isPlayerCentered(centerThreshold) && !Main.gvStorage.player.isColliding) {  // Spieler steht, nutze Zentrierschwelle
            cameraSpeed = calculateCameraSpeed(centerThreshold);  // Nutze Zentrierschwelle
            if (cameraSpeed > 0) {
                adjustCameraToCenter();
            }
        }
        Logger.logInfo("isColliding: "+Main.gvStorage.player.isColliding);
    }
	
	
	private static float getPlayerDistCenter() {
		return Main.gvStorage.centerPos.getDistance(Main.gvStorage.player.pos);
	}
	
	private static float calculateCameraSpeed(float threshold) {
		if(Main.gvStorage.player.isColliding) {
			return 0;
		}
        float maxSpeed = Main.gvStorage.player.maxSpeed;
        float outerBox = Main.gvStorage.outerCameraBox / 2;
        float innerBox = Main.gvStorage.innerCameraBox / 2;
        float distance = getPlayerDistCenter();

        float distanceFactor = outerBox - innerBox;

        if (distanceFactor <= 0) {
            throw new IllegalArgumentException("Division durch Null oder negative Werte im Nenner: " + distanceFactor);
        }

        float adjustedDistance = Math.max(0, Math.min(distance - threshold, distanceFactor));
        return (maxSpeed / distanceFactor) * adjustedDistance;
    }
	
	private static void moveObjects(float Speed) {		
		if(Main.gvStorage.player.isColliding) {
			return;
		}
		Vector3 antiPlayer = Main.gvStorage.player.movement.getReverse();
		
		antiPlayer.normalize();
		antiPlayer.scale(Speed);
		
		if(Main.gvStorage.screenController.getAllObjects().size() == 0) {
			return;
		}
		
		Iterator<SimpleObject> iterator = Main.gvStorage.screenController.getAllObjects().iterator();
		while(iterator.hasNext()) {
			SimpleObject ob = iterator.next();
				ob.pos.add(antiPlayer);
		}

	}
	
	private static void moveObjects(Vector3 direction, float speed) {
	    // Skaliere den Bewegungsvektor entsprechend der Geschwindigkeit
	    direction.scale(speed);

	    Iterator<SimpleObject> iterator = Main.gvStorage.screenController.getAllObjects().iterator();
	    while (iterator.hasNext()) {
	        SimpleObject ob = iterator.next();
	        ob.pos.add(direction);  // Bewege jedes Objekt in die Richtung des Zentrums
	    }
	}
	
	
	
	private static boolean isPlayerCentered(float threshold) {
        float distance = getPlayerDistCenter();
        return distance <= threshold;
    }

	private static void adjustCameraToCenter() {
        // Bewege die Kamera Richtung Zentrum des Spielers
        Vector3 directionToCenter = Main.gvStorage.centerPos.copy();
        directionToCenter.subtract(Main.gvStorage.player.pos);
        directionToCenter.normalize();
        
        moveObjects(directionToCenter, Main.gvStorage.player.maxSpeed / 5);  // Kamera bewegt sich langsamer
    }
	
}
