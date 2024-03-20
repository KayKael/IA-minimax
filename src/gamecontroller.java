import java.util.Currency;

public class gamecontroller {
    private player current_player;
    private human_player humanPlayer;
    private ai_player aiPlayer;
    private Board board;
    private Game game;
    private MainPanel mainPanel;

    public gamecontroller(Game game, MainPanel mainPanel) {
        this.game = game;
        this.mainPanel = mainPanel;
    }
    public void makeMove(int row, int col) {
        // Verifica se o movimento é válido
        if (board.isValidMove(row, col)) {
            // Realiza o movimento
            board.makeMove(row, col, current_player.getSymbol());

            // Verifica se o jogo acabou
            if (board.isGameOver()) {
                // Se o jogo terminou, não há mais movimentos
                return;
            }

            // Troca o jogador
            current_player = (current_player == humanPlayer) ? aiPlayer : humanPlayer;

            // Se o próximo jogador for a AI, faz o próximo movimento
            if (current_player instanceof ai_player) {
                makeAImove();
            }
        }
    }


    public void makeAImove(){

    }
    public player getCurrent_player() {
        return current_player;
    }

    public void setCurrent_player(player current_player) {
        this.current_player = current_player;
    }

}
