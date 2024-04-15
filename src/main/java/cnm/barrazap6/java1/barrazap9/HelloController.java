//ALexis Barraza abarraza24@cnm.edu
//Using DataBase in JAVA Intellij Idea
//BarrazaP9 Fav Movie DataBase.

package cnm.barrazap6.java1.barrazap9;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class HelloController {
    //Database object
    DBManager dbm;

    @FXML
    private Button btnCreate;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnDisplay;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnID;

    @FXML
    private Button btnInsert;

    @FXML
    private Button btnPopulate;

    @FXML
    private TextField txbDirector;

    @FXML
    private TextField txbGross;

    @FXML
    private TextField txbID;

    @FXML
    private TextArea txbMovies;

    @FXML
    private TextField txbTitle;

    @FXML
    private TextField txbYear;

    //Button to create a table in the database
    @FXML
    void createTable(ActionEvent event) {
        dbm = new DBManager();
        dbm.createTable();

    }
    //Button to delete a record from the database
    @FXML
    void onDelete(ActionEvent event) {
        //Checks if the DBManager object is initialized
        if(dbm != null)
        {
            //Checks if the database is populated with records
            if(!(dbm.getLastID() > 0)) txbMovies.setText("[ERROR] You must populate a table before using it");
            else {
                int id = 0;
                try {
                    //Get's the ID of the record to be deleted from the txt field
                    id = Integer.parseInt(txbID.getText());
                } catch (NumberFormatException ex) {
                }
                //Check if the entered ID is valid
                if (id > 0 && id <= dbm.getLastID()) {
                    //Deleted the record with specific id
                    //Clears the ID text field
                    //refreshes the display to reflect the changes
                    dbm.deleteRecord(id);
                    txbID.setText("");
                    onDisplay(event);
                } else txbMovies.setText("[ERROR] Enter a valid record ID");
            }
        }
        else txbMovies.setText("[ERROR] You must create and populate a table before using it");
    }
    //Method to display records from the databse
    @FXML
    void onDisplay(ActionEvent event) {
        //Check if the DBManager object is initialized
        if(dbm != null)
        {
            int tempSize = 0;
            try{
                //gets the number of records in the database
                tempSize = dbm.getLastID();
            }catch (Exception ex){txbMovies.setText("[ERROR] You must populate a table before using it");}
            //Checks if there are no records in the data base
            if(tempSize == 0) txbMovies.setText("[ERROR] You must populate a table before using it");
            else {
                int id = 0;
                try {
                    //gets the ID entered by the user
                    id = Integer.parseInt(txbID.getText());
                } catch (NumberFormatException ex) {
                }
                String results = "";
                //If no ID is entered
                //or if the entered ID is greater than the last ID in the database
                if (id == 0 || id > dbm.getLastID()) {
                    //Looping through each record in the database
                    for (int i = 1; i <= dbm.getLastID(); i++) {
                        if (i > 1) {
                            results += "\n" + i + " ";
                        } else results += i + " ";
                        //Get the details of the record and appends the ruslts to a string
                        for (String temp : dbm.getRecordById(i)) results += temp + "\t\t";
                    }
                } else
                {
                    //If a specific ID is entered
                    // we get the details of that record
                    results += id + " ";
                    for (String temp : dbm.getRecordById(id)) results += temp + "\t\t";
                }
                txbMovies.setText(results);
            }
        }
        else txbMovies.setText("[ERROR] You must create and populate a table before using it");
    }

    @FXML// Method to edit a record in the database
    void onEdit(ActionEvent event) {
        // Check if the DBManager object is initialized
        if(dbm != null) {
            // Check if the database is populated with records
            if(!(dbm.getLastID() > 0))
                txbMovies.setText("[ERROR] You must populate a table before using it");
            else {
                int id = 0;
                try {
                    // Get the ID of the record to be edited from the text field
                    id = Integer.parseInt(txbID.getText());
                } catch (NumberFormatException ex) {
                    // Display an error message if the entered ID is not a number
                    txbMovies.setText("[ERROR] You must enter a number");
                }
                String results = "";
                // Check if the entered ID is valid
                if (id > 0 && id <= dbm.getLastID()) {
                    String title = "";
                    String director = "";
                    int year = 0;
                    Double gross = 0.00;
                    try {
                        // Get the values for title, director, year, and gross from the corresponding text fields
                        title = txbTitle.getText();
                        director = txbDirector.getText();
                        year = Integer.parseInt(txbYear.getText());
                        gross = Double.parseDouble(txbGross.getText());
                    } catch (NumberFormatException ex) {
                        // Display an error message if any of the fields are not correctly populated
                        txbMovies.setText("[ERROR] All fields must be correctly populated");
                    }
                    // Check if all fields are correctly populated
                    if (title != "" && director != "" && year > 0 && gross > 0.00) {
                        // Edit the record in the database with the new values
                        dbm.editRecord(id, title, director, year, gross);
                        // Refresh the display to reflect the changes
                        onDisplay(event);
                        // Clear the text fields after editing
                        txbID.setText("");
                        txbTitle.setText("");
                        txbDirector.setText("");
                        txbYear.setText("");
                        txbGross.setText("");
                    } else
                        // Display an error message if any field is not correctly populated
                        txbMovies.setText("[ERROR] All fields must be correctly populated before submitting edit");
                } else
                    // Display an error message if the entered ID is invalid
                    txbMovies.setText("[ERROR] Enter a valid record ID");
            }
        } else
            // Display an error message if the DBManager object is not initialized
            txbMovies.setText("[ERROR] You must create and populate a table before using it");
    }


    @FXML// Method to handle the action event triggered when an ID is entered
    void onEnterID(ActionEvent event) {
        // Check if the DBManager object is initialized
        if(dbm != null) {
            // Check if the database is populated with records
            if(!(dbm.getLastID() > 0))
                // If the database is not populated, display an error message
                txbMovies.setText("[ERROR] You must populate a table before using it");
            else {
                int id = 0;
                try {
                    // Parse the ID entered by the user from the txbID TextField
                    id = Integer.parseInt(txbID.getText());
                } catch (NumberFormatException ex) {
                    // If the entered value is not a number, display an error message
                    txbMovies.setText("[ERROR] You must enter a number");
                }
                // If the entered ID is within the range of valid IDs in the database
                if (id > 0 && id <= dbm.getLastID()) {
                    // Retrieve the details of the record associated with the entered ID
                    var temp = dbm.getRecordById(id);
                    // Set the TextFields with the details of the retrieved record
                    txbTitle.setText(temp[0]);
                    txbDirector.setText(temp[1]);
                    txbYear.setText(temp[2]);
                    txbGross.setText(temp[3]);
                } else
                    // If the entered ID is invalid, display an error message
                    txbMovies.setText("[ERROR] Enter a valid record ID");
            }
        } else
            // If the DBManager object is not initialized, display an error message
            txbMovies.setText("[ERROR] You must create and populate a table before using it");
    }


    // Method to handle the action event triggered when inserting a new record
    @FXML
    void onInsert(ActionEvent event) {
        // Check if the DBManager object is initialized
        if(dbm != null) {
            // Check if the database is populated with records
            if(!(dbm.getLastID() > 0))
                // If the database is not populated, display an error message
                txbMovies.setText("[ERROR] You must populate a table before using it");
            else {
                // Initialize variables to store record details
                String title = "";
                String director = "";
                int id = 0;
                int year = 0;
                Double gross = 0.00;
                try {
                    // Parse the values entered by the user from the TextFields
                    id = Integer.parseInt(txbID.getText());
                    title = txbTitle.getText();
                    director = txbDirector.getText();
                    year = Integer.parseInt(txbYear.getText());
                    gross = Double.parseDouble(txbGross.getText());
                } catch (NumberFormatException ex) {
                    // If any field is not correctly populated, display an error message
                    txbMovies.setText("[ERROR] All fields must be correctly populated!");
                }
                // Check if all fields are correctly populated
                if (title != "" && director != "" && year > 0 && gross > 0.00) {
                    // Check if the entered ID is greater than the last ID in the database
                    if (id > dbm.getLastID()) {
                        // Insert the new record into the database
                        dbm.insert(id, title, director, year, gross);
                        // Refresh the display to show the new record
                        onDisplay(event);
                        // Clear the TextFields for the next entry
                        txbID.setText("");
                        txbTitle.setText("");
                        txbDirector.setText("");
                        txbYear.setText("");
                        txbGross.setText("");
                    } else
                        // If the entered ID already exists or is not provided, display an error message
                        txbMovies.setText("[ERROR] This ID already exists or you are trying to create a record without an ID");
                }
            }
        } else
            // If the DBManager object is not initialized, display an error message
            txbMovies.setText("[ERROR] You must create and populate a table before using it");
    }



    @FXML   // Method to handle the action event triggered when populating the database
    void populateDatabase(ActionEvent event) {
        // Call the populateDatabase method of the DBManager object to populate the database
        dbm.populateDatabase();
        // Refresh the display to show the populated database
        onDisplay(event);
    }


}
