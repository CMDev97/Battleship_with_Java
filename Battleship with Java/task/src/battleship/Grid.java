package battleship;



public class Grid {
    private final char[][] grid;
    private int point = 0;

    public Grid() {
        grid = new char[Constant.SIZE][Constant.SIZE];
    }


    public char[][] getGrid() {
        return grid;
    }

    public void addShip(PositionShip ship) {
        ship.getParts()
                .forEach(item -> grid[item[0]][item[1]] = 'O');
        this.point += ship.getParts().size();
    }

    public void isValidPosition(PositionShip ship) {
        boolean hitOrNear = ship.getParts().stream().anyMatch(item -> isHitOrNearHit(item[0], item[1]));
        if (hitOrNear) {
            throw new IllegalStateException("Error! You placed it too close to another one. Try again:");
        }
    }

    public boolean isShot(String positionShot) {
        int[] coordinate = Utility.convertCoordinate(positionShot);
        int row = coordinate[0];
        int col = coordinate[1];

        assert row >= 0 && col >= 0 && col < Constant.SIZE : "Error! You entered the wrong coordinates! Try again:";

        if (grid[row][col] == 'O') {
            grid[row][col] = 'X';
            this.point -= 1;
            return true;
        }
        if (grid[row][col] == 'X') {
            return false;
        }
        grid[row][col] = 'M';
        return false;
    }

    public boolean isSunk(String positionSunk) {
        int[] coordinate = Utility.convertCoordinate(positionSunk);
        int row = coordinate[0];
        int col = coordinate[1];

        return checkDirection(row, col, 0, 1) &&
                checkDirection(row, col, 0, -1) &&
                checkDirection(row, col, 1, 0) &&
                checkDirection(row, col, -1, 0);
    }

    public boolean isNoShip() {
        return this.point == 0;
    }

    private boolean checkDirection(int row, int col, int dRow, int dCol) {
        int rows = grid.length;
        int cols = grid[0].length;

        int r = row, c = col;

        while (r >= 0 && r < rows && c >= 0 && c < cols && grid[r][c] != '\u0000') {
            if (grid[r][c] == 'O') {
                return false;
            }
            r += dRow;
            c += dCol;
        }

        return true;
    }

    private boolean isHitOrNearHit(int row, int col) {
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = row + i;
                int newCol = col + j;

                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                    if (grid[newRow][newCol] == 'O') {
                        return true;
                    }
                }
            }
        }

        return false;
    }

}
