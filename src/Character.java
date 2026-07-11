//Character Class:-

public abstract class Character extends Person implements Damageable {

    // Common Attributes:-
    protected int health;
    protected int attackPower;
    protected int defensePower;
    protected int level;

    // No-Arg Constructor:-
    public Character() {
    }

    // Parameterized Constructor #1:-
    public Character(String name, String gender, int age, int health, int attackPower, int defensePower, int level) {
        super(name, gender, age);
        this.health = health;
        this.attackPower = attackPower;
        this.defensePower = defensePower;
        this.level = level;
    }

    // Parameterized Constructor #2:-
    public Character(String name, int health, int attackPower, int defensePower, int level) {
        super(name);
        this.health = health;
        this.attackPower = attackPower;
        this.defensePower = defensePower;
        this.level = level;
    }

    // Is Alive Method:-
    public boolean isAlive() {
        return health > 0;
    }

    // Increase Health Method:-
    public int increaseHealth() {

        // Check If Health Increase Exceeds Max Health:
        if (health + GameConfig.healthIncreasePerLevel > GameConfig.maxHealth) {
            health = GameConfig.maxHealth;
            System.out.println(
                    GameConfig.GREEN + name + " Health Is Full! " + health + "/" + GameConfig.maxHealth
                            + GameConfig.RESET);
        }
        // Otherwise, Increase Health Normally:
        else {
            health += GameConfig.healthIncreasePerLevel;
            System.out.println(GameConfig.CYAN + name + " Health Increased To " + health + "/" + GameConfig.maxHealth
                    + " " + GameConfig.RESET);
        }
        // Return Updated Health:
        return health;
    }

    // Take Damage Method:-
    @Override
    public void takeDamage(int damage) {

        // Calculate Actual Damage After Defense:
        this.health = Math.max(0, this.health - damage);

        // Display Damage Taken:
        System.out.println(GameConfig.RED + name + " Takes " + damage + " Damage! "
                + "(" + this.health + " HP Left)" + GameConfig.RESET);
    }

    // Display Information Method:-
    public String displayInformation() {

        // Create A Health Bar:
        int filled = (int) (20.0 * this.health / GameConfig.maxHealth);
        String bar = "█".repeat(filled) + "░".repeat(20 - filled);
        String hpColor = this.health == GameConfig.maxHealth ? GameConfig.GREEN
                : this.health >= 60 ? GameConfig.YELLOW : GameConfig.RED;

        // Return Formatted Information:
        return GameConfig.CYAN + "╔══════════════════════╗\n" +
                "║  " + GameConfig.YELLOW + name + GameConfig.CYAN + "\n" +
                "╠══════════════════════╣\n" +
                "║ HP : " + hpColor + "[" + bar + "] " + this.health + "/" + GameConfig.maxHealth + GameConfig.CYAN
                + "\n" +
                "║ Attack  : " + GameConfig.YELLOW + this.attackPower + GameConfig.CYAN + "\n" +
                "║ Defense : " + GameConfig.YELLOW + defensePower + GameConfig.CYAN + "\n" +
                "║ Level   : " + GameConfig.YELLOW + level + GameConfig.CYAN + "\n" +
                "╚══════════════════════╝" + GameConfig.RESET;
    }

    // Abstract Method (attack):-
    public abstract void attack(Character target);
}
// End of Character Class.