package logic.player;

import logic.field.Field;
import view.Front;

public class Player {
    private Field field;

    public Player(Field field) {
        this.field = field;
    }

    public Field getClassField() {
        return field;
    }

    //false - miss
    //true - hit
    public boolean makeStep(Field field) {
        Front front = new Front();
        while (true) {
            String key = front.askCoordinates();
            boolean hit = field.shot(key);
            if(hit == false){
                return false;
            }
            if(hit){
                return true;
            }
        }
    }
}
