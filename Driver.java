import java.util.Scanner;

public class Driver {
    static ContainerItem myInventory;
    static Location currLocation;

    static void createWorld() {
        Location parking = new Location("Parking", "An empty gravel lot. A dark trail leads North. You spot a discarded keycard on the ground.");
        Location cave = new Location("Cave", "Strange breathing echoes from the darkness. A path leads West to a cabin, East to water, and South back to your car. A broken motion tracker lies in the dirt.");
        Location cabin = new Location("Cabin", "Feels like an abandoned quarantine zone. The only way out is back East. A red emergency flare rests on a dusty table.");
        Location pond = new Location("Pond", "Black water swallowing every reflection. The cave is West, and a tall silhouette rises to the North. A rusty crowbar is half-buried in the mud.");
        Location watchtower = new Location("Watchtower", "A faint metallic hum vibrates through the wood. The path leads back South. A strange glowing module sits on the floorboards.");

        currLocation = parking;

        myInventory = new ContainerItem("Backpack", "Container", "A worn canvas backpack");

        parking.connect("north", cave);
        cave.connect("south", parking);

        cave.connect("west", cabin);
        cabin.connect("east", cave);

        cave.connect("east", pond);
        pond.connect("west", cave);

        pond.connect("north", watchtower);
        watchtower.connect("south", pond);

        parking.addItem(new Item("Keycard", "Tool", "A white plastic card with a faded barcode."));
        cave.addItem(new Item("Tracker", "Device", "A motion tracker. Its screen is cracked but faintly blinking."));
        cabin.addItem(new Item("Flare", "Consumable", "An unlit red signal flare."));
        pond.addItem(new Item("Crowbar", "Tool", "A heavy iron crowbar, stained with dark rust."));
        watchtower.addItem(new Item("Module", "Device", "A cylindrical power module pulsing with blue light."));
    }

    static boolean isValidDirection(String direction) {
        return direction.equals("north") || direction.equals("south")
                || direction.equals("east") || direction.equals("west");
    }

    static void printHelp() {
        System.out.println("Available commands:");
        System.out.println("look - Shows the location's description and items.");
        System.out.println("examine [NAME] - Shows the description of a specific item.");
        System.out.println("go [DIRECTION] - Moves your character north, east, south, or west.");
        System.out.println("inventory - Lists the items currently in your backpack.");
        System.out.println("take [NAME] - Picks up an item from the current location.");
        System.out.println("drop [NAME] - Drops an item from your backpack to the location.");
        System.out.println("help - Shows this list of commands.");
        System.out.println("quit - Exits the game.");
    }

    public static void main(String[] args) {
        createWorld();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to my forest adventure game!");

        boolean playing = true;
        while (playing) {
            System.out.print("Enter command: ");
            if (!scanner.hasNextLine()) {
                break;
            }
            String command = scanner.nextLine().trim().toLowerCase();
            String[] words = command.split("\\s+");

            switch (words[0]) {
                case "quit":
                    System.out.println("Exiting the game.");
                    playing = false;
                    break;

                case "look":
                    System.out.println(currLocation.getName() + " - " + currLocation.getDescription()
                            + " currently has the following items:");
                    for (int i = 0; i < currLocation.numItems(); i++) {
                        System.out.println("+ " + currLocation.getItem(i).getName());
                    }
                    break;

                case "examine":
                    if (words.length < 2) {
                        System.out.println("You did not tell me an item you want to examine.");
                    } else {
                        Item item = currLocation.getItem(words[1]);
                        if (item != null) {
                            System.out.println(item.toString());
                        } else {
                            System.out.println("Cannot find that item");
                        }
                    }
                    break;

                case "go":
                    if (words.length < 2) {
                        System.out.println("Go where?");
                    } else {
                        String direction = words[1];

                        if (!isValidDirection(direction)) {
                            System.out.println("'" + direction + "' is not a valid direction. Try north, south, east, or west.");
                        } else if (currLocation.canMove(direction)) {
                            currLocation = currLocation.getLocation(direction);
                            System.out.println("You walked " + direction);
                            System.out.println(currLocation.getName() + " - " + currLocation.getDescription());
                        } else {
                            System.out.println("You cannot go that way");
                        }
                    }
                    break;

                case "inventory":
                    System.out.println(myInventory.toString());
                    break;

                case "take":
                    if (words.length < 2) {
                        System.out.println("You did not tell me an item to take.");
                    } else {
                        String itemName = words[1];
                        // Remove the item from the current location
                        Item itemToTake = currLocation.removeItem(itemName);
                        if (itemToTake != null) {
                            myInventory.addItem(itemToTake);
                            System.out.println("You took the " + itemToTake.getName() + ".");
                        } else {
                            System.out.println("Cannot find that item here.");
                        }
                    }
                    break;

                case "drop":
                    if (words.length < 2) {
                        System.out.println("You did not tell me an item to drop.");
                    } else {
                        String itemName = words[1];
                        Item itemToDrop = myInventory.removeItem(itemName);
                        if (itemToDrop != null) {
                            currLocation.addItem(itemToDrop);
                            System.out.println("You dropped the " + itemToDrop.getName() + ".");
                        } else {
                            System.out.println("Cannot find that item in your inventory.");
                        }
                    }
                    break;

                case "help":
                    printHelp();
                    break;

                default:
                    System.out.println("I don't know how to do that.");
                    break;
            }
        }

        scanner.close();
    }
}