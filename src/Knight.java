public class Knight extends Piece {
    public Knight(boolean white) {
        super(white);
    }

    @Override
    public String getSvgPath() {
        return white ? "/images/white_knight.svg" : "/images/black_knight.svg";
    }

    @Override
    public String getType() {
        return "Knight";
    }
}
