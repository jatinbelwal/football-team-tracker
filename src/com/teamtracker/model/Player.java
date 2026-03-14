package com.teamtracker.model;

public class Player {
    private final int id;
    private String name;
    private String position;
    private int age;
    private int jerseyNumber;
    private int matchesPlayed;
    private int goalsScored;

    public Player(int id, String name, String position, int age, int jerseyNumber, int matchesPlayed, int goalsScored) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.age = age;
        this.jerseyNumber = jerseyNumber;
        this.matchesPlayed = matchesPlayed;
        this.goalsScored = goalsScored;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getAge() {
        return age;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %d | Name: %s | Position: %s | Age: %d | Jersey: %d | Matches: %d | Goals: %d",
                id, name, position, age, jerseyNumber, matchesPlayed, goalsScored
        );
    }
}
