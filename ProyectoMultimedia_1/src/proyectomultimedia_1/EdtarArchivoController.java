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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static proyectomultimedia_1.ProyectoMultimedia_1.preferences;
import static proyectomultimedia_1.reproductorController.PLAYER;

/**
 * FXML Controller class
 *
 * @author Sergio Bonachera
 */
public class EdtarArchivoController implements Initializable {

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

    private File file;
    private Stage dialogStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void cancelViewOnClick(MouseEvent event) {
        dialogStage.close();
    }

    @FXML
    private void saveViewOnClick(MouseEvent event) throws IOException, UnsupportedTagException, InvalidDataException, InterruptedException {
        Mp3File mp3file = new Mp3File(file.getAbsoluteFile());
        ID3v2 tag;
        if (mp3file.hasId3v2Tag()) {
            tag = mp3file.getId3v2Tag();
        } else {
            // mp3 does not have an ID3v2 tag, let's create one..
            tag = new ID3v24Tag();
            mp3file.setId3v2Tag(tag);
        }
        if (!editViewRating.getText().isEmpty() && (Integer.parseInt(editViewRating.getText()) < -1 || Integer.parseInt(editViewRating.getText()) > 5)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error en el Rating"); //TODO Internacionalizar
            alert.setHeaderText("El rating debe de ser entre 0 y 5. Para ponerlo por defecto introduzca -1 o dejelo en blanco");

            alert.showAndWait();
        } else {
            if (editViewRating.getText().isEmpty()) {
                tag.setWmpRating(-1);
            }else{
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

            dialogStage.close();
        }
    }

    public void fillWindow(File file) throws IOException, UnsupportedTagException, InvalidDataException {
        Mp3File mp3file = new Mp3File(file.getAbsoluteFile());
        if (mp3file.hasId3v2Tag()) {
            ID3v2 id3v2Tag = mp3file.getId3v2Tag();
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

    public void setFile(File file) {
        this.file = file;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
}
