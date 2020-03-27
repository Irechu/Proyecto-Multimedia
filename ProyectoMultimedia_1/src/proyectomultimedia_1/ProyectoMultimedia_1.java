/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomultimedia_1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static proyectomultimedia_1.reproductorController.PLAYER;

/**
 *
 * @author Irene Maria Padilla Munoz
 */
public class ProyectoMultimedia_1 extends Application {

    private Stage stage;
    public static Preferences preferences = Preferences.userNodeForPackage(ProyectoMultimedia_1.class);

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("                                    START");
        /*
        preferences.putBoolean("daltonism", false);
        preferences.putInt("idIdioma", 0);
        preferences.putInt("tab", PLAYER);
        preferences.put("library", "");
         */
        int idIdioma = preferences.getInt("idIdioma", 0);
        ResourceBundle resources = null;
        if (idIdioma == 0) {
            resources = ResourceBundle.getBundle("languages.text_es");
        } else {
            resources = ResourceBundle.getBundle("languages.text_en");
        }
        Parent root = FXMLLoader.load(getClass().getResource("reproductor.fxml"), resources);

        Scene scene = new Scene(root);
        
        
        stage.setScene(scene);
        stage.setResizable(false);
        this.stage = stage;
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
