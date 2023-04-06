package cwk4;



public class Force
{
    private ForceState state;
    private String reference;
    private String name;
    private int fee;
    private int strikers;
    private int laser;
    private int photon;
    private int strength;
    private boolean cloak;
    private ForceType type;

    public Force(String r, String n, int f, int s, int l, int p, int str, boolean c, ForceType t)
    {
        state = ForceState.DOCKED;
        reference = r;
        name = n;
        fee = f;
        strikers = s;
        laser = l;
        photon = p;
        strength = str;
        cloak = c;
        type = t;


    }


}