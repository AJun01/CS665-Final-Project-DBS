import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
/*
 * This is DogBoardingCampDatabase class
 * this is responsible the Go Boarding page
 *
 * in charge of insert data and display data
 *  */
public class DogBoardingCampDatabase extends JFrame {

    private String url = "jdbc:mysql://localhost:3306/myDogBoardingCamp";
    private String user = "root";
    private String password = "85631596";
    private JTextField fnameField, lnameField, breedField, colorField, ageField, sizeField;
    private JButton addButton;

    private JButton backButton;

    private JTable table;
    private DefaultTableModel tableModel;

    //Screen Settings
    final int originalTileSize = 16; // 16 x 16
    final int scale =3;

    public final int tileSize = originalTileSize * scale; // 48 *48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels


    public DogBoardingCampDatabase() {
        createView();
        setTitle("Add Dog to Boarding Camp");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private void createView() {
        JPanel panel = new JPanel(new GridBagLayout());
        getContentPane().add(panel);

        Color color = new Color(244,194,187);
        panel.setBackground(color);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;

        panel.add(new JLabel("First Name: "), c);
        c.gridx = 1;
        fnameField = new JTextField(20);
        panel.add(fnameField, c);
        c.gridx = 0;
        c.gridy++;


        panel.add(new JLabel("Last Name: "), c);
        c.gridx = 1;
        lnameField = new JTextField(20);
        panel.add(lnameField, c);
        c.gridx = 0;
        c.gridy++;


        panel.add(new JLabel("Breed: "), c);
        c.gridx = 1;
        breedField = new JTextField(20);
        panel.add(breedField, c);
        c.gridx = 0;
        c.gridy++;


        panel.add(new JLabel("Color: "), c);
        c.gridx = 1;
        colorField = new JTextField(20);
        panel.add(colorField, c);
        c.gridx = 0;
        c.gridy++;


        panel.add(new JLabel("Age: "), c);
        c.gridx = 1;
        ageField = new JTextField(20);
        panel.add(ageField, c);
        c.gridx = 0;
        c.gridy++;


        panel.add(new JLabel("Size: "), c);
        c.gridx = 1;
        sizeField = new JTextField(20);
        panel.add(sizeField, c);
        c.gridx = 0;
        c.gridy++;


        addButton = new JButton("Add");
        c.gridy++;
        panel.add(addButton, c);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertData();
            }
        });

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose this window and go back to the previous one
                DogBoardingCampDatabase.this.dispose();
            }
        });
        add(backButton, BorderLayout.SOUTH); // Add the back button to the bottom of the JFrame
    }
    private void insertData() {

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO Dogs (fname, lname, breed, color, age, size) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, fnameField.getText());
                pstmt.setString(2, lnameField.getText());
                pstmt.setString(3, breedField.getText());
                pstmt.setString(4, colorField.getText());
                pstmt.setInt(5, Integer.parseInt(ageField.getText()));
                pstmt.setString(6, sizeField.getText());

                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data Added Successfully!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error in database operation: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input: Age must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


}
