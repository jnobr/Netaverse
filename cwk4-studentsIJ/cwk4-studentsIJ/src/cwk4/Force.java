package cwk4;


/**
 * Represents a force that can be inherited from, but never instantiated itself.
 * @author Andrei Cirlig, Janine Obiri, Lewis Turnbull, Zohaib Rehman
 */
abstract class Force {
    private String fleetReference;
    private String name;
    private ForceState state;
    private int activationFee;
    private int battleStrength;

    private boolean canSkirmish;
    private boolean canAmbush;
    private boolean canFight;

    public Force(String fleetReference, String name, int activationFee, int battleStrength,
                 boolean canSkirmish, boolean canAmbush, boolean canFight) {
        this.state = ForceState.DOCKED;

        this.fleetReference = fleetReference;
        this.name = name;
        this.activationFee = activationFee;
        this.battleStrength = battleStrength;

        this.canSkirmish = canSkirmish;
        this.canAmbush = canAmbush;
        this.canFight = canFight;
    }

    public String toString() {
        return "Fleet reference: " + fleetReference +
                "\nName: " + name +
                "\nForce state: " + state +
                "\nActivation fee: " + activationFee + " bit coins" +
                "\nBattle strength: " + battleStrength +
                "\nCan skirmish: " + canSkirmish +
                "\nCan ambush: " + canAmbush +
                "\nCan fight: " + canFight;
    }
}