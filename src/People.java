
public abstract class People {
    private String name;
    private int id;

    public People(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }

    public abstract String visitAnimal(Animal animal);


    public abstract void feedAnimal(Animal animal, int numberOfMeals) throws Exception;
}

class Visitor extends People {

    public Visitor(String name, int id) {
        super(name, id);
    }

    @Override
    public String visitAnimal(Animal animal) {
        return getName() + " successfully visited " + animal.getName() + ".";
    }

    @Override
    public void feedAnimal(Animal animal, int numberOfMeals) throws Exception {
        throw new Exception("Visitors do not have the authority to feed animals.");
    }
}

class Personnel extends People {

    public Personnel(String name, int id) {
        super(name, id);
    }

    @Override
    public String visitAnimal(Animal animal) {
        return getName() + "started cleaning " + animal.getName() + "'s habitat.\n" +
                "Cleaning " + animal.getName() + "'s habitat: " + animal.cleanHabitat();
    }

    @Override
    public void feedAnimal(Animal animal, int numberOfMeals) throws Exception {
        animal.feed(numberOfMeals);
    }
}