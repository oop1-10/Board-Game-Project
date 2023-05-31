import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MainUI implements ActionListener {
    public static int currentPlayer = 1;
    static Font largeFont = new Font(null, Font.PLAIN, 25);
    static Font boldFont = new Font(null, Font.BOLD, 25);
    static JFrame frame = new JFrame();
    public static JTextArea[] squares = new JTextArea[24];
    static JTextArea notification = new JTextArea();
    JButton roll = new JButton("Roll");
    public static int winner = 0;
    static JTextArea[] names = new JTextArea[indicatePlayers.getPlayerNum()];
    JTextArea namesText = new JTextArea("Names: ");
    JTextArea pointsText = new JTextArea("Points: ");

    MainUI() {
        int x = 10, y = 10;
        for (int i = 0; i < squares.length; i++) {
            squares[i] = new JTextArea();
            if (i < 6) {
                squares[i].setBounds(x, y, 40, 40);
                x += 50;
            } else if (i < 12) {
                squares[i].setBounds(x, y, 40, 40);
                y += 50;
            } else if (i < 18) {
                squares[i].setBounds(x, y, 40, 40);
                x -= 50;
            } else {
                squares[i].setBounds(x, y, 40, 40);
                y -= 50;
            }
            squares[i].setEditable(false);
            squares[i].setLayout(null);
            squares[i].setText(Integer.toString(Main.pointsAmount[i]));
            frame.add(squares[i]);
        }

        for (int i = 0; i < names.length; i++) {
            names[i] = new JTextArea(indicatePlayers.visiblePlayerNames[i]);
            names[i].setBounds(400, (100 * (i + 1)) /2, 120, 30);
            names[i].setFont(largeFont);
            names[i].setEditable(false);
            names[i].setLayout(null);
            frame.add(names[i]);
        }

        namesText.setBounds(400, 20,50,15);
        namesText.setLayout(null);
        namesText.setEditable(false);
        namesText.setVisible(true);

        pointsText.setBounds(500, 20,50,15);
        pointsText.setLayout(null);
        pointsText.setEditable(false);
        pointsText.setVisible(true);

        roll.setFocusable(false);
        roll.setBounds(650, 100, 100, 40);
        roll.addActionListener(this);
        roll.setLayout(null);

        notification.setBounds(600, 400, 200, 40);
        notification.setEditable(false);
        notification.setLayout(null);

        frame.add(roll);
        frame.add(notification);
        frame.add(namesText);
        frame.add(pointsText);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Random rn = new Random();
        // when roll button is clicked, run function
        if (e.getSource()==roll) {
            // calling update board function
            updateBoard(currentPlayer, rn.nextInt(1, 6), indicatePlayers.playerInfo);
            // calling events function to see if player landed on a special tile
            events(currentPlayer, indicatePlayers.playerInfo);
            // when the player number becomes greater than the total amount, it resets it to zero
            if (currentPlayer < indicatePlayers.getPlayerNum()) {
                currentPlayer++;
            } else {
                currentPlayer = 1;
            }
        }
    }

    /**
     * Whenever a player lands on a special tile, this function identifies that, and runs the required external functions
     * @param currentPlayer - this identifies the current player
     * @param input - this is the current data collected
     */
    public static void events (int currentPlayer,String[][] input) {
        // if the player position reaches over 24, the game ends
        if (Integer.parseInt(input[currentPlayer-1][2]) > 24) {
            frame.dispose();
            for (int i = 0; i < indicatePlayers.getPlayerNum(); i++) {
                // chooses the player that reached the end first by selecting the highest player position
                if (Integer.parseInt(indicatePlayers.playerInfo[i][2]) > Integer.parseInt(indicatePlayers.playerInfo[winner][2])) {
                    winner = Integer.parseInt(indicatePlayers.playerInfo[i][2]);
                }
            }
            // runs the ending program
            endGame ending = new endGame();
        } else {
            int currentPos = Integer.parseInt(input[currentPlayer-1][2]);
            // if the player lands on one of these squares, start a minigame or move forward
            if (currentPos == 6) {
                frame.dispose();
                mini9 firstMinigame = new mini9();
            } else if (currentPos == 12) {
                frame.dispose();
                mini13 secondMinigame = new mini13();
            } else if (currentPos == 18){
                frame.dispose();
                mini21 thirdMinigame = new mini21();
            } else if (currentPos == 4) {
                updateBoard(currentPlayer, 2, indicatePlayers.playerInfo);
                notification.setText(indicatePlayers.playerInfo[currentPlayer-1][1] + " landed on a ladder and went 2 spaces forward!");
            } else if (currentPos == 16) {
                updateBoard(currentPlayer, 3, indicatePlayers.playerInfo);
                notification.setText(indicatePlayers.playerInfo[currentPlayer-1][1] + " landed on a ladder and went 3 spaces forward!");
            }  else if (currentPos == 11) {
                updateBoard(currentPlayer, -2, indicatePlayers.playerInfo);
                notification.setText(indicatePlayers.playerInfo[currentPlayer-1][1] + " landed on a snake and went 2 spaces backward!");
            } else if (currentPos == 22) {
                updateBoard(currentPlayer, -4, indicatePlayers.playerInfo);
                notification.setText(indicatePlayers.playerInfo[currentPlayer-1][1] + " landed on a snake and went 4 spaces backward!");
            }
        }
    }

    /**
     * This function is run whenever a player's position changes and updates the game board accordingly
     * @param currentPlayer - this identifies which players position is changing
     * @param newPos - this is the difference in position the player is moving
     * @param input - this is the current data that has been collected
     */
    public static void updateBoard (int currentPlayer, int newPos, String[][] input) {
        // finding initial position
        int currentPos = Integer.parseInt(input[currentPlayer-1][2]);

        // setting old bolded name back to normal
        names[currentPlayer-1].setFont(largeFont);

        // removing old player position display
        squares[currentPos].setText(squares[currentPos].getText().replaceAll(", " + currentPlayer, ""));

        // getting new player position
        input[currentPlayer - 1][2] = Integer.toString(Integer.parseInt(input[currentPlayer - 1][2]) + newPos);

        // adding player number to new position
        squares[Integer.parseInt(input[currentPlayer-1][2])].setText(squares[Integer.parseInt(input[currentPlayer-1][2])].getText() + ", " + currentPlayer);

        // adding notification of player's position change
        notification.setText(input[currentPlayer-1][1] + " moved " + newPos + " spaces.");

        // setting font to bold to emphasize
        names[currentPlayer-1].setFont(boldFont);
    }
}