import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LinearSearch {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String search;
        System.out.println("Enter a keyword to search for: ");
        search = s.next();
        int line = 0;
        Airport[] airport = new Airport[7700];

        try {
            FileReader readfile = new FileReader("airports.csv");
            BufferedReader readBuffer = new BufferedReader(readfile);
            Scanner sc = new Scanner(readBuffer);

            while (sc.hasNextLine()) {
                line++;
                String[] values = sc.nextLine().split(",");
                airport[line] = new Airport(values[0], values[1], values[2], values[3], values[4], values[5],values[6],values[7], values[8]);

                if (sc.nextLine().contains(search)) {
                    System.out.println(search+" was found on line "+(line));
                    String word = sc.nextLine();
                    System.out.println(word);
                }
            }
            sc.close(); 

        } catch (IOException e) {
            e.printStackTrace();
        } 
        s.close();
    }
}