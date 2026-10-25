interface EnergyMonitor {
    String getName();
    double getEnergy();

    default void showStatus() {
        System.out.println("Monitoring energy consumption.");
    }
}

interface Controllable {
    void switchOn();
    void switchOff();

    default void showStatus() {
        System.out.println("Resource operational status.");
    }
}

interface CostCalculable {
    double calculateCost(double tariff);
}

interface RenewableEnergy {
    double getEnergyGenerated();

    default double calculateSavings(double tariff) {
        return getEnergyGenerated() * tariff;
    }
}

// Smart Classroom
class SmartClassroom implements EnergyMonitor, Controllable, CostCalculable {
    public String getName() {
        return "Smart Classroom";
    }

    public double getEnergy() {
        return 12.5;
    }

    public double calculateCost(double tariff) {
        return getEnergy() * tariff;
    }

    public void switchOn() {
        System.out.println("Classroom lights and AC switched ON.");
    }

    public void switchOff() {
        System.out.println("Classroom lights and AC switched OFF.");
    }

    // Resolving the default-method conflict
    @Override
    public void showStatus() {
        System.out.println("Classroom is operational and monitoring energy.");
    }
}

// Computer Laboratory
class ComputerLaboratory implements EnergyMonitor, Controllable, CostCalculable {
    public String getName() {
        return "Computer Laboratory";
    }

    public double getEnergy() {
        return 35.0;
    }

    public double calculateCost(double tariff) {
        return getEnergy() * tariff;
    }

    public void switchOn() {
        System.out.println("Computer systems switched ON.");
    }

    public void switchOff() {
        System.out.println("Computer systems switched OFF.");
    }

    @Override
    public void showStatus() {
        System.out.println("Computer laboratory is operational.");
    }
}

// EV Charging Station
class EVChargingStation implements EnergyMonitor, Controllable, CostCalculable {
    public String getName() {
        return "EV Charging Station";
    }

    public double getEnergy() {
        return 20.0;
    }

    public double calculateCost(double tariff) {
        return getEnergy() * tariff;
    }

    public void switchOn() {
        System.out.println("EV charging started.");
    }

    public void switchOff() {
        System.out.println("EV charging stopped.");
    }

    @Override
    public void showStatus() {
        System.out.println("EV charging station is operational.");
    }
}

// Solar Power Unit
class SolarPowerUnit implements EnergyMonitor, RenewableEnergy {
    public String getName() {
        return "Solar Power Unit";
    }

    public double getEnergy() {
        return getEnergyGenerated();
    }

    public double getEnergyGenerated() {
        return 50.0;
    }

    @Override
    public void showStatus() {
        System.out.println("Solar unit is generating renewable energy.");
    }
}

// Main class
public class SmartCampusSystem {

    // Runtime polymorphism using interface references
    static void processResource(EnergyMonitor resource) {
        System.out.println("\nResource: " + resource.getName());
        System.out.println("Energy: " + resource.getEnergy() + " kWh");

        resource.showStatus();

        if (resource instanceof Controllable) {
            Controllable control = (Controllable) resource;
            control.switchOn();
        }

        if (resource instanceof CostCalculable) {
            CostCalculable cost = (CostCalculable) resource;

            double tariff = resource instanceof EVChargingStation ? 10.0 : 8.0;

            System.out.println("Operating Cost: Rs. "
                    + cost.calculateCost(tariff));
        }

        if (resource instanceof RenewableEnergy) {
            RenewableEnergy renewable = (RenewableEnergy) resource;

            System.out.println("Energy Generated: "
                    + renewable.getEnergyGenerated() + " kWh");

            System.out.println("Estimated Savings: Rs. "
                    + renewable.calculateSavings(8.0));
        }
    }

    public static void main(String[] args) {

        EnergyMonitor[] resources = {
            new SmartClassroom(),
            new ComputerLaboratory(),
            new EVChargingStation(),
            new SolarPowerUnit()
        };

        System.out.println("SMART CAMPUS RESOURCE AND ENERGY MANAGEMENT SYSTEM");

        for (EnergyMonitor resource : resources) {
            processResource(resource);
        }
    }
}