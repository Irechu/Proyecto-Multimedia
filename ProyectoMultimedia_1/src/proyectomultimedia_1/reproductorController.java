/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomultimedia_1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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
    private SplitPane playlistSplitPane;
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
    @FXML
    private AnchorPane playlistSplit1;
    @FXML
    ListView<String> playlistList;
    @FXML
    private AnchorPane playlistSplit2;
    @FXML
    private VBox main;
    @FXML
    private TableColumn<?, ?> songColumnPl;
    @FXML
    private TableColumn<?, ?> artistColumnPl;
    @FXML
    private TableColumn<?, ?> albumColumnPl;
    @FXML
    private TableColumn<?, ?> dateColumnPl;
    @FXML
    private TableColumn<?, ?> durationColumnPl;
    @FXML
    private AnchorPane favouritesPane;
    @FXML
    private TableColumn<?, ?> songColumnFav;
    @FXML
    private TableColumn<?, ?> artistColumnFav;
    @FXML
    private TableColumn<?, ?> albumColumnFav;
    @FXML
    private TableColumn<?, ?> dateColumnFav;
    @FXML
    private TableColumn<?, ?> durationColumnFav;
    @FXML
    private AnchorPane libraryPane;
    @FXML
    private TableColumn<?, ?> songColumnFav1;
    @FXML
    private TableColumn<?, ?> artistColumnFav1;
    @FXML
    private TableColumn<?, ?> albumColumnFav1;
    @FXML
    private TableColumn<?, ?> dateColumnFav1;
    @FXML
    private TableColumn<?, ?> durationColumnFav1;
    @FXML
    private AnchorPane settingsPane;
    @FXML
    private ImageView espanolBtn;
    @FXML
    private ImageView inglesBtn;
    @FXML
    private RadioButton daltonicRadioBtn;
    @FXML
    private TextField path;
    @FXML
    private Button pathBtn;

    //Variables
    boolean daltonism;
    
    
    //IMAGENES//
    /*Corazones*/
    final private Image favRedImage = new Image(getClass().getResourceAsStream("/assets/imagenes/favRed.png"));
    final private Image favImage = new Image(getClass().getResourceAsStream("/assets/imagenes/fav.png"));


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Inicializamos las variables
        daltonism = false;
        ObservableList<String> data = FXCollections.observableArrayList("cancion1", "cancion2", "...");
        playlistList.setItems(data);
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
        fav.setImage(favRedImage);
        //TODO segun si la canción está marcada como favorita o no, pintamos el fav
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

    @FXML
    private void espOnClick(MouseEvent event) {
    }

    @FXML
    private void enOnClick(MouseEvent event) {
    }

    @FXML
    private void daltonismRadioBtnOnClick(MouseEvent event) {
        daltonism = daltonicRadioBtn.isSelected();
        if (daltonism) {
            menuSplitPane.setStyle("-fx-background-color:#ff9500");
            name.setTextFill(Color.web("#ff9500"));

        } else {
            menuSplitPane.setStyle("-fx-background-color:#4a0707");
            name.setTextFill(Color.web("#4a0707"));
        }
    }

    @FXML
    private void pathBtnOnClick(MouseEvent event) {
    }

}
