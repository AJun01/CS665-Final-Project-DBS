import javax.swing.*;
import java.awt.*;
/*
 * This is MainView class
 * this is View role in MVC pattern
 *
 * in charge of display everything, this is the main interface leading to subpages.
 *  */
public class MainView extends JFrame {
    JButton boardingButton = new JButton("Go Boarding!");
    JButton pickingButton = new JButton("Picking Up?");
    JButton viewingButton = new JButton("Just Visiting~");
    JLabel myLabel;
    ImageIcon bgImage;

    public MainView() {
        setTitle("Dog Boarding Camp");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createView();
        pack();
        setLocationRelativeTo(null); // Center the window
    }

    private void createView() {
        setPreferredSize(new Dimension(600, 400));
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(600, 400));
        getContentPane().add(layeredPane);

        bgImage = new ImageIcon(getClass().getResource("/bgimage.png"));
        Image image = bgImage.getImage();
        Image newImg = image.getScaledInstance(600, 400, Image.SCALE_SMOOTH);
        bgImage = new ImageIcon(newImg);

        myLabel = new JLabel(bgImage);
        myLabel.setBounds(0, 0, 600, 400);
        layeredPane.add(myLabel, Integer.valueOf(1));

        boardingButton.setBounds(10, 50, 120, 30);
        pickingButton.setBounds(10, 100, 120, 30);
        viewingButton.setBounds(10, 150, 120, 30);

        layeredPane.add(boardingButton, Integer.valueOf(2));
        layeredPane.add(pickingButton, Integer.valueOf(2));
        layeredPane.add(viewingButton, Integer.valueOf(2));
    }
}

