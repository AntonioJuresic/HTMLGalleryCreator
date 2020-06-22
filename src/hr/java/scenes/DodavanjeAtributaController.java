package hr.java.scenes;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class DodavanjeAtributaController {
    static List<String> HTMLCodeArrayList = new ArrayList<>();

    @FXML private TextField imgDestination;
    @FXML private TextField alt;
    @FXML private TextField width;
    @FXML private TextField height;
    @FXML private TextField id;
    @FXML private TextField imgClass;
    @FXML private TextField longsec;
    @FXML private TextField style;
    //@FXML private TextField usemap;
    //@FXML private CheckBox ismap;

    @FXML private Label labelExampleCode;

    public void initialize () {
        List<String> imagesNamesArrayList = PocetnaStranicaController.getListOfImages();

        System.out.println("Loaded list of image names is:");
        imagesNamesArrayList.stream().forEach(System.out::println);
    }

    @FXML
    public void GenerateExampleCode(ActionEvent event) {
        String exampleCode = "<img src=";

        exampleCode += "\"";
        if(imgDestination.getText().isEmpty() == false)    exampleCode += imgDestination.getText() + "/";
        exampleCode += "IMAGE_NAME.jpg";
        exampleCode += "\"";

        if(alt.getText().isEmpty() == false) 		exampleCode += " alt=" + "\"" + alt.getText() + "\"";
        if(width.getText().isEmpty() == false) 		exampleCode += " width=" + "\"" + width.getText() + "\"";
        if(height.getText().isEmpty() == false)  	exampleCode += " height=" + "\"" + height.getText() + "\"";
        if(id.getText().isEmpty() == false)  	    exampleCode += " id=" + "\"" + id.getText() + "\"";
        if(imgClass.getText().isEmpty() == false)  	exampleCode += " class=" + "\"" + imgClass.getText() + "\"";
        if(longsec.getText().isEmpty() == false)  	exampleCode += " longsec=" + "\"" + longsec.getText() + "\"";
        if(style.getText().isEmpty() == false)  	exampleCode += " style=" + "\"" + style.getText() + "\"";
        //if(usemap.getText().isEmpty() == false)  	exampleCode += " usemap=" + "\"" + usemap.getText() + "\"";
        //if(ismap.isSelected())				    exampleCode += " ismap";

        exampleCode += ">";

        labelExampleCode.setText(exampleCode);

        System.out.println("Example code will look like this:");
        System.out.println(exampleCode);
    }

    @FXML
    public void GenerateCode(ActionEvent event) {
        List<String> imagesNamesArrayList = PocetnaStranicaController.getListOfImages();

        for(String currentImageString : imagesNamesArrayList) {
            String currentCode = "<img src=";

            currentCode += "\"";
            if(imgDestination.getText().isEmpty() == false)    currentCode += imgDestination.getText() + "/";
            currentCode += currentImageString;
            currentCode += "\"";

            if(alt.getText().isEmpty() == false) 		currentCode += " alt=" + "\"" + alt.getText() + "\"";
            if(width.getText().isEmpty() == false) 		currentCode += " width=" + "\"" + width.getText() + "\"";
            if(height.getText().isEmpty() == false)  	currentCode += " height=" + "\"" + height.getText() + "\"";
            if(id.getText().isEmpty() == false)  	    currentCode += " id=" + "\"" + id.getText() + "\"";
            if(imgClass.getText().isEmpty() == false)  	currentCode += " class=" + "\"" + imgClass.getText() + "\"";
            if(longsec.getText().isEmpty() == false)  	currentCode += " longsec=" + "\"" + longsec.getText() + "\"";
            if(style.getText().isEmpty() == false)  	currentCode += " style=" + "\"" + style.getText() + "\"";
            //if(usemap.getText().isEmpty() == false)  	currentCode += " usemap=" + "\"" + usemap.getText() + "\"";
            //if(ismap.isSelected())					currentCode += " ismap";

            currentCode += ">";

            HTMLCodeArrayList.add(currentCode);
        }

        System.out.println("Generated HTML code will look like this:");
        HTMLCodeArrayList.stream().forEach(System.out::println);

        try {
            BorderPane center = FXMLLoader.load(getClass().getResource("SpremanjeKoda.fxml"));
            Main.setMainPage(center);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void PreviousPage(ActionEvent event) {
        try {
            BorderPane center = FXMLLoader.load(getClass().getResource("PocetnaStranica.fxml"));
            Main.setMainPage(center);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getHTMLCodeArrayList() {
        return HTMLCodeArrayList;
    }

}