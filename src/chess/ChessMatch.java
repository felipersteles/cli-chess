package chess;

import board.Board;

// coracao do nosso
// sistema de xadrez
public class ChessMatch {
    private Board board;

    public ChessMatch() {
        board = new Board(8, 8);
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
}
