package src;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Search {
    public static void search(){
        Doctor[] filteredSearch;

        Path fileName = Paths.get("random_doctors_florida_zipcodes.csv");

        try(BufferedReader reader = Files.newBufferedReader(fileName)){
            String line;

            while ((line = reader.readLine()) != null){
                // tokenize to pass 
                System.out.println(line);
                
        } catch(IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    }
}
    

