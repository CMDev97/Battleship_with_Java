package battleship;

public enum TypePawn {
    AIRCRAFT("Aircraft Carrier",5),
    BATTLESHIP("Battleship", 4),
    SUBMARINE("Submarine", 3),
    CRUISER("Cruiser", 3),
    DESTROYER("Destroyer", 2);

    private final String name;
    private int maxLength;

    TypePawn(String name, int maxLength) {
        this.name = name;
        this.maxLength = maxLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public String getName() {
        return name;
    }
}
