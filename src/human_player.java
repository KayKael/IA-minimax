import java.sql.SQLInvalidAuthorizationSpecException;

public class human_player extends player {
    private Move move;
    public human_player(char symbol) {
        super(symbol);

    }

    @Override
    public Move makeMove(Board board, Move move) {
        board.makeMove(move, getSymbol());
        return move;
    }

}
