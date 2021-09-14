package logic.player;

import logic.field.Field;

import static logic.field.FieldManager.createRandomKey;

public class Bot extends Player {

    public Bot(Field field) {
        super(field);
    }

    @Override
    public boolean makeStep(Field field) {
        while (true){
            String key = createRandomKey(field);
            boolean result = field.shot(key);
            if(result == false){
             return false;
            }
            if(result) {
                return true;
            }
        }
    }
}
