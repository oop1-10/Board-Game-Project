import java.util.Random;

public class Main {
    public static int[] pointsAmount = new int[24];

    public static void main(String[] args) { // this is the main method that just starts the process of calling all the windows and processes
        startGame game = new startGame();
        Random rn = new Random();
        for (int i = 0; i < pointsAmount.length; i++) {
            pointsAmount[i] = rn.nextInt(-3, 3);
        }
    }
}
