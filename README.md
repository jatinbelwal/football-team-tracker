# Football Team Tracker

Football Team Tracker is a Java Swing desktop application for managing a football squad using core data structures and algorithms.

## Features

- Add, update, search, view, and remove players
- Find the top scorer instantly using tracked player stats
- View team statistics such as squad size, total goals, and average age
- Sort players by goals or age
- Filter players by position
- Use a GUI dashboard built with Java Swing for easier interaction

## DSA Concepts Used

- `HashMap<Integer, Player>` for fast player lookup by ID
- `ArrayList<Player>` for flexible player collections and reporting
- Merge sort for ordered player reports
- Linear traversal for squad statistics and top-scorer analysis

## Project Structure

- `src/com/teamtracker/Main.java` launches the Swing app
- `src/com/teamtracker/model/Player.java` stores player data
- `src/com/teamtracker/service/TeamTracker.java` contains the DSA-backed logic
- `src/com/teamtracker/ui/TeamTrackerFrame.java` provides the desktop interface

## Compile

```powershell
javac -d out src\com\teamtracker\model\Player.java src\com\teamtracker\service\TeamTracker.java src\com\teamtracker\ui\TeamTrackerFrame.java src\com\teamtracker\Main.java
```

## Run

```powershell
java -cp out com.teamtracker.Main
```

## GUI Highlights

- Form fields for player details
- Buttons for add, update, delete, sort, search, and statistics
- Output panel to display squad reports and analytics
- Preloaded sample players for quick demo usage
