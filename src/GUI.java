import javax.swing.*;

public class GUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Game game = new Game(); // Inicializa o jogo
            MainPanel mainpanel = new MainPanel(game); // Inicializa o painel principal com o jogo
            //mainpanel.setGame(game);

            //Cria o frame da interface gráfica
            JFrame frame = createFrame();
            //Adiciona o painel principal ao frame
           frame.getContentPane().add(mainpanel);
             //Torna o frame visível
            frame.setVisible(true);

                // Inicia a thread do jogo após a inicialização da interface gráfica
            startGameThread(new gamecontroller(game, mainpanel));

        });
    }


    private static JFrame createFrame() {
        JFrame frame = new JFrame("Jogo da Velha");
        frame.setSize(400, 400); // Define largura e altura
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        return frame;
    }

    private static void startGameThread(gamecontroller gameController) {
        Thread gameThread = new Thread(() -> {
            while (!gameController.getGame().isGameOver()) {
                player currentPlayer = gameController.getCurrent_player();
                if (currentPlayer instanceof human_player) {
                    human_player currentPlayerHuman = (human_player) currentPlayer;
                    // Verifica se é a vez do jogador humano
                    if (gameController.getCurrent_player() == gameController.getHumanPlayer()) {
                        // Pausa a thread do jogo até que o jogador humano faça seu movimento
                        try {
                            synchronized (gameController.getMainPanel()) {
                                gameController.getMainPanel().wait();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    // Após o jogador humano fazer o movimento, atualiza a interface gráfica
                    // e muda para o próximo jogador
                    Move move = currentPlayerHuman.makeMove(gameController.getBoard(), new Move());
                    gameController.makeMove(move);
                    gameController.getMainPanel().updateUI();
                } else if (currentPlayer instanceof ai_player) {
                    ai_player currentPlayerAI = (ai_player) currentPlayer;
                    // Se for a vez do jogador da IA, ela faz o movimento e o jogo continua
                    Move move = currentPlayerAI.makeMove(gameController.getBoard(), new Move());
                    gameController.makeMove(move);
                    gameController.getMainPanel().updateUI();
                    // Desabilita o botão correspondente à posição escolhida pela IA
                    SquareButton button = gameController.getMainPanel().getButton(move.getRow(), move.getCol());
                    button.setEnabled(false);
                }
                // Troca para o próximo jogador
                gameController.setCurrent_player((currentPlayer == gameController.getHumanPlayer()) ? gameController.getAiPlayer() : gameController.getHumanPlayer());
            }
        });
        gameThread.start();
    }


}
