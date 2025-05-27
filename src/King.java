public class King extends Piece {
    public King(boolean white) {
        super(white);
    }

    @Override
    public String getSvgPath() {
        return white ? "/images/white_king.svg" : "/images/black_king.svg";
    }

    @Override
    public String getType() {
        return "King";
    }
}
