package battleship;

public class GameController {
    private final Player player;
    private final Player opponent;
    private final GameView view;
    private Player currentPlayer;

    public GameController(Player player, Player opponent, GameView view) {
        this.player = player;
        this.opponent = opponent;
        this.view = view;
        this.currentPlayer = player;
    }

    public void startGame() {
        fillGridPlayer(player);
        this.view.showEnterForChangePlayer();
        fillGridPlayer(opponent);
        this.view.showEnterForChangePlayer();
        boolean alreadyGame = true;
        while(alreadyGame) {
            this.view.showGameGrid(this.currentPlayer.getGrid());
            String position = this.view.readPositionPlayer(this.currentPlayer);
            alreadyGame = !this.isEndedGame(position);
            if (alreadyGame) {
                this.view.showEnterForChangePlayer();
                this.currentPlayer = getOpponentPlayer();
            }
        }
    }

    private Player getOpponentPlayer() {
        if (this.currentPlayer.getName().equals(this.opponent.getName())) {
            return this.player;
        } else {
            return this.opponent;
        }
    }

    private boolean isEndedGame(String position) {
        boolean isShot = getOpponentPlayer().isShot(position);
        boolean isSunk = getOpponentPlayer().isSunk(position);
        boolean isLastShip = getOpponentPlayer().isNoShip();

        this.view.showMsgResultMove(isShot, isSunk, isLastShip);

        return isShot && isSunk && isLastShip;
    }

    private void fillGridPlayer(Player player) {
        this.view.showMsgPlayerPlaceShip(player.getName());
        this.view.showEmptySingleGrid();
        for (TypePawn pawn : TypePawn.values()) {
            PositionShip positionShip = this.getAndValidatePosition(player, pawn);
            player.addShip(positionShip);
            this.view.showGripWithMarker(player.getGrid());
        }
    }

    private PositionShip getAndValidatePosition(Player player, TypePawn pawn) {
        this.view.showMsgCoordinate(pawn);
        while (true) {
            try {
                PositionShip ship = this.view.readCoordinatePlace(pawn);
                player.isValidPosition(ship);
                return ship;
            } catch (IllegalStateException e) {
                this.view.showMsgException(e);
            }
        }
    }

}
