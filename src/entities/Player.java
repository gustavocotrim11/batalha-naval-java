package entities;

import java.util.Scanner;

public class Player extends Board {

    public Player() {
        super();
        this.name = "JOGADOR";
    }

    public void buildPlayerBoard (Scanner sc) {
        System.out.println("Você deve posicionar 10 navios no tabuleiro através das coordenadas");

        while(this.ships < 10) {
            this.printBoard();

            int i;

            System.out.print("Digite a linha da coordenada (A, B, C, D, E, F, G, H, I, ou J): ");
            char iChar = Character.toUpperCase(sc.next().charAt(0));

            System.out.println();

            i = this.charToInt(iChar);

            System.out.print("Digite a coluna da coordenada (0, 1, 2, 3, 4, 5, 6, 7, 8, ou 9): ");
            int j = sc.nextInt();

            System.out.println();

            if (i >= 0 && i <=9 && j >= 0 && j <= 9 && this.getBoardMatrixPoint(i, j) == ' ') {
                setBoardMatrixPoint (i, j, 'N');
                this.ships++;
            } else {
                System.out.println("Você digitou as coordenadas incorretamente, tente novamente.");
            }
        }
    }

    public void playerShoot (Bot bot, Scanner sc) {
        boolean failed = false;
        boolean alreadyShot = false;

        do {
            System.out.print("Linha A, B, C, D, E, F, G, H, I, ou J: ");
            char iChar = Character.toUpperCase(sc.next().charAt(0));
            int i = this.charToInt(iChar);

            System.out.println();

            System.out.print("Coluna 0, 1, 2, 3, 4, 5, 6, 7, 8, ou 9: ");
            int j = sc.nextInt();

            System.out.println();

            if (i < 0 || i > 9 || j < 0 || j > 9) {
                failed = true;
                System.out.println("Poxa, você errou as coordenadas, vamos tentar novamente...");
                continue;
            }

            if (this.getBoardMatrixPoint(i, j) != 'N' && this.getBoardMatrixPoint(i, j) != ' ') {
                alreadyShot = true;
                System.out.println("Você já atirou nessas coordenadas, vamos tentar novamente...");
            } else {
                boolean thereShip = this.isThereShip(i, j);
                char result = bot.shoot(i, j, thereShip);
                this.setBoardMatrixPoint(i, j, result);

                alreadyShot = false;
            }


        }
        while (failed || alreadyShot);
    }
}
