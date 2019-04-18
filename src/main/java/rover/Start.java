package rover;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

public class Start {

	static Random r = new Random();
	static LinkedHashMap<int[], String> marsMap;

	public static void createMapAndRover () {
		marsMap = new LinkedHashMap<>();
		int FIELD_WIDTH = 80;
		int FIELD_HEIGHT = 20;
		int ROVER_POSITIONX = FIELD_WIDTH / 2;
		int ROVER_POSITIONY = FIELD_HEIGHT / 2;
		for (int i = 0; i < FIELD_WIDTH; i++) {
			for (int j = 0; j < FIELD_HEIGHT; j++) {
				int[] emptyField = new int[] { i, j };
				if (r.nextDouble() < 0.25 && !(ROVER_POSITIONX == i && ROVER_POSITIONY == j))
					marsMap.put(emptyField, "#");
			}
		}
		marsMap.put(new int[] { ROVER_POSITIONX, ROVER_POSITIONY }, "n");
	}

	public static int[] determineMaximumWidthAndHeight (Set<int[]> mapCoordinates) {
		int[] boundaryCoordinates = new int[2];
		for (int[] e : mapCoordinates) {
			if (e[0] > boundaryCoordinates[0])
				boundaryCoordinates[0] = e[0];
			if (e[1] > boundaryCoordinates[1])
				boundaryCoordinates[1] = e[1];
		}
		return boundaryCoordinates;
	}

	public static String getRoverAndObstacles(Map<int[], String> marsObject, int[] currentPosition) {
		Set<Entry<int[], String>> entrySet = marsObject.entrySet();
		for (Entry<int[], String> entry : entrySet) {
			if (entry.getKey()[0] == currentPosition[0] && entry.getKey()[1] == currentPosition[1])
				return entry.getValue();
		}
		return null;
	}

	public static void printoutTheField() {
		// Set<int[]> keySet = marsMap.keySet();
		// for (int[] e : keySet) {
		// if (e[0] == 39 && e[1] == 10)
		// System.err.println(marsMap.get(e) + " " + e.hashCode());
		// }

		int[] WidthAndHeight = determineMaximumWidthAndHeight(marsMap.keySet());
		for (int j = 0; j < WidthAndHeight[1]; j++) {
			for (int i = 0; i < WidthAndHeight[0]; i++) {
				//System.out.println(i + "," + j + ": " + getRoverAndObstacles(marsMap, new int[] { i, j }));

				if (getRoverAndObstacles(marsMap, new int[] { i, j }) == null) {
					System.out.print(" ");
					continue;
				}
				if (getRoverAndObstacles(marsMap, new int[] { i, j }).equals("#"))
					System.out.print("#");
				else if (getRoverAndObstacles(marsMap, new int[] { i, j }).equals("n"))
					System.out.print("^");
				else if (getRoverAndObstacles(marsMap, new int[] { i, j }).equals("s"))
					System.out.print("V");
				else if (getRoverAndObstacles(marsMap, new int[] { i, j }).equals("e"))
					System.out.print(">");
				if (getRoverAndObstacles(marsMap, new int[] { i, j }).equals("w"))
					System.out.print("<");

			}
			System.out.println();
		}
		for (int i = 0; i < WidthAndHeight[0]; i++) {
			System.out.print("=");
		}
		System.out.println();
	}

	public static void main(String[] args) {

		if (args.length > 1) {
			long seed = Long.parseLong(args[1]);
			r.setSeed(seed);
			// System.out.println("Seed: " + seed);
		}
		createMapAndRover();	
		String directionsOfRover = args[0];
		printoutTheField();
		for (int i = 0; i < directionsOfRover.length(); i++) {
			roverMovement(directionsOfRover.charAt(i));
			printoutTheField();
		}
	}

	public static void roverMovement(char c) {
		if (c == 'f') {
			int[] currentPosition = findRover();
			if (getRoverAndObstacles(marsMap, currentPosition).equals("n"))
				currentPosition[1]--;
			else if (getRoverAndObstacles(marsMap, currentPosition).equals("s"))
				currentPosition[1]++;
			else if (getRoverAndObstacles(marsMap, currentPosition).equals("e"))
				currentPosition[0]++;
			else if (getRoverAndObstacles(marsMap, currentPosition).equals("w"))
				currentPosition[0]--;
		} else if (c == 'b') {
			int[] currentPosition = findRover();
			if (getRoverAndObstacles(marsMap, currentPosition).equals("s"))
				currentPosition[1]--;
			else if (getRoverAndObstacles(marsMap, currentPosition).equals("n"))
				currentPosition[1]++;
			else if (getRoverAndObstacles(marsMap, currentPosition).equals("w"))
				currentPosition[0]++;
			else if (getRoverAndObstacles(marsMap, currentPosition).equals("e"))
				currentPosition[0]--;
		} else if (c == 'l') {
			int[] currentPosition = findRover();
			if (getRoverAndObstacles(marsMap, currentPosition).equals("n"))
				marsMap.put(currentPosition, "w");
			else if (getRoverAndObstacles(marsMap, currentPosition).equals("s"))
				marsMap.put(currentPosition, "e");
			else if (getRoverAndObstacles(marsMap, currentPosition).equals("e"))
				marsMap.put(currentPosition, "n");
			else if (getRoverAndObstacles(marsMap, currentPosition).equals("w"))
				marsMap.put(currentPosition, "s");
		} else if (c == 'r') {
			int[] currentPosition = findRover();
			if (getRoverAndObstacles(marsMap, currentPosition).equals("w"))
				marsMap.put(currentPosition, "n");
			else if (getRoverAndObstacles(marsMap, currentPosition).equals("e"))
				marsMap.put(currentPosition, "s");
			else if (getRoverAndObstacles(marsMap, currentPosition).equals("n"))
				marsMap.put(currentPosition, "e");
			else if (getRoverAndObstacles(marsMap, currentPosition).equals("s"))
				marsMap.put(currentPosition, "w");
		}
	}

	private static int[] findRover() {
		Set<Entry<int[], String>> entrySet = marsMap.entrySet();
		for (Entry<int[], String> entry : entrySet) {
			if (entry.getValue() != null && !entry.getValue().equals("#"))
				return entry.getKey();
		}
		throw new IllegalStateException("Rover missing in action");

	}
}
