import java.util.ArrayList;

public class ContainerItem extends Item {
    private ArrayList<Item> items;

    public ContainerItem(String name, String type, String description) {
        super(name, type, description);
        this.items = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public boolean hasItem(String name) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public Item removeItem(String name) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equalsIgnoreCase(name)) {
                return items.remove(i);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(getName());
        s.append(" [ ");
        s.append(getType());
        s.append(" ] : ");
        s.append(getDescription());
        s.append(" that contains:");
        for (Item item : items) {
            s.append("\n");
            s.append("+ ");
            s.append(item.getName());
        }
        return s.toString();
    }
}