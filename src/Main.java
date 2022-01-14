import entities.Bot;
import entities.Player;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        Bot bot = new Bot();
        Player player = new Player();
        boolean gameOver = false;

        System.out.println("Olá desafiante, meu nome é Bot! Está pronto para nossa batalha?");
        System.out.println("Para lhe auxiliar, utilizaremos as marcações abaixo em nosso tabuleiro:\n"
                + " - Navio posicionado N (ene maiúsculo);\n"
                + " - Tiro certeiro * (asterisco);\n"
                + " - Tiro na água – (traço);\n"
                + " - Tiro certeiro com navio posicionado X (xis maiúsculo);\n"
                + " - Tiro na água com navio posicionado n (ene minúsculo)."
        );

        bot.buildBotBoard();
        player.autoBuildPlayerBoard(sc);

        System.out.println("\nParabéns, tudo preenchido! AGORA VAMOS BATALHAR!!\n" +
                "Defina seu tiro digitando as coordenadas Linha e Coluna juntas\n");

        while(!gameOver) {
            System.out.println("Bot: É a sua vez de jogar");
            player.playerShoot(bot, sc);
            player.printBoard();
            bot.printResume("Bot");

            System.out.println();

            System.out.println("Bot: É a minha vez de jogar");
            bot.botShoot(player);
            player.printResume("Player");

            System.out.println();

            if (player.getShips() == 0) {
                gameOver = true;
                System.out.println("Bot: A batalha acabou, você perdeu hahahah");
                System.out.println("Bot: Quem sabe você tenha sorte da próxima vez...");
                bot.printBoard();
                player.printBoard();
            }
            if (bot.getShips() == 0) {
                gameOver = true;
                System.out.println("Bot: A batalha acabou, infelizmente você ganhou...");
                System.out.println("Bot: Voltarei mais forte!");
                bot.printBoard();
                player.printBoard();
            }
        }

        sc.close();
    }
}
