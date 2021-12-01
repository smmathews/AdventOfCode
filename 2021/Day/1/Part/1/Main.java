import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Integer lastInteger = null;
        int increases = 0;
        try (Scanner scanner = new Scanner(new File("input"))) {
            while(scanner.hasNextInt()) {
                int nextInt = scanner.nextInt();
                if(lastInteger != null && lastInteger < nextInt) {
                    ++increases;
                }
                lastInteger = nextInt;
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(increases);
    }
}