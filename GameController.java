package test;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class GameController {

    @FXML
    private GridPane grid;

    private Button[][] cards;

    private final int SIZE = 5;

    private int score = 0;

    private int cardsTurned = 0;

    public void initialize(){
        cards = new Button[SIZE][SIZE];
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                cards[i][j] = new Button("");
                cards[i][j].setPrefSize(120, 80);
                cards[i][j].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        handleButton(actionEvent);
                    }
                });
                grid.add(cards[i][j], j, i);
            }
        }
    }

    private void handleButton(ActionEvent actionEvent){
        Random r = new Random();
        Button b = (Button)actionEvent.getSource();
        int val = r.nextInt(5);
        val *= 100;
        b.setText(val + "");
        if(val == 0) {
            endGame();
        }else{
            b.setDisable(true);
            score += val;
        }
        cardsTurned++;
        if(cardsTurned == 25)
            endGame();
    }

    private void endGame(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("your score is: " + score + "\nGood Job!");
        alert.showAndWait();
        restart();
    }

    private void restart(){
        score = 0;
        cardsTurned = 0;
        initialize();
    }
}
