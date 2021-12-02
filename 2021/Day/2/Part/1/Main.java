import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int d = 0;
        int h = 0;
        try (Scanner scanner = new Scanner(new File("input")).useDelimiter("\\s")) {
            while(scanner.hasNextLine()) {
                var direction = scanner.next();
                var distance = scanner.nextInt();
                switch (direction.charAt(0)) {
                    case 'f'://forward
                        h += distance;
                        break;
                    case 'u'://up
                        d -= distance; 
                        break;
                    case 'd'://down
                        d += distance;
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println((d*h));
    }
}