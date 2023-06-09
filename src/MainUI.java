import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class MainUI implements ActionListener {
    public static int currentPlayer = 0;
    static Font largeFont = new Font(null, Font.PLAIN, 25);
    static JFrame frame = new JFrame("Board Game");
    public static JTextArea[] squares = new JTextArea[24];
    static JTextArea notification = new JTextArea();
    JButton roll = new JButton("Roll");
    public static int[] winners = new int[2];
    static JLabel[] names = new JLabel[indicatePlayers.playerNum];
    static JLabel[] points = new JLabel[indicatePlayers.playerNum];
    JLabel[] text = new JLabel[2];
    public static boolean[] events = new boolean[3];
    public static int rollMove;
    Border border = BorderFactory.createLineBorder(Color.BLACK);
    ImageIcon image;
    JButton skipTurn = new JButton("Skip turn");
    JButton accept = new JButton("Accept point loss");
    int[] skipped = new int[indicatePlayers.getPlayerNum()];

    /**
     * This is a constructor used to build the window, every application I have made uses these to enable window creation.
     */
    MainUI() {
        // initializing the x and y positions so that they can be easily changed
        int x = 10, y = 10;

        for (int i = 0; i < squares.length; i++) {
            squares[i] = new JTextArea(Integer.toString(Main.pointsAmount[i]));
            squares[i].setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(3, 3, 3, 3)));
            // this will change the x and y values so that the boxes will be created in a rectangular shape
            if (i < 6) {
                squares[i].setBounds(x, y, 90, 90);
                x += 100;
            } else if (i < 12) {
                squares[i].setBounds(x, y, 90, 90);
                y += 100;
            } else if (i < 18) {
                squares[i].setBounds(x, y, 90, 90);
                x -= 100;
            } else {
                squares[i].setBounds(x, y, 90, 90);
                y -= 100;
            }

            if (i % 2 == 0) {
                squares[i].setBackground(Color.white);
            } else {
                squares[i].setBackground(Color.gray);
            }

            // setting layout, text, focusability and adding the ability to wrap lines
            squares[i].setLayout(null);
            squares[i].setFocusable(false);
            squares[i].setLineWrap(true);
            // adding the squares to the frame
            frame.add(squares[i]);
        }
        y = 50;
        for (int i = 0; i < indicatePlayers.playerNum; i++) {
            // initializing the arrays so that it knows what data type it is
            names[i] = new JLabel(indicatePlayers.playerInfo[i][1]);
            points[i] = new JLabel(indicatePlayers.playerInfo[i][3]);
            // setting bounds, font, layout and focusability
            names[i].setBounds(750, y, 100, 30);
            names[i].setFont(largeFont);
            names[i].setLayout(null);
            names[i].setFocusable(false);
            points[i].setBounds(870, y, 60, 30);
            points[i].setFont(largeFont);
            points[i].setLayout(null);
            points[i].setFocusable(false);
            // adding the now configured data types to the board
            frame.add(points[i]);
            frame.add(names[i]);
            y += 40;
        }

        try {
            image = new ImageIcon(getClass().getResource(""));
        } catch (Exception e) {
            System.out.print("Image not found!");
        }

        // creating text to clarify what is being shown
        text[0] = new JLabel("Names: ");
        text[0].setBounds(750, 20, 50, 15);
        text[0].setLayout(null);
        // creating text more text
        text[1] = new JLabel("Points: ");
        text[1].setBounds(870, 20, 50, 15);
        text[1].setLayout(null);
        // configuring a roll button so that the player can roll the dice
        roll.setFocusable(false);
        roll.setBounds(950, 100, 100, 40);
        roll.addActionListener(this);
        roll.setLayout(null);

        //skip turn to avoid negative points
        skipTurn.addActionListener(this);
        skipTurn.setVisible(false);
        skipTurn.setFocusable(false);
        skipTurn.setBounds(870, 70, 100, 40);
        skipTurn.setLayout(null);

        //accept negative point loss
        accept.addActionListener(this);
        accept.setVisible(false);
        accept.setFocusable(false);
        accept.setBounds(980, 70, 100, 40);
        accept.setLayout(null);

        // setting up a notification box to tell you what is going on
        notification.setBounds(800, 400, 200, 55);
        notification.setFocusable(false);
        notification.setLineWrap(true);
        notification.setLayout(null);
        notification.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(3, 3, 3, 3)));
        // adding the now configured buttons and texts
        frame.add(roll);
        frame.add(notification);
        frame.add(accept);
        frame.add(skipTurn);
        for (JLabel jLabel : text) {
            frame.add(jLabel);
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 1000);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    public static int pastPlayer;
    @Override
    public void actionPerformed(ActionEvent e) {
        Random rn = new Random();
        int temp;
        // when roll button is clicked, run function
        if (e.getSource() == roll) {
            pastPlayer = currentPlayer;

            // dice variable
            rollMove = rn.nextInt(1, 6);

            temp = Integer.parseInt(indicatePlayers.playerInfo[currentPlayer][2]) + rollMove;

            if (points(temp) < 0) {
                skipTurn.setVisible(true);
                accept.setVisible(true);
                roll.setVisible(false);
            } else {
                updateBoard(currentPlayer, rollMove, indicatePlayers.playerInfo);
                // changing players' points variable with the new points amount
                indicatePlayers.playerInfo[currentPlayer][3] = Integer.toString(Integer.parseInt(indicatePlayers.playerInfo[currentPlayer][3]) + points(temp));
                // changing the current players points display
                points[currentPlayer].setText(indicatePlayers.playerInfo[currentPlayer][3]);
                // running events function to see if the player landed on a special square
                events(currentPlayer, indicatePlayers.playerInfo);
            }
            if (skipped[currentPlayer] == 0) {
                // when the player number becomes greater than the total amount, it resets it to zero
                playerIncrement();
                // changing highlighted player
                names[pastPlayer].setForeground(Color.black);
                names[currentPlayer].setForeground(Color.red);
            } else {
                skipped[currentPlayer]+= 1;
                if (skipped[currentPlayer] == 3) {
                    skipped[currentPlayer] = 0;
                    playerIncrement();
                }
            }
        }
        if (e.getSource() == skipTurn) {
            // changing visibility
            roll.setVisible(true);
            skipTurn.setVisible(false);
            accept.setVisible(false);
            pastPlayer = currentPlayer;
            skipped[currentPlayer] = 1;

            playerIncrement();

//            for (int i = 0; i < 2;) {
//                if (e.getSource()==roll) {
//                    // dice variable
//                    rollMove = rn.nextInt(1, 6);
//                    // calling update board function
//                    updateBoard(currentPlayer, rollMove, indicatePlayers.playerInfo);
//                    // changing players' points variable with the new points amount
//                    indicatePlayers.playerInfo[currentPlayer][3] = Integer.toString(Integer.parseInt(indicatePlayers.playerInfo[currentPlayer][3]) + points(currentPlayer, indicatePlayers.playerInfo));
//                    // changing the current players points display
//                    points[currentPlayer].setText(indicatePlayers.playerInfo[currentPlayer][3]);
//                    // running events function to see if the player landed on a special square
//                    events(currentPlayer, indicatePlayers.playerInfo);
//                    i++;
//                }
//            }
//            names[pastPlayer].setForeground(Color.black);
//            names[currentPlayer].setForeground(Color.red);
//            currentPlayer = pastPlayer;
        }
        if (e.getSource() == accept) {
            temp = Integer.parseInt(indicatePlayers.playerInfo[currentPlayer][2]) + rollMove;
            // adding negative points to player total
            indicatePlayers.playerInfo[currentPlayer][3] = Integer.toString(Integer.parseInt(indicatePlayers.playerInfo[currentPlayer][3]) + points(temp));
            // changing the current players points display
            points[currentPlayer].setText(indicatePlayers.playerInfo[currentPlayer][3]);

            // running events function to see if the player landed on a special square
            events(currentPlayer, indicatePlayers.playerInfo);

            roll.setVisible(true);
            skipTurn.setVisible(false);
            accept.setVisible(false);
            pastPlayer = currentPlayer;

            // when the player number becomes greater than the total amount, it resets it to zero
            playerIncrement();
            names[pastPlayer].setForeground(Color.black);
            names[currentPlayer].setForeground(Color.red);
        }
    }

    /**
     * Whenever a player lands on a special tile, this function identifies that, and runs the required external functions.
     * If a player lands on a minigame tile, it will run the appropriate file for that minigame.
     * If the player lands on a ladder, it will launch them forward a couple spaces.
     * If a player lands a snake, it will send them backward a few spaces.
     * @param currentPlayer - this identifies the current player
     * @param input         - this is the current data collected
     */
    public static void events(int currentPlayer, String[][] input) {
        Random rn = new Random();
        int currentPos = Integer.parseInt(input[currentPlayer][2]);
        // if the player lands on one of these squares, start a minigame or move forward
        if (currentPos == 6) {
            //mini6.attempts = 0;
            frame.setVisible(false);
            mini6 firstMinigame = new mini6();
        } else if (currentPos == 12) {
            frame.setVisible(false);
            mini12 secondMinigame = new mini12();
        } else if (currentPos == 18) {
            frame.setVisible(false);
            mini18 thirdMinigame = new mini18();
        } else if (currentPos == Main.firstLadder) { // must change the way the notifications are set up, it will only save one of the notifications or use only one of the notifs
            events[1] = true;
            int posChange = rn.nextInt(1, 2);
            updateBoard(currentPlayer, posChange, indicatePlayers.playerInfo);
        } else if (currentPos == Main.secondLadder) {
            events[1] = true;
            int posChange = rn.nextInt(2, 3);
            updateBoard(currentPlayer, posChange, indicatePlayers.playerInfo);
        } else if (currentPos == Main.firstSnake) {
            events[2] = true;
            int posChange = rn.nextInt(-3, -1);
            updateBoard(currentPlayer, posChange, indicatePlayers.playerInfo);
        } else if (currentPos == 22) {
            events[2] = true;
            int posChange = rn.nextInt(-3, -2);
            updateBoard(currentPlayer, posChange, indicatePlayers.playerInfo);
        }
    }

    /**
     * This function is run whenever a player's position changes and updates the game board accordingly.
     * It takes 3 different inputs, which includes the player's number so that it knows who to move,
     * the amount of spaces to move, which is generated and inputted before running,
     * and it also takes in the collected information throughout the game.
     * @param currentPlayer - this identifies which players position is changing
     * @param newPos        - this is the difference in position the player is moving
     * @param input         - this is the current data that has been collected
     */
    public static void updateBoard(int currentPlayer, int newPos, String[][] input) {
        try { // if the player position reaches over 24, the game ends
            int oldPos = Integer.parseInt(input[currentPlayer][2]);

            // getting new player position
            input[currentPlayer][2] = Integer.toString(Integer.parseInt(input[currentPlayer][2]) + newPos);

            // removing old player position display
            squares[oldPos].setText(squares[oldPos].getText().replaceAll(", " + input[currentPlayer][1], ""));

            // adding player name to new position
            squares[Integer.parseInt(input[currentPlayer][2])].setText(squares[Integer.parseInt(input[currentPlayer][2])].getText() + ", " + input[currentPlayer][1]);

            // adding notification of player's position change
            notification.setText(input[currentPlayer][1] + " moved " + newPos + " spaces.");

            // this statement identifies the minigame event the player just experienced and updates the notification accordingly
            if (events[0] && newPos > 0) { // if the player comes out of a minigame, and the result position is greater than 0, than they won the minigame and change the notification
                notification.setText(input[currentPlayer][1] + " moved " + rollMove + " spaces. " + indicatePlayers.playerInfo[currentPlayer][1] + " won the minigame and went " + newPos + " spaces forward!");
            } else if (events[0] && newPos < 0) {// if the player comes out of a minigame, and the result position is greater than 0, than they lost the minigame and change the notification
                notification.setText(input[currentPlayer][1] + " moved " + rollMove + " spaces. " + indicatePlayers.playerInfo[currentPlayer][1] + " lost the minigame and went " + Math.abs(newPos) + " spaces backward!");
            }
            events[0] = false;
            // this statement identifies the snake or ladder event the player just experienced and updates the notification accordingly
            if (events[1]) {// if the player lands on a ladder, then update the notification accordingly
                notification.setText(input[currentPlayer][1] + " moved " + rollMove + " spaces. " + indicatePlayers.playerInfo[currentPlayer][1] + " landed on a ladder and went " + newPos + " spaces forward!");
                events[1] = false;
            } else if (events[2]) {// if the player lands on a snake, then update the notification accordingly
                notification.setText(input[currentPlayer][1] + " moved " + rollMove + " spaces. " + indicatePlayers.playerInfo[currentPlayer][1] + " landed on a snake and went " + Math.abs(newPos) + " spaces backward!");
                events[2] = false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            frame.dispose();
            for (int i = 0; i < indicatePlayers.getPlayerNum(); i++) {
                // chooses the player that reached the end first by selecting the highest player position
                winners[0] = currentPlayer;
                if (Integer.parseInt(indicatePlayers.playerInfo[i][3]) > Integer.parseInt(indicatePlayers.playerInfo[winners[1]][3])) {
                    winners[1] = Integer.parseInt(indicatePlayers.playerInfo[i][3]);
                }
            }
            // runs the ending program
            endGame ending = new endGame();
        }
    }

    public static int points (int position) {
        int i = findFirstDigitIndex(squares[position].getText());
        return Integer.parseInt(squares[position].getText().substring(0, i));
    }

    private static void playerIncrement () {
        if (currentPlayer == indicatePlayers.getPlayerNum() - 1) {
            currentPlayer = 0;
        } else {
            currentPlayer++;
        }
    }
    public static int findFirstDigitIndex(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))) {
                return i;
            }
        }
        return 1; // Return -1 if no digit is found
    }
}