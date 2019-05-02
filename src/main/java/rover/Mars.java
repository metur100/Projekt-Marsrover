package rover;

import java.util.Map;

import java.util.Map.Entry;
import java.util.Set;



public class Mars {
	
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
		
		if(Rover.checkIfThereIsAnObstacle('f')) return; //Wenn der Rover auf Hinderniss laeuft, dann springen wir zueruck.

		int[] WidthAndHeight = determineMaximumWidthAndHeight(Rover.MARS_MAP.keySet());
		for (int j = 0; j < WidthAndHeight[1]; j++) {
			for (int i = 0; i < WidthAndHeight[0]; i++) {
				//System.out.println(i + "," + j + ": " + getRoverAndObstacles(marsMap, new int[] { i, j }));

				if (getRoverAndObstacles(Rover.MARS_MAP, new int[] { i, j }) == null) {
					System.out.print(" ");
					continue;
				}
				if (getRoverAndObstacles(Rover.MARS_MAP, new int[] { i, j }).equals("#"))
					System.out.print("#");
				else if (getRoverAndObstacles(Rover.MARS_MAP, new int[] { i, j }).equals("n"))
					System.out.print("^");
				else if (getRoverAndObstacles(Rover.MARS_MAP, new int[] { i, j }).equals("s"))
					System.out.print("V");
				else if (getRoverAndObstacles(Rover.MARS_MAP, new int[] { i, j }).equals("e"))
					System.out.print(">");
				if (getRoverAndObstacles(Rover.MARS_MAP, new int[] { i, j }).equals("w"))
					System.out.print("<");

			}
			System.out.println();
		}
		for (int i = 0; i < WidthAndHeight[0]; i++) {
			System.out.print("=");
		}
		
		System.out.println();
	}
}
