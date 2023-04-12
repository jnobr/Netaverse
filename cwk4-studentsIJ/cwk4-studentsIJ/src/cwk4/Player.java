package cwk4;

import java.util.*;

public class Player {
    private String name;
    private int warChest;
    private int score;
    private PlayerRole role;

    private ArrayList<Force> ASF = new ArrayList<Force>();
    public Player(String n)
    {
        name = n;
        warChest = 100;
        score = 0;
        role = PlayerRole.ADMIRAL;
    }

    public String getName()
    {
        return name;
    }

    public int getWarChest()
    {
        return warChest;
    }

    public int score()
    {
        return score;
    }

    public PlayerRole getRole()
    {
        return role;
    }

    public void addToScore(int toAdd)
    {

    }

    public String getASF()
    {
        return null ;
    }
}
