/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomultimedia_1;

import java.util.ArrayList;
import java.util.ResourceBundle;
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

    @Override
    public void start(Stage stage) throws Exception {
        

        ResourceBundle resources = ResourceBundle.getBundle("languages.text_es");
        Parent root = FXMLLoader.load(getClass().getResource("reproductor.fxml"), resources);

        Scene scene = new Scene(root);
        

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
