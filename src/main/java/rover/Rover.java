package rover;

import java.util.LinkedHashMap;

import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;

public class Rover {

	static Random ZUFALL_ZAHL = new Random();
	static KarteMitPosition MARS_KARTE;
	static int KARTE_BREITE = 80;
	static int KARTE_HOEHE = 20;
	static Position ROVER_POSITION;
	
    public static void erstelleKarteUndSetzeRover() {
        MARS_KARTE = new KarteMitPosition (KARTE_BREITE, KARTE_HOEHE);
        ROVER_POSITION = new Position(
                KARTE_BREITE / 2,
                KARTE_HOEHE / 2);

        for (int x = 0; x < KARTE_BREITE; x++) {
            for (int y = 0; y < KARTE_HOEHE; y++) {
                Position p = new Position(x, y);
                if (ZUFALL_ZAHL.nextDouble() < 0.25 &&
                        !(ROVER_POSITION.getX() == x && ROVER_POSITION.getY() == y))
                    MARS_KARTE.setzePositionUndFeldinhalt(p, Feldinhalt.HINDERNIS);
            }
        }

        MARS_KARTE.setzePositionUndFeldinhalt(ROVER_POSITION, Feldinhalt.ROVER_NORD);
    }
	public static Feldinhalt rechtsAbbiegen(Feldinhalt roverRichtung) {
        switch (roverRichtung) {
            case ROVER_NORD:
                return Feldinhalt.ROVER_OST;

            case ROVER_OST:
                return Feldinhalt.ROVER_SUED;

            case ROVER_SUED:
                return Feldinhalt.ROVER_WEST;

            case ROVER_WEST:
                return Feldinhalt.ROVER_NORD;

            default:
                throw new IllegalArgumentException("roverRichtung");
        }
    }

    /**
     * Biege links von angegebenen Richtung.
     * @param richtung aktuelle Richtung als Feldinhalt
     * @return neue Richtung als Feldinhalt
     */
    public static Feldinhalt linksAbbiegen(Feldinhalt roverRichtung) {
        switch (roverRichtung) {
            case ROVER_NORD:
                return Feldinhalt.ROVER_WEST;

            case ROVER_OST:
                return Feldinhalt.ROVER_NORD;

            case ROVER_SUED:
                return Feldinhalt.ROVER_OST;

            case ROVER_WEST:
                return Feldinhalt.ROVER_SUED;

            default:
                throw new IllegalArgumentException("roverRichtung");
        }
    }
    public static void printKarte () {
        for (int y = 0; y < KARTE_HOEHE; y++) {
            for (int x = 0; x < KARTE_BREITE; x++) {
                Feldinhalt inhalt = MARS_KARTE.getPositionMitFeldinhalt(new Position(x, y));
                System.out.print(inhalt.getPrintChar());
            }
            System.out.println();
        }
        for (int x = 0; x < KARTE_BREITE; x++) {
            System.out.print("=");
        }
        System.out.println();
    }
}
