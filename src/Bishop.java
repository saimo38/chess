public class Bishop extends Piece {
    public Bishop(boolean white) {
        super(white);
    }

    @Override
    public String getSvgPath() {
        return white ? "/images/white_bishop.svg" : "/images/black_bishop.svg";
    }

    @Override
    public String getType() {
        return "Bishop";
    }
}
