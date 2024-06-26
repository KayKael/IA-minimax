import java.util.Currency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

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
        this.aiPlayer = game.getAiPlayer();
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

                if (current_player instanceof ai_player) {
                    // Se for a vez da IA, faz o movimento da IA diretamente
                    Move aiMove = makeAImove();
                    game.makeMove(aiMove); // Atualiza o jogo com o movimento da IA
                    if (game.isGameOver()) { // Verifica se o jogo acabou após o movimento da IA
                        return null;
                    }
                    current_player = humanPlayer; // Troca para o próximo jogador após o movimento da IA

                    // Obtém o botão correspondente à jogada da IA
                    SquareButton button = mainPanel.getButton(aiMove.getRow(), aiMove.getCol());
                    // Chama o método updateForAI() para atualizar a aparência do botão na interface gráfica
                    button.updateForAI();

                    // Simula o clique do jogador da IA no botão
                    ActionEvent aiClick = new ActionEvent(button, ActionEvent.ACTION_PERFORMED, "O");
                    for (ActionListener listener : button.getActionListeners()) {
                        listener.actionPerformed(aiClick);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao fazer movimento: " + e.getMessage());
        }
        return null;
    }





    public  Move makeAImove() {

        return aiPlayer.aimakeMove(board);

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
    public player getNextPlayer() {
        player currentPlayer = getCurrent_player();
        if (currentPlayer == getHumanPlayer()) {
            System.out.println("alterei para AI");
            return getAiPlayer();
        } else {
            System.out.println("Alterei para Humano");
            return getHumanPlayer();
        }
    }
    public boolean isGameOver() {
        // O jogo termina se o tabuleiro estiver cheio ou se houver um vencedor
        return board.isFull() || board.checkWinner() != ' ';
    }

}
