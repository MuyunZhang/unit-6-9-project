public class Player extends Space {
    private int score;
    private String name;

    public Player(String name) {
        super(name.substring(0, 1)); // symbol is first initial
        score = 0;
        this.name = name;
    }


    public int getScore() {
        return score;
    }

    public void addPoints(int amt) {
        score += amt;
    }

}