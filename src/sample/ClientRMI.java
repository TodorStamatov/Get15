package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientRMI extends Application {
    private char Id;
    private boolean myTurn = false;
    private ServerInterface server;
    private Controller controller;

    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }

    public boolean isMyTurn() {
        return myTurn;
    }

    public void setMessage(String message) {
        controller.setTxtArea(message);
    }

    public void setController(Controller controller) {
        if (controller != null) {
            this.controller = controller;
        }
    }

    public void opponentMove(int number){
        controller.opponentTurnMark(number);
    }


    protected boolean initialize() throws Exception {
        String host = "";
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            server = (ServerInterface) registry.lookup("Get 15");
            System.out.println("Server object " + server + " is found");
        } catch (Exception ex) {
            System.out.println(ex);
        }
        CallbackImp callback = new CallbackImp(this);

        if ((Id = server.connect((Callback) callback)) != ' ') {
            System.out.println("connected as player " + Id);
            return true;
        } else {
            System.out.println("there are already 2 players");
            return false;
        }
    }

    public void notifyServer(int number) {
            try {
                server.move(number, this.Id);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Get 15");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
