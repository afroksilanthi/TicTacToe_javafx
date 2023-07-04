package TicTacToe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SceneController2 {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private String playerSymbol;

    @FXML
    private Text winnertext;

    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setPlayerSymbol(String symbol) {
        playerSymbol = symbol;
    }

    private MediaPlayer mediaPlayer;

    public void setmediaPlayer(MediaPlayer mediaplayer) {
        mediaPlayer = mediaplayer;
    }

    @FXML
    private Slider volumeSlider;

    public void setVolumeSlider(Slider volumeSlider) {
        this.volumeSlider = volumeSlider;
    }

    public void controlvolume() {
        mediaPlayer.volumeProperty().bind(volumeSlider.valueProperty());
    }

    @FXML
    public void handleVolumeControl() {
        double volume = volumeSlider.getValue() / 100.0; // Scale the slider value to the range [0.0, 1.0]
        mediaPlayer.setVolume(volume);
    }

    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;
    ArrayList<Button> buttons;

    @FXML
    public void handleButton1(ActionEvent event) {
        winnertext.setText(" ");
        buttons = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));
        for (int i = 0; i < 9; i++) {
            if (event.getSource() == buttons.get(i)) {
                buttons.get(i).setText(playerSymbol);
                buttons.get(i).setDisable(true);
            }

        }
        checkIfGameIsOver();
        if (!gameWon) {
            makeAIMove();

        }

    }

    public void makeAIMove() {
        buttons = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));
        ArrayList<Button> availableButtons = new ArrayList<>();

        for (Button button : buttons) {
            if (button.getText().isEmpty()) {
                availableButtons.add(button);
            }
        }
        if (availableButtons.isEmpty()) {
            return;
        }
        Random random = new Random();
        int index = random.nextInt(availableButtons.size());
        
        
        Button randomButton = availableButtons.get(index);
        if (playerSymbol.equals("X")) {
            randomButton.setText("O");
            randomButton.setTextFill(Color.rgb(7, 255, 238));
        } else {
            randomButton.setText("X");
            randomButton.setTextFill(Color.rgb(7, 255, 238));
        }
        randomButton.setDisable(true);
        checkIfGameIsOver();
    }

@FXML
public void restartGame(ActionEvent event) {
    gameWon = false;
    invisablelines();
    button1.setText("");
    button2.setText("");
    button3.setText("");
    button4.setText("");
    button5.setText("");
    button6.setText("");
    button7.setText("");
    button8.setText("");
    button9.setText("");

    buttons = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));

    winnertext.setText("Your Turn!");

    for (Button button : buttons) {
        button.setDisable(false);
        button.setTextFill(Color.rgb(94, 255, 80));
    }
}


    @FXML
    private Text scoreX;
    @FXML
    private Text scoreO;
    @FXML
    private Text scoreTie;

    private int xi = 0;
    private int o = 0;
    private int t = 0;
    private boolean gameWon = false;

    
    
    private void handleWin(String symbol) {
        if (!gameWon) {
            gameWon = true;
            winnertext.setText(symbol + " won!");
            if (symbol.equals("X")) {
                xi += 1;
                scoreX.setText(Integer.toString(xi));
            } else if (symbol.equals("O")) {
                o += 1;
                scoreO.setText(Integer.toString(o));
            }
            for (int b = 0; b < 9; b++) {
                buttons.get(b).setDisable(true);
            }
            for (Button button : buttons) {
                button.setDisable(true);
            }

        }
    }
    public void checkIfGameIsOver() {
        boolean isBoardFull = true;
        String[] symbols = {"X", "O"};
        for (String symbol : symbols) {
            //orizontia
            if (button1.getText().equals(symbol) && button2.getText().equals(symbol) && button3.getText().equals(symbol)) {
                handleWin(symbol);
                Line1.setVisible(true);
            } else if (button4.getText().equals(symbol) && button5.getText().equals(symbol) && button6.getText().equals(symbol)) {
                handleWin(symbol);
                Line2.setVisible(true);
            } else if (button7.getText().equals(symbol) && button8.getText().equals(symbol) && button9.getText().equals(symbol)) {
                handleWin(symbol);
                Line3.setVisible(true);
            } //katheta
            else if (button1.getText().equals(symbol) && button4.getText().equals(symbol) && button7.getText().equals(symbol)) {
                handleWin(symbol);
                Line4.setVisible(true);
            } else if (button2.getText().equals(symbol) && button5.getText().equals(symbol) && button8.getText().equals(symbol)) {
                handleWin(symbol);
                Line5.setVisible(true);
            } else if (button3.getText().equals(symbol) && button6.getText().equals(symbol) && button9.getText().equals(symbol)) {
                handleWin(symbol);
                Line6.setVisible(true);
            } //diagonia
            else if (button1.getText().equals(symbol) && button5.getText().equals(symbol) && button9.getText().equals(symbol)) {
                handleWin(symbol);
                Line7.setVisible(true);
            } else if (button3.getText().equals(symbol) && button5.getText().equals(symbol) && button7.getText().equals(symbol)) {
                handleWin(symbol);
                Line8.setVisible(true);
            }
        }
        for (Button button : buttons) {
            if (button.getText().isEmpty()) {
                isBoardFull = false;
                break;
            }
        }
        if (isBoardFull && !gameWon) {
            t++;
            scoreTie.setText(Integer.toString(t));
            winnertext.setText("It's a tie!");
        }
    }


    @FXML
    private Line Line1;
    @FXML
    private Line Line2;
    @FXML
    private Line Line3;
    @FXML
    private Line Line4;
    @FXML
    private Line Line5;
    @FXML
    private Line Line6;
    @FXML
    private Line Line7;
    @FXML
    private Line Line8;

    private void invisablelines() {
        Line1.setVisible(false);
        Line2.setVisible(false);
        Line3.setVisible(false);
        Line4.setVisible(false);
        Line5.setVisible(false);
        Line6.setVisible(false);
        Line7.setVisible(false);
        Line8.setVisible(false);
    }

}
