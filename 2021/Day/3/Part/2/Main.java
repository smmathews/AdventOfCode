import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int numberOfBits = 12;
        ArrayList<Integer> originalBitMasks = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("input"))) {
            while(scanner.hasNextLine()) {
                int line = Integer.parseInt(scanner.nextLine(), 2);
                originalBitMasks.add(line);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ArrayList<Integer> oxygenBitMasks = new ArrayList<>(originalBitMasks);
        ArrayList<Integer> co2BitMasks = new ArrayList<>(originalBitMasks);
        for (int bitI = 0; bitI < numberOfBits && (oxygenBitMasks.size() > 1 || co2BitMasks.size() > 1); bitI++) {
            final int finalBiti = bitI;
            if(oxygenBitMasks.size() > 1) {
                int numberOfOnes = 0;
                for (Integer o : oxygenBitMasks) {
                    numberOfOnes += (o & (1 << (numberOfBits-bitI-1))) != 0 ? 1 : 0;
                }
                var iter = oxygenBitMasks.iterator();
                boolean hasSkippedItem = false;
                boolean removeZeroes = numberOfOnes >= (float)oxygenBitMasks.size()/2;
                while(iter.hasNext()) {
                    var o = iter.next();
                    var toTest = (o & (1 << (numberOfBits-bitI-1))) != 0 ? 1 : 0;
                    if(removeZeroes) {
                        if(toTest == 0 && (hasSkippedItem || iter.hasNext())) {
                            iter.remove();
                        } else {
                            hasSkippedItem = true;
                        }
                    } else {
                        if(toTest != 0 && (hasSkippedItem || iter.hasNext())) {
                            iter.remove();
                        } else {
                            hasSkippedItem = true;
                        }
                    }
                }
            }
            if(co2BitMasks.size() > 1) {
                int numberOfOnes = 0;
                for (Integer o : co2BitMasks) {
                    numberOfOnes += (o & (1 << (numberOfBits-bitI-1))) != 0 ? 1 : 0;
                }
                var iter = co2BitMasks.iterator();
                boolean hasSkippedItem = false;
                boolean removeOnes = numberOfOnes >= (float)co2BitMasks.size()/2;
                while(iter.hasNext()) {
                    var o = iter.next();
                    var toTest = (o & (1 << (numberOfBits-bitI-1))) != 0 ? 1 : 0;
                    if(removeOnes) {
                        if(toTest != 0 && (hasSkippedItem || iter.hasNext())) {
                            iter.remove();
                        } else {
                            hasSkippedItem = true;
                        }
                    } else {
                        if(toTest == 0 && (hasSkippedItem || iter.hasNext())) {
                            iter.remove();
                        } else {
                            hasSkippedItem = true;
                        }
                    }
                }
            }
        }

        System.out.println(co2BitMasks.get(0)*oxygenBitMasks.get(0));
    }
}