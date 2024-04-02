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

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setCurrentPlayer(player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setHumanPlayer(human_player humanPlayer) {
        this.humanPlayer = humanPlayer;
    }

    public ai_player getAiPlayer() {
        return aiPlayer;
    }

    public void setAiPlayer(ai_player aiPlayer) {
        this.aiPlayer = aiPlayer;
    }

    public char getHumanSymbol() {
        return humanSymbol;
    }

    public void setHumanSymbol(char humanSymbol) {
        this.humanSymbol = humanSymbol;
    }

    public char getAiSymbol() {
        return aiSymbol;
    }

    public void setAiSymbol(char aiSymbol) {
        this.aiSymbol = aiSymbol;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }
}
