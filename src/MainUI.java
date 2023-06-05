import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MainUI implements ActionListener {
    public static int currentPlayer = 0;
    public static int totalPlayers = indicatePlayers.getPlayerNum();
    static Font largeFont = new Font(null, Font.PLAIN, 25);
    static JFrame frame = new JFrame();
    public static JTextArea[] squares = new JTextArea[24];
    static JTextArea notification = new JTextArea();
    JButton roll = new JButton("Roll");
    public static int squaresWinner = 0;
    public static int pointsWinner = 0;
    static JLabel[] names = new JLabel[indicatePlayers.playerNum];
    static JLabel[] points  = new JLabel[indicatePlayers.playerNum];
    JLabel namesText = new JLabel("Names: ");
    JLabel pointsText = new JLabel("Points: ");
    public static String oldNoti;

    /**
     * This is a constructor used to build the window, every application I have made uses these to enable window creation.
     */
    MainUI() {
        // initializing the x and y positions so that they can be easily changed
        int x = 10, y = 10;
        for (int i = 0; i < squares.length; i++) {
            squares[i] = new JTextArea(Integer.toString(Main.pointsAmount[i]));
            // this will change the x and y values so that the boxes will be created in a rectangular shape
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
            names[i].setBounds(400, y, 100, 30);
            names[i].setFont(largeFont);
            names[i].setLayout(null);
            names[i].setFocusable(false);
            points[i].setBounds(520, y, 60, 30);
            points[i].setFont(largeFont);
            points[i].setLayout(null);
            points[i].setFocusable(false);
            // adding the now configured data types to the board
            frame.add(points[i]);
            frame.add(names[i]);
            y += 40;
        }
        // creating text to clarify what is being shown
        namesText.setBounds(400, 20,50,15);
        namesText.setLayout(null);
        // creating text to clarify what is being shown
        pointsText.setBounds(500, 20,50,15);
        pointsText.setLayout(null);
        // configuring a roll button so that the player can roll the dice
        roll.setFocusable(false);
        roll.setBounds(650, 100, 100, 40);
        roll.addActionListener(this);
        roll.setLayout(null);
        // setting up a notification box to tell you what is going on
        notification.setBounds(400, 400, 200, 50);
        notification.setFocusable(false);
        notification.setLineWrap(true);
        notification.setLayout(null);
        // adding the now configured buttons and texts
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
            int pastPlayer = currentPlayer;
            // calling update board function
            updateBoard(currentPlayer, rn.nextInt(1, 6), indicatePlayers.playerInfo);
            // when the player number becomes greater than the total amount, it resets it to zero
            if (currentPlayer == totalPlayers-1) {
                currentPlayer = 0;
            } else {
                currentPlayer++;
            }
            names[pastPlayer].setForeground(Color.black);
            names[currentPlayer].setForeground(Color.red);
        }
    }

    /**
     * Whenever a player lands on a special tile, this function identifies that, and runs the required external functions
     * @param currentPlayer - this identifies the current player
     * @param input - this is the current data collected
     */
    public static void events (int currentPlayer,String[][] input) {
        Random rn = new Random();
        oldNoti = notification.getText();
        int currentPos = Integer.parseInt(input[currentPlayer][2]);
        // if the player lands on one of these squares, start a minigame or move forward
        if (currentPos == 6) {
            frame.dispose();
            mini6 firstMinigame = new mini6();
        } else if (currentPos == 12) {
            frame.dispose();
            mini12 secondMinigame = new mini12();
        } else if (currentPos == 18) {
            frame.dispose();
            mini18 thirdMinigame = new mini18();
        } else if (currentPos == rn.nextInt(1, 3)) {
            updateBoard(currentPlayer, 2, indicatePlayers.playerInfo);
            notification.setText(oldNoti + " " + indicatePlayers.playerInfo[currentPlayer][1] + " landed on a ladder and went 2 spaces forward!");
        } else if (currentPos == rn.nextInt(13, 14)) {
            updateBoard(currentPlayer, 3, indicatePlayers.playerInfo);
            notification.setText(oldNoti + " " + indicatePlayers.playerInfo[currentPlayer][1] + " landed on a ladder and went 3 spaces forward!");
        } else if (currentPos == rn.nextInt(9, 11)) {
            updateBoard(currentPlayer, -2, indicatePlayers.playerInfo);
            notification.setText(oldNoti + " " + indicatePlayers.playerInfo[currentPlayer][1] + " landed on a snake and went 2 spaces backward!");
        } else if (currentPos == 22) {
            updateBoard(currentPlayer, -4, indicatePlayers.playerInfo);
            notification.setText(oldNoti + " " + indicatePlayers.playerInfo[currentPlayer][1] + " landed on a snake and went 4 spaces backward!");
        }
    }

    /**
     * This function is run whenever a player's position changes and updates the game board accordingly.
     * It takes 3 different inputs, which includes the player's number so that it knows who to move,
     * the amount of spaces to move, which is generated and inputted before running,
     * and it also takes in the collected information throughout the game.
     * @param currentPlayer - this identifies which players position is changing
     * @param newPos - this is the difference in position the player is moving
     * @param input - this is the current data that has been collected
     */
    public static void updateBoard (int currentPlayer, int newPos, String[][] input) {
        try { // if the player position reaches over 24, the game ends
            // removing old player position display
            squares[Integer.parseInt(input[currentPlayer][2])].setText(squares[Integer.parseInt(input[currentPlayer][2])].getText().replaceAll(", " + (currentPlayer + 1), ""));

            // getting new player position
            input[currentPlayer][2] = Integer.toString(Integer.parseInt(input[currentPlayer][2]) + newPos);

            // adding player number to new position
            squares[Integer.parseInt(input[currentPlayer][2])].setText(squares[Integer.parseInt(input[currentPlayer][2])].getText() + ", " + (currentPlayer + 1));

            // adding notification of player's position change
            notification.setText(input[currentPlayer][1] + " moved " + newPos + " spaces.");

            // finding the index and adding the points earned from
            int index = squares[Integer.parseInt(input[currentPlayer][2])].getText().indexOf(",");
            indicatePlayers.playerInfo[currentPlayer][3] = Integer.toString(Integer.parseInt(indicatePlayers.playerInfo[currentPlayer][3]) + Integer.parseInt(squares[Integer.parseInt(input[currentPlayer][2])].getText().substring(0, index)));

            points[currentPlayer].setText(indicatePlayers.playerInfo[currentPlayer][3]);

            // running events function to see if the player landed on a special square
            events(currentPlayer, input);


        } catch (ArrayIndexOutOfBoundsException e){
            frame.dispose();
            for (int i = 0; i < indicatePlayers.getPlayerNum(); i++) {
                // chooses the player that reached the end first by selecting the highest player position
                squaresWinner = currentPlayer;
                if (Integer.parseInt(indicatePlayers.playerInfo[i][3]) > Integer.parseInt(indicatePlayers.playerInfo[pointsWinner][3])) {
                    pointsWinner = Integer.parseInt(indicatePlayers.playerInfo[i][3]);
                }
            }
            // runs the ending program
            endGame ending = new endGame();
        }
    }
}