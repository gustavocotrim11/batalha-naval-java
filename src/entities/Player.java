package entities;

import java.util.Random;
import java.util.Scanner;

public class Player extends Board {
    private final Random random;
    char option = ' ';

    public Player() {
        super();
        this.name = "JOGADOR";
        this.random = new Random();
    }

    public void buildPlayerBoard (Scanner sc) {
        this.printBoard();
        System.out.println("Você deve posicionar 10 navios no tabuleiro através das coordenadas");

        while(this.ships < 10) {

            int iChar;

            System.out.printf("%sº navio a ser posicionado \nFaltam %s",this.ships+1,10-this.ships);
            System.out.print("\nDigite o par Linha e Coluna do Tabuleiro. Ex: A1, B0, C3: ");
            String temp = sc.next();

            char auxiliar = Character.toUpperCase(temp.charAt(0));
            iChar = this.charToInt(auxiliar);

            int jChar = Character.getNumericValue(temp.charAt(1));

            if (iChar >= 0 && iChar <=9 && jChar >= 0 && jChar <= 9 && this.getBoardMatrixPoint(iChar, jChar) == ' ') {
                setBoardMatrixPoint (iChar, jChar, 'N');
                this.ships++;
            } else {
                System.out.println("Você digitou as coordenadas incorretamente, tente novamente.");
            }
            this.printBoard();
        }
    }

    public void autoBuildPlayerBoard(Scanner sc) {

        System.out.print("\nPressione a letra 'M' para posicionar os navios Manualmente ou a letra 'A' para posicionar Automaticamente: ");
        option = sc.next().charAt(0);

        if (option == 'A') {
            while (this.ships < 10) {
                int i = this.random.nextInt(10);
                int j = this.random.nextInt(10);

                if (this.getBoardMatrixPoint(i, j) == ' ') {
                    setBoardMatrixPoint(i, j, 'N');
                    this.ships++;
                }
            }
        } else if(option == 'M') {
            buildPlayerBoard(sc);
        } else {
            System.out.println("\nDigitação Errada: Você não digitou 'A' ou 'M'. Por favor, digite uma destas letras.");
        }

    }

    public void playerShoot (Bot bot, Scanner sc) {
        boolean failed = false;
        boolean alreadyShot = false;

        do {
            System.out.print("Digite o par Linha/Coluna que quer atacar: ");
            String temp = sc.next();

            char auxiliar = Character.toUpperCase(temp.charAt(0));
            int iChar = this.charToInt(auxiliar);

            int jChar = Character.getNumericValue(temp.charAt(1));

            if (iChar < 0 || iChar > 9 || jChar < 0 || jChar > 9) {
                failed = true;
                System.out.println("\nPoxa, você errou as coordenadas, vamos tentar novamente...");
                continue;
            }

            if (this.getBoardMatrixPoint(iChar, jChar) != 'N' && this.getBoardMatrixPoint(iChar, jChar) != ' ') {
                alreadyShot = true;
                System.out.println("Você já atirou nessas coordenadas, vamos tentar novamente...");
            } else {
                boolean thereShip = this.isThereShip(iChar, jChar);
                char result = bot.shoot(iChar, jChar, thereShip);
                this.setBoardMatrixPoint(iChar, jChar, result);

                alreadyShot = false;
            }

        }
        while (failed || alreadyShot);
    }
}


//    public void playerShoot (Bot bot, Scanner sc) {
//        boolean failed = false;
//        boolean alreadyShot = false;
//
//        do {
//            System.out.print("Linha A, B, C, D, E, F, G, H, I, ou J: ");
//            char iChar = Character.toUpperCase(sc.next().charAt(0));
//            int i = this.charToInt(iChar);
//
//            System.out.println();
//
//            System.out.print("Coluna 0, 1, 2, 3, 4, 5, 6, 7, 8, ou 9: ");
//            int j = sc.nextInt();
//
//            System.out.println();
//
//            if (i < 0 || i > 9 || j < 0 || j > 9) {
//                failed = true;
//                System.out.println("Poxa, você errou as coordenadas, vamos tentar novamente...");
//                continue;
//            }
//
//            if (this.getBoardMatrixPoint(i, j) != 'N' && this.getBoardMatrixPoint(i, j) != ' ') {
//                alreadyShot = true;
//                System.out.println("Você já atirou nessas coordenadas, vamos tentar novamente...");
//            } else {
//                boolean thereShip = this.isThereShip(i, j);
//                char result = bot.shoot(i, j, thereShip);
//                this.setBoardMatrixPoint(i, j, result);
//
//                alreadyShot = false;
//            }
//
//
//        }
//        while (failed || alreadyShot);
//    }

//    public void buildPlayerBoard (Scanner sc) {
//        System.out.println("Você deve posicionar 10 navios no tabuleiro através das coordenadas");
//
//        while(this.ships < 10) {
//            this.printBoard();
//
//            int i;
//
//            System.out.print("Digite a linha da coordenada (A, B, C, D, E, F, G, H, I, ou J): ");
//            char iChar = Character.toUpperCase(sc.next().charAt(0));
//
//            System.out.println();
//
//            i = this.charToInt(iChar);
//
//            System.out.print("Digite a coluna da coordenada (0, 1, 2, 3, 4, 5, 6, 7, 8, ou 9): ");
//            int j = sc.nextInt();
//
//            System.out.println();
//
//            if (i >= 0 && i <=9 && j >= 0 && j <= 9 && this.getBoardMatrixPoint(i, j) == ' ') {
//                setBoardMatrixPoint (i, j, 'N');
//                this.ships++;
//            } else {
//                System.out.println("Você digitou as coordenadas incorretamente, tente novamente.");
//            }
//        }
//    }
