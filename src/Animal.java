
public abstract class Animal {
    private final String name;
    private final int age;
    private double mealSize;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
        calculateMealSize(); // Calculate meal size based on animal type and age
    }

    protected abstract void calculateMealSize();
    public abstract void feed(int numberOfMeals) throws Exception;
    public abstract String cleanHabitat();

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getMealSize() {
        return mealSize;
    }

    protected void setMealSize(double mealSize) {
        this.mealSize = mealSize;
    }
}

class Lion extends Animal {

    public Lion(String name, int age) {
        super(name, age);
    }

    @Override
    protected void calculateMealSize() {

        double baseMealSize = 5.0;
        double ageDifference = getAge() - 5;
        double mealAdjustment = ageDifference * 0.05;
        setMealSize(baseMealSize + mealAdjustment);
    }

    @Override
    public void feed(int numberOfMeals) throws NotEnoughFoodStock {
        try {
            double foodNeeded = getMealSize() * numberOfMeals;
            if (FoodStock.getPlant() < foodNeeded) {
                throw new NotEnoughFoodStock("Not enough ");
            }
            FoodStock.removePlant(foodNeeded);
            System.out.println(getName() + " has been given " + foodNeeded + " kgs assorted fruits and hay");
        } catch (NotEnoughFoodStock e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public String cleanHabitat() {
        return "Removing bones and refreshing sand.";
    }
}


class Elephant extends Animal {
    public Elephant(String name, int age) {
        super(name, age);
    }

    @Override
    protected void calculateMealSize() {
        // Elephants at age 20 eat 10kgs of plant per meal
        // Increasing or decreasing by 15 grams per age difference
        double baseMealSize = 10.0; // base meal size at age 20
        double ageDifference = getAge() - 20;
        double mealAdjustment = ageDifference * 0.015; // 15 grams per age difference
        setMealSize(baseMealSize + mealAdjustment);
    }

    @Override
    public void feed(int numberOfMeals) throws NotEnoughFoodStock {
        try {
            double foodNeeded = getMealSize() * numberOfMeals;
            if (FoodStock.getPlant() < foodNeeded) {
                throw new NotEnoughFoodStock("Not enough Plant");
            }
            FoodStock.removePlant(foodNeeded);
            System.out.println(getName() + " has been given " + foodNeeded + " kgs assorted fruits and hay");
        } catch (NotEnoughFoodStock e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public String cleanHabitat() {
        return "Washing the water area.";
    }
}

class Penguin extends Animal {
    public Penguin(String name, int age) {
        super(name, age);
    }

    @Override
    protected void calculateMealSize() {
        // Penguins at age 4 eat 3 kgs of fish
        // Increasing or decreasing by 40 grams per age difference
        double baseMealSize = 3.0; // base meal size at age 4
        double ageDifference = getAge() - 4;
        double mealAdjustment = ageDifference * 0.04; // 40 grams per age difference
        setMealSize(baseMealSize + mealAdjustment);
    }

    @Override
    public void feed(int numberOfMeals) throws NotEnoughFoodStock {
        try {
            double foodNeeded = getMealSize() * numberOfMeals;
            if (FoodStock.getFish() < foodNeeded) {
                throw new NotEnoughFoodStock("Not enough Fish");
            }
            FoodStock.removeFish(foodNeeded);
            System.out.println(getName() + " has been given " + foodNeeded + " kgs of various kinds of fish");
        } catch (NotEnoughFoodStock e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public String cleanHabitat() {
        return "Replenishing ice and scrubbing walls.";
    }
}

class Chimpanzee extends Animal {
    public Chimpanzee(String name, int age) {
        super(name, age);
    }

    @Override
    protected void calculateMealSize() {
        // Chimpanzees at age 10 eat 3 kgs of Meat and 3 kgs of plants
        // The total amount increases or decreases by 0.025 per age difference
        double baseMealSize = 3.0; // base meal size at age 10 (for each type)
        double ageDifference = getAge() - 10;
        double mealAdjustment = ageDifference * 0.025;
        setMealSize(baseMealSize + mealAdjustment);
    }

    @Override
    public void feed(int numberOfMeals) throws NotEnoughFoodStock {
        try{
            double foodNeeded = getMealSize() * numberOfMeals;
            if (FoodStock.getMeat() < foodNeeded || FoodStock.getPlant() < foodNeeded) {
                if (FoodStock.getMeat() < foodNeeded) {
                    throw new NotEnoughFoodStock("Not enough Meat");
                } else {
                    throw new NotEnoughFoodStock("Not enough Plant");
                }
            }
            FoodStock.removeMeat(foodNeeded);
            FoodStock.removePlant(foodNeeded);
            System.out.println(getName() + " has been given " + foodNeeded + " kgs of meat and " + foodNeeded + " kgs of leaves");
        }
        catch (NotEnoughFoodStock e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        }
    }
    @Override
    public String cleanHabitat() {
        return "Sweeping the enclosure and replacing branches.";
    }
}