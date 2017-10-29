/*
Bepen Neupane
Lab: Tuesday & Thursday 4:50-6:05
Project 3
bneupane@u.rochester.edu
*/

public class MyTreeNode {
	public MyTreeNode leftChild;
	public MyTreeNode rightChild;
	public MyTreeNode parent;
	public MyTreeNode root;
	public Line line;

	public MyTreeNode(Line line) {
		this.line = line;
		leftChild = null;
		rightChild = null;
		parent = null;
	}

	public int numOfExternalNodes() { // counts the number of external nodes by traversing through the tree and incrementing the count variable
		int count = 1;
		if (leftChild != null) {
			count += leftChild.numOfExternalNodes();
		}
		if (rightChild != null) {
			count += rightChild.numOfExternalNodes();
		}
		return count;
	}

	public int externalNodeLength(MyTreeNode node) { //finds the length of the longest branch and returns i t
		MyTreeNode node2 = node;
		if (node == null) {
			return 0;
		}
		int left = 0;
		int right = 0;

		while (node != null) {
			left++;
			node = node.leftChild;
		}

		while (node2 != null) {
			right++;
			node2 = node2.rightChild;
		}
		int length = Math.max(left, right);
		return length;
	}

}
