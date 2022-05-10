package sample;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Callback extends Remote {
    void takeTurn(boolean turn) throws RemoteException;

    void notify(String message) throws RemoteException;

    void opponentMove(int number) throws RemoteException;
}
