import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MainUI implements ActionListener {
    public static int currentPlayer = 0;
    public static int totalPlayers = indicatePlayers.getPlayerNum();
    static Font largeFont = new Font(null, Font.PLAIN, 25);
    static Font boldFont = new Font(null, Font.BOLD, 25);
    static JFrame frame = new JFrame();
    public static JTextArea[] squares = new JTextArea[24];
    static JTextArea notification = new JTextArea();
    JButton roll = new JButton("Roll");
    public static int winner = 0;
    static JLabel[] names = new JLabel[indicatePlayers.playerNum];
    JLabel namesText = new JLabel("Names: ");
    JLabel pointsText = new JLabel("Points: ");

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
            squares[i].setFocusable(false);
            squares[i].setLineWrap(true);
            frame.add(squares[i]);
        }

        for (int i = 0; i < names.length; i++) {
            names[i] = new JLabel();
            names[i].setBounds(400, (100 * (i + 1)) /2, 120, 30);
            names[i].setFont(largeFont);
            names[i].setLayout(null);
            names[i].setFocusable(false);
            names[i].setText(indicatePlayers.visiblePlayerNames[i]);
            frame.add(names[i]);
        }

        namesText.setBounds(400, 20,50,15);
        namesText.setLayout(null);
        namesText.setVisible(true);

        pointsText.setBounds(500, 20,50,15);
        pointsText.setLayout(null);
        pointsText.setVisible(true);

        roll.setFocusable(false);
        roll.setBounds(650, 100, 100, 40);
        roll.addActionListener(this);
        roll.setLayout(null);

        notification.setBounds(600, 400, 200, 40);
        notification.setEditable(false);
        notification.setLineWrap(true);
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
            if (currentPlayer == totalPlayers-1) {
                currentPlayer = 0;
            } else {
                currentPlayer++;
            }
            // setting font to bold to emphasize
            names[currentPlayer].setFont(boldFont);
        }
    }

    /**
     * Whenever a player lands on a special tile, this function identifies that, and runs the required external functions
     * @param currentPlayer - this identifies the current player
     * @param input - this is the current data collected
     */
    public static void events (int currentPlayer,String[][] input) {
        Random rn = new Random();
        // if the player position reaches over 24, the game ends
        try {
            int currentPos = Integer.parseInt(input[currentPlayer][2]);
            // if the player lands on one of these squares, start a minigame or move forward
            if (currentPos == 6) {
                frame.dispose();
                mini9 firstMinigame = new mini9();
            } else if (currentPos == 12) {
                frame.dispose();
                mini13 secondMinigame = new mini13();
            } else if (currentPos == 18) {
                frame.dispose();
                mini21 thirdMinigame = new mini21();
            } else if (currentPos == rn.nextInt(2, 4)) {
                updateBoard(currentPlayer, 2, indicatePlayers.playerInfo);
                events(currentPlayer, indicatePlayers.playerInfo);
                notification.setText(indicatePlayers.playerInfo[currentPlayer][1] + " landed on a ladder and went 2 spaces forward!");
            } else if (currentPos == rn.nextInt(14, 16)) {
                updateBoard(currentPlayer, 3, indicatePlayers.playerInfo);
                events(currentPlayer, indicatePlayers.playerInfo);
                notification.setText(indicatePlayers.playerInfo[currentPlayer][1] + " landed on a ladder and went 3 spaces forward!");
            } else if (currentPos == rn.nextInt(9, 11)) {
                updateBoard(currentPlayer, -2, indicatePlayers.playerInfo);
                events(currentPlayer, indicatePlayers.playerInfo);
                notification.setText(indicatePlayers.playerInfo[currentPlayer][1] + " landed on a snake and went 2 spaces backward!");
            } else if (currentPos == 22) {
                updateBoard(currentPlayer, -4, indicatePlayers.playerInfo);
                events(currentPlayer, indicatePlayers.playerInfo);
                notification.setText(indicatePlayers.playerInfo[currentPlayer][1] + " landed on a snake and went 4 spaces backward!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            frame.dispose();
            for (int i = 0; i < indicatePlayers.getPlayerNum(); i++) {
                // chooses the player that reached the end first by selecting the highest player position
                if (Integer.parseInt(indicatePlayers.playerInfo[i][2]) > Integer.parseInt(indicatePlayers.playerInfo[winner][2])) {
                    winner = Integer.parseInt(indicatePlayers.playerInfo[i][2]);
                }
            }
            // runs the ending program
            endGame ending = new endGame();
        }
    }

    /**
     * This function is run whenever a player's position changes and updates the game board accordingly
     * @param currentPlayer - this identifies which players position is changing
     * @param newPos - this is the difference in position the player is moving
     * @param input - this is the current data that has been collected
     */
    public static void updateBoard (int currentPlayer, int newPos, String[][] input) {
        // setting old bolded name back to normal
        names[currentPlayer].setFont(largeFont);

        // removing old player position display
        squares[Integer.parseInt(input[currentPlayer][2])].setText(squares[Integer.parseInt(input[currentPlayer][2])].getText().replaceAll(", " + (currentPlayer+1), ""));

        // getting new player position
        input[currentPlayer][2] = Integer.toString(Integer.parseInt(input[currentPlayer][2]) + newPos);

        // adding player number to new position
        squares[Integer.parseInt(input[currentPlayer][2])].setText(squares[Integer.parseInt(input[currentPlayer][2])].getText() + ", " + (currentPlayer + 1));

        // adding notification of player's position change
        notification.setText(input[currentPlayer][1] + " moved " + newPos + " spaces.");
    }
}