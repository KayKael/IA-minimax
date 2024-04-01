import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private Game game;
    private boolean userInputPending = false;
    private SquareButton[][] buttons;
    private int i;



    public MainPanel(Game game) {
        initializeUI();
       this.game = game;
       updateUI();
    }

    public boolean isUserInputPending() {
        return userInputPending;
    }

    public void setUserInputPending(boolean userInputPending) {
        this.userInputPending = userInputPending;
    }

    public void initializeUI() {
        System.out.println("Inicializei");
        setLayout(new GridLayout(3, 3)); // Define um layout de grade 3x3 para o tabuleiro
        int buttonSize = 100; // Tamanho desejado para cada botão
        Dimension buttonDimension = new Dimension(buttonSize, buttonSize);
        buttons = new SquareButton[3][3]; // Inicializa a matriz de botões

        // Cria e adiciona botões para representar os espaços do tabuleiro
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                SquareButton button = new SquareButton(row, col, game);
                button.setPreferredSize(buttonDimension);
                buttons[row][col] = button; // Adiciona o botão à matriz
                add(button);
            }
        }
    }

    public void updateUI() {
        System.out.println("Atualizando interface gráfica...");
        // Verifica se os botões foram inicializados
        if (buttons != null) {
            // Atualiza o estado dos botões existentes sem remover e adicionar novamente
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    SquareButton button = buttons[row][col];
                    System.out.println("Verificando botão [" + row + "][" + col + "]...");
                    button.setEnabled(!game.isGameOver() && game.getCurrentPlayer() instanceof human_player);
                    // Define o texto do botão com base no estado atual do tabuleiro
                    char symbol = game.getBoard().getSymbol(row, col);
                    if (symbol != ' ') {
                        button.setText(Character.toString(symbol));
                    }
                }
            }
            revalidate(); // Revalida o layout do painel para garantir que as mudanças sejam aplicadas
            repaint(); // Repinta o painel para atualizar a exibição
            System.out.println("Interface gráfica atualizada com sucesso.");
        } else {
            System.out.println("Botões não inicializados. Não é possível atualizar a interface gráfica.");
        }
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
        updateUI(); // Atualiza a interface gráfica após configurar o jogo
    }

    public void setButtons(SquareButton[][] buttons) {
        this.buttons = buttons;
    }

    public SquareButton getButton(int row, int col) {
        Component[] components = getComponents(); // Obtém todos os componentes dentro do painel
        for (Component component : components) {
            if (component instanceof SquareButton) {
                SquareButton button = (SquareButton) component;
                if (button.getRow() == row && button.getCol() == col) {
                    return button; // Retorna o botão correspondente à posição
                }
            }
        }
        return null; // Retorna null se nenhum botão for encontrado para a posição especificada
    }

    public SquareButton[][] getButtons() {
        return buttons;
    }

    public int getI() {
        return i;
    }
}
