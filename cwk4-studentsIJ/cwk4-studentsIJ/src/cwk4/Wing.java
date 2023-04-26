package cwk4;

import java.io.Serializable;

/**
 * @author Andrei Cirlig, Janine Obiri, Lewis Turnbull, Zohaib Rehman
 */
public class Wing extends Force implements Serializable {
    final private int strikers;

    public Wing(String fleetReference, String name, int strikers) {
        super(fleetReference, name, 200, 20 * strikers,
                true, true, false);

        this.strikers = strikers;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nStrikers: " + strikers;
    }

    public int getStrikers() {
        return strikers;
    }
}
