import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ZooManager {
    private Map<String, Animal> animals;
    private Map<Integer, People> people;


    /**
     * Constructor for the ZooManager class
     */
    public ZooManager() {
        animals = new HashMap<>();
        people = new HashMap<>();
    }

    public void initializeAnimals(String animalsFile) throws IOException {
        System.out.println("***********************************");
        System.out.println("***Initializing Animal information***");

        try (BufferedReader reader = new BufferedReader(new FileReader(animalsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0];
                String name = parts[1];
                int age = Integer.parseInt(parts[2]);

                Animal animal = createAnimal(type, name, age);
                if (animal != null) {
                    animals.put(name, animal);
                    System.out.println("Added new " + type + " with name " + name + " aged " + age + ".");
                }
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private Animal createAnimal(String type, String name, int age) {
        switch (type) {
            case "Lion":
                return new Lion(name, age);
            case "Elephant":
                return new Elephant(name, age);
            case "Penguin":
                return new Penguin(name, age);
            case "Chimpanzee":
                return new Chimpanzee(name, age);
            default:
                return null;
        }
    }

    public void initializePeople(String personsFile, String id) throws IOException {
        System.out.println("***********************************");
        System.out.println("***Initializing Visitor and Personnel information***");

        try (BufferedReader reader = new BufferedReader(new FileReader(personsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0];
                String name = parts[1];
                id = (parts[2]);

                People person = createPerson(type, name, id);
                if (person != null) {
                    people.put(id, person);
                    System.out.println("Added new " + type + " with id " + id + " and name " + name + ".");
                }
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private People createPerson(String type, String name, int id) {
        switch (type) {
            case "Visitor":
                return new Visitor(name, id);
            case "Personnel":
                return new Personnel(name, id);
            default:
                return null;
        }
    }

    public void initializeFoodStock(String foodsFile) throws IOException {
        System.out.println("***********************************");
        System.out.println("***Initializing Food Stock***");

        double meat = 0, fish = 0, plant = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(foodsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0];
                double amount = Double.parseDouble(parts[1]);

                switch (type) {
                    case "Meat":
                        meat = amount;
                        break;
                    case "Fish":
                        fish = amount;
                        break;
                    case "Plant":
                        plant = amount;
                        break;
                }
            }
        }


        FoodStock.initialize(meat, fish, plant);
    }

    public void processCommands(String commandsFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(commandsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("***********************************");
                System.out.println("***Processing new Command***");

                try {
                    String[] parts = line.split(",");
                    String command = parts[0];

                    switch (command) {
                        case "List Food Stock":
                            FoodStock.listFoodStock();
                            break;
                        case "Animal Visitation":
                            processVisitation(parts);
                            break;
                        case "Feed Animal":
                            processFeeding(parts);
                            break;
                        default:
                            System.out.println("Unknown command: " + command);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error processing command: " + line);
                    System.out.println("Error: " + e.getMessage());
                } catch (Exception e) {
                    // Don't print "Error processing command" for expected application errors
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }
    public void processVisitation(String[] parts) throws Exception {
        int personId = Integer.parseInt(parts[1]);
        String animalName = parts[2];

        People person = people.get(personId);
        if (person == null) {
            throw new Exception("There are no visitors or personnel with the id " + personId);
        }

        Animal animal = animals.get(animalName);

        if (person instanceof Visitor) {
            System.out.println(person.getName() + " tried  to register for a visit to " + animalName + ".");
        } else if (person instanceof Personnel) {
            System.out.println(person.getName() + " attempts to clean " + animalName + "'s habitat.");
        }

        if (animal == null) {
            throw new Exception("There are no animals with the name " + animalName + ".");
        }

        System.out.println(person.visitAnimal(animal));
    }

    private void processFeeding(String[] parts) throws Exception {
        int personId = Integer.parseInt(parts[1]);
        String animalName = parts[2];
        int numberOfMeals = Integer.parseInt(parts[3]);

        People person = people.get(personId);
        if (person == null) {
            throw new Exception("There are no visitors or personnel with the id " + personId);
        }

        Animal animal = animals.get(animalName);
        if (animal == null) {
            throw new Exception(
                    "There are no animals with the name " + animalName + ".");
        }

        if (person instanceof Visitor) {
            System.out.println(person.getName() + " tried to feed " + animalName);
        } else if (person instanceof Personnel) {
            System.out.println(person.getName() + " attempts to feed " + animalName + ".");
        }
        person.feedAnimal(animal, numberOfMeals);
    }

}