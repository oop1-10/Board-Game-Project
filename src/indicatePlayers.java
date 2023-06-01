import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class indicatePlayers implements ActionListener {
    JFrame frame = new JFrame("Player Indication");
    Integer[] playerNumbers = {2, 3, 4};
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
        // when this button is pressed, it will save all of the names entered and close the window, while also opening the MainUI window.  Lastly, it will call the player info initializer function
        if (e.getSource()==confirm) {
            for (int i = 0; i < getPlayerNum(); i++) {
                visiblePlayerNames[i] = playerNames[i].getText();
                frame.dispose();
                MainUI openFinally = new MainUI();
            }
            playerInfo = playerInfoInitializer(playerNum, visiblePlayerNames);
        }
    }

    /**
     * This function returns the player num variable
     * @return the amount of players currently playing the game
     */
    public static int getPlayerNum (){
        return playerNum;
    }

    /**
     * This functions initialzes the 2D array that is being used to store the basic player information
     * @param playerTotal - the total amount of players currently in the game
     * @param name - an array of names entered that will loop into the 2D array above
     * @return the now initialized player info array
     */
    public static String[][] playerInfoInitializer (int playerTotal, String[] name) {
        // making the output array for the information
        String[][] output = new String[playerTotal][4];
        // loop through the first row of the array and list the player numbers
        for (int i = 0; i < playerTotal; i++) {
            output[i][0] = Integer.toString(i+1);
        }
        // loop through the second row and copy over the collected names
        for (int i = 0; i < playerTotal; i++) {
            output[i][1] = name[i];
        }
        // loop through the last 2 rows with 0, 2 being position and 3 being points
        for (int i = 0; i < playerTotal; i++) {
            output[i][2] = Integer.toString(0);
            output[i][3] = Integer.toString(0);
        }

        return output;
    }

}