import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private Game game;
    private boolean userInputPending = false;
    private SquareButton[][] buttons;
    private Move humanMove;

    private JLabel turnLabel; // Adiciona um JLabel para mostrar o turno e o jogador atual

    public MainPanel(Game game) {
        this.game = game;
        initializeUI();
    }

    public boolean isUserInputPending() {
        return userInputPending;
    }

    public void setUserInputPending(boolean userInputPending) {
        this.userInputPending = userInputPending;
    }

    public void initializeUI() {
        System.out.println("inicializei");
        setLayout(new BorderLayout()); // Altera o layout para BorderLayout
        JPanel boardPanel = new JPanel(new GridLayout(3, 3)); // Cria um painel para o tabuleiro
        turnLabel = new JLabel(); // Inicializa o JLabel
        add(turnLabel, BorderLayout.NORTH); // Adiciona o JLabel acima do tabuleiro
        add(boardPanel, BorderLayout.CENTER); // Adiciona o painel do tabuleiro ao centro

        int buttonSize = 100; // Tamanho desejado para cada botão
        Dimension buttonDimension = new Dimension(buttonSize, buttonSize);
        buttons = new SquareButton[3][3]; // Inicializa o array de botões

        // Cria e adiciona botões para representar os espaços do tabuleiro
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                SquareButton button = new SquareButton(row, col, game);
                button.setPreferredSize(buttonDimension);
                button.addActionListener(e -> {
                    if (!userInputPending) {
                        // Se não estiver aguardando entrada do usuário, não faça nada
                        return;
                    }
                    // Obtém o botão clicado
                    SquareButton clickedButton = (SquareButton) e.getSource();
                    // Cria um objeto de movimento com base na posição do botão clicado
                    humanMove = new Move(clickedButton.getRow(), clickedButton.getCol());
                    // Notifica a thread principal que o movimento humano foi feito
                    synchronized (MainPanel.this) {
                        MainPanel.this.notify();
                    }
                });
                boardPanel.add(button); // Adiciona o botão ao painel do tabuleiro
                buttons[row][col] = button; // Adiciona o botão ao array de botões
            }
        }
    }

    // Método para aguardar a entrada do jogador humano
    public Move waitForHumanMove() throws InterruptedException {
        // Define a entrada do usuário como pendente
        userInputPending = true;
        // Aguarda até que o usuário faça um movimento
        synchronized (this) {
            wait();
        }
        // Retorna o movimento feito pelo jogador humano
        return humanMove;
    }

    public void updateUI() {
        System.out.println("comecei o update");
        SwingUtilities.invokeLater(() -> {
            turnLabel.setText("Turno: " + this.game.getCurrentPlayer().getClass().getSimpleName());
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    SquareButton button = buttons[row][col];
                    button.setEnabled(!game.isGameOver() && game.getCurrentPlayer() instanceof human_player);
                    System.out.println("entrei no for");
                    // Define o texto do botão com base no estado atual do tabuleiro
                    char symbol = game.getBoard().getSymbol(row, col);
                    if (symbol != ' ') {
                        button.setText(Character.toString(symbol));
                    }
                }
            }
        });
    }

    // Método para atualizar o JLabel com o turno e o jogador atual
    public void updateTurnLabel() {
        turnLabel.setText("Turno: " + this.game.getCurrentPlayer().getClass().getSimpleName()); // Atualiza o texto do JLabel com o nome da classe do jogador atual
    }

    public Game getGame() {
        return game;
    }

    public void setButtons(SquareButton[][] buttons) {
        this.buttons = buttons;
    }

    public SquareButton getButton(int row, int col) {
        Component[] components = getComponents();
        for (Component component : components) {
            if (component instanceof SquareButton) {
                SquareButton button = (SquareButton) component;
                System.out.println("Botão na posição [" + button.getRow() + "][" + button.getCol() + "]");
                if (button.getRow() == row && button.getCol() == col) {
                    System.out.println("Botão encontrado na posição [" + row + "][" + col + "]");
                    return button;
                }
            }
        }
        System.out.println("Nenhum botão encontrado na posição [" + row + "][" + col + "]");
        return null;
    }


    public SquareButton[][] getButtons() {
        return buttons;
    }
}
