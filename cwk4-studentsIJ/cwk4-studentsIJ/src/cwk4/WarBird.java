package cwk4;

/**
 * @author Andrei Cirlig, Janine Obiri, Lewis Turnbull, Zohaib Rehman
 */
public class WarBird extends Force {
    final private boolean canCloak;

    public WarBird(String fleetReference, String name, boolean canCloak, int battleStrength) {
        super(fleetReference, name, canCloak ? 400 : 300, battleStrength,
                false, canCloak, true);

        this.canCloak = canCloak;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nCan cloak: " + canCloak;
    }

    public boolean getCanCloak() {
        return canCloak;
    }
}