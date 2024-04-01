public class Game {
    private Board board;

    private player currentPlayer;
    private human_player humanPlayer;
    private ai_player aiPlayer;
    private char humanSymbol = 'X';
    private char aiSymbol = 'O';
    private Move move;

    public Game() {

        this.board = new Board();
        this.humanPlayer = new human_player(humanSymbol);
        this.aiPlayer = new ai_player(aiSymbol);
        this.currentPlayer = humanPlayer;
    }

    public Board getBoard() {
        return board;
    }

    public player getCurrentPlayer() {
        return currentPlayer;
    }

    public human_player getHumanPlayer() {
        return humanPlayer;
    }

    public ai_player getAIPlayer() {
        return aiPlayer;
    }

    public boolean makeMove(Move move) {
        // Verifica se a posição é válida e se está vazia
        if (board.isValidMove(move.getRow(), move.getCol()) && !board.isOccupied(move.getRow(), move.getCol())) {
            // Faz a jogada atual
            board.makeMove(move, currentPlayer.getSymbol());
            // Verifica se o jogo acabou
            if (isGameOver()) {
                return true; // Retorna true se o jogo acabou após a jogada
            } else {
                // Alternar para o próximo jogador
                currentPlayer = (currentPlayer == humanPlayer) ? aiPlayer : humanPlayer;
            }
            return false; // Retorna false se o jogo não acabou após a jogada
        }
        return false; // Retorna false se a jogada não foi válida
    }

    public boolean isGameOver() {
        // O jogo termina se o tabuleiro estiver cheio ou se houver um vencedor
        return board.isFull() || board.checkWinner() != ' ';
    }

}
