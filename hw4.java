//Sara Schultz CSE360

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.*;
import java.util.Random;
import java.text.*;

public class hw4 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    //variable declaration
    Pane root;
    final int SCENEWIDTH = 1500, SCENEHEIGHT = 900;
    VBox patientIntakeViewPane, homePage, technicianViewVBox, patientViewVBox, patientViewEnterPatientIDVBox;
    HBox patientViewEnterPatientIDHBox;
    Label patientViewEnterPatientIDLabel, patientViewEnterPatientIDErrorLabel;
    Button patientViewEnterPatientIDButton;
    TextField patientViewEnterPatientIDTF;
    Button patientIntakeButton, CTScanTechViewButton, patientViewButton;
    HBox firstNameHBox, lastNameHBox, emailHBox, phoneNumberHBox, healthHistoryHBox, insuranceIDHBox;
    HBox patientIDTechViewHBox, totalCACScoreTechViewHBox, LMTechViewHBox, LADTechViewHBox, LCXTechViewHBox, RCATechViewHBox, PDATechViewHBox;
    HBox totalCACScorePatientViewHBox, LMPatientViewHBox, LADPatientViewHBox, LCXPatientViewHBox, RCAPatientViewHBox, PDAPatientViewHBox;
    boolean orderButtonPressed, cancelButtonPressed, confirmButtonPressed;
    Label welcome, helloPatientNameLabel, patientIDTechViewLabel, totalCACScoreLabel, vesselLevelCACScoreTechViewLabel, LMLabel, LADLabel, LCXLabel, RCALabel, PDALabel;
    Label LMLabelPV, LADLabelPV, LCXLabelPV, RCALabelPV, PDALabelPV, totalCACScoreLabelPV;
    Label patientViewTotalCACScore, patientViewLMScore, patientViewLADScore, patientViewLCXScore, patientViewRCAScore, patientViewPDAScore;
    //text fields
    TextField firstNameTF, lastNameTF, emailTF, phoneNumberTF, healthHistoryTF, insuranceIDTF;
    TextField patientIDTF, totalCACScoreTF, LMTF, LADTF, LCXTF, RCATF, PDATF;
    Label patientIntakeFormLabel, firstNameLabel, lastNameLabel, emailLabel, phoneNumberLabel, healthHistoryLabel, insuranceIDLabel;
    Button patientIntakeFormSaveButton, techViewSaveButton;
    Label errorLabel;
    //patient name
    //String patientName; // I don't know where we are getting their name from
    String patientIntakeFormTextFileString;

    public void start(Stage primaryStage) {
        //System.out.println("ASU Hello World!");
        //System.out.println("It started!");
        primaryStage.setTitle("Heart Health Imaging and Recording System");

        //patient name
        //patientName = "<Patient Name>";

        //boolean
        orderButtonPressed = false;
        cancelButtonPressed = false;
        confirmButtonPressed = false;

        //labels
        welcome = new Label("Welcome to Heart Health Imaging and Recording System");
        errorLabel = new Label("");
        patientViewEnterPatientIDLabel = new Label("Enter patient ID: ");
        patientViewEnterPatientIDErrorLabel = new Label("");
        patientViewEnterPatientIDTF = new TextField();

        //Buttons and their handlers
        patientIntakeButton = new Button();
        CTScanTechViewButton = new Button();
        patientViewButton = new Button();
        patientIntakeButton.setText("Patient Intake");
        CTScanTechViewButton.setText("CT Scan Tech View");
        patientViewButton.setText("Patient View");
        patientViewEnterPatientIDButton = new Button("Enter");

        //Patient Intake Form Elements
        patientIntakeFormLabel = new Label("Patient Intake Form");
        patientIntakeFormSaveButton = new Button();
        patientIntakeFormSaveButton.setText("Save");
        firstNameLabel = new Label("First Name:");
        lastNameLabel = new Label("Last Name:");
        emailLabel = new Label("Email:");
        phoneNumberLabel = new Label("Phone Number:");
        healthHistoryLabel = new Label("Health History:");
        insuranceIDLabel = new Label("Insurance ID:");
        firstNameTF = new TextField();
        lastNameTF = new TextField();
        emailTF = new TextField();
        phoneNumberTF = new TextField();
        healthHistoryTF = new TextField();
        insuranceIDTF = new TextField();
        firstNameTF.getText();

        //Patient Intake Button
        patientIntakeButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                //Once the user presses the patient intake button, the patient intake form will
                //become visible and the others will be invisible
                homePage.setVisible(false);
                technicianViewVBox.setVisible(false);
                patientViewVBox.setVisible(false);
                patientViewEnterPatientIDVBox.setVisible(false);
                patientIntakeViewPane.setVisible(true);
                //primaryStage.setTitle("Receptionist View");

            }
        });
        //when we press the save button, we want to go back to the home page and save
        // all the patient data to a text file (save first, then exit)
        //Save Patient Intake Form Button
        patientIntakeFormSaveButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent patientIntakeSaveButtonPressed) {
                homePage.setVisible(true);
                patientViewVBox.setVisible(false);
                patientViewEnterPatientIDVBox.setVisible(false);
                technicianViewVBox.setVisible(false);
                patientIntakeViewPane.setVisible(false);
                String patientIntakeFormTextFileString = "";
                patientIntakeFormTextFileString += firstNameTF.getText() + ","
                        + lastNameTF.getText() + ","
                        + emailTF.getText() + ","
                        + phoneNumberTF.getText() + ","
                        + healthHistoryTF.getText() + ","
                        + insuranceIDTF.getText();
                //System.out.println(patientIntakeFormTextFileString);
                //generate random 5 digit ID
                Random rand = new Random();
                int fiveDigitID = rand.nextInt(90000) + 10000;

                try {
                    String fileName = fiveDigitID + "_PatientInfo.txt";
                    File file = new File(fileName);
                    if (!file.exists()) {
                        FileWriter fw = new FileWriter(file);
                        fw.write(patientIntakeFormTextFileString);
                        fw.close(); // Close FileWriter to save data
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.print("Error writing file");
                }
            }
        });

        // Technician View Elements
        patientIDTechViewLabel = new Label("Patient ID: ");
        patientIDTF = new TextField();
        totalCACScoreLabel = new Label("The total Agatston CAC Score ");
        totalCACScoreTF = new TextField();
        vesselLevelCACScoreTechViewLabel = new Label("Vessel level Agatston CAC Score");
        LMLabel = new Label("LM: ");
        LADLabel = new Label("LAD: ");
        LCXLabel = new Label("LCX: ");
        RCALabel = new Label("RCA: ");
        PDALabel = new Label("PDA: ");
        LMTF = new TextField();
        LADTF = new TextField();
        LCXTF = new TextField();
        RCATF = new TextField();
        PDATF = new TextField();
        techViewSaveButton = new Button("Save");

        //Open Tech View Button
        CTScanTechViewButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event2) {
                //Once the user presses the tech view button, the tech view will
                //become visible and the others will be invisible
                homePage.setVisible(false);
                patientViewVBox.setVisible(false);
                patientViewEnterPatientIDVBox.setVisible(false);
                technicianViewVBox.setVisible(true);
                patientIntakeViewPane.setVisible(false);

            }
        });
        //when we press the save button, we want to go back to the home page and save
        // all the patient data to a text file (save first, then exit)
        //Open Patient View Button
        techViewSaveButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent techViewSaveButtonPressed) {

                //if any of the text fields are empty, give error. Otherwise, save info
                //If any of the data items are missing an error message
                // should be shown to the technician and the file will not be modified
                if (patientIDTF.getText().trim().isEmpty() || totalCACScoreTF.getText().trim().isEmpty() || LMTF.getText().trim().isEmpty() || LADTF.getText().trim().isEmpty() || LCXTF.getText().trim().isEmpty() || RCATF.getText().trim().isEmpty() || PDATF.getText().trim().isEmpty()){
                    //System.out.print("Error. Please fill out all text fields before proceeding.");
                    errorLabel.setText("Error. Please fill out all text fields before proceeding.");
                }
                else {
                    errorLabel.setText("");
                    String techViewTextFileString = "";
                    techViewTextFileString += patientIDTF.getText() + ","
                            + totalCACScoreTF.getText() + ","
                            + LMTF.getText() + ","
                            + LADTF.getText() + ","
                            + LCXTF.getText() + ","
                            + RCATF.getText() + ","
                            + PDATF.getText();
                    try {
                        String fileName = patientIDTF.getText() + "CTResults.txt";
                        File file = new File(fileName);
                        if (!file.exists()) {
                            FileWriter fw = new FileWriter(file);
                            fw.write(techViewTextFileString);
                            fw.close(); // Close FileWriter to save data
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.print("Error writing file");
                    }
                    homePage.setVisible(true);
                    patientViewVBox.setVisible(false);
                    patientViewEnterPatientIDVBox.setVisible(false);
                    technicianViewVBox.setVisible(false);
                    patientIntakeViewPane.setVisible(false);
                }
            }
        });

        //Patient View Elements
        totalCACScoreLabelPV = new Label("The total Agatston CAC Score ");
        patientViewTotalCACScore = new Label();
        patientViewLMScore = new Label();
        patientViewLADScore = new Label();
        patientViewLCXScore = new Label();
        patientViewRCAScore = new Label();
        patientViewPDAScore = new Label();
        LMLabelPV = new Label("LM: ");
        LADLabelPV = new Label("LAD: ");
        LCXLabelPV = new Label("LCX: ");
        RCALabelPV = new Label("RCA: ");
        PDALabelPV = new Label("PDA: ");
        helloPatientNameLabel = new Label("Hello Patient");

        //Open Patient View Button
        patientViewButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event3) {
                //Once the user presses the tech view button, the tech view will
                //become visible and the others will be invisible
                homePage.setVisible(false);
                patientViewEnterPatientIDVBox.setVisible(true);
                patientViewVBox.setVisible(false); //set true once enter button is pressed
                technicianViewVBox.setVisible(false);
                patientIntakeViewPane.setVisible(false);

            }
        });
        //Enter Actual Patient View Button
        patientViewEnterPatientIDButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event4) {
                //If the ID exists, enter, if not, do not enter
                //if these 5 digits match the first 5 digits of any of the existing files, then go to next screen
                //if not, give error message
                String patientId = patientViewEnterPatientIDTF.getText().trim();
                if(patientViewEnterPatientIDTF.getText().trim().isEmpty() || !patientFilesExist(patientId)){
                    patientViewEnterPatientIDErrorLabel.setText("Error: We cannot find any patients in our system with that ID.");
                }
                else{
                    //do stuff, make the variables in patient view relevant to what is in the text files
                    patientViewEnterPatientIDErrorLabel = new Label(""); //set label back to nothing
                    homePage.setVisible(false);
                    patientViewEnterPatientIDVBox.setVisible(false);
                    patientViewVBox.setVisible(true); //set true once enter button is pressed
                    technicianViewVBox.setVisible(false);
                    patientIntakeViewPane.setVisible(false);

                    if (!patientId.isEmpty()) {

                        // Filenames based on patient ID
                        String patientInfoFilename = patientId + "_PatientInfo.txt";
                        String ctScanFilename = patientId + "CTResults.txt";

                        // Reading and parsing data
                        String[] patientInfo = readAndParse(patientInfoFilename);
                        if (patientInfo != null && patientInfo.length > 0) {
                            String fName = patientInfo[0];
                            helloPatientNameLabel.setText("Hello " + fName);
                        }

                        String[] ctScanResults = readAndParse(ctScanFilename);
                        if (ctScanResults != null && ctScanResults.length > 1) {
                            String totalCACScore = ctScanResults[1];
                            //System.out.println("Total CAC Score: " + totalCACScore);
                            patientViewTotalCACScore.setText(totalCACScore);

                            // Printing other CT scan details
                            patientViewLMScore.setText(ctScanResults[2]);
                            patientViewLADScore.setText(ctScanResults[3]);
                            patientViewLCXScore.setText(ctScanResults[4]);
                            patientViewRCAScore.setText(ctScanResults[5]);
                            patientViewPDAScore.setText(ctScanResults[6]);
                        }
                    }
                }

            }
        });




        //set the scene and add the elements

        root = new Pane();

        //home page
        homePage = new VBox();
        homePage.setAlignment(Pos.CENTER);
        homePage.setSpacing(20);
        homePage.getChildren().add(welcome);
        homePage.getChildren().add(patientIntakeButton);
        homePage.getChildren().add(CTScanTechViewButton);
        homePage.getChildren().add(patientViewButton);

        //patient intake view pane
        patientIntakeViewPane = new VBox();
        patientIntakeViewPane.setSpacing(20);
        patientIntakeViewPane.getChildren().add(patientIntakeFormLabel);
        firstNameHBox = new HBox(firstNameLabel, firstNameTF);
        lastNameHBox = new HBox(lastNameLabel, lastNameTF);
        emailHBox = new HBox(emailLabel, emailTF);
        phoneNumberHBox = new HBox(phoneNumberLabel, phoneNumberTF);
        healthHistoryHBox = new HBox(healthHistoryLabel, healthHistoryTF);
        insuranceIDHBox = new HBox(insuranceIDLabel, insuranceIDTF);
        firstNameHBox.setSpacing(20);
        lastNameHBox.setSpacing(20);
        emailHBox.setSpacing(20);
        phoneNumberHBox.setSpacing(20);
        healthHistoryHBox.setSpacing(20);
        insuranceIDHBox.setSpacing(20);
        patientIntakeViewPane.getChildren().add(firstNameHBox);
        patientIntakeViewPane.getChildren().add(lastNameHBox);
        patientIntakeViewPane.getChildren().add(emailHBox);
        patientIntakeViewPane.getChildren().add(phoneNumberHBox);
        patientIntakeViewPane.getChildren().add(healthHistoryHBox);
        patientIntakeViewPane.getChildren().add(insuranceIDHBox);
        patientIntakeViewPane.getChildren().add(patientIntakeFormSaveButton);

        //technician view
        patientIDTechViewHBox = new HBox(patientIDTechViewLabel, patientIDTF);
        totalCACScoreTechViewHBox = new HBox(totalCACScoreLabel, totalCACScoreTF);
        LMTechViewHBox = new HBox(LMLabel, LMTF);
        LADTechViewHBox = new HBox(LADLabel, LADTF);
        LCXTechViewHBox = new HBox(LCXLabel, LCXTF);
        RCATechViewHBox = new HBox(RCALabel, RCATF);
        PDATechViewHBox = new HBox(PDALabel, PDATF, techViewSaveButton);
        technicianViewVBox = new VBox(patientIDTechViewHBox, totalCACScoreTechViewHBox, vesselLevelCACScoreTechViewLabel, LMTechViewHBox, LADTechViewHBox, LCXTechViewHBox, RCATechViewHBox, PDATechViewHBox, errorLabel);
        technicianViewVBox.setSpacing(20);

        //patient view
        totalCACScorePatientViewHBox = new HBox(totalCACScoreLabelPV, patientViewTotalCACScore);
        LMPatientViewHBox = new HBox(LMLabelPV, patientViewLMScore);
        LADPatientViewHBox = new HBox(LADLabelPV, patientViewLADScore);
        LCXPatientViewHBox = new HBox(LCXLabelPV, patientViewLCXScore);
        RCAPatientViewHBox = new HBox(RCALabelPV, patientViewRCAScore);
        PDAPatientViewHBox = new HBox(PDALabelPV, patientViewPDAScore);
        patientViewVBox = new VBox(helloPatientNameLabel, totalCACScorePatientViewHBox, LMPatientViewHBox, LADPatientViewHBox, LCXPatientViewHBox, RCAPatientViewHBox, PDAPatientViewHBox);
        patientViewVBox.setSpacing(20);
        //patient view enter patient ID
        patientViewEnterPatientIDHBox = new HBox(patientViewEnterPatientIDLabel, patientViewEnterPatientIDTF, patientViewEnterPatientIDButton);
        patientViewEnterPatientIDVBox = new VBox(patientViewEnterPatientIDHBox, patientViewEnterPatientIDErrorLabel);
        patientViewEnterPatientIDVBox.setSpacing(20);
        
        //set the stage
        patientIntakeViewPane.setVisible(false);
        technicianViewVBox.setVisible(false);
        patientViewVBox.setVisible(false);
        patientViewEnterPatientIDVBox.setVisible(false);
        homePage.setVisible(true);
        root.getChildren().add(patientIntakeViewPane);
        root.getChildren().add(patientViewEnterPatientIDVBox);
        root.getChildren().add(technicianViewVBox);
        root.getChildren().add(patientViewVBox);
        root.getChildren().add(homePage);
        homePage.setPrefSize(SCENEWIDTH, SCENEHEIGHT);
        patientIntakeViewPane.setPrefSize(SCENEWIDTH, SCENEHEIGHT);

        primaryStage.setResizable(true);
        primaryStage.setScene(new Scene(root, SCENEWIDTH, SCENEHEIGHT));
        primaryStage.show();
    }

    //Read the files and parse the files
    private static String[] readAndParse(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine(); // Assuming each file contains only one line of interest
            if (line != null) {
                return line.split(",");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print("Error on " + filename);
        }
        return null;
    }

    // Do patient files exist
    private boolean patientFilesExist(String patientId) {
        String patientInfoFilename = patientId + "_PatientInfo.txt";
        String ctScanFilename = patientId + "CTResults.txt";
        File patientInfoFile = new File(patientInfoFilename);
        File ctScanFile = new File(ctScanFilename);
        return patientInfoFile.exists() && ctScanFile.exists();
    }
}