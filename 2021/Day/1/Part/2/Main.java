import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> lastIntegersQueue= new ArrayList<>(4);
        int increases = 0;
        try (Scanner scanner = new Scanner(new File("input"))) {
            while(scanner.hasNextInt()) {
                int added = scanner.nextInt();
                lastIntegersQueue.add(added);
                if(lastIntegersQueue.size() > 3) {
                    int removed = lastIntegersQueue.remove(0);
                    // if what we added is greter than what we removed, our window has increased
                    if(added > removed) {
                        ++increases;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(increases);
    }
}