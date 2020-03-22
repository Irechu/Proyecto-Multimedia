/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomultimedia_1;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v1Tag;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.RoundingMode;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.List;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import static proyectomultimedia_1.ProyectoMultimedia_1.preferences;

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
    private TableColumn<Song, String> songColumnPl;
    @FXML
    private TableColumn<Song, String> artistColumnPl;
    @FXML
    private TableColumn<Song, String> albumColumnPl;
    @FXML
    private TableColumn<Song, LocalDate> dateColumnPl;
    @FXML
    private TableColumn<Song, String> durationColumnPl;
    @FXML
    private AnchorPane favouritesPane;
    @FXML
    private AnchorPane libraryPane;
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
    private AnchorPane aboutPane;
    @FXML
    private ImageView change;
    @FXML
    private ImageView musicImage;
    @FXML
    private MediaView musicVideo;
    @FXML
    private ImageView librarySelected;
    @FXML
    private ImageView favouritesSelected;
    @FXML
    private ImageView playlistSelected;
    @FXML
    private ImageView ecualizatorSelected;
    @FXML
    private ImageView settingsSelected;
    @FXML
    private ImageView aboutSelected;
    @FXML
    private ImageView playerSelected;
    @FXML
    private Label libraryEntrieLabel;
    @FXML
    private Label favouritesEntrieLabel;
    @FXML
    private Label playlistEntrieLabel;
    @FXML
    private Label playerEntrieLabel;
    @FXML
    private Label ecualizatorEntrieLabel;
    @FXML
    private Label settingsEntrieLabel;
    @FXML
    private Label aboutEntrieLabel;
    @FXML
    private AnchorPane playlistSplit1AnchorPane;
    @FXML
    private Label playlistSplit1Label;
    @FXML
    private TableColumn<Song, String> durationColumnFav;
    @FXML
    private TableColumn<Song, String> songColumnFav;
    @FXML
    private TableColumn<Song, String> artistColumnFav;
    @FXML
    private TableColumn<Song, String> albumColumnFav;
    @FXML
    private TableColumn<Song, LocalDate> dateColumnFav;
    @FXML
    private Label libraryPaneLabel;
    @FXML
    private Label favouritesPaneLabel;
    @FXML
    private TableColumn<Song, String> songColumnLib;
    @FXML
    private TableColumn<Song, String> artistColumnLib;
    @FXML
    private TableColumn<Song, String> albumColumnLib;
    @FXML
    private TableColumn<Song, LocalDate> dateColumnLib;
    @FXML
    private TableColumn<Song, String> durationColumnLib;
    @FXML
    private TableView<Song> favouritesTable;
    @FXML
    private TableView<Song> libraryTable;

    @FXML
    private void sliderDurationKeyPressed(KeyEvent event) {
        double valor = sliderDuration.getValue();
        System.out.println("valor: " + valor);
        String[] arr = String.valueOf(valor).split("\\.");
        int minutos = Integer.parseInt(arr[0]);
        int segundos = Integer.parseInt(arr[1].substring(0, 1));
        System.out.println("minutos: " + minutos + " segundos: " + segundos);
        tiempo = String.format("%02d", minutos) + ":" + String.format("%02d", segundos);
        timeCounter.setText(tiempo);
    }

    private void sliderDurationOnScroll(ScrollEvent event) {
        double valor = sliderDuration.getValue();
        System.out.println("valor: " + valor);
        String[] arr = String.valueOf(valor).split("\\.");

        int minutos = Integer.parseInt(arr[0]);
        int segundos = Integer.parseInt(arr[1].substring(0, 1));
        System.out.println("minutos: " + minutos + " segundos: " + segundos);
        tiempo = String.format("%02d", minutos) + ":" + String.format("%02d", segundos);
        timeCounter.setText(tiempo);
    }

    @FXML
    private void sliderDurationOnDragged(MouseEvent event) {
        double valor = sliderDuration.getValue();
        System.out.println("valor: " + valor);
        String[] arr = String.valueOf(valor).split("\\.");

        int minutos = Integer.parseInt(arr[0]);
        int segundos = Integer.parseInt(arr[1].substring(0, 1));
        System.out.println("minutos: " + minutos + " segundos: " + segundos);
        tiempo = String.format("%02d", minutos) + ":" + String.format("%02d", segundos);
        timeCounter.setText(tiempo);
    }

    //Variables
    public static final int PLAYLIST = 0;
    public static final int LIBRARY = 1;
    public static final int FAVOURITES = 2;
    public static final int PLAYER = 3;
    public static final int ECUALIZATOR = 4;
    public static final int ABOUT = 5;
    public static final int SETTINGS = 6;
    public static final int LIBRARY_TABLE = 0;
    public static final int FAVOURITES_TABLE = 1;
    public static final int PLAYLISTS_TABLE = 2;

    boolean daltonism;
    boolean persistentDaltonims;
    boolean video;
    boolean shuffleActive;
    boolean repeatActive;
    boolean playActive;
    boolean favActive;
    int tab;
    String tiempo;
    DecimalFormat df = new DecimalFormat("##.##");

    //IMAGENES//
    final private Image favRedImg = new Image(getClass().getResourceAsStream("/assets/imagenes/favRed.png"));
    final private Image favImg = new Image(getClass().getResourceAsStream("/assets/imagenes/fav.png"));
    final private Image favDaltImg = new Image(getClass().getResourceAsStream("/assets/imagenes/favDalt.png"));
    final private Image changeMusicImg = new Image(getClass().getResourceAsStream("/assets/imagenes/music.png"));
    final private Image changeVideoImg = new Image(getClass().getResourceAsStream("/assets/imagenes/video.png"));
    final private Image muteImg = new Image(getClass().getResourceAsStream("/assets/imagenes/mute.png"));
    final private Image soundImg = new Image(getClass().getResourceAsStream("/assets/imagenes/sound.png"));
    final private Image shuffleSelectedImg = new Image(getClass().getResourceAsStream("/assets/imagenes/shuffleSelected.png"));
    final private Image shuffleSelectedDaltImg = new Image(getClass().getResourceAsStream("/assets/imagenes/shuffleSelectedDalt.png"));
    final private Image shuffleNotSelectedImg = new Image(getClass().getResourceAsStream("/assets/imagenes/shuffle.png"));
    final private Image repeatSelectedImg = new Image(getClass().getResourceAsStream("/assets/imagenes/repeatSelected.png"));
    final private Image repeatSelectedDaltImg = new Image(getClass().getResourceAsStream("/assets/imagenes/repeatSelectedDalt.png"));
    final private Image repeatNotSelectedImg = new Image(getClass().getResourceAsStream("/assets/imagenes/repeat.png"));
    final private Image pauseImg = new Image(getClass().getResourceAsStream("/assets/imagenes/pause.png"));
    final private Image playImg = new Image(getClass().getResourceAsStream("/assets/imagenes/play.png"));

    public class Song {

        private String songName;
        private String artist;
        private String album;
        private LocalDate date;
        private String duration;

        public Song(String songName, String artist, String album, LocalDate date, String duration) {
            this.songName = songName;
            this.artist = artist;
            this.album = album;
            this.date = date;
            this.duration = duration;
        }

        public Song() {
            this.songName = "";
            this.artist = "";
            this.album = "";
            this.date = LocalDate.now();
            this.duration = "0:0";
        }

        public String getSongName() {
            return songName;
        }

        public void setSongName(String songName) {
            this.songName = songName;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public String getAlbum() {
            return album;
        }

        public void setAlbum(String album) {
            this.album = album;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Inicializamos las variables
        persistentDaltonims = preferences.getBoolean("daltonism", false);
        repeatActive = preferences.getBoolean("repeatActive", false);
        shuffleActive = preferences.getBoolean("shuffleActive", false);
        daltonismFunc(persistentDaltonims);
        video = false;
        
        ObservableList<String> data = FXCollections.observableArrayList("cancion1", "cancion2", "...");
        playlistList.setItems(data);
        playlistSelected.setVisible(false);

        //Quitamos todos los seleccionados
        librarySelected.setVisible(false);
        playerSelected.setVisible(false);
        favouritesSelected.setVisible(false);
        ecualizatorSelected.setVisible(false);
        aboutSelected.setVisible(false);
        settingsSelected.setVisible(false);

        tab = preferences.getInt("tab", PLAYER);
        activaSeleccion();

        initializeTables();

        if (!preferences.get("library", "").isEmpty()) {
            path.setText(preferences.get("library", ""));
            fillLibrary();
        }

        System.out.println("                                            INICIALIZA");

        sliderVolume.setValue(50);
        sliderDuration.setValue(0);
    }

    @FXML
    private void favOnClick(MouseEvent event) {
        favActive = !favActive;
        if (favActive) {
            if (daltonism) {
                fav.setImage(favDaltImg);
            } else {
                fav.setImage(favRedImg);
            }
        } else {
            fav.setImage(favImg);
        }

    }

    @FXML
    private void playOnClick(MouseEvent event) {
        System.out.println("Has pinchado en: PLAYSE");
        playActive = !playActive;
        if (playActive) {
            play.setImage(pauseImg);
        } else {
            play.setImage(playImg);
        }
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
        shuffleActive = !shuffleActive;
        preferences.putBoolean("shuffleActive", shuffleActive);
        if (shuffleActive) {
            if (daltonism) {
                shuffle.setImage(shuffleSelectedDaltImg);
            } else {
                shuffle.setImage(shuffleSelectedImg);
            }
        } else {
            shuffle.setImage(shuffleNotSelectedImg);
        }
    }

    @FXML
    private void repeatOnClick(MouseEvent event) {
        System.out.println("Has pinchado en: REPETIR");
        repeatActive = !repeatActive;
        preferences.putBoolean("repeatActive", repeatActive);
        if (repeatActive) {
            if (daltonism) {
                repeat.setImage(repeatSelectedDaltImg);
            } else {
                repeat.setImage(repeatSelectedImg);
            }
        } else {
            repeat.setImage(repeatNotSelectedImg);
        }

    }
    
    private void shuffleRepeatActive() {
        if (shuffleActive) {
            if (daltonism) {
                shuffle.setImage(shuffleSelectedDaltImg);
            } else {
                shuffle.setImage(shuffleSelectedImg);
            }
        } else {
            shuffle.setImage(shuffleNotSelectedImg);
        }

        if (repeatActive) {
            if (daltonism) {
                repeat.setImage(repeatSelectedDaltImg);
            } else {
                repeat.setImage(repeatSelectedImg);
            }
        } else {
            repeat.setImage(repeatNotSelectedImg);
        }

        if (favActive) {
            if (daltonism) {
                fav.setImage(favDaltImg);
            } else {
                fav.setImage(favRedImg);
            }
        } else {
            fav.setImage(favImg);
        }
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

    private void escribeCambioIdioma(int idIdioma) {
        preferences.putInt("idIdioma", idIdioma);
    }

    @FXML
    private void daltonismRadioBtnOnClick(MouseEvent event) {
        daltonism = daltonicRadioBtn.isSelected();
        if (daltonism) {
            preferences.putBoolean("daltonism", true);
            menuSplitPane.setStyle("-fx-background-color:#ff9500");
            name.setTextFill(Color.web("#ff9500"));

            libraryEntrieLabel.setStyle("-fx-text-fill:#000000");
            settingsEntrieLabel.setStyle("-fx-text-fill:#000000");
            aboutEntrieLabel.setStyle("-fx-text-fill:#000000");
            favouritesEntrieLabel.setStyle("-fx-text-fill:#000000");
            playlistEntrieLabel.setStyle("-fx-text-fill:#000000");
            playerEntrieLabel.setStyle("-fx-text-fill:#000000");
            ecualizatorEntrieLabel.setStyle("-fx-text-fill:#000000");
            playlistSplit1Label.setStyle("-fx-text-fill:#000000");
            playlistSplit1AnchorPane.setStyle("-fx-background-color:#ff9500");
            libraryPaneLabel.setStyle("-fx-text-fill:#ff9500");
            favouritesPaneLabel.setStyle("-fx-text-fill:#ff9500");
        } else {
            preferences.putBoolean("daltonism", false);
            menuSplitPane.setStyle("-fx-background-color:#4a0707");
            name.setTextFill(Color.web("#4a0707"));

            libraryEntrieLabel.setStyle("-fx-text-fill:#ababab");
            settingsEntrieLabel.setStyle("-fx-text-fill:#ababab");
            aboutEntrieLabel.setStyle("-fx-text-fill:#ababab");
            favouritesEntrieLabel.setStyle("-fx-text-fill:#ababab");
            playlistEntrieLabel.setStyle("-fx-text-fill:#ababab");
            playerEntrieLabel.setStyle("-fx-text-fill:#ababab");
            ecualizatorEntrieLabel.setStyle("-fx-text-fill:#ababab");
            playlistSplit1Label.setStyle("-fx-text-fill:#ababab");
            playlistSplit1AnchorPane.setStyle("-fx-background-color:#4a0707");
            libraryPaneLabel.setStyle("-fx-text-fill:#4a0707");
            favouritesPaneLabel.setStyle("-fx-text-fill:#4a0707");
        }
        shuffleRepeatActive();
    }

    private void daltonismFunc(boolean persistentDaltonims) {
        daltonicRadioBtn.setSelected(persistentDaltonims); //Cambia el boton de radio
        daltonism = persistentDaltonims;
        if (persistentDaltonims) {
            preferences.putBoolean("daltonism", true);
            menuSplitPane.setStyle("-fx-background-color:#ff9500");
            name.setTextFill(Color.web("#ff9500"));

            libraryEntrieLabel.setStyle("-fx-text-fill:#000000");
            settingsEntrieLabel.setStyle("-fx-text-fill:#000000");
            aboutEntrieLabel.setStyle("-fx-text-fill:#000000");
            favouritesEntrieLabel.setStyle("-fx-text-fill:#000000");
            playlistEntrieLabel.setStyle("-fx-text-fill:#000000");
            playerEntrieLabel.setStyle("-fx-text-fill:#000000");
            ecualizatorEntrieLabel.setStyle("-fx-text-fill:#000000");
            playlistSplit1Label.setStyle("-fx-text-fill:#000000");
            playlistSplit1AnchorPane.setStyle("-fx-background-color:#ff9500");
            libraryPaneLabel.setStyle("-fx-text-fill:#ff9500");
            favouritesPaneLabel.setStyle("-fx-text-fill:#ff9500");
        } else {
            preferences.putBoolean("daltonism", false);
            menuSplitPane.setStyle("-fx-background-color:#4a0707");
            name.setTextFill(Color.web("#4a0707"));

            libraryEntrieLabel.setStyle("-fx-text-fill:#ababab");
            settingsEntrieLabel.setStyle("-fx-text-fill:#ababab");
            aboutEntrieLabel.setStyle("-fx-text-fill:#ababab");
            favouritesEntrieLabel.setStyle("-fx-text-fill:#ababab");
            playlistEntrieLabel.setStyle("-fx-text-fill:#ababab");
            playerEntrieLabel.setStyle("-fx-text-fill:#ababab");
            ecualizatorEntrieLabel.setStyle("-fx-text-fill:#ababab");
            playlistSplit1Label.setStyle("-fx-text-fill:#ababab");
            playlistSplit1AnchorPane.setStyle("-fx-background-color:#4a0707");
            libraryPaneLabel.setStyle("-fx-text-fill:#4a0707");
            favouritesPaneLabel.setStyle("-fx-text-fill:#4a0707");
        }
        shuffleRepeatActive();
    }

    @FXML
    private void pathBtnOnClick(MouseEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Seleccione Carpeta Biblioteca");//TODO INTERNACIONALIZAR

        // Obtener la carpeta de la biblioteca
        File defaultDirectory = new File("./src/assets/audio");
        directoryChooser.setInitialDirectory(defaultDirectory);
        File selectedDirectory = directoryChooser.showDialog(null);

        // Mostar la imagen
        if (selectedDirectory.isDirectory()) {
            path.setText(selectedDirectory.getAbsolutePath());
            preferences.put("library", selectedDirectory.getAbsolutePath());
            fillLibrary();
        } else {
            System.out.println("Seleccione una carpeta, por favor");
        }
    }

    @FXML
    private void favouritesOnClick(MouseEvent event) {
        System.out.println("Has pinchado en: FAVORITOS");
        favouritesPane.toFront();
        cambiarSeleccion();
        tab = FAVOURITES;
        guardarSeleccion(tab);
        favouritesSelected.setVisible(true);
    }

    @FXML
    private void playlistOnClick(MouseEvent event) {
        System.out.println("Has pinchado en: PLAYLIST");
        playlistPane.toFront();
        cambiarSeleccion();
        tab = PLAYLIST;
        guardarSeleccion(tab);
        playlistSelected.setVisible(true);

    }

    @FXML
    private void settingsOnClick(MouseEvent event) {
        System.out.println("Has pinchado en: OPCIONES");
        settingsPane.toFront();
        cambiarSeleccion();
        tab = SETTINGS;
        guardarSeleccion(tab);
        settingsSelected.setVisible(true);

    }

    @FXML
    private void aboutOnClick(MouseEvent event) {
        System.out.println("Has pinchado en:INFO");
        aboutPane.toFront();
        cambiarSeleccion();
        tab = ABOUT;
        guardarSeleccion(tab);
        aboutSelected.setVisible(true);

    }

    @FXML
    private void libraryOnClick(MouseEvent event) {
        System.out.println("Has pinchado en: BIBLIOTECA");
        libraryPane.toFront();
        cambiarSeleccion();
        tab = LIBRARY;
        guardarSeleccion(tab);
        librarySelected.setVisible(true);

    }

    @FXML
    private void playerOnClick(MouseEvent event) {
        System.out.println("Has pinchado en: REPRODUCTOR");
        audioPane.toFront();
        cambiarSeleccion();
        tab = PLAYER;
        guardarSeleccion(tab);
        playerSelected.setVisible(true);

    }

    @FXML
    private void ecualizatorOnClick(MouseEvent event) {
        System.out.println("Has pinchado en: ECUALIZADOR");
        ecualizatorPane.toFront();
        cambiarSeleccion();
        tab = ECUALIZATOR;
        guardarSeleccion(tab);
        ecualizatorSelected.setVisible(true);
    }

    @FXML
    private void changeOnClick(MouseEvent event) {
        if (!video) {
            change.setImage(changeMusicImg);
            video = true;
            musicVideo.toFront();
            musicVideo.setVisible(true);
            musicImage.setVisible(false);
        } else {
            change.setImage(changeVideoImg);
            video = false;
            musicImage.toFront();
            musicVideo.setVisible(false);
            musicImage.setVisible(true);
        }
    }
    
    @FXML
    private void volumeSliderMouseReleased(MouseEvent event) {
        //sliderVolume.getValue();
        System.out.println("volumen ajustado a: " + sliderVolume.getValue());
        if (sliderVolume.getValue() == 0) {
            sound.setImage(muteImg);
        } else {
            sound.setImage(soundImg);
        }
    }
    
    private void cambiarSeleccion() {
        switch (tab) {
            case PLAYLIST:
                playlistSelected.setVisible(false);
                break;
            case LIBRARY:
                librarySelected.setVisible(false);
                break;
            case PLAYER:
                playerSelected.setVisible(false);
                break;
            case FAVOURITES:
                favouritesSelected.setVisible(false);
                break;
            case ECUALIZATOR:
                ecualizatorSelected.setVisible(false);
                break;
            case ABOUT:
                aboutSelected.setVisible(false);
                break;
            case SETTINGS:
                settingsSelected.setVisible(false);
                break;

        }
    }

    private void activaSeleccion() {
        switch (tab) {
            case PLAYLIST:
                playlistPane.toFront();
                playlistSelected.setVisible(true);
                break;
            case LIBRARY:
                libraryPane.toFront();
                librarySelected.setVisible(true);
                break;
            case PLAYER:
                audioPane.toFront();
                playerSelected.setVisible(true);
                break;
            case FAVOURITES:
                favouritesPane.toFront();
                favouritesSelected.setVisible(true);
                break;
            case ECUALIZATOR:
                ecualizatorPane.toFront();
                ecualizatorSelected.setVisible(true);
                break;
            case ABOUT:
                aboutPane.toFront();
                aboutSelected.setVisible(true);
                break;
            case SETTINGS:
                settingsPane.toFront();
                settingsSelected.setVisible(true);
                break;

        }
    }

    private void guardarSeleccion(int tab) {
        preferences.putInt("tab", tab);
    }

    private void initializeTables() {
        songColumnLib.setCellValueFactory(new PropertyValueFactory<>("songName"));
        artistColumnLib.setCellValueFactory(new PropertyValueFactory<>("artist"));
        albumColumnLib.setCellValueFactory(new PropertyValueFactory<>("album"));
        dateColumnLib.setCellValueFactory(new PropertyValueFactory<>("date"));
        durationColumnLib.setCellValueFactory(new PropertyValueFactory<>("duration"));

        songColumnFav.setCellValueFactory(new PropertyValueFactory<>("songName"));
        artistColumnFav.setCellValueFactory(new PropertyValueFactory<>("artist"));
        albumColumnFav.setCellValueFactory(new PropertyValueFactory<>("album"));
        dateColumnFav.setCellValueFactory(new PropertyValueFactory<>("date"));
        durationColumnFav.setCellValueFactory(new PropertyValueFactory<>("duration"));

        songColumnPl.setCellValueFactory(new PropertyValueFactory<>("songName"));
        artistColumnPl.setCellValueFactory(new PropertyValueFactory<>("artist"));
        albumColumnPl.setCellValueFactory(new PropertyValueFactory<>("album"));
        dateColumnPl.setCellValueFactory(new PropertyValueFactory<>("date"));
        durationColumnPl.setCellValueFactory(new PropertyValueFactory<>("duration"));
    }

    private void fillLibrary() {
        //TODO codigo para saber directorios
        /*String[] listado = file.list();
                if (listado == null || listado.length == 0) {
                    System.out.println("No hay elementos dentro de la carpeta actual");
                    return;
                } else {
                    for (int i = 0; i < listado.length; i++) {
                        System.out.println(listado[i]);
                    }
                }*/
        libraryTable.getItems().clear(); //Vaciamos por haber cambiado de directorio
        File dir = new File(path.getText()); //Conseguimos la carpeta que quiere usar el usuario como biblioteca
        File[] listado = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) { //Filtramos a las extensiones y obtenemos la lista
                //TODO ampliar quizas
                return (name.toLowerCase().endsWith(".mp3") || name.toLowerCase().endsWith(".avi"));
            }
        });
        if (listado == null || listado.length == 0) {
            System.out.println("No hay elementos dentro de la carpeta actual");
            return;
        } else {
            try {
                File file = new File("./src/assets/library.txt");//Guardamos las rutas para poder reproducirlas luego
                FileWriter fstream = new FileWriter(file, false);
                BufferedWriter out = new BufferedWriter(fstream);
                for (File mp3 : listado) {
                    out.write(mp3.getAbsolutePath()); //Vamos escribiendo las rutas
                    out.write("\n");
                    Mp3File mp3file = new Mp3File(mp3.getAbsoluteFile());
                    //Atributos de la cancion por defecto, por si no hay
                    String title = "---";
                    String artist = "---";
                    String album = "---";
                    //La fecha de creacion la vamos a tener siempre
                    LocalDate date = creationDate(mp3.getAbsoluteFile());
                    //Duracion igual, simpre la vamos a tener
                    String time = durationFormatted(mp3file.getLengthInMilliseconds());
                    if (mp3file.hasId3v2Tag()) {
                        ID3v2 id3v2Tag = mp3file.getId3v2Tag();
                        title = id3v2Tag.getTitle();
                        artist = id3v2Tag.getArtist();
                        album = id3v2Tag.getAlbum();
                    }
                    Song s = new Song(title, artist, album, date, time);
                    addEntrie(LIBRARY_TABLE, s);
                }
                out.close();
            } catch (IOException | UnsupportedTagException | InvalidDataException ex) {
                System.out.println("Error al abrir el fichero de la biblioteca");
                ex.printStackTrace();
            }

        }
    }

    private LocalDate creationDate(File file) {
        BasicFileAttributes attrs;
        LocalDate date = null;
        try {
            attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            FileTime time = attrs.creationTime();

            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            String formatted = simpleDateFormat.format(new Date(time.toMillis()));
            String split[] = formatted.split("-");
            date = LocalDate.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return date;
    }

    private String durationFormatted(long durationMs) {
        long minutes = (durationMs / 1000) / 60;
        long seconds = (durationMs / 1000) % 60;
        String time = String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
        
        return time;
    }

    private void addEntrie(int table, Song s) {
        switch (table) {
            case LIBRARY_TABLE:
                libraryTable.getItems().add(s);
                break;
            case FAVOURITES_TABLE:
                favouritesTable.getItems().add(s);
                break;
            case PLAYLISTS_TABLE:
                break;
        }
    }

}
