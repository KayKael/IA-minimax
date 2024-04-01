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
        this.board = game.getBoard();
        this.humanPlayer = game.getHumanPlayer();
        this.aiPlayer = game.getAIPlayer();
        this.current_player = humanPlayer; // Definir o jogador atual como o jogador humano
        // Passa a matriz de botões do MainPanel para o gamecontroller
        this.mainPanel.setButtons(mainPanel.getButtons());
    }

    public Move makeMove(Move move) {
        try {
            if (board.isValidMove(move.getRow(), move.getCol())) {
                // Faz o movimento do jogador atual
                game.makeMove(current_player.makeMove(board, move));
                // Verifica se o jogo acabou após o movimento do jogador atual
                if (board.isGameOver()) {
                    return null;
                }
                // Troca para o próximo jogador
                current_player = (current_player == humanPlayer) ? aiPlayer : humanPlayer;
                // Se o próximo jogador for a IA, faz o movimento da IA
                if (current_player instanceof ai_player) {
                    Move aiMove = makeAImove(move);
                    game.makeMove(aiMove);
                    // Verifica se o jogo acabou após o movimento da IA
                    if (board.isGameOver()) {
                        return null;
                    }
                    // Troca para o próximo jogador após o movimento da IA
                    current_player = humanPlayer;
                    // Obtém o botão correspondente à jogada da IA
                    SquareButton button = mainPanel.getButton(aiMove.getRow(), aiMove.getCol());
                    // Chama o método updateForAI() para atualizar a aparência do botão na interface gráfica
                    button.updateForAI();
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao fazer movimento: " + e.getMessage());
        }
        return null;
    }




    private Move makeAImove(Move move) {
     return aiPlayer.makeMove(board,move);
    }

    public player getCurrent_player() {
        System.out.println("o player atual é:"+ current_player);
        return current_player;
    }

    public void setCurrent_player(player current_player) {
        this.current_player = current_player;
    }

    public human_player getHumanPlayer() {
        return humanPlayer;
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

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
}
