//ItemType Enum:-

public enum ItemType {

    // Enum Constants:-
    HEALTH_POTION("Health Potion ", 30, 50),
    MANA_POTION("Mana Potion ", 20, 40),
    STRENGTH_POTION("Strength Potion ", 25, 60),
    DEFENSE_POTION("Defense Potion ", 25, 55),
    ANTIDOTE("Antidote ", 15, 30),
    REVIVAL("Revival Stone ", 100, 200),
    GOLD_COIN("Gold Coin ", 10, 15);

    // Attributes For Each Item Type: Display Name, Value, Price.
    private final String displayName;
    private final int value;
    private final int price;

    // Constructor For ItemType Enum.
    ItemType(String displayName, int value, int price) {
        this.displayName = displayName;
        this.value = value;
        this.price = price;
    }

    // Method To Display Item Information In A Readable Format:-
    public String displayItem() {

        // Using String.format to:
        // align the display name, value, and price in a neat format.
        return String.format("%-22s | Val: %-3d | Price: %d", displayName, value, price);
    }

    // Getters For Each Attribute:-
    public String getDisplayName() {
        return displayName;
    }

    public int getValue() {
        return value;
    }

    public int getPrice() {
        return price;
    }
}
// End of ItemType Enum.