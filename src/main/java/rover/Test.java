package rover;

public class Test {
	public static void main(String[] args) {
		
		if (args.length > 1) {
			long seed = Long.parseLong(args[1]);
			Rover.ZUFALL_ZAHL.setSeed(seed);
			// System.out.println("Seed: " + seed);
		}		
		Rover.erstelleKarteUndSetzeRover();
		String directionsOfRover = args[0];
		Rover.printKarte();
		
		for(int i=0; i < directionsOfRover.length(); i++) {
			//Rover.roverMovement(directionsOfRover.charAt(i));
			Rover.printKarte();
		}
	}
}
