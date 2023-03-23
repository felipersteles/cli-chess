package chess;

import board.Board;
import board.Position;
import chess.pieces.King;
import chess.pieces.Rook;

// coracao do nosso
// sistema de xadrez
public class ChessMatch {
    private Board board;

    public ChessMatch() {
        board = new Board(8, 8);
        placePieces();
    }

    // as peças do tipo Piece não são retornadas e sim
    // uma variacao delas, isto acontece para evitar erros
    // durante o desenvolvimento
    public ChessPiece[][] getPieces() {
        ChessPiece[][] matrix = new ChessPiece[board.getRows()][board.getColumns()];

        for(int i=0; i<board.getRows(); i++)    {
            for(int j=0; j<board.getColumns(); j++) {   
                matrix[i][j] = (ChessPiece) board.piece(i,j);
            }
        }

        return matrix;
    }

    private void placePieces(){
        board.placePiece(new Rook(board, Color.WHITE), new Position(2, 1));
        board.placePiece(new King(board, Color.BLACK), new Position(0, 4));
        board.placePiece(new King(board, Color.WHITE), new Position(7, 4));
    }
}
