package JavaGame;

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
