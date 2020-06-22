package hr.java.scenes;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

public class SpremanjeKodaController {
    @FXML ListView<String> HTMLCodeListView;

    public void initialize () {
        List<String> HTMLCodeArrayList = DodavanjeAtributaController.getHTMLCodeArrayList();

        for(String currentHTMLCode : HTMLCodeArrayList)
            HTMLCodeListView.getItems().add(currentHTMLCode);

        System.out.println("Loaded code is: ");
        HTMLCodeArrayList.stream().forEach(System.out::println);
    }

    @FXML
    public void SaveFile(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("*.html", "*.html"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("*.php", "*.php"));
        File newFile = fileChooser.showSaveDialog(null);

        if (newFile != null) {
            List<String> HTMLCodeArrayList = DodavanjeAtributaController.getHTMLCodeArrayList();
            PrintWriter printWriter = new PrintWriter(new FileWriter(newFile));

            for(String currentHTMLCode : HTMLCodeArrayList)
                printWriter.println(currentHTMLCode);

            printWriter.close();

            Alert alert = new Alert(AlertType.INFORMATION);

            alert.setTitle("Success!");
            alert.setHeaderText(null);
            alert.setContentText("Creating code was successful. Check you newly created gallery.");

            alert.showAndWait();
        }
    }

    @FXML
    public void PreviousPage(ActionEvent event) {
        try {
            BorderPane center = FXMLLoader.load(getClass().getResource("DodavanjeAtributa.fxml"));
            Main.setMainPage(center);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}