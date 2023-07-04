package TicTacToe;

import java.io.File;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToScene2(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene2.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        SceneController2 destinationController = loader.getController();
        destinationController.setPlayerSymbol(playerSymbol);
        destinationController.setmediaPlayer(mediaPlayer);
    }

    @FXML
    private AnchorPane scenePane;
    @FXML
    private Button xbutton;
    @FXML
    private Button obutton;

    @FXML
    public void initialize() {
        xbutton.setOnAction(event -> {
            setPlayerSymbol("X");
            xbutton.setDisable(true);
            obutton.setDisable(false);
        });
        obutton.setOnAction(event -> {
            setPlayerSymbol("O");
            obutton.setDisable(true);
            xbutton.setDisable(false);

        });

        Platform.runLater(() -> {
            String musicFileName = "Undertale - Megalovania.mp3";
            String currentDirectory = System.getProperty("user.dir");
            String musicFilePath = currentDirectory + File.separator + musicFileName;

            File musicFile = new File(musicFilePath);

            if (musicFile.exists()) {
                Media media = new Media(musicFile.toURI().toString());

                mediaPlayer = new MediaPlayer(media);

                // Play the music
                mediaPlayer.play();
            } else {
                System.err.println("Music file not found: " + musicFilePath);
            }
        });

    }
    private String playerSymbol = "X";

    public void setPlayerSymbol(String symbol) {
        playerSymbol = symbol;
    }

    public void logout(ActionEvent event) {
        stage = (Stage) scenePane.getScene().getWindow();
        System.out.print("GoodBye!\n");
        stage.close();
    }

    @FXML
    private ToggleButton musicToggleButton;

    private MediaPlayer mediaPlayer;
    
     public void setmediaPlayer(MediaPlayer mediaplayer) {
        mediaPlayer = mediaplayer;
    } 
     
    @FXML
    public void handleMusicToggle(ActionEvent event) {
        if (musicToggleButton.isSelected()) {
            // Stop the music
            if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                mediaPlayer.stop();
                musicToggleButton.setText("🔇");
            }
        } else {
            // Start or resume the music
            if (mediaPlayer != null && mediaPlayer.getStatus() != MediaPlayer.Status.PLAYING) {
                mediaPlayer.play();
                musicToggleButton.setText("🔊");
            }
        }
    }
}
