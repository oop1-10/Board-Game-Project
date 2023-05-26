import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MainUI implements ActionListener {
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
        int currentPlayer = 1;
        if (e.getSource()==roll) {
            updateBoard(currentPlayer, indicatePlayers.playerInfo);
            currentPlayer++;
        }
    }

    public static String[][] updateBoard (int currentPlayer,String[][] input) {
        Random rn = new Random();

        int originalPos = Integer.parseInt(input[currentPlayer][2].substring(0,1));

        input[currentPlayer][2] = Integer.toString(Integer.parseInt(input[currentPlayer][2]) + rn.nextInt());

        squares[Integer.parseInt(input[currentPlayer][2])].setText(squares[Integer.parseInt(input[currentPlayer][2])].getText() + ", " + input[currentPlayer][2]);

        squares[originalPos].setText(Integer.toString(originalPos));

        return input;
    }
}