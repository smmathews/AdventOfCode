import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        long depth = 0;
        long horizontal = 0;
        long aim = 0;
        try (Scanner scanner = new Scanner(new File("input")).useDelimiter("\\s")) {
            while(scanner.hasNextLine()) {
                var direction = scanner.next();
                var distance = scanner.nextInt();
                switch (direction.charAt(0)) {
                    case 'f'://forward
                        horizontal += distance;
                        depth += distance*aim;
                        break;
                    case 'u'://up
                        aim -= distance; 
                        break;
                    case 'd'://down
                        aim += distance;
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println((depth*horizontal));
    }
}