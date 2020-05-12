/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomultimedia_1;

import com.mpatric.mp3agic.EncodedText;
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v1Tag;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.ID3v24Tag;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.beans.EventHandler;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
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
import java.util.HashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
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
import javafx.scene.Scene;
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
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import static proyectomultimedia_1.ProyectoMultimedia_1.preferences;

/**
 *
 * @author Irene Maria Padilla Munoz
 */
public class reproductorController implements Initializable {

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
    private void sliderDurationKeyPressed(KeyEvent event) {
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
    private void sliderDurationOnDragged(MouseEvent event) {
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
        /*double valor = sliderDuration.getValue();
        double valor1 = sliderDuration1.getValue();
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

        player.setPos(valor * 60);*/
    }

    //Variables
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
    public static final String PLAYLIST_PATH = "./src/assets/playlists/";

    public boolean daltonism;
    public boolean persistentDaltonims;
    public boolean video;
    public boolean shuffleActive;
    public boolean repeatActive;
    public boolean playActive;
    public boolean favActive;
    public boolean miniplayerActive;
    public int tab;
    public String tiempo;
    public DecimalFormat df = new DecimalFormat("##.##");
    public File loadedSong;
    public Song playingSong;
    public Player player;
    public String favouritesPath;

    private String selectedPlaylistToAdd;
    private Song selectedSongToAddToPL;

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
    final public Image pauseImg = new Image(getClass().getResourceAsStream("/assets/imagenes/pause.png"));
    final public Image playImg = new Image(getClass().getResourceAsStream("/assets/imagenes/play.png"));

    @FXML
    private void searchOnClick(MouseEvent event) {
        System.out.println("Has pinchado en: BUSCAR");
        searchPane.toFront();
        cambiarSeleccion();
        tab = SEARCH;
        guardarSeleccion(tab);
        searchSelected.setVisible(true);
    }

    @FXML
    private void searchKeyPressed(KeyEvent event) {

        TableView<Song> table = null;
        switch (searchChoice.getValue()) {
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
        FilteredList<Song> filteredData = new FilteredList<Song>(table.getItems(), p -> true);
        searchTable.setItems(filteredData);

        searchBar.textProperty().addListener((prop, old, text) -> {
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
            if (searchTable.getItems().size() == 0) {
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

    private void searchRadioOnClick(MouseEvent event) {
        searchBar.setText("");
        searchTable.getItems().clear();
    }

    @FXML
    private void crossOnClick(MouseEvent event) {
        playlistPane.toFront();
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
        for (int i = 0; i < listado.length && existe == false; i++) {
            File file = listado[i];
            if (file.getName().equals(newPlaylistTextField.getText() + ".txt")) {
                existe = true;
            }
        }
        if (!existe) {
            File file = new File(PLAYLIST_PATH + newPlaylistTextField.getText() + ".txt");
            FileWriter fstream = new FileWriter(file, false);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(""); //Vamos escribiendo las rutas
            playlistPane.toFront();
        } else {
            if (preferences.getInt("idIdioma", 0) == 0) {
                newPlaylistExist.setText("Ya existe!");
            } else {
                newPlaylistExist.setText("It already exists");
            }
        }
    }

    @FXML
    private void newPlaylistOnClick(MouseEvent event) {
        newPlaylistExist.setText("");
        newPlaylistTextField.setText("");
        newPlaylistPane.toFront();
        //newPlaylistPane.toFront();
    }

    @FXML
    private void crossPLOnClick(MouseEvent event) {
        addSongToPlaylistPane.toBack();
    }

    @FXML
    private void okBtnPLOnClick(ActionEvent event) {
        String playlistPath = PLAYLIST_PATH + selectedPlaylistToAdd + ".txt";
        boolean presente = songInPlaylist(playlistPath, selectedSongToAddToPL);
        System.out.println(selectedSongToAddToPL.file.getAbsolutePath() + " -- " + selectedPlaylistToAdd + " --- " + presente);
        if (presente) {
            okCancelPlPane.toFront();
        } else {
            FileWriter fstream = null;
            try {
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
            addSongToPlaylistPane.toBack();
        }
    }

    @FXML
    private void okPlConfirmBtnOnClick(ActionEvent event) {
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
    private void cancelPlConfirmBtnOnClick(ActionEvent event) {
        okCancelPlPane.toBack();
    }

    @FXML
    private void sliderDurationKeyPressed1(KeyEvent event) {
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
    private void sliderDurationOnDragged1(MouseEvent event) {
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

    public class Song {

        private String songName;
        private String artist;
        private String album;
        private LocalDate date;
        private String duration;
        private File file;
        private boolean fav;

        public Song(String songName, String artist, String album, LocalDate date, String duration, File file, boolean fav) {
            this.songName = songName;
            this.artist = artist;
            this.album = album;
            this.date = date;
            this.duration = duration;
            this.file = file;
            this.fav = fav;
        }

        public Song() {
            this.songName = "";
            this.artist = "";
            this.album = "";
            this.date = LocalDate.now();
            this.duration = "0:0";
            this.file = null;
            this.fav = false;
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

        public File getFile() {
            return file;
        }

        public void setFile(File file) {
            this.file = file;
        }

        public boolean isFav() {
            return fav;
        }

        public void setFav(boolean fav) {
            this.fav = fav;
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

        if (preferences.getInt("idIdioma", 0) == 0) {
            ObservableList<String> options = FXCollections.observableArrayList("Internet", "Biblioteca", "Favoritos");
            searchChoice.setItems(options);
            searchChoice.setValue("Biblioteca");

        } else {
            ObservableList<String> options = FXCollections.observableArrayList("Internet", "Library", "Favourites");
            searchChoice.setItems(options);
            searchChoice.setValue("Library");

        }
        searchChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                searchBar.clear();
            }
        });

        //TODO cargar playlists
        ObservableList<String> data = FXCollections.observableArrayList("playlist 1");
        playlistList.setItems(data);
        playlistSelected.setVisible(false);

        //
        songColumnSrch.setCellValueFactory(new PropertyValueFactory<Song, String>("songName"));
        artistColumnSrch.setCellValueFactory(new PropertyValueFactory<Song, String>("artist"));
        albumColumnSrch.setCellValueFactory(new PropertyValueFactory<Song, String>("album"));
        durationColumnSrch.setCellValueFactory(new PropertyValueFactory<Song, String>("duration"));
        dateColumnSrch.setCellValueFactory(new PropertyValueFactory<Song, LocalDate>("date"));

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
        activaSeleccion();

        initializeTables();

        if (!preferences.get("favouritePath", "").isEmpty()) {
            favouritesPath = preferences.get("favouritePath", "");
            fillFavourites();
        }
        if (!preferences.get("library", "").isEmpty()) {
            path.setText(preferences.get("library", ""));
            fillLibrary();
        }

        fillPlaylists();
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

        sliderVolume.setValue(preferences.getFloat("preferedVolume", 50));
        sliderDuration.setValue(0);
        sliderDuration1.setValue(0);

        player = new Player(this, true);
    }

    @FXML
    private void favOnClick(MouseEvent event) {
        favActive = !favActive;
        if (favActive) {
            if (daltonism) {
                fav.setImage(favDaltImg);
                fav1.setImage(favDaltImg);

            } else {
                fav.setImage(favRedImg);
                fav1.setImage(favRedImg);

            }
            addFavourite(playingSong);
        } else {
            fav.setImage(favImg);
            fav1.setImage(favImg);

            removeFavourite(playingSong);

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
        player.pauseResume();
    }

    @FXML
    private void nextOnClick(MouseEvent event) {
        System.out.println("Has pinchado en: SIGUIENTE CANCION");
        player.playNext();
    }

    @FXML
    private void previousOnClick(MouseEvent event) {
        System.out.println("Has pinchado en: CANCION ANTERIOR");
        player.playPrevious();
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

    @FXML
    private void espOnClick(MouseEvent event) {
        System.out.println("Has pinchado en: ESPAÑITA");
        Parent root = null;
        main.getChildren().remove(0);
        int es_EN = 0;
        escribeCambioIdioma(es_EN);

        try {
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
    private void enOnClick(MouseEvent event) {
        System.out.println("Has pinchado en: INGLESE");
        Parent root = null;
        main.getChildren().remove(0);
        int es_EN = 1;
        escribeCambioIdioma(es_EN);

        try {
            Locale.setDefault(Locale.ENGLISH);

            ResourceBundle resourceBundle = ResourceBundle.getBundle("languages.text_en");
            root = FXMLLoader.load(getClass().getResource("reproductor.fxml"), resourceBundle);

            VBox.setVgrow(root, Priority.ALWAYS);
        } catch (IOException ex) {
            System.out.println("Recurso no encontrado");
        }

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
            searchEntrieLabel.setStyle("-fx-text-fill:#000000");
            playlistSplit1Label.setStyle("-fx-text-fill:#000000");

            playlistSplit1AnchorPane.setStyle("-fx-background-color:#ff9500");
            libraryPaneLabel.setStyle("-fx-text-fill:#ff9500");
            searchLabel.setStyle("-fx-text-fill:#ff9500");
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
            searchEntrieLabel.setStyle("-fx-text-fill:#ababab");

            playlistSplit1AnchorPane.setStyle("-fx-background-color:#4a0707");
            libraryPaneLabel.setStyle("-fx-text-fill:#4a0707");
            searchLabel.setStyle("-fx-text-fill:#4a0707");
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
            searchEntrieLabel.setStyle("-fx-text-fill:#000000");
            playlistSplit1AnchorPane.setStyle("-fx-background-color:#ff9500");
            libraryPaneLabel.setStyle("-fx-text-fill:#ff9500");
            searchLabel.setStyle("-fx-text-fill:#ff9500");
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
            searchEntrieLabel.setStyle("-fx-text-fill:#ababab");
            playlistSplit1AnchorPane.setStyle("-fx-background-color:#4a0707");
            libraryPaneLabel.setStyle("-fx-text-fill:#4a0707");
            searchLabel.setStyle("-fx-text-fill:#4a0707");
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

    @FXML
    private void changeOnClick(MouseEvent event) {
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
            MenuItem play = new MenuItem("Reproducir"); //TODO Internacionalizar
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
            MenuItem edit = new MenuItem("Editar");
            edit.setOnAction((ActionEvent e) -> {
                try {
                    int idIdioma = preferences.getInt("idIdioma", PLAYER);
                    ResourceBundle resources = null;
                    if (idIdioma == 0) {
                        resources = ResourceBundle.getBundle("languages.text_es");
                    } else {
                        resources = ResourceBundle.getBundle("languages.text_en");
                    }
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("edtarArchivo.fxml"));
                    loader.setResources(resources);
                    AnchorPane page = (AnchorPane) loader.load();
                    // Crear el dialogo de la escena
                    Stage dialogStage = new Stage();
                    dialogStage.setTitle("Edit Person");
                    dialogStage.initModality(Modality.APPLICATION_MODAL);
                    Scene scene = new Scene(page);
                    dialogStage.setScene(scene);

                    // Pasar el fichero de la cancion en cuestion
                    EdtarArchivoController controller = loader.getController();
                    controller.setDialogStage(dialogStage);
                    controller.setFile(table.getSelectionModel().getSelectedItem().file);
                    controller.fillWindow(table.getSelectionModel().getSelectedItem().file);

                    // Se muestra y espera
                    dialogStage.showAndWait();
                    fillLibrary();
                } catch (IOException | UnsupportedTagException | InvalidDataException ex) {
                    System.out.println("ALGO FUE MAL AL EDITAR");
                }
            });
            MenuItem addFav = null;
            if (tab != FAVOURITES) {
                addFav = new MenuItem("Añadir/Quitar a/de Favoritos");
                addFav.setOnAction((ActionEvent e) -> {
                    if (table.getSelectionModel().getSelectedItem().fav) {
                        removeFavourite(table.getSelectionModel().getSelectedItem());
                    } else {
                        addFavourite(table.getSelectionModel().getSelectedItem());
                    }
                });
            }
            MenuItem addPl = new MenuItem("Añadir a Playlist");
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
                    delete = new MenuItem("Eliminar de esta Playlist");
                    delete.setOnAction((ActionEvent e) -> {
                        Alert alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("¿Está Seguro?"); //TODO Internacionalizar
                        alert.setHeaderText("El archivo será retirado solamente de la Playlist.");
                        alert.setContentText("¿Está seguro de querer realizar esto?");

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            //TODO quitar de la playlist
                        } else {
                            // El usuario cancela y no se hace nada
                        }
                    });
                    break;
                case LIBRARY:
                    delete = new MenuItem("Borrar*");
                    delete.setOnAction((ActionEvent e) -> {
                        Alert alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("¿Está Seguro?"); //TODO Internacionalizar
                        alert.setHeaderText("El archivo será borrado del disco por completo.");
                        alert.setContentText("¿Está seguro de querer realizar esto?");

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
                        delete = new MenuItem("Borrar*");
                        delete.setOnAction((ActionEvent e) -> {
                            Alert alert = new Alert(AlertType.CONFIRMATION);
                            alert.setTitle("¿Está Seguro?"); //TODO Internacionalizar
                            alert.setHeaderText("El archivo será borrado del disco por completo.");
                            alert.setContentText("¿Está seguro de querer realizar esto?");

                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK) {
                                table.getSelectionModel().getSelectedItem().file.delete();
                                fillLibrary();
                            } else {
                                // El usuario cancela y no se hace nada
                            }

                        });
                    } else {
                        delete = new MenuItem("Quitar de favoritos");
                        delete.setOnAction((ActionEvent e) -> {
                            removeFavourite(table.getSelectionModel().getSelectedItem());
                            fillFavourites();
                        });
                        break;
                    }
                    break;
                default:
                    delete = new MenuItem("Quitar de favoritos");
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
    
    public void ponerActualizarMetaDatos() throws IOException, UnsupportedTagException, InvalidDataException{
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
            case SEARCH:
                searchSelected.setVisible(false);
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
            case SEARCH:
                searchPane.toFront();
                searchSelected.setVisible(true);
                break;
        }
        guardarSeleccion(tab);
    }

    private void guardarSeleccion(int tab) {
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
                    Song s = new Song(title, artist, album, date, time, file, true);
                    addEntrie(FAVOURITES_TABLE, s);
                }
            }
            b.close();
        } catch (IOException | UnsupportedTagException | InvalidDataException ex) {
            System.out.println("ERROR AL CARGAR LA LISTA DE FAVORITOS");
        } finally {
            try {
                f.close();
            } catch (IOException ex) {
                Logger.getLogger(reproductorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

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

    private void fillPlaylists() {
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

    private void cargaPlaylist(String playlist) {
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

    public String durationFormatted(long durationMs) {
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
                playlistTable.getItems().add(s);
                break;
        }
    }

    private boolean songInPlaylist(String playlistPath, Song song) {
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
}
