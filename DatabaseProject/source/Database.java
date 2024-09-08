import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;
import java.io.FileWriter;  
import java.io.IOException;

public class Database {
    ArrayList<Employee> employees = new ArrayList<Employee>(); 
    ArrayList<Employee> searchResults = new ArrayList<Employee>(); 

    public Database(){
       
        try {
            File myObj = new File("./DatabaseProject/employees.csv");
            Scanner myReader = new Scanner(myObj);
            int index = 0; 
            while (myReader.hasNextLine()) {
                
                String [] data = myReader.nextLine().split(",");
                double jobID = Double.parseDouble(data[0]); 
                String gender = data[1]; 
                String birthDate = data[2]; 
                String education = data[3];  
                String jobCategory = data[4]; 
                double currentSalary = Double.parseDouble(data[5]); 
                double beginningSalary = Double.parseDouble(data[6]); 
                boolean minority; 
                if(data[9].equals("Yes")){
                    minority = true; 
                } else {
                    minority = false; 
                }

                Employee e = new Employee(jobID,gender,birthDate,education,jobCategory,currentSalary,beginningSalary,minority); 
                index+=1;
                employees.add(e); 



            }
            myReader.close();           

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    
    public void printEmployees(){
        for(Employee e: employees){
            System.out.println(e); 
        }
    }

    public ArrayList<Employee> getEmployees(){
        return employees; 
    }

    public ArrayList<Employee> getSearchResults(){
        return searchResults; 
    }

    public String displayData(ArrayList<Employee> input){

        String result = "\n" + MyString.repeat("-",90) + "\n";
        result += MyString.formatString("JOB ID", 15); 
        result += MyString.formatString("BIRTHDATE", 15); 
        result += MyString.formatString("CATEGORY", 15); 
        result += MyString.formatString("MINORITY?", 15); 
        result += MyString.formatString("GENDER", 15); 
        result += MyString.formatString("SALARY", 15); 
        result += "\n" + MyString.repeat("-",90) + "\n";

        for(int index = 0; index < input.size(); index++){
            Employee e = input.get(index);

            result += e.toString(); 

        }
        result += "\n" + MyString.repeat("-",90) + "\n";
        
        return result; 
    }



     public ArrayList<Employee> search(String input){
        String[] queries = input.toLowerCase().split(" "); 
        ArrayList<Employee> list = new ArrayList<Employee>(); 

        for(Employee e: employees){
            boolean result = true; 
            for(String query: queries){
                if(!e.toString().toLowerCase().contains(query.toLowerCase())){
                    result = false; 
                }
            }

            if(result == true){
                list.add(e); 
            }
            
        }
        searchResults = list; 
        return list; 
    }

    public boolean delete(int ID){
        
        for(int i = 0; i < searchResults.size(); i++){
            Employee e = searchResults.get(i); 
            if(e.getJobID() == ID){
                searchResults.remove(i); 
               return true; 
            }
        }
        return false; 
    }

    public boolean save(){
        try {
            FileWriter myWriter = new FileWriter("./DatabaseProject/results.txt",false);
            myWriter.write(displayData(searchResults));
            myWriter.close();
            
            return true; 

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();

            return false; 
        }
    }


    public void sort(){
        for (int j = 1; j < employees.size(); j++){
                Employee temp = employees.get(j);
                int possibleIndex = j;

                //Keep comparing until we find the place to swap
                while (possibleIndex > 0 && temp.getSalary() < employees.get(possibleIndex - 1).getSalary()){
                    employees.set(possibleIndex,employees.get(possibleIndex - 1));
                    possibleIndex--;
                }
                employees.set(possibleIndex, temp); 
         }
        
         
    }
    
    /*
    public static void main(String[] args) {
        Database db = new Database(); 
        //System.out.println(db.displayData(db.search("male true Custodial"))); 
        
    }*/
    

}
