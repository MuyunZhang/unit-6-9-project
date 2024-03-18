public class Main {
    public static void main(String[] args) {
        String hi = "wooo";
        String RED = "\033[0;31m";
        String WHITE = "\033[0;37m";
        String sub = RED + hi.substring(0, 1);
        System.out.println(sub);
        System.out.println("hii");
    }
}