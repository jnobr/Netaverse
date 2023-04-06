package cwk4;

import java.util.ArrayList;

public class Player {
    private String name;
    private int warChest;
    private int score;
    private PlayerRole role;

    private ArrayList<Force> ASF = new ArrayList<Force>();
    public Player(String n, int w, int s)
    {
        name = n;
        warChest = w;
        score = s;
        role = PlayerRole.ADMIRAL;
    }
}
