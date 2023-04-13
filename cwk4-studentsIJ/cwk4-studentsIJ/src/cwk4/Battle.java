package cwk4;

import java.util.ArrayList;

/**
 * @author Andrei Cirlig, Janine Obiri, Lewis Turnbull, Zohaib Rehman
 */
public class Battle {
    final private int battleNo;
    final private BattleType type;
    final private String enemyName;
    final private int enemyStrength;
    final private int losses;
    final private int gains;

    private ArrayList<Force> forces = new ArrayList<Force>();
    public Battle(int battleNo, BattleType type, String enemyName, int enemyStrength, int losses, int gains) {
        this.battleNo = battleNo;
        this.type = type;
        this.enemyName = enemyName;
        this.enemyStrength = enemyStrength;
        this.losses = losses;
        this.gains = gains;
    }

    @Override
    public String toString() {
        return "Battle number: " + battleNo +
                "\nBattle type: " + type.toString() +
                "\nEnemy name: " + enemyName +
                "\nEnemy strength: " + enemyStrength +
                "\nPlayer losses: " + losses + " bit coins" +
                "\nPlayer gains: " + gains + " bit coins";
    }

    public int getBattleNo() {
        return battleNo;
    }

    public BattleType getType() {
        return type;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public int getEnemyStrength() {
        return enemyStrength;
    }

    public int getLosses() {
        return losses;
    }

    public int getGains() {
        return gains;
    }

    public void addForce(Force f)
    {
        forces.add(f);
    }

    public void battle()
    {

    }
}