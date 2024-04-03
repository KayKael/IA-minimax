import java.util.*;
import java.util.stream.Collectors;

public class ai_player extends player {
    private Move move;
    private Set<Move> visitedMoves; // Manter um conjunto de movimentos visitados

    public ai_player(char symbol) {
        super(symbol);
        visitedMoves = new HashSet<>();
    }

    @Override
    public Move makeMove(Board board, Move move) {
        return null;
    }



    @Override
    public Move aimakeMove(Board board) {
        Move bestmove = minimax(board, getSymbol());
        visitedMoves.add(bestmove); // Adicionar o melhor movimento à lista de movimentos visitados
        return bestmove;
    }


    public Move minimax(Board board, char playerSymbol) {
        // Verifica se o jogo terminou
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

        // Inicializa a lista de movimentos
        List<Move> moves = new ArrayList<>();

        // Faz uma recursão para todos os movimentos possíveis
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!board.isOccupied(i, j)) {
                    Move move = new Move(i, j);
                    board.makeMove(move, playerSymbol);
                    char nextPlayerSymbol = (playerSymbol == 'X') ? 'O' : 'X'; // Alternar o jogador
                    int score = minimax(board, nextPlayerSymbol).getScore();
                    move.setScore(score);
                    moves.add(move);
                    board.undoMove(); // Desfaz o movimento para simular o próximo movimento
                }
            }
        }

        // Filtra os movimentos válidos
        List<Move> validMoves = moves.stream()
            .filter(move -> !visitedMoves.contains(move))
            .collect(Collectors.toList());

        // Encontra o melhor movimento
        if (!validMoves.isEmpty()) {
            if (playerSymbol == this.getSymbol()) {
                // AIPlayer maximiza o score
                return Collections.max(validMoves, Comparator.comparing(Move::getScore));
            } else {
                // Oponente minimiza o score
                return Collections.min(validMoves, Comparator.comparing(Move::getScore));
            }
        } else {
            // Se não houver movimentos válidos, retorna um movimento inválido
            return new Move(-100); // Valor de score arbitrário para indicar movimento inválido
        }
    }
}

