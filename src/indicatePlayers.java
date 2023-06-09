import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class indicatePlayers implements ActionListener {
    JFrame frame = new JFrame("Player Indication");
    Integer[] playerNumbers = {2, 3, 4};
    Border border = BorderFactory.createLineBorder(Color.BLACK);
    public static String[] visiblePlayerNames = new String[4];
    public static int playerNum = 4;
    public static String[][] playerInfo = new String[playerNum][4];
    JButton button = new JButton("Confirm");
    JButton confirm = new JButton("Confirm");
    JComboBox<Integer> playerAmount = new JComboBox<>(playerNumbers);
    JTextArea[] playerNames = new JTextArea[getPlayerNum()];
    JLabel[] names = new JLabel[getPlayerNum()];
    JLabel text = new JLabel("Players: ");

    indicatePlayers() {
        for (int i = 0; i < playerNames.length; i++) {
            playerNames[i] = new JTextArea();
            names[i] = new JLabel();
        }

        int x = 10, y = 10;
        for (int i = 0; i < names.length; i++) {
            names[i].setBounds(x, y + i * 40,50,30);
            names[i].setText("Name: ");
            names[i].setLayout(null);
            names[i].setVisible(false);

            playerNames[i].setBounds(x + 60, y + i * 40, 100, 30);
            playerNames[i].setVisible(false);
            playerNames[i].setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(3, 3, 3, 3)));
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

        text.setLayout(null);
        text.setFocusable(false);
        text.setBounds(40,50,50,30);

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
        // when this button is pressed, it will save the amount of players and reveal the boxes for names
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
        // when this button is pressed, it will save all the names entered and close the window, while also opening the MainUI window.  Lastly, it will call the player info initializer function
        if (e.getSource()==confirm) {
            for (int i = 0; i < getPlayerNum(); i++) {
                visiblePlayerNames[i] = playerNames[i].getText();
            }
            // this is a loop to initialize the player info collected at the start of the game
            // loop through the first row of the array and list the player numbers
            for (int i = 0; i < playerNum; i++) {
                playerInfo[i][0] = Integer.toString(i);
                // loop through the second row and copy over the collected names
                playerInfo[i][1] = visiblePlayerNames[i];
                // loop through the last 2 rows with 0, 2 being position and 3 being points
                playerInfo[i][2] = Integer.toString(0);
                playerInfo[i][3] = Integer.toString(0);
            }
            // getting rid of the window and opening the main window, as well as attempting to display the players' positions on the zero square
            frame.dispose();
            MainUI openFinally = new MainUI();
            for (int i = 0; i < getPlayerNum(); i++) {
                // adding player number to new position
                MainUI.squares[Integer.parseInt(playerInfo[i][2])].setText(MainUI.squares[Integer.parseInt(playerInfo[i][2])].getText() + ", " + (i + 1));
            }
        }
    }

    /**
     * This function returns the player num variable
     * @return the amount of players currently playing the game
     */
    public static int getPlayerNum (){
        return playerNum;
    }
}