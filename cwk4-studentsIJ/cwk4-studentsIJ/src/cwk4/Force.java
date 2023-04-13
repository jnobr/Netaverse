package cwk4;


/**
 * Represents a force that can be inherited from, but never instantiated itself.
 * @author Andrei Cirlig, Janine Obiri, Lewis Turnbull, Zohaib Rehman
 */
abstract class Force {
    final private String fleetReference;
    final private String name;
    private ForceState state;
    final private int activationFee;
    final private int battleStrength;

    final private boolean canSkirmish;
    final private boolean canAmbush;
    final private boolean canFight;

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

    public String getFleetReference() {
        return fleetReference;
    }

    public String getName() {
        return name;
    }

    public ForceState getState() {
        return state;
    }

    public int getActivationFee() {
        return activationFee;
    }

    public int getBattleStrength() {
        return battleStrength;
    }

    public boolean getCanSkirmish() {
        return canSkirmish;
    }

    public boolean getCanAmbush() {
        return canAmbush;
    }

    public boolean getCanFight() {
        return canFight;
    }
    public void activate()
    {
        state = ForceState.ACTIVE;

    }

    public void recall()
    {
        state = ForceState.DOCKED;
    }
}