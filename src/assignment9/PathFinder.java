package assignment9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 * Class for solving mazes
 * 
 * @author Emily Dennis, Johnny Le
 *
 */
public class PathFinder {

	public static <E> void solveMaze(String inputFile, String outputFile) {

		// Variables to keep track of
		Graph<Node> graph;
		int height = 0;
		int width = 0;

		Node start = null;
		Node current = null;
		Node goal = null;

		LinkedList<Node> queue = new LinkedList<Node>();

		int lineNumber = 0;
		int columnNumber = -1; // Shifted to -1 so that the graph dimensions
								// start at 0

		// Reads the input file

		String file = new String();
		try {
			BufferedReader input = new BufferedReader(new FileReader(inputFile));
			String[] dimensions = input.readLine().split(" ");
			height = Integer.parseInt(dimensions[0]);
			width = Integer.parseInt(dimensions[1]);
			while (input.ready()) {
				file = file + input.readLine() + '\n';
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Change the file to Characters so we can examine each character
		Character[] charFile = toCharacter(file);

		// Construct the graph with the appropriate dimensions
		graph = new Graph<Node>(height, width);

		// Loop through all the characters in the file
		for (int i = 3; i < charFile.length; i++) {

			// Every time we loop through, increment columnNumber
			columnNumber++;

			// If the character is '\n' then increment lineNumber, reset
			// columnNumber to 0
			if (charFile[i].equals('\n')) {
				lineNumber++;
				columnNumber = -1; // Adjusted the frame of columnNumber so that
									// the graph width dimensions begin at 0
									// like lineNumber
			}

			// If the character is a space, add an ordinary node
			else if (charFile[i].equals(' ')) {
				graph.addNode(lineNumber, columnNumber, ' ');
			}

			// If the character is a wall
			else if (charFile[i].equals('X')) {
				graph.addWallNode(lineNumber, columnNumber);
			}

			// If the character is a starting node
			else if (charFile[i].equals('S')) {
				graph.addStartNode(lineNumber, columnNumber, 'S');
				start = graph.nodes[lineNumber][columnNumber];
			}

			// If the character is a goal node
			else if (charFile[i].equals('G')) {
				graph.addGoalNode(lineNumber, columnNumber, 'G');
				goal = graph.nodes[lineNumber][columnNumber];
			}

		}

		// Connect the nodes appropriately once the 2D array has been fully
		// constructed

		start.setVisited();
		current = start;

		// If the node below is not null, then add it to the queue and mark it
		// visited
		if (graph.nodes[current.x + 1][current.y] != null) {
			queue.addFirst(graph.nodes[current.x + 1][current.y]);
			graph.nodes[current.x + 1][current.y].setVisited();
		}

		// If the node to then left is not null, then add it to the queue and
		// mark it visited
		if (graph.nodes[current.x][current.y - 1] != null) {
			queue.addFirst(graph.nodes[current.x][current.y - 1]);
			graph.nodes[current.x][current.y - 1].setVisited();
		}

		// If the node above is not null, then add it to the queue and mark it
		// visited
		if (graph.nodes[current.x - 1][current.y] != null) {
			queue.addFirst(graph.nodes[current.x - 1][current.y]);
			graph.nodes[current.x - 1][current.y].setVisited();
		}

		// If the node to the right is not null, then add it to the queue and
		// mark it visited
		if (graph.nodes[current.x][current.y + 1] != null) {
			queue.addFirst(graph.nodes[current.x][current.y + 1]);
			graph.nodes[current.x][current.y + 1].setVisited();
		}

		// Traverse the queue
		while (queue.size() != 0) {

			// If we've reached the goal, clear the queue so we can exit the
			// loop and print the solution
			if (current == goal) {
				queue.clear();
			}

			else {

				// Set the current node to the node just deleted from the queue
				current = queue.pop();

				// If the node to the left of the current node isn't null and
				// hasn't been visited, add it to the queue, set that it came
				// from current, and set that it was visited
				if (graph.nodes[current.x][current.y - 1] != null
						&& !graph.nodes[current.x][current.y - 1].visited) {
					queue.addLast(graph.nodes[current.x][current.y - 1]);
					graph.nodes[current.x][current.y - 1].setCameFrom(current);
					graph.nodes[current.x][current.y - 1].setVisited();
				}

				// If the node to the right of the current node isn't null and
				// hasn't been visited, add it to the queue, set that it came
				// from current, and set that it was visited
				if (graph.nodes[current.x][current.y + 1] != null
						&& !graph.nodes[current.x][current.y + 1].visited) {
					queue.addLast(graph.nodes[current.x][current.y + 1]);
					graph.nodes[current.x][current.y + 1].setCameFrom(current);
					graph.nodes[current.x][current.y + 1].setVisited();
				}

				// If the node above the current node isn't null and hasn't
				// already been visited, add it to the queue, set that it came
				// from current, and set that it was visited
				if (graph.nodes[current.x - 1][current.y] != null
						&& !graph.nodes[current.x - 1][current.y].visited) {
					queue.addLast(graph.nodes[current.x - 1][current.y]);
					graph.nodes[current.x - 1][current.y].setCameFrom(current);
					graph.nodes[current.x - 1][current.y].setVisited();
				}

				// If the node below the current node isn't null and
				// hasn't been visited, add it to the queue, set that it came
				// from current, and set that it was visited
				if (graph.nodes[current.x + 1][current.y] != null
						&& !graph.nodes[current.x + 1][current.y].visited) {
					queue.addLast(graph.nodes[current.x + 1][current.y]);
					graph.nodes[current.x + 1][current.y].setCameFrom(current);
					graph.nodes[current.x + 1][current.y].setVisited();
				}
			}
		}

		// Traverse the nodes and mark a path with dots as we go

		if (current == goal) {
			current = current.cameFrom;
			while (current != start && current != null) {
				current.data = '.';
				current = current.cameFrom;
			}
		}

		// Creating the output file

		try {
			PrintWriter output = new PrintWriter(new FileWriter(outputFile));

			output.print(height + " " + width);
			for (int i = 0; i < height; i++) {
				output.print("\n");
				for (int j = 0; j < width; j++) {
					if (graph.nodes[i][j] == null) {
						output.print('X');
					} else {
						output.print(graph.nodes[i][j].data);
					}
				}
			}

			output.println();

			output.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Changes the file to an array filled with character objects
	 *
	 * @param file
	 *            - string of the filename
	 * @return Character[] - Character array of the file
	 */
	private static Character[] toCharacter(String file) {
		char[] charFile = file.toCharArray();
		Character[] characterFile = new Character[charFile.length];
		for (int i = 0; i < charFile.length; i++) {
			characterFile[i] = new Character(charFile[i]);
		}
		return characterFile;
	}

}