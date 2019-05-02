package rover;

public class Test {
	public static void main(String[] args) {
		
		if (args.length > 1) {
			long seed = Long.parseLong(args[1]);
			Rover.ZUFALL_ZAHL.setSeed(seed);
		}		
		Rover.erstelleKarteUndSetzeRover();
		String commands = args[0];
		Rover.printKarte();
		
		for(char befehle: commands.toCharArray()) {
			Rover.behandleBefehle(Befehle.vonChar(befehle));
			Rover.printKarte();
		}
	}
}
