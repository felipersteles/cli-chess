package chess;

import board.Position;

public class ChessPosition {
    private char column;
    private int row;

    public ChessPosition(char column, int row) {
        if (column < 'a' || column > 'h' || row < 0 || row > 8)
            throw new ChessException("Erro ao estaciar posição. As possibilidades são de a1 a h8.");

        this.column = column;
        this.row = row;
    }

    public char getColumn() {
        return this.column;
    }

    public void setColumn(char column) {
        this.column = column;
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    protected Position toPosition() {
        return new Position(8 - row, column - 'a');
    }

    protected static ChessPosition fromPosition(Position pos){
        return new ChessPosition((char)('a' + pos.getColumn()), 8 - pos.getRow());
    }

    @Override
    public String toString() {
        return "" + column + row;
    }
}
