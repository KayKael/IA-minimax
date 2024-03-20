import javax.swing.*;

public class GUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Game game = new Game();
            MainPanel mainPanel = new MainPanel(game);
            gamecontroller gameController = new gamecontroller(game, mainPanel);
            JFrame frame = new JFrame("Jogo da Velha");
            frame.setSize(400, 400); // Define largura e altura
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(mainPanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
