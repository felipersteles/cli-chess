package board;

public class Board {
    private int rows;
    private int columns;
    private Piece[][] pieces; // matrix de peças

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }

    public int getRows() {
        return this.rows;
    }

    @Override
    public String toString() {
        return "{" +
            " rows='" + getRows() + "'" +
            ", columns='" + getColumns() + "'" +
            "}";
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return this.columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public Piece piece(int row, int column) {
        return pieces[row][column];
    }

    public Piece piece(Position position) {
        return pieces[position.getRow()][position.getColumn()];
    }

    public void placePiece(Piece piece, Position pos) {
        pieces[pos.getRow()][pos.getColumn()] = piece;

        // esse acesso direto só é possivel
        // pois position é protected então
        // como board esta no mesmo pacote
        // ele tem acesso.
        piece.position = pos;
    }
}
