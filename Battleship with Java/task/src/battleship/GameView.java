package battleship;

import java.util.Scanner;

public class GameView {
    private final Scanner scanner;


    public GameView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showMsgResultMove(boolean isShot, boolean isSunk, boolean isLastShip) {
        if (isShot && isSunk && isLastShip) {
            System.out.println("You sank the last ship. You won. Congratulations!");
        } else if (isShot && isSunk) {
            System.out.println("You sank a ship!");
        } else if (isShot) {
            System.out.println("You hit a ship!");
        } else {
            System.out.println("You missed!");
        }
    }

    public PositionShip readCoordinatePlace(TypePawn pawn) {
        String value = scanner.nextLine();
        return PositionShip.buildWithValidation(value, pawn);
    }

    public void showMsgCoordinate(TypePawn pawn) {
        System.out.printf("Enter the coordinates of the %s (%d cells)%n", pawn.getName(), pawn.getMaxLength());
    }

    public void showMsgException(Exception e) {
        System.out.println(e.getMessage());
    }

    public void showMsgPlayerPlaceShip(String namePlayer) {
        System.out.println( namePlayer + ", place your ships on the game field");
    }

    public void showEnterForChangePlayer() {
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
    }

    public void showGameGrid(char[][] grid) {
        this.showEmptySingleGrid();
        System.out.println("---------------------");
        this.showGripWithMarker(grid);
    }

    public String readPositionPlayer(Player player) {
        System.out.println(player.getName() + ", it's your turn:");
        return scanner.nextLine();
    }

    public void showEmptySingleGrid() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Constant.SIZE+1; i++) {
            if (i == 0) {
                sb.append("  ");
                for (int j = 0; j < Constant.SIZE; j++) {
                    sb.append(j+1).append(" ");
                }
            } else {
                sb.append(Constant.CHARACTERS.get(i-1))
                        .append(" ")
                        .append("~ ".repeat(Constant.SIZE));
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public void showGripWithMarker(char[][] grid){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Constant.SIZE+1; i++) {
            if (i == 0) {
                sb.append("  ");
                for (int j = 0; j < Constant.SIZE; j++) {
                    sb.append(j+1).append(" ");
                }
            } else {
                sb.append(Constant.CHARACTERS.get(i-1)).append(" ");
                for (int j = 0; j < Constant.SIZE; j++) {
                    if (grid[i-1][j] == 'O') {
                        sb.append(grid[i-1][j]).append(" ");
                    } else if (grid[i-1][j] == '\u0000') {
                        sb.append("~ ");
                    } else {
                        sb.append(grid[i-1][j]).append(" ");
                    }
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

}
