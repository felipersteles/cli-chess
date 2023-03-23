package board;

public class Piece {
    protected Position position;
    private Board board;

    public Piece(Board board) {
        this.board = board;
        position = null; // nao é necessario ja esta subtendito que ele é null
    }

    protected Board getBoard() {
        return this.board;
    }
}
