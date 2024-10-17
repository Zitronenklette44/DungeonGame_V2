package system;

import main.Main;
import rendering.Screen;

public class GameLogic {

	private float lastXPos = 0;
	private float lastYPos = 0;
	private int XCounter = 0;
	private int YCounter = 0;

	public GameLogic() {

	}

	public void startProcesses() {

		movePlayer();
		System.out.println("change");
	}

	private void movePlayer() {
		boolean moveLeft = Main.gvStorage.moveLeft;
		boolean moveRight = Main.gvStorage.moveRight;
		boolean moveUp = Main.gvStorage.moveUp;
		boolean moveDown = Main.gvStorage.moveDown;

		//		Logger.logInfo("L: "+moveLeft+" R: "+ moveRight+" U: "+ moveUp+" D: "+moveDown);

		if (moveLeft || moveRight || moveUp || moveDown) {
			Main.gvStorage.player.speed += Main.gvStorage.player.accelerationRate;

			if (Main.gvStorage.player.speed > Main.gvStorage.player.maxSpeed) {
				Main.gvStorage.player.speed = Main.gvStorage.player.maxSpeed;
			}
		} else {
			if (Main.gvStorage.player.speed > 0) {
				Main.gvStorage.player.speed -= Main.gvStorage.player.accelerationRate * 2;
			}
			if (Main.gvStorage.player.speed < 0) {
				Main.gvStorage.player.speed = 0;
			}
		}
		//				Logger.logInfo("Player Speed: "+ Main.gvStorage.player.speed);

		if (!moveLeft && !moveRight) {
			if (Main.gvStorage.player.movement.vecX >= 0.1) {
				Main.gvStorage.player.movement.vecX -= (Main.gvStorage.player.movement.vecX / 100) * 20;
			} else if (Main.gvStorage.player.movement.vecX <= -0.1) {
				Main.gvStorage.player.movement.vecX += (Main.gvStorage.player.movement.vecX / 100) * 20;
			} else {
				Main.gvStorage.player.movement.vecX = 0;
			}
		}

		if (!moveUp && !moveDown) {
			Logger.logInfo("No move up/Down + player vecY: "+ Main.gvStorage.player.movement.vecY);
			if (Main.gvStorage.player.movement.vecY >= 0.1) {
				Main.gvStorage.player.movement.vecY -= (Main.gvStorage.player.movement.vecY / 100) * 20;
			} else if (Main.gvStorage.player.movement.vecY <= -0.1) {
				Main.gvStorage.player.movement.vecY += (Main.gvStorage.player.movement.vecY / 100) * 20;
			} else {
				Main.gvStorage.player.movement.vecY = 0;
			}
		}

		if (Main.gvStorage.player.speed == 0) {
			Main.gvStorage.player.movement = Vector3.zeroVec();
		}

		if (moveLeft && moveRight) {
			Main.gvStorage.player.movement.vecX = 0;
		} else {
			if (moveLeft) {
				if (Main.gvStorage.player.movement.vecX > -Main.gvStorage.player.maxSpeed) {
					Main.gvStorage.player.movement.add(Vector3.leftVec());
					if (XCounter >= 3) {
						Logger.logInfo("force Reset Speed: Player stuck on value " + Main.gvStorage.player.movement.vecX);
						Main.gvStorage.player.movement.vecX = 0;
						XCounter = 0;
					}
					if (lastXPos == Main.gvStorage.player.movement.vecX && lastXPos != 0) {
						XCounter++;
					}
					lastXPos = Main.gvStorage.player.movement.vecX;
//					Logger.logInfo("addLeft: " + Main.gvStorage.player.movement.vecX);
				}
			}
			if (moveRight) {
				if (Main.gvStorage.player.movement.vecX < Main.gvStorage.player.maxSpeed) {
					Main.gvStorage.player.movement.add(Vector3.rightVec());
					if (XCounter >= 3) {
						Logger.logInfo("force Reset Speed: Player stuck on value " + Main.gvStorage.player.movement.vecX);
						Main.gvStorage.player.movement.vecX = 0;
						XCounter = 0;
					}
//					Logger.logInfo("LastXPos: "+ lastXPos);
					if (lastXPos == Main.gvStorage.player.movement.vecX && lastXPos != 0) {
						XCounter++;
					}
					lastXPos = Main.gvStorage.player.movement.vecX;
//						    	        Logger.logInfo("addRight: " + Main.gvStorage.player.movement.vecX);
				}
			}
		}

		if (moveUp && moveDown) {
			Main.gvStorage.player.movement.vecY = 0;
		} else {
			if (moveUp) {
				if (Main.gvStorage.player.movement.vecY > -Main.gvStorage.player.maxSpeed) {
					Main.gvStorage.player.movement.add(Vector3.upVec());
					Logger.logInfo("addUp: "+ Main.gvStorage.player.movement.vecY);
					if (YCounter >= 3) {
						Logger.logInfo("force Reset Speed: Player stuck on value " + Main.gvStorage.player.movement.vecY);
						Main.gvStorage.player.movement.vecY = 0;
						YCounter = 0;
					}
					if (lastYPos == Main.gvStorage.player.movement.vecY && lastYPos != 0) {
						YCounter++;
					}
					lastYPos = Main.gvStorage.player.movement.vecY;
				}
			}
			if (moveDown) {
				if (Main.gvStorage.player.movement.vecY < Main.gvStorage.player.maxSpeed) {
					Main.gvStorage.player.movement.add(Vector3.downVec());
					if (YCounter >= 3) {
						Logger.logInfo("force Reset Speed: Player stuck on value " + Main.gvStorage.player.movement.vecY);
						Main.gvStorage.player.movement.vecY = 0;
						YCounter = 0;
					}
					if (lastYPos == Main.gvStorage.player.movement.vecY && lastYPos != 0) {
						YCounter++;
					}
					lastYPos = Main.gvStorage.player.movement.vecY;
				}
			}
		}


		//		Logger.logInfo("playerSpeed: "+Main.gvStorage.player.movement.toString());
		Main.gvStorage.player.movement.normalize();
		Main.gvStorage.player.movement.scale(Main.gvStorage.player.speed);
		if (!outOfBounds()) {
			Main.gvStorage.player.pos.add(Main.gvStorage.player.movement);
			// moveObjects();
		}

	}

	private boolean outOfBounds() {
		Vector3 pos = Main.gvStorage.player.pos.copy();
		Vector3 movement = Main.gvStorage.player.movement.copy();
		pos.add(movement);
		if (pos.getVecX() < 0 || pos.getVecX() > Screen.size.width || pos.getVecY() < 0
				|| pos.getVecY() > Screen.size.height) {
			return true;
		}
		return false;
	}

	// private void moveObjects() { // temp
	// Iterator<SimpleObject> iterator =
	// Main.gvStorage.screenController.getAllObjects().iterator();
	// while(iterator.hasNext()) {
	// iterator.next().pos.add(Main.gvStorage.player.movement.getReverse());
	// }
	//
	//
	// }

}
