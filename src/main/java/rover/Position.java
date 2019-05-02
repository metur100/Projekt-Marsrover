package rover;

/**
 * Position innerhalb der Karte
 */
public class Position {
    private int x;
    private int y;

    public Position (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean pruefeMaxHoeheUndBreite (int maxX, int maxY) {
        return (maxX > x) && (maxY > y) && (x>=0) && (y>=0);
    }

    public int getX() { return x; }
    public int getY() { return y; }

    @Override 
    /**
     * Diese Methode wird zum Vorteil von Hashtabellen unterstuetzt, wie sie von {@link java.util.HashMap} bereitgestellt werden.
     * @return  ein Hash-Code-Wert für dieses Objekt.
     */
    public int hashCode() {
    	 // Der vorzeichenbehaftete Left-Shift-Operator "<<" verschiebt ein Bit-Muster nach links
        return x + (y << 16);
    }

    @Override
    /**
	 * @param   obj das Referenzobjekt, mit dem verglichen werden soll.
	 * @return  {@code true} Wenn dieses Objekt mit dem "obj" identisch ist
	 */
    public boolean equals(Object obj) {
        if (obj == this) return true;

        if (obj instanceof Position) {
            Position other = (Position) obj;
            return
                    other.x == x &&
                    other.y == y;
        }

        return false;
    }
}