import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SquareButton extends JButton {
    private final int row;
    private final int col;
    private final Game game;

    public SquareButton(int row, int col, Game game) {
        this.game = game;
        this.row = row;
        this.col = col;

        setPreferredSize(new Dimension(100, 100)); // Tamanho do botão

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lidar com o clique no botão
                if (!game.isGameOver() && game.getCurrentPlayer() instanceof human_player) {
                    // Faz a jogada no jogo
                    Move move = new Move(row, col); // Cria um novo movimento com as coordenadas do botão clicado
                    game.makeMove(move);
                    // Define o texto do botão como "X"
                    setText("X");
                    // Desabilita o botão para evitar cliques adicionais
                    setEnabled(false);
                }
            }
        });
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    public void updateForAI() {
        // Define o texto do botão como "O"
        setText("O");
        // Desabilita o botão para evitar cliques adicionais
        setEnabled(false);
    }
}
