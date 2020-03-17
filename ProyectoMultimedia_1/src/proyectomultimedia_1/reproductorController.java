/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomultimedia_1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Irene Maria Padilla Munoz
 */
public class reproductorController implements Initializable {
    
    private Label label;
    @FXML
    private AnchorPane menuSplitPane;
    @FXML
    private AnchorPane favouritesEntrie;
    @FXML
    private AnchorPane playlistEntrie;
    @FXML
    private AnchorPane settingsEntrie;
    @FXML
    private AnchorPane aboutEntrie;
    @FXML
    private AnchorPane principalSplitPane;
    @FXML
    private AnchorPane playlistPane;
    @FXML
    private AnchorPane playlistSplitPane;
    @FXML
    private AnchorPane audioPane;
    @FXML
    private AnchorPane metadata_;
    @FXML
    private ImageView image;
    @FXML
    private AnchorPane metadata;
    @FXML
    private Label artist;
    @FXML
    private Label name;
    @FXML
    private Slider sliderDuration;
    @FXML
    private ImageView fav;
    @FXML
    private Label timeCounter;
    @FXML
    private Label duration;
    @FXML
    private AnchorPane controls;
    @FXML
    private ImageView play;
    @FXML
    private ImageView next;
    @FXML
    private ImageView previous;
    @FXML
    private ImageView shuffle;
    @FXML
    private ImageView repeat;
    @FXML
    private AnchorPane volume;
    @FXML
    private ImageView sound;
    @FXML
    private Slider sliderVolume;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void favouritesOnClick(MouseEvent event) {
    }

    @FXML
    private void playlistOnClick(MouseEvent event) {
    }

    @FXML
    private void settingsOnClick(MouseEvent event) {
    }

    @FXML
    private void aboutOnClick(MouseEvent event) {
    }

    @FXML
    private void favOnClick(MouseEvent event) {
    }

    @FXML
    private void playOnClick(MouseEvent event) {
    }

    @FXML
    private void nextOnClick(MouseEvent event) {
    }

    @FXML
    private void previousOnClick(MouseEvent event) {
    }

    @FXML
    private void shuffleOnClick(MouseEvent event) {
    }

    @FXML
    private void repeatOnClick(MouseEvent event) {
    }
    
}
