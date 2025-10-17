![Banner](img/banner.png?raw=true)
=====

Components
=====
* Four type of ships of different length: Battleship (4), Cruiser (3), Destroyer (2), and Submarine (1).
* Ships are randomly placed in the 9x9 grid of ocean


Gameplay
=====
* Enter the row and column index separated by a comma to fire at the ocean


Demo
=====

Landing a hit on a ship at (0,0)

![alt text](img/hit.PNG?raw=true)


Hitting empty ocean at (1,1)

![alt text](img/miss.PNG?raw=true)


Hitting the last part of a cruiser (2,0)

![alt text](img/sank.PNG?raw=true)

Hitting the same cruiser again (2,0)

![alt text](img/sunken.PNG?raw=true)


Game over (a different run) - and I'm simply terrible

![alt text](img/gameover.PNG?raw=true)

## Upgrade to Java 21

This project can be compiled and run with Java 21 (LTS). If you can't use the automated Copilot upgrade tools, follow these manual steps:

1. Install a Java 21 JDK (Adoptium/Eclipse Temurin or another vendor). On Windows, download and run the installer from https://adoptium.net/ or use your package manager.

2. Verify the installation in PowerShell:

```
java -version
javac -version
```

Both commands should report a 21.x version.

3. From the repository root run the bundled build script which compiles sources targeting Java 21:

```
.\build.ps1
```

4. To run the main program after a successful compile:

```
java -cp bin battleship.BattleshipGame
```

If you use CI, set the runner's JDK to 21 and compile with `javac --release 21` or configure your build tool accordingly.
![Banner](img/banner.png?raw=true)
=====

Components
=====
* Four type of ships of different length: Battleship (4), Cruiser (3), Destroyer (2), and Submarine (1).
* Ships are randomly placed in the 9x9 grid of ocean


Gameplay
=====
* Enter the row and column index separated by a comma to fire at the ocean


Demo
=====

Landing a hit on a ship at (0,0)

![alt text](img/hit.PNG?raw=true)


Hitting empty ocean at (1,1)

![alt text](img/miss.PNG?raw=true)


Hitting the last part of a cruiser (2,0)

![alt text](img/sank.PNG?raw=true)

Hitting the same cruiser again (2,0)

![alt text](img/sunken.PNG?raw=true)


Game over (a different run) - and I'm simply terrible

![alt text](img/gameover.PNG?raw=true)