package cwk4;

/**
 * @author Andrei Cirlig, Janine Obiri, Lewis Turnbull, Zohaib Rehman
 */
public class Wing extends Force {
    private int strikers;

    public Wing(String fleetReference, String name, int strikers) {
        super(fleetReference, name, 200, 20 * strikers,
                true, true, false);

        this.strikers = strikers;
    }

    public String toString() {
        return super.toString() +
                "\nStrikers: " + strikers;
    }
}
