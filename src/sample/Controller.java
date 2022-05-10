package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;

public class Controller {

    @FXML
    private TextArea txtArea;

    @FXML
    private Label l1;

    @FXML
    private Label l2;

    @FXML
    private Label l3;

    @FXML
    private Label l4;

    @FXML
    private Label l5;

    @FXML
    private Label l6;

    @FXML
    private Label l7;

    @FXML
    private Label l8;

    @FXML
    private Label l9;

    @FXML
    private TextArea txtNumbers;

    @FXML
    private TextArea txtOpponent;

    @FXML
    private Button btnRules;

    private ClientRMI client;

    void opponentTurnMark(int number){
        if(number==1){
            l1.setVisible(false);
            txtOpponent.appendText(" 1 ");
        }
        if(number==2){
            l2.setVisible(false);
            txtOpponent.appendText(" 2 ");
        }
        if(number==3){
            l3.setVisible(false);
            txtOpponent.appendText(" 3 ");
        }
        if(number==4){
            l4.setVisible(false);
            txtOpponent.appendText(" 4 ");
        }
        if(number==5){
            l5.setVisible(false);
            txtOpponent.appendText(" 5 ");
        }
        if(number==6){
            l6.setVisible(false);
            txtOpponent.appendText(" 6 ");
        }
        if(number==7){
            l7.setVisible(false);
            txtOpponent.appendText(" 7 ");
        }
        if(number==8){
            l8.setVisible(false);
            txtOpponent.appendText(" 8 ");
        }
        if(number==9){
            l9.setVisible(false);
            txtOpponent.appendText(" 9 ");
        }
    }

    public void setTxtArea(String str){txtArea.setText(str);}

    @FXML
    void l1Click(MouseEvent event) {
        if(l1.isVisible()&&client.isMyTurn()) {
            l1.setVisible(false);
            client.notifyServer(1);
            txtNumbers.appendText(" 1 ");
        }
    }

    @FXML
    void l2Click(MouseEvent event) {
        if(l2.isVisible()&&client.isMyTurn()) {
            l2.setVisible(false);
            client.notifyServer(2);
            txtNumbers.appendText(" 2 ");
        }
    }

    @FXML
    void l3Click(MouseEvent event) {
        if(l3.isVisible()&&client.isMyTurn())
        {
            l3.setVisible(false);
            client.notifyServer(3);
            txtNumbers.appendText(" 3 ");
        }
    }

    @FXML
    void l4Click(MouseEvent event) {
        if(l4.isVisible()&&client.isMyTurn()) {
            l4.setVisible(false);
            client.notifyServer(4);
            txtNumbers.appendText(" 4 ");
        }

    }

    @FXML
    void l5Click(MouseEvent event) {
        if(l5.isVisible()&&client.isMyTurn()){
            l5.setVisible(false);
            client.notifyServer(5);
            txtNumbers.appendText(" 5 ");
        }

    }

    @FXML
    void l6Click(MouseEvent event) {
        if(l6.isVisible()&&client.isMyTurn()){
            l6.setVisible(false);
            client.notifyServer(6);
            txtNumbers.appendText(" 6 ");
        }
    }

    @FXML
    void l7Click(MouseEvent event) {
        if(l7.isVisible()&&client.isMyTurn()){
            l7.setVisible(false);
            client.notifyServer(7);
            txtNumbers.appendText(" 7 ");
        }

    }

    @FXML
    void l8Click(MouseEvent event) {
        if(l8.isVisible()&&client.isMyTurn()){
            l8.setVisible(false);
            client.notifyServer(8);
            txtNumbers.appendText(" 8 ");
        }

    }

    @FXML
    void l9Click(MouseEvent event) {
        if(l9.isVisible()&&client.isMyTurn()){
            l9.setVisible(false);
            client.notifyServer(9);
            txtNumbers.appendText(" 9 ");
        }
    }

    @FXML
    void btnRulesOnAction(ActionEvent event) {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setHeight(250);
        alert.setWidth(250);
        alert.setHeaderText("Rules");
        alert.setTitle("Get 15");
        alert.setContentText("You and your opponent take alternate turns, each taking a number. Each number can be taken only once:" +
                " If you opponent has selected a number, you can no longer take it. The first person to have any three numbers that total 15 wins the game.");
        alert.showAndWait();
    }

    @FXML
    void initialize(){
        client=new ClientRMI();
        client.setController(this);
        try {
            client.initialize();
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}

