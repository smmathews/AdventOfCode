import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int numberOfLines = 0;
        final int numberOfBits = 12;
        int[] bitCounter= new int[numberOfBits];
        try (Scanner scanner = new Scanner(new File("input"))) {
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                ++numberOfLines;
                for (int i = 0; i < numberOfBits; i++) {
                    bitCounter[i] += (line.charAt(i) == '1') ? 1 : 0;
                }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        final int halfNumberOfLines = numberOfLines/2;
        int gamma = 0;//most commmon bits
        int epsilon = 0;
        for (int i = 0; i < numberOfBits; i++) {
            final int toAdd =(1 << (numberOfBits-i-1));
            if(bitCounter[i] == halfNumberOfLines) {
                gamma += toAdd;
                epsilon += toAdd;
            }
            else if(bitCounter[i] > halfNumberOfLines) {
                gamma += toAdd;
            } else {
                epsilon += toAdd;
            }
        }

        System.out.println(gamma*epsilon);
    }
}