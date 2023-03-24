package chess;

import java.util.ArrayList;
import java.util.List;

import board.Board;
import board.Piece;
import board.Position;
import chess.pieces.King;
import chess.pieces.Rook;

// coracao do nosso
// sistema de xadrez
public class ChessMatch {
    private Board board;
    private int turn;
    private Color currentPlayer;

    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();

    public ChessMatch() {
        board = new Board(8, 8);
        currentPlayer = Color.WHITE;
        turn = 1;
        initialSetup();
    }

    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    // as peças do tipo Piece não são retornadas e sim
    // uma variacao delas, isto acontece para evitar erros
    // durante o desenvolvimento
    public ChessPiece[][] getPieces() {
        ChessPiece[][] matrix = new ChessPiece[board.getRows()][board.getColumns()];

        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                matrix[i][j] = (ChessPiece) board.piece(i, j);
            }
        }

        return matrix;
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();

        validateSourcePosition(source);
        validateTargetPosition(source, target);

        Piece capturedPiece = makeMove(source, target);

        nextTurn();

        return (ChessPiece) capturedPiece;
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition) {
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);

        return board.piece(position).possibleMoves();
    }

    private Piece makeMove(Position sourcePosition, Position targetPosition) {
        Piece capturingPiece = board.removePiece(sourcePosition);
        Piece capturedPiece = board.removePiece(targetPosition);
        board.placePiece(capturingPiece, targetPosition);

        if (capturedPiece != null){
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);}

        return capturedPiece;
    }

    private void validateSourcePosition(Position position) {
        if (!board.thereIsAPiece(position)) {
            throw new ChessException("Não tem peça na posição de origem.");
        }

        if (currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {
            throw new ChessException("A peça escolhida não é do seu time.");
        }

        if (!board.piece(position).isThereAnyPossibleMove()) {
            throw new ChessException("Não existem movimentos possiveis para a peça escolhida.");
        }
    }

    private void validateTargetPosition(Position source, Position target) {
        if (!board.piece(source).possibleMove(target)) {
            throw new ChessException("A peça escolhida nao pode se mover para a posição selecionada.");
        }
    }

    private void nextTurn() {
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
    }

    private void initialSetup() {
        placeNewPiece('b', 6, new Rook(board, Color.WHITE));
        placeNewPiece('c', 4, new Rook(board, Color.WHITE));
        placeNewPiece('d', 4, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new King(board, Color.BLACK));
        placeNewPiece('e', 1, new King(board, Color.WHITE));
    }
}
