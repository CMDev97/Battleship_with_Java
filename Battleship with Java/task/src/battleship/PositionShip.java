package battleship;


import java.util.ArrayList;
import java.util.List;

public class PositionShip {
    private final int startColumn;
    private final int startRow;
    private final int endColumn;
    private final int endRow;

    private PositionShip(int startRow, int startColumn, int endRow, int endColumn) {
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.endRow = endRow;
        this.endColumn = endColumn;
    }

    public int getLength() {
        if (this.startRow != (this.endRow)) {
            return Math.abs(startRow - endRow) + 1;
        }
        return Math.abs(this.endColumn - this.startColumn) + 1;
    }

    public List<int[]> getParts() {
        List<int[]> parts = new ArrayList<>();
        int index1 = this.startColumn;
        int index2 = this.endColumn;
        if (this.startRow != this.endRow) {
            index1 = this.startRow;
            index2 = this.endRow;
        }
        for (int i = 0; i < this.getLength(); i++) {
            int finalIndex = index1 + i;
            if (index1 > index2) {
                finalIndex = index1 - i;
            }
            int[] part = new int[]{this.startRow, finalIndex};
            if (startColumn == endColumn) {
                part = new int[]{finalIndex, startColumn};
            }
            parts.add(part);
        }
        return parts;
    }

    public static PositionShip buildWithValidation(String positionShip, TypePawn pawn) {
        if (positionShip == null  || positionShip.isBlank()) {
            throw new IllegalStateException("Error! Wrong ship location! Try again:");
        }
        String[] coordinate = positionShip.split(" ");
        if (coordinate.length != 2) {
            throw new IllegalStateException("Error! Wrong ship location! Try again:");
        }
        String[] x1Coordinate = Utility.splitCoordinate(coordinate[0]);
        String[] x2Coordinate = Utility.splitCoordinate(coordinate[1]);

        if (x1Coordinate.length != 2 && x1Coordinate.length != x2Coordinate.length) throw new IllegalStateException("Error !");

        int index1 = Constant.CHARACTERS.indexOf(x1Coordinate[0]);
        int index2 = Constant.CHARACTERS.indexOf(x2Coordinate[0]);

        if (index1 == -1 || index2 == -1) throw new IllegalStateException("Error! Wrong ship location! Try again:");

        int startColumn = Integer.parseInt(x1Coordinate[1])-1;
        int endColumn = Integer.parseInt(x2Coordinate[1])-1;

        if (startColumn < 0 || startColumn > Constant.SIZE || endColumn < 0 || endColumn > Constant.SIZE) throw new IllegalStateException("Error !");
        if (startColumn != endColumn && index1 != index2) throw new IllegalStateException("Error! Wrong ship location! Try again:");
        PositionShip position = new PositionShip(index1, startColumn, index2, endColumn);
        if (position.getLength() != pawn.getMaxLength()) {
            String msg = String.format("Error! Wrong length of the %s! Try again", pawn.getName());
            throw new IllegalStateException(msg);
        }
        return position;
    }

}
