/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomultimedia_1;

/*import animatefx.animation.FadeIn;
import animatefx.animation.FadeInUp;
import animatefx.animation.FadeOutDown;
import com.jfoenix.controls.JFXButton;*/
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
/*import org.json.JSONArray;
import org.json.JSONObject;*/

import javax.imageio.ImageIO;
import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Random;

public class Player {

    Media media;
    MediaPlayer mediaPlayer;

    String source;
    double totalCurr;

    boolean isPlaying = false;
    boolean isActive = false;

    private Thread updaterThread;

    //int songIndex;

    reproductorController reproductor;

    //String currentPlaylistName = "";

    String youtubeExecName = "youtube-dl.exe";

    public Player() {
        isPlaying = false;
        isActive = false;
    }
    
    boolean playList;

    public Player(reproductorController d, boolean pl) {
        reproductor = d;
        this.playList = pl;

        //playSong(inputIndex);
    }

    public void setVolume(float volume){
        if(isActive)
            mediaPlayer.setVolume(volume/100);
    }
    
    public void playSong(int index) {
        Platform.runLater(() -> {
            reproductor.previous.setDisable(false);
            reproductor.next.setDisable(false);
            reproductor.shuffle.setDisable(false);
            reproductor.repeat.setDisable(false);
            reproductor.play.setDisable(false);
            reproductor.fav.setDisable(false);
        });
        
        x = new Thread(new Task<Void>() {
            @Override
            protected Void call() {
                try {

                    //HashMap<String,Object> songDetails = reproductor.cachedPlaylist.get(currentPlaylistName).get(index);
                    File song = reproductor.loadedSong;

                    isActive = true;
                    if (playList) {
                        /*while (true) {
                            try {
                                for (Node eachNode : dash.playlistListView.getItems()) {
                                    HBox x = (HBox) eachNode;

                                    if (x.getChildren().get(0).getId().equals(songIndex + "")) {
                                        Platform.runLater(() -> dash.playlistListView.getSelectionModel().select(x));
                                        break;
                                    }
                                }
                                break;
                            } catch (ConcurrentModificationException e) {
                                System.out.println("concurrent exception, retrying ...");
                            }
                        }*/
                    }else{
                    }
                    
                    if (true) { //TODO cambiar a local
                        java.net.URI uri = song.toURI();
                        source = uri.toString();
                        //TODO foto del album
                        /*Platform.runLater(() -> {
                            if (songDetails.containsKey("album_art")) {
                                try {
                                    Image x = SwingFXUtils.toFXImage(ImageIO.read(new ByteArrayInputStream((byte[]) songDetails.get("album_art"))), null);
                                    dash.albumArtImgView.setImage(x);
                                } catch (IOException e) {
                                    dash.albumArtImgView.setImage(dash.defaultAlbumArt);
                                    e.printStackTrace();
                                }
                            } else {
                                dash.albumArtImgView.setImage(dash.defaultAlbumArt);
                            }

                            show();
                        });*/
                    } /*else if (songDetails.get("location").toString().equals("youtube")) {
                        Image x = new Image(songDetails.get("thumbnail").toString());
                        Platform.runLater(() -> {
                            dash.songNameLabel.setText(songDetails.get("title").toString());
                            dash.artistLabel.setText(songDetails.get("channelTitle").toString());
                            dash.albumArtImgView.setImage(x);
                            show();
                        });

                        String videoURL = songDetails.getOrDefault("videoURL", "null").toString();
                        if (videoURL.equals("null")) {
                            String youtubeDLQuery = youtubeExecName + " -f 18 -g https://www.youtube.com/watch?v=" + songDetails.get("videoID");
                            Process p = Runtime.getRuntime().exec(youtubeDLQuery);
                            InputStream i = p.getInputStream();
                            InputStream e = p.getErrorStream();

                            String result = "";
                            while (true) {
                                int c = i.read();
                                if (c == -1) {
                                    break;
                                }
                                result += (char) c;
                            }

                            if (result.length() == 0) {
                                //get errors
                                String errResult = "";
                                while (true) {
                                    int c = e.read();
                                    if (c == -1) {
                                        break;
                                    }
                                    errResult += (char) c;
                                }

                                e.close();
                                dash.showErrorAlert("Uh OH!", "Unable to play, probably because Age Restricted/Live Video. If not, check connection and try again!\n\n" + errResult);
                                stop();
                                hide();
                                return null;
                            }

                            i.close();
                            e.close();

                            videoURL = result.substring(0, result.length() - 1);
                            songDetails.put("videoURL", videoURL);
                            dash.cachedPlaylist.get(currentPlaylistName).get(songIndex).put("videoURL", videoURL);
                        }

                        source = videoURL;

                    }*/

                    /*if (!isActive || index != songIndex) {
                        System.out.println("Skipping because video no longer required ...");
                        return null;
                    }*/

                    System.out.println("starting ...");

                    media = new Media(source);

                    mediaPlayer = new MediaPlayer(media);

                    media.setOnError(() -> {
                        stop();
                        media.getError().printStackTrace();
                    });

                    mediaPlayer.setOnReady(() -> {

                        System.out.println("Start Playing ...");

                        totalCurr = media.getDuration().toSeconds();

                        Platform.runLater(() -> {
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
                        });

                        mediaPlayer.play();
                        isPlaying = true;

                        startUpdating();
                    });

                    mediaPlayer.setOnEndOfMedia(() -> {
                        if(playList){
                            onEndOfMediaTrigger();
                        }else{
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

    public void onEndOfMediaTrigger() {
        /*if (dash.isShuffle) {
            playNextRandom();
        } else {
            if (dash.isRepeat) {
                setPos(0);
            } else {
                if (songIndex == (dash.cachedPlaylist.get(currentPlaylistName).size() - 1)) {
                    stop();
                    hide();
                } else {
                    playNext();
                }
            }
        }*/
        reproductor.playActive = false;
        reproductor.play.setImage(reproductor.playImg);
    }

    public void playNext() {
        /*if (songIndex < (dash.cachedPlaylist.get(currentPlaylistName).size() - 1)) {
            if (isPlaying) {
                mediaPlayer.stop();
                mediaPlayer.dispose();
            }
            playSong((songIndex + 1));
        }*/
    }

    private void playNextRandom() {
        /*if (dash.isRepeat) {
            setPos(0);
        } else {
            if (isPlaying) {
                mediaPlayer.stop();
                mediaPlayer.dispose();
            }
            mediaPlayer.dispose();
            playSong((new Random().nextInt(dash.cachedPlaylist.get(currentPlaylistName).size())));
        }*/
    }

    public void playPrevious() {
        /*if (songIndex > 0) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
            playSong((songIndex - 1));
        }*/
    }

    public void setPos(double newDurSecs) {
        mediaPlayer.seek(Duration.seconds(newDurSecs));
    }

    public void pauseResume() {
        new Thread(new Task() {
            @Override
            protected Object call() throws Exception {
                if (mediaPlayer.getStatus().equals(MediaPlayer.Status.PAUSED)) {
                    isPlaying = true;
                    reproductor.playActive = false;
                    reproductor.play.setImage(reproductor.pauseImg);
                    mediaPlayer.play();
                } else if (mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)) {
                    isPlaying = false;
                    reproductor.playActive = true;
                    reproductor.play.setImage(reproductor.playImg);
                    mediaPlayer.pause();
                }
                return null;
            }
        }).start();
    }

    public void stop() {
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

    private void startUpdating() {
        updaterThread = new Thread(new Task<Void>() {
            @Override
            protected Void call() {
                try {
                    while (isActive) {
                        if (mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)) {
                            double currSec = mediaPlayer.getCurrentTime().toMillis();
                            String currentSimpleTimeStamp = reproductor.durationFormatted((long) currSec);
                            Platform.runLater(() -> reproductor.timeCounter.setText(currentSimpleTimeStamp));

                            double currentProgress = currSec/1000;
                            if (!reproductor.sliderDuration.isValueChanging()) {
                                reproductor.sliderDuration.setValue(currentProgress);
                                //dash.refreshSlider(dash.songSeek);
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

    double currentP = 0.0;
    
    
}
