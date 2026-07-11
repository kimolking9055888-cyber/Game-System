//Item Class:-

public class Item {

    // Attributes:-
    protected String name;
    protected ItemType itemType;
    protected int quantity;
    protected int value;
    protected int price;

    // No-Arg Constructor:-
    public Item() {
        this.name = "Default Item";
        this.itemType = ItemType.HEALTH_POTION;
        this.quantity = 1;
        this.value = itemType.getValue();
        this.price = itemType.getPrice();
    }

    // Parameterized Constructor:-
    public Item(String name, ItemType itemType) {
        this.name = name;
        this.itemType = itemType;
        this.quantity = 1;
        this.value = itemType.getValue();
        this.price = itemType.getPrice();
    }

    // Use Method:-
    public void use(Player player) {

        // Check if item is in stock and player is valid:
        if (quantity <= 0) {
            System.out.println(name + GameConfig.RED + " Is Out Of Stock!");
            return;
        }

        if (player == null) {
            System.out.println(GameConfig.YELLOW + "Player Is Null!");
            return;
        }

        // Apply item effects based on item type:
        switch (itemType) {
            case HEALTH_POTION:
                int healAmount = value;
                int oldHealth = player.health;
                player.health += healAmount;
                if (player.health > GameConfig.maxHealth) {
                    player.health = GameConfig.maxHealth;
                }
                System.out.println(GameConfig.GREEN + player.name + " Used " + name + "! Healed "
                        + (player.health - oldHealth) + " HP!");
                break;

            case MANA_POTION:
                System.out.println(player.name + " Used " + name + "! Restored Mana!");
                break;

            case STRENGTH_POTION:
                player.attackPower += value;
                System.out.println(player.name + " Used " + name + "! Gained " + value + " Attack Power!");
                break;

            case DEFENSE_POTION:
                player.defensePower += value;
                System.out.println(player.name + " Used " + name + "! Gained " + value + " Defense Power!");
                break;

            case ANTIDOTE:
                System.out.println(player.name + " Used " + name + "! Cured All Status Effects!");
                break;

            case GOLD_COIN:
                player.goldPoints += value;
                System.out.println(player.name + " Used " + name + "! Gained " + value + " Gold Points!");
                break;

            default:
                System.out.println("Cannot Use " + name + "!");
                break;
        }

        // Decrease quantity after use:
        quantity--;
    }

    // Getters and Setters:-
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getValue() {
        return value;
    }

    public int getPrice() {
        return price;
    }
}
// End of Item Class.