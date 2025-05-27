public class Queen extends Piece {
    public Queen(boolean white) {
        super(white);
    }

    @Override
    public String getSvgPath() {
        return white ? "/images/white_queen.svg" : "/images/black_queen.svg";
    }

    @Override
    public String getType() {
        return "Queen";
    }
}
