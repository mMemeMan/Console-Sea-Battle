package logic.field;

public interface Standard {
    int HEIGHT = 10;
    int WIDTH = 10;

    int COUNT_SHIPS = 10;

    //number of ships on the map
    int LINCOR = 1;
    int CRUISER = 2;
    int DESTROYER = 3;
    int BOAT = 4;

    int getCountLincor();
    int getCountCruiser();
    int getCountDestroyer();
    int getCountBoat();
}
