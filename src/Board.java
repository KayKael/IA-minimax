public class Board {
    private char[][] grid;
    private int lastMoveRow;
    private int lastMoveCol;
    private boolean firstmove = false;
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

    /*public void firstMove(Move move, char symbol) {

        grid[move.getRow()][move.getCol()] = symbol;
        firstmove = true;
    }

     */
    public void makeMove(Move move, char symbol) {
        grid[move.getRow()][move.getCol()] = symbol;
        lastMoveRow = move.getRow();
        lastMoveCol = move.getCol();
    }

    public boolean isOccupied(int row, int col) {
        return grid[row][col] != ' ';
    }

    public boolean isGameOver() {
        return isFull() || checkWinner() != ' '; // O jogo acaba se o tabuleiro estiver cheio ou houver um vencedor
    }

    public boolean isValidMove(int row, int col) {
        // Verifica se a célula está dentro dos limites do tabuleiro
        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            return false;
        }
        // Verifica se a célula está vazia
        return grid[row][col] == ' ';
    }

    public boolean isFull() {
        // Verifica se todas as células do tabuleiro estão ocupadas
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (grid[row][col] == ' ') {
                    return false; // Se houver pelo menos uma célula vazia, o tabuleiro não está cheio
                }
            }
        }
        return true; // Se não houver células vazias, o tabuleiro está cheio
    }

    public char checkWinner() {
        // Verifica linhas
        for (int row = 0; row < 3; row++) {
            if (grid[row][0] == grid[row][1] && grid[row][1] == grid[row][2] && grid[row][0] != ' ') {
                return grid[row][0]; // Se todas as células em uma linha forem iguais e não forem vazias, retornamos o símbolo do jogador vencedor
            }
        }

        // Verifica colunas
        for (int col = 0; col < 3; col++) {
            if (grid[0][col] == grid[1][col] && grid[1][col] == grid[2][col] && grid[0][col] != ' ') {
                return grid[0][col]; // Se todas as células em uma coluna forem iguais e não forem vazias, retornamos o símbolo do jogador vencedor
            }
        }

        // Verifica diagonais
        if (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] && grid[0][0] != ' ') {
            return grid[0][0]; // Verifica diagonal principal
        }
        if (grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0] && grid[0][2] != ' ') {
            return grid[0][2]; // Verifica diagonal secundária
        }

        return ' '; // Se não houver vencedor, retornamos um espaço em branco
    }


    public void undoMove() {
        // Verifica se houve alguma jogada anteriormente
        if (lastMoveRow != -1 && lastMoveCol != -1) {
            // Remove a última jogada feita no tabuleiro
            grid[lastMoveRow][lastMoveCol] = ' ';
            // Reseta as coordenadas da última jogada
            lastMoveRow = -1;
            lastMoveCol = -1;
        }
    }

    public boolean firstMove() {
        return isFirstmove();
    }
    public boolean isFirstmove() {
        return firstmove;
    }

    public void setFirstmove(boolean firstmove) {
        this.firstmove = firstmove;
    }
}
