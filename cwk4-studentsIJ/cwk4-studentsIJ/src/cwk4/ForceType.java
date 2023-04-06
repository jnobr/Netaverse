package cwk4;
import java.io.*;
/**
 * Enumeration class ForceState
 *
 * @author A.Marczyk
 * @version 02/11/2019
 */
public enum ForceType implements Serializable
{
    WING("Wing"), WARBIRD("Warbird"), STARSHIP("Starship");
    private String type;

    private ForceType(String st)
    {
        type = st;
    }

    public String toString()
    {
        return type;
    }
}