# Java Text-Based Adventure Game Engine

## 🌲 Overview
This project is a Command-Line Interface (CLI) text-based adventure game engine built entirely in Java. Inspired by classic interactive fiction games, it features a custom parser that processes user commands to navigate a virtual world, interact with physical items, and manage a dynamic inventory. 

The current implementation features a mysterious forest adventure (including a Cave, Cabin, Pond, and Watchtower) but serves as a robust framework that can easily be expanded into any thematic setting.

## ⚙️ Technical Architecture
The engine is strictly designed using Object-Oriented Programming (OOP) principles, utilizing core Java Data Structures:

* **`Item.java`**: The base class encapsulating fundamental entity attributes (name, type, description).
* **`ContainerItem.java`**: A subclass of `Item` representing objects capable of storing other objects. It leverages an `ArrayList` to manage dynamic states. In this game, it acts as the player's personal "Backpack" (inventory).
* **`Location.java`**: Represents a node in the game's spatial graph. 
  * Uses a `HashMap<String, Location>` to map cardinal directions to adjacent memory addresses, creating a Directed Graph for navigation.
  * Uses an `ArrayList<Item>` to manage the loot/items currently dropped in that specific environment.
* **`Driver.java`**: The main controller maintaining global player states (`currLocation`, `myInventory`). It features an infinite game loop, reading standard input via `Scanner`, parsing multi-word commands using `String.split()`, and routing logic safely while preventing null-pointer exceptions and handling edge-case inputs.

## 🚀 How to Run

### Prerequisites
* [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/) installed on your machine.

### Compilation & Execution
1. Clone the repository and navigate to the project directory:
   ```bash
   git clone <your-repository-url>
   cd <repository-folder>
   ```
2. Compile the Java files:
   ```bash
   javac *.java
   ```
3. Run the main Driver class:
   ```bash
   java Driver
   ```

## 🎮 Supported Commands
The engine's command parser is case-insensitive and safely handles missing parameters or invalid targets. 

| Command | Usage Example | Description |
| :--- | :--- | :--- |
| **`go`** | `go north` | Moves the player to an adjacent location (north, south, east, west). |
| **`look`** | `look` | Inspects the current location, displaying its description and items on the ground. |
| **`examine`** | `examine tracker` | Displays detailed information about a specific item in the current room. |
| **`take`** | `take keycard` | Removes an item from the current location and adds it to your backpack. |
| **`drop`** | `drop flare` | Removes an item from your backpack and drops it at your current location. |
| **`inventory`**| `inventory` | Lists all items currently stored in your backpack. |
| **`help`** | `help` | Prints the list of available commands. |
| **`quit`** | `quit` | Safely terminates the game loop and exits the application. |

## 🗺️ World Map (Current Implementation)
The current world is configured as follows:
* **Parking** (Start) ↔ **Cave**
* **Cave** ↔ **Cabin** (West)
* **Cave** ↔ **Pond** (East)
* **Pond** ↔ **Watchtower** (North)

## 📄 License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.