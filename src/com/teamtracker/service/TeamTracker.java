package com.teamtracker.service;

import com.teamtracker.model.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamTracker {
    private final Map<Integer, Player> playersById = new HashMap<>();

    public boolean addPlayer(Player player) {
        if (playersById.containsKey(player.getId())) {
            return false;
        }
        playersById.put(player.getId(), player);
        return true;
    }

    public Player findPlayerById(int id) {
        return playersById.get(id);
    }

    public boolean updatePlayer(int id, String name, String position, int age, int jerseyNumber, int matchesPlayed, int goalsScored) {
        Player player = playersById.get(id);
        if (player == null) {
            return false;
        }

        player.setName(name);
        player.setPosition(position);
        player.setAge(age);
        player.setJerseyNumber(jerseyNumber);
        player.setMatchesPlayed(matchesPlayed);
        player.setGoalsScored(goalsScored);
        return true;
    }

    public boolean removePlayer(int id) {
        return playersById.remove(id) != null;
    }

    public List<Player> getAllPlayers() {
        return new ArrayList<>(playersById.values());
    }

    public List<Player> searchByPosition(String position) {
        List<Player> matches = new ArrayList<>();
        for (Player player : playersById.values()) {
            if (player.getPosition().equalsIgnoreCase(position)) {
                matches.add(player);
            }
        }
        return sortPlayers(matches, Comparator.comparing(Player::getName, String.CASE_INSENSITIVE_ORDER));
    }

    public Player getTopScorer() {
        Player topScorer = null;
        for (Player player : playersById.values()) {
            if (topScorer == null || player.getGoalsScored() > topScorer.getGoalsScored()) {
                topScorer = player;
            }
        }
        return topScorer;
    }

    public int getSquadSize() {
        return playersById.size();
    }

    public int getTotalGoals() {
        int totalGoals = 0;
        for (Player player : playersById.values()) {
            totalGoals += player.getGoalsScored();
        }
        return totalGoals;
    }

    public double getAverageAge() {
        if (playersById.isEmpty()) {
            return 0.0;
        }

        int totalAge = 0;
        for (Player player : playersById.values()) {
            totalAge += player.getAge();
        }
        return (double) totalAge / playersById.size();
    }

    public List<Player> getPlayersSortedByGoalsDesc() {
        return sortPlayers(getAllPlayers(), Comparator.comparingInt(Player::getGoalsScored).reversed());
    }

    public List<Player> getPlayersSortedByAgeAsc() {
        return sortPlayers(getAllPlayers(), Comparator.comparingInt(Player::getAge));
    }

    private List<Player> sortPlayers(List<Player> players, Comparator<Player> comparator) {
        if (players.size() <= 1) {
            return players;
        }
        return mergeSort(players, comparator);
    }

    private List<Player> mergeSort(List<Player> players, Comparator<Player> comparator) {
        if (players.size() <= 1) {
            return players;
        }

        int mid = players.size() / 2;
        List<Player> left = mergeSort(new ArrayList<>(players.subList(0, mid)), comparator);
        List<Player> right = mergeSort(new ArrayList<>(players.subList(mid, players.size())), comparator);

        return merge(left, right, comparator);
    }

    private List<Player> merge(List<Player> left, List<Player> right, Comparator<Player> comparator) {
        List<Player> merged = new ArrayList<>();
        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (comparator.compare(left.get(leftIndex), right.get(rightIndex)) <= 0) {
                merged.add(left.get(leftIndex++));
            } else {
                merged.add(right.get(rightIndex++));
            }
        }

        while (leftIndex < left.size()) {
            merged.add(left.get(leftIndex++));
        }

        while (rightIndex < right.size()) {
            merged.add(right.get(rightIndex++));
        }

        return merged;
    }
}
