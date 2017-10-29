
/*
Bepen Neupane
Lab: Tuesday & Thursday 4:50-6:05
Project 3
bneupane@u.rochester.edu
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static Line lineArray(String linePos) { //gets coordinates from the text file and creates a line and returns it
		linePos = linePos.trim();
		String[] tempArray = linePos.split(" +");
		Double[] arrayOfPoints = new Double[4];
		Line tempLine = null;
		for (int j = 0; j < 4; j++) {
			arrayOfPoints[j] = Double.parseDouble(tempArray[j]);
		}
		double x1 = arrayOfPoints[0];
		double y1 = arrayOfPoints[1];
		double x2 = arrayOfPoints[2];
		double y2 = arrayOfPoints[3];
		tempLine = new Line(x1, y1, x2, y2);
		return tempLine;
	}

	public static Point[] pointArray(String pointPairs) { //gets coordinates from the text file and creates two points in an array and returns it
		pointPairs = pointPairs.trim();
		String[] tempArrayCoordinates = pointPairs.split(" +");
		Point[] arrayPoint = new Point[2];
		Point point = new Point(Double.parseDouble(tempArrayCoordinates[0]),
				Double.parseDouble(tempArrayCoordinates[1]));
		Point point2 = new Point(Double.parseDouble(tempArrayCoordinates[2]),
				Double.parseDouble(tempArrayCoordinates[3]));
		arrayPoint[0] = point;
		arrayPoint[1] = point2;
		return arrayPoint;
	}
	
	public static void main(String[] args) {
		try {
			Scanner scan = new Scanner(System.in);
			System.out.print("Please enter 'basic.txt', 'yes.txt', or 'no.txt' for different test cases (if you have your own file, enter that instead): ");
			String testCase = scan.next();
			BufferedReader textFileReader = new BufferedReader(new FileReader(testCase)); // file i/o for textfile
			Line[] lineArray;
			Point[][] pointArray;
			while (textFileReader.ready()) {
				String lineCountString = textFileReader.readLine();
				int lineCount = Integer.parseInt(lineCountString);
				lineArray = new Line[lineCount];
				for (int i = 0; i < lineCount; i++) { //creates lines then puts them into an array
					String lineCoords = textFileReader.readLine();
					Line tempLine = lineArray(lineCoords);
					lineArray[i] = tempLine;
				}
				String pointString;
				String pointTotalString = "";
				while ((pointString = textFileReader.readLine()) != null) {
					pointTotalString += pointString + "-";
				}
				String[] pointStringArray = pointTotalString.split("-");
				pointArray = new Point[pointStringArray.length][2];
				for (int i = 0; i < pointStringArray.length; i++) { // creates points and puts it into an array of an array of length two
					Point[] points = pointArray(pointStringArray[i]);
					pointArray[i][0] = points[0];
					pointArray[i][1] = points[1];
				}

				MyBinarySearchTree BST = new MyBinarySearchTree();
				MyTreeNode[] treeNodeArray = new MyTreeNode[Math.max(lineArray.length, pointArray.length)];

				for (int i = 0; i < lineArray.length; i++) { //inserts lines into the binary search tree
					BST.insert(lineArray[i]);
				}

				for (int i = 0; i < pointArray.length; i++) {
					treeNodeArray[i] = BST.lookup(BST.root, pointArray[i][0], pointArray[i][1]);
					if (treeNodeArray[i] == null) {
						System.out.println("The points " + pointArray[i][0].toString() + " and "
								+ pointArray[i][1].toString() + " are not separated by a line.");
					} else if (treeNodeArray[i] != null) {

						System.out.println(
								"The points " + pointArray[i][0].toString() + " and " + pointArray[i][1].toString()
										+ " are separated by the line " + treeNodeArray[i].line.toString());
					}
				}
				double numOfExternalNodes = Double.valueOf(BST.root.numOfExternalNodes()); //gets number of external nodes
				double lengthOfExternalNodes = Double.valueOf(BST.externalNodeLength()); //gets length of the longest branch of external nodes
				double averagePathLen = numOfExternalNodes / lengthOfExternalNodes; //gets the average length of the external nodes
				System.out.println("External nodes: " + numOfExternalNodes);
				System.out.println("External path length: " + lengthOfExternalNodes);
				System.out.println("Average length of paths: " + averagePathLen);

			}
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (IOException e) {
			System.out.println("IO Exception");
		}

	}

}
