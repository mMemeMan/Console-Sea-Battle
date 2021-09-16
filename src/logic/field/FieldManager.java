package logic.field;

public class FieldManager {

    //the key is created to navigate the field
    //todo добавить разделитель что бы ключ был более универсален
    public static String createKey(int x, int y) {
        String key = Integer.toString(x) + Integer.toString(y);
        return key;
    }

        public static String createRandomKey(Field field) {
        int randomX = (int) (Math.random() * field.getWidth());
        int randomY = (int) (Math.random() * field.getHeight());
        String key = createKey(randomX, randomY);
        return key;
    }
}
