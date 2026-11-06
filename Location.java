import java.util.ArrayList;
import java.util.HashMap;

public class Location {
    private String name;
    private String description;
    private ArrayList<Item> items;
    private HashMap<String, Location> connections;
    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        this.items = new ArrayList<>();
        this.connections = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public boolean hasItem(String itemName) {
        return (getItem(itemName) != null);
    }

    public Item getItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public Item getItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null;
    }

    public int numItems() {
        return items.size();
    }

    public Item removeItem(String itemName) {
        Item foundItem = getItem(itemName);
        if (foundItem != null){
            items.remove(foundItem);
            return foundItem;
        }
        return null;
    }

    public void connect(String direction, Location location){
        connections.put(direction, location);
    }

    public boolean canMove(String direction){
        return connections.containsKey(direction);
    }

    public Location getLocation(String direction){
        if (canMove(direction) == true){
            return connections.get(direction);
        }
        return null;
    }
}