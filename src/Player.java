public class Player extends Space {
    private int score;
    private String name;

    private String color;

    public static final String RESET = "\033[0m";

    public Player(String name, String color) {
        super(name.substring(0, 1) + RESET); // symbol is first initial
        this.color = color;
        score = 0;
        this.name = color + name + RESET;
    }


    public String getName(){
        return name;
    }
    @Override
    public String getSymbol() {
        return color + super.getSymbol();
    }


    public int getScore() {
        return score;
    }

    public void addPoints(int amt) {
        score += amt;
    }
}