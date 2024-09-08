import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;

public class Employee {
    private int jobID;
    private String gender; 
    private String birthDate; 
    private String education; 
    private String jobCategory; 
    private double currentSalary; 
    private double beginningSalary; 
    private boolean minority; 
     
    public Employee(double jobID, String gender, String birthDate, String education, String jobCategory,double currentSalary, double beginningSalary, boolean minority){
        this.jobID = (int) jobID;
        this.gender = gender;
        this.birthDate = birthDate;
        this.education = education;
        this.jobCategory = jobCategory;
        this.currentSalary = currentSalary;
        this.beginningSalary = beginningSalary;
        this.minority = minority;

    }

    public int getJobID(){
        return jobID; 
    }

    public String getBirthDate(){
        return birthDate; 
    }

    public String getEducation(){
        return education;
    }

    public String getJobCategory(){
        return jobCategory; 
    }

    public boolean getMinorityStatus(){
        return minority; 
    }

    public String getGender(){
        return gender; 
    }

    public double getSalary(){
        return currentSalary; 
    }

     public double getBeginningSalary(){
        return beginningSalary; 
    }

    public String toString(){
        
        String result = ""; 
        //Print the job ID
        result += "\n" + MyString.formatString("" + jobID, 15); 

        //Print the birthDate
        result += MyString.formatString(birthDate, 15); 

        //Print the job category
        result += MyString.formatString(jobCategory, 15); 

        //Print whether or not the person is a minority
        result += MyString.formatString(MyString.convertBoolean(minority), 15); 

        //Print the person's gender
        result += MyString.formatString(gender, 15); 

        //Print the person's current salary 
        result += MyString.formatString("" + currentSalary, 15); 

        //Make newline character before moving on 
        result += "\n"; 

        return result; 
    }

}