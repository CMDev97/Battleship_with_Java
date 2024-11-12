package battleship;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Player player = new Player("Player 1");
        Player player2 = new Player("Player 2");
        GameView gameView = new GameView(scanner);
        GameController gameController = new GameController(player, player2, gameView);

        gameController.startGame();

        scanner.close();
    }

}
