package PackageImperium.Space;
/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

public class Planet {

    private String planetName;
    private int resourceProduction;

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
        if (newResourceProduction < 0 || newResourceProduction > 6) {
            throw new IllegalArgumentException();
        } else {
            resourceProduction = newResourceProduction;

        }
    }

    public String getPlanetName() {
        return planetName;
    }

    public int getResourceProduction() {
        return resourceProduction;
    }
}