/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomultimedia_1;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

public class Player {

    Media media;
    MediaPlayer mediaPlayer;

    String source;
    double totalCurr;

    boolean isPlaying = false;
    boolean isActive = false;

    private Thread updaterThread;

    int songIndex;
    private ObservableList<Song> table;
    private float volumen;

    reproductorController reproductor;

    String youtubeExecName = "youtube-dl.exe";

    public Player() {
        isPlaying = false;
        isActive = false;
    }

    boolean playList;

    public Player(reproductorController d, boolean pl) { //Instancia el player con el reproductor que lo llame y asocia el volumen que hubiese
        reproductor = d;
        this.playList = pl;

        volumen = (float) reproductor.sliderVolume.getValue();
    }

    public void setVolume(float volume) {
        volumen = volume;
        if (isActive) {
            mediaPlayer.setVolume(volumen / 100);
        }
    }

    /**
     * *
     * Reproduce la cancion que tenga el index de la tabla que se le pase como
     * argumento
     *
     * @param table Tabla donde están las canciones a reproducir
     * @param index El indice de la canción que queremos reproducir
     */
    public void playSong(ObservableList<Song> table, int index) {
        this.table = table;
        this.songIndex = index;
        Platform.runLater(() -> { //Se activan los botones del reproductor
            reproductor.previous.setDisable(false);
            reproductor.next.setDisable(false);
            reproductor.shuffle.setDisable(false);
            reproductor.repeat.setDisable(false);
            reproductor.play.setDisable(false);
            reproductor.fav.setDisable(false);

            reproductor.previous1.setDisable(false);
            reproductor.next1.setDisable(false);
            reproductor.shuffle1.setDisable(false);
            reproductor.repeat1.setDisable(false);
            reproductor.play1.setDisable(false);
            reproductor.fav1.setDisable(false);
            reproductor.sliderDuration1.setDisable(false);

            reproductor.loadedSong = table.get(songIndex).getFile(); //Se guardan como activas la cancion que toque
            reproductor.playingSong = table.get(songIndex);
            try {
                reproductor.ponerActualizarMetaDatos(); //Actualiza los nombres y descripciones del player
            } catch (IOException | UnsupportedTagException | InvalidDataException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        x = new Thread(new Task<Void>() { //Hilo que albergara la ejecución de la canción o vídeo
            @Override
            protected Void call() {
                try {

                    File song = table.get(songIndex).getFile(); //Conseguimos el fichero que toque reproducir

                    isActive = true;

                    java.net.URI uri = song.toURI();
                    source = uri.toString();

                    System.out.println("starting ...");

                    media = new Media(source); //Creamos los medios necesarios
                    mediaPlayer = new MediaPlayer(media);

                    reproductor.equalizerModeChoiceBox.setValue("Flat"); //Introducimos los parametros del ecualizador
                    equalize("Flat");
                    mediaPlayer.getAudioEqualizer().setEnabled(true);
                    media.setOnError(() -> {
                        stop();
                        media.getError().printStackTrace();
                    });

                    mediaPlayer.setOnReady(() -> { //Cuando todo esté listo comenzamos la reproducción

                        System.out.println("Start Playing ...");

                        setVolume(volumen);

                        totalCurr = media.getDuration().toSeconds();

                        Platform.runLater(() -> {
                            /*if (!reproductor.miniplayerActive) {*/
                            reproductor.sliderDuration.setDisable(false);
                            reproductor.sliderDuration.setMin(0);
                            reproductor.sliderDuration.setMax(totalCurr);
                            reproductor.controls.setDisable(false);
                            reproductor.musicImage.setOpacity(1.0);
                            reproductor.duration.setText(reproductor.durationFormatted((long) media.getDuration().toMillis()));
                            reproductor.duration.setVisible(true);
                            reproductor.timeCounter.setVisible(true);
                            reproductor.playActive = false;
                            reproductor.play.setImage(reproductor.pauseImg);
                            /* } else {*/
                            reproductor.sliderDuration1.setMin(0);
                            reproductor.sliderDuration1.setMax(totalCurr);
                            reproductor.controls1.setDisable(false);
                            reproductor.musicImage1.setOpacity(1.0);
                            reproductor.duration1.setText(reproductor.durationFormatted((long) media.getDuration().toMillis()));
                            reproductor.duration1.setVisible(true);
                            reproductor.timeCounter1.setVisible(true);
                            reproductor.playActive = false;
                            reproductor.play1.setImage(reproductor.pauseImg);
                            /* }*/
                            if (song.getName().toLowerCase().endsWith(".mp4")) { // Es un video-clip
                                reproductor.musicVideo.setMediaPlayer(mediaPlayer);
                                reproductor.musicVideo1.setMediaPlayer(mediaPlayer);
                                /*reproductor.musicVideo.setFitHeight(300);
                                reproductor.musicVideo.setFitWidth(400);
                                reproductor.musicVideo.setPreserveRatio(false);
                                
                                
                                reproductor.musicVideo1.setFitHeight(100);
                                reproductor.musicVideo1.setFitWidth(150);
                                reproductor.musicVideo1.setPreserveRatio(false);*/

                                reproductor.change.setImage(reproductor.changeMusicImg);
                                reproductor.change1.setImage(reproductor.changeMusicImg);

                                reproductor.video = true;
                                reproductor.musicVideo.toFront();
                                reproductor.musicVideo1.toFront();

                                reproductor.musicVideo.setVisible(true);
                                reproductor.musicVideo1.setVisible(true);

                                reproductor.musicImage.setVisible(false);
                                reproductor.musicImage1.setVisible(false);
                            } else {
                                reproductor.change.setImage(reproductor.changeVideoImg);
                                reproductor.change1.setImage(reproductor.changeVideoImg);

                                reproductor.video = false;
                                reproductor.musicImage.toFront();
                                reproductor.musicImage1.toFront();

                                reproductor.musicVideo.setVisible(false);
                                reproductor.musicVideo1.setVisible(false);

                                reproductor.musicImage.setVisible(true);
                                reproductor.musicImage1.setVisible(true);
                            }

                        });

                        mediaPlayer.play(); //Comenzamos la reproduccion
                        isPlaying = true;

                        startUpdating(); // y el actualizado de la GUI
                        equalize("Flat");
                        reproductor.equalizerModeChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                            @Override
                            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                                equalize((String) reproductor.equalizerModeChoiceBox.getItems().get((Integer) number2));
                            }
                        });
                        mediaPlayer.setAudioSpectrumListener((double timestamp, double duration, float[] magnitudes, float[] phases) -> {
                            handleUpdate(timestamp, duration, magnitudes, phases);
                        });
                    });

                    mediaPlayer.setOnEndOfMedia(() -> { //cuando se acabe el archivo vemos si es una playlist o no para seguir
                        if (playList) {
                            onEndOfMediaTrigger();
                        } else {
                            stop();
                            hide();
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
        x.setPriority(1);
        x.start();
    }

    Thread x;

    public void onEndOfMediaTrigger() { // Se termina la cancion o vídeo
        if (reproductor.shuffleActive) {
            playNextRandom();
        } else {
            if (reproductor.repeatActive) { // Si hay que repetir la cancion se pone al inicio y no se hacen nada mas
                setPos(0);
            } else {
                if (songIndex == (table.size() - 1)) { // Si hemos llegado a la ultima cancion de la tabla se para
                    reproductor.playActive = false;
                    reproductor.play.setImage(reproductor.playImg);
                    reproductor.play1.setImage(reproductor.playImg);
                    stop();
                    hide();
                } else {
                    playNext();
                }
            }
        }
    }

    public void playNext() { // Pasa a la siguiente cancion si la hay
        if (songIndex < (table.size() - 1)) {
            if (isPlaying) {
                mediaPlayer.stop();
                mediaPlayer.dispose();
            }
            playSong(table, songIndex + 1);
        }
    }

    private void playNextRandom() { // Pasa la siguiente cancion de manera aleatoria
        if (reproductor.repeatActive) { // Si hay que repetir no se calcula otra aleatoria
            setPos(0);
        } else {
            if (isPlaying) {
                mediaPlayer.stop();
                mediaPlayer.dispose();
            }
            mediaPlayer.dispose();
            playSong(table, (new Random().nextInt(table.size())));
        }
    }

    public void playPrevious() { //Pasa a la anterior cancion si la hay
        if (mediaPlayer.getCurrentTime().toSeconds() > 3) { //Si han pasado 3 segundos reinicia la cancion, si no pasa a la anterior
            setPos(0);
        } else if (songIndex > 0) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
            playSong(table, songIndex - 1);
        }
    }

    public void setPos(double newDurSecs) { //Establece la "posicion" del archivo que está siendo reproducido
        mediaPlayer.seek(Duration.seconds(newDurSecs));
    }

    public void pauseResume() { // Continua o pausa la ejecucion
        new Thread(new Task() {
            @Override
            protected Object call() throws Exception {
                if (mediaPlayer.getStatus().equals(MediaPlayer.Status.PAUSED)) {
                    isPlaying = true;
                    reproductor.playActive = false;
                    reproductor.play.setImage(reproductor.pauseImg);
                    reproductor.play1.setImage(reproductor.pauseImg);

                    mediaPlayer.play();
                } else if (mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)) {
                    isPlaying = false;
                    reproductor.playActive = true;
                    reproductor.play.setImage(reproductor.playImg);
                    reproductor.play1.setImage(reproductor.playImg);

                    mediaPlayer.pause();
                }
                return null;
            }
        }).start();
    }

    public void stop() { // Para la ejecucion
        if (isPlaying) {
            isPlaying = false;
            try {
                mediaPlayer.stop();
                mediaPlayer.dispose();
            } catch (Exception e) {
                System.out.println("disposing ...");
            }
        }
        isActive = false;
    }

    public void hide() {

    }

    private void startUpdating() { // Hilo que se llama cada cierto tiempo para actualizar principalmente el tiempo de ejecucion y sliders.
        updaterThread = new Thread(new Task<Void>() {
            @Override
            protected Void call() {
                try {
                    while (isActive) {
                        if (mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)) {
                            double currSec = mediaPlayer.getCurrentTime().toMillis();
                            String currentSimpleTimeStamp = reproductor.durationFormatted((long) currSec);
                            Platform.runLater(() -> reproductor.timeCounter.setText(currentSimpleTimeStamp));
                            Platform.runLater(() -> reproductor.timeCounter1.setText(currentSimpleTimeStamp));
                            double currentProgress = currSec / 1000;
                            if (!reproductor.sliderDuration.isValueChanging() && !reproductor.sliderDuration1.isValueChanging()) {
                                reproductor.sliderDuration.setValue(currentProgress);
                                reproductor.sliderDuration1.setValue(currentProgress);
                                currentP = currentProgress;
                            }
                        }
                        Thread.sleep(500);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
        updaterThread.start();
    }

    float magnitudesCorregidas[];

    private void handleUpdate(double timestamp, double duration, float[] magnitudes, float[] phases) {
        float[] buffer = createFilledBuffer(mediaPlayer.getAudioSpectrumNumBands(), mediaPlayer.getAudioSpectrumThreshold());

        for (int i = 0; i < magnitudes.length; i++) {
            if (magnitudes[i] >= buffer[i]) {
                buffer[i] = magnitudes[i];
                reproductor.series1Data[i].setYValue(magnitudes[i] - mediaPlayer.getAudioSpectrumThreshold());
            } else {
                reproductor.series1Data[i].setYValue(buffer[i] - mediaPlayer.getAudioSpectrumThreshold());
                buffer[i] -= 0.25;
            }
        }
    }

    private float[] createFilledBuffer(int size, float fillValue) {
        float[] floats = new float[size];
        Arrays.fill(floats, fillValue);
        return floats;
    }

    double currentP = 0.0;

    void equalize(String modo) {

        float a[] = new float[10];
        //miramos el modo para poner unos valores u otros
        switch (modo) {
            case "Flat":
                a[0] = 50f;
                a[1] = 50f;
                a[2] = 50f;
                a[3] = 50f;
                a[4] = 50f;
                a[5] = 50f;
                a[6] = 50f;
                a[7] = 50f;
                a[8] = 50f;
                a[9] = 50f;
                break;
            case "Electronic":
                a[0] = (4f + 24) / 0.36f;
                a[1] = (3.5f + 24) / 0.36f;
                a[2] = (1f + 24) / 0.36f;
                a[3] = (0f + 24) / 0.36f;
                a[4] = (-2f + 24) / 0.36f;
                a[5] = (2f + 24) / 0.36f;
                a[6] = (0.5f + 24) / 0.36f;
                a[7] = (1f + 24) / 0.36f;
                a[8] = (4f + 24) / 0.36f;
                a[9] = (5f + 24) / 0.36f;

                break;
            case "Classic":
                a[0] = (4.5f + 24) / 0.36f;
                a[1] = (3.5f + 24) / 0.36f;
                a[2] = (3f + 24) / 0.36f;
                a[3] = (2.5f + 24) / 0.36f;
                a[4] = (-2f + 24) / 0.36f;
                a[5] = (-1.5f + 24) / 0.36f;
                a[6] = (0f + 24) / 0.36f;
                a[7] = (2f + 24) / 0.36f;
                a[8] = (3.5f + 24) / 0.36f;
                a[9] = (4f + 24) / 0.36f;

                break;
            case "Jazz":
                a[0] = (4f + 24) / 0.36f;
                a[1] = (3f + 24) / 0.36f;
                a[2] = (1f + 24) / 0.36f;
                a[3] = (2f + 24) / 0.36f;
                a[4] = (-2f + 24) / 0.36f;
                a[5] = (-2f + 24) / 0.36f;
                a[6] = (0f + 24) / 0.36f;
                a[7] = (1.5f + 24) / 0.36f;
                a[8] = (3f + 24) / 0.36f;
                a[9] = (3.5f + 24) / 0.36f;

                break;
            case "Pop":
                a[0] = (-2f + 24) / 0.36f;
                a[1] = (-1f + 24) / 0.36f;
                a[2] = (0f + 24) / 0.36f;
                a[3] = (2f + 24) / 0.36f;
                a[4] = (4f + 24) / 0.36f;
                a[5] = (4f + 24) / 0.36f;
                a[6] = (2f + 24) / 0.36f;
                a[7] = (0f + 24) / 0.36f;
                a[8] = (-1.5f + 24) / 0.36f;
                a[9] = (-2f + 24) / 0.36f;

                break;
            case "Voice":
                a[0] = (-2f + 24) / 0.36f;
                a[1] = (-3.5f + 24) / 0.36f;
                a[2] = (-3f + 24) / 0.36f;
                a[3] = (1f + 24) / 0.36f;
                a[4] = (3.5f + 24) / 0.36f;
                a[5] = (3.5f + 24) / 0.36f;
                a[6] = (3f + 24) / 0.36f;
                a[7] = (1.5f + 24) / 0.36f;
                a[8] = (0.5f + 24) / 0.36f;
                a[9] = (-2f + 24) / 0.36f;

                break;
            case "Dance":
                a[0] = (3.5f + 24) / 0.36f;
                a[1] = (6.5f + 24) / 0.36f;
                a[2] = (5f + 24) / 0.36f;
                a[3] = (0f + 24) / 0.36f;
                a[4] = (2f + 24) / 0.36f;
                a[5] = (3.5f + 24) / 0.36f;
                a[6] = (5f + 24) / 0.36f;
                a[7] = (4f + 24) / 0.36f;
                a[8] = (3.5f + 24) / 0.36f;
                a[9] = (0f + 24) / 0.36f;

                break;
            case "Rock":
                a[0] = (5f + 24) / 0.36f;
                a[1] = (4f + 24) / 0.36f;
                a[2] = (3f + 24) / 0.36f;
                a[3] = (1.5f + 24) / 0.36f;
                a[4] = (-0.5f + 24) / 0.36f;
                a[5] = (-1.5f + 24) / 0.36f;
                a[6] = (0.5f + 24) / 0.36f;
                a[7] = (2.5f + 24) / 0.36f;
                a[8] = (3.5f + 24) / 0.36f;
                a[9] = (4.5f + 24) / 0.36f;

                break;
        }
        //Eventos para las diferentes frecuencias del ecualizador
        reproductor.volumeSlider.setValue(reproductor.sliderVolume.getValue());
        reproductor.volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mediaPlayer.setVolume(reproductor.volumeSlider.getValue() / 100);
            }
        });
        reproductor.slider1.setValue(a[0]);
        reproductor.slider1.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(
                    ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                mediaPlayer.getAudioEqualizer().getBands().get(0).setGain((newValue.doubleValue() * 0.36 - 24)); // Se pasa del intervalo [0, 100] al [-24, 12]
                System.out.println("valor de la primera banda: " + mediaPlayer.getAudioEqualizer().getBands().get(0).getGain());
            }
        });
        reproductor.slider2.setValue(a[1]);
        reproductor.slider2.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number oldValue, Number newValue) {
                mediaPlayer.getAudioEqualizer().getBands().get(1).setGain((newValue.doubleValue() * 0.36 - 24)); // Se pasa del intervalo [0, 100] al [-24, 12]
                System.out.println("valor de la 1 banda: " + mediaPlayer.getAudioEqualizer().getBands().get(0).getGain());

            }
        });
        reproductor.slider3.setValue(a[2]);
        reproductor.slider3.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number oldValue, Number newValue) {
                mediaPlayer.getAudioEqualizer().getBands().get(2).setGain((newValue.doubleValue() * 0.36 - 24)); // Se pasa del intervalo [0, 100] al [-24, 12]
                System.out.println("valor de la 2 banda: " + mediaPlayer.getAudioEqualizer().getBands().get(2).getGain());

            }
        });
        reproductor.slider4.setValue(a[3]);
        reproductor.slider4.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number oldValue, Number newValue) {
                mediaPlayer.getAudioEqualizer().getBands().get(3).setGain((newValue.doubleValue() * 0.36 - 24)); // Se pasa del intervalo [0, 100] al [-24, 12]
                System.out.println("valor de la 3 banda: " + mediaPlayer.getAudioEqualizer().getBands().get(3).getGain());

            }
        });
        reproductor.slider5.setValue(a[4]);
        reproductor.slider5.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number oldValue, Number newValue) {
                mediaPlayer.getAudioEqualizer().getBands().get(4).setGain((newValue.doubleValue() * 0.36 - 24)); // Se pasa del intervalo [0, 100] al [-24, 12]
                System.out.println("valor de la 4 banda: " + mediaPlayer.getAudioEqualizer().getBands().get(4).getGain());

            }
        });
        reproductor.slider6.setValue(a[5]);
        reproductor.slider6.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number oldValue, Number newValue) {
                mediaPlayer.getAudioEqualizer().getBands().get(5).setGain((newValue.doubleValue() * 0.36 - 24)); // Se pasa del intervalo [0, 100] al [-24, 12]
                System.out.println("valor de la 5 banda: " + mediaPlayer.getAudioEqualizer().getBands().get(5).getGain());

            }
        });
        reproductor.slider7.setValue(a[6]);
        reproductor.slider7.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number oldValue, Number newValue) {
                mediaPlayer.getAudioEqualizer().getBands().get(6).setGain((newValue.doubleValue() * 0.36 - 24)); // Se pasa del intervalo [0, 100] al [-24, 12]
                System.out.println("valor de la 6 banda: " + mediaPlayer.getAudioEqualizer().getBands().get(6).getGain());

            }
        });
        reproductor.slider8.setValue(a[7]);
        reproductor.slider8.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number oldValue, Number newValue) {
                mediaPlayer.getAudioEqualizer().getBands().get(7).setGain((newValue.doubleValue() * 0.36 - 24)); // Se pasa del intervalo [0, 100] al [-24, 12]
                System.out.println("valor de la 7 banda: " + mediaPlayer.getAudioEqualizer().getBands().get(7).getGain());

            }
        });
        reproductor.slider9.setValue(a[8]);
        reproductor.slider9.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number oldValue, Number newValue) {
                mediaPlayer.getAudioEqualizer().getBands().get(8).setGain((newValue.doubleValue() * 0.36 - 24)); // Se pasa del intervalo [0, 100] al [-24, 12]
                System.out.println("valor de la 8 banda: " + mediaPlayer.getAudioEqualizer().getBands().get(8).getGain());

            }
        });
        reproductor.slider10.setValue(a[9]);
        reproductor.slider10.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number oldValue, Number newValue) {
                mediaPlayer.getAudioEqualizer().getBands().get(9).setGain(newValue.doubleValue() * 0.36 - 24); // Se pasa del intervalo [0, 100] al [-24, 12]
                System.out.println("valor de la 9 banda: " + mediaPlayer.getAudioEqualizer().getBands().get(9).getGain());

            }
        });

    }

}
