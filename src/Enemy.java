//Enemy Class:-

public class Enemy extends Character {

    // Attributes:-
    protected int rewardExperiencePoints;
    protected int rewardGoldPoints;

    // No-Arg Constructor:-
    public Enemy() {
        super();
        this.name = "Enemy";
        this.rewardExperiencePoints = GameConfig.experiencePointsPerLevel;
        this.rewardGoldPoints       = GameConfig.goldPointsPerLevel;
    }

    // Parameterized Constructor:-
    public Enemy(String name, int health, int attackPower, int defensePower, int level, int rewardXP, int rewardGold) {
        super(name, health, attackPower, defensePower, level);
        this.rewardExperiencePoints = rewardXP;
        this.rewardGoldPoints       = rewardGold;
    }

    // Override Method (attack) :-
    @Override
    public void attack(Character target) {

        // Check if target is already defeated:
        if (target.health <= 0) {
            System.out.println(GameConfig.RED + target.name + " Is Already Defeated!" + GameConfig.RESET);
            return;
        }

        // Calculate damage with critical hit chance:
        int baseDamage   = Math.max(1, this.attackPower - target.defensePower);
        boolean isCrit   = Math.random() < GameConfig.critChance;
        int actualDamage = isCrit ? (int)(baseDamage * GameConfig.critMultiplier) : baseDamage;

        // Display attack message:
        if (isCrit) {
            System.out.println(GameConfig.PURPLE + "💥 CRITICAL HIT! " + name + " strikes with fury!" + GameConfig.RESET);
        } else {
            System.out.println(GameConfig.YELLOW + "👹 " + name + " attacks!" + GameConfig.RESET);
        }

        // Apply damage to target:
        target.takeDamage(actualDamage);

        // Display block message if target's defense is high:
        if (target.defensePower >= this.attackPower && !isCrit) {
            System.out.println(GameConfig.CYAN + "🛡️  " + target.name + " partially blocked!" + GameConfig.RESET);
        }
        // Check if target is defeated:
        if (target.health <= 0) {
            System.out.println(GameConfig.RED + "💀 " + target.name + " has been defeated!" + GameConfig.RESET);
        }
    }

    // Drop Reward Method:-
    public void dropReward(Player player) {

        // Check if enemy is still alive:
        if (this.isAlive()) {
            System.out.println(name + " Is Still Alive! No Rewards.");
            return;
        }
        // Grant rewards to player:
        player.experiencePoints += rewardExperiencePoints;
        player.goldPoints       += rewardGoldPoints;
        System.out.println(GameConfig.GREEN + "🏆 " + name + " defeated!");
        System.out.println("💰 +" + rewardGoldPoints + " Gold  |  ⭐ +" + rewardExperiencePoints + " XP" + GameConfig.RESET);
    }
}
//End of Enemy Class.