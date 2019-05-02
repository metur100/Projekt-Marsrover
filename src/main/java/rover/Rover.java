package rover;

import java.util.LinkedHashMap;

import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;

public class Rover {

	static Random RANDOM_NUMBER = new Random();
	static LinkedHashMap<int[], String> MARS_MAP;
	static int FIELD_WIDTH = 80;
	static int FIELD_HEIGHT = 20;
	static int ROVER_POSITIONX = FIELD_WIDTH / 2;
	static int ROVER_POSITIONY = FIELD_HEIGHT / 2;

	public static void createMapAndRover () {
		MARS_MAP = new LinkedHashMap<>();
		for (int i = 0; i < FIELD_WIDTH; i++) {
			for (int j = 0; j < FIELD_HEIGHT; j++) {
				int[] obstacles = new int[] { i, j };
				if (RANDOM_NUMBER.nextDouble() < 0.25 && !(ROVER_POSITIONX == i && ROVER_POSITIONY == j))
					//Die Karte mit den Hindernissen wurde hier erzeugt
					MARS_MAP.put(obstacles, "#");
			}
		}
		//Der Rover wird in der Mitte erzeugt und zeigt auf Nord (n)
		MARS_MAP.put(new int[] { ROVER_POSITIONX, ROVER_POSITIONY }, "n");
	}
	
	public static void roverMovement(char befehl) {
		if (befehl == 'f') {
			int[] currentPosition = findRover();
			if (Mars.getRoverAndObstacles(MARS_MAP, currentPosition).equals("n"))
				currentPosition[1]--;
			else if (Mars.getRoverAndObstacles(MARS_MAP, currentPosition).equals("s"))
				currentPosition[1]++;
			else if (Mars.getRoverAndObstacles(MARS_MAP, currentPosition).equals("e"))
				currentPosition[0]++;
			else if (Mars.getRoverAndObstacles(MARS_MAP, currentPosition).equals("w"))
				currentPosition[0]--;
		} else if (befehl == 'b') {
			int[] currentPosition = findRover();
			if (Mars.getRoverAndObstacles(MARS_MAP, currentPosition).equals("s"))
				currentPosition[1]--;
			else if (Mars.getRoverAndObstacles(MARS_MAP, currentPosition).equals("n"))
				currentPosition[1]++;
			else if (Mars.getRoverAndObstacles(MARS_MAP, currentPosition).equals("w"))
				currentPosition[0]++;
			else if (Mars.getRoverAndObstacles(MARS_MAP, currentPosition).equals("e"))
				currentPosition[0]--;
		} else if (befehl == 'l') {
			int[] currentPosition = findRover();
			if (Mars.getRoverAndObstacles(MARS_MAP, currentPosition).equals("n"))
				MARS_MAP.put(currentPosition, "w");
			else if (Mars.getRoverAndObstacles(MARS_MAP, currentPosition).equals("s"))
				MARS_MAP.put(currentPosition, "e");
			else if (Mars.getRoverAndObstacles(MARS_MAP, currentPosition).equals("e"))
				MARS_MAP.put(currentPosition, "n");
			else if (Mars.getRoverAndObstacles(MARS_MAP, currentPosition).equals("w"))
				MARS_MAP.put(currentPosition, "s");
		} else if (befehl == 'r') {
			int[] currentPosition = findRover();
			if (Mars.getRoverAndObstacles(MARS_MAP, currentPosition).equals("w"))
				MARS_MAP.put(currentPosition, "n");
			else if (Mars.getRoverAndObstacles(MARS_MAP, currentPosition).equals("e"))
				MARS_MAP.put(currentPosition, "s");
			else if (Mars.getRoverAndObstacles(MARS_MAP, currentPosition).equals("n"))
				MARS_MAP.put(currentPosition, "e");
			else if (Mars.getRoverAndObstacles(MARS_MAP, currentPosition).equals("s"))
				MARS_MAP.put(currentPosition, "w");
		}
	}
	public static boolean checkIfThereIsAnObstacle(char befehl){	//Wenn der Rover auf Hinderniss laeuft, dann printen wir das Feld nicht mehr aus
        int[] currentPosition = findRover();				//Somit verschwindet er nicht mehr
        if (!Mars.getRoverAndObstacles(Rover.MARS_MAP, currentPosition).equals("#")){
        	return false;
        }
        return true;
	}

	private static int[] findRover() {
		Set<Entry<int[], String>> entrySet = Rover.MARS_MAP.entrySet();
		for (Entry<int[], String> entry : entrySet) {
			if (entry.getValue() != null && !entry.getValue().equals("#"))
				return entry.getKey();
		}
		throw new IllegalStateException("Rover missing in action");

	}

}
