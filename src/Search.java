import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;

public class Search {
    public static ArrayList<Doctor> docList = new ArrayList<>();
    public static ArrayList<Doctor> filteredSearch = new ArrayList<>();

    public static ArrayList<Doctor> search(){

        Path fileName = Paths.get("random_doctors_florida_zipcodes.csv");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try(BufferedReader reader = Files.newBufferedReader(fileName)){
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null){
                // tokenize to pass 
                  String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                // Remove surrounding quotes from each token
                for (int i = 0; i < tokens.length; i++) {
                    tokens[i] = tokens[i].replaceAll("^\"|\"$", "");
                }

                Date enumerationDate = null;
                
                try{
                    enumerationDate = dateFormat.parse(tokens[7]);
                }catch(ParseException e){
                    e.printStackTrace();
                }
                
                Doctor doctor = new Doctor(
                            tokens[0],                  
                            tokens[1].charAt(0),     
                            tokens[2],                  
                            Integer.parseInt(tokens[3]),
                            tokens[4].charAt(0),       
                            tokens[2],                
                            enumerationDate,            
                            tokens[8].split(";"),      
                            tokens[9].split(";"),      
                            tokens[5],                 
                            tokens[10],                 
                            tokens[11].split(";"),     
                            Boolean.parseBoolean(tokens[12]),
                            tokens[13],                
                            tokens[14],                
                            tokens[15],                
                            Float.parseFloat(tokens[16])); 
                        
                    docList.add(doctor);    
            }
            
            
                
        } catch(IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
        return docList;
    }
            
    }

    
