package assignment9;

/**
 * Class to represent a Graph<E>
 * 
 * @author Emily Dennis, Johnny Le
 *
 * @param <E>
 *            Generic parameter
 */
public class Graph<E> {

	// Member variables
	Node[][] nodes;

	// Graph Constructor
	public Graph(int x, int y) {
		nodes = new Node[x][y];
	}

	public Node get(int x, int y) {
		return nodes[x][y];
	}

	public void addNode(int x, int y, char info) {
		nodes[x][y] = new Node(x, y, info);
	}

	// Sets wall nodes equal to null
	public void addWallNode(int x, int y) {
		nodes[x][y] = null;
	}

	public void addStartNode(int x, int y, char info) {
		nodes[x][y] = new Node(x, y, info);
		nodes[x][y].setStart();
	}

	public void addGoalNode(int x, int y, char info) {
		nodes[x][y] = new Node(x, y, info);
		nodes[x][y].setGoal();
	}
}