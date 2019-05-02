package rover;

public class Test {
	public static void main(String[] args) {
		
		if (args.length > 1) {
			long seed = Long.parseLong(args[1]);
			Rover.RANDOM_NUMBER.setSeed(seed);
			// System.out.println("Seed: " + seed);
		}		
		Rover.createMapAndRover();
		String directionsOfRover = args[0];
		Mars.printoutTheField();
		
		for(int i=0; i < directionsOfRover.length(); i++) {
			Rover.roverMovement(directionsOfRover.charAt(i));
			Mars.printoutTheField();
		}
	}
}
