package com.teamtracker;

import com.teamtracker.model.Player;
import com.teamtracker.service.TeamTracker;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final TeamTracker TEAM_TRACKER = new TeamTracker();

    public static void main(String[] args) {
        preloadSamplePlayers();
        int choice;

        do {
            printMenu();
            choice = readInt("Choose an option: ");

            switch (choice) {
                case 1 -> addPlayer();
                case 2 -> viewAllPlayers();
                case 3 -> searchPlayerById();
                case 4 -> updatePlayer();
                case 5 -> removePlayer();
                case 6 -> showTopScorer();
                case 7 -> showTeamStatistics();
                case 8 -> showPlayersByGoals();
                case 9 -> showPlayersByAge();
                case 10 -> searchByPosition();
                case 0 -> System.out.println("Exiting Football Team Tracker.");
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 0);
    }

    private static void printMenu() {
        System.out.println("\n=== Football Team Tracker ===");
        System.out.println("1. Add Player");
        System.out.println("2. View All Players");
        System.out.println("3. Search Player by ID");
        System.out.println("4. Update Player");
        System.out.println("5. Remove Player");
        System.out.println("6. Show Top Scorer");
        System.out.println("7. Show Team Statistics");
        System.out.println("8. Sort Players by Goals");
        System.out.println("9. Sort Players by Age");
        System.out.println("10. Search Players by Position");
        System.out.println("0. Exit");
    }

    private static void addPlayer() {
        Player player = readPlayerDetails();
        boolean added = TEAM_TRACKER.addPlayer(player);

        if (added) {
            System.out.println("Player added successfully.");
        } else {
            System.out.println("A player with this ID already exists.");
        }
    }

    private static void viewAllPlayers() {
        displayPlayers(TEAM_TRACKER.getAllPlayers(), "Current Squad");
    }

    private static void searchPlayerById() {
        int id = readInt("Enter player ID: ");
        Player player = TEAM_TRACKER.findPlayerById(id);

        if (player == null) {
            System.out.println("Player not found.");
        } else {
            System.out.println(player);
        }
    }

    private static void updatePlayer() {
        int id = readInt("Enter player ID to update: ");
        if (TEAM_TRACKER.findPlayerById(id) == null) {
            System.out.println("Player not found.");
            return;
        }

        System.out.println("Enter updated player details:");
        String name = readLine("Name: ");
        String position = readLine("Position: ");
        int age = readInt("Age: ");
        int jerseyNumber = readInt("Jersey Number: ");
        int matchesPlayed = readInt("Matches Played: ");
        int goalsScored = readInt("Goals Scored: ");

        TEAM_TRACKER.updatePlayer(id, name, position, age, jerseyNumber, matchesPlayed, goalsScored);
        System.out.println("Player updated successfully.");
    }

    private static void removePlayer() {
        int id = readInt("Enter player ID to remove: ");
        boolean removed = TEAM_TRACKER.removePlayer(id);

        if (removed) {
            System.out.println("Player removed successfully.");
        } else {
            System.out.println("Player not found.");
        }
    }

    private static void showTopScorer() {
        Player topScorer = TEAM_TRACKER.getTopScorer();
        if (topScorer == null) {
            System.out.println("No players available.");
        } else {
            System.out.println("Top Scorer:");
            System.out.println(topScorer);
        }
    }

    private static void showTeamStatistics() {
        System.out.println("\n=== Team Statistics ===");
        System.out.println("Squad Size: " + TEAM_TRACKER.getSquadSize());
        System.out.println("Total Goals: " + TEAM_TRACKER.getTotalGoals());
        System.out.printf("Average Age: %.2f%n", TEAM_TRACKER.getAverageAge());
    }

    private static void showPlayersByGoals() {
        displayPlayers(TEAM_TRACKER.getPlayersSortedByGoalsDesc(), "Players Sorted by Goals");
    }

    private static void showPlayersByAge() {
        displayPlayers(TEAM_TRACKER.getPlayersSortedByAgeAsc(), "Players Sorted by Age");
    }

    private static void searchByPosition() {
        String position = readLine("Enter position to search: ");
        displayPlayers(TEAM_TRACKER.searchByPosition(position), "Players in Position: " + position);
    }

    private static Player readPlayerDetails() {
        int id = readInt("ID: ");
        String name = readLine("Name: ");
        String position = readLine("Position: ");
        int age = readInt("Age: ");
        int jerseyNumber = readInt("Jersey Number: ");
        int matchesPlayed = readInt("Matches Played: ");
        int goalsScored = readInt("Goals Scored: ");

        return new Player(id, name, position, age, jerseyNumber, matchesPlayed, goalsScored);
    }

    private static void displayPlayers(List<Player> players, String title) {
        System.out.println("\n=== " + title + " ===");
        if (players.isEmpty()) {
            System.out.println("No players found.");
            return;
        }

        for (Player player : players) {
            System.out.println(player);
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = SCANNER.nextLine();
            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return SCANNER.nextLine().trim();
    }

    private static void preloadSamplePlayers() {
        TEAM_TRACKER.addPlayer(new Player(7, "Aarav Sharma", "Forward", 24, 9, 18, 11));
        TEAM_TRACKER.addPlayer(new Player(10, "Rohan Mehta", "Midfielder", 26, 8, 20, 6));
        TEAM_TRACKER.addPlayer(new Player(4, "Kabir Nair", "Defender", 23, 5, 22, 2));
        TEAM_TRACKER.addPlayer(new Player(1, "Vihaan Patel", "Goalkeeper", 28, 1, 21, 0));
    }
}
