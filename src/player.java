public abstract class player {
    private char symbol;
    private Move move;

    public player(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public abstract Move makeMove(Board board, Move move);

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }
}
