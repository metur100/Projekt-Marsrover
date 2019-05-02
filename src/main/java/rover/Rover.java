package rover;

import java.util.Random;

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
    /**
     * Bewege ein Objekt, das sich an der angegebenen Position befindet. Muss ein Rover am Standort sein.
     *
     * Ueberprueft, dass die Karte nicht verlassen wird und sich nicht in leeren Feldern bewegt.
     * @param position Position zum Ueberpruefen
     * @param nachVorne Sollte sich der Rover vorwaerts (wahr) oder rueckwaerts (falsch) bewegen
     * @return Neue Position oder alte Position, wenn neue Position ungueltig war (blockiert, außerhalb der Karte)
     */
    public static Position bewegeRover (Position position, boolean nachVorne) {
        // Bekommt neue Position wenn sich bewegt
        Position neuePosition = getNeuePosition(position, nachVorne);

        // Pruefe die neue Position
        if (!neuePosition.pruefeMaxHoeheUndBreite(KARTE_BREITE, KARTE_HOEHE)) return position;

        // Pruefen, ob die neue Position blockiert ist
        if (MARS_KARTE.isBlockiert(neuePosition)) return position;

        // Bewege den Rover
        MARS_KARTE.bewegeUndFuegeNeuePositionEin(position, neuePosition);

        // return die neue Position
        return neuePosition;
    }

    /**
     * Bekommt neue Position wenn sich in eine Richtung bewege.
     * @param position Aktuelle position
     * @param forward Vorwaerts oder rueckwaerts bewegen
     * @return neue Position.
     */
    private static Position getNeuePosition(Position position, boolean nachVorne) {
        Feldinhalt roverRichtung = MARS_KARTE.getPositionMitFeldinhalt(position);

        switch (roverRichtung) {
            case ROVER_NORD:
                return new Position(
                        position.getX(),
                        nachVorne ? position.getY() - 1 : position.getY() + 1);

            case ROVER_OST:
                return new Position(
                        nachVorne ? position.getX() + 1 : position.getX() - 1,
                        position.getY());

            case ROVER_SUED:
                return new Position(
                        position.getX(),
                        nachVorne ? position.getY() + 1 : position.getY() - 1);

            case ROVER_WEST:
                return new Position(
                        nachVorne ? position.getX() - 1 : position.getX() + 1,
                        position.getY());

            default:
                throw new IllegalArgumentException("roverRichtung");
        }

    }

    /**
     * Behandle gegebenen Befehle
     * @param Befehl zu behandeln.
     */
    public static void behandleBefehle(Befehle befehle) {
        Feldinhalt roverRichtung = MARS_KARTE.getPositionMitFeldinhalt(ROVER_POSITION);
        switch (befehle) {
            case NACH_VORNE:
                ROVER_POSITION = bewegeRover(ROVER_POSITION, true);
                break;

            case RUECKWAERTS:
                ROVER_POSITION = bewegeRover(ROVER_POSITION, false);
                break;

            case LINKS_ABBIEGEN:
                roverRichtung = linksAbbiegen(roverRichtung);
                MARS_KARTE.setzePositionUndFeldinhalt(ROVER_POSITION, roverRichtung);
                break;
            case RECHTS_ABBIEGEN:
                roverRichtung = rechtsAbbiegen(roverRichtung);
                MARS_KARTE.setzePositionUndFeldinhalt(ROVER_POSITION, roverRichtung);
                break;

            default:
                System.out.println("Unknown command: " + befehle);
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
