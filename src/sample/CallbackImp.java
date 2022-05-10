package sample;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CallbackImp extends UnicastRemoteObject implements Callback {

    private ClientRMI client;

    public CallbackImp(Object client) throws RemoteException {
        this.client = (ClientRMI) client;
    }

    protected CallbackImp() throws RemoteException {
    }

    @Override
    public void takeTurn(boolean turn) throws RemoteException {
        this.client.setMyTurn(turn);
    }

    @Override
    public void notify(String message) throws RemoteException {
        this.client.setMessage(message);
    }

    @Override
    public void opponentMove(int number) throws RemoteException {
        this.client.opponentMove(number);
    }
}
