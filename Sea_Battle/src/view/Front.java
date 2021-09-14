package view;

import logic.field.Cell;
import logic.field.Field;
import logic.game.Game;
import java.util.HashMap;
import java.util.Scanner;

import static logic.field.FieldManager.createKey;

public class Front {

    public void print(Field field, Boolean showShips) {
        HashMap<String, Cell> cells = field.getField();
        System.out.println("  ");
        System.out.print(" ");
        for (int i = 0; i < field.getWidth(); i++) {
            System.out.print(" "+i);
        }
        for (int y = 0; y < field.getWidth(); y++) {
            System.out.println();
            System.out.print(y+" ");
            for (int x = 0; x < field.getHeight(); x++) {
                String key = createKey(x,y);

                /*
                О - ship
                - - void
                ▦ - miss
                X - hit
                */

                switch (cells.get(key).getStatus()) {
                    case VOID:
                        System.out.print("- ");
                        break;
                    case SHIP:
                        if (showShips) System.out.print("О ");
                        else System.out.print("- ");//show ships
                        break;
                    case MISS:
                        System.out.print("▦ ");
                        break;
                    case DEAD:
                        System.out.print("X ");
                        break;
                }

            }
        }
    }

    public String askCoordinates(){
        System.out.println();
        System.out.println("enter x coordinate");
        int x = Game.scanner.nextInt();
        System.out.println("enter y coordinate");
        int y = Game.scanner.nextInt();
        String key = createKey(x,y);
        return key;
    }

    public void step(String name) {
        System.out.println();
        System.out.println(name + " move");
    }

    public String startLogin(Scanner scanner) {
        System.out.println("Enter your nick:");
        String nick = scanner.nextLine();
        return nick;
    }

    public void win (String name){
        System.out.println();
        System.out.println(name + "win");
    }
}
