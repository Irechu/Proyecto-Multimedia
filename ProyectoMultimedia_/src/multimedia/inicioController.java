/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multimedia;

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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Sergio Bonachera Romero e Irene Maria Padilla Munoz
 */
public class inicioController implements Initializable {
    
    private Media media;
    private MediaPlayer player;
    
    private Label label;
    
    //Botones del reproductor
    @FXML
    ImageView play, previous, next, shuffle, repeat;
    
    private Slider volumeSlider;
    @FXML
    private ImageView image;
    @FXML
    private Slider sliderDuration;
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
    AnchorPane audioPane;
    @FXML
    private AnchorPane metadata;
    @FXML
    private ImageView fav;
    @FXML
    private Label timeCounter;
    @FXML
    private AnchorPane controls;
    @FXML
    private AnchorPane volume;
    @FXML
    private ImageView sound;
    @FXML
    private Slider sliderVolume;
    @FXML
    private AnchorPane playlistPane;
    @FXML
    private AnchorPane playlistSplitPane;
    @FXML
    private Label artist;
    @FXML
    private Label name;
    @FXML
    private Label duration;
    
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
       

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        volumeSlider.valueProperty().addListener((observable, oldVal, newVal) ->
        {
            //TODO copiado del tio, no sé si fufa bien
            //refreshSlider(volumeSlider);
            player.setVolume(newVal.floatValue()/100);
        });    }

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
