package com.teamtracker.ui;

import com.teamtracker.model.Player;
import com.teamtracker.service.TeamTracker;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

public class TeamTrackerFrame extends JFrame {
    private final TeamTracker teamTracker;
    private final JTextField idField = new JTextField();
    private final JTextField nameField = new JTextField();
    private final JComboBox<String> positionBox = new JComboBox<>(new String[]{"Forward", "Midfielder", "Defender", "Goalkeeper"});
    private final JTextField ageField = new JTextField();
    private final JTextField jerseyField = new JTextField();
    private final JTextField matchesField = new JTextField();
    private final JTextField goalsField = new JTextField();
    private final JTextField searchPositionField = new JTextField();
    private final JTextArea outputArea = new JTextArea();

    public TeamTrackerFrame(TeamTracker teamTracker) {
        this.teamTracker = teamTracker;

        setTitle("Football Team Tracker");
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(16, 16));

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
            // Fall back to the default look and feel if the system theme is unavailable.
        }

        getContentPane().setBackground(new Color(241, 245, 233));

        add(buildHeader(), BorderLayout.NORTH);
        add(buildFormPanel(), BorderLayout.WEST);
        add(buildOutputPanel(), BorderLayout.CENTER);

        refreshAllPlayers();
    }

    private JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(23, 63, 95));
        header.setBorder(BorderFactory.createEmptyBorder(18, 20, 18, 20));

        JLabel title = new JLabel("Football Team Tracker", SwingConstants.LEFT);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));

        JLabel subtitle = new JLabel("Swing GUI + Java DSA player management", SwingConstants.RIGHT);
        subtitle.setForeground(new Color(215, 234, 220));
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        header.add(title, BorderLayout.WEST);
        header.add(subtitle, BorderLayout.EAST);
        return header;
    }

    private JPanel buildFormPanel() {
        JPanel wrapper = new JPanel(new BorderLayout(0, 16));
        wrapper.setBackground(new Color(241, 245, 233));
        wrapper.setBorder(BorderFactory.createEmptyBorder(0, 16, 16, 0));
        wrapper.setPreferredSize(new java.awt.Dimension(360, 0));

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(197, 214, 181)),
                BorderFactory.createEmptyBorder(16, 16, 16, 16)
        ));

        addField(formPanel, "Player ID", idField);
        addField(formPanel, "Name", nameField);
        addField(formPanel, "Position", positionBox);
        addField(formPanel, "Age", ageField);
        addField(formPanel, "Jersey No.", jerseyField);
        addField(formPanel, "Matches", matchesField);
        addField(formPanel, "Goals", goalsField);
        addField(formPanel, "Search Position", searchPositionField);

        JPanel buttonPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(197, 214, 181)),
                BorderFactory.createEmptyBorder(16, 16, 16, 16)
        ));

        buttonPanel.add(createButton("Add Player", new Color(54, 116, 181), this::addPlayer));
        buttonPanel.add(createButton("Update Player", new Color(76, 149, 108), this::updatePlayer));
        buttonPanel.add(createButton("Remove Player", new Color(176, 73, 73), this::removePlayer));
        buttonPanel.add(createButton("Search by ID", new Color(113, 90, 153), this::searchById));
        buttonPanel.add(createButton("View All", new Color(66, 92, 102), this::refreshAllPlayers));
        buttonPanel.add(createButton("Top Scorer", new Color(189, 131, 57), this::showTopScorer));
        buttonPanel.add(createButton("Team Stats", new Color(36, 128, 120), this::showTeamStatistics));
        buttonPanel.add(createButton("Sort by Goals", new Color(100, 92, 79), this::showSortedByGoals));
        buttonPanel.add(createButton("Sort by Age", new Color(99, 123, 66), this::showSortedByAge));
        buttonPanel.add(createButton("Filter Position", new Color(135, 90, 90), this::searchByPosition));
        buttonPanel.add(createButton("Clear Fields", new Color(129, 129, 129), this::clearFields));

        wrapper.add(formPanel, BorderLayout.NORTH);
        wrapper.add(buttonPanel, BorderLayout.CENTER);
        return wrapper;
    }

    private JScrollPane buildOutputPanel() {
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 15));
        outputArea.setBackground(new Color(252, 252, 248));
        outputArea.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 0, 16, 16),
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(new Color(197, 214, 181)),
                        "Tracker Output"
                )
        ));
        return scrollPane;
    }

    private void addField(JPanel panel, String labelText, java.awt.Component field) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 13));
        panel.add(label);
        panel.add(field);
    }

    private JButton createButton(String text, Color color, Runnable action) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.addActionListener(event -> action.run());
        return button;
    }

    private void addPlayer() {
        try {
            Player player = buildPlayerFromFields();
            boolean added = teamTracker.addPlayer(player);
            if (!added) {
                showError("A player with this ID already exists.");
                return;
            }
            displayPlayers("Player added successfully. Current Squad", teamTracker.getAllPlayers());
        } catch (IllegalArgumentException exception) {
            showError(exception.getMessage());
        }
    }

    private void updatePlayer() {
        try {
            Player player = buildPlayerFromFields();
            boolean updated = teamTracker.updatePlayer(
                    player.getId(),
                    player.getName(),
                    player.getPosition(),
                    player.getAge(),
                    player.getJerseyNumber(),
                    player.getMatchesPlayed(),
                    player.getGoalsScored()
            );
            if (!updated) {
                showError("Player not found for update.");
                return;
            }
            outputArea.setText("Player updated successfully.\n\n" + teamTracker.findPlayerById(player.getId()));
        } catch (IllegalArgumentException exception) {
            showError(exception.getMessage());
        }
    }

    private void removePlayer() {
        try {
            int id = parseInteger(idField.getText(), "Player ID");
            boolean removed = teamTracker.removePlayer(id);
            if (!removed) {
                showError("Player not found for removal.");
                return;
            }
            displayPlayers("Player removed successfully. Current Squad", teamTracker.getAllPlayers());
        } catch (IllegalArgumentException exception) {
            showError(exception.getMessage());
        }
    }

    private void searchById() {
        try {
            int id = parseInteger(idField.getText(), "Player ID");
            Player player = teamTracker.findPlayerById(id);
            if (player == null) {
                outputArea.setText("No player found with ID: " + id);
                return;
            }
            outputArea.setText("Player found:\n\n" + player);
            populateFields(player);
        } catch (IllegalArgumentException exception) {
            showError(exception.getMessage());
        }
    }

    private void refreshAllPlayers() {
        displayPlayers("Current Squad", teamTracker.getAllPlayers());
    }

    private void showTopScorer() {
        Player topScorer = teamTracker.getTopScorer();
        if (topScorer == null) {
            outputArea.setText("No players available.");
            return;
        }
        outputArea.setText("Top Scorer\n\n" + topScorer);
        populateFields(topScorer);
    }

    private void showTeamStatistics() {
        outputArea.setText(String.format(
                "Team Statistics%n%nSquad Size: %d%nTotal Goals: %d%nAverage Age: %.2f",
                teamTracker.getSquadSize(),
                teamTracker.getTotalGoals(),
                teamTracker.getAverageAge()
        ));
    }

    private void showSortedByGoals() {
        displayPlayers("Players Sorted by Goals", teamTracker.getPlayersSortedByGoalsDesc());
    }

    private void showSortedByAge() {
        displayPlayers("Players Sorted by Age", teamTracker.getPlayersSortedByAgeAsc());
    }

    private void searchByPosition() {
        String position = searchPositionField.getText().trim();
        if (position.isEmpty()) {
            position = String.valueOf(positionBox.getSelectedItem());
        }
        displayPlayers("Players in Position: " + position, teamTracker.searchByPosition(position));
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        positionBox.setSelectedIndex(0);
        ageField.setText("");
        jerseyField.setText("");
        matchesField.setText("");
        goalsField.setText("");
        searchPositionField.setText("");
    }

    private Player buildPlayerFromFields() {
        int id = parseInteger(idField.getText(), "Player ID");
        String name = requireText(nameField.getText(), "Name");
        String position = String.valueOf(positionBox.getSelectedItem());
        int age = parseInteger(ageField.getText(), "Age");
        int jerseyNumber = parseInteger(jerseyField.getText(), "Jersey Number");
        int matchesPlayed = parseInteger(matchesField.getText(), "Matches Played");
        int goalsScored = parseInteger(goalsField.getText(), "Goals Scored");

        if (age <= 0 || jerseyNumber <= 0 || matchesPlayed < 0 || goalsScored < 0) {
            throw new IllegalArgumentException("Use positive values for age and jersey, and non-negative values for matches and goals.");
        }

        return new Player(id, name, position, age, jerseyNumber, matchesPlayed, goalsScored);
    }

    private void populateFields(Player player) {
        idField.setText(String.valueOf(player.getId()));
        nameField.setText(player.getName());
        positionBox.setSelectedItem(player.getPosition());
        ageField.setText(String.valueOf(player.getAge()));
        jerseyField.setText(String.valueOf(player.getJerseyNumber()));
        matchesField.setText(String.valueOf(player.getMatchesPlayed()));
        goalsField.setText(String.valueOf(player.getGoalsScored()));
    }

    private void displayPlayers(String title, List<Player> players) {
        StringBuilder builder = new StringBuilder(title).append("\n\n");
        if (players.isEmpty()) {
            builder.append("No players found.");
        } else {
            int count = 1;
            for (Player player : players) {
                builder.append(count++).append(". ").append(player).append("\n");
            }
        }
        outputArea.setText(builder.toString());
    }

    private int parseInteger(String value, String fieldName) {
        String text = requireText(value, fieldName);
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(fieldName + " must be a valid number.");
        }
    }

    private String requireText(String value, String fieldName) {
        String text = value == null ? "" : value.trim();
        if (text.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
        return text;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }
}
