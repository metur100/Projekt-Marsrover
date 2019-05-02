package rover;

public enum Befehle {
    /**
     * Bewege den Rover nach vorne.
     */
    NACH_VORNE('f'),

    /**
     * Bewege den Rover rueckwearts.
     */
     RUECKWAERTS('b'),

    /**
     * Biege den Rover recths ab.
     */
     RECHTS_ABBIEGEN('r'),

    /**
     * Biege den Rover links ab.
     */
     LINKS_ABBIEGEN('l');

    /**
     * Befehlszeichen, das beim Senden eines Befehls verwendet wird.
     */
    private char befehlChar;

    /**
     * Erstellen eine neue Instanz von Befehle.
     * @param befehlChar Character, das zum Uebertragen des Befehls verwendet werden soll.
     */
    Befehle(char befehlChar) {
        this.befehlChar = befehlChar;
    }

    /**
     * Bekommt Befehlszeichen aus dem Befehl.
     * @return Character, das zum Uebertragen des Befehls verwendet werden soll.
     */
    public char getBefehlChar() { return befehlChar; }

    /**
     * Bekommt Befehlsinstanz aus einem Befehlszeichen.
     * @param c Befehl character
     * @return Befehl instance.
     * @throws IllegalArgumentException wenn Zeichen kein gueltiges Befehlszeichen ist.
     */
    public static Befehle vonChar(char c) {
        for (Befehle befehle: Befehle.values()) {
            if (befehle.getBefehlChar() == c) return befehle;
        }

        throw new IllegalArgumentException("befehlChar");
    }
}
