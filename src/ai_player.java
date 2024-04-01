import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ai_player extends player{
    private Move move;
    public ai_player(char symbol) {
        super(symbol);
    }

    @Override
    public Move makeMove(Board board, Move bestmove) {
        bestmove = minimax(board,getSymbol());
        return bestmove;
    }


    public Move minimax(Board board, char playerSymbol) {
        System.out.println("O minimax ta correndo");
        // Verifique se o jogo terminou
        if (board.isGameOver()) {
            char winner = board.checkWinner();
            if (winner == this.getSymbol()) {
                return new Move(10); // Pontuação alta para a vitória do AIPlayer
            } else if (winner != ' ') {
                return new Move(-10); // Pontuação baixa para a derrota do AIPlayer
            } else {
                return new Move(0); // Empate
            }
        }

        // Inicialize a lista de movimentos
        List<Move> moves = new ArrayList<>();

        // Faça uma recursão para todos os movimentos possíveis
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!board.isOccupied(i, j)) {
                    Move move = new Move(i, j);
                    board.makeMove(move, playerSymbol);
                    char nextPlayerSymbol = (playerSymbol == 'X') ? 'O' : 'X'; // Alternar o jogador
                    move.setScore(minimax(board, nextPlayerSymbol).getScore());
                    moves.add(move);
                    board.undoMove(); // Desfaz o movimento para simular o próximo movimento
                }
            }
        }

        // Encontre o melhor movimento
        if (playerSymbol == this.getSymbol()) {
            return Collections.max(moves); // AIPlayer maximiza o score
        } else {
            return Collections.min(moves); // Oponente minimiza o score
        }

    }
    }
