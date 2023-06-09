import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class startGame implements ActionListener {
    JFrame frame = new JFrame("Board Game!");
    JButton myButton = new JButton("Start Board Game!");
    ImageIcon image;
    JLabel displayField;

    startGame() {
        try {
            image = new ImageIcon(getClass().getResource("startImage.jpg"));
            Image image1 = image.getImage();
            Image newimg = image1.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            image = new ImageIcon(newimg);
        } catch (Exception e) {
            System.out.print("Image cannot be found!");
        }
        displayField = new JLabel(image);
        displayField.setBounds(100, 0, 200, 200);
        displayField.setLayout(null);
        displayField.setVisible(true);
        // configuring the button
        myButton.setBounds(100, 210, 200, 40);
        myButton.setFocusable(false);
        myButton.addActionListener(this);
        // adding the start button to the screen
        frame.add(myButton);
        frame.add(displayField);
        // setting the frames initial values, this is almost always the same in every program, minus the frame size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==myButton) {
            // getting rid of the frame to free up screen space
            frame.dispose();
            // running the indicate players program
            indicatePlayers startGame = new indicatePlayers();
        }
    }
}
