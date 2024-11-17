package rendering;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Path2D;

public class CustomShapes {

	public static Shape RoundedRec(int posX, int posY, int width, int height) {

		Point startPoint = new Point(posX, posY + height / 2);
		Point oppositePoint = new Point(posX + width, posY + height / 2);
		
		Point curveControllPoint1 = new Point(posX, posY + height / 4);
		Point curveControllPoint2 = new Point(posX + height / 4, posY);

		Point curveEndPoint1 = new Point(posX + width / 100 * 5, posY);
		Point curveStartPoint2 = new Point(oppositePoint.x - width / 100 * 5, posY);
		
		Point curveControllPoint3 = new Point(oppositePoint.x - height / 4, posY);
		Point curveControllPoint4 = new Point(oppositePoint.x, posY + height / 4);
		
		Point curveControllPoint5 = new Point(oppositePoint.x, oppositePoint.y + height / 4);
		Point curveControllPoint6 = new Point(oppositePoint.x - height / 4, oppositePoint.y + height / 2);

		Point curveEndPoint3 = new Point(oppositePoint.x - width / 100 * 5, curveControllPoint6.y);
		Point curveStartPoint4 = new Point(posX + width / 100 * 5, curveControllPoint6.y);
		
		Point curveControllPoint7 = new Point(posX + height / 4, curveControllPoint6.y);
		Point curveControllPoint8 = new Point(posX , posY + height/ 4 * 3);

		Path2D.Double path = new Path2D.Double();
		path.moveTo(startPoint.x, startPoint.y);
		path.curveTo(curveControllPoint1.x, curveControllPoint1.y, curveControllPoint2.x, curveControllPoint2.y, curveEndPoint1.x, curveEndPoint1.y);
		path.lineTo(curveStartPoint2.x, curveStartPoint2.y);
		path.curveTo(curveControllPoint3.x, curveControllPoint3.y, curveControllPoint4.x, curveControllPoint4.y, oppositePoint.x, oppositePoint.y);
		path.curveTo(curveControllPoint5.x, curveControllPoint5.y, curveControllPoint6.x, curveControllPoint6.y, curveEndPoint3.x, curveEndPoint3.y);
		path.lineTo(curveStartPoint4.x, curveStartPoint4.y);
		path.curveTo(curveControllPoint7.x, curveControllPoint7.y, curveControllPoint8.x, curveControllPoint8.y, startPoint.x, startPoint.y);
		path.closePath();

		
		return path;
	}

}
