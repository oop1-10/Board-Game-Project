import java.util.Random;

public class Main {
    public static Random random = new Random();
    public static int[] pointsAmount = new int[24];
    public static int firstLadder = random.nextInt(1,3);
    public static int secondLadder = random.nextInt(13,14);
    public static int firstSnake = random.nextInt(10,11);

    public static void main(String[] args) { // this is the main method that just starts the process of calling all the windows and processes
        startGame game = new startGame();
        Random rn = new Random();
        for (int i = 0; i < pointsAmount.length; i++) {
            if (i == 0 || i == 6 || i == 12 || i == 18 || i == firstLadder || i == secondLadder || i == firstSnake) {
                pointsAmount[i] = 0;
            } else {
                pointsAmount[i] = rn.nextInt(-1, 4);
            }
        }
    }
}
