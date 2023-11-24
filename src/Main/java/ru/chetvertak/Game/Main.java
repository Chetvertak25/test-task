package src.main.java.ru.chetvertak.Game;

import src.main.java.ru.chetvertak.Game.service.Game;

public class Main {
    /*
Игра "Угадай животное", использующая структуру данных - двоичное дерево.
*/

    public static void main(String[] args) throws Exception {
        System.out.println("Загадай животное, а я попробую угадать...");
        Game game = new Game();
        game.play();
    }
}
