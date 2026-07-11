//EnemyType Enum:-

public enum EnemyType {

    // Enemy types with their attributes:-
    // -> name, health, attack power, defense power, level, XP reward, and gold reward.

    // Level 1
    WOLF       ("Wolf",       30,  8,  3, 1,  5, 8),
    SNAKE      ("Snake",      25, 10,  4, 1,  5, 6),
    SPIDER     ("Spider",     35, 12,  5, 1,  6, 7),
    GOBLIN     ("Goblin",     50, 15,  8, 1, 10, 10),
    ZOMBIE     ("Zombie",     60, 12,  6, 1,  8, 12),

    // Level 2-3
    ORC        ("Orc",        65, 22, 12, 2, 15, 18),
    BANDIT     ("Bandit",     55, 25, 10, 2, 14, 16),
    WEREWOLF   ("Werewolf",   70, 28, 14, 3, 20, 22),

    // Level 4-5
    SKELETON   ("Skeleton",   75, 35, 20, 4, 25, 28),
    DARK_ELF   ("Dark Elf",   65, 40, 18, 4, 28, 30),
    OGRE       ("Ogre",       90, 38, 25, 5, 30, 35),

    // Level 6-7
    TROLL      ("Troll",      85, 50, 35, 6, 38, 42),
    VAMPIRE    ("Vampire",    80, 55, 30, 6, 40, 45),
    GOLEM      ("Golem",     100, 45, 45, 7, 45, 50),

    // Level 8-9
    DEMON      ("Demon",      90, 70, 50, 8, 55, 60),
    SHADOW     ("Shadow",     85, 75, 45, 8, 58, 65),
    LICH       ("Lich",       95, 80, 55, 9, 65, 70),

    // Level 10 - Boss
    DRAGON     ("Dragon",    100, 100, 70, 10, 80, 100);

    // Enum fields to store attributes for each enemy type:
    private final String displayName;
    private final int health;
    private final int attackPower;
    private final int defensePower;
    private final int level;
    private final int rewardXP;
    private final int rewardGold;

    // Constructor to initialize enum values for each enemy type:
    EnemyType(String displayName, int health, int attackPower, int defensePower,
              int level, int rewardXP, int rewardGold) {
        this.displayName  = displayName;
        this.health       = health;
        this.attackPower  = attackPower;
        this.defensePower = defensePower;
        this.level        = level;
        this.rewardXP     = rewardXP;
        this.rewardGold   = rewardGold;
    }

    // Getter methods to access the attributes of each enemy type:
    public String getDisplayName() { return displayName; }
    public int getHealth()         { return health; }
    public int getAttackPower()    { return attackPower; }
    public int getDefensePower()   { return defensePower; }
    public int getLevel()          { return level; }
    public int getRewardXP()       { return rewardXP; }
    public int getRewardGold()     { return rewardGold; }
}
//End of EnemyType Enum.