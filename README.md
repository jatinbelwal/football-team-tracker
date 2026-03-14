# Football Team Tracker

Football Team Tracker is a Java console application for managing a football squad using core data structures and algorithms.

## Features

- Add, update, search, view, and remove players
- Find the top scorer instantly using tracked player stats
- View team statistics such as squad size, total goals, and average age
- Sort players by goals or age
- Filter players by position

## DSA Concepts Used

- `HashMap<Integer, Player>` for fast player lookup by ID
- `ArrayList<Player>` for flexible player collections and reporting
- Merge sort for ordered player reports
- Linear traversal for squad statistics and top-scorer analysis

## Compile

```powershell
javac -d out src\com\teamtracker\model\Player.java src\com\teamtracker\service\TeamTracker.java src\com\teamtracker\Main.java
```

## Run

```powershell
java -cp out com.teamtracker.Main
```
