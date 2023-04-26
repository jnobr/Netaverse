package cwk4; 
import java.util.*;
import java.io.*;
/**
 package cwk4;
 import java.util.*;
 import java.io.*;
 /**
 * This class implements the behaviour expected from a WIN
 system as required for 5COM2007 - March 2023
 *
 * @author Team 4
 * @version 15 April 2023
 */

public class SpaceWars implements WIN,Serializable
{
    private Player player;
    private ArrayList<Battle> battles = new ArrayList<Battle>();
    private Force[] forces;
    private ArrayList<Force> destroyed = new ArrayList<Force>();

    private ArrayList<Force> UFF = new ArrayList<Force>();

//**************** WIN **************************
    /** Constructor requires the name of the admiral
     * @param admiral the name of the admiral
     */
    public SpaceWars(String admiral)
    {
        setupPlayer(admiral);
        setupForces();
        setupBattles();
    }

    /** Second constructor - task 3.5
     *  To be added for task 3.5
     */

    public SpaceWars(String admiral, String fname) {
        setupPlayer(admiral);
        setupForces();
        readBattles(fname);
    }





    /**Returns a String representation of the state of the game,
     * including the name of the admiral, state of the war chest,
     * whether defeated or not, and the forces currently in the
     * Active Star Fleet(ASF),(or, "No forces" if Star Fleet is empty)
     * @return a String representation of the state of the game,
     * including the name of the admiral, state of the war chest,
     * whether defeated or not, and the forces currently in the
     * Star Fleet,(or, "No forces" if Active Star Fleet is empty)
     **/
    public String toString()
    {
        return "Admiral: " + player.getName() + "\n" +
                "War Chest: " + player.getWarChest() + "\n" +
                "Defeated: " + isDefeated() + "\n" +
                "Active Star Fleet: " + "\n" + player.getASF();

    }


    /** returns true if war chest <=0 AND the active Star Fleet(ASF) has no
     * forces which can be recalled.
     * @returns true if war chest <=0 and the active Star Fleet(ASF) has no
     * forces which can be recalled.
     */
    public boolean isDefeated()
    {
        if (player.returnLost()) {return true;}
        if ((player.getWarChest() <= 0) && player.returnSizeASF() == 0) {return true;}

        return false;
    }


    /** returns the number of bit coins in the war chest
     * @returns the number of bit coins in the war chest
     */
    public int getWarchest()
    {
        return player.getWarChest();
    }

    /* Returns a list of all forces in the system by listing :
     * All forces in the Active Star Fleet(ASF), or "No forces in ASF")
     * All forces remaining in the UFF dock, or "No forces in UFF dock
     * All forces destroyed as a result of losing a battle, or "No destroyed forces"
     */
    public String getAllForces()
    {
        String s = "******Forces in dock ******";
        s = s + getForcesInDock();
        s = s + "\n*****Forces in ASF****" + getASFleet();


        return s;
    }


    /**Returns true if force is in the United Forces Fleet(UFF), else false
     * @param ref reference of the force
     * @return a String representation of all forces in the United Forces Fleet(UFF)
     **/
    public boolean isInUFFDock(String ref)
    {
        Force temp = findForceUFF(ref);

        if(temp == null) {return false;}
        else {
            return true;
        }
    }

    /**Returns a String representation of all forces in the United Forces Fleet(UFF) dock.
     * Does not include destroyed forces
     * @return a String representation of all forces in the United Forces Fleet(UFF) dock.
     **/
    public String getForcesInDock()
    {
        String s = "\n\n************ Forces available in UFFleet Dock********\n";

        for (int i = 0; i < UFF.size(); i++)
        {
            s = s + "\n" + UFF.get(i).toString();
        }


        return s;
    }

    /** Returns a list of all destroyed forces in the system
     * @return all destroyed forces
     */
    public String getDestroyedForces()
    {
        String s ="\n***** Destroyed Forces ****\n";

        for (int i = 0; i < destroyed.size(); i++) {
            s = s + "\n" + destroyed.get(i).toString();
        }


        return s;
    }

    /** Returns details of the force with the given reference code, or "No such force"
     * @param ref the reference of the force
     * @return details of the force with the given reference code
     **/
    public String getForceDetails(String ref)
    {
        Force force = findForce(ref);
        if (force != null) {
            return force.toString();
        }


        return "\nNo such force";
    }


    // ***************** Active Star Fleet Forces ************************
    /** Allows a force to be activated into the Active Star Fleet(ASF), but
     * only if there are enough resources for the activation fee.The force's
     * state is set to "active"
     * @param ref represents the reference code of the force
     * @return 0 if force is activated, 1 if force is not in the UFF dock or is destroyed
     * 2 if not enough money, -1 if no such force
     **/
    public int activateForce(String ref)
    {
        Force force = findForce(ref);
        if(force == null){return -1;}
        else if ((player.inASF(ref) == true) || (isInUFFDock(ref) == false) )  {
            return 1;
        }
        player.addToASF(force);
        if (player.inASF(ref) == false){ return 2;}
        else {
            removeFromUFF(force);
            return 0;
        }

    }


    /** Returns true if the force with the reference code is in
     * the Active Star Fleet(ASF), false otherwise.
     * @param ref is the reference code of the force
     * @return returns true if the force with the reference code
     * is in the active Star Fleet(ASF), false otherwise.
     **/
    public boolean isInASFleet(String ref)
    {
        return player.inASF(ref);

    }

    /**Returns a String representation of the forces in the active
     * Star Fleet(ASF), or the message "No forces activated"
     * @return a String representation of the forces in the active
     * Star Fleet, or the message "No forces activated"
     **/
    public String getASFleet()
    {
        String s = player.getASF();

        return s;
    }

    /** Recalls a force from the Star Fleet(ASF) back to the UFF dock, but only
     * if it is in the Active Star Fleet(ASF)
     * @param ref is the reference code of the force
     **/
    public void recallForce(String ref)
    {
        Force force = findForce(ref);
        if (force != null && force.getState() == ForceState.ACTIVE) {
        force.recall();
        int moneyBack = force.getActivationFee()/2;
        player.addToWarchest(moneyBack);
        addToUFF(force);}


    }


//**********************Battles*************************
    /** returns true if the number represents a battle
     * @param num is the number of the required battle
     * @returns true if the number represents a battle
     **/
    public boolean isBattle(int num)
    {
        Battle temp = findBattle(num);
        if (temp == null) {return false;}
        else {return true;}

    }


    /** Provides a String representation of a battle given by
     * the battle number
     * @param num the number of the battle
     * @return returns a String representation of a battle given by
     * the battle number
     **/
    public String getBattle(int num)
    {
        Battle bat = findBattle(num);
        if (bat == null) {return "No such battle";}
        else {return bat.toString();}


    }

    /** Provides a String representation of all battles
     * @return returns a String representation of all battles
     **/
    public String getAllBattles()
    {
        String s = "\n************ All Battles ************\n";

        for (int i = 0; i < battles.size(); i++)
        {
            s = s + "\n" + battles.get(i).toString();
        }

        return s;
    }


    /** Retrieves the battle represented by the battle number.Finds
     * a force from the Active Star Fleet which can engage in the battle.The
     * results of battle will be one of the following:
     * 0 - Battle won, battle gains added to the war chest,
     * 1 - Battle lost as no suitable force available, battle losses
     * deducted from war chest
     * 2 - Battle lost on battle strength , battle losses
     * deducted from war chest and force destroyed
     * 3 - If a battle is lost and admiral completely defeated (no resources and
     * no forces to recall)
     * -1 - no such battle
     * @param battleNo is the number of the battle
     * @return an int showing the result of the battle (see above)
     */
    public int doBattle(int battleNo)
    {


        Battle bat = findBattle(battleNo);
        if (bat == null){return -1;}
        Force playerForce = matchForceToBattle(bat);

        bat.addForce(playerForce);
        int result = bat.battle();
        if (result == 0)
        {
            int g = bat.getGains();
            player.addToWarchest(g);
        }
        if (result == 1) {
            int l = bat.getLosses();
            player.removeFromWarchest(l);
        }
        if (result == 2)
        {
            playerForce.destroy();
            destroyed.add(playerForce);

            if (matchForceToBattle(bat) == null)
            {
                player.gameover();
                return 3;
            }

            int l = bat.getLosses();
            player.removeFromWarchest(l);
            player.removeFromASF(playerForce);
        }


        return result;
    }




    //*******************************************************************************
    private void setupForces()
    {
        Force f1 = new Wing("IW1","Twisters",10);
        Force f2 = new Starship("SS2","Enterprise",10,20);
        Force f3 = new WarBird("WB3","Droop",false,100);
        Force f4 = new Wing("IW4","Wingers",20);
        Force f5 = new WarBird("WB5","Hang",true,300);
        Force f6 = new Starship("SS6","Voyager",15,10);
        Force f7 = new Starship("SS7", "Explorer",4,5);
        Force f8 = new WarBird("WB9","Hover",false,400);
        Force f9 = new Wing("IW10","Flyers",5);


        forces = new Force[]{f1, f2, f3, f4, f5, f6, f7, f8, f9};

        Collections.addAll(UFF, forces);


    }

    private void setupBattles()
    {
        Battle b1 = new Battle(1, BattleType.FIGHT,"Borg",200,300,100);
        Battle b2 = new Battle(2,BattleType.SKIRMISH,"Kardassians",700,200,120);
        Battle b3 = new Battle(3,BattleType.AMBUSH,"Ferengi",100,400,150);
        Battle b4 = new Battle(4,BattleType.FIGHT,"Ewoks",600,600,200);
        Battle b5 = new Battle(5,BattleType.AMBUSH,"Borg",500,400,90);
        Battle b6 = new Battle(6,BattleType.SKIRMISH,"Groaners",150,100,100);
        Battle b7 = new Battle(7,BattleType.FIGHT, "Borg",150,500,300);
        Battle b8 = new Battle(8,BattleType.AMBUSH,"Wailers",300,300,300);

        battles.add(b1);
        battles.add(b2);
        battles.add(b3);
        battles.add(b4);
        battles.add(b5);
        battles.add(b6);
        battles.add(b7);
        battles.add(b8);
    }



    //**************************Add your own private methos here ***********************
    private Force findForce(String ref) {

        for(Force temp : forces)
        {
            if (temp.getFleetReference().equals(ref))
            {
                return temp;
            }
        }
        return null;
    }
    private Battle findBattle(int num)
    {
        for(Battle temp : battles)
        {
            if (temp.getBattleNo() == num)
            {
                return temp;
            }
        }
        return null;
    }
    private void setupPlayer(String name)
    {
        player = new Player(name);
    }

    private Force matchForceToBattle(Battle bat)
    {

        if (bat.getType() == BattleType.AMBUSH) {return player.getAmbush();}
        if (bat.getType() == BattleType.SKIRMISH) {return player.getSkirmish();}
        if (bat.getType() == BattleType.FIGHT) {return player.getFight();}

        return null;
    }

    private void addToUFF(Force f)
    {
        UFF.add(f);
        player.removeFromASF(f);
    }

    private Force findForceUFF(String ref)
    {
        for (Force temp : UFF)
        {
            if (temp.getFleetReference().equals(ref))
            {return temp;}
        }
        return null;
    }
    private void removeFromUFF(Force f)
    {
        UFF.remove(f);
    }




    //*******************************************************************************

    //These methods are not needed until Task 3.5. Uncomment thmemto complete task 3.5
    // ***************   file write/read  *********************

    /** Writes whole game to the specified file
     * @param fname name of file storing requests
     */
    public void saveGame(String fname) {   // uses object serialisation

        try {
            FileOutputStream fileOutputStream
                    = new FileOutputStream(fname);
            ObjectOutputStream objectOutputStream
                    = new ObjectOutputStream(fileOutputStream);
            SpaceWars s = this;
            objectOutputStream.writeObject(s);



            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    /** reads all information about the game from the specified file
     * and returns a SpaceWars object
     * @param fname name of file storing the game
     * @return the game (as a SpaceWars object)
     */
    public SpaceWars restoreGame(String fname)
    {
        try {
            FileInputStream fileInputStream
                    = new FileInputStream(fname);
            ObjectInputStream objectInputStream
                    = new ObjectInputStream(fileInputStream);
            SpaceWars s = (SpaceWars) objectInputStream.readObject();

            objectInputStream.close();
            return s;

        }

        catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }



    /** Reads information about battles from the specified file into the appropriate collection
     ** @param fname the name of the file
     */
    private void readBattles(String fname)
    {
        try {
            BufferedReader inf = new BufferedReader(new FileReader(fname));

            String currentLine;
            while((currentLine = inf.readLine() )!= null) {
                String[] info = currentLine.split(",");
                int num = Integer.parseInt(info[0]);
                BattleType ty = BattleType.valueOf(info[1]);
                String na = info[2];
                int str = Integer.parseInt(info[3]);
                int gain = Integer.parseInt(info[4]);
                int losses = Integer.parseInt(info[5]);

                Battle p2 = new Battle(num,ty,na,str,gain,losses);
                battles.add(p2);
            }



        }

        catch (Exception e) {
            e.printStackTrace();

        }
    }


}