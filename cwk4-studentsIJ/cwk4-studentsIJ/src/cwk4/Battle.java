package cwk4;
public class Battle

{
    private int number;
    private BattleType type;
    private String enemy;
    private int enemyStrength;
    private int loss;
    private int gain;

    public Battle(int n,BattleType t, String e, int es, int l, int g)
    {
        number = n;
        type = t;
        enemy = e;
        enemyStrength = es;
        loss = l;
        gain = g;

    }


}