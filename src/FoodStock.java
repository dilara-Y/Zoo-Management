public class FoodStock {
    private static double meat = 0;
    private static double fish = 0;
    private static double plant = 0;

    public static void initialize(double meatAmount, double fishAmount, double plantAmount) {
        meat = meatAmount;
        fish = fishAmount;
        plant = plantAmount;

        System.out.println("There are " + meat + " kg of Meat in stock");
        System.out.println("There are " + fish + " kg of Fish in stock");
        System.out.println("There are " + plant + " kg of Plant in stock");
    }

    public static double getMeat() {
        return meat;
    }

    public static double getFish() {
        return fish;
    }

    public static double getPlant() {
        return plant;
    }

    public static void removeMeat(double amount) {
        meat -= amount;
    }

    public static void removeFish(double amount) {
        fish -= amount;
    }

    public static void removePlant(double amount) {
        plant -= amount;
    }

    public static void listFoodStock() {
        System.out.println("Listing available Food Stock:");
        System.out.println("Plant: " + plant + " kgs");
        System.out.println("Fish: " + fish + " kgs");
        System.out.println("Meat: " + meat + " kgs");
    }
}