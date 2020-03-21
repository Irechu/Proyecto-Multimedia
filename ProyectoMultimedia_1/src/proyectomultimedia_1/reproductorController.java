/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomultimedia_1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
import javafx.scene.layout.Priority;
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
    private AnchorPane audioPane;
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
    String color ="#4a0707";
    
    
    //IMAGENES//
    /*Corazones*/
    final private Image favRedImage = new Image(getClass().getResourceAsStream("/assets/imagenes/favRed.png"));
    final private Image favImage = new Image(getClass().getResourceAsStream("/assets/imagenes/fav.png"));
    @FXML
    private VBox menuVBox;
    @FXML
    private VBox principalVBox;
    @FXML
    private AnchorPane libraryEntrie;
    @FXML
    private AnchorPane main;
    @FXML
    private AnchorPane playerEntrie;
    @FXML
    private AnchorPane ecualizatorEntrie;
    @FXML
    private AnchorPane ecualizatorPane;
    @FXML
    private ImageView espanolBtn1;
    @FXML
    private ImageView inglesBtn1;
    @FXML
    private TextField path1;
    @FXML
    private Button pathBtn1;
    @FXML
    private RadioButton daltonicRadioBtn1;
    @FXML
    private AnchorPane aboutPane;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Inicializamos las variables
        daltonism = false;
        ObservableList<String> data = FXCollections.observableArrayList("cancion1", "cancion2", "...");
        playlistList.setItems(data);
    }

    @FXML
    private void favouritesOnClick(MouseEvent event) {
        System.out.println("Has pinchado en: FAVORITOS");
        favouritesPane.toFront();
    }

    @FXML
    private void playlistOnClick(MouseEvent event) {
        System.out.println("Has pinchado en: PLAYLIST");
        playlistPane.toFront();
    }

    @FXML
    private void settingsOnClick(MouseEvent event) {
        System.out.println("Has pinchado en: OPCIONES");
        settingsPane.toFront();
    }

    @FXML
    private void aboutOnClick(MouseEvent event) {
        System.out.println("Has pinchado en: INFO ---- TODAVIA NO HAY NA");
    }

    @FXML
    private void favOnClick(MouseEvent event) {
        fav.setImage(favRedImage);
        //TODO segun si la canción está marcada como favorita o no, pintamos el fav
    }

    @FXML
    private void playOnClick(MouseEvent event) {
        System.out.println("Has pinchado en: PLAYSE");
    }

    @FXML
    private void nextOnClick(MouseEvent event) {
        System.out.println("Has pinchado en: SIGUIENTE CANCION");
    }

    @FXML
    private void previousOnClick(MouseEvent event) {
        System.out.println("Has pinchado en: CANCION ANTERIOR");
    }

    @FXML
    private void shuffleOnClick(MouseEvent event) {
        System.out.println("Has pinchado en: MODO ALEATORIO");
    }

    @FXML
    private void repeatOnClick(MouseEvent event) {
        System.out.println("Has pinchado en: REPETIR");
    }

    @FXML
    private void espOnClick(MouseEvent event) {
        System.out.println("Has pinchado en: ESPAÑITA");
        Parent root = null;
        main.getChildren().remove(0);
        int es_EN = 0;
        
        try {
            Locale.setDefault(new Locale("es_es"));

            ResourceBundle resourceBundle = ResourceBundle.getBundle("languages.text_es");
            root = FXMLLoader.load(getClass().getResource("reproductor.fxml"), resourceBundle);

            VBox.setVgrow(root, Priority.ALWAYS);
        } catch (IOException ex) {
            System.out.println("Recurso no encontrado");
        }

        escribeCambioIdioma(es_EN);

        main.getChildren().add(root);
    }

    @FXML
    private void enOnClick(MouseEvent event) {
        System.out.println("Has pinchado en: INGLESE");
        Parent root = null;
        main.getChildren().remove(0);
        int es_EN = 1;

        try {
            Locale.setDefault(Locale.ENGLISH);

            ResourceBundle resourceBundle = ResourceBundle.getBundle("languages.text_en");
            root = FXMLLoader.load(getClass().getResource("reproductor.fxml"), resourceBundle);

            VBox.setVgrow(root, Priority.ALWAYS);
        } catch (IOException ex) {
            System.out.println("Recurso no encontrado");
        }

        escribeCambioIdioma(es_EN);

        main.getChildren().add(root);
    }
    
    public static int leeIdioma() {
        int es_EN;
        try {
            //Lectura del idioma cargado en la configuracion
            String idioma;
            BufferedReader br;
            br = new BufferedReader(new FileReader("config.txt"));
            idioma = br.readLine();
            es_EN = Integer.parseInt(idioma.split("=")[1]);
        } catch (FileNotFoundException ex) {
            System.out.println("ALGO FUE MAL EN LA LECTURA DEL IDIOMA. SE PONDRA EN ESPAÑOL POR DEFECTO");
            es_EN = 0;
        } catch (IOException ex) {
            System.out.println("ALGO FUE MAL EN LA LECTURA DEL IDIOMA. SE PONDRA EN ESPAÑOL POR DEFECTO");
            es_EN = 0;
        }

        return es_EN;
    }

    private void escribeCambioIdioma(int idIdioma) {
        try {
            RandomAccessFile archivo = new RandomAccessFile("config.txt", "rw");
            archivo.seek(0);
            archivo.writeBytes("idioma=" + idIdioma);
            archivo.close();
        } catch (FileNotFoundException ex) {
            System.out.println("ALGO FUE MAL AL GUARDAR LAS PREFERENCIAS DEL IDIOMA. SE TRATARA DE GUARDAR EL IDIOMA POR DEFECTO ESPAÑOL");
            try {
                RandomAccessFile archivo = new RandomAccessFile("config.txt", "rw");
                archivo.seek(0);
                archivo.writeBytes("idioma=" + idIdioma);
                archivo.close();
            } catch (Exception e) {
                System.out.println("FUE IMPOSIBLE GUARDAR EL IDIOMA. SE DESCONOCE EL FALLO");
                e.printStackTrace();
            }
        } catch (IOException ex) {
            System.out.println("ALGO FUE MAL AL GUARDAR LAS PREFERENCIAS DEL IDIOMA. SE TRATARA DE GUARDAR EL IDIOMA POR DEFECTO ESPAÑOL");
            try {
                RandomAccessFile archivo = new RandomAccessFile("config.txt", "rw");
                archivo.seek(0);
                archivo.writeBytes("idioma=" + idIdioma);
                archivo.close();
            } catch (Exception e) {
                System.out.println("FUE IMPOSIBLE GUARDAR EL IDIOMA. SE DESCONOCE EL FALLO");
                e.printStackTrace();
            }
        }
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

    @FXML
    private void libraryOnClick(MouseEvent event) {
        System.out.println("Has pinchado en: BIBLIOTECA");
        libraryPane.toFront();
    }

    @FXML
    private void playerOnClick(MouseEvent event) {
        System.out.println("Has pinchado en: REPRODUCTOR");
        audioPane.toFront();
    }

}
