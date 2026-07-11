# Level-Based Action Game

A 2D action game built in Java, where the player controls a character progressing through multiple levels. Each level introduces a set of enemies that must be defeated before the player can advance to the next stage.

## 🎮 Overview

- The player moves through a series of levels, each with its own set of enemies.
- To advance to the next level, the player must defeat all enemies present in the current level.
- The game features a graphical user interface (GUI) for rendering gameplay, handling player input, and managing transitions between levels.

## ✨ Features

- Multiple levels with increasing difficulty
- Enemy encounters unique to each level
- Player progression system (must clear a level to move forward)
- Clean, object-oriented codebase (separate classes for the player, enemies, and levels)

## 🛠️ Technologies

- Language: Java
- Paradigm: Object-Oriented Programming (OOP)
- Interface: Graphical User Interface (GUI)

## 🕹️ Controls

- Movement: Keyboard (arrow keys / WASD)
- Actions: Mouse and/or keyboard input
- *(Exact key bindings — to be confirmed/updated)*

## 🚀 Getting Started

### Prerequisites

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/) installed on your machine
- An IDE such as [IntelliJ IDEA](https://www.jetbrains.com/idea/) (recommended), or any text editor + terminal

### Option 1 — Run with IntelliJ IDEA

1. Clone or download this repository.
2. Open the project folder in IntelliJ IDEA.
3. Locate the main class (entry point of the game) in the src folder.
4. Click the Run ▶ button next to the main class.

### Option 2 — Run from the terminal

1. Clone this repository:
     git clone https://github.com/kimolking9055888-cyber/Game-System.git
   cd Game-System
   2. Compile the source files:
     javac -d out src/*.java
   3. Run the game (replace MainClassName with the actual entry-point class name):
     java -cp out MainClassName
   
## 📁 Project Structure
Game-System/
├── src/          # Java source files (Player, Enemy, Level, and game logic classes)
├── out/          # Compiled class files
└── README.md

## 📌 Project Status

This project is a personal learning project built to practice Object-Oriented Programming concepts in Java, including class design, encapsulation, and structuring a multi-level game system.

## 👤 Author

Karim Abdelaziz Farouk
- LinkedIn: [linkedin.com/in/karim-abdelaziz-4b9784421](https://www.linkedin.com/in/karim-abdelaziz-4b9784421)
- Email: kimolking9055888@gmail.com
