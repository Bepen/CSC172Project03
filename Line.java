/*
Bepen Neupane
Lab: Tuesday & Thursday 4:50-6:05
Project 3
bneupane@u.rochester.edu
*/

public class Line { //line object

	public Point point;
	public Point point2;

	public Line(double xPos, double yPos, double xPos2, double yPos2) { //creates a line out of two sets of coordinates
		point = new Point(xPos, yPos);
		point2 = new Point(xPos2, yPos2);
	}

	public Line() {
		
	}

	public static Point intersectionPoint(Line firstLine, Line secondLine) { //checks for points of intersection
		double xPos = firstLine.point.x;
		double yPos = firstLine.point.y;
		double xPos2 = firstLine.point2.x;
		double yPos2 = firstLine.point2.y;
		double xPos3 = secondLine.point.x;
		double yPos3 = secondLine.point.y;
		double xPos4 = secondLine.point2.x;
		double yPos4 = secondLine.point2.y;
		double slope;
		double yIntercept;
		double slope2;
		double yIntercept2;
		double intersectionX = 0;
		double intersectionY = 0;
		
		if ((xPos == xPos2 && xPos3 == xPos4) && xPos != xPos3) {
			return null;
		} else if ((xPos == xPos2 && xPos3 != xPos4)) {
			slope2 = (yPos4 - yPos3) / (xPos4 - xPos3);
			yIntercept2 = yPos3 - slope2 * xPos3;
			intersectionX = xPos;
			intersectionY = slope2 * intersectionX + yIntercept2;
		} else if ((xPos != xPos2 && xPos3 == xPos4)) {
			slope = (yPos4 - yPos3) / (xPos4 - xPos3);
			yIntercept = yPos3 - slope * xPos3;
			intersectionX = xPos3;
			intersectionY = slope * intersectionX + yIntercept;
		} else {
			slope = (yPos2 - yPos) / (xPos2 - xPos);
			yIntercept = yPos - slope * xPos;
			slope2 = (yPos4 - yPos3) / (xPos4 - xPos3);
			yIntercept2 = yPos3 - slope2 * xPos3;

			if (slope == slope2) {
				if (yIntercept == yIntercept2) {
					return null;
				}
			}

			intersectionX = -(yIntercept - yIntercept2) / (slope - slope2);
			intersectionY = slope * intersectionX + yIntercept;

		}

		if (intersectionX < 0 || intersectionY < 0 || intersectionX > 1 || intersectionY > 1) {
			return null;
		}

		return new Point(intersectionX, intersectionY);
	}

	public static boolean intersectCheck(Line firstLine, Line secondLine) { //ckecks if lines intersect and returns a boolean value accordingly
		if (intersectionPoint(firstLine, secondLine) == null) {
			return false;
		} else {
			return true;
		}
	}

	public String toString() {
		return "[" + point.toString() + ", " + point2.toString() + "]";
	}
}
