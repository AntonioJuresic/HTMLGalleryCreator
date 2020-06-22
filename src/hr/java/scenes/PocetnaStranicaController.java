package hr.java.scenes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

public class PocetnaStranicaController {
    @FXML ListView<String> imagesNamesListView;

    static List<String> imagesNamesArrayList = new ArrayList<>();
    List<File> selectedFiles = null;

    boolean didNotClickAddImageButton = true;
    boolean canRightClick = true;

    public void initialize () {
        for(String currentImageString : imagesNamesArrayList)
            imagesNamesListView.getItems().add(currentImageString);

        if(imagesNamesArrayList.isEmpty() == false)
            didNotClickAddImageButton = false;

        imagesNamesListView.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if(e.getButton() == MouseButton.SECONDARY && didNotClickAddImageButton == false && canRightClick == true) {
                canRightClick = false;
                System.out.println("Right click on: " + imagesNamesListView.getSelectionModel().getSelectedItem() + ", at position: " + imagesNamesListView.getSelectionModel().getSelectedIndex());

                ContextMenu contextMenu = new ContextMenu();
                MenuItem delete = new MenuItem("Delete");

                contextMenu.getItems().addAll(delete);
                contextMenu.show(imagesNamesListView, e.getScreenX(), e.getScreenY());

                delete.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        canRightClick = true;
                        System.out.println("Deleted: " + imagesNamesListView.getSelectionModel().getSelectedItem() + ", at position: " + imagesNamesListView.getSelectionModel().getSelectedIndex());

                        imagesNamesArrayList.remove(imagesNamesListView.getSelectionModel().getSelectedIndex());
                        imagesNamesListView.getItems().remove(imagesNamesListView.getSelectionModel().getSelectedItem());
                    }
                });
            }
        });
    }

    @FXML
    public void AddImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All extensions", "*.gif", "*.GIF", "*.jpg", "*.JPG", "*.png", "*.PNG", "*.bmp", "*.BMP", "*.svg", "*.SVG"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("*.gif", "*.gif", "*.GIF"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("*.jpg", "*.jpg", "*.JPG"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("*.png", "*.png", "*.PNG"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("*.bmp", "*.bmp", "*.BMP"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("*.svg", "*.svg", "*.SVG"));

        selectedFiles = fileChooser.showOpenMultipleDialog(null);

        if(selectedFiles != null) {
            for(File currentFile : selectedFiles) {
                imagesNamesListView.getItems().add(currentFile.getName());
                imagesNamesArrayList.add(currentFile.getName());
            }

            System.out.println("Newly loaded images are: ");
            selectedFiles.stream().forEach(System.out::println);
        }

        didNotClickAddImageButton = false;
    }

    @FXML
    public void NextPage(ActionEvent event) {
        if (imagesNamesArrayList.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);

            alert.setTitle("No images chosen!");
            alert.setHeaderText(null);
            alert.setContentText("Chose some images to continue.");

            alert.showAndWait();
        } else if(imagesNamesArrayList.isEmpty() == false) {
            System.out.println("All loaded images are:");

            for(String currentImageString : imagesNamesArrayList)
                System.out.println(currentImageString);

            try {
                BorderPane center = FXMLLoader.load(getClass().getResource("DodavanjeAtributa.fxml"));
                Main.setMainPage(center);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<String> getListOfImages() {
        return imagesNamesArrayList;
    }

}