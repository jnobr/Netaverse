package cwk4;

/**
 * @author Andrei Cirlig, Janine Obiri, Lewis Turnbull, Zohaib Rehman
 */
public class Starship extends Force {
    final private int laserCanons;
    final private int photonTorpedoes;

    public Starship(String fleetReference, String name, int laserCanons, int photonTorpedoes) {
        super(fleetReference, name, 30 * laserCanons, 5 * photonTorpedoes + 10 * laserCanons,
                true, false, true);

        this.laserCanons = laserCanons;
        this.photonTorpedoes = photonTorpedoes;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nLaser canon: " + laserCanons +
                "\nPhoton torpedoes: " + photonTorpedoes;
    }

    public int getLaserCanons() {
        return laserCanons;
    }

    public int getPhotonTorpedoes() {
        return photonTorpedoes;
    }
}