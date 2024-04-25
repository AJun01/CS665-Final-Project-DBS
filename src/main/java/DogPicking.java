import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
/*
 * This is DogPicking class
 * this is responsible the Picking Up? page
 *
 * in charge of delete data and display
 *  */

public class DogPicking extends JFrame {

    private JTextField fnameField, lnameField, breedField, colorField, ageField, sizeField;

    private JButton deleteButton;
    private JButton SearchButton;
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

    public DogPicking() {
        createView();
        setTitle("Witch one is yours?");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }

    private void createView() {
        JPanel panel = new JPanel(new GridBagLayout());
        getContentPane().add(panel);

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));

        Color color = new Color(244,194,187);
        panel.setBackground(color);

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

        String[] columnNames = {"Dog ID", "First Name", "Last Name", "Breed", "Color", "Age", "Size"};

        // Initialize the DefaultTableModel with the column names and zero rows
        tableModel = new DefaultTableModel(columnNames, 0);

        // Initialize the JTable with the DefaultTableModel
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 200)); // Set the scroll pane size
        c.gridx = 0;
        c.gridy++;
        c.gridwidth = 2; // Span across two columns
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(scrollPane, c);

        scrollPane.setVisible(false);

        SearchButton = new JButton("Search");
        c.gridy++;
        panel.add(SearchButton, c);
        SearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scrollPane.setVisible(true);
                deleteButton.setVisible(true);
                loadTableData();
            }
        });

        deleteButton = new JButton("Pick up your dog");
        c.gridy++;
        panel.add(deleteButton, c);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedDog();
            }
        });

        deleteButton.setVisible(false);


        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose this window and go back to the previous one
                DogPicking.this.dispose();
            }
        });
        add(backButton, BorderLayout.SOUTH); // Add the back button to the bottom of the JFrame
    }

    private void deleteSelectedDog() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a dog to delete.", "No Dog Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int selectedDogId = (int) table.getValueAt(table.getSelectedRow(), 0);

        // Confirm deletion
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the selected dog?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // Perform the deletion
            String url = "jdbc:mysql://localhost:3306/myDogBoardingCamp";
            String user = "root";
            String password = "85631596";
            String query = "DELETE FROM Dogs WHERE dog_id = ?";
            try (Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement  deleteStmt = conn.prepareStatement(query)) {
                deleteStmt.setInt(1, selectedDogId);
                int affectedRows = deleteStmt.executeUpdate();
                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(this, "Dog deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadTableData();
                    // Optionally, refresh the table to show the updated data
                } else {
                    JOptionPane.showMessageDialog(this, "Error: Dog could not be deleted.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error during database operation: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private void loadTableData() {
        // Clear the existing data
        tableModel.setRowCount(0);

        String url = "jdbc:mysql://localhost:3306/myDogBoardingCamp";
        String user = "root";
        String password = "85631596";
        String query = "SELECT * FROM Dogs WHERE fname = ? AND lname = ?";

        // Connect to the database and fetch the data
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, fnameField.getText());
            stmt.setString(2, lnameField.getText());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] row = {
                        rs.getInt("dog_id"),
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("breed"),
                        rs.getString("color"),
                        rs.getInt("age"),
                        rs.getString("size")
                };
                tableModel.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


}

