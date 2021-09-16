package logic.field;

public class Cell {

    private int x;
    private int y;
    /*
    v - void,
    s - ship,
    d - dead,
    m - miss
     */
    private Status status;

    public Cell(int x, int y,Status status) {
        this.x = x;
        this.y = y;
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
