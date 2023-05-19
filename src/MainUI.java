import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class MainUI {
    static Font defaultFont = new Font(null, Font.PLAIN, 12);
    static JFrame frame = new JFrame();

    public static JTextArea[] squares = new JTextArea[24];

    MainUI() {
        int x = 10, y = 10;
        for (int i = 0; i < squares.length; i++) {
            squares[i] = new JTextArea(Integer.toString(i));
            if (i < 8) {
                squares[i].setBounds(x, y, 30, 30);
                x += 40;
            } else if (i < 12) {
                squares[i].setBounds(x, y, 30, 30);
                y += 40;
            } else if (i < 20) {
                squares[i].setBounds(x, y, 30, 30);
                x -= 40;
            } else {
                squares[i].setBounds(x, y, 30, 30);
                y -= 40;
            }
            squares[i].setFont(defaultFont);
            squares[i].setEditable(false);
            squares[i].setLayout(null);
            squares[i].setText(Integer.toString(i+1));
            frame.add(squares[i]);
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}