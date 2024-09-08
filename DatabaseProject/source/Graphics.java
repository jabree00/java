//Usually you will require both swing and awt packages
// even if you are working with just swings.

import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 
import java.util.ArrayList;

public class Graphics {
    private Database db; 
    private JFrame frame; //Holds all the graphics, labels, buttons, input, etc. 
    private JTextArea displayArea; //Show the results of the database queries 
    private JPanel panel; //Holds the buttons 
    private JTextField input; //Lets the user type into the console
    private JButton displayButton; 
    private JButton searchButton; 
    private JButton sortButton; 
    private JButton saveButton; 
    private JButton deleteButton; 
    private JScrollPane scrollBar; 

    public Graphics(){
        db = new Database(); 
        displayArea = new JTextArea();
        frame = new JFrame("My Database");
        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        input = new JTextField(20);
        displayButton = new JButton("Display");
        searchButton = new JButton("Search");
        sortButton = new JButton("Sort");
        saveButton = new JButton("Save");
        deleteButton = new JButton("Delete");
        scrollBar = new JScrollPane(displayArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        
    }

    //Draws all elements of the graphical user interface
    public void setup(){
        //Creating the frame to hold all of the GUI elements
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        displayButton.addActionListener(new ButtonListener()); 
        displayButton.setActionCommand("display");
        searchButton.addActionListener(new ButtonListener()); 
        searchButton.setActionCommand("search");
        sortButton.addActionListener(new ButtonListener()); 
        sortButton.setActionCommand("sort");
        saveButton.addActionListener(new ButtonListener()); 
        saveButton.setActionCommand("save");
        deleteButton.addActionListener(new ButtonListener()); 
        deleteButton.setActionCommand("delete");

        //add label, input text, and buttons to the panel
        panel.add(displayButton);
        panel.add(sortButton);
        panel.add(deleteButton);
        panel.add(saveButton);
        panel.add(searchButton);
        panel.add(input);
    

        //Adding the panel and the displayArea to the frame
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(scrollBar, BorderLayout.CENTER);
        frame.setVisible(true);
        scrollBar.setVisible(true); 

        //Make the displayArea unable to be typed into 
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Courier", Font.BOLD,12));

    }

    //Experiment with changing the GUI style here
    public void changeStyle(){ 

        
    }

    public void displayInstructions(){
        String result = "Welcome to the Employee Database:\n"; 
        result += "Use the buttons below to choose an option:\n\n";
        result += "* Display the records in the database.\n"; 
        result += "* Search for a particular record.\n"; 
        result += "* Sort by salary.\n"; 
        result += "* Save the search results.\n"; 
        result += "* Delete a record from the search results."; 

        displayArea.setText(result); 

    }

    
    public static void main(String args[]) {
        Graphics g = new Graphics();     
        g.setup(); 
        g.changeStyle(); 
        g.displayInstructions(); 
      
    }
    
    
    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            String command = e.getActionCommand();

            if(command.equals("display")){

                //Call the displayData method on the database object 
                String result = db.displayData(db.getEmployees()); 
                //Show the result in the display area
                displayArea.setText(result); 
                
            } else if(command.equals("search")){
                //Call the searchByJobCategory method on the database object 
                String userInput = input.getText(); 
                
                if(userInput.equals("")){
                    input.setText("Type what you want to search for..."); 

                } else { 
                    ArrayList<Employee> result = db.search(userInput); 
                    //Show the result in the display area
                    displayArea.setText(db.displayData(result)); 
                }
                
            } else if(command.equals("sort")){
                //Call the sortBySalary method on the database object 
                db.sort(); 
                //Show the result in the display area
                displayArea.setText(db.displayData(db.getEmployees())); 

            } else if(command.equals("save")){
                //Call the sortBySalary method on the database object 
                if(db.save()){
                    displayArea.setText("Saved latest results to results.txt."); 
                } else {
                    displayArea.setText("An error occured. Results could not be saved."); 
                }
          
            } else if(command.equals("delete")){

                //Call the delete method on the database object 
                
                String userInput = input.getText();
                
                if(userInput.equals("")){
                    input.setText("Type the ID of the record to be deleted."); 
                } else {
                    try { 
                        int number = Integer.parseInt(userInput); 
                        db.delete(number); 
                
                        //Show the result in the display area
                        displayArea.setText(db.displayData(db.getSearchResults()));

                    } catch (Exception except){
                        input.setText("Type a numeral, no words."); 
                    }
                }

                
            }
            
        }
    } //end ButtonListener class

} //end Graphics class