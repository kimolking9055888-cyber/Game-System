//Player Class:-

public class Player extends Character {

    // Attributes:-
    protected int experiencePoints;
    protected int goldPoints;

    // Parameterized Constructor:-
    public Player(String name, String gender, int age) {
        super(name, gender, age, GameConfig.maxHealth, GameConfig.defaultAttackPower,
                GameConfig.defaultDefensePower, GameConfig.defaultLevel);
        this.experiencePoints = GameConfig.defaultExperiencePoints;
        this.goldPoints = GameConfig.defaultGoldPoints;
    }

    // No-Arg Constructor:-
    public Player() {
        super();
        this.name = "Player";
        this.gender = "Unknown";
        this.age = 0;
        this.health = GameConfig.maxHealth;
        this.attackPower = GameConfig.defaultAttackPower;
        this.defensePower = GameConfig.defaultDefensePower;
        this.level = GameConfig.defaultLevel;
        this.experiencePoints = GameConfig.defaultExperiencePoints;
        this.goldPoints = GameConfig.defaultGoldPoints;
    }

    // Level Up Method:-
    public int levelUp() {

        // Check if max level reached:
        if (this.level >= GameConfig.maxLevel) {
            System.out.println(GameConfig.YELLOW + "⭐ Maximum Level Reached!" + GameConfig.RESET);
            return level;
        }

        // Check if enough XP to level up:
        if (experiencePoints >= GameConfig.experiencePointsPerLevel) {
            level++;
            experiencePoints -= GameConfig.experiencePointsPerLevel;
            increaseHealth();
            attackPower += GameConfig.attackPointsPerLevel;
            defensePower += GameConfig.defensePointsPerLevel;

            // Level Up Notification:
            System.out.println(GameConfig.GREEN
                    + "\n╔══════════════════════════╗"
                    + "\n║     🎉 LEVEL UP!  LVL " + level + "  ║"
                    + "\n╠══════════════════════════╣"
                    + "\n║  ⚔️  ATK +8  |  🛡️ DEF +4  ║"
                    + "\n╚══════════════════════════╝" + GameConfig.RESET);
        } else {
            int needed = GameConfig.experiencePointsPerLevel - experiencePoints;
            System.out.println(GameConfig.YELLOW + "Need " + needed + " more XP to level up." + GameConfig.RESET);
        }
        // Return the updated level:
        return level;
    }

    // Override Method (attack):-
    @Override
    public void attack(Character target) {

        // Check if target is already defeated:
        if (target.health <= 0) {
            System.out.println(GameConfig.CYAN + target.name + " Is Already Defeated!" + GameConfig.RESET);
            return;
        }

        // Calculate base damage and check for critical hit:
        int baseDamage = Math.max(1, this.attackPower - target.defensePower);
        boolean isCrit = Math.random() < GameConfig.critChance;
        int actualDamage = isCrit ? (int) (baseDamage * GameConfig.critMultiplier) : baseDamage;

        // Attack Notification:
        if (isCrit) {
            System.out.println(
                    GameConfig.PURPLE + "⚡ CRITICAL STRIKE! " + name + " deals massive damage!" + GameConfig.RESET);
        } else {
            System.out.println(GameConfig.YELLOW + "⚔️  " + name + " attacks!" + GameConfig.RESET);
        }

        // Apply damage to target:
        target.takeDamage(actualDamage);

        // Experience and Gold Rewards for Blocking:
        if (target.defensePower >= this.attackPower && !isCrit) {
            System.out.println(GameConfig.CYAN + "🛡️  " + target.name + " partially blocked!" + GameConfig.RESET);
            this.experiencePoints += GameConfig.experiencePerBlock;
            this.goldPoints += GameConfig.goldPerBlock;
        }

        // Check if target is defeated:
        if (target.health <= 0) {
            target.health = 0;
            System.out.println(GameConfig.RED + "💀 " + target.name + " Has Been Defeated!" + GameConfig.RESET);
            this.goldPoints += GameConfig.goldPerKill;
            this.experiencePoints += GameConfig.experiencePerKill;
            System.out.println(GameConfig.GREEN + "💰 +" + GameConfig.goldPerKill
                    + " Gold  |  ⭐ +" + GameConfig.experiencePerKill + " XP" + GameConfig.RESET);
        }
    }

    // Show Status Method:-
    public void showStatus() {

        // Calculate health percentage and create a visual health bar:
        double percentage = (double) this.health / GameConfig.maxHealth * 100;
        int filled = (int) (20.0 * this.health / GameConfig.maxHealth);
        String bar = "█".repeat(filled) + "░".repeat(20 - filled);

        // Determine color and status message based on health percentage:
        String color, status;
        if (this.health == GameConfig.maxHealth) {
            color = GameConfig.GREEN;
            status = "Perfect! 💚";
        } else if (this.health >= 70) {
            color = GameConfig.GREEN;
            status = "Good 💛";
        } else if (this.health >= 40) {
            color = GameConfig.YELLOW;
            status = "Caution! 🟡";
        } else if (this.health > 0) {
            color = GameConfig.RED;
            status = "Critical! ❤️‍🔥";
        } else {
            color = GameConfig.PURPLE;
            status = "Defeated 💀";
        }

        // Display the status information:
        System.out.println(color + "=== " + this.name + " ===" + GameConfig.RESET);
        System.out.println(color + "HP : [" + bar + "] " + this.health + "/" + GameConfig.maxHealth
                + " (" + (int) percentage + "%)" + GameConfig.RESET);
        System.out.println(color + "Status: " + status + GameConfig.RESET);
    }

    // Override displayInformation:-
    @Override
    public String displayInformation() {

        // Calculate health bar and color based on current health:
        int filled = (int) (20.0 * health / GameConfig.maxHealth);
        String bar = "█".repeat(filled) + "░".repeat(20 - filled);
        String hpColor = health == GameConfig.maxHealth ? GameConfig.GREEN
                : health >= 60 ? GameConfig.YELLOW : GameConfig.RED;

        // Calculate experience bar:
        int xpProgress = (int) (20.0 * experiencePoints / GameConfig.experiencePointsPerLevel);
        xpProgress = Math.min(xpProgress, 20);
        String xpBar = "▓".repeat(xpProgress) + "░".repeat(20 - xpProgress);

        // Return formatted information string:
        return GameConfig.CYAN
                + "╔════════════════════════════════╗\n"
                + "║ 🧙 " + GameConfig.YELLOW + String.format("%-28s", name + " (" + gender + ")") + GameConfig.CYAN
                + "║\n"
                + "╠════════════════════════════════╣\n"
                + "║ ❤️  HP  : " + hpColor + "[" + bar + "] " + health + "/" + GameConfig.maxHealth + GameConfig.CYAN
                + "\n"
                + "║ ⭐ XP  : " + GameConfig.GREEN + "[" + xpBar + "] " + experiencePoints + "/"
                + GameConfig.experiencePointsPerLevel + GameConfig.CYAN + "\n"
                + "╠════════════════════════════════╣\n"
                + "║ ⚔️ ATK : " + GameConfig.YELLOW + attackPower + GameConfig.CYAN + "\n"
                + "║ 🛡️ DEF : " + GameConfig.YELLOW + defensePower + GameConfig.CYAN + "\n"
                + "║ 📊 LVL : " + GameConfig.YELLOW + level + "/" + GameConfig.maxLevel + GameConfig.CYAN + "\n"
                + "║ 💰 Gold: " + GameConfig.GREEN + goldPoints + GameConfig.CYAN + "\n"
                + "║ 🎂 Age : " + GameConfig.WHITE + age + GameConfig.CYAN + "\n"
                + "╚════════════════════════════════╝" + GameConfig.RESET;
    }
}
// End of Player Class.