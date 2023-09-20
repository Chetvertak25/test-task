package Test2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
Игра "Угадай животное", использующая структуру данных - двоичное дерево.
*/
class TreeNode {
    private String question;      // Вопрос
    private TreeNode yesNode;     // Узел ответа да
    private TreeNode noNode;      // Узел ответа нет

    public TreeNode(String question) {
        this.question = question;
        this.yesNode = null;
        this.noNode = null;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public TreeNode getYesNode() {
        return yesNode;
    }

    public void setYesNode(TreeNode yesNode) {
        this.yesNode = yesNode;
    }

    public TreeNode getNoNode() {
        return noNode;
    }

    public void setNoNode(TreeNode noNode) {
        this.noNode = noNode;
    }
}

public class Game {
    private TreeNode rootNode;                   // Корневой узел

    public Game() {
        knowledgeBase();
    }

    private void knowledgeBase() {                                                     // Начальная база знаний
        TreeNode questionNode = new TreeNode("Живет на суше?");
        TreeNode catNode = new TreeNode("кот");
        TreeNode whaleNode = new TreeNode("кит");

        rootNode = questionNode;
        questionNode.setYesNode(catNode);
        questionNode.setNoNode(whaleNode);
    }

    public void play() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));       // Чтение с консоли

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

    public static void main(String[] args) throws Exception {
        System.out.println("Загадай животное, а я попробую угадать...");
        Game game = new Game();
        game.play();
    }
}
