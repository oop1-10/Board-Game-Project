import javax.swing.*;

public class endGame {

    static JFrame frame = new JFrame("Ending");
    static JLabel endText = new JLabel();
    static JTextArea endingText = new JTextArea();

    endGame() {
        endText.setBounds(100,100, 100, 40);
        endText.setLayout(null);
        endText.setText("<html>The player with the most points was " + MainUI.pointsWinner + ".<br/> The player who reached the end first was " + MainUI.squaresWinner + ".<br/>  We are all winners here!</html>");
        endText.setFocusable(false);

        frame.add(endingText);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
