import javax.swing.*;

public class endGame {

    static JFrame frame = new JFrame("Ending");
    static JTextArea endText = new JTextArea();
    static JTextArea endingText = new JTextArea();

    endGame() {
        endText.setLayout(null);
        endText.setText("The winner of the game was " + indicatePlayers.playerInfo[MainUI.winner][1] + ".  They reached the end first!");

        frame.add(endingText);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
