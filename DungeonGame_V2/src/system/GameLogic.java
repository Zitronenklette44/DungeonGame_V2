package system;

import fundamentals.CameraMovement;
import main.Main;
import rendering.Screen;

public class GameLogic {

	private float lastXPos = 0;
	private float lastYPos = 0;
	private int XCounter = 0;
	private int YCounter = 0;

	public GameLogic() {

	}

	public void startProcesses() {		//All Processes each tick

		movePlayer();
		CameraMovement.movementLogic();
	}

	private void movePlayer() {		//Controls for the player movement
		//Variables from key press
		boolean moveLeft = Main.gvStorage.moveLeft;
		boolean moveRight = Main.gvStorage.moveRight;
		boolean moveUp = Main.gvStorage.moveUp;
		boolean moveDown = Main.gvStorage.moveDown;

		//		Logger.logInfo("L: "+moveLeft+" R: "+ moveRight+" U: "+ moveUp+" D: "+moveDown);
		
		//while at least one is pressed
		if (moveLeft || moveRight || moveUp || moveDown) {
			Main.gvStorage.player.speed += Main.gvStorage.player.accelerationRate;		//adding acceleration to current speed

			if (Main.gvStorage.player.speed > Main.gvStorage.player.maxSpeed) {		//if max speed is crossed
				Main.gvStorage.player.speed = Main.gvStorage.player.maxSpeed;	//set to max Speed
			}
		} else {
			if (Main.gvStorage.player.speed > 0) {	//if speed is over zero
				Main.gvStorage.player.speed -= Main.gvStorage.player.accelerationRate * 2;		//decrease speed by double the acceleration rate
			}
			if (Main.gvStorage.player.speed < 0) {		//if speed is negative
				Main.gvStorage.player.speed = 0;	//set zero
			}
		}
		//				Logger.logInfo("Player Speed: "+ Main.gvStorage.player.speed);

		if (!moveLeft && !moveRight) {		//while not moving on X-axis
			if (Main.gvStorage.player.movement.vecX >= 0.1) {	//when movement is over 0.1
				Main.gvStorage.player.movement.vecX -= (Main.gvStorage.player.movement.vecX / 100) * 20;	//decrease movement vector for x-axis by 20% of current movement
			} else if (Main.gvStorage.player.movement.vecX <= -0.1) {	//when speed is under -0.1
				Main.gvStorage.player.movement.vecX += (Main.gvStorage.player.movement.vecX / 100) * 20;	//increase movement vector for x-axis by 20% of current movement
			} else {
				Main.gvStorage.player.movement.vecX = 0;	//set movement to zero
			}
		}

		if (!moveUp && !moveDown) {	//while not moving on Y-axis
//			Logger.logInfo("No move up/Down + player vecY: "+ Main.gvStorage.player.movement.vecY);
			if (Main.gvStorage.player.movement.vecY >= 0.1) {	//when movement is over 0.1
				Main.gvStorage.player.movement.vecY -= (Main.gvStorage.player.movement.vecY / 100) * 20;	//decrease movement vector for y-axis by 20% of current movement
			} else if (Main.gvStorage.player.movement.vecY <= -0.1) {	//when movement is under -0.1
				Main.gvStorage.player.movement.vecY += (Main.gvStorage.player.movement.vecY / 100) * 20;	//increase movement vector for y-axis by 20% of current movement
			} else {
				Main.gvStorage.player.movement.vecY = 0;	//set movement zero
			}
		}

		if (Main.gvStorage.player.speed == 0) {	//when speed is zero remove motion by setting motion vector to zero vector
			Main.gvStorage.player.movement = Vector3.zeroVec();
		}

		if (moveLeft && moveRight) {		//when moving right and left at the same time
			Main.gvStorage.player.movement.vecX = 0;		//set vector of X-axis to zero
		} else {
			if (moveLeft) {		//when moving left
				if (Main.gvStorage.player.movement.vecX > -Main.gvStorage.player.maxSpeed) {	//when movement is bigger then the negative maxSpeed
					Main.gvStorage.player.movement.add(Vector3.leftVec());		//increase movement vector by negative one
					if (XCounter >= 3) {		//if counter reaches 3
						Logger.logInfo("force Reset Speed: Player stuck on value " + Main.gvStorage.player.movement.vecX);
						Main.gvStorage.player.movement.vecX = 0;	//reset movement do to error
						XCounter = 0;	//reset counter
					}
					if (lastXPos == Main.gvStorage.player.movement.vecX && lastXPos != 0) {		//if last stored movement equals current movement and isn't zero
						XCounter++;		//increment counter
					}
					lastXPos = Main.gvStorage.player.movement.vecX;		//store movement into variable
//					Logger.logInfo("addLeft: " + Main.gvStorage.player.movement.vecX);
				}
			}
			if (moveRight) {	//when move right
				if (Main.gvStorage.player.movement.vecX < Main.gvStorage.player.maxSpeed) {		//when movement is smaller then max speed
					Main.gvStorage.player.movement.add(Vector3.rightVec());		//increase motion vector by one
					if (XCounter >= 3) {	//if counter reaches 3
						Logger.logInfo("force Reset Speed: Player stuck on value " + Main.gvStorage.player.movement.vecX);	
						Main.gvStorage.player.movement.vecX = 0;	//reset movement do to error
						XCounter = 0;	//reset counter
					}
//					Logger.logInfo("LastXPos: "+ lastXPos);
					if (lastXPos == Main.gvStorage.player.movement.vecX && lastXPos != 0) {	//when last stored movement equals current movement and isn't zero
						XCounter++;		//increment counter
					}
					lastXPos = Main.gvStorage.player.movement.vecX;		//store current movement
//						    	        Logger.logInfo("addRight: " + Main.gvStorage.player.movement.vecX);
				}
			}
		}

		if (moveUp && moveDown) {		//when moving up and down at the same time
			Main.gvStorage.player.movement.vecY = 0;	//set movement on y-axis to zero
		} else {
			if (moveUp) {	//when move up
				if (Main.gvStorage.player.movement.vecY > -Main.gvStorage.player.maxSpeed) {	// when movement is bigger then negative max speed value
					Main.gvStorage.player.movement.add(Vector3.upVec());	//increase movement by negative one
//					Logger.logInfo("addUp: "+ Main.gvStorage.player.movement.vecY);
					if (YCounter >= 3) {	//if counter reaches 3
						Logger.logInfo("force Reset Speed: Player stuck on value " + Main.gvStorage.player.movement.vecY);
						Main.gvStorage.player.movement.vecY = 0;	//reset movement
						YCounter = 0;	//reset counter
					}
					if (lastYPos == Main.gvStorage.player.movement.vecY && lastYPos != 0) {	//when last movement equals current movement and isn't zero
						YCounter++;		//increment counter
					}
					lastYPos = Main.gvStorage.player.movement.vecY;	//store current movement
				}
			}
			if (moveDown) {		//when move down
				if (Main.gvStorage.player.movement.vecY < Main.gvStorage.player.maxSpeed) {		//when movement smaller then max speed
					Main.gvStorage.player.movement.add(Vector3.downVec());		//increase movement by one
					if (YCounter >= 3) {	//when counter reaches 3
						Logger.logInfo("force Reset Speed: Player stuck on value " + Main.gvStorage.player.movement.vecY);
						Main.gvStorage.player.movement.vecY = 0;	//reset movement
						YCounter = 0;		//reset counter
					}
					if (lastYPos == Main.gvStorage.player.movement.vecY && lastYPos != 0) {	//when current movement equals last movement and isn't zero
						YCounter++;		//increment counter
					}
					lastYPos = Main.gvStorage.player.movement.vecY;		//store current movement
				}
			}
		}


		//		Logger.logInfo("playerSpeed: "+Main.gvStorage.player.movement.toString());
		Main.gvStorage.player.movement.normalize();		//normalize movement vector
		Main.gvStorage.player.movement.scale(Main.gvStorage.player.speed);		//scale vector by the current speed
		if (!outOfBounds()) {	//if player movement isn't out of Bounds
			Main.gvStorage.player.pos.add(Main.gvStorage.player.movement);	//update player position with player movement
			// moveObjects();
		}
	}

	private boolean outOfBounds() {
		Vector3 pos = Main.gvStorage.player.pos.copy();
		Vector3 movement = Main.gvStorage.player.movement.copy();
		pos.add(movement);
		if (pos.getVecX() < 0 || pos.getVecX() + Main.gvStorage.player.size.width > Screen.size.width - 15 || pos.getVecY() < 0
				|| pos.getVecY() + Main.gvStorage.player.size.height > Screen.size.height - 40) {
			return true;
		}
		return false;
	}


}
