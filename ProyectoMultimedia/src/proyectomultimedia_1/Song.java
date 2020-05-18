/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomultimedia_1;

import java.io.File;
import java.time.LocalDate;

/**
 *
 * @author Irene Maria Padilla Munoz
 */
public class Song {

    public String songName;
    public String artist;
    public String album;
    public LocalDate date;
    public String duration;
    public File file;
    boolean fav;

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
