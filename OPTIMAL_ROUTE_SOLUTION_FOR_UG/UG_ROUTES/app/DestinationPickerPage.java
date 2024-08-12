package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import static app.Main.campusNavigationGraph;

public class DestinationPickerPage extends JFrame {

    private final JButton backButton;
    private final JComboBox<String> originCombo;
    private final JComboBox<String> landMarkCombo;
    private final JComboBox<String> destinationCombo;
    private final JLabel halfPathToDestinationPicker;
    private final JLabel halfwayToEndPath;
    private final JLabel distanceDisplay;

    DestinationPickerPage() {

        backButton = new JButton();
        backButton.setText("Go Back");
        backButton.setBounds(0, 0, 100, 30);
        backButton.setFocusable(false);
        backButton.setBorder(new CurvedBorder(25));
        backButton.addActionListener(this::mainPage);
        this.add(backButton);

        JLabel currentLocation = new JLabel();
        currentLocation.setText("Choose your location:");
        currentLocation.setBounds(100, 50, 200, 40);
        currentLocation.setFont(new Font("Serif", Font.BOLD, 20));
        this.add(currentLocation);

        JLabel landMarkLocation = new JLabel();
        landMarkLocation.setText("Choose a landmark:");
        landMarkLocation.setBounds(100, 150, 250, 40);
        landMarkLocation.setFont(new Font("Serif", Font.BOLD, 20));
        this.add(landMarkLocation);

        JLabel destinationLocation = new JLabel();
        destinationLocation.setText("Choose final destination:");
        destinationLocation.setBounds(100, 250, 200, 40);
        destinationLocation.setFont(new Font("Serif", Font.BOLD, 20));
        this.add(destinationLocation);

        String[] places = { "Common wealth hall", "Main Gate", "Law school", "Night Market", "UG Fire Service",
                "CS Department", "Athletic Oval", "Diaspora", "Volta Hall", "Akuafo Hall", "Business School",
                "Legon Hall", "Great Hall",
                "NNB", "N Block", "JQB", "Balme+ Library", "UGCS", "Pentagon Hall" };

        originCombo = new JComboBox<>(places);
        originCombo.setBounds(550, 50, 200, 30);
        this.add(originCombo);

        landMarkCombo = new JComboBox<>(places);
        landMarkCombo.setBounds(550, 150, 200, 30);
        this.add(landMarkCombo);

        destinationCombo = new JComboBox<>(places);
        destinationCombo.setBounds(550, 250, 200, 30);
        this.add(destinationCombo);

        JLabel initialPath = new JLabel();
        initialPath.setText("Initial path: ");
        initialPath.setBounds(100, 400, 250, 40);
        initialPath.setFont(new Font("Serif", Font.BOLD, 20));
        this.add(initialPath);

        JLabel secondPath = new JLabel();
        secondPath.setText("Final path: ");
        secondPath.setBounds(100, 500, 250, 40);
        secondPath.setFont(new Font("Serif", Font.BOLD, 20));
        this.add(secondPath);

        halfPathToDestinationPicker = new JLabel();
        halfPathToDestinationPicker.setBounds(300, 400, 800, 40);
        halfPathToDestinationPicker.setBorder(new CurvedBorder(10));
        halfPathToDestinationPicker.setFont(new Font("Serif", Font.BOLD, 15));
        this.add(halfPathToDestinationPicker);

        halfwayToEndPath = new JLabel();
        halfwayToEndPath.setBounds(300, 500, 800, 40);
        halfwayToEndPath.setBorder(new CurvedBorder(10));
        halfwayToEndPath.setFont(new Font("Serif", Font.BOLD, 15));
        this.add(halfwayToEndPath);

        distanceDisplay = new JLabel();
        distanceDisplay.setBounds(250, 600, 400, 40);
        distanceDisplay.setFont(new Font("Serif", Font.BOLD, 20));
        this.add(distanceDisplay);

        JButton getPossiblePaths = new JButton("Get possible paths");
        getPossiblePaths.setBounds(580, 350, 130, 25);
        getPossiblePaths.setBorder(new CurvedBorder(10));
        getPossiblePaths.setFocusable(false);
        this.add(getPossiblePaths);

        getPossiblePaths.addActionListener(this::getPaths);

        this.setLayout(null);
        this.setSize(1300, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.yellow);
        this.setTitle("LANDMARK ROUTING");
        this.setVisible(true);
    }

    // Handles switching from Destination page to UI page.
    private void mainPage(ActionEvent actionEvent) {
        if (actionEvent.getSource() == backButton) {
            this.dispose();
            new DestinationSelectorUI();
        }
    }

    // Prints paths and total distance.
    private void getPaths(ActionEvent actionEvent) {
        try {
            String theOrigin = originCombo.getSelectedItem().toString();
            String theLandMark = landMarkCombo.getSelectedItem().toString();
            String theEnd = destinationCombo.getSelectedItem().toString();

            // Gets item of source, landmark and destination.
            Destination sourceDijkstra = campusNavigationGraph.getNodeByName(theOrigin);
            Destination landMarkDijkstra = campusNavigationGraph.getNodeByName(theLandMark);
            Destination destinationDijkstra = campusNavigationGraph.getNodeByName(theEnd);

            // Print path from source to landmark and landmark to destination.

            // Finds path and get distance between source and landmark.
            Dijkstra.findShortestPath(campusNavigationGraph, sourceDijkstra, landMarkDijkstra);
            String firstPath = Dijkstra.getShortestPath(sourceDijkstra, landMarkDijkstra);
            float firstPathDistance = Dijkstra.getTotalDistance(landMarkDijkstra);

            // Finds path and get distance between landmark and destination.
            Dijkstra.findShortestPath(campusNavigationGraph, landMarkDijkstra, destinationDijkstra);
            String secondPath = Dijkstra.getShortestPath(landMarkDijkstra, destinationDijkstra);
            float secondPathDistance = Dijkstra.getTotalDistance(destinationDijkstra);

            float totalPathDistance = firstPathDistance + secondPathDistance;

            halfPathToDestinationPicker.setText(firstPath);
            halfwayToEndPath.setText(secondPath);

            distanceDisplay.setText("Approximate distance: " + totalDistance(totalPathDistance) + "km");
        } catch (NullPointerException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static String totalDistance(float total) {
        return String.format("%.3f", total);
    }

}
