public class Board {
    private char[][] grid;

    public Board() {
        grid = new char[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                grid[row][col] = ' ';
            }
        }
    }

    public char getSymbol(int row, int col) {
        return grid[row][col];
    }

    public void makeMove(int row, int col, char symbol) {
        grid[row][col] = symbol;
    }

    public boolean isOccupied(int row, int col) {
        return grid[row][col] != ' ';
    }

    public boolean isGameOver() {
        // Implemente lógica para verificar se o jogo acabou
        return false;
    }

    public boolean isValidMove(int row, int col) {
        // Verifica se a célula está dentro dos limites do tabuleiro
        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            return false;
        }
        // Verifica se a célula está vazia
        return grid[row][col] == ' ';
    }


    // Outros métodos conforme necessário para manipular o tabuleiro
}
