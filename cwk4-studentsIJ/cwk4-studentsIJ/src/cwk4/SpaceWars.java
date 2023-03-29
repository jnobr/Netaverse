package cwk4; 
import java.util.*;
import java.io.*;
/**
 * This class implements the behaviour expected from a WIN
 system as required for 5COM2007 - March 2023
 * 
 * @author Team ??
 * @version March 2023
 */

public class SpaceWars implements WIN 
{

//**************** WIN **************************  
    /** Constructor requires the name of the admiral
     * @param admiral the name of the admiral
     */  
    public SpaceWars(String admiral)
    {
       
        
       setupForces();
       setupBattles();
    }
    
    /** Second constructor - task 3.5
     *  To be added for task 3.5
     */
     
    
    
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
        
        return "";
    }
        
      
    /** returns true if war chest <=0 AND the active Star Fleet(ASF) has no 
     * forces which can be recalled. 
     * @returns true if war chest <=0 and the active Star Fleet(ASF) has no 
     * forces which can be recalled. 
     */
    public boolean isDefeated()
    {
        return false;
    }
    
    
    /** returns the number of bit coins in the war chest
     * @returns the number of bit coins in the war chest
     */
    public int getWarchest()
    {
        return 0;
    }
    
    /* Returns a list of all forces in the system by listing :
     * All forces in the Active Star Fleet(ASF), or "No forces in ASF")
     * All forces remaining in the UFF dock, or "No forces in UFF dock
     * All forces destroyed as a result of losing a battle, or "No destroyed forces"
     */
    public String getAllForces()
    {
        
        return "";
    }
        
    
    /**Returns true if force is in the United Forces Fleet(UFF), else false
     * @param ref reference of the force
     * @return a String representation of all forces in the United Forces Fleet(UFF)
     **/
    public boolean isInUFFDock(String ref) 
    {
        return false;
    }
    
    /**Returns a String representation of all forces in the United Forces Fleet(UFF) dock.
     * Does not include destroyed forces
     * @return a String representation of all forces in the United Forces Fleet(UFF) dock.
     **/
    public String getForcesInDock()
    {   
        String s = "\n\n************ Forces available in UFFleet Dock********\n";
        
        
        return s;
    }
    
     /** Returns a list of all destroyed forces in the system 
     * @return all destroyed forces   
     */
    public String getDestroyedForces()
    {
        String s ="\n***** Destroyed Forces ****\n";
        
        
        return s;
    }
        
    /** Returns details of the force with the given reference code, or "No such force" 
     * @param ref the reference of the force
     * @return details of the force with the given reference code
     **/
    public String getForceDetails(String ref)
    {
        
        
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
        
        
        return -1;
    }
    
        
    /** Returns true if the force with the reference code is in 
     * the Active Star Fleet(ASF), false otherwise.
     * @param ref is the reference code of the force
     * @return returns true if the force with the reference code
     * is in the active Star Fleet(ASF), false otherwise.
     **/
    public boolean isInASFleet(String ref)
    {
        return false;
    }
    
    /**Returns a String representation of the forces in the active 
     * Star Fleet(ASF), or the message "No forces activated"
     * @return a String representation of the forces in the active
     * Star Fleet, or the message "No forces activated"
     **/
    public String getASFleet()
    {
        String s = "\n****** Forces in the Active Star Fleet******\n";
        
        return s;
    }
    
    /** Recalls a force from the Star Fleet(ASF) back to the UFF dock, but only  
     * if it is in the Active Star Fleet(ASF)
     * @param ref is the reference code of the force
     **/
    public void recallForce(String ref)
    {
        
    }   
            
    
//**********************Battles************************* 
    /** returns true if the number represents a battle
     * @param num is the number of the required battle
     * @returns true if the number represents a battle
     **/
     public boolean isBattle(int num)
     {
         return false;
     }
    
    
    /** Provides a String representation of a battle given by 
     * the battle number
     * @param num the number of the battle
     * @return returns a String representation of a battle given by 
     * the battle number
     **/
    public String getBattle(int num)
    {
        
        return "No such battle";
    }
    
    /** Provides a String representation of all battles 
     * @return returns a String representation of all battles
     **/
    public String getAllBattles()
    {
        String s = "\n************ All Battles ************\n";
        
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
        
        return 999;
    }
    


    
    //*******************************************************************************
    private void setupForces()
    {
        
    }
    
    private void setupBattles()
    {
        
    }
    
    //**************************Add your own private methos here ***********************

    
    
    //*******************************************************************************
  
    //These methods are not needed until Task 3.5. Uncomment thmemto complete task 3.5
    // ***************   file write/read  *********************
 
//     /** Writes whole game to the specified file
//      * @param fname name of file storing requests
//      */
//     public void saveGame(String fname)
//     {   // uses object serialisation 
//         
//     }
//     
//     /** reads all information about the game from the specified file 
//      * and returns a SpaceWars object
//      * @param fname name of file storing the game
//      * @return the game (as a SpaceWars object)
//      */
//     public SpaceWars restoreGame(String fname)
//     {    
//         
//     }
// 
//     /** Reads information about battles from the specified file into the appropriate collection
//      * @param the name of the file
//      */
//     private void readBattles(String fname)
//     { 
//         
//     }


}
