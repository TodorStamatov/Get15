package sample;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerImp extends UnicastRemoteObject implements ServerInterface {
    private Callback player1=null;
    private Callback player2=null;
    private char[] numbers=new char[10];
    protected ServerImp() throws RemoteException {
        super();
    }

    protected ServerImp(int port) throws RemoteException {
        super(port);
    }

    public void setNumbers() {
        for(int i=0;i<10;i++){
            numbers[i]=' ';
        }
    }

    @Override
    public char connect(Callback client) throws RemoteException {
        if(player1==null){
            player1=client;
            player1.notify("You are connected! Please wait for a second player to join! ");
            return '1';
        }else if(player2==null){
            player2=client;
            player2.notify("You are connected! Please wait for the other player to move!");
            setNumbers();
            player2.takeTurn(false);
            player1.notify("It's your turn!");
            player1.takeTurn(true);
            return '2';
        }else{
            client.notify("You can't play because there are already 2 players!");
            return ' ';
        }
    }

    @Override
    public void move(int number, char playerID) throws RemoteException {

        numbers[number]=playerID;

        if(playerID=='1'){
            player2.opponentMove(number);
        }else{
            player1.opponentMove(number);
        }
        if(isWon(playerID)){
            if(playerID=='1'){
                player1.notify("YOU WON! :)");
                player2.notify("YOU LOST! :(");
                player1.takeTurn(false);
            }else{
                player2.notify("YOU WON! :)");
                player1.notify("YOU LOST! :(");
                player2.takeTurn(false);
            }
        }
        else if(isFull()){
            player1.notify("DRAW");
            player2.notify("DRAW");
            player1.takeTurn(false);
            player2.takeTurn(false);
        }
        else if (playerID=='1'){
            player1.notify("It's other player's turn! Please wait!");
            player1.takeTurn(false);
            player2.notify("It's your turn!");
            player2.takeTurn(true);
        }
        else if (playerID=='2'){
            player2.notify("It's other player's turn! Please wait!");
            player2.takeTurn(false);
            player1.notify("It's your turn!");
            player1.takeTurn(true);
        }


    }

    boolean isWon(char id) {
        if(numbers[4]==id&&numbers[9]==id&&numbers[2]==id){
            return true;
        }
        if(numbers[4]==id&&numbers[3]==id&&numbers[8]==id){
            return true;
        }
        if(numbers[8]==id&&numbers[1]==id&&numbers[6]==id){
            return true;
        }
        if(numbers[6]==id&&numbers[7]==id&&numbers[2]==id){
            return true;
        }
        if(numbers[4]==id&&numbers[5]==id&&numbers[6]==id){
            return true;
        }
        if(numbers[2]==id&&numbers[5]==id&&numbers[8]==id){
            return true;
        }
        return false;
    }

    boolean isFull(){
        for(int i=1;i<10;i++){
            if(numbers[i]==' '){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        try{
            ServerInterface obj=new ServerImp();
            Registry registry= LocateRegistry.createRegistry(1099);
            registry.rebind("Get 15",obj);
            System.out.println("Server "+obj+" is registered");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
