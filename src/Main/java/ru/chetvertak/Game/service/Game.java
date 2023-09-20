package Main.java.ru.chetvertak.Game.service;

import Main.java.ru.chetvertak.Game.model.TreeNode;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Game {
    private TreeNode rootNode;                 // Корневой узел
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));       // Чтение с консоли
    public Game() {
        knowledgeBase();
    }

    private void knowledgeBase() {                                               // Начальная база знаний
        TreeNode questionNode = new TreeNode("Живет на суше?");
        TreeNode catNode = new TreeNode("кот");
        TreeNode whaleNode = new TreeNode("кит");

        rootNode = questionNode;
        questionNode.setYesNode(catNode);
        questionNode.setNoNode(whaleNode);
    }

    public void play() throws Exception {

        // Главный цикл, повторяющий игру, пока игрок хочет играть
        do {
            TreeNode currentNode = rootNode;

            // Цикл задающий уточняющие вопросы

            while (currentNode.getYesNode() != null && currentNode.getNoNode() != null) {
                System.out.print(currentNode.getQuestion() + " (да/нет) ");
                String answer = reader.readLine();

                if (answer.equalsIgnoreCase("да")) {
                    currentNode = currentNode.getYesNode();
                } else if (answer.equalsIgnoreCase("нет")) {
                    currentNode = currentNode.getNoNode();
                }
            }
            // Если уточняющие вопросы закончились, есть предпологаемое загаданное животное
            if (currentNode.getYesNode() == null && currentNode.getNoNode() == null) {
                System.out.print("Это " + currentNode.getQuestion() + "? (да/нет) ");
                String finalAnswer = reader.readLine();
                while (!((finalAnswer.equalsIgnoreCase("нет")) || (finalAnswer.equalsIgnoreCase("да")))) {   // Цикл, проверяющий, что ответ либо да либо нет
                    System.out.print("Это " + currentNode.getQuestion() + "? (да/нет) ");
                    finalAnswer = reader.readLine();
                }

                // Если животное не угадано, в базе знаний появляется нвоое животное и его отличительная часть
                if (finalAnswer.equalsIgnoreCase("нет")) {
                    System.out.print("Какое животное ты загадал? ");
                    String animal = reader.readLine().toLowerCase();
                    System.out.print("Чем \"" + animal + "\" отличается от \"" + currentNode.getQuestion() + "\"? ");
                    String difference = reader.readLine().toLowerCase();
                    difference = difference.substring(0, 1).toUpperCase() + difference.substring(1);

                    currentNode.setYesNode(new TreeNode(animal));
                    currentNode.setNoNode(new TreeNode(currentNode.getQuestion()));

                    currentNode.setQuestion(difference + "?");
                } else {
                    System.out.println("Угадал!");
                }
            }

            System.out.print("Играем еще? (да/нет) ");
            String playAgain = reader.readLine();
            while (!((playAgain.equalsIgnoreCase("нет")) || (playAgain.equalsIgnoreCase("да")))) {    // Цикл, проверяющий, что ответ либо да либо нет
                System.out.print("Играем еще? (да/нет) ");
                playAgain = reader.readLine();
            }

            if (!playAgain.equalsIgnoreCase("да")) {
                break;
            }
        } while (true);

        reader.close();
    }




}
