/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomultimedia_1;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.ID3v24Tag;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.NotSupportedException;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javax.imageio.ImageIO;
import static proyectomultimedia_1.ProyectoMultimedia_1.preferences;

/**
 *
 * @author Irene Maria Padilla Munoz
 */
public class reproductorController implements Initializable {

    /*ELEMENTOS DE LA INTERFAZ*/
    @FXML
    AnchorPane menuSplitPane;
    @FXML
    AnchorPane favouritesEntrie;
    @FXML
    AnchorPane playlistEntrie;
    @FXML
    AnchorPane settingsEntrie;
    @FXML
    AnchorPane aboutEntrie;
    @FXML
    AnchorPane principalSplitPane;
    @FXML
    AnchorPane playlistPane;
    @FXML
    AnchorPane audioPane;
    @FXML
    AnchorPane metadata;
    @FXML
    Label artist;
    @FXML
    Label name;
    @FXML
    Slider sliderDuration;
    @FXML
    ImageView fav;
    @FXML
    Label timeCounter;
    @FXML
    Label duration;
    @FXML
    AnchorPane controls;
    @FXML
    ImageView play;
    @FXML
    ImageView next;
    @FXML
    ImageView previous;
    @FXML
    ImageView shuffle;
    @FXML
    ImageView repeat;
    @FXML
    AnchorPane volume;
    @FXML
    ImageView sound;
    @FXML
    Slider sliderVolume;
    @FXML
    AnchorPane playlistSplit1;
    @FXML
    ListView<String> playlistList;
    @FXML
    AnchorPane playlistSplit2;
    @FXML
    TableColumn<Song, String> songColumnPl;
    @FXML
    TableColumn<Song, String> artistColumnPl;
    @FXML
    TableColumn<Song, String> albumColumnPl;
    @FXML
    TableColumn<Song, LocalDate> dateColumnPl;
    @FXML
    TableColumn<Song, String> durationColumnPl;
    @FXML
    AnchorPane favouritesPane;
    @FXML
    AnchorPane libraryPane;
    @FXML
    AnchorPane settingsPane;
    @FXML
    ImageView espanolBtn;
    @FXML
    ImageView inglesBtn;
    @FXML
    RadioButton daltonicRadioBtn;
    @FXML
    TextField path;
    @FXML
    Button pathBtn;
    @FXML
    VBox menuVBox;
    @FXML
    VBox principalVBox;
    @FXML
    AnchorPane libraryEntrie;
    @FXML
    AnchorPane main;
    @FXML
    AnchorPane playerEntrie;
    @FXML
    AnchorPane ecualizatorEntrie;
    @FXML
    AnchorPane ecualizatorPane;
    @FXML
    AnchorPane aboutPane;
    @FXML
    ImageView change;
    @FXML
    ImageView musicImage;
    @FXML
    MediaView musicVideo;
    @FXML
    ImageView librarySelected;
    @FXML
    ImageView favouritesSelected;
    @FXML
    ImageView playlistSelected;
    @FXML
    ImageView ecualizatorSelected;
    @FXML
    ImageView settingsSelected;
    @FXML
    ImageView aboutSelected;
    @FXML
    ImageView playerSelected;
    @FXML
    Label libraryEntrieLabel;
    @FXML
    Label favouritesEntrieLabel;
    @FXML
    Label playlistEntrieLabel;
    @FXML
    Label playerEntrieLabel;
    @FXML
    Label ecualizatorEntrieLabel;
    @FXML
    Label settingsEntrieLabel;
    @FXML
    Label aboutEntrieLabel;
    @FXML
    AnchorPane playlistSplit1AnchorPane;
    @FXML
    Label playlistSplit1Label;
    @FXML
    TableColumn<Song, String> durationColumnFav;
    @FXML
    TableColumn<Song, String> songColumnFav;
    @FXML
    TableColumn<Song, String> artistColumnFav;
    @FXML
    TableColumn<Song, String> albumColumnFav;
    @FXML
    TableColumn<Song, LocalDate> dateColumnFav;
    @FXML
    Label libraryPaneLabel;
    @FXML
    Label favouritesPaneLabel;
    @FXML
    TableColumn<Song, String> songColumnLib;
    @FXML
    TableColumn<Song, String> artistColumnLib;
    @FXML
    TableColumn<Song, String> albumColumnLib;
    @FXML
    TableColumn<Song, LocalDate> dateColumnLib;
    @FXML
    TableColumn<Song, String> durationColumnLib;
    @FXML
    TableView<Song> favouritesTable;
    @FXML
    TableView<Song> libraryTable;
    AnchorPane ecualizatorEntrie1;
    Label ecualizatorEntrieLabel1;
    ImageView ecualizatorSelected1;
    @FXML
    AnchorPane searchPane;
    @FXML
    private AnchorPane searchEntrie;
    @FXML
    private ImageView searchSelected;
    @FXML
    private TableView<Song> searchTable;
    @FXML
    private TextField searchBar;
    @FXML
    private RadioButton searchSong;
    @FXML
    private RadioButton searchArtist;
    @FXML
    private RadioButton searchAlbum;
    @FXML
    private ChoiceBox<String> searchChoice;
    @FXML
    private ToggleGroup searchGroup;
    @FXML
    private TableColumn<Song, String> songColumnSrch;
    @FXML
    private TableColumn<Song, String> artistColumnSrch;
    @FXML
    private TableColumn<Song, String> albumColumnSrch;
    @FXML
    private TableColumn<Song, LocalDate> dateColumnSrch;
    @FXML
    private TableColumn<Song, String> durationColumnSrch;
    @FXML
    private Label searchLabel;
    @FXML
    private ImageView searchButton;
    @FXML
    private Label searchEntrieLabel;
    @FXML
    private AnchorPane newPlaylistPane;
    @FXML
    private AnchorPane newPlayListWindow;
    @FXML
    private ImageView crossBtn;
    @FXML
    private Label okBtn;
    @FXML
    private TextArea newPlaylistTextField;
    @FXML
    private Label newPlaylistExist;
    @FXML
    private TableView<Song> playlistTable;
    @FXML
    private AnchorPane miniPlayer;
    @FXML
    private AnchorPane addSongToPlaylistPane;
    @FXML
    private AnchorPane selectPlaylistWindow;
    @FXML
    private ImageView crossBtnPl;
    @FXML
    private Button okPlBtn;
    @FXML
    private ListView<String> selectPlaylistList;
    @FXML
    private Button okPlConfirmBtn;
    @FXML
    private Button cancelPlConfirmBtn;
    @FXML
    private AnchorPane okCancelPlPane;
    @FXML
    ImageView play1;
    @FXML
    private AnchorPane tapa;
    @FXML
    Label artist1;
    @FXML
    Label name1;
    @FXML
    ImageView fav1;
    @FXML
    ImageView musicImage1;
    @FXML
    MediaView musicVideo1;
    @FXML
    Slider sliderDuration1;
    @FXML
    Label timeCounter1;
    @FXML
    Label duration1;
    @FXML
    ImageView change1;
    @FXML
    AnchorPane controls1;
    @FXML
    ImageView next1;
    @FXML
    ImageView previous1;
    @FXML
    ImageView shuffle1;
    @FXML
    ImageView repeat1;
    @FXML
    private AnchorPane rectangleContainer;
    Button importBtn;
    Button pause;
    @FXML
    Slider slider1;
    @FXML
    Slider slider2;
    @FXML
    Slider slider3;
    @FXML
    Slider slider4;
    @FXML
    Slider slider5;
    @FXML
    Slider slider6;
    @FXML
    Slider slider7;
    @FXML
    Slider slider8;
    @FXML
    Slider slider9;
    @FXML
    Slider slider10;
    @FXML
    Slider volumeSlider;
    @FXML
    private AreaChart<String, Number> chart;
    @FXML
    ChoiceBox<String> equalizerModeChoiceBox;
    @FXML
    private ImageView sound1;
    @FXML
    private AnchorPane editarMetadatos;
    @FXML
    private TextField editViewTitle;
    @FXML
    private TextField editViewArtist;
    @FXML
    private TextField editViewAlbum;
    @FXML
    private TextField editViewRating;
    @FXML
    private TextField editViewCopyright;
    @FXML
    private Button editViewSaveButon;
    @FXML
    private Button editViewCancelButton;

    //Variables
    //numeración de las pestañas, para saber en todo momento en cual estamos situados
    public static final int PLAYLIST = 0;
    public static final int LIBRARY = 1;
    public static final int FAVOURITES = 2;
    public static final int PLAYER = 3;
    public static final int ECUALIZATOR = 4;
    public static final int ABOUT = 5;
    public static final int SETTINGS = 6;
    public static final int SEARCH = 7;
    public static final int LIBRARY_TABLE = 0;
    public static final int FAVOURITES_TABLE = 1;
    public static final int PLAYLISTS_TABLE = 2;
    public static final String PLAYLIST_PATH = "./src/assets/playlists/";// donde se van a situar las playlists
    XYChart.Data[] series1Data;

    //booleanos para indicar si hay determinadas opciones seleccionadas
    public boolean daltonism;//modo daltónico
    public boolean persistentDaltonims;
    public boolean video;
    public boolean shuffleActive;
    public boolean repeatActive;
    public boolean playActive;
    public boolean favActive;
    public boolean miniplayerActive;
    public int tab;//variable en la que almacenamos la pestaña en la que estamos
    public String tiempo;
    public File loadedSong;//canción a reproducir
    public Song playingSong;//canción reproduciendose
    public Player player;
    public String favouritesPath;

    private File file;//archivo que mostramos en la ventana de editar metadatos

    private String selectedPlaylistToAdd;
    private Song selectedSongToAddToPL;

    //IMAGENES//
    final private Image favRedImg = new Image(getClass().getResourceAsStream("/assets/imagenes/favRed.png"));
    final private Image favImg = new Image(getClass().getResourceAsStream("/assets/imagenes/fav.png"));
    final private Image favDaltImg = new Image(getClass().getResourceAsStream("/assets/imagenes/favDalt.png"));
    final public Image changeMusicImg = new Image(getClass().getResourceAsStream("/assets/imagenes/music.png"));
    final public Image changeVideoImg = new Image(getClass().getResourceAsStream("/assets/imagenes/video.png"));
    final private Image muteImg = new Image(getClass().getResourceAsStream("/assets/imagenes/mute.png"));
    final private Image soundImg = new Image(getClass().getResourceAsStream("/assets/imagenes/sound.png"));
    final private Image shuffleSelectedImg = new Image(getClass().getResourceAsStream("/assets/imagenes/shuffleSelected.png"));
    final private Image shuffleSelectedDaltImg = new Image(getClass().getResourceAsStream("/assets/imagenes/shuffleSelectedDalt.png"));
    final private Image shuffleNotSelectedImg = new Image(getClass().getResourceAsStream("/assets/imagenes/shuffle.png"));
    final private Image repeatSelectedImg = new Image(getClass().getResourceAsStream("/assets/imagenes/repeatSelected.png"));
    final private Image repeatSelectedDaltImg = new Image(getClass().getResourceAsStream("/assets/imagenes/repeatSelectedDalt.png"));
    final private Image repeatNotSelectedImg = new Image(getClass().getResourceAsStream("/assets/imagenes/repeat.png"));
    final public Image pauseImg = new Image(getClass().getResourceAsStream("/assets/imagenes/pause.png"));
    final public Image playImg = new Image(getClass().getResourceAsStream("/assets/imagenes/play.png"));

    /* Método que nos inicializa la aplicación*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Inicializamos las variables
        //obtenemos los valores que se habían guardado de la sesión anterior, las opciones que habíamos dejado marcadas
        persistentDaltonims = preferences.getBoolean("daltonism", false);
        repeatActive = preferences.getBoolean("repeatActive", false);
        shuffleActive = preferences.getBoolean("shuffleActive", false);
        daltonismFunc(persistentDaltonims); //adaptamos el contenido a si está el modo daltónico o no
        video = false;

        //choice box búsquda
        if (preferences.getInt("idIdioma", 0) == 0) {
            ObservableList<String> options = FXCollections.observableArrayList("Biblioteca", "Favoritos");
            searchChoice.setItems(options);
            searchChoice.setValue("Biblioteca");

        } else {
            ObservableList<String> options = FXCollections.observableArrayList("Library", "Favourites");
            searchChoice.setItems(options);
            searchChoice.setValue("Library");

        }

        searchChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                searchBar.clear();
            }
        });

        //choice box ecualizador
        ObservableList<String> options1 = FXCollections.observableArrayList("Flat", "Electronic", "Classic", "Jazz", "Pop", "Voice", "Dance", "Rock");
        equalizerModeChoiceBox.setItems(options1);
        equalizerModeChoiceBox.setValue("Flat");

        //inicializa las playlists
        ObservableList<String> data = FXCollections.observableArrayList("playlist 1");
        playlistList.setItems(data);
        playlistSelected.setVisible(false);

        //Quitamos todos los seleccionados
        librarySelected.setVisible(false);
        playerSelected.setVisible(false);
        favouritesSelected.setVisible(false);
        ecualizatorSelected.setVisible(false);
        aboutSelected.setVisible(false);
        settingsSelected.setVisible(false);
        searchSelected.setVisible(false);

        //desactivamos botones
        play.setDisable(true);
        previous.setDisable(true);
        next.setDisable(true);
        shuffle.setDisable(true);
        repeat.setDisable(true);
        sliderDuration.setDisable(true);
        fav.setDisable(true);
        play1.setDisable(true);
        previous1.setDisable(true);
        next1.setDisable(true);
        shuffle1.setDisable(true);
        repeat1.setDisable(true);
        sliderDuration1.setDisable(true);
        fav1.setDisable(true);

        tab = preferences.getInt("tab", PLAYER);
        activaSeleccion();//activamos la pestaña actual

        initializeTables();//inicializamos las columnas de las tablas

        //obtenemos el path de favourites y de la biblioteca
        if (!preferences.get("favouritePath", "").isEmpty()) {
            favouritesPath = preferences.get("favouritePath", "");
            fillFavourites();
        }
        if (!preferences.get("library", "").isEmpty()) {
            path.setText(preferences.get("library", ""));
            fillLibrary();
        }

        fillPlaylists(); // rellenamos la lista de las playlists

        //añadimos los diferentes listener
        playlistList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String playlist = (String) newValue;
                cargaPlaylist(playlist);
            }
        });
        selectPlaylistList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selectedPlaylistToAdd = (String) newValue;
            }
        });

        System.out.println("                                            INICIALIZA");

        //ponemos los valores del reproductos por defecto
        sliderVolume.setValue(preferences.getFloat("preferedVolume", 50));//el valor del volumen lo extraemos de las preferencias
        sliderDuration.setValue(0);
        sliderDuration1.setValue(0);

        player = new Player(this, true);//creamos el player

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();//inicializamos el chart donde se va a visualizar el audio
        series1Data = new XYChart.Data[128];
        for (int i = 0; i < series1Data.length; i++) {
            series1Data[i] = new XYChart.Data<>(Integer.toString(i + 1), 0);
            series1.getData().add(series1Data[i]);
        }
        chart.getData().add(series1);

    }

    private void cambiarSeleccion() {//cambia de selección nos muestra el indicador de la pestaña en la que estamos (>)
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
            case SEARCH:
                searchSelected.setVisible(false);
                break;
        }
    }

    private void activaSeleccion() { //nos movemos a la pestaña seleccionada, la llevamos al frente en la jerarquía 
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
            case SEARCH:
                searchPane.toFront();
                searchSelected.setVisible(true);
                break;
        }
        guardarSeleccion(tab);//guardamos la selección en las preferencias por si cerramos la aplicación, que esta swe vuelva a abrir donde lo dejamos
    }

    private void guardarSeleccion(int tab) { //guardamos la selección en las preferencias y mostramos el miniplayer o no, según la pestaña en la que nos encontremos
        preferences.putInt("tab", tab);
        if (tab == PLAYER) {
            tapa.toFront();
            miniplayerActive = false;
        } else {
            miniPlayer.toFront();
            miniplayerActive = false;
        }
    }

    private void initializeTables() {
        //inicializa las columnas de las tablas
        songColumnSrch.setCellValueFactory(new PropertyValueFactory<Song, String>("songName"));
        artistColumnSrch.setCellValueFactory(new PropertyValueFactory<Song, String>("artist"));
        albumColumnSrch.setCellValueFactory(new PropertyValueFactory<Song, String>("album"));
        durationColumnSrch.setCellValueFactory(new PropertyValueFactory<Song, String>("duration"));
        dateColumnSrch.setCellValueFactory(new PropertyValueFactory<Song, LocalDate>("date"));

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

    /*############################>>>PLAYER/AUDIO PANE<<<###############################*/
    @FXML
    private void changeOnClick(MouseEvent event) { //botón que nos permite alterar entre video y audio
        if (!video) {
            change.setImage(changeMusicImg);
            change1.setImage(changeMusicImg);

            video = true;
            musicVideo.toFront();
            musicVideo1.toFront();

            musicVideo.setVisible(true);
            musicVideo1.setVisible(true);

            musicImage.setVisible(false);
            musicImage1.setVisible(false);

        } else {
            change.setImage(changeVideoImg);
            change1.setImage(changeVideoImg);

            video = false;
            musicImage.toFront();
            musicImage1.toFront();

            musicVideo.setVisible(false);
            musicVideo1.setVisible(false);

            musicImage.setVisible(true);
            musicImage1.setVisible(true);

        }
    }

    /*Controlamos los eventos del slider del volumen, es un evento con el ratón*/
    @FXML
    private void volumeSliderMouseDragged(MouseEvent event) {
        //sliderVolume.getValue();
        System.out.println("volumen ajustado a: " + sliderVolume.getValue() / 100);
        player.setVolume((float) sliderVolume.getValue());
        preferences.putFloat("preferedVolume", (float) sliderVolume.getValue());
        if (sliderVolume.getValue() == 0) {
            sound.setImage(muteImg);
        } else {
            sound.setImage(soundImg);
        }
    }

    /*Controles cuando interactuamos con los sliders de la duración de la canción*/
    @FXML
    private void sliderDurationKeyPressed(KeyEvent event) {//metodo para el teclado
        double valor = sliderDuration.getValue();//obtenemos el value del slider, el minuto sobre el que hemos soltado
        System.out.println("valor: " + valor);
        valor = valor / 60;//calculamos el tiempo en minutos y segundos, separados por '.'

        int minutos = (int) valor;

        String[] arr = String.valueOf(valor).split("\\.");

        int segundos = Integer.parseInt(arr[1].substring(0, 2));
        segundos = (segundos * 60) / 100;
        System.out.println("minutos: " + minutos + " segundos: " + segundos);
        tiempo = String.format("%02d", minutos) + ":" + String.format("%02d", segundos);//con los datos obtenidos, le damos formato
        timeCounter.setText(tiempo);//ponemos el tiempo restante en el label del tiempo en el reproductor
        timeCounter1.setText(tiempo);//idem en el miniPalyer

        player.setPos(valor * 60);//ponemos la canción en dicho instante
    }

    @FXML
    private void sliderDurationOnDragged(MouseEvent event) {//metodo para el ratón
        double valor = sliderDuration.getValue();
        System.out.println("valor: " + valor);
        valor = valor / 60;

        int minutos = (int) valor;

        String[] arr = String.valueOf(valor).split("\\.");

        int segundos = Integer.parseInt(arr[1].substring(0, 2));
        segundos = (segundos * 60) / 100;
        System.out.println("minutos: " + minutos + " segundos: " + segundos);
        tiempo = String.format("%02d", minutos) + ":" + String.format("%02d", segundos);
        timeCounter.setText(tiempo);
        timeCounter1.setText(tiempo);

        player.setPos(valor * 60);
    }

    @FXML
    private void sliderDurationKeyPressed1(KeyEvent event) { //idem al anterior pero para el miniPlayer
        double valor = sliderDuration1.getValue();
        System.out.println("valor: " + valor);
        valor = valor / 60;
        int minutos = (int) valor;
        String[] arr = String.valueOf(valor).split("\\.");
        int segundos = Integer.parseInt(arr[1].substring(0, 2));
        segundos = (segundos * 60) / 100;
        System.out.println("minutos: " + minutos + " segundos: " + segundos);
        tiempo = String.format("%02d", minutos) + ":" + String.format("%02d", segundos);
        timeCounter.setText(tiempo);
        timeCounter1.setText(tiempo);

        player.setPos(valor * 60);
    }

    @FXML
    private void sliderDurationOnDragged1(MouseEvent event) { //idem al anterior pero para el miniPlayer
        double valor = sliderDuration1.getValue();
        System.out.println("valor: " + valor);
        valor = valor / 60;
        int minutos = (int) valor;
        String[] arr = String.valueOf(valor).split("\\.");
        int segundos = Integer.parseInt(arr[1].substring(0, 2));
        segundos = (segundos * 60) / 100;
        System.out.println("minutos: " + minutos + " segundos: " + segundos);
        tiempo = String.format("%02d", minutos) + ":" + String.format("%02d", segundos);
        timeCounter.setText(tiempo);
        timeCounter1.setText(tiempo);

        player.setPos(valor * 60);
    }

    //los métodos para los diferentes botones de los controles
    @FXML
    private void favOnClick(MouseEvent event) {//añadimos a favoritos si pulsamos sobre el corazón <3
        favActive = !favActive;
        if (favActive) {
            if (daltonism) {
                fav.setImage(favDaltImg);
                fav1.setImage(favDaltImg);

            } else {
                fav.setImage(favRedImg);
                fav1.setImage(favRedImg);

            }
            addFavourite(playingSong); //añadimos si no estaba marcado el corazón
        } else {
            fav.setImage(favImg);
            fav1.setImage(favImg);

            removeFavourite(playingSong); //eliminamos si ya estaba añadido en favoritos

        }

    }

    //gestión favoritos
    private void addFavourite(Song playingSong) {
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            String data = playingSong.file.getAbsolutePath() + "\n";
            File file = new File(preferences.get("favouritePath", ""));
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            bw.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //Cierra instancias de FileWriter y BufferedWriter
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void removeFavourite(Song playingSong) {
        playingSong.fav = false;
        BufferedReader reader = null;
        try {
            File inputFile = new File(preferences.get("favouritePath", ""));
            File tempFile = new File(preferences.get("favouritePath", "").substring(0, preferences.get("favouritePath", "").length() - 4) + "a.txt");//Creamos copia del fichero donde está pero con una A
            reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String lineToRemove = playingSong.file.getAbsolutePath();
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.equals(lineToRemove)) {
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
            inputFile.delete();
            boolean successful = tempFile.renameTo(inputFile);
            System.out.println("Borrado:" + successful);
        } catch (IOException ex) {
            Logger.getLogger(reproductorController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(reproductorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        fillFavourites();
    }

    //gestión favoritos
    @FXML
    private void playOnClick(MouseEvent event) { //boton de play, para o reanuda la reproducción
        System.out.println("Has pinchado en: PLAYSE");
        playActive = !playActive;
        if (playActive) {
            play.setImage(pauseImg);
        } else {
            play.setImage(playImg);
        }
        player.pauseResume();
    }

    @FXML
    private void nextOnClick(MouseEvent event) { //pasamos a la siguiente canción
        System.out.println("Has pinchado en: SIGUIENTE CANCION");
        player.playNext();
    }

    @FXML
    private void previousOnClick(MouseEvent event) { //pasams a la canción anterior
        System.out.println("Has pinchado en: CANCION ANTERIOR");
        player.playPrevious();
    }

    @FXML
    private void shuffleOnClick(MouseEvent event) { //modo aleatorio
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
    private void repeatOnClick(MouseEvent event) { //repetimos la canción
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

        if (playingSong == null) {
            if (favActive) {
                if (daltonism) {
                    fav.setImage(favDaltImg);
                    fav1.setImage(favDaltImg);

                } else {
                    fav.setImage(favRedImg);
                    fav1.setImage(favRedImg);

                }
            } else {
                fav.setImage(favImg);
                fav1.setImage(favImg);

            }
        } else {
            if (playingSong.fav) {
                if (daltonism) {
                    fav.setImage(favDaltImg);
                    fav1.setImage(favDaltImg);

                } else {
                    fav.setImage(favRedImg);
                    fav1.setImage(favRedImg);

                }
            } else {
                fav.setImage(favImg);
                fav1.setImage(favImg);

            }
        }
    }


    /*############################>>>BÚSQUEDA<<<###############################*/
    @FXML
    private void searchOnClick(MouseEvent event) {//cambiamos la selección 
        System.out.println("Has pinchado en: BUSCAR");
        searchPane.toFront();//movemos el pane al frente
        cambiarSeleccion();
        tab = SEARCH;
        guardarSeleccion(tab);//guardamos la tab actual en las preferencias
        searchSelected.setVisible(true);//mostramos la flecha que nos indica en que pestaña estamos (>)
    }

    //Cada vez que escribimos algo en la barra de búsqueda, filtramos la tabla
    @FXML
    private void searchKeyPressed(KeyEvent event) {
        TableView<Song> table = null;
        if (!event.getCode().equals(KeyCode.ENTER)) {
            switch (searchChoice.getValue()) {//seleccionamos la tabla que vamos a filtar según que opción se haya escogido
                case "Library":
                case "Biblioteca":
                    System.out.println("biblioteca");
                    table = libraryTable;
                    break;
                case "Favourites":
                case "Favoritos":
                    System.out.println("fav");
                    table = favouritesTable;
                    break;
                case "Internet":
                    //TODO
                    searchLabel.setText("Buscando en internet...");
                    break;

            }
            FilteredList<Song> filteredData = new FilteredList<Song>(table.getItems(), p -> true);//creamos la lista filtrada
            searchTable.setItems(filteredData);

            searchBar.textProperty().addListener((prop, old, text) -> {//añadimos el listener
                filteredData.setPredicate(song -> {
                    if (text == null || text.isEmpty()) {
                        return true;
                    }
                    String cancion = "";

                    switch (searchGroup.getSelectedToggle().toString().split("'")[1]) {
                        case "Song":
                        case "Canción":
                            cancion = song.getSongName().toLowerCase();
                            break;
                        case "Artist":
                        case "Artista":
                            cancion = song.getArtist().toLowerCase();
                            break;
                        case "Album":
                            cancion = song.getAlbum().toLowerCase();
                            break;
                    }

                    return cancion.contains(text.toLowerCase());
                });
                if (searchTable.getItems().size() == 0) {//so no hay resultados, lo indicamos
                    if (preferences.getInt("idIdioma", 0) == 0) {
                        searchLabel.setText("No hay resulatdos");
                    } else {
                        searchLabel.setText("No results");
                    }
                } else {
                    searchLabel.setText("");
                }
            });
        }
    }

    private void searchRadioOnClick(MouseEvent event) { //Al cambiar el lugar de busqueda, ponemos el campo de texto otra vez en blanco
        searchBar.setText("");
        searchTable.getItems().clear();
    }

    /*########################################### LIBRARY PANE ##########################################*/
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
        fillFavourites();
        File dir = new File(path.getText()); //Conseguimos la carpeta que quiere usar el usuario como biblioteca
        File[] listado = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) { //Filtramos a las extensiones y obtenemos la lista
                return (name.toLowerCase().endsWith(".mp3") || name.toLowerCase().endsWith(".mp4"));
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

                    BufferedReader reader = null;
                    Song s = null;
                    boolean yaExiste = false;
                    try {
                        File inputFile = new File(preferences.get("favouritePath", "")); //Consigo los favoritos para obtener la instancia de la cancion
                        reader = new BufferedReader(new FileReader(inputFile));
                        String favSong;
                        while ((favSong = reader.readLine()) != null && !yaExiste) { //Si no la he encontrado ya y quedan mas lineas por leer
                            if (favSong.equals(mp3.getAbsolutePath())) { //La cancion de la libreria está marcada como favorita
                                int index = 0;
                                ObservableList<Song> list = favouritesTable.getItems();  //Buscamos el indice de la tabla donde está dicha cancion, pueden estar ordenadas de otra manera
                                if (!list.isEmpty()) {
                                    while (!yaExiste) {
                                        if (list.get(index).file.getAbsolutePath().equals(favSong)) {
                                            yaExiste = true;
                                            s = list.get(index); //Conseguimos la cancion en cuestion
                                        }
                                        index++;
                                    }
                                }
                            }
                        }
                        reader.close();
                    } catch (IOException ex) {
                        Logger.getLogger(reproductorController.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            reader.close();
                        } catch (IOException ex) {
                            Logger.getLogger(reproductorController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (!yaExiste) {
                        if (mp3.getName().toLowerCase().endsWith(".mp3")) {
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
                                title = id3v2Tag.getTitle() == null ? mp3.getName() : id3v2Tag.getTitle();
                                artist = id3v2Tag.getArtist() == null ? "---" : id3v2Tag.getArtist();
                                album = id3v2Tag.getAlbum() == null ? "---" : id3v2Tag.getAlbum();
                            }
                            s = new Song(title, artist, album, date, time, mp3, false);
                        } else {
                            LocalDate fecha = creationDate(mp3);
                            Media media = new Media(mp3.toURI().toString());
                            s = new Song(mp3.getName(), "---", "---", fecha, durationFormatted((long) media.getDuration().toMillis()), mp3, false);
                        }
                    }
                    addEntrie(LIBRARY_TABLE, s);
                }
                out.close();
            } catch (IOException | UnsupportedTagException | InvalidDataException ex) {
                System.out.println("Error al abrir el fichero de la biblioteca");
                ex.printStackTrace();
            }

        }
    }

    /*########################################### FAVOURITES PANE ##########################################*/
    private void fillFavourites() {
        FileReader f = null;
        try {
            favouritesTable.getItems().clear(); //Vaciamos por haber cambiado de directorio
            String archivo;
            f = new FileReader(favouritesPath); //Conseguimos el fichero que contiene todas las canciones favoritas
            BufferedReader b = new BufferedReader(f);
            while ((archivo = b.readLine()) != null) {
                File file = new File(archivo);
                if (file.exists()) {
                    Song s;
                    if (archivo.toLowerCase().endsWith(".mp3")) {
                        Mp3File mp3file = new Mp3File(file.getAbsoluteFile());
                        //Atributos de la cancion por defecto, por si no hay
                        String title = "---";
                        String artist = "---";
                        String album = "---";
                        //La fecha de creacion la vamos a tener siempre
                        LocalDate date = creationDate(file.getAbsoluteFile());
                        //Duracion igual, simpre la vamos a tener
                        String time = durationFormatted(mp3file.getLengthInMilliseconds());
                        if (mp3file.hasId3v2Tag()) {
                            ID3v2 id3v2Tag = mp3file.getId3v2Tag();
                            title = id3v2Tag.getTitle() == null ? file.getName() : id3v2Tag.getTitle();
                            artist = id3v2Tag.getArtist() == null ? "---" : id3v2Tag.getArtist();
                            album = id3v2Tag.getAlbum() == null ? "---" : id3v2Tag.getAlbum();
                        }
                        s = new Song(title, artist, album, date, time, file, true);
                    } else {
                        LocalDate fecha = creationDate(file);
                        Media media = new Media(file.toURI().toString());
                        s = new Song(file.getName(), "---", "---", fecha, durationFormatted((long) media.getDuration().toMillis()), file, false);
                    }
                    addEntrie(FAVOURITES_TABLE, s);
                }
            }
            b.close();
        } catch (IOException | UnsupportedTagException | InvalidDataException ex) {
            System.out.println("ERROR AL CARGAR LA LISTA DE FAVORITOS");
            System.out.println(ex);
        } finally {
            try {
                f.close();
            } catch (IOException ex) {
                Logger.getLogger(reproductorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    /*############################>>>PLAYLIST PANE<<<###############################*/
    private void fillPlaylists() { //ponemos las playlist que exiisten en la lista de playlists
        playlistList.getItems().clear(); //Vaciamos
        File dir = new File(PLAYLIST_PATH); //Conseguimos la carpeta que contiene las playlists
        File[] listado = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) { //Filtramos a las extensiones y obtenemos la lista
                return (name.toLowerCase().endsWith(".txt"));
            }
        });
        if (listado == null || listado.length == 0) {
            System.out.println("No hay elementos dentro de la carpeta actual");
            return;
        } else {
            for (File playlist : listado) {
                playlistList.getItems().add(playlist.getName().substring(0, playlist.getName().length() - 4));
                playlistList.getItems().sort(null); //Ordena de manera natural los Strings
            }
        }
    }

    private void cargaPlaylist(String playlist) { //te carga las canciones de la playlist en una tabla situada a la derecha de la lista de playlists
        playlistTable.getItems().clear();
        File pl = new File(PLAYLIST_PATH + playlist + ".txt"); //Conseguimos la playlist en cuestion
        BufferedReader r;
        BufferedReader reader = null;
        try {
            r = new BufferedReader(new FileReader(pl));
            Song s = null;
            String ss;
            try {
                while ((ss = r.readLine()) != null) { //Para cada cancion de la playlist
                    File mp3 = new File(ss);
                    if (mp3.exists()) {
                        boolean yaExiste = false;
                        try {
                            File inputFile = new File(preferences.get("favouritePath", "")); //Consigo los favoritos para obtener la instancia de la cancion
                            reader = new BufferedReader(new FileReader(inputFile));
                            String favSong;
                            while ((favSong = reader.readLine()) != null && !yaExiste) { //Si no la he encontrado ya y quedan mas lineas por leer
                                if (favSong.equals(mp3.getAbsolutePath())) { //La cancion de la playlist está marcada como favorita
                                    int index = 0;
                                    ObservableList<Song> list = favouritesTable.getItems();  //Buscamos el indice de la tabla donde está dicha cancion, pueden estar ordenadas de otra manera
                                    while (!yaExiste) {
                                        if (list.get(index).file.getAbsolutePath().equals(favSong)) {
                                            yaExiste = true;
                                            s = list.get(index); //Conseguimos la cancion en cuestion
                                        }
                                        index++;
                                    }
                                }
                            }
                            reader.close();
                        } catch (IOException ex) {
                            Logger.getLogger(reproductorController.class.getName()).log(Level.SEVERE, null, ex);
                        } finally {
                            try {
                                reader.close();
                            } catch (IOException ex) {
                                Logger.getLogger(reproductorController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        if (!yaExiste) {
                            try {
                                if (mp3.getName().toLowerCase().endsWith(".mp3")) {
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
                                        title = id3v2Tag.getTitle() == null ? mp3.getName() : id3v2Tag.getTitle();
                                        artist = id3v2Tag.getArtist() == null ? "---" : id3v2Tag.getArtist();
                                        album = id3v2Tag.getAlbum() == null ? "---" : id3v2Tag.getAlbum();
                                    }
                                    s = new Song(title, artist, album, date, time, mp3, false);
                                } else {
                                    LocalDate fecha = creationDate(mp3);
                                    Media media = new Media(mp3.toURI().toString());
                                    s = new Song(mp3.getName(), "---", "---", fecha, durationFormatted((long) media.getDuration().toMillis()), mp3, false);
                                }
                            } catch (Exception ex) {
                                Logger.getLogger(reproductorController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                        addEntrie(PLAYLISTS_TABLE, s);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(reproductorController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    r.close();
                } catch (IOException ex) {
                    Logger.getLogger(reproductorController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("No se pudieron crear los buffered readers al cargar la playlist");
        }
    }

    @FXML
    private void okBtnOnClick(MouseEvent event) throws IOException {
        boolean existe = false;
        //Primero comprobamos si ya existe
        File dir = new File(PLAYLIST_PATH);
        File[] listado = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) { //Filtramos a las extensiones y obtenemos la lista
                return (name.toLowerCase().endsWith(".txt"));
            }
        });
        if (newPlaylistTextField.getText().equals("")) {
            //El textfield está vacío
            if (preferences.getInt("idIdioma", 0) == 0) {
                newPlaylistExist.setText("Introduce un nombre!");
            } else {
                newPlaylistExist.setText("Insert a name for the playlist");
            }
        } else { //no está vacío
            for (int i = 0; i < listado.length && existe == false; i++) {
                File file = listado[i];
                if (file.getName().equals(newPlaylistTextField.getText() + ".txt")) {
                    existe = true;
                }
            }
            //si no existe, creamos un nuevo fichero con el nombre de la playlist 
            //donde vamos a almacenar las canciones pertenecientes a esta playlist
            if (!existe) {
                File file = new File(PLAYLIST_PATH + newPlaylistTextField.getText() + ".txt");
                FileWriter fstream = new FileWriter(file, false);
                BufferedWriter out = new BufferedWriter(fstream);
                out.write(""); //Vamos escribiendo las rutas
                playlistPane.toFront();
                fillPlaylists();//recargamos la tabla de las playlists
            } else {//indicamos que ya existe
                if (preferences.getInt("idIdioma", 0) == 0) {
                    newPlaylistExist.setText("Ya existe!");
                } else {
                    newPlaylistExist.setText("It already exists");
                }
            }
        }
    }

    @FXML
    private void newPlaylistOnClick(MouseEvent event) { //cuando pulsamos el botón de nueva playlist, mostramos la vista para añadirla
        newPlaylistExist.setText("");
        newPlaylistTextField.setText("");
        newPlaylistPane.toFront();
        //newPlaylistPane.toFront();
    }

    @FXML
    private void crossOnClick(MouseEvent event) { //cuando pulsamos el icono de la cruz para cerrar la ventana de nueva playlist
        playlistPane.toFront();
    }

    @FXML
    private void crossPLOnClick(MouseEvent event) {//cerramos la ventana de añadir canción a playList
        addSongToPlaylistPane.toBack();
    }

    @FXML
    private void okBtnPLOnClick(ActionEvent event) { //mostramos las playlist existentes, para seleccionar en cual introducir la canción
        String playlistPath = PLAYLIST_PATH + selectedPlaylistToAdd + ".txt";
        boolean presente = songInPlaylist(playlistPath, selectedSongToAddToPL);//comprobamos si está la canción en la playlist
        System.out.println(selectedSongToAddToPL.file.getAbsolutePath() + " -- " + selectedPlaylistToAdd + " --- " + presente);
        if (presente) {
            okCancelPlPane.toFront();//mostramos la ventana si la canción ya se encontraba en la playlist
        } else {//si no está la mete directamente
            FileWriter fstream = null;
            try {
                //modificamos el fichero de dicha playlist para meter la ruta de la canción
                File playlistFile = new File(playlistPath);
                fstream = new FileWriter(playlistFile, true); // Escribe al final
                BufferedWriter out = new BufferedWriter(fstream);
                System.out.println(selectedSongToAddToPL.file.getAbsolutePath());
                out.write("\n" + selectedSongToAddToPL.file.getAbsolutePath()); // Escribimos la cancion al final
                out.close();
                fstream.close();
            } catch (IOException ex) {
                Logger.getLogger(reproductorController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fstream.close();
                } catch (IOException ex) {
                }
            }
            addSongToPlaylistPane.toBack();//al terminar, se oculta la ventana
        }
    }

    private boolean songInPlaylist(String playlistPath, Song song) { //comprobamos si una canción ya está en la playlist
        boolean presente = false;
        File pl = new File(playlistPath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(pl));
            String s;
            while ((s = reader.readLine()) != null && !presente) {
                if (s.equals(song.file.getAbsolutePath())) {
                    presente = true;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(reproductorController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
            }
        }

        return presente;
    }

    @FXML
    private void okPlConfirmBtnOnClick(ActionEvent event) {//indicamos que ya está dentro, queremos meterla
        FileWriter fstream = null;
        try {
            String playlistPath = PLAYLIST_PATH + selectedPlaylistToAdd + ".txt";
            File playlistFile = new File(playlistPath);
            fstream = new FileWriter(playlistFile, true); // Escribe al final
            BufferedWriter out = new BufferedWriter(fstream);

            out.write("\n" + selectedSongToAddToPL.file.getAbsolutePath()); // Escribimos la cancion al final
            out.close();
            fstream.close();
        } catch (IOException ex) {
            Logger.getLogger(reproductorController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fstream.close();
            } catch (IOException ex) {
            }
        }
        addSongToPlaylistPane.toBack();
    }

    @FXML
    private void cancelPlConfirmBtnOnClick(ActionEvent event) {//no queremos meter la canción duplicada
        okCancelPlPane.toBack();
    }

    @FXML
    private void volumeSliderMouseDragged1(MouseEvent event) {
        //sliderVolume.getValue();
        System.out.println("volumen ajustado a: " + volumeSlider.getValue() / 100);
        player.setVolume((float) volumeSlider.getValue());
        preferences.putFloat("preferedVolume", (float) volumeSlider.getValue());
        if (volumeSlider.getValue() == 0) {
            sound1.setImage(muteImg);
        } else {
            sound1.setImage(soundImg);
        }
    }

    //menu contextual en la lista de playliusts
    private void listOnClick(MouseEvent event) {
        ListView<String> listaPlaylist = playlistList;

        if (event.getButton().equals(MouseButton.SECONDARY) && (listaPlaylist.getSelectionModel().getSelectedItem() != null)) {
            System.out.println(listaPlaylist.getSelectionModel().getSelectedItem());
            //Creamos menu contextual del click derecho
            ContextMenu context = new ContextMenu();
            MenuItem play;
            if (preferences.getInt("idIdioma", 0) == 0) {
                play = new MenuItem("Reproducir");
            } else {
                play = new MenuItem("Play");
            }
            play.setOnAction((ActionEvent e) -> {
                System.out.println("PLAY playlist");
                try {
                    cargaPlaylist(listaPlaylist.getSelectionModel().getSelectedItem());
                    if (!playlistTable.getItems().isEmpty()) {
                        playlistTable.getSelectionModel().selectFirst();
                        playSong(playlistTable);
                    }
                } catch (IOException | UnsupportedTagException | InvalidDataException ex) {
                    Logger.getLogger(reproductorController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            MenuItem delete;
            if (preferences.getInt("idIdioma", 0) == 0) {
                delete = new MenuItem("Borrar*");
            } else {
                delete = new MenuItem("Delete*");
            }
            delete.setOnAction((ActionEvent e) -> {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                if (preferences.getInt("idIdioma", 0) == 0) {
                    alert.setTitle("¿Está Seguro?");
                    alert.setHeaderText("El archivo será borrado del disco por completo.");
                    alert.setContentText("¿Está seguro de querer realizar esto?");
                } else {
                    alert.setTitle("You sure?");
                    alert.setHeaderText("The file will be deleted from the disk completely.");
                    alert.setContentText("Are you sure that you want to do this?");
                }

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    File pl = new File(PLAYLIST_PATH + listaPlaylist.getSelectionModel().getSelectedItem() + ".txt");
                    pl.delete();
                    fillPlaylists();
                } else {
                    // El usuario cancela y no se hace nada
                }
            });
            //Añadimos las opciones con un separador en borrar ya que esto borrara el fichero del disco.
            context.getItems().addAll(play, new SeparatorMenuItem(), delete);
            listaPlaylist.setContextMenu(context);
            //Añadimos el gestor de eventos del raton para la seleccion del men
        }
    }

    /*############################>>> EDITAR METADATOS PANE <<<###############################*/
    @FXML
    private void cancelViewOnClick(MouseEvent event) { //ocultamos la ventana de editar los metadatos 
        editarMetadatos.toBack();
    }

    //guardamos los datos que el usuario ha querido modificar de la canción
    @FXML
    private void saveViewOnClick(MouseEvent event) throws IOException, UnsupportedTagException, InvalidDataException, InterruptedException {
        Mp3File mp3file = new Mp3File(file.getAbsoluteFile());
        ID3v2 tag;
        if (mp3file.hasId3v2Tag()) {
            tag = mp3file.getId3v2Tag();
        } else {
            tag = new ID3v24Tag();
            mp3file.setId3v2Tag(tag);
        }
        //si el rate metido no es correcto, surge una alerta del sistema indicandonos el probblema y como ha de actuar el usuario
        if (!editViewRating.getText().isEmpty() && (Integer.parseInt(editViewRating.getText()) < -1 || Integer.parseInt(editViewRating.getText()) > 5)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if (preferences.getInt("idIdioma", 0) == 0) {
                alert.setTitle("Error en el Rating");
                alert.setHeaderText("El rating debe de ser entre 0 y 5. Para ponerlo por defecto introduzca -1 o dejelo en blanco");
            } else {
                alert.setTitle("Error in Rating");
                alert.setHeaderText("Ther rating must be betweeb 0 and 5. To put the default value enter -1 o let it empty");
            }

            alert.showAndWait();
        } else {
            if (editViewRating.getText().isEmpty()) {
                tag.setWmpRating(-1);
            } else {
                tag.setWmpRating(Integer.parseInt(editViewRating.getText()));
            }
            tag.setTitle(editViewTitle.getText());
            tag.setArtist(editViewArtist.getText());
            tag.setAlbum(editViewAlbum.getText());
            tag.setCopyright(editViewCopyright.getText());

            //La biblioteca mp3agic no deja renombrar ficheros por lo que
            String nombre = file.getName();
            File aux = new File(file.getParent(), nombre + "a"); //Lo crearemos con el mismo nombre pero una A al final
            String ruta = aux.getAbsolutePath();
            try {
                mp3file.save(ruta);//Guardamos el que tiene una a al final para que no nos diga que ya existe
                file.delete();//Borramos el que habia
                String cadena = aux.getName();
                cadena = cadena.substring(0, cadena.length() - 1); //Quitamos la a del final
                File aux2 = new File(aux.getParent(), cadena);
                aux.renameTo(aux2);//Renombramos para que se quede como estaba el original.
            } catch (NotSupportedException ex) {
                System.out.println("NO SE PUDO GUARDAR EL FICHERO");
            }

            editarMetadatos.toBack();
            fillLibrary();//recargamos la tabla de la biblioteca para mostrar los datos actualizados
        }
    }

    //rellenamos los datos de la ventana con los metadatos del fichero que se ha seleccionado y se quiere modificar
    public void fillWindow(File file) throws IOException, UnsupportedTagException, InvalidDataException {
        Mp3File mp3file = new Mp3File(file.getAbsoluteFile());
        if (mp3file.hasId3v2Tag()) {
            ID3v2 id3v2Tag = mp3file.getId3v2Tag();
            //si hay datos los ponemos, si no ponemos unos por defecto
            if (id3v2Tag.getTitle() != null) {
                editViewTitle.setText(id3v2Tag.getTitle());
            } else {
                editViewTitle.setText("---");
            }
            if (id3v2Tag.getArtist() != null) {
                editViewArtist.setText(id3v2Tag.getArtist());
            } else {
                editViewArtist.setText("---");
            }
            if (id3v2Tag.getAlbum() != null) {
                editViewAlbum.setText(id3v2Tag.getAlbum());
            } else {
                editViewAlbum.setText("---");
            }
            if (id3v2Tag.getCopyright() != null) {
                editViewCopyright.setText(id3v2Tag.getCopyright());
            }
            if (id3v2Tag.getWmpRating() != -1) {
                editViewRating.setText("" + id3v2Tag.getWmpRating());
            }
        }
    }

    public void setFile(File file) {//escogemos el fichero que vamos a modificar en la ventana de editar metadatos
        this.file = file;
    }

    /*############################>>> AJUSTES <<<###############################*/
    @FXML
    private void espOnClick(MouseEvent event) {//queremos cambiar de idioma a español
        System.out.println("Has pinchado en: ESPAÑOL");
        Parent root = null;
        main.getChildren().remove(0);
        int es_EN = 0;
        escribeCambioIdioma(es_EN);

        try {
            player.stop();
            Locale.setDefault(new Locale("es_es"));

            ResourceBundle resourceBundle = ResourceBundle.getBundle("languages.text_es");
            root = FXMLLoader.load(getClass().getResource("reproductor.fxml"), resourceBundle);

            VBox.setVgrow(root, Priority.ALWAYS);
        } catch (IOException ex) {
            System.out.println("Recurso no encontrado");
        }

        main.getChildren().add(root);
    }

    @FXML
    private void enOnClick(MouseEvent event) {//queremos cambiar a inglés
        System.out.println("Has pinchado en: INGLESE");
        Parent root = null;
        main.getChildren().remove(0);
        int es_EN = 1;
        escribeCambioIdioma(es_EN);

        try {
            player.stop();
            Locale.setDefault(Locale.ENGLISH);

            ResourceBundle resourceBundle = ResourceBundle.getBundle("languages.text_en");
            root = FXMLLoader.load(getClass().getResource("reproductor.fxml"), resourceBundle);

            VBox.setVgrow(root, Priority.ALWAYS);
        } catch (IOException ex) {
            System.out.println("Recurso no encontrado");
        }

        main.getChildren().add(root);
    }

    private void escribeCambioIdioma(int idIdioma) { //añadimos en el archivo de preferencias el idioma seleccionado
        preferences.putInt("idIdioma", idIdioma);

    }

    @FXML
    private void daltonismRadioBtnOnClick(MouseEvent event) { //si pulsamos sobre el radio de daltonismo, hacemos todos los cambios de color pertinentes
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
            searchEntrieLabel.setStyle("-fx-text-fill:#000000");
            playlistSplit1Label.setStyle("-fx-text-fill:#000000");

            playlistSplit1AnchorPane.setStyle("-fx-background-color:#ff9500");
            libraryPaneLabel.setStyle("-fx-text-fill:#ff9500");
            searchLabel.setStyle("-fx-text-fill:#ff9500");
            favouritesPaneLabel.setStyle("-fx-text-fill:#ff9500");

            tapa.setStyle("-fx-background-color:#ff9500");
            name1.setTextFill(Color.web("#ff9500"));
            miniPlayer.setStyle("-fx-background-color:#cc7700");
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
            searchEntrieLabel.setStyle("-fx-text-fill:#ababab");

            playlistSplit1AnchorPane.setStyle("-fx-background-color:#4a0707");
            libraryPaneLabel.setStyle("-fx-text-fill:#4a0707");
            searchLabel.setStyle("-fx-text-fill:#4a0707");
            favouritesPaneLabel.setStyle("-fx-text-fill:#4a0707");

            tapa.setStyle("-fx-background-color:#4a0707");
            name1.setTextFill(Color.web("#4a0707"));
            miniPlayer.setStyle("-fx-background-color:#240000");
        }
        shuffleRepeatActive();
    }

    private void daltonismFunc(boolean persistentDaltonims) { //al cargar la interfaz, cambiamos a modo daltónico si estaba marcado a true en las preferencias
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
            searchEntrieLabel.setStyle("-fx-text-fill:#000000");
            playlistSplit1AnchorPane.setStyle("-fx-background-color:#ff9500");
            libraryPaneLabel.setStyle("-fx-text-fill:#ff9500");
            searchLabel.setStyle("-fx-text-fill:#ff9500");
            favouritesPaneLabel.setStyle("-fx-text-fill:#ff9500");

            tapa.setStyle("-fx-background-color:#ff9500");
            name1.setTextFill(Color.web("#ff9500"));
            miniPlayer.setStyle("-fx-background-color:#cc7700");
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
            searchEntrieLabel.setStyle("-fx-text-fill:#ababab");
            playlistSplit1AnchorPane.setStyle("-fx-background-color:#4a0707");
            libraryPaneLabel.setStyle("-fx-text-fill:#4a0707");
            searchLabel.setStyle("-fx-text-fill:#4a0707");
            favouritesPaneLabel.setStyle("-fx-text-fill:#4a0707");

            tapa.setStyle("-fx-background-color:#4a0707");
            name1.setTextFill(Color.web("#4a0707"));
            miniPlayer.setStyle("-fx-background-color:#240000");
        }
        shuffleRepeatActive();
    }

    @FXML
    private void pathBtnOnClick(MouseEvent event) { //cambiamos el path de la biblioteca local, donde vamos a situar los archivos
        DirectoryChooser directoryChooser = new DirectoryChooser();
        if (preferences.getInt("idIdioma", 0) == 0) {
            directoryChooser.setTitle("Seleccione Carpeta Biblioteca");
        } else {
            directoryChooser.setTitle("Selecct Your Library Folder");
        }

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

    /*############################## >>> MENU LATERAL <<< ############################*/
    //estos métodos son para mostrar la pestaña al pulsar sobre alguna de las entradas en el menú lateral
    @FXML
    private void favouritesOnClick(MouseEvent event) {
        System.out.println("Has pinchado en: FAVORITOS");
        favouritesPane.toFront();
        cambiarSeleccion();
        tab = FAVOURITES;
        guardarSeleccion(tab);
        fillFavourites();
        favouritesSelected.setVisible(true);
    }

    @FXML
    private void playlistOnClick(MouseEvent event) {
        System.out.println("Has pinchado en: PLAYLIST");
        playlistPane.toFront();
        cambiarSeleccion();
        tab = PLAYLIST;
        guardarSeleccion(tab);
        fillPlaylists();
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
        fillLibrary();
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

    /*################################# MENU CONTEXTUAL DE LAS TABLAS #############################*/
    //segun la tabla en la que nos encontramos, realizamos diferentes menús contextuales
    @FXML
    private void tableOnClick(MouseEvent event) throws IOException, UnsupportedTagException, InvalidDataException {
        TableView<Song> tabla = null;
        switch (tab) {
            case LIBRARY:
                tabla = libraryTable;
                break;
            case FAVOURITES:
                tabla = favouritesTable;
                break;
            case SEARCH:
                tabla = searchTable;
                break;
            case PLAYLIST:
                tabla = playlistTable;
                break;
        }
        final TableView<Song> table = tabla;

        if (event.getButton().equals(MouseButton.SECONDARY) && (table.getSelectionModel().getSelectedItem() != null)) {
            System.out.println(table.getSelectionModel().getSelectedItem().file);
            //Creamos menu contextual del click derecho
            ContextMenu context = new ContextMenu();
            MenuItem play;
            if (preferences.getInt("idIdioma", 0) == 0) {
                play = new MenuItem("Reproducir");
            } else {
                play = new MenuItem("Play");
            }

            play.setOnAction((ActionEvent e) -> {
                System.out.println("PLAY");
                try {
                    playSong(table);
                } catch (IOException ex) {
                    Logger.getLogger(reproductorController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedTagException ex) {
                    Logger.getLogger(reproductorController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidDataException ex) {
                    Logger.getLogger(reproductorController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            MenuItem edit;
            if (preferences.getInt("idIdioma", 0) == 0) {
                edit = new MenuItem("Editar");
            } else {
                edit = new MenuItem("Edit");
            }
            edit.setOnAction((ActionEvent e) -> {
                try {
                    // Pasar el fichero de la cancion en cuestion
                    editarMetadatos.toFront();
                    setFile(table.getSelectionModel().getSelectedItem().file);
                    fillWindow(table.getSelectionModel().getSelectedItem().file);
                    // Se muestra y espera
                } catch (IOException | UnsupportedTagException | InvalidDataException ex) {
                    System.out.println("ALGO FUE MAL AL EDITAR");
                }
            });
            MenuItem addFav = null;
            if (tab != FAVOURITES) {
                if (preferences.getInt("idIdioma", 0) == 0) {
                    addFav = new MenuItem("Añadir/Quitar a/de Favoritos");
                } else {
                    addFav = new MenuItem("Add/Remove from Favourites");
                }
                addFav.setOnAction((ActionEvent e) -> {
                    if (table.getSelectionModel().getSelectedItem().fav) {
                        removeFavourite(table.getSelectionModel().getSelectedItem());
                    } else {
                        addFavourite(table.getSelectionModel().getSelectedItem());
                    }
                });
            }
            MenuItem addPl;
            if (preferences.getInt("idIdioma", 0) == 0) {
                addPl = new MenuItem("Añadir a Playlist");
            } else {
                addPl = new MenuItem("Add to Playlist");
            }
            addPl.setOnAction((ActionEvent e) -> {
                // Guardamos qué cancion se quiere añadir
                selectedSongToAddToPL = table.getSelectionModel().getSelectedItem();

                // Rellenamos la lista
                selectPlaylistList.getItems().clear(); //Vaciamos
                File dir = new File(PLAYLIST_PATH); //Conseguimos la carpeta que contiene las playlists
                File[] listado = dir.listFiles(new FilenameFilter() {
                    public boolean accept(File dir, String name) { //Filtramos a las extensiones y obtenemos la lista
                        return (name.toLowerCase().endsWith(".txt"));
                    }
                });
                if (listado == null || listado.length == 0) {
                    System.out.println("No hay elementos dentro de la playlist seleccionada");
                    return;
                } else {
                    for (File playlist : listado) {
                        selectPlaylistList.getItems().add(playlist.getName().substring(0, playlist.getName().length() - 4));
                    }
                }
                playlistList.getItems().sort(null); //Ordena de manera natural los Strings

                //Traemos el planel al frente
                selectPlaylistWindow.toFront();
                addSongToPlaylistPane.toFront();
            });
            MenuItem delete;
            switch (tab) {
                case PLAYLIST:
                    if (preferences.getInt("idIdioma", 0) == 0) {
                        delete = new MenuItem("Eliminar de esta Playlist");
                    } else {
                        delete = new MenuItem("Remove from Playlist");
                    }
                    delete.setOnAction((ActionEvent e) -> {
                        Alert alert = new Alert(AlertType.CONFIRMATION);
                        if (preferences.getInt("idIdioma", 0) == 0) {
                            alert.setTitle("¿Está Seguro?");
                            alert.setHeaderText("El archivo será borrado del disco por completo.");
                            alert.setContentText("¿Está seguro de querer realizar esto?");
                        } else {
                            alert.setTitle("You sure?");
                            alert.setHeaderText("The file will be deleted from the disk completely.");
                            alert.setContentText("Are you sure that you want to do this?");
                        }

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            //TODO quitar de la playlist
                        } else {
                            // El usuario cancela y no se hace nada
                        }
                    });
                    break;
                case LIBRARY:
                    if (preferences.getInt("idIdioma", 0) == 0) {
                        delete = new MenuItem("Borrar*");
                    } else {
                        delete = new MenuItem("Delete*");
                    }
                    delete.setOnAction((ActionEvent e) -> {
                        Alert alert = new Alert(AlertType.CONFIRMATION);
                        if (preferences.getInt("idIdioma", 0) == 0) {
                            alert.setTitle("¿Está Seguro?");
                            alert.setHeaderText("El archivo será borrado del disco por completo.");
                            alert.setContentText("¿Está seguro de querer realizar esto?");
                        } else {
                            alert.setTitle("You sure?");
                            alert.setHeaderText("The file will be deletit from the disk completely.");
                            alert.setContentText("Are you sure that you want to do this?");
                        }

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            table.getSelectionModel().getSelectedItem().file.delete();
                            fillLibrary();
                        } else {
                            // El usuario cancela y no se hace nada
                        }
                    });
                    break;
                case SEARCH:
                    if (searchChoice.getValue().equals("Library") || searchChoice.getValue().equals("Biblioteca")) {
                        if (preferences.getInt("idIdioma", 0) == 0) {
                            delete = new MenuItem("Borrar*");
                        } else {
                            delete = new MenuItem("Delete*");
                        }
                        delete.setOnAction((ActionEvent e) -> {
                            Alert alert = new Alert(AlertType.CONFIRMATION);
                            if (preferences.getInt("idIdioma", 0) == 0) {
                                alert.setTitle("¿Está Seguro?");
                                alert.setHeaderText("El archivo será borrado del disco por completo.");
                                alert.setContentText("¿Está seguro de querer realizar esto?");
                            } else {
                                alert.setTitle("You sure?");
                                alert.setHeaderText("The file will be deleted from the disk completely.");
                                alert.setContentText("Are you sure that you want to do this?");
                            }

                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK) {
                                table.getSelectionModel().getSelectedItem().file.delete();
                                fillLibrary();
                            } else {
                                // El usuario cancela y no se hace nada
                            }

                        });
                    } else {
                        if (preferences.getInt("idIdioma", 0) == 0) {
                            delete = new MenuItem("Quitar de favoritos");
                        } else {
                            delete = new MenuItem("Remove from favourites");
                        }
                        delete.setOnAction((ActionEvent e) -> {
                            removeFavourite(table.getSelectionModel().getSelectedItem());
                            fillFavourites();
                        });
                        break;
                    }
                    break;
                default:
                    if (preferences.getInt("idIdioma", 0) == 0) {
                        delete = new MenuItem("Quitar de favoritos");
                    } else {
                        delete = new MenuItem("Remove from favourites");
                    }
                    delete.setOnAction((ActionEvent e) -> {
                        removeFavourite(table.getSelectionModel().getSelectedItem());
                        fillFavourites();
                    });
                    break;
            }
            //Añadimos las opciones con un separador en borrar ya que esto borrara el fichero del disco.
            if (addFav == null) {
                context.getItems().addAll(play, edit, addPl, new SeparatorMenuItem(), delete);
            } else {
                context.getItems().addAll(play, edit, addFav, addPl, new SeparatorMenuItem(), delete);
            }
            table.setContextMenu(context);
            //Añadimos el gestor de eventos del raton para la seleccion del menú

        } else if (event.getButton().equals(MouseButton.PRIMARY) && (event.getClickCount() == 2) && (table.getSelectionModel().getSelectedItem() != null)) {
            playSong(table);
        }
    }

    /*########################################### PLAY SONG ##########################################*/
    //reproducimos la canción seleccionada 
    private void playSong(TableView<Song> table) throws IOException, UnsupportedTagException, InvalidDataException {
        player.stop();
        //TODO quizas hacer algo con thread tambien player.x

        loadedSong = table.getSelectionModel().getSelectedItem().file;
        playingSong = table.getSelectionModel().getSelectedItem();
        audioPane.toFront();

        ponerActualizarMetaDatos();

        //TODO mirar que tiene la table si viene de una playlist
        player.playSong(table.getItems(), table.getItems().indexOf(table.getSelectionModel().getSelectedItem()));
        cambiarSeleccion();
        tab = PLAYER;
        activaSeleccion();
    }

    //ponemos los metadatos de la canción en la ventana de reproducción
    public void ponerActualizarMetaDatos() throws IOException, UnsupportedTagException, InvalidDataException {
        if (loadedSong.getName().toLowerCase().endsWith(".mp3")) {
            Mp3File mp3file = new Mp3File(loadedSong.getAbsoluteFile());
            ID3v2 tag;
            if (mp3file.hasId3v2Tag()) {
                tag = mp3file.getId3v2Tag();
            } else {
                // mp3 does not have an ID3v2 tag, let's create one..
                tag = new ID3v24Tag();
                mp3file.setId3v2Tag(tag);
            }
            name.setText(tag.getTitle() == null ? loadedSong.getName() : tag.getTitle());
            name1.setText(tag.getTitle() == null ? loadedSong.getName() : tag.getTitle());
            artist.setText(tag.getTitle() == null ? "---" : tag.getArtist());
            artist1.setText(tag.getTitle() == null ? "---" : tag.getArtist());

            Image caratula = null;
            if (tag.getAlbumImage() != null) {
                caratula = SwingFXUtils.toFXImage(ImageIO.read(new ByteArrayInputStream((byte[]) tag.getAlbumImage())), null);
            } else {
                caratula = new Image(getClass().getResourceAsStream("/assets/imagenes/disk.png"));
            }
            musicImage.setImage(caratula);
            musicImage1.setImage(caratula);
        } else {
            name.setText(playingSong.songName);
            name1.setText(playingSong.songName);
            artist.setText(playingSong.artist);
            artist1.setText(playingSong.artist);

            Image caratula = new Image(getClass().getResourceAsStream("/assets/imagenes/disk.png"));
            musicImage.setImage(caratula);
            musicImage1.setImage(caratula);
        }

        if (playingSong.fav) {
            if (daltonism) {
                fav.setImage(favDaltImg);
                fav1.setImage(favDaltImg);

            } else {
                fav.setImage(favRedImg);
                fav1.setImage(favRedImg);

            }
        } else {
            fav.setImage(favImg);
            fav1.setImage(favImg);

        }
    }

    /*####################################### OTROS METODOS ######################################*/
    private LocalDate creationDate(File file) { //formatea la fecha de un ffichero
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

    public String durationFormatted(long durationMs) { //da formato a la duración de un fichero
        long minutes = (durationMs / 1000) / 60;
        long seconds = (durationMs / 1000) % 60;
        String time = String.format("%02d", minutes) + ":" + String.format("%02d", seconds);

        return time;
    }

    private void addEntrie(int table, Song s) {//mete una entrada en una tabla
        switch (table) {
            case LIBRARY_TABLE:
                libraryTable.getItems().add(s);
                break;
            case FAVOURITES_TABLE:
                favouritesTable.getItems().add(s);
                break;
            case PLAYLISTS_TABLE:
                playlistTable.getItems().add(s);
                break;
        }
    }

}
