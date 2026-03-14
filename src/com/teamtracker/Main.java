package com.teamtracker;

import com.teamtracker.model.Player;
import com.teamtracker.service.TeamTracker;
import com.teamtracker.ui.TeamTrackerFrame;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        TeamTracker teamTracker = new TeamTracker();
        preloadSamplePlayers(teamTracker);

        SwingUtilities.invokeLater(() -> {
            TeamTrackerFrame frame = new TeamTrackerFrame(teamTracker);
            frame.setVisible(true);
        });
    }

    private static void preloadSamplePlayers(TeamTracker teamTracker) {
        teamTracker.addPlayer(new Player(7, "Aarav Sharma", "Forward", 24, 9, 18, 11));
        teamTracker.addPlayer(new Player(10, "Rohan Mehta", "Midfielder", 26, 8, 20, 6));
        teamTracker.addPlayer(new Player(4, "Kabir Nair", "Defender", 23, 5, 22, 2));
        teamTracker.addPlayer(new Player(1, "Vihaan Patel", "Goalkeeper", 28, 1, 21, 0));
    }
}
