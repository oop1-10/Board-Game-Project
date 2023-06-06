import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class endGame implements ActionListener {

    static JFrame frame = new JFrame("Ending");
    static JLabel endText = new JLabel();
    JButton close = new JButton("Finish");

    endGame() {
        endText.setBounds(50,100, 200, 80);
        endText.setLayout(null);
        endText.setText("<html>The player with the most points was " + MainUI.winners[1] + ". The player who reached the end first was " + MainUI.winners[0] + ". We are all winners here!</html>");
        endText.setFocusable(false);

        close.setVisible(true);
        close.setLayout(null);
        close.addActionListener(this);

        frame.add(close);
        frame.add(endText);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==close) {
            System.exit(0);
        }
    }
}
