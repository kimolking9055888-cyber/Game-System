//Game Class:-

// Imports:-
import java.util.*;

public class Game {

    // Attributes:-
    static Scanner input = new Scanner(System.in);
    static Player currentPlayer;

    // Start Game Method:-
    public static void startGame() {

        // Welcome Banner:
        System.out.println(GameConfig.CYAN
                + "╔══════════════════════════════════╗\n"
                + "║                                  ║\n"
                + "║     ⚔️  WELCOME TO THE GAME  ⚔️   ║\n"
                + "║                                  ║\n"
                + "╠══════════════════════════════════╣\n"
                + "║  " + GameConfig.YELLOW + "Initializing game resources..." + GameConfig.CYAN + "   ║\n"
                + "║  " + GameConfig.YELLOW + "Loading characters...        " + GameConfig.CYAN + "   ║\n"
                + "║  " + GameConfig.GREEN + "Ready to play!               " + GameConfig.CYAN + "   ║\n"
                + "╚══════════════════════════════════╝" + GameConfig.RESET);

        // Main Menu:
        System.out.println(GameConfig.YELLOW + "1) Start New Game\n2) Exit" + GameConfig.RESET);

        // Get user choice:
        System.out.print("Choose: ");
        int option = input.nextInt();
        input.nextLine();

        // Handle user choice:
        switch (option) {
            // Start New Game:
            case 1:
                startNewGame();
                break;
                // Exit:
            case 2:
                System.out.println(GameConfig.RED + "Goodbye! 👋" + GameConfig.RESET);
                System.exit(0);
                break;
                // Invalid:
            default:
                System.out.println(GameConfig.RED + "Invalid!" + GameConfig.RESET);
                startGame();
        }
    }

    // Start New Game:-
    public static void startNewGame() {

        // Character Creation Banner:
        System.out.println(GameConfig.CYAN + "\n╔══════════════════════════════════╗");
        System.out.println("║      🎮 START NEW GAME           ║");
        System.out.println("╚══════════════════════════════════╝" + GameConfig.RESET);
        System.out.println(GameConfig.YELLOW + "1) Create New Character");
        System.out.println("2) Play as Default Player" + GameConfig.RESET);

        // Get user choice:
        System.out.print("Choose: ");
        int option = input.nextInt();
        input.nextLine();

        // Handle user choice:
        switch (option) {
            // Create New Character:
            case 1:
                // Character Creation Banner:
                System.out.println(GameConfig.CYAN + "\n========⚙️[CHARACTER CREATION]⚙️========" + GameConfig.RESET);
                System.out.print("Enter Your Character Name: ");
                String name = input.nextLine();

                // Gender Selection:
                System.out.println(GameConfig.YELLOW + "1) Male  2) Female" + GameConfig.RESET);
                System.out.print("Choose Gender: ");
                int genderChoice = input.nextInt();
                input.nextLine();

                String gender = genderChoice == 1 ? "Male" : genderChoice == 2 ? "Female" : "Unknown";

                // Age Input:
                System.out.print(GameConfig.CYAN + "Enter Your Age: ");
                int age = input.nextInt();
                input.nextLine();

                // Age Validation:
                if (age >= 18) {
                    currentPlayer = new Player(name, gender, age);
                    System.out.println(GameConfig.GREEN + "\n✅ Character Created!" + GameConfig.RESET);
                    System.out.println(currentPlayer.displayInformation());
                } else {
                    System.out.println(
                            GameConfig.RED + "\n❌ You Must Be At Least 18 Years Old To Play!" + GameConfig.RESET);
                    System.out.println(GameConfig.YELLOW + "Exiting game..." + GameConfig.RESET);
                    System.exit(0);
                }
                break;
                // Play as Default Player:
            case 2:
                currentPlayer = new Player();
                break;
                // Invalid:
            default:
                System.out.println(GameConfig.RED + "Invalid! Starting with default Player." + GameConfig.RESET);
                currentPlayer = new Player();
                break;
        }
        // Start Game Loop:
        gameLoop();
    }

    // Main Menu:-
    public static void displayMainMenu() {

        // Main Menu Banner:
        System.out.println(GameConfig.CYAN
                + "\n╔══════════════════════════════════╗"
                + "\n║           📋 MAIN MENU           ║"
                + "\n╠══════════════════════════════════╣"
                + "\n║  " + GameConfig.YELLOW + "1) ⚔️ Search For Enemies        " + GameConfig.CYAN + "║"
                + "\n║  " + GameConfig.YELLOW + "2) 📊 Character Info             " + GameConfig.CYAN + "║"
                + "\n║  " + GameConfig.YELLOW + "3) 💚 View Status                " + GameConfig.CYAN + "║"
                + "\n║  " + GameConfig.YELLOW + "4) 🎒 Item Shop                  " + GameConfig.CYAN + "║"
                + "\n║  " + GameConfig.YELLOW + "5) 😴 Rest (Heal)                " + GameConfig.CYAN + "║"
                + "\n║  " + GameConfig.YELLOW + "6) 📈 Level Up                   " + GameConfig.CYAN + "║"
                + "\n║  " + GameConfig.YELLOW + "7) 🚪 Exit                       " + GameConfig.CYAN + "║"
                + "\n╚══════════════════════════════════╝" + GameConfig.RESET);
        System.out.print("Choose: ");
    }

    // Game Loop:-
    public static void gameLoop() {
        boolean running = true;

        // Main Game Loop:
        while (running && currentPlayer.isAlive()) {

            // Display Main Menu:-
            displayMainMenu();
            int choice = input.nextInt();
            input.nextLine();

            // Handle user choice:-
            switch (choice) {
                // Search For Enemies:
                case 1:
                    fight();
                    break;

                    // Character Info:
                case 2:
                    System.out.println(currentPlayer.displayInformation());
                    break;

                    // View Status:
                case 3:
                    currentPlayer.showStatus();
                    break;

                    // Item Shop:
                case 4:
                    showShop();
                    break;

                    // Rest (Heal):
                case 5:
                    rest();
                    break;

                    // Level Up:
                case 6:
                    currentPlayer.levelUp();
                    break;

                    // Exit:
                case 7:
                    System.out.println(GameConfig.YELLOW + "Thanks for playing! 👋" + GameConfig.RESET);
                    running = false;
                    break;

                    // Invalid:
                default:
                    System.out.println(GameConfig.RED + "Invalid!" + GameConfig.RESET);
            }
        }

        // Game Over Screen:
        if (!currentPlayer.isAlive()) {
            System.out.println(GameConfig.RED
                    + "\n╔══════════════════════════════╗"
                    + "\n║         💀  GAME OVER        ║"
                    + "\n╠══════════════════════════════╣"
                    + "\n║  You have been defeated!     ║"
                    + "\n║  Level  : " + currentPlayer.level
                    + "\n║  Gold   : " + currentPlayer.goldPoints
                    + "\n╚══════════════════════════════╝" + GameConfig.RESET);
        }
    }

    // Fight Method — 70% chance encounter:-
    public static void fight() {

        // Searching for enemies:
        System.out.println(GameConfig.YELLOW + "\n🔍 Searching for enemies..." + GameConfig.RESET);

        // Simulate searching time:
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
        }

        // 70% chance to encounter an enemy:
        if (Math.random() < 0.7) {
            spawnRandomEnemy();
        }
        // No enemy found:
        else {
            System.out.println(GameConfig.CYAN + "👀 You searched but found nothing..." + GameConfig.RESET);
        }
    }

    // Battle Method:-
    public static void battle(Enemy enemy) {

        // Battle Start Banner:
        boolean battleRunning = true;
        int turnCount = 0;

        // Battle Loop:
        while (battleRunning && currentPlayer.isAlive() && enemy.isAlive()) {
            turnCount++;

            // HP Bars
            int pFilled = (int) (20.0 * currentPlayer.health / GameConfig.maxHealth);
            String pBar = "█".repeat(pFilled) + "░".repeat(20 - pFilled);
            String pColor = currentPlayer.health >= 60 ? GameConfig.GREEN
                    : currentPlayer.health >= 30 ? GameConfig.YELLOW : GameConfig.RED;

            int eFilled = (int) (20.0 * enemy.health / GameConfig.maxHealth);
            String eBar = "█".repeat(eFilled) + "░".repeat(20 - eFilled);
            String eColor = enemy.health >= 60 ? GameConfig.GREEN
                    : enemy.health >= 30 ? GameConfig.YELLOW : GameConfig.RED;

            // Display Battle Status:
            System.out.println(GameConfig.CYAN
                    + "\n╔══════════════════════════════════════════════╗"
                    + "\n║           ⚔️  BATTLE  —  Turn " + String.format("%-14d", turnCount) + "║"
                    + "\n╠══════════════════════════════════════════════╣" + GameConfig.RESET);
            System.out.println(pColor + "  🧙 " + currentPlayer.name
                    + " HP: [" + pBar + "] " + currentPlayer.health + "/" + GameConfig.maxHealth + GameConfig.RESET);
            System.out.println(eColor + "  👹 " + enemy.name
                    + " HP: [" + eBar + "] " + enemy.health + "/" + GameConfig.maxHealth + GameConfig.RESET);
            System.out.println(GameConfig.CYAN
                    + "╠══════════════════════════════════════════════╣"
                    + "\n║  " + GameConfig.YELLOW + "1) ⚔️ Attack"
                    + "\n║  2) 🏃 Run Away"
                    + "\n║  3) 🧪 Use Item"
                    + "\n║  4) ⚡  Auto Fight"
                    + GameConfig.CYAN + "\n╚══════════════════════════════════════════════╝" + GameConfig.RESET);

            // Get user choice:
            System.out.print("Choose: ");
            int choice = input.nextInt();
            input.nextLine();
            System.out.println();

            // Handle user choice:
            switch (choice) {

                // Attack:
                case 1:
                    currentPlayer.attack(enemy);
                    if (enemy.isAlive())
                        enemy.attack(currentPlayer);
                    break;

                    // Run Away:
                case 2:
                    if (Math.random() < GameConfig.escapeChance) {
                        System.out.println(GameConfig.GREEN + "✅ Escaped successfully!" + GameConfig.RESET);
                        battleRunning = false;
                    } else {
                        System.out.println(GameConfig.RED + "❌ Escape failed!" + GameConfig.RESET);
                        enemy.attack(currentPlayer);
                    }
                    break;

                    // Use Item:
                case 3:
                    showItemMenu();
                    break;

                    // Auto Fight:
                case 4:
                    System.out.println(GameConfig.YELLOW + "⚡ Auto Fighting..." + GameConfig.RESET);
                    while (currentPlayer.isAlive() && enemy.isAlive()) {
                        currentPlayer.attack(enemy);
                        if (enemy.isAlive())
                            enemy.attack(currentPlayer);
                    }
                    break;

                    // Invalid:
                default:
                    System.out.println(GameConfig.RED + "Invalid!" + GameConfig.RESET);
            }
        }

        // Battle Result:
        if (!enemy.isAlive()) {
            enemy.dropReward(currentPlayer);
            currentPlayer.levelUp();
        }
    }

    // Spawn Enemy :-
    public static void spawnRandomEnemy() {

        // Generate a random enemy based on player level:
        Random rand = new Random();
        EnemyType[] all = EnemyType.values();

        // Filter enemies that are within ±1 level of the player:
        List<EnemyType> suitable = new ArrayList<>();
        for (EnemyType w : all) {
            if (w.getLevel() >= currentPlayer.level - 1 && w.getLevel() <= currentPlayer.level + 1) {
                suitable.add(w);
            }
        }
        if (suitable.isEmpty())
            suitable.addAll(Arrays.asList(all));

        EnemyType type = suitable.get(rand.nextInt(suitable.size()));
        Enemy enemy = new Enemy(type.getDisplayName(), type.getHealth(),
                type.getAttackPower(), type.getDefensePower(), type.getLevel(),
                type.getRewardXP(), type.getRewardGold());

        System.out.println(GameConfig.RED + "\n⚠️  A wild " + GameConfig.YELLOW + enemy.name
                + GameConfig.RED + " appeared! (Level " + enemy.level + ")" + GameConfig.RESET);
        battle(enemy);
    }

    // Shop:-
    public static void showShop() {

        // Shop Banner:
        ItemType[] list = ItemType.values();
        System.out.println(GameConfig.CYAN
                + "╔══════════════════════════════════════════╗"
                + "\n║           🎒 ITEM SHOP                   ║"
                + "\n╠══════════════════════════════════════════╣"
                + "\n║  " + GameConfig.GREEN + "Your Gold: " + currentPlayer.goldPoints + GameConfig.CYAN + "\n"
                + "╠══════════════════════════════════════════╣" + GameConfig.RESET);

        // Display items for sale:
        for (int i = 0; i < list.length; i++) {
            System.out.println(GameConfig.YELLOW + "  " + (i + 1) + ") " + GameConfig.CYAN + list[i].displayItem()
                    + GameConfig.RESET);
        }
        System.out.println(GameConfig.CYAN + "╚══════════════════════════════════════════╝" + GameConfig.RESET);

        // Get user choice:
        System.out.print("Buy item # (0 to cancel): ");
        int pick = input.nextInt();
        input.nextLine();

        // Handle purchase:
        if (pick >= 1 && pick <= list.length) {
            ItemType selected = list[pick - 1];
            if (currentPlayer.goldPoints >= selected.getPrice()) {
                currentPlayer.goldPoints -= selected.getPrice();
                Item item = new Item(selected.getDisplayName(), selected);
                item.use(currentPlayer);
            } else {
                System.out.println(
                        GameConfig.RED + "Not enough gold! Need " + selected.getPrice() + " Gold." + GameConfig.RESET);
            }
        }
    }

    // Item Menu (in battle):-
    public static void showItemMenu() {

        // Item Menu Banner:
        ItemType[] list = ItemType.values();
        System.out.println(GameConfig.CYAN + "Choose an item:" + GameConfig.RESET);

        // Display items for use:
        for (int i = 0; i < list.length; i++) {
            System.out.println(GameConfig.YELLOW + (i + 1) + ") " + list[i].displayItem() + GameConfig.RESET);
        }

        // Get user choice:
        System.out.print("Choose (0 to cancel): ");
        int pick = input.nextInt();
        input.nextLine();

        // Handle item usage:
        if (pick >= 1 && pick <= list.length) {
            ItemType selected = list[pick - 1];
            if (currentPlayer.goldPoints >= selected.getPrice()) {
                currentPlayer.goldPoints -= selected.getPrice();
                Item item = new Item(selected.getDisplayName(), selected);
                item.use(currentPlayer);
            } else {
                System.out.println(
                        GameConfig.RED + "Not enough gold! Need " + selected.getPrice() + " Gold." + GameConfig.RESET);
            }
        }
    }

    // Rest Method:-
    public static void rest() {

        // Calculate missing HP and cost:
        int missing = GameConfig.maxHealth - currentPlayer.health;
        if (missing == 0) {
            System.out.println(GameConfig.GREEN + "❤️  HP is already full!" + GameConfig.RESET);
            return;
        }

        // Cost is max(5, missing/2):
        int cost = Math.max(5, missing / 2);
        System.out.println(GameConfig.YELLOW + "💊 Healing " + missing + " HP costs " + cost + " Gold.");
        System.out.println("Your Gold: " + currentPlayer.goldPoints + GameConfig.RESET);

        // Get user confirmation:
        System.out.print("Confirm? (1=Yes / 2=No): ");
        int confirm = input.nextInt();
        input.nextLine();

        // Handle healing:
        if (confirm == 1) {
            if (currentPlayer.goldPoints >= cost) {
                currentPlayer.goldPoints -= cost;
                currentPlayer.health = GameConfig.maxHealth;
                System.out.println(GameConfig.GREEN + "✅ Fully healed! (-" + cost + " Gold)" + GameConfig.RESET);
            } else {
                System.out.println(GameConfig.RED + "❌ Not enough gold!" + GameConfig.RESET);
            }
        } else {
            System.out.println(GameConfig.YELLOW + "Cancelled." + GameConfig.RESET);
        }
    }
}
// End of Game Class.