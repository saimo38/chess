public class Rook extends Piece {
    public Rook(boolean white) {
        super(white);
    }

    @Override
    public String getSvgPath() {
        return white ? "/images/white_rook.svg" : "/images/black_rook.svg";
    }

    @Override
    public String getType() {
        return "Rook";
    }
}
