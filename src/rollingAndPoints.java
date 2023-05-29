public class rollingAndPoints {

    public static String[][] playerInfoInitializer (int playerTotal, String[] name) {
        String[][] output = new String[playerTotal][4];

        for (int i = 1; i < playerTotal + 1; i++) {
            output[i-1][0] = Integer.toString(i);
        }

        for (int i = 0; i < playerTotal; i++) {
            output[i][1] = name[i];
        }

        for (int i = 0; i < playerTotal; i++) {
            output[i][2] = Integer.toString(0);
            output[i][3] = Integer.toString(0);
        }

        return output;
    }
}