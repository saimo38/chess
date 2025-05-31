public class Move {
    private int fromRow;
    private int fromCol;
    private int toRow;
    private int toCol;
    private boolean enPassant = false;
    private boolean castleKingSide = false;
    private boolean castleQueenSide = false;

    public boolean isCastleKingSide() {
        return castleKingSide;
    }

    public void setCastleKingSide(boolean castleKingSide) {
        this.castleKingSide = castleKingSide;
    }

    public boolean isCastleQueenSide() {
        return castleQueenSide;
    }

    public void setCastleQueenSide(boolean castleQueenSide) {
        this.castleQueenSide = castleQueenSide;
    }

    public Move(int fromRow, int fromCol, int toRow, int toCol) {
        this.fromRow = fromRow;
        this.fromCol = fromCol;
        this.toRow = toRow;
        this.toCol = toCol;
    }

    public boolean isEnPassant() {
        return enPassant;
    }

    public void setEnPassant(boolean enPassant) {
        this.enPassant = enPassant;
    }

    public int getFromCol() {
        return fromCol;
    }

    public int getFromRow() {
        return fromRow;
    }

    public int getToCol() {
        return toCol;
    }

    public int getToRow() {
        return toRow;
    }
}
