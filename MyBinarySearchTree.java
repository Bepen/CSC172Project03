/*
Bepen Neupane
Lab: Tuesday & Thursday 4:50-6:05
Project 3
bneupane@u.rochester.edu
*/

public class MyBinarySearchTree {
	public MyTreeNode root;
	public final static int CLOCKWISE = -1;
	public final static int COLINEAR = 0;
	public final static int COUNTERCLOCKWISE = 1;

	public MyBinarySearchTree() {

	}

	public void insert(Line line) { //assigns the root with a line then calls up to the MyTreeNode insert method which then actually puts it into the tree
		if (root == null) {
			root = new MyTreeNode(line);
		} else {
			root = insert(line, root);
		}
	}

	int ccw(Point p0, Point p1, Point p2) { //tests whether a point is on one side of a line or the other
		double dx1 = p1.x - p0.x;
		double dy1 = p1.y - p0.y;
		double dx2 = p2.x - p0.x;
		double dy2 = p2.y - p0.y;
		if (dx1 * dy2 > dy1 * dx2)
			return COUNTERCLOCKWISE;
		else if (dx1 * dy2 < dy1 * dx2)
			return CLOCKWISE;
		else if ((dx1 * dx2 < 0) || (dy1 * dy2 < 0))
			return CLOCKWISE;
		else if ((dx1 * dx1 + dy1 * dy1) < (dx2 * dx2 + dy2 * dy2))
			return COUNTERCLOCKWISE;
		else
			return COLINEAR;
	}

	public MyTreeNode insert(Line line, MyTreeNode node) { // inserts the line and the root into the tree
		if (Line.intersectCheck(line, node.line)) {
			if (node.leftChild != null) {
				node.leftChild = insert(line, node.leftChild);
			} else {
				node.leftChild = new MyTreeNode(line);
			}
			if (node.rightChild != null) {
				node.rightChild = insert(line, node.rightChild);
			} else {
				node.rightChild = new MyTreeNode(line);
			}
		} else if (ccw(line.point, node.line.point, node.line.point) > 0) {
			if (node.leftChild != null) {
				node.leftChild = insert(line, node.leftChild);
			} else {
				node.leftChild = new MyTreeNode(line);
			}
		} else if (ccw(line.point, node.line.point, node.line.point) > 0) {
			if (node.rightChild != null) {
				node.rightChild = insert(line, node.rightChild);
			} else {
				node.rightChild = new MyTreeNode(line);
			}
		}
		return node;
	}

	public MyTreeNode lookup(MyTreeNode node, Point point1, Point point2) { //lookup method
		int point1Pos = ccw(point1, node.line.point, node.line.point2);
		int point2Pos = ccw(point2, node.line.point, node.line.point2);
		if (point1Pos != point2Pos) {
			return node;
		} else if (point1Pos == 1) {
			if (node.leftChild == null) {
				return null;
			} else {
				lookup(node.leftChild, point1, point2);
			}
		} else if (point1Pos == -1) {
			if (node.rightChild == null) {
				return null;
			} else {
				lookup(node.rightChild, point1, point2);
			}
		}
		return null;
	}

	public int externalNodeLength() { // gets length of the tree
		return root.externalNodeLength(root);
	}
}
