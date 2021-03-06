import java.util.*;
import java.io.*;

public class RodCutter {
  public static int [] cutRod(int [] prices, int length) {
    //store the values for each length
    int [] value = new int [length + 1];
    //store the cut that had to be made at that length
    int [] cuts = new int [length + 1];
    //set the initial value to be 0;
    value[0] = 0;
    //for each length from 1 to the length
    for (int i = 1; i <= length; i++) {
      //keep track of the highest value
      int maxSoFar = -1;
      //make cuts from length 0 (no cutting), all the way to cutting the
      //current length - 1
      for (int j = 0; j < i; j++) {
        //if the cuts you made yield a more valuable store
        if (maxSoFar < prices[j] + value[i-j-1]) {
          //keep track of it, and the cut you made
          maxSoFar = prices[j] + value[i-j-1];
          cuts[i] = j+1;
        }
      }
      //print out the highest value of the cuts of that length
      System.out.println("total for length " + i + "\t= " + maxSoFar);
      //store that value
      value[i] = maxSoFar;
    }
    //return the cuts made
    return cutsMade(cuts, length);
  }

  private static int [] cutsMade(int [] cuts, int length) {
    //creates array to store how many of each cut is made
    int [] cutsMade = new int [length + 1];
    //keeps track of how much of the rod is leftover
    int remainder = length;
    //while there is still some of the rod left to be cut
    while (remainder > 0) {
      //find how much you cut at that length;
      int cutLength = cuts[remainder];
      //increment that cut length's value
      cutsMade[cutLength]++;
      //calculate how much is left over
      remainder = remainder - cuts[remainder];
    }
    //return the cuts made
    return cutsMade;
  }

  private static void printCuts(int [] cutsMade) {
    //go through every cut made
    for (int i = 0; i < cutsMade.length; i++) {
      //if you made that type of cut
      if (cutsMade[i] != 0) {
        //print it out, and how many times you cut a piece of that length
        System.out.println("Number of rods of length " + i + "\t= "
                            + cutsMade[i]);
      }
    }
    System.out.println();
  }

  public static void main(String [] argv) {
    Scanner scan = new Scanner(System.in);
    System.out.print("What is the name of your file: ");
    String filename = scan.next();
    try {
      Scanner fileScanner = new Scanner(new File(filename));
      int testCount = Integer.parseInt(fileScanner.nextLine().split(" ")[0]);
      for (int i = 1; i <= testCount; i++) {
        System.out.println("Test " + i + " -------------------------");
        int length = Integer.parseInt(fileScanner.nextLine().split(" ")[0]);
        int [] prices = new int[length];
        String [] data = fileScanner.nextLine().split(" ");
        for (int j = 0; j < length; j++) {
          prices[j] = Integer.parseInt(data[j]);
        }
        printCuts(cutRod(prices, length));
      }
    } catch (Exception e) {
      System.out.print(e);
    }
  }

}
