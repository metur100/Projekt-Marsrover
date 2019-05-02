package rover;

public enum Feldinhalt {

    LEER(" "),
    HINDERNIS("#"),
    ROVER_NORD("^"),
    ROVER_OST(">"),
    ROVER_SUED("V"),
    ROVER_WEST("<");

    private String PRINT_CHAR;

    Feldinhalt(String PRINT_CHAR) {
        this.PRINT_CHAR = PRINT_CHAR;
    }

    public String getPrintChar() {
        return PRINT_CHAR;
    }
    
}