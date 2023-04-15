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

    public void addToWarchest(int m) {
        warChest += m;
    }
    public void removeFromWarchest(int m)
    {
        warChest -= m;
    }

    public String getASF()
    {
        return null ;
    }

    public Force findForce(String ref)

    {
        for(int i = 0; i < ASF.size(); i++)
        {
            Force temp = ASF.get(i);
            if ((temp.getFleetReference()).equals(ref))
            {
                return temp;
            }
        }
        return null;
    }

    public void removeFromASF(Force f)
    {
        ASF.remove(f);
    }

    public void addToASF(Force f){
        int amount = f.getActivationFee();
        if (warChest - amount >= 0) {
            ASF.add(f);
            warChest -= amount;
            f.activate();
        }

    }

    public boolean inASF(String ref)
    {
        Force temp = findForce(ref);
        if(temp == null){
            return false;
        }
        return true;
    }

    public Force getSkirmish()
    {
        for (int i = 0; i < ASF.size(); i++) {

            Force temp = ASF.get(i);
            if (temp.getCanSkirmish() == true) {return temp;}
        }
        return null;
    }

    public Force getAmbush()
    {
        for (int i = 0; i < ASF.size(); i++) {

            Force temp = ASF.get(i);
            if (temp.getCanAmbush() == true) {return temp;}
        }
        return null;
    }

    public Force getFight()
    {
        for (int i = 0; i < ASF.size(); i++) {

            Force temp = ASF.get(i);
            if (temp.getCanFight() == true) {return temp;}
        }
        return null;
    }

    public int returnSizeASF()
    {
        return ASF.size();
    }
}
