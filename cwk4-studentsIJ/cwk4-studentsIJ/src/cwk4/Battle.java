package cwk4;

/**
 * @author Andrei Cirlig, Janine Obiri, Lewis Turnbull, Zohaib Rehman
 */
public class Battle {
    private int battleNo;
    private BattleType type;
    private String enemyName;
    private int enemyStrength;
    private int losses;
    private int gains;

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
}