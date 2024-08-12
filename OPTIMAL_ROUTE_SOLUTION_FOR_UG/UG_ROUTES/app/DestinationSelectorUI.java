package app;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static app.Main.campusNavigationGraph;

public class DestinationSelectorUI extends JFrame {

    private final JComboBox<String> sourceCombo;
    private final JComboBox<String> destinationCombo;
    private final JLabel shortestPathDisplay;
    private final JLabel distanceDisplay;
    private final JButton landMarkButton;
    private final JButton getPossiblePaths;

    DestinationSelectorUI() {
        this.setTitle("Legon Campus Route Finder");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1300, 700);
        this.setResizable(false);

        // Add background panel
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        this.setContentPane(backgroundPanel);
        this.setLayout(null);

        String[] places = {
                "Main Gate", "Law School", "Night Market", "UG Fire Service", "CS Department",
                "Athletic Oval", "Diaspora Halls", "Volta Hall", "Akuafo Hall", "Business School",
                "Legon Hall", "Great Hall", "NNB", "N Block", "Commonwealth Hall", "JQB",
                "Balme Library", "UGCS", "Pentagon Hall", "Mensah Sarbah Hall",
                "Institute of African Studies", "Central Cafeteria", "ISSER", "School of Performing Arts",
                "UG Hospital", "University Stadium", "Noguchi Memorial Institute", "International House",
                "School of Engineering", "School of Physical and Mathematical Sciences",
                "Institute of Continuing and Distance Education", "School of Nursing", "School of Public Health"
        };

        // Add placeholder items
        sourceCombo = new JComboBox<>();
        sourceCombo.addItem("Select your location");
        for (String place : places) {
            sourceCombo.addItem(place);
        }
        sourceCombo.setBounds(550, 50, 200, 30);
        this.add(sourceCombo);

        destinationCombo = new JComboBox<>();
        destinationCombo.addItem("Select your destination");
        for (String place : places) {
            destinationCombo.addItem(place);
        }
        destinationCombo.setBounds(550, 150, 200, 30);
        this.add(destinationCombo);

        // Custom renderer to handle placeholder text
        sourceCombo.setRenderer(new ComboBoxRenderer("Select your location"));
        destinationCombo.setRenderer(new ComboBoxRenderer("Select your destination"));

        JLabel info = new JLabel(" Paths Found: ");
        info.setBounds(580, 300, 130, 40);
        info.setFont(new Font("Serif", Font.BOLD, 20));
        info.setForeground(Color.BLACK); // Set text color to black
        info.setOpaque(true); // Make label background visible
        info.setBackground(new Color(255, 255, 055, 180)); // Semi-transparent white background
        this.add(info);

        shortestPathDisplay = new JLabel();
        shortestPathDisplay.setBounds(295, 350, 700, 40);
        shortestPathDisplay.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        shortestPathDisplay.setBackground(new Color(255, 255, 055, 180)); // Semi-transparent white background
        shortestPathDisplay.setOpaque(true); // Make sure the background color is visible
        shortestPathDisplay.setFont(new Font("Serif", Font.BOLD, 16));
        shortestPathDisplay.setForeground(Color.BLACK); // Set text color to black
        this.add(shortestPathDisplay);
        this.distanceDisplay = new JLabel();

        getPossiblePaths = new JButton("Find path");
        getPossiblePaths.setBounds(580, 230, 130, 35); // Increased button size
        getPossiblePaths.setFont(new Font("Arial", Font.BOLD, 16));
        getPossiblePaths.setBackground(Color.GREEN);
        getPossiblePaths.setForeground(Color.BLACK);
        getPossiblePaths.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY, 5), // Outer border
                BorderFactory.createEmptyBorder(5, 5, 5, 5) // Padding inside the border
        ));
        getPossiblePaths.setOpaque(true);
        getPossiblePaths.setFocusable(false);

        // Add hover effect for getPossiblePaths button
        getPossiblePaths.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                getPossiblePaths.setBackground(Color.LIGHT_GRAY); // Change color on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                getPossiblePaths.setBackground(Color.GREEN); // Revert color when not hovering
            }
        });

        landMarkButton = new JButton("USE LANDMARK");
        landMarkButton.setBounds(555, 560, 180, 50);
        landMarkButton.setFont(new Font("Arial", Font.BOLD, 19));
        landMarkButton.setBackground(Color.GREEN);
        landMarkButton.setForeground(Color.BLACK);
        landMarkButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY, 5), // Outer border
                BorderFactory.createEmptyBorder(5, 5, 5, 5) // Padding inside the border
        ));
        landMarkButton.setOpaque(true);
        landMarkButton.setFocusable(false);

        // Add hover effect for landMarkButton
        landMarkButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                landMarkButton.setBackground(Color.LIGHT_GRAY); // Change color on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                landMarkButton.setBackground(Color.GREEN); // Revert color when not hovering
            }
        });

        this.add(getPossiblePaths);
        this.add(landMarkButton);
        this.setVisible(true);

        getPossiblePaths.addActionListener(this::getPaths);
        landMarkButton.addActionListener(this::landMarkPath);
    }

    private void getPaths(ActionEvent actionEvent) {
        try {
            String theOrigin = sourceCombo.getSelectedItem().toString();
            String theEnd = destinationCombo.getSelectedItem().toString();

            if ("Select your location".equals(theOrigin) || "Select your destination".equals(theEnd)) {
                shortestPathDisplay.setText("Please select both location and destination.");
                distanceDisplay.setText("");
                return;
            }

            Destination sourceDijkstra = campusNavigationGraph.getNodeByName(theOrigin);
            Destination destinationDijkstra = campusNavigationGraph.getNodeByName(theEnd);

            Dijkstra.findShortestPath(campusNavigationGraph, sourceDijkstra, destinationDijkstra);
            String path = Dijkstra.getShortestPath(sourceDijkstra, destinationDijkstra);
            shortestPathDisplay.setText(path);

            distanceDisplay.setText("Approximate distance: " + Dijkstra.getTotalDistance(destinationDijkstra) + "km");
        } catch (NullPointerException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void landMarkPath(ActionEvent actionEvent) {
        if (actionEvent.getSource() == landMarkButton) {
            this.dispose();
            new DestinationPickerPage();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DestinationSelectorUI::new);
    }

    // Inner class for background panel
    static class BackgroundPanel extends JPanel {

        private BufferedImage backgroundImage;

        public BackgroundPanel() {
            try {
                // Load the background image from a file
                backgroundImage = ImageIO.read(new File(
                        "C:\\Users\\DELL\\Desktop\\Lv 200 assignments\\2nd Sem\\DCIT 204\\SEMESTER_PROJECT\\OPTIMAL_ROUTE_SOLUTION_FOR_UG\\UG_ROUTES\\app\\assets\\legon.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Draw the background image
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    // Custom renderer for JComboBox to handle placeholder text
    static class ComboBoxRenderer extends DefaultListCellRenderer {

        private final String placeholder;

        public ComboBoxRenderer(String placeholder) {
            this.placeholder = placeholder;
        }

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (value != null && value.equals(placeholder)) {
                setForeground(Color.GRAY);
            } else {
                setForeground(Color.BLACK);
            }

            return this;
        }
    }
}
