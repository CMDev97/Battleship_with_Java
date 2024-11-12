package battleship;

public class Player extends Grid {
    private final String name;


    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
