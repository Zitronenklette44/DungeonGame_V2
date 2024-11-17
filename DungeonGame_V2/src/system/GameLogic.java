package system;

import java.util.Iterator;

import fundamentals.CameraMovement;
import fundamentals.SimpleObject;
import main.Main;
import rendering.Screen;

public class GameLogic {

	private float lastXMovement = 0;
	private float lastYMovement = 0;
	private int XCounter = 0;
	private int YCounter = 0;
	
	private Vector3 lastPos = Vector3.zeroVec();

	public GameLogic() {

	}

	public void startProcesses() {		//All Processes each tick

		movePlayer();
		CameraMovement.movementLogic();
		CollisionObjects();
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
			if(lastPos.equals(Main.gvStorage.player.pos)) {	//when position is equal to saved position
				Main.gvStorage.player.isColliding = true;	//set collision true
			}else {
				Main.gvStorage.player.isColliding = false;	//set collision false
			}
		}else {
			Main.gvStorage.player.isColliding = false;	//set collision false
		}
		
		lastPos = Main.gvStorage.player.pos.copy();	//save current position
		
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
						MyConsole.logInfo("force Reset Speed: Player stuck on value " + Main.gvStorage.player.movement.vecX);
						Main.gvStorage.player.movement.vecX = 0;	//reset movement do to error
						XCounter = 0;	//reset counter
					}
					if (lastXMovement == Main.gvStorage.player.movement.vecX && lastXMovement != 0 && Math.abs(lastXMovement) != 1) {		//if last stored movement equals current movement and isn't zero
						XCounter++;		//increment counter
					}else {
						XCounter = 0;
					}
					lastXMovement = Main.gvStorage.player.movement.vecX;		//store movement into variable
					//					Logger.logInfo("addLeft: " + Main.gvStorage.player.movement.vecX);
				}
			}
			if (moveRight) {	//when move right
				if (Main.gvStorage.player.movement.vecX < Main.gvStorage.player.maxSpeed) {		//when movement is smaller then max speed
					Main.gvStorage.player.movement.add(Vector3.rightVec());		//increase motion vector by one
					if (XCounter >= 3) {	//if counter reaches 3
						MyConsole.logInfo("force Reset Speed: Player stuck on value " + Main.gvStorage.player.movement.vecX);	
						Main.gvStorage.player.movement.vecX = 0;	//reset movement do to error
						XCounter = 0;	//reset counter
					}
					//					Logger.logInfo("LastXPos: "+ lastXPos);
					if (lastXMovement == Main.gvStorage.player.movement.vecX && lastXMovement != 0 && Math.abs(lastXMovement) != 1) {	//when last stored movement equals current movement and isn't zero
						XCounter++;		//increment counter
					}else {
						XCounter = 0;
					}
					lastXMovement = Main.gvStorage.player.movement.vecX;		//store current movement
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
						MyConsole.logInfo("force Reset Speed: Player stuck on value " + Main.gvStorage.player.movement.vecY);
						Main.gvStorage.player.movement.vecY = 0;	//reset movement
						YCounter = 0;	//reset counter
					}
					if (lastYMovement == Main.gvStorage.player.movement.vecY && lastYMovement != 0 && Math.abs(lastYMovement) != 1) {	//when last movement equals current movement and isn't zero
						YCounter++;		//increment counter
					}else {
						YCounter = 0;
					}
					lastYMovement = Main.gvStorage.player.movement.vecY;	//store current movement
				}
			}
			if (moveDown) {		//when move down
				if (Main.gvStorage.player.movement.vecY < Main.gvStorage.player.maxSpeed) {		//when movement smaller then max speed
					Main.gvStorage.player.movement.add(Vector3.downVec());		//increase movement by one
					if (YCounter >= 3) {	//when counter reaches 3
						MyConsole.logInfo("force Reset Speed: Player stuck on value " + Main.gvStorage.player.movement.vecY);
						Main.gvStorage.player.movement.vecY = 0;	//reset movement
						YCounter = 0;		//reset counter
					}
					if (lastYMovement == Main.gvStorage.player.movement.vecY && lastYMovement != 0 && Math.abs(lastYMovement) != 1) {	//when current movement equals last movement and isn't zero
						YCounter++;		//increment counter
					}else {
						YCounter = 0;
					}
					lastYMovement = Main.gvStorage.player.movement.vecY;		//store current movement
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

	private boolean outOfBounds() {		//checks if player would move out of Bounds
		Vector3 pos = Main.gvStorage.player.pos.copy();		//copy current position
		Vector3 movement = Main.gvStorage.player.movement.copy();	//copy current movement
		pos.add(movement);	//add movement to position
		if (pos.getVecX() < 0 || pos.getVecX() + Main.gvStorage.player.size.width > Screen.size.width - 15 || pos.getVecY() < 0
				|| pos.getVecY() + Main.gvStorage.player.size.height > Screen.size.height - 40) {	//if out of Bound from Frame
			return true;
		}
		return false;
	}

	public boolean checkCollision(SimpleObject obj1, SimpleObject obj2) {

		boolean collisionX = obj1.pos.getVecX() + obj1.size.width > obj2.pos.getVecX() &&
                obj1.pos.getVecX() < obj2.pos.getVecX() + obj2.size.width;	//overlapping on x-axis

		boolean collisionY = obj1.pos.getVecY() + obj1.size.height > obj2.pos.getVecY() &&
                obj1.pos.getVecY() < obj2.pos.getVecY() + obj2.size.height;	//overlapping on y-axis
		
		if (collisionX && collisionY) {
	        obj1.onCollision(obj2);
	        obj2.onCollision(obj1);
	        
	        return true;
	    }
		
		/*if (collisionX && collisionY) {
	        if (!obj1.isColliding && !obj2.isColliding) {
	            //start collision
	        	obj1.startCollision(obj2);
	        	obj2.startCollision(obj1);
	        	obj1.isColliding = true;  //save collision start
	            obj2.isColliding = true;
	            return true;
	        }
	    } else {
	        if (obj1.isColliding || obj2.isColliding) {
	            //end collision
	        	obj1.stopCollision(obj2);
	        	obj2.stopCollision(obj1);
	            obj1.isColliding = false; 	//save collision stop
	            obj2.isColliding = false;
	        }
	    }*/
		// no collision
		return false;
	}

	private void CollisionObjects() {	//checks collisions for all Elements on Screen with all other Elements on screen
	    Iterator<SimpleObject> iterator = Main.gvStorage.screenController.getCurrentObjects().iterator();
	    while (iterator.hasNext()) {	//for all current Elements
	        SimpleObject ob = iterator.next();
	        if(ob.hasCollision) {
	        	Iterator<SimpleObject> iteratorInner = Main.gvStorage.screenController.getCurrentObjects().iterator();
	        	while (iteratorInner.hasNext()) {	//for all current Elements
	        		SimpleObject obInner = iteratorInner.next();	
	            	if (ob != obInner) {	//stop self collision
	                	checkCollision(ob, obInner);	//check collision
	            	}
	        	}
	        }
	    }
	}

	public void moveLastPos(Vector3 movement) {
		this.lastPos.add(movement);
	}



}
