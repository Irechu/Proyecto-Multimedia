/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomultimedia_1;

import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        preferences.put("library", "C:\\Users\\impm0\\OneDrive\\Escritorio\\Proyecto-Multimedia\\ProyectoMultimedia_1\\src\\assets");
        preferences.put("favouritePath", "./src/assets/favourites.txt");
        preferences.put("preferedVolume", "50");
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
