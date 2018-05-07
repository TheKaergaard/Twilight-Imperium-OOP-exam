package PackageImperium.Space;
/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

public class Planet {

    public String planetName;
    private int resourceProduction;

    public Planet(String planetName) {
        this.planetName = planetName;
    }

    public Planet(String planetName, int resourceProduction) {
        if (resourceProduction < 0 || resourceProduction > 6) {
            throw new IllegalArgumentException();
        } else {
            this.resourceProduction = resourceProduction;
            this.planetName = planetName;
        }
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }

    public void setResourceProduction(int newResourceProduction) {
        resourceProduction = newResourceProduction;

    }

    public String getPlanetName() {
        return planetName;
    }

    public int getResourceProduction() {
        return resourceProduction;
    }
}