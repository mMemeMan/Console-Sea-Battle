package logic.game;

import initialization.Initialization;
import logic.field.Field;
import logic.player.Player;


public class Main {
    public static void main(String[] args) {
        Initialization init = new Initialization();

        //field initialization
        Field fieldPlayer = init.initField();
        Field fieldPlayer1 = init.initField();

        //placement of ships
        init.automaticallyInitShips(fieldPlayer);
        init.automaticallyInitShips(fieldPlayer1);

        //players initialization
        Player player = init.initPlayer(fieldPlayer);
        Player bot = init.initBot(fieldPlayer1);

        Game game = Game.getInstance(player, bot);
        try {
            game.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
