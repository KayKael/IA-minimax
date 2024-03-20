public abstract class player {
    private char symbol;

    public player(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public abstract void makeMove(Board board, int row, int col);
}
