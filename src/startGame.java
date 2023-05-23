import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class startGame implements ActionListener {
    JFrame frame = new JFrame("Board Game!");
    JButton myButton = new JButton("Start Board Game!");

    startGame() {

        myButton.setBounds(100, 160, 200, 40);
        myButton.setFocusable(false);
        myButton.addActionListener(this);

        frame.add(myButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==myButton) {
            frame.dispose();
            indicatePlayers startGame = new indicatePlayers();
        }
    }
}
