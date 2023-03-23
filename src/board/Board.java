package board;

public class Board {
    private int rows;
    private int columns;
    private Piece[][] pieces; // matrix de peças

    public Board(int rows, int columns) {
        if (rows < 1 || columns < 1)
            throw new BoardException("Erro ao criar tabuleiro: é necessário pelo menos 1 linha e 1 coluna");
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }

    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return this.columns;
    }

    public Piece piece(int row, int column) {
        if (!positionExists(row, column))
            throw new BoardException("Posição não encontrada");

        return pieces[row][column];
    }

    public Piece piece(Position position) {
        return pieces[position.getRow()][position.getColumn()];
    }

    public void placePiece(Piece piece, Position pos) {
        if (thereIsAPiece(pos))
            throw new BoardException("Já existe uma pela nessa posição: " + pos);
        pieces[pos.getRow()][pos.getColumn()] = piece;

        // esse acesso direto só é possivel
        // pois position é protected então
        // como board esta no mesmo pacote
        // ele tem acesso.
        piece.position = pos;
    }

    public Piece removePiece(Position pos) {
        if (!positionExists(pos))
            throw new BoardException("Esta posição não existe no tabuleiro.");

        if (piece(pos) == null)
            return null;

        Piece removedPiece = piece(pos);
        removedPiece.position = null;
        pieces[pos.getRow()][pos.getColumn()] = null;

        return removedPiece;
    }

    private boolean positionExists(int row, int column) {
        // condições:
        // row >= 0 and row < rows
        // column >= 0 and column < columns
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    public boolean positionExists(Position pos) {
        return positionExists(pos.getRow(), pos.getColumn());
    }

    public boolean thereIsAPiece(Position pos) {
        if (!positionExists(pos))
            throw new BoardException("Posição não encontrada");
        return piece(pos) != null;
    }

    @Override
    public String toString() {
        return "{" +
                " rows='" + getRows() + "'" +
                ", columns='" + getColumns() + "'" +
                "}";
    }
}
