package assignment9;

/**
 * Class to represent a Node
 * 
 * @author Emily Dennis, Johnny Le
 *
 */
public class Node {

	// Member variables
	char data;
	int x;
	int y;
	boolean start;
	boolean goal;
	boolean visited;
	Node cameFrom;

	public Node(int xCoord, int yCoord, char info) {
		x = xCoord;
		y = yCoord;
		data = info;
		start = false;
		goal = false;
	}

	public void setStart() {
		start = true;
		data = 'S';
	}

	public void setGoal() {
		goal = true;
		data = 'G';
	}

	public void setVisited() {
		visited = true;
	}

	public void setCameFrom(Node previous) {
		cameFrom = previous;
	}

}