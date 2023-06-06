import javax.swing.*;

public class endGame {

    static JFrame frame = new JFrame("Ending");
    static JLabel endText = new JLabel();

    endGame() {
        endText.setBounds(50,100, 200, 40);
        endText.setLayout(null);
        endText.setText("<html>The player with the most points was " + MainUI.winners[1] + ".<br/> The player who reached the end first was " + MainUI.winners[0] + ".<br/>  We are all winners here!</html>");
        endText.setFocusable(false);

        frame.add(endText);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
