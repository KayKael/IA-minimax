import java.lang.reflect.WildcardType;

public class Move implements Comparable<Move> {
    private  int row;
    private int col;
    private int score;

    public Move() {
    }

    public Move(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public Move(int row, int col, int score) {
        this.row = row;
        this.col = col;
        this.score = score;
    }

    public Move(int i) {
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getScore() {
        return score;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(Move move) {
        return Integer.compare(this.score, move.score);
    }
}
