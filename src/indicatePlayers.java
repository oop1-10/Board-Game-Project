import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class indicatePlayers implements ActionListener {

    JFrame frame = new JFrame("Player Indication");
    Font defaultFont = new Font(null, Font.PLAIN, 12);
    Integer[] playerNumbers = {2, 3, 4};
    String[] visiblePlayerNames = new String[4];
    public static int playerNum = 4;
    JButton button = new JButton("Confirm");
    JButton confirm = new JButton("Confirm");
    JComboBox<Integer> playerAmount = new JComboBox<>(playerNumbers);
    JTextArea[] playerNames = new JTextArea[getPlayerNum()];
    JTextArea[] names = new JTextArea[getPlayerNum()];
    JTextArea text = new JTextArea();

    indicatePlayers() {
        for (int i = 0; i < playerNames.length; i++) {
            playerNames[i] = new JTextArea();
            names[i] = new JTextArea();
        }

        int x = 10, y = 10;
        for (int i = 0; i < names.length; i++) {
            names[i].setBounds(x, y + i * 40,50,30);
            names[i].setText("Name: ");
            names[i].setLayout(null);
            names[i].setEditable(false);
            names[i].setFont(defaultFont);
            names[i].setVisible(false);

            playerNames[i].setBounds(x + 60, y + i * 40, 100, 30);
            playerNames[i].setVisible(false);
            frame.add(playerNames[i]);
            frame.add(names[i]);
        }

        confirm.setBounds(100, 200, 100, 40);
        confirm.setFocusable(false);
        confirm.addActionListener(this);
        confirm.setVisible(false);

        frame.add(confirm);

        playerAmount.getSelectedIndex();
        playerAmount.setBounds(100,50,90,20);

        button.setFocusable(false);
        button.setBounds(100, 160, 100, 40);
        button.addActionListener(this);
        button.setLayout(null);

        text.setText("Players: ");
        text.setLayout(null);
        text.setBounds(40,50,50,30);
        text.setFont(defaultFont);
        text.setEditable(false);

        frame.add(text);
        frame.getContentPane().add(playerAmount);
        frame.add(button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,300);
        frame.setLayout(null);
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==button) {
            playerNum = (int) playerAmount.getSelectedItem();
            for (int i = 0; i < getPlayerNum(); i++) {
                playerNames[i].setVisible(true);
                names[i].setVisible(true);
                button.setVisible(false);
                confirm.setVisible(true);
                playerAmount.setVisible(false);
                text.setVisible(false);
            }
        }

        if (e.getSource()==confirm) {
            for (int i = 0; i < getPlayerNum(); i++) {
                visiblePlayerNames[i] = playerNames[i].getText();
                frame.dispose();
                MainUI openFinally = new MainUI();
            }
        }
    }

    public static int getPlayerNum (){
        return playerNum;
    }
}
