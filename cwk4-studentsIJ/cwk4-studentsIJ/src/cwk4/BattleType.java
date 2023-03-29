package cwk4; 
import java.io.*;
/**
 * Enumeration class BattleType - write a description of the enum class here
 * 
 * @author A.Marczyk
 * @version 02/11/2019
 */
public enum BattleType implements Serializable
{
    SKIRMISH (" Skirmish"), AMBUSH(" Ambush"), FIGHT(" Fight") ;
    private String type;
    
    private BattleType(String ty)
    {
        type = ty;
    }
    
    public String toString()
    {
        return type;
    }
}
