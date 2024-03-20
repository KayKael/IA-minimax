public class Game {
    private Board board;
    private player currentPlayer;
    private human_player humanPlayer;
    private ai_player aiPlayer;
    private char x='X';
    private char o='O';

    public Game() {
        this.board = new Board();
        this.humanPlayer = new human_player(x);
        this.aiPlayer = new ai_player(o);
        this.currentPlayer = humanPlayer; // Definir o jogador atual como o jogador humano
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

    public void makeMove(int row, int col) {
        currentPlayer.makeMove(board, row, col);
        currentPlayer = (currentPlayer == humanPlayer) ? aiPlayer : humanPlayer; // Alternar jogador atual
    }
}
