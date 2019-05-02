package rover;

import java.util.LinkedHashMap;
/**
 * Karte mit Position fuer die Rover-Loesung
 */
public class KarteMitPosition {

    private int KARTE_HOEHE;
    private int KARTE_BREITE;
    private LinkedHashMap<Position, Feldinhalt> MARS_KARTE;

    public KarteMitPosition(int KARTE_BREITE, int KARTE_HOEHE) {
        MARS_KARTE = new LinkedHashMap<>();
        this.KARTE_HOEHE = KARTE_HOEHE;
        this.KARTE_BREITE = KARTE_BREITE;
    }

    public Feldinhalt getPositionMitFeldinhalt(Position position) {
        // Bekommt das Ergebnis.
        Feldinhalt ergebnis = MARS_KARTE.get(position);

        // return leer wenn das Feld leer ist.
        if (ergebnis == null) return Feldinhalt.LEER;

        // return ergebnis
        return ergebnis;
    }

    public void setzePositionUndFeldinhalt(Position position, Feldinhalt inhalt) {
        // Pruefen
        if (!position.pruefeMaxHoeheUndBreite(KARTE_BREITE, KARTE_HOEHE)) throw new IllegalArgumentException("position");

        MARS_KARTE.put(position, inhalt);
    }
    /**
     * @return Entfernt, falls vorhanden, mapping fuer den angegebenen Schluessel aus dieser map.
    */
    public Feldinhalt loescheAltePosition(Position position) {
        // Pruefen
        if (!position.pruefeMaxHoeheUndBreite(KARTE_BREITE, KARTE_HOEHE)) throw new IllegalArgumentException("position");
        
        return MARS_KARTE.remove(position); 
    }

    public void bewegeUndFuegeNeuePositionEin(Position altePosition, Position neuePosition) {
        // Pruefen
        if (!altePosition.pruefeMaxHoeheUndBreite(KARTE_BREITE, KARTE_HOEHE)) throw new IllegalArgumentException("altePosition");
        if (!neuePosition.pruefeMaxHoeheUndBreite(KARTE_BREITE, KARTE_HOEHE)) throw new IllegalArgumentException("neuePosition");

        Feldinhalt inhalt = loescheAltePosition(altePosition);
        if (inhalt != null)
        	setzePositionUndFeldinhalt(neuePosition, inhalt);
    }

    public boolean isBlockiert(Position position) {
        return getPositionMitFeldinhalt(position) != Feldinhalt.LEER;
    }
    
}