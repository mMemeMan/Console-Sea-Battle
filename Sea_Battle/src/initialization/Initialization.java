package initialization;

import logic.field.Cell;
import logic.field.Field;
import logic.field.Status;
import logic.player.Bot;
import logic.player.Player;
import logic.ships.*;
import java.util.ArrayList;
import java.util.HashMap;

import static initialization.Direction.*;
import static logic.field.FieldManager.createKey;
import static logic.field.FieldManager.createRandomKey;

public class Initialization {
    Direction direction;


    public Field initField() {
        Field field = new Field();

        for (int y = 0; y < field.getHeight(); y++) {
            for (int x = 0; x < field.getWidth(); x++) {
                String key = createKey(x,y);
                field.getField().put(key, new Cell(x, y, Status.VOID));
            }
        }

        return field;
    }

    //automatic placement
    public void automaticallyInitShips(Field field) {
        for (int i = 0; i < field.getCountLincor(); i++) {
            while (true) {
                Ship lincor = new Lincor();
                if (randomShip(field, lincor, Lincor.getSIZE())) break;
            }
        }
        for (int i = 0; i < field.getCountCruiser(); i++) {
            while (true) {
                Ship cruiser = new Cruiser();
                if (randomShip(field, cruiser, Cruiser.getSIZE())) break;
            }
        }
        for (int i = 0; i < field.getCountDestroyer(); i++) {
            while (true) {
                Ship destroyer = new Destroyer();
                if (randomShip(field, destroyer, Destroyer.getSIZE())) break;
            }
        }
        for (int i = 0; i < field.getCountBoat(); i++) {
            while (true) {
                Ship boat = new Boat();
                if (randomShip(field, boat, Boat.getSIZE())) break;
            }
        }
    }

    private boolean randomShip (Field field, Ship ship, int size) {
        String key = createRandomKey(field);
        Object randomDirection = direction.getRandomInstance();
        boolean result = putShip(field, ship, key, size, randomDirection);
        if (result) {
            field.getShips().add(ship);
            return true;
        }
        return false;
    }


    //place a ship on the map
    public boolean putShip(Field field, Ship ship, String key, int length, Object direction) {
        HashMap<String, Cell> cells = field.getField();
        ArrayList<String> keys = new ArrayList();
        for (int i = 0; i < length; i++) {
            if (cells.get(key) == null || cells.get(key).getStatus() != Status.VOID || field.checkNeighbors(key, keys) == false) {
                return false;
            }
            keys.add(key);
            int x = cells.get(key).getX();
            int y = cells.get(key).getY();

            if (UP.equals(direction)) {
                y++;
            } else if (RIGHT.equals(direction)) {
                x++;
            } else if (DOWN.equals(direction)) {
                y--;
            } else if (LEFT.equals(direction)) {
                x--;
            }
            key = createKey(x ,y);
        }
        for (int i = 0; i < keys.size(); i++) {
            cells.get(keys.get(i)).setStatus(Status.SHIP);
            ship.addCell(cells.get(keys.get(i)));
        }
        return true;
    }

    public Player initPlayer(Field field) {
        Player player = new Player(field);
        return player;
    }

    public Player initBot(Field field) {
        Player bot = new Bot(field);
        return bot;
    }
}



