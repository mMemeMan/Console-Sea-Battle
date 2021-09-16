package logic.ships;

import logic.field.Cell;
import logic.field.Status;

import java.util.ArrayList;

public abstract class Ship {
    protected ArrayList<Cell> cells = new ArrayList();

    public ArrayList<Cell> getCells() {
        return new ArrayList<>(cells);
    }

    public void addCell(Cell cell) {
        cells.add(cell);
    }

    //checking if the ship is alive
    public boolean doUDead() {
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i).getStatus() != Status.DEAD) {
                return false;
            }
        }
        return true;
    }
}
