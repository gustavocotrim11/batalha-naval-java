package entities;

public class Board {
    private final Character[][] boardMatrix;
    protected int ships;
    protected String name;

    public Board() {
        this.boardMatrix = new Character[10][10];
        this.ships = 0;
    }

    protected Character getBoardMatrixPoint (int i, int j) {
        return this.boardMatrix[i][j] == null ? ' ' : this.boardMatrix[i][j];
    }

    protected void setBoardMatrixPoint (int i, int j, char x) {
        this.boardMatrix[i][j] = x;
    }

    protected boolean isThereShip (int i, int j) {
        return this.getBoardMatrixPoint(i, j) == 'N';
    }

    public int getShips() {
        return ships;
    }

    protected int charToInt (char Ichar) {
        int i = 404;

        switch (Ichar) {
            case 'A': i = 0;
                break;
            case 'B': i = 1;
                break;
            case 'C': i = 2;
                break;
            case 'D': i = 3;
                break;
            case 'E': i = 4;
                break;
            case 'F': i = 5;
                break;
            case 'G': i = 6;
                break;
            case 'H': i = 7;
                break;
            case 'I': i = 8;
                break;
            case 'J': i = 9;
                break;
            default:
                break;
        }

        return i;
    }

    public char shoot (int i, int j, boolean thereShip) {
            if (thereShip) {
                if (this.getBoardMatrixPoint(i, j) == 'N' || this.getBoardMatrixPoint(i, j) == 'n') {
                    this.ships--;
                    System.out.println("Acertou!");
                    return 'X';
                } else {
                    System.out.println("Errou...");
                    return 'n';
                }
            } else {
                if (this.getBoardMatrixPoint(i, j) == 'N'  || this.getBoardMatrixPoint(i, j) == 'n') {
                    this.ships--;
                    System.out.println("Acertou!");
                    return '*';

                } else {
                    System.out.println("Errou...");
                    return '-';
                }
            }
    }

    private void printLineBoard () {
        char[] lines = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

        for (int i = 0; i < 10; i++) {
            System.out.print("| " + lines[i] + " |");

            for (int j = 0; j < 10; j++) {
                char jChar = this.getBoardMatrixPoint(i, j);

                System.out.print(" " + jChar + " |");
            }

            System.out.println();
            System.out.println("---------------------------------------------");
        }
    }

    public void printBoard () {

        System.out.println(
                    "---------------------------------------------\n"
                +   "                   " + this.name + "\n"
                +   "---------------------------------------------\n"
                +   "|   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |\n"
                +   "---------------------------------------------"
        );
        this.printLineBoard();
    }

    public void printResume (String name) {
        System.out.println("Navios restantes (" + name + "): " + this.ships);
    }
}
