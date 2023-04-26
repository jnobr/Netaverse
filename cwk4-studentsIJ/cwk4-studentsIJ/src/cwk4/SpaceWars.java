package cwk4; 
import java.sql.Array;
import java.util.*;
import java.io.*;

/**
 * This class implements the behaviour expected from a WIN
 system as required for 5COM2007 - March 2023
 * 
 * @author Team 4
 * @version 15 April 2023
 */

public class SpaceWars implements WIN, Serializable
{
    private String admiralName;
    private int warChest = 1000;

    private HashMap<String, Force> forces;
    private Battle[] battles;

//**************** WIN **************************  
    /** Constructor requires the name of the admiral
     * @param admiral the name of the admiral
     */  
    public SpaceWars(String admiral)
    {
        admiralName = admiral;

        setupForces();
        setupBattles();
    }
    
    /** Second constructor - task 3.5
     *  To be added for task 3.5
     */
    public SpaceWars(String admiral, String fname) {
        admiralName = admiral;

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
        String s = "Admiral name: " + admiralName;
        s += "\nWar chest balance: " + getWarchest() + " bit coins";
        s += "\n" + getASFleet();

        return s;
    }


    /** returns true if war chest <=0 AND the active Star Fleet(ASF) has no 
     * forces which can be recalled. 
     * @returns true if war chest <=0 and the active Star Fleet(ASF) has no 
     * forces which can be recalled. 
     */
    public boolean isDefeated()
    {
        return ((warChest <= 0) && getForceObjectsByState(ForceState.ACTIVE).length == 0);
    }
    
    
    /** returns the number of bit coins in the war chest
     * @returns the number of bit coins in the war chest
     */
    public int getWarchest()
    {
        return warChest;
    }
    
    /* Returns a list of all forces in the system by listing :
     * All forces in the Active Star Fleet(ASF), or "No forces in ASF")
     * All forces remaining in the UFF dock, or "No forces in UFF dock
     * All forces destroyed as a result of losing a battle, or "No destroyed forces"
     */
    public String getAllForces()
    {
        //Appropriate "no such forces" messages are returned when criteria are met inside each method called here.
        String s = "";

        s += getASFleet();
        s += getForcesInDock();
        s += getDestroyedForces();

        return s;
    }
        
    
    /**Returns true if force is in the United Forces Fleet(UFF), else false
     * @param ref reference of the force
     * @return a String representation of all forces in the United Forces Fleet(UFF)
     **/
    public boolean isInUFFDock(String ref) 
    {
        return forces.get(ref) != null;
    }
    
    /**Returns a String representation of all forces in the United Forces Fleet(UFF) dock.
     * Does not include destroyed forces
     * @return a String representation of all forces in the United Forces Fleet(UFF) dock.
     **/
    public String getForcesInDock()
    {   
        String s = "\n\n************ Forces available in UFFleet Dock********\n";
        
        Force[] dockedForces = getDockedForceObjects();

        if (dockedForces.length == 0) {
            s += "\nNo forces in dock";
        } else {
            for (Force force : dockedForces) {
                s += "\n" + force.toString() + "\n";
            }
        }

        return s;
    }
    
     /** Returns a list of all destroyed forces in the system 
     * @return all destroyed forces   
     */
    public String getDestroyedForces()
    {
        String s = "\n***** Destroyed Forces ****\n";

        Force[] destroyedForces = getDestroyedForceObjects();

        if (destroyedForces.length == 0) {
            s += "\nNo destroyed forces";
        } else {
            for (Force force : destroyedForces) {
                s += "\n" + force.toString();
            }
        }
        
        return s;
    }
        
    /** Returns details of the force with the given reference code, or "No such force" 
     * @param ref the reference of the force
     * @return details of the force with the given reference code
     **/
    public String getForceDetails(String ref)
    {
        Force force = forces.get(ref);

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
        Force force = forces.get(ref);

        if (force == null) {
            return -1;
        }

        if (force.getState() != ForceState.DOCKED) {
            return 1;
        }

        if (force.getActivationFee() > warChest) {
            return 2;
        }

        force.activate();
        warChest -= force.getActivationFee();
        return 0;

    }
    
        
    /** Returns true if the force with the reference code is in 
     * the Active Star Fleet(ASF), false otherwise.
     * @param ref is the reference code of the force
     * @return returns true if the force with the reference code
     * is in the active Star Fleet(ASF), false otherwise.
     **/
    public boolean isInASFleet(String ref)
    {
        Force force = forces.get(ref);

        if (force == null) {
            return false;
        }

        return force.getState() == ForceState.ACTIVE;
    }
    
    /**Returns a String representation of the forces in the active 
     * Star Fleet(ASF), or the message "No forces activated"
     * @return a String representation of the forces in the active
     * Star Fleet, or the message "No forces activated"
     **/
    public String getASFleet()
    {
        String s = "\n****** Forces in the Active Star Fleet******\n";

        Force[] asf = getASFObjects();

        if (asf.length == 0) {
            s += "No forces activated";
        } else {
            for (Force force : asf) {
                s += "\n" + force.toString();
            }
        }

        return s;
    }
    
    /** Recalls a force from the Star Fleet(ASF) back to the UFF dock, but only  
     * if it is in the Active Star Fleet(ASF)
     * @param ref is the reference code of the force
     **/
    public void recallForce(String ref)
    {
        Force force = forces.get(ref);

        if (force == null || force.getState() != ForceState.ACTIVE) {
            return;
        }

        force.recall();

        int moneyBack = force.getActivationFee() / 2;
        warChest += moneyBack;
    }   
            
    
//**********************Battles************************* 
    /** returns true if the number represents a battle
     * @param num is the number of the required battle
     * @returns true if the number represents a battle
     **/
     public boolean isBattle(int num)
     {
         return findBattle(num) != null;
     }
    
    
    /** Provides a String representation of a battle given by 
     * the battle number
     * @param num the number of the battle
     * @return returns a String representation of a battle given by 
     * the battle number
     **/
    public String getBattle(int num)
    {
        Battle battle = findBattle(num);

        if (battle == null) {
            return "No such battle";
        }

        return battle.toString();

    }
    
    /** Provides a String representation of all battles 
     * @return returns a String representation of all battles
     **/
    public String getAllBattles()
    {
        String s = "\n************ All Battles ************\n";

        for (Battle battle : battles) {
            s += "\n" + battle.toString() + "\n";
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

        if (bat == null) {
            return -1;
        }

        Force playerForce = matchForceToBattle(bat);

        if (playerForce == null || playerForce.getBattleStrength() < bat.getEnemyStrength()) {
            warChest -= bat.getLosses();

            if (playerForce == null) {
                return 1;
            }

            playerForce.destroy();

            if (isDefeated()) {
                return 3;
            }

            return 2;
        }

        warChest += bat.getGains();
        return 0;
    }
    


    
    //*******************************************************************************
    private void setupForces()
    {
        forces = new HashMap<String, Force>();

        forces.put("IW1", new Wing("IW1","Twister",10));
        forces.put("SS2", new Starship("SS2","Enterprise",10,20));
        forces.put("WB3", new WarBird("WB3","Droop",false,100));
        forces.put("IW4", new Wing("IW4","Winger",20));
        forces.put("WB5", new WarBird("WB5","Hang",true,300));
        forces.put("SS6", new Starship("SS6", "Voyager", 15, 10));
        forces.put("SS7", new Starship("SS7", "Explorer", 4, 5));
        forces.put("WB9", new WarBird("WB9", "Hover", false, 400));
        forces.put("IW10", new Wing("IW10", "Flyer", 5));
    }
    
    private void setupBattles()
    {
        battles = new Battle[] {
                new Battle(1, BattleType.FIGHT, "Borg", 200, 300, 100),
                new Battle(2, BattleType.SKIRMISH, "Kardassians", 700, 200, 120),
                new Battle(3, BattleType.AMBUSH, "Ferengi", 100, 400, 150),
                new Battle(4, BattleType.FIGHT, "Ewoks", 600, 600, 200),
                new Battle(5, BattleType.AMBUSH, "Borg", 500, 400, 90),
                new Battle(6, BattleType.SKIRMISH, "Groaners", 150, 100, 100),
                new Battle(7, BattleType.FIGHT, "Borg", 150, 500, 300),
                new Battle(8, BattleType.AMBUSH, "Wailers", 300, 300, 300)
        };
    }


    
    //**************************Add your own private methos here ***********************
    private Battle findBattle(int num)
    {
        int index = num - 1;

        if (index < 0 || index >= battles.length) {
            return null;
        }

        return battles[index];
    }

    private Force[] getForceObjectsByState(ForceState state) {
        ArrayList<Force> forceObjects = new ArrayList<Force>();

        for (Force force : forces.values()) {
            if (force.getState() == state) {
                forceObjects.add(force);
            }
        }

        return forceObjects.toArray(new Force[forceObjects.size()]);
    }

    private Force[] getASFObjects() {
        return getForceObjectsByState(ForceState.ACTIVE);
    }

    private Force[] getDestroyedForceObjects() {
        return getForceObjectsByState(ForceState.DESTROYED);
    }

    private Force[] getDockedForceObjects() {
        return getForceObjectsByState(ForceState.DOCKED);
    }

    private Force matchForceToBattle(Battle bat)
    {
        if (bat.getType() == BattleType.AMBUSH) { return findActiveAmbusher(); }
        if (bat.getType() == BattleType.SKIRMISH) { return findActiveSkirmisher(); }
        if (bat.getType() == BattleType.FIGHT) { return findActiveFighter();}

        return null;
    }

    /**
     * Finds first force in the ASF that can ambush.
     * @return first force in the ASF that can ambush - otherwise null if no such force exists.
     */
    private Force findActiveAmbusher() {
        Force[] activeForces = getASFObjects();

        for (Force force : activeForces) {
            if (force.getCanAmbush()) {
                return force;
            }
        }

        return null;
    }

    /**
     * Finds first force in the ASF that can skirmish.
     * @return first force in the ASF that can skirmish - otherwise null if no such force exists.
     */
    private Force findActiveSkirmisher() {
        Force[] activeForces = getASFObjects();

        for (Force force : activeForces) {
            if (force.getCanSkirmish()) {
                return force;
            }
        }

        return null;
    }

    /**
     * Finds first force in the ASF that can fight.
     * @return first force in the ASF that can fight - otherwise null if no such force exists.
     */
    private Force findActiveFighter() {
        Force[] activeForces = getASFObjects();

        for (Force force : activeForces) {
            if (force.getCanFight()) {
                return force;
            }
        }

        return null;
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
      * @param fname the name of the file
      */
     private void readBattles(String fname)
     {
         ArrayList<Battle> battleList = new ArrayList<>();
         BufferedReader reader;

         try {
             reader = new BufferedReader(new FileReader(fname));

             String line = reader.readLine();
             int battleCounter = 1;

             while (line != null) {
                String[] data = line.split(",");

                BattleType battleType = switch (data[0]) {
                    case "Skirmish":
                        yield BattleType.SKIRMISH;
                    case "Ambush":
                        yield BattleType.AMBUSH;
                    case "Fight":
                        yield BattleType.FIGHT;
                    default:
                        throw new Exception("Invalid battle type in file.");
                };
                String enemyName = data[1];
                int enemyStrength = Integer.parseInt(data[2]);
                int losses = Integer.parseInt(data[3]);
                int gains = Integer.parseInt(data[4]);

                Battle battleFromLine = new Battle(battleCounter, battleType, enemyName, enemyStrength, losses, gains);
                battleList.add(battleFromLine);

                battleCounter++;
                line = reader.readLine();
             }

             battles = battleList.toArray(new Battle[battleList.size()]);
         }

         catch (Exception e) {
             e.printStackTrace();
         }
     }


}
