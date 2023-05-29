import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

public class MainUI implements ActionListener {
    public static int currentPlayer = 1;

    static Font defaultFont = new Font(null, Font.PLAIN, 12);
    static JFrame frame = new JFrame();
    public static JTextArea[] squares = new JTextArea[24];

    JButton roll = new JButton("Roll");

    MainUI() {
        int x = 10, y = 10;
        for (int i = 0; i < squares.length; i++) {
            squares[i] = new JTextArea(Integer.toString(i));
            if (i < 8) {
                squares[i].setBounds(x, y, 40, 40);
                x += 50;
            } else if (i < 12) {
                squares[i].setBounds(x, y, 40, 40);
                y += 50;
            } else if (i < 20) {
                squares[i].setBounds(x, y, 40, 40);
                x -= 50;
            } else {
                squares[i].setBounds(x, y, 40, 40);
                y -= 50;
            }
            squares[i].setFont(defaultFont);
            squares[i].setEditable(false);
            squares[i].setLayout(null);
            squares[i].setText(Integer.toString(i+1));
            frame.add(squares[i]);
        }

        roll.setFocusable(false);
        roll.setBounds(650, 100, 100, 40);
        roll.addActionListener(this);
        roll.setLayout(null);

        frame.add(roll);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==roll) {
            indicatePlayers.playerInfo = updateBoard(currentPlayer, indicatePlayers.playerInfo);
            if (currentPlayer < indicatePlayers.getPlayerNum()) {
                currentPlayer++;
            } else {
                currentPlayer = 1;
            }
        }
    }

    public static String[][] updateBoard (int currentPlayer,String[][] input) {
        Random rn = new Random();
        // removing old player position display
        squares[Integer.parseInt(input[currentPlayer-1][2])].setText(input[currentPlayer-1][2]);
        // getting new player position
        input[currentPlayer-1][2] = Integer.toString(Integer.parseInt(input[currentPlayer-1][2]) + rn.nextInt(1, 6));
        // if the player position reaches over 24, the game ends
        if (Integer.parseInt(input[currentPlayer-1][2]) > 24) {
            endGame();
        } else {
            // adding player number to new position
            squares[Integer.parseInt(input[currentPlayer-1][2])].setText(squares[Integer.parseInt(input[currentPlayer-1][2])].getText() + ", " + currentPlayer);

            if (Integer.parseInt(input[currentPlayer][2]) == 9 || Integer.parseInt(input[currentPlayer][2]) == 13 || Integer.parseInt(input[currentPlayer][2]) == 21) {
                int currentPos = Integer.parseInt(input[currentPlayer][2]);

                if (currentPos == 9) {
                    
                }
            }
        }
        return input;
    }

    public static void endGame () {

    }


}