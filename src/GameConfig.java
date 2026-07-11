//GameConfig Class:-

public class GameConfig {

    /*
 * ╔══════════════════════════════════════╗
 * ║         PROJECT STRUCTURE            ║
 * ╠══════════════════════════════════════╣
 * ║  Person      → Abstract Class        ║
 * ║  Character   → Abstract Class        ║
 * ║  Player      → Player Class          ║
 * ║  Enemy       → Enemy Class           ║
 * ║  Game        → Game Logic            ║
 * ║  GameConfig  → Game Settings         ║
 * ║  EnemyType   → Enemy Types (Enum)    ║
 * ║  ItemType    → Item Types (Enum)     ║
 * ║  Item        → Item Usage            ║
 * ║  Main        → Entry Point           ║
 * ╚══════════════════════════════════════╝
 */

    /*
 * ╔══════════════════════════════════════════╗
 * ║           INHERITANCE STRUCTURE          ║
 * ╠══════════════════════════════════════════╣
 * ║                                          ║
 * ║   Person  (Abstract Class)               ║
 * ║      │                                   ║
 * ║      └── Character (Abstract Class)      ║
 * ║               │ implements Damageable    ║
 * ║               ├── Player (Player)        ║
 * ║               └── Enemy (Enemy)          ║
 * ║                                          ║
 * ║   Damageable  (Interface)                ║
 * ║      └── takeDamage()                    ║
 * ║                                          ║
 * ╚══════════════════════════════════════════╝
 */

    // Player Defaults:-
    protected static final int defaultLevel        = 1;
    protected static final int defaultAttackPower  = 15;
    protected static final int defaultDefensePower = 8;
    protected static final int defaultExperiencePoints = 0;
    protected static final int defaultGoldPoints   = 20;
    protected static final int maxHealth           = 100;
    protected static final int maxLevel            = 10;

    // Level Up Rewards:-
    protected static final int attackPointsPerLevel   = 8;
    protected static final int defensePointsPerLevel  = 4;
    protected static final int experiencePointsPerLevel = 50;
    protected static final int goldPointsPerLevel     = 15;
    protected static final int healthIncreasePerLevel = 20;

    // Combat Rewards:-
    protected static final int goldPerKill        = 15;
    protected static final int experiencePerKill  = 20;
    protected static final int goldPerBlock       = 3;
    protected static final int experiencePerBlock = 5;

    // Critical Hit:-
    protected static final double critChance    = 0.20; // 20%
    protected static final double critMultiplier = 1.5;

    // Escape Chance:-
    protected static final double escapeChance = 0.55; // 55%

    // Colors:-
    protected static final String RESET  = "\u001B[0m";
    protected static final String CYAN   = "\u001B[36m";
    protected static final String YELLOW = "\u001B[33m";
    protected static final String GREEN  = "\u001B[32m";
    protected static final String RED    = "\u001B[31m";
    protected static final String PURPLE = "\u001B[35m";
    protected static final String WHITE  = "\u001B[37m";
}
//End of GameConfig Class.