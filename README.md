
# Othello Game

## Table of Contents
1. [Introduction](#introduction)
2. [Features](#features)
3. [Installation](#installation)
4. [Usage](#usage)
5. [Rules](#rules)
6. [Code Structure](#code-structure)
7. [Contributing](#contributing)
8. [License](#license)
9. [Contact](#contact)

## Introduction
This Java program implements the classic board game Othello (also known as Reversi). The game is designed for two players who take turns placing discs on an 8x8 grid. The goal is to have the majority of discs on the board showing your color at the end of the game.

## Features
- Two-player mode
- Graphical User Interface (GUI) for interactive play
- Legal move indication (simple implementation)
- Score tracking (future implementation)

## Installation
To run this program, you need to have Java Development Kit (JDK) installed on your machine.

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/othello-game.git
   ```
2. **Navigate to the project directory**:
   ```bash
   cd othello-game
   ```
3. **Compile the program**:
   ```bash
   javac -d bin src/com/othello/*.java
   ```
4. **Run the Othello game**:
   ```bash
   java -cp bin com.othello.Main
   ```

## Usage
1. **Starting the Game**:
   - Run the Othello game as described in the installation section.
   - A window will open with the Othello board.

2. **Playing the Game**:
   - Players take turns placing their discs on the board.
   - The program will alternate between black and white discs.
   - Click on an empty square to place your disc.
   - The game ends when no more legal moves are available for both players, and the player with the most discs of their color wins.


## Rules
- Players take turns placing discs on the board with their assigned color facing up (black or white).
- A valid move must capture at least one of the opponent's discs.
- Captured discs are flipped to the player's color.
- The game ends when neither player can make a legal move.
- The player with the most discs of their color on the board at the end wins.

## Code Structure
The codebase is organized as follows:
```
othello-game/
│
├── src/
│   └── com/othello/
│       ├── Main.java
│       ├── GUI.java
│       └── Pressy.java
│
├── bin/
│
└── README.md
```

- `Main.java`: Entry point of the Othello game application.
- `GUI.java`: Handles the graphical user interface and game logic for Othello.
- `Pressy.java`: A simple demo program that shows a button which, when pressed, displays a message.

## Contributing
Contributions are welcome! If you find a bug or have a feature request, please open an issue. 

## License
This project is licensed under the MIT License.

## Contact
For any inquiries or feedback, please contact pan.daniilidis@gmail.com.
