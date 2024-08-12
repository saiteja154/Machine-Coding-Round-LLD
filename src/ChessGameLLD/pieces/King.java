package ChessGameLLD.pieces;

import ChessGameLLD.Board;
import ChessGameLLD.Color;

public class King extends Piece{


    public King(Color color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean canMove(Board board, int destRow, int destCol) {
        return false;
    }
}
