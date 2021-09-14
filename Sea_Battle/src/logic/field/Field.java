package logic.field;

import logic.ships.Ship;

import java.util.ArrayList;
import java.util.HashMap;

import static logic.field.FieldManager.createKey;

public class Field implements Standard {

    private HashMap<String, Cell> field = new HashMap(HEIGHT * WIDTH);

    private ArrayList<Ship> ships = new ArrayList(COUNT_SHIPS);

    public HashMap<String, Cell> getField() {
        return field;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public int getWidth() {
        return WIDTH;
    }


    @Override
    public int getCountLincor() {
        return LINCOR;
    }

    @Override
    public int getCountCruiser() {
        return CRUISER;
    }

    @Override
    public int getCountDestroyer() {
        return DESTROYER;
    }

    @Override
    public int getCountBoat() {
        return BOAT;
    }

    //false - miss
    //true - hit
    public boolean shot(String key) {
        if (field.get(key) == null) {
            return Boolean.parseBoolean(null);
        }
        switch (field.get(key).getStatus()) {
            case VOID:
                field.get(key).setStatus(Status.MISS);
                return false;
            case SHIP:
                field.get(key).setStatus(Status.DEAD);
                return true;
            case MISS:
            case DEAD:
                return Boolean.parseBoolean(null);// when re-fired at 1 point
        }
        return true;
    }

    public boolean shipsIsAlive() {
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).doUDead() == false) {
                return true;
            }
        }
        return false;
    }

    //checking if ships are sticking
    public boolean checkNeighbors(String key, ArrayList<String> exceptionsKeys) {
        int x = field.get(key).getX();
        int y = field.get(key).getY();
        //keys of adjacent cells
        ArrayList<String> neighborsKeys = new ArrayList();

        neighborsKeys.add(createKey(x, y + 1));
        neighborsKeys.add(createKey(x + 1, y + 1));
        neighborsKeys.add(createKey(x + 1, y));
        neighborsKeys.add(createKey(x + 1, y - 1));
        neighborsKeys.add(createKey(x, y - 1));
        neighborsKeys.add(createKey(x - 1, y - 1));
        neighborsKeys.add(createKey(x - 1, y));
        neighborsKeys.add(createKey(x - 1, y + 1));

        for (int i = 0; i < neighborsKeys.size(); i++) {
            if (field.get(neighborsKeys.get(i)) != null) {
                boolean result = checkExKeys(neighborsKeys.get(i), exceptionsKeys);
                if (result == false) {
                    if (field.get(neighborsKeys.get(i)).getStatus() != Status.VOID) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean checkExKeys(String key, ArrayList<String> exceptionsKeys) {
        for (int j = 0; j < exceptionsKeys.size(); j++) {
            if (field.get(key).equals(field.get(exceptionsKeys.get(j)))) {
                return true;
            }
        }
        return false;
    }

    public Status getCellStatus(String key) {
        Cell cell = field.get(key);
        Status status = cell.getStatus();
        return status;
    }
}
