import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
/*
 * This is DogDataTable class
 * this is responsible the Just Vising page
 *
 * in charge of display data
 *  */
public class DogDataTable extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    private JButton backButton;

    public DogDataTable() {
        setTitle("Dog Data");
        setSize(600, 400); // Set the window size
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window when closed

        createTable();
        createBackButton();
        loadTableData();
    }

    private void createTable() {
        // Column names for the table
        String[] columnNames = {"Dog ID", "First Name", "Last Name", "Breed", "Color", "Age", "Size"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);



        // Add a JScrollPane with the JTable to the JFrame
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(580, 350)); // Set the scroll pane size
        add(scrollPane); // Add the scroll pane to the JFrame
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        table.setOpaque(false);
        table.setBackground(new Color(0,0,0,0));
        table.setGridColor(new Color(0,0,0,0));
    }

    private void createBackButton() {
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose this window and go back to the previous one
                DogDataTable.this.dispose();
            }
        });
        add(backButton, BorderLayout.SOUTH); // Add the back button to the bottom of the JFrame
    }

    private void loadTableData() {
        // Clear the existing data
        tableModel.setRowCount(0);

        String url = "jdbc:mysql://localhost:3306/myDogBoardingCamp?";
        String user = "root";
        String password = "85631596";

        // Connect to the database and fetch the data
        try (Connection conn = DriverManager.getConnection(url+"user=root&password=85631596");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Dogs")) {

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

