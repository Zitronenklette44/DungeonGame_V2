package system;

public class Vector3 {

	float vecX, vecY, vecZ;

	public Vector3(float vecX, float vecY, float vecZ) {
		this.vecX = vecX;
		this.vecY = vecY;
		this.vecZ = vecZ;
	}

	public float getVecX() {
		return vecX;
	}

	public float getVecY() {
		return vecY;
	}

	public float getVecZ() {
		return vecZ;
	}

	public static Vector3 zeroVec() {
		return new Vector3(0, 0, 0);
	}

	public static Vector3 oneVec() {
		return new Vector3(1, 1, 1);
	}

	public static Vector3 rightVec() {
		return new Vector3(1, 0, 0);
	}

	public static Vector3 leftVec() {
		return new Vector3(-1, 0, 0);
	}

	public static Vector3 upVec() {
		return new Vector3(0, -1, 0);
	}

	public static Vector3 downVec() {
		return new Vector3(0, 1, 0);
	}

	public static Vector3 forwardsVec() {
		return new Vector3(0, 0, 1);
	}

	public static Vector3 backwardsVec() {
		return new Vector3(0, 0, -1);
	}

	public void add(Vector3 vec) {
		this.vecX += vec.getVecX();
		this.vecY += vec.getVecY();
		this.vecZ += vec.getVecZ();
	}

	public void subtract(Vector3 vec) {
		this.vecX -= vec.getVecX();
		this.vecY -= vec.getVecY();
		this.vecZ -= vec.getVecZ();
	}

	public void scale(float factor) {
		this.vecX *= factor;
		this.vecY *= factor;
		this.vecZ *= factor;
	}

	public Vector3 copy() {
		return new Vector3(this.vecX, this.vecY, this.vecZ);
	}

	public Vector3 getReverse() {
		return new Vector3(-vecX, -vecY, -vecZ);
	}

	public float length() {
		return (float) Math.sqrt(vecX * vecX + vecY * vecY + vecZ * vecZ);
	}

	public void normalize() {
		float lenght = length();
		if (lenght != 0) {
			scale(1 / lenght);
		}
	}

	public float getDistance(Vector3 vec) {
		return (float) Math.sqrt((vecX - vec.vecX) * (vecX - vec.vecX) + 
				(vecY - vec.vecY) * (vecY - vec.vecY)// +
//		        (vecZ - vec.vecZ) * (vecZ - vec.vecZ)
		);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() == this.getClass()) {
			Vector3 vec = (Vector3) obj;
			return vecX == vec.getVecX() && vecY == vec.getVecY() && vecZ == vec.getVecZ();
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "(" + vecX + "|" + vecY + "|" + vecZ + ")";
	}

}
