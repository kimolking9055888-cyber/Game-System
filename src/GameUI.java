// GameUI Class:-

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameUI extends JFrame {

    // ── Color Palette ──
    private static final Color BG        = new Color(8, 10, 18);
    private static final Color SURFACE   = new Color(16, 20, 36);
    private static final Color SURFACE2  = new Color(22, 28, 48);
    private static final Color BORDER    = new Color(45, 55, 95);
    private static final Color GOLD      = new Color(255, 195, 40);
    private static final Color GOLD_DIM  = new Color(180, 130, 20);
    private static final Color GREEN     = new Color(40, 200, 100);
    private static final Color RED       = new Color(220, 55, 55);
    private static final Color BLUE      = new Color(55, 115, 225);
    private static final Color PURPLE    = new Color(155, 75, 220);
    private static final Color ORANGE    = new Color(255, 140, 0);
    private static final Color TEXT      = new Color(210, 215, 235);
    private static final Color MUTED     = new Color(110, 120, 155);
    private static final Color HP_GREEN  = new Color(40, 200, 100);
    private static final Color HP_YELLOW = new Color(240, 190, 40);
    private static final Color HP_RED    = new Color(220, 55, 55);
    private static final Color XP_COLOR  = new Color(55, 150, 255);

    // ── State ──
    private Player player;
    private Enemy enemy;
    private boolean inBattle  = false;
    private int     turnCount = 0;
    private int     kills     = 0;

    // ── Layout ──
    private JPanel     root;
    private CardLayout cards;

    // ── Game Panel ──
    private JTextArea    gameLog;
    private JLabel       gName, gLevel, gGold, gAtk, gDef, gStatus, gKills;
    private JProgressBar gHP, gXP;
    private JLabel       gHPTxt, gXPTxt;

    // ── Battle Panel ──
    private JTextArea    battleLog;
    private JLabel       bHeroName, bEnemyName, bTurnLbl;
    private JProgressBar bHeroHP, bEnemyHP;
    private JLabel       bHeroHPTxt, bEnemyHPTxt;
    private JLabel       bEnemyStats;

    public GameUI() {
        setTitle("Game System  |  An Epic Adventure");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(BG);

        cards = new CardLayout();
        root  = new JPanel(cards);
        root.setBackground(BG);

        root.add(buildWelcome(), "WELCOME");
        root.add(buildCreate(),  "CREATE");
        root.add(buildGame(),    "GAME");
        root.add(buildBattle(),  "BATTLE");

        add(root);
        setVisible(true);
    }

    // ══════════════════════════════════
    //  WELCOME SCREEN
    // ══════════════════════════════════
    private JPanel buildWelcome() {
        JPanel p = panel(new GridBagLayout(), BG);
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0; g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(14, 60, 14, 60);

        // Top border line
        g.gridy = 0;
        p.add(hLine(GOLD, 500), g);

        // Title
        g.gridy = 1;
        JLabel title = label("GAME  SYSTEM", new Font("Monospaced", Font.BOLD, 54), GOLD);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        p.add(title, g);

        // Sub
        g.gridy = 2;
        JLabel sub = label("An Epic Adventure Awaits You", new Font("Monospaced", Font.ITALIC, 16), MUTED);
        sub.setHorizontalAlignment(SwingConstants.CENTER);
        p.add(sub, g);

        g.gridy = 3; p.add(hLine(BORDER, 400), g);

        // Buttons
        g.gridy = 4;
        JButton start = btn("  START NEW GAME  ", GREEN, 17);
        start.addActionListener(e -> cards.show(root, "CREATE"));
        p.add(start, g);

        g.gridy = 5;
        JButton exit = btn("  EXIT  ", RED, 14);
        exit.addActionListener(e -> System.exit(0));
        p.add(exit, g);

        g.gridy = 6; p.add(hLine(BORDER, 400), g);

        g.gridy = 7;
        JLabel ver = label("v2.0  Built with Java Swing", new Font("Monospaced", Font.PLAIN, 11), BORDER);
        ver.setHorizontalAlignment(SwingConstants.CENTER);
        p.add(ver, g);

        return p;
    }

    // ══════════════════════════════════
    //  CREATE CHARACTER
    // ══════════════════════════════════
    private JPanel buildCreate() {
        JPanel outer = panel(new GridBagLayout(), BG);
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(9, 40, 9, 40);
        g.fill   = GridBagConstraints.HORIZONTAL;

        // Title
        g.gridx = 0; g.gridy = 0; g.gridwidth = 2;
        JLabel title = label("CHARACTER CREATION", new Font("Monospaced", Font.BOLD, 28), GOLD);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        outer.add(title, g);
        g.gridy = 1; outer.add(hLine(BORDER, 420), g);
        g.gridwidth = 1;

        // Fields
        JTextField nameF = field();
        JTextField ageF  = field();
        JComboBox<String> genderBox = combo("Male", "Female");
        JComboBox<String> classBox  = combo("Warrior", "Mage", "Rogue", "Archer");

        addRow(outer, g, 2, "Name:",   nameF);
        addRow(outer, g, 3, "Gender:", genderBox);
        addRow(outer, g, 4, "Age:",    ageF);
        addRow(outer, g, 5, "Class:",  classBox);

        // Error
        g.gridx=0; g.gridy=6; g.gridwidth=2;
        JLabel err = label("", new Font("Monospaced", Font.PLAIN, 13), RED);
        err.setHorizontalAlignment(SwingConstants.CENTER);
        outer.add(err, g);

        // Buttons
        g.gridy=7;
        JButton createBtn = btn("  CREATE CHARACTER  ", GREEN, 15);
        outer.add(createBtn, g);

        g.gridy=8;
        JButton back = btn("  BACK  ", BLUE, 13);
        back.addActionListener(e -> cards.show(root, "WELCOME"));
        outer.add(back, g);

        createBtn.addActionListener(e -> {
            String name   = nameF.getText().trim();
            String gender = (String) genderBox.getSelectedItem();
            String ageStr = ageF.getText().trim();
            if (name.isEmpty()) { err.setText("Please enter your name!"); return; }
            try {
                int age = Integer.parseInt(ageStr);
                if (age < 18) { err.setText("You must be 18+ to play!"); return; }
                player = new Player(name, gender, age);
                kills  = 0;
                refreshGame();
                cards.show(root, "GAME");
                glog("[SYSTEM] Welcome, " + name + "! (Class: " + classBox.getSelectedItem() + ")");
                glog("[SYSTEM] Your adventure begins... Good luck!");
                glog("--------------------------------------------------");
            } catch (NumberFormatException ex) {
                err.setText("Age must be a valid number!");
            }
        });

        return outer;
    }

    // ══════════════════════════════════
    //  MAIN GAME PANEL
    // ══════════════════════════════════
    private JPanel buildGame() {
        JPanel p = panel(new BorderLayout(8, 8), BG);
        p.setBorder(new EmptyBorder(10, 10, 10, 10));

        // ── Stats Panel (TOP) ──
        JPanel stats = panel(new BorderLayout(0,4), SURFACE);
        stats.setBorder(titledBorder(" HERO STATS ", GOLD));
        stats.setBorder(BorderFactory.createCompoundBorder(
            titledBorder(" HERO STATS ", GOLD),
            new EmptyBorder(6,10,8,10)));

        // Row 1: labels
        JPanel row1 = panel(new GridLayout(1,6,12,0), SURFACE);
        gName  = label("Name: -",   new Font("Monospaced", Font.BOLD, 13),   GOLD);
        gLevel = label("Level: -",  new Font("Monospaced", Font.PLAIN, 13),  TEXT);
        gGold  = label("Gold: -",   new Font("Monospaced", Font.PLAIN, 13),  new Color(255,200,50));
        gAtk   = label("ATK: -",    new Font("Monospaced", Font.PLAIN, 13),  RED);
        gDef   = label("DEF: -",    new Font("Monospaced", Font.PLAIN, 13),  BLUE);
        gKills = label("Kills: 0",  new Font("Monospaced", Font.PLAIN, 13),  PURPLE);
        row1.add(gName); row1.add(gLevel); row1.add(gGold);
        row1.add(gAtk);  row1.add(gDef);  row1.add(gKills);

        // Row 2: bars
        JPanel row2 = panel(new GridLayout(1,4,8,0), SURFACE);
        gHP    = bar(HP_GREEN, 100);
        gXP    = bar(XP_COLOR, 100);
        gHPTxt = label("HP: -", new Font("Monospaced", Font.PLAIN, 12), TEXT);
        gXPTxt = label("XP: -", new Font("Monospaced", Font.PLAIN, 12), XP_COLOR);
        row2.add(gHP); row2.add(gHPTxt); row2.add(gXP); row2.add(gXPTxt);

        JPanel statsRows = panel(new GridLayout(2,1,0,6), SURFACE);
        statsRows.add(row1); statsRows.add(row2);
        stats.add(statsRows);
        p.add(stats, BorderLayout.NORTH);

        // ── Log (CENTER) ──
        gameLog = log();
        JScrollPane sp = scroll(gameLog, " ACTIVITY LOG ", BLUE);
        p.add(sp, BorderLayout.CENTER);

        // ── Buttons (BOTTOM) ──
        JPanel btnRow1 = panel(new GridLayout(1,4,8,0), BG);
        JPanel btnRow2 = panel(new GridLayout(1,4,8,0), BG);
        JPanel btnAll  = panel(new BorderLayout(0,8), BG);
        btnAll.setBorder(new EmptyBorder(8,0,0,0));

        JButton bFight  = btn("SEARCH ENEMY",    RED,    13);
        JButton bInfo   = btn("CHARACTER INFO",  BLUE,   13);
        JButton bStatus = btn("VIEW STATUS",     GREEN,  13);
        JButton bShop   = btn("ITEM SHOP",       GOLD_DIM, 13);
        JButton bRest   = btn("REST / HEAL",     PURPLE, 13);
        JButton bLevel  = btn("LEVEL UP",        ORANGE, 13);
        JButton bClear  = btn("CLEAR LOG",       MUTED,  13);
        JButton bExit   = btn("EXIT GAME",       RED,    13);

        btnRow1.add(bFight); btnRow1.add(bInfo); btnRow1.add(bStatus); btnRow1.add(bShop);
        btnRow2.add(bRest);  btnRow2.add(bLevel); btnRow2.add(bClear); btnRow2.add(bExit);
        btnAll.add(btnRow1, BorderLayout.NORTH);
        btnAll.add(btnRow2, BorderLayout.SOUTH);
        p.add(btnAll, BorderLayout.SOUTH);

        bFight.addActionListener(e  -> fight());
        bInfo.addActionListener(e   -> showInfoDialog());
        bStatus.addActionListener(e -> showStatusDialog());
        bShop.addActionListener(e   -> showShopDialog(false));
        bRest.addActionListener(e   -> rest());
        bLevel.addActionListener(e  -> { player.levelUp(); refreshGame(); glog("[SYSTEM] Level Up attempted."); });
        bClear.addActionListener(e  -> gameLog.setText(""));
        bExit.addActionListener(e   -> confirmExit());

        return p;
    }

    // ══════════════════════════════════
    //  BATTLE PANEL
    // ══════════════════════════════════
    private JPanel buildBattle() {
        JPanel p = panel(new BorderLayout(8,8), BG);
        p.setBorder(new EmptyBorder(10,10,10,10));

        // ── Battle Header (TOP) ──
        JPanel header = panel(new GridBagLayout(), SURFACE);
        header.setBorder(BorderFactory.createCompoundBorder(
            titledBorder(" BATTLE ARENA ", RED),
            new EmptyBorder(6,14,8,14)));
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(4,10,4,10);

        bHeroName    = label("Player",    new Font("Monospaced",Font.BOLD,15), GREEN);
        bTurnLbl     = label("Turn 0",  new Font("Monospaced",Font.BOLD,15), GOLD);
        bEnemyName   = label("Enemy",   new Font("Monospaced",Font.BOLD,15), RED);
        bHeroHP      = bar(HP_GREEN, 100);
        bEnemyHP     = bar(HP_RED,   100);
        bHeroHPTxt   = label("100/100", new Font("Monospaced",Font.PLAIN,12), TEXT);
        bEnemyHPTxt  = label("100/100", new Font("Monospaced",Font.PLAIN,12), TEXT);
        bEnemyStats  = label("ATK: -  DEF: -", new Font("Monospaced",Font.PLAIN,12), MUTED);

        // Row 0
        g.gridy=0;
        g.gridx=0; g.anchor=GridBagConstraints.WEST;  header.add(bHeroName,  g);
        g.gridx=1; g.anchor=GridBagConstraints.CENTER;header.add(bTurnLbl,   g);
        g.gridx=2; g.anchor=GridBagConstraints.EAST;  header.add(bEnemyName, g);

        // Row 1 - HP bars
        g.gridy=1; g.fill=GridBagConstraints.HORIZONTAL; g.weightx=1;
        g.gridx=0; g.anchor=GridBagConstraints.WEST;  header.add(bHeroHP,  g);
        g.gridx=1; g.fill=GridBagConstraints.NONE; g.weightx=0;
        JLabel vs = label(" VS ", new Font("Monospaced",Font.BOLD,14), GOLD);
        header.add(vs, g);
        g.gridx=2; g.fill=GridBagConstraints.HORIZONTAL; g.weightx=1;
        g.anchor=GridBagConstraints.EAST; header.add(bEnemyHP, g);

        // Row 2 - HP text
        g.gridy=2; g.fill=GridBagConstraints.NONE; g.weightx=0;
        g.gridx=0; g.anchor=GridBagConstraints.WEST;  header.add(bHeroHPTxt,  g);
        g.gridx=2; g.anchor=GridBagConstraints.EAST;  header.add(bEnemyHPTxt, g);

        // Row 3 - enemy stats
        g.gridy=3; g.gridx=1; g.anchor=GridBagConstraints.CENTER;
        header.add(bEnemyStats, g);

        p.add(header, BorderLayout.NORTH);

        // ── Battle Log (CENTER) ──
        battleLog = log();
        JScrollPane sp = scroll(battleLog, " BATTLE LOG ", RED);
        p.add(sp, BorderLayout.CENTER);

        // ── Battle Buttons (BOTTOM) ──
        JPanel btns = panel(new GridLayout(1,4,10,0), BG);
        btns.setBorder(new EmptyBorder(8,0,0,0));

        JButton bAtk  = btn("ATTACK",     RED,    15);
        JButton bRun  = btn("RUN AWAY",   BLUE,   15);
        JButton bItem = btn("USE ITEM",   PURPLE, 15);
        JButton bAuto = btn("AUTO FIGHT", ORANGE, 15);

        btns.add(bAtk); btns.add(bRun); btns.add(bItem); btns.add(bAuto);
        p.add(btns, BorderLayout.SOUTH);

        bAtk.addActionListener(e -> {
            if (!inBattle) return;
            turnCount++;
            bTurnLbl.setText("Turn " + turnCount);
            blog("--- Turn " + turnCount + " ---");
            doAttack();
        });

        bRun.addActionListener(e -> {
            if (!inBattle) return;
            if (Math.random() < GameConfig.escapeChance) {
                blog("[ESCAPE] Successfully escaped!");
                endBattle();
            } else {
                blog("[ESCAPE] Failed to escape!");
                enemy.attack(player);
                refreshBattle();
                checkEnd();
            }
        });

        bItem.addActionListener(e -> { if (inBattle) showShopDialog(true); });

        bAuto.addActionListener(e -> {
            if (!inBattle) return;
            blog("[AUTO] Auto-fighting until one side falls...");
            while (player.isAlive() && enemy.isAlive()) {
                turnCount++;
                player.attack(enemy);
                if (enemy.isAlive()) enemy.attack(player);
            }
            refreshBattle();
            checkEnd();
        });

        return p;
    }

    // ══════════════════════════════════
    //  GAME LOGIC
    // ══════════════════════════════════
    private void fight() {
        glog("[SEARCH] Searching for enemies...");
        if (Math.random() < 0.70) spawnEnemy();
        else glog("[SEARCH] You found nothing this time. Try again!");
    }

    private void spawnEnemy() {
        EnemyType[] all = EnemyType.values();
        List<EnemyType> pool = new ArrayList<>();
        for (EnemyType w : all)
            if (w.getLevel() >= player.level - 1 && w.getLevel() <= player.level + 1)
                pool.add(w);
        if (pool.isEmpty()) pool.addAll(Arrays.asList(all));

        EnemyType t = pool.get(new Random().nextInt(pool.size()));
        enemy     = new Enemy(t.getDisplayName(), t.getHealth(),
                t.getAttackPower(), t.getDefensePower(), t.getLevel(),
                t.getRewardXP(), t.getRewardGold());
        inBattle  = true;
        turnCount = 0;

        battleLog.setText("");
        refreshBattle();
        blog("=== A wild " + enemy.name.toUpperCase() + " appeared! (Level " + enemy.level + ") ===");
        blog("[ENEMY]  HP:" + enemy.health + "  ATK:" + enemy.attackPower + "  DEF:" + enemy.defensePower);
        blog("[SYSTEM] Choose your action!");
        blog("--------------------------------------------------");

        glog("[BATTLE] Entering battle with " + enemy.name + " (Lv." + enemy.level + ")...");
        cards.show(root, "BATTLE");
    }

    private void doAttack() {
        player.attack(enemy);
        if (enemy.isAlive()) enemy.attack(player);
        refreshBattle();
        checkEnd();
    }

    private void rest() {
        int miss = GameConfig.maxHealth - player.health;
        if (miss == 0) { glog("[REST] HP is already full!"); return; }
        int cost = Math.max(5, miss / 2);
        int ok = JOptionPane.showConfirmDialog(this,
                "Heal " + miss + " HP for " + cost + " Gold?\nYour Gold: " + player.goldPoints,
                "Rest & Recover", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            if (player.goldPoints >= cost) {
                player.goldPoints -= cost;
                player.health = GameConfig.maxHealth;
                glog("[REST] Fully healed!  (-" + cost + " Gold)");
                refreshGame();
            } else glog("[REST] Not enough gold! Need " + cost + " Gold.");
        }
    }

    private void checkEnd() {
        if (!enemy.isAlive()) {
            kills++;
            enemy.dropReward(player);
            player.levelUp();
            blog("=== VICTORY! " + enemy.name + " defeated! ===");
            blog("[REWARD] +" + enemy.rewardGoldPoints + " Gold  |  +" + enemy.rewardExperiencePoints + " XP");
            endBattle();
        } else if (!player.isAlive()) {
            blog("=== YOU HAVE BEEN DEFEATED... ===");
            JOptionPane.showMessageDialog(this,
                    "GAME OVER\n\nName:   " + player.name +
                    "\nLevel:  " + player.level +
                    "\nKills:  " + kills +
                    "\nGold:   " + player.goldPoints,
                    "Game Over", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    private void endBattle() {
        inBattle = false;
        enemy    = null;
        refreshGame();
        cards.show(root, "GAME");
        glog("[SYSTEM] Returned to world map. Kills: " + kills);
    }

    // ── Dialogs ──
    private void showInfoDialog() {
        JPanel dp = panel(new GridLayout(0,2,10,8), SURFACE);
        dp.setBorder(new EmptyBorder(14,20,14,20));
        infoRow(dp,"Name",    player.name);
        infoRow(dp,"Gender",  player.gender);
        infoRow(dp,"Age",     String.valueOf(player.age));
        infoRow(dp,"Level",   player.level+" / "+GameConfig.maxLevel);
        infoRow(dp,"HP",      player.health+" / "+GameConfig.maxHealth);
        infoRow(dp,"ATK",     String.valueOf(player.attackPower));
        infoRow(dp,"DEF",     String.valueOf(player.defensePower));
        infoRow(dp,"XP",      player.experiencePoints+" / "+GameConfig.experiencePointsPerLevel);
        infoRow(dp,"Gold",    String.valueOf(player.goldPoints));
        infoRow(dp,"Kills",   String.valueOf(kills));
        JOptionPane.showMessageDialog(this, dp, "Character Info", JOptionPane.PLAIN_MESSAGE);
    }

    private void showStatusDialog() {
        double pct = (double) player.health / GameConfig.maxHealth * 100;
        String st = player.health==GameConfig.maxHealth?"Perfect!" : player.health>=70?"Good" : player.health>=40?"Caution!":"Critical!";
        JOptionPane.showMessageDialog(this,
                player.name+"\nHP: "+ player.health+"/"+GameConfig.maxHealth+" ("+(int)pct+"%)\nStatus: "+st,
                "Status", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showShopDialog(boolean isBattle) {
        ItemType[] list = ItemType.values();
        String[] opts = new String[list.length];
        for (int i=0;i<list.length;i++)
            opts[i] = list[i].getDisplayName().trim() + " | Val:" + list[i].getValue() + " | Price:" + list[i].getPrice() + "g";

        String pick = (String) JOptionPane.showInputDialog(this,
                "Your Gold: " + player.goldPoints + "\nSelect an item:",
                "Item Shop", JOptionPane.PLAIN_MESSAGE, null, opts, opts[0]);

        if (pick != null) {
            for (ItemType t : list) {
                if (pick.startsWith(t.getDisplayName().trim())) {
                    if (player.goldPoints >= t.getPrice()) {
                        player.goldPoints -= t.getPrice();
                        new Item(t.getDisplayName(), t).use(player);
                        String msg = "[SHOP] Bought: " + t.getDisplayName().trim();
                        if (isBattle) { blog(msg); refreshBattle(); }
                        else glog(msg);
                        refreshGame();
                    } else {
                        String msg = "[SHOP] Not enough gold! Need " + t.getPrice() + "g.";
                        if (isBattle) blog(msg); else glog(msg);
                    }
                    break;
                }
            }
        }
    }

    private void confirmExit() {
        int ok = JOptionPane.showConfirmDialog(this, "Exit the game?", "Exit", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) System.exit(0);
    }

    // ── Refresh ──
    private void refreshGame() {
        if (player == null) return;
        gName.setText("Name: "  + player.name);
        gLevel.setText("Level: "+ player.level + "/" + GameConfig.maxLevel);
        gGold.setText("Gold: "  + player.goldPoints + "g");
        gAtk.setText("ATK: "   + player.attackPower);
        gDef.setText("DEF: "   + player.defensePower);
        gKills.setText("Kills: "+ kills);

        int h = player.health;
        Color hpCol = h >= 70 ? HP_GREEN : h >= 40 ? HP_YELLOW : HP_RED;
        gHP.setForeground(hpCol);
        gHP.setMaximum(GameConfig.maxHealth);
        gHP.setValue(h);
        gHPTxt.setText("HP " + h + "/" + GameConfig.maxHealth);
        gHPTxt.setForeground(hpCol);

        int xp = Math.min(player.experiencePoints, GameConfig.experiencePointsPerLevel);
        gXP.setMaximum(GameConfig.experiencePointsPerLevel);
        gXP.setValue(xp);
        gXPTxt.setText("XP " + player.experiencePoints + "/" + GameConfig.experiencePointsPerLevel);
    }

    private void refreshBattle() {
        if (player == null || enemy == null) return;

        int h = player.health;
        Color hCol = h >= 70 ? HP_GREEN : h >= 40 ? HP_YELLOW : HP_RED;
        bHeroHP.setForeground(hCol);
        bHeroHP.setMaximum(GameConfig.maxHealth);
        bHeroHP.setValue(Math.max(0, h));
        bHeroHPTxt.setText("HP " + h + "/" + GameConfig.maxHealth);
        bHeroName.setText(player.name);

        int e = enemy.health;
        Color eCol = e >= 70 ? HP_GREEN : e >= 40 ? HP_YELLOW : HP_RED;
        bEnemyHP.setForeground(eCol);
        bEnemyHP.setMaximum(GameConfig.maxHealth);
        bEnemyHP.setValue(Math.max(0, e));
        bEnemyHPTxt.setText("HP " + e + "/" + GameConfig.maxHealth);
        bEnemyName.setText(enemy.name + " (Lv." + enemy.level + ")");
        bEnemyStats.setText("ATK: " + enemy.attackPower + "   DEF: " + enemy.defensePower);
        bTurnLbl.setText("Turn " + turnCount);
    }

    private void glog(String s) {
        gameLog.append(s + "\n");
        gameLog.setCaretPosition(gameLog.getDocument().getLength());
    }
    private void blog(String s) {
        battleLog.append(s + "\n");
        battleLog.setCaretPosition(battleLog.getDocument().getLength());
    }

    // ══════════════════════════════════
    //  UI HELPERS
    // ══════════════════════════════════
    private JPanel panel(LayoutManager lm, Color bg) {
        JPanel p = new JPanel(lm);
        p.setBackground(bg);
        return p;
    }

    private JButton btn(String text, Color col, int size) {
        JButton b = new JButton(text);
        b.setFont(new Font("Monospaced", Font.BOLD, size));
        b.setForeground(Color.WHITE);
        b.setBackground(col.darker().darker());
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(col, 1),
                BorderFactory.createEmptyBorder(11, 24, 11, 24)));
        b.addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e){ b.setBackground(col.darker()); }
            public void mouseExited(MouseEvent e) { b.setBackground(col.darker().darker()); }
        });
        return b;
    }

    private JLabel label(String t, Font f, Color c) {
        JLabel l = new JLabel(t);
        l.setFont(f);
        l.setForeground(c);
        return l;
    }

    private JProgressBar bar(Color col, int max) {
        JProgressBar b = new JProgressBar(0, max);
        b.setValue(max);
        b.setForeground(col);
        b.setBackground(new Color(25, 30, 50));
        b.setStringPainted(false);
        b.setBorderPainted(false);
        b.setPreferredSize(new Dimension(280, 16));
        b.setUI(new BasicProgressBarUI());
        return b;
    }

    private JTextArea log() {
        JTextArea t = new JTextArea();
        t.setEditable(false);
        t.setBackground(new Color(6, 8, 16));
        t.setForeground(TEXT);
        t.setFont(new Font("Monospaced", Font.PLAIN, 13));
        t.setLineWrap(true);
        t.setWrapStyleWord(true);
        t.setBorder(new EmptyBorder(8,10,8,10));
        return t;
    }

    private JScrollPane scroll(JTextArea t, String title, Color col) {
        JScrollPane sp = new JScrollPane(t);
        sp.setBorder(titledBorder(title, col));
        sp.getVerticalScrollBar().setBackground(SURFACE2);
        return sp;
    }

    private JTextField field() {
        JTextField f = new JTextField(18);
        f.setBackground(new Color(20, 25, 44));
        f.setForeground(TEXT);
        f.setCaretColor(TEXT);
        f.setFont(new Font("Monospaced", Font.PLAIN, 14));
        f.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(7,10,7,10)));
        return f;
    }

    private JComboBox<String> combo(String... items) {
        JComboBox<String> c = new JComboBox<>(items);
        c.setBackground(new Color(20,25,44));
        c.setForeground(TEXT);
        c.setFont(new Font("Monospaced", Font.PLAIN, 14));
        return c;
    }

    private Border titledBorder(String title, Color col) {
        return BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(col, 1), title,
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Monospaced", Font.BOLD, 12), col);
    }

    private JSeparator hLine(Color col, int w) {
        JSeparator s = new JSeparator();
        s.setForeground(col);
        s.setPreferredSize(new Dimension(w, 2));
        return s;
    }

    private void addRow(JPanel p, GridBagConstraints g, int row, String lbl, Component field) {
        g.gridx=0; g.gridy=row; g.gridwidth=1;
        JLabel l = label(lbl, new Font("Monospaced",Font.PLAIN,14), MUTED);
        p.add(l, g);
        g.gridx=1; p.add(field, g);
    }

    private void infoRow(JPanel p, String key, String val) {
        p.add(label(key + ":", new Font("Monospaced",Font.PLAIN,13),  MUTED));
        p.add(label(val,       new Font("Monospaced",Font.BOLD,13),   GOLD));
    }
}
// End of GameUI Class.