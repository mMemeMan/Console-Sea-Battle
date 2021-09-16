package logic.game;

import logic.player.Player;
import view.Front;

import java.util.Scanner;

public class Game {
    private Front front = new Front();
    public static Scanner scanner = new Scanner(System.in);
    private static Game game;
    private Player player;
    private Player player1;

    private Game(Player player, Player player1) {
        this.player = player;
        this.player1 = player1;
    }


    //Singleton
    protected static Game getInstance(Player player, Player player1) {
        if (game == null) game = new Game(player, player1);
        return game;
    }

    protected void start() throws InterruptedException {
        String name = front.startLogin(scanner);

        front.print(player1.getClassField(), false);
        front.print(player.getClassField(), true);

        while (true) {
            front.step(name);
            while (true) {
                boolean hit = player.makeStep(player1.getClassField());

                front.print(player1.getClassField(), false);
                front.print(player.getClassField(), true);

                if (!hit) {//if the player missed
                    break;
                }
                //check for victory, only if the player got into the ship
                if (!player1.getClassField().shipsIsAlive()) {
                    front.win(name);
                    System.exit(0);
                }
            }

            front.step("Bot");
            Thread.sleep(2000);
            while (true) {
                boolean hit = player1.makeStep(player.getClassField());

                front.print(player1.getClassField(), false);
                front.print(player.getClassField(), true);

                if (!hit) {
                    break;
                }
                //check for victory, only if the player got into the ship
                if (!player.getClassField().shipsIsAlive()) {
                    front.win("Bot");
                    System.exit(0);
                }
            }
        }
    }
}
