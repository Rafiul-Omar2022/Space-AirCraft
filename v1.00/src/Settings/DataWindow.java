package Settings;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class DataWindow extends JFrame {

    public DataWindow(ArrayList<Data> data) {
        super("Data Table Example");

        // Create a DefaultTableModel with columns "Name" and "Score"
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Score");

        // Add data to the table model
        for (Data item : data) {
            model.addRow(new Object[]{item.getName(), item.getScore()});
        }

        // Create JTable with the created model
        JTable table = new JTable(model);

        // Set up the frame layout
        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Set frame properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }
}
