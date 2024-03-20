import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private Game game;

    public MainPanel(Game game) {
        this.game = game;
        initializeUI();
    }

    public void initializeUI() {
        setLayout(new GridLayout(3, 3)); // Define um layout de grade 3x3 para o tabuleiro
        int buttonSize = 100; // Tamanho desejado para cada botão
        Dimension buttonDimension = new Dimension(buttonSize, buttonSize);
        // Cria e adiciona botões para representar os espaços do tabuleiro
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                SquareButton button = new SquareButton(row, col);
                button.setPreferredSize(buttonDimension);
                button.addActionListener(e -> {
                    int buttonRow = ((SquareButton) e.getSource()).getRow();
                    int buttonCol = ((SquareButton) e.getSource()).getCol();
                    if (!game.getBoard().isOccupied(buttonRow, buttonCol)) {
                        game.makeMove(buttonRow, buttonCol);
                        updateUI();
                    }
                });
                add(button);
            }
        }
    }

    public void updateUI() {
        for (Component component : getComponents()) {
            if (component instanceof SquareButton) {
                SquareButton button = (SquareButton) component;
                int row = button.getRow();
                int col = button.getCol();
                button.setText(String.valueOf(game.getBoard().getSymbol(row, col)));
                button.setEnabled(!game.getBoard().isOccupied(row, col));
            }
        }
    }
}
