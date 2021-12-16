package entities;

import java.util.Random;

public class Bot extends Board {
    private final Random random;

    public Bot() {
        super();
        this.name = "BOT";
        this.random = new Random();
    }

    public void buildBotBoard () {
        while(this.ships < 10) {
            int i = this.random.nextInt(10);
            int j = this.random.nextInt(10);

            if (this.getBoardMatrixPoint(i, j) == ' ') {
                setBoardMatrixPoint (i, j, 'N');
                this.ships++;
            }
        }
    }

    public void botShoot (Player player) {
        boolean alreadyShot;

        do {
            int i = this.random.nextInt(10);
            int j = this.random.nextInt(10);

            if (this.getBoardMatrixPoint(i, j) != 'N' && this.getBoardMatrixPoint(i, j) != ' ') {
                alreadyShot = true;
            } else {
                boolean thereShip = this.isThereShip(i, j);
                char result = player.shoot(i, j, thereShip);

                this.setBoardMatrixPoint(i, j, result);
                alreadyShot = false;
            }
        }
        while(alreadyShot);

    }
}
