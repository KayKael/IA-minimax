import javax.swing.*;

public class GUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Game game = new Game(); // Inicializa o jogo
            MainPanel mainPanel = new MainPanel(game); // Inicializa o painel principal com o jogo

            // Cria o frame da interface gráfica
            JFrame frame = createFrame();
            // Adiciona o painel principal ao frame
            frame.getContentPane().add(mainPanel);
            // Torna o frame visível
            frame.setVisible(true);

            if (mainPanel.getButtons() != null) {
                // Inicia o jogo
                gamecontroller gameController = new gamecontroller(game, mainPanel);
                startGame(gameController);
            }
        });
    }

    private static JFrame createFrame() {
        JFrame frame = new JFrame("Jogo da Velha");
        frame.setSize(400, 400); // Define largura e altura
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        return frame;
    }

    private static void startGame(gamecontroller gameController) {
        new Thread(() -> {
            while (!gameController.isGameOver()) {
                player currentPlayer = gameController.getCurrent_player();
                if (currentPlayer instanceof human_player) {
                    // Verifica se é a vez do jogador humano
                    if (gameController.getCurrent_player() == gameController.getHumanPlayer()) {
                        // Aguarda a entrada do jogador humano
                        try {
                            Move move = gameController.getMainPanel().waitForHumanMove();
                            System.out.println("Movimento humano: " + move.getRow() + ", " + move.getCol());
                            SquareButton button = gameController.getMainPanel().getButton(move.getRow(), move.getCol());
                            button.setEnabled(false);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (currentPlayer instanceof ai_player) {
                    // Se for a vez do jogador da IA, ela faz o movimento e o jogo continua
                    Move move = gameController.makeAImove(); // Faz o movimento da IA
                    System.out.println("Movimento da IA: " + move.getRow() + ", " + move.getCol());
                    SquareButton button = gameController.getMainPanel().getButton(move.getRow(), move.getCol());
                    button.setEnabled(false);
                }
                // Troca para o próximo jogador
                gameController.setCurrent_player(gameController.getNextPlayer());
            }
        }).start();
    }


}
