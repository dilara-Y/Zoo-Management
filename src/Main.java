import java.io.*;

public class Main {
    public static void main(String[] args, String id) throws IOException {
        if (args.length < 5) {
            System.out.println("Usage: java Main <animals_file> <persons_file> <foods_file> <commands_file> <output_file>");
            return;
        }

        String outputFile = args[4];

        PrintStream fileOut = new PrintStream(new FileOutputStream(outputFile));
        System.setOut(fileOut);

        ZooManager zooManager = new ZooManager();
        zooManager.initializeAnimals(args[0]);
        zooManager.initializePeople(args[1]);
        zooManager.initializeFoodStock(args[2]);
        zooManager.processCommands(args[3]);

        fileOut.close();
    }
}